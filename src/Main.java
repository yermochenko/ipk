import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void out(List<Product> products) {
		System.out.println("========\nПродукты:\n---------");
		for(Product product : products) {
			System.out.printf(
				"%s, %d руб. %02d коп.\n",
				product.getName(),
				product.getPrice() / 100,
				product.getPrice() % 100
			);
		}
	}

	public static ProductReader getReader() {
		return new ProductFileReader();
	}

	public static void main(String[] args) {
		ProductReader reader = getReader();
		List<Product> products = reader.read();
		out(products);
		Collections.sort(products, new ProductByNameComparator());
		out(products);
		Collections.sort(products, new ProductByPriceComparator());
		out(products);
		Collections.sort(products, new Comparator<Product>() {
			@Override
			public int compare(Product a, Product b) {
				int result = a.getName().compareToIgnoreCase(b.getName());
				if(result == 0) {
					result = Long.compare(a.getPrice(), b.getPrice());
				}
				return result;
			}
		});
		out(products);
	}

}
