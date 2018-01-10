package component;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.LineFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.statistics.Regression;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import model.PropertySale;

public class PSChart {

	private JFreeChart chart;
	private ChartPanel chartPanel;
	
	public PSChart(Rectangle rectangle) {
		chart = ChartFactory.createScatterPlot("Year vs Price", "Year of Sale", "Price (£)",
				null);
		chart.getXYPlot().setDomainAxis(new DateAxis());
		chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(rectangle);
		chartPanel.setVisible(false);
	}
	
	public ChartPanel getChartPanel() {
		return chartPanel;
	}
	public void updateDataset(List<PropertySale> propertySales) {
		TimeSeriesCollection chartDataset = createScatterPlotDataset(propertySales);
		chart.getXYPlot().setDataset(0, chartDataset);
		drawLineOfBestFit(chartDataset);
		chartPanel.setVisible(true);
	}
	
	private TimeSeriesCollection createScatterPlotDataset(List<PropertySale> propertySales) {
		TimeSeriesCollection dataset = new TimeSeriesCollection();
		TimeSeries series = new TimeSeries("One sale");
		for (PropertySale propertySale : propertySales) {
			series.addOrUpdate(new Day(propertySale.getSaleDate().toDate()),
					propertySale.getPrice().getAmount());
		
		}
		dataset.addSeries(series);

		return dataset;
	}

	private void drawLineOfBestFit(TimeSeriesCollection dataset) {
		double regressionParameters[] = Regression.getOLSRegression(dataset, 0);

		LineFunction2D lineFunction = new LineFunction2D(regressionParameters[0],
				regressionParameters[1]);

		XYDataset xyDataset = DatasetUtilities.sampleFunction2D(lineFunction,
				dataset.getDomainLowerBound(true), dataset.getDomainUpperBound(true), 100,
				"Line of Best Fit");

		XYPlot plot = chart.getXYPlot();
		plot.setDataset(1, xyDataset);
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
		renderer.setSeriesPaint(0, Color.YELLOW);
		plot.setRenderer(1, renderer);
	}
}
