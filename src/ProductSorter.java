import java.util.ArrayList;

public class ProductSorter {
	public static void sort(ArrayList<Product> products, ProductComparator comparator) {
		for(int i = 0; i < products.size() - 1; i++) {
			int imin = i;
			for(int j = i + 1; j < products.size(); j++) {
				Product min = products.get(imin);
				Product cur = products.get(j);
				if(comparator.compare(cur, min) < 0) {
					imin = j;
				}
			}
			Product tmp = products.get(i);       // tmp = p[i]
			products.set(i, products.get(imin)); // p[i] = p[imin]
			products.set(imin, tmp);             // p[imin] = tmp
		}
	}
}
