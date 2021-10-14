package by.vsu.ipk.storage.test;
import java.util.ArrayList;
import java.util.List;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductReader;

public class ProductTestReader implements ProductReader {
	public List<Product> read() {
		List<Product> products = new ArrayList<>();
		Product product;
		product = new Product();
		product.setName("Картофель");
		product.setPrice(234L);
		products.add(product);
		product = new Product();
		product.setName("Авокадо");
		product.setPrice(345L);
		products.add(product);
		product = new Product();
		product.setName("Мандарин");
		product.setPrice(123L);
		products.add(product);
		return products;
	}
}