
public class ProductByPriceComparator extends ProductComparator {
	@Override
	public int compare(Product a, Product b) {
		return Long.compare(a.getPrice(), b.getPrice());
	}
}
