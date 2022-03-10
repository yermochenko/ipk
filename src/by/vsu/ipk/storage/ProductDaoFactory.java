package by.vsu.ipk.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import by.vsu.ipk.storage.db.ProductDaoDatabaseImpl;
//import by.vsu.ipk.storage.test.ProductDaoTestImpl;

public class ProductDaoFactory implements AutoCloseable {
	private Connection connection = null;

	public ProductDaoFactory() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/ipk_2022_db?useUnicode=true&characterEncoding=UTF-8",
				"root",
				""
			);
		} catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public ProductDao getInstance() {
//		return new ProductDaoTestImpl();
		ProductDaoDatabaseImpl dao = new ProductDaoDatabaseImpl();
		dao.setConnection(connection);
		return dao;
	}

	@Override
	public void close() {
		try { connection.close(); } catch(Exception e) {}
	}
}
