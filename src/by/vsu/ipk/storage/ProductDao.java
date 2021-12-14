package by.vsu.ipk.storage;
import java.util.List;

import by.vsu.ipk.domain.Product;

public interface ProductDao {
	List<Product> read();

	Product read(Long id);

	Long create(Product product);

	void update(Product product);

	void delete(Long id);
}
