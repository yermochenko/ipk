package by.vsu.ipk.logic;
import java.util.Comparator;
import java.util.List;

public class Sorter {
	public static <T> void sort(List<T> arr, Comparator<T> comparator) {
		for(int i = 0; i < arr.size() - 1; i++) {
			int imin = i;
			for(int j = i + 1; j < arr.size(); j++) {
				T min = arr.get(imin);
				T cur = arr.get(j);
				if(comparator.compare(cur, min) < 0) {
					imin = j;
				}
			}
			T tmp = arr.get(i);        // tmp = p[i]
			arr.set(i, arr.get(imin)); // p[i] = p[imin]
			arr.set(imin, tmp);        // p[imin] = tmp
		}
	}
}
