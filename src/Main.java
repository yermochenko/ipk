import java.util.ArrayList;

public class Main {
	public static void out(ArrayList<Product> products) {
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

	public static void main(String[] args) {
		ArrayList<Product> products = ProductReader.read();
		out(products);
		ProductSorter.sort(products, new ProductByNameComparator());
		out(products);
		ProductSorter.sort(products, new ProductByPriceComparator());
		out(products);
	}

}
