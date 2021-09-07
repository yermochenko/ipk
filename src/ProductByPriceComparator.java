import java.util.Comparator;

public class ProductByPriceComparator implements Comparator<Product> {
	@Override
	public int compare(Product a, Product b) {
		return Long.compare(a.getPrice(), b.getPrice());
	}
}
