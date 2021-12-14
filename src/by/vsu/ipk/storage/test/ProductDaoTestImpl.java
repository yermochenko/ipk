package by.vsu.ipk.storage.test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductDao;

public class ProductDaoTestImpl implements ProductDao {
	private static Map<Long, Product> products = new HashMap<>();

	static {
		Product product;
		product = new Product();
		product.setId(1L);
		product.setName("Картофель");
		product.setPrice(234L);
		products.put(product.getId(), product);
		product = new Product();
		product.setId(2L);
		product.setName("Авокадо");
		product.setPrice(345L);
		products.put(product.getId(), product);
		product = new Product();
		product.setId(3L);
		product.setName("Мандарин");
		product.setPrice(123L);
		products.put(product.getId(), product);
	}

	public List<Product> read() {
		return new ArrayList<>(products.values());
	}

	@Override
	public Product read(Long id) {
		return products.get(id);
	}

	@Override
	public Long create(Product product) {
		Long id = 1L;
		if(!products.isEmpty()) {
			id += Collections.max(products.keySet());
		}
		product.setId(id);
		products.put(id, product);
		return id;
	}

	@Override
	public void update(Product product) {
		if(products.containsKey(product.getId())) {
			products.put(product.getId(), product);
		}
	}

	@Override
	public void delete(Long id) {
		products.remove(id);
	}
}
