package by.vsu.ipk.storage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductDao;

public class ProductDaoDatabaseImpl implements ProductDao {
	private Connection connection;

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Product> read() {
		String sql = "SELECT `id`, `name`, `price` FROM `product`";
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			List<Product> products = new ArrayList<>();
			while(resultSet.next()) {
				Product product = new Product();
				product.setId(resultSet.getLong("id"));
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getLong("price"));
				products.add(product);
			}
			return products;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public Product read(Long id) {
		String sql = "SELECT `name`, `price` FROM `product` WHERE `id` = ?";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			Product product = null;
			if(resultSet.next()) {
				product = new Product();
				product.setId(id);
				product.setName(resultSet.getString("name"));
				product.setPrice(resultSet.getLong("price"));
			}
			return product;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public Long create(Product product) {
		String sql = "INSERT INTO `product`(`name`, `price`) VALUES(?, ?)";
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, product.getName());
			statement.setLong(2, product.getPrice());
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys();
			resultSet.next();
			return resultSet.getLong(1);
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			try { resultSet.close(); } catch(Exception e) {}
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void update(Product product) {
		String sql = "UPDATE `product` SET `name` = ?, `price` = ? WHERE `id` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setLong(2, product.getPrice());
			statement.setLong(3, product.getId());
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { statement.close(); } catch(Exception e) {}
		}
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM `product` WHERE `id` = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { statement.close(); } catch(Exception e) {}
		}
	}
}
