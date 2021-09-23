package by.vsu.ipk.logic;
import java.util.Comparator;

import by.vsu.ipk.domain.Product;

public class ProductByNameComparator implements Comparator<Product> {
	@Override
	public int compare(Product a, Product b) {
		return a.getName().compareTo(b.getName());
	}
}
