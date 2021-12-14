package by.vsu.ipk.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductDao;
import by.vsu.ipk.storage.ProductDaoFactory;

public class ProductListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDao productDao = ProductDaoFactory.getInstance();
		List<Product> products = productDao.read();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/WEB-INF/jsp/product/list.jsp").forward(req, resp);
	}
}
