
public class ProductByNameComparator implements Comparator<Product> {
	@Override
	public int compare(Product a, Product b) {
		return a.getName().compareTo(b.getName());
	}
}
