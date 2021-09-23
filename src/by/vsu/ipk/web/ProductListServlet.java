package by.vsu.ipk.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductReader;
import by.vsu.ipk.storage.file.ProductFileReader;

public class ProductListServlet extends HttpServlet {
	private static ProductReader getReader() {
		return new ProductFileReader();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Product> products = getReader().read();
		req.setAttribute("products", products);
		req.getRequestDispatcher("/WEB-INF/product/list.html").forward(req, resp);
	}
}
