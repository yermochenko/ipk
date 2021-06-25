import java.util.ArrayList;

public class ProductTestReader extends ProductReader {
	public ArrayList<Product> read() {
		ArrayList<Product> products = new ArrayList<>();
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
