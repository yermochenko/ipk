
public class ProductByNameComparator extends ProductComparator {
	@Override
	public int compare(Product a, Product b) {
		return a.getName().compareTo(b.getName());
	}
}
