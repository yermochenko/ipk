package by.vsu.ipk.storage.file;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductReader;

public class ProductFileReader implements ProductReader {
	@Override
	public List<Product> read() {
		List<Product> products = new ArrayList<>();
		BufferedReader bufferedReader = null;
		try {
			InputStream inputStream = new FileInputStream("D:\\eclipse-workspaces\\git\\ipk\\products.csv");
			Reader reader = new InputStreamReader(inputStream, "cp1251");
			bufferedReader = new BufferedReader(reader);
			String line;
			while((line = bufferedReader.readLine()) != null) {
				String[] cells = line.split(";");
				Product product = new Product();
				product.setName(cells[0]);
				product.setPrice(Long.valueOf(cells[1]));
				products.add(product);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { bufferedReader.close(); } catch(Exception e) {}
		}
		return products;
	}
}