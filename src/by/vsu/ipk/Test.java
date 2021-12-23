package by.vsu.ipk;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductDao;
import by.vsu.ipk.storage.ProductDaoFactory;

public class Test {

	public static void main(String[] args) throws StreamWriteException, DatabindException, IOException {
		ProductDao productDao = ProductDaoFactory.getInstance();
		List<Product> products = productDao.read();
		for(Product product : products) {
			System.out.println("**********");
			System.out.println("id    = " + product.getId());
			System.out.println("name  = " + product.getName());
			System.out.println("price = " + product.getPrice());
		}
		System.out.println("==================");
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.writeValue(System.out, products);
	}

}
