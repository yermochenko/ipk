package by.vsu.ipk.logic;
import java.util.Comparator;

import by.vsu.ipk.domain.Product;

public class ProductByPriceComparator implements Comparator<Product> {
	@Override
	public int compare(Product a, Product b) {
		return Long.compare(a.getPrice(), b.getPrice());
	}
}
