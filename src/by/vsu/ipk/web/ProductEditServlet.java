package by.vsu.ipk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductDao;
import by.vsu.ipk.storage.ProductDaoFactory;

public class ProductEditServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if(id != null) {
			try(ProductDaoFactory factory = new ProductDaoFactory()) {
				ProductDao productDao = factory.getInstance();
				Product product = productDao.read(Long.valueOf(id));
				if(product != null) {
					req.setAttribute("product", product);
				} else {
					throw new IllegalArgumentException();
				}
			} catch(IllegalArgumentException e) {
				resp.sendError(404);
				return;
			}
		}
		req.getRequestDispatcher("/WEB-INF/jsp/product/edit.jsp").forward(req, resp);
	}
}
