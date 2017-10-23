package service;

import java.util.List;

import dao.PropertySalesDao;
import model.PropertySale;

public class PropertySalesService {
	PropertySalesDao propertySalesDao;
	
	public PropertySalesService(PropertySalesDao propertySalesDao) {
		this.propertySalesDao = propertySalesDao;
	}
	
	public List<PropertySale> getPropertySalesByPostcode(String postcode) {
		return propertySalesDao.findPropertySalesByPostcode(postcode);
	}
}
