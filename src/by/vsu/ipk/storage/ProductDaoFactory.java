package by.vsu.ipk.storage;

import by.vsu.ipk.storage.test.ProductDaoTestImpl;

public class ProductDaoFactory {
	public static ProductDao getInstance() {
		return new ProductDaoTestImpl();
	}
}
