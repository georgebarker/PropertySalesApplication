CREATE TABLE IF NOT EXISTS sales(
					id TEXT, price INTEGER, sale_date TEXT, postcode TEXT, 
					property_type CHAR, new_build CHAR, lease_type CHAR, paon TEXT, 
					saon TEXT, street TEXT, locality TEXT, town TEXT, 
					district TEXT, county TEXT, category CHAR, status CHAR
					);
CREATE INDEX IF NOT EXISTS i_postcode ON sales(postcode collate nocase);