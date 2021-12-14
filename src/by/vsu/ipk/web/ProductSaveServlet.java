package by.vsu.ipk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductDao;
import by.vsu.ipk.storage.ProductDaoFactory;

public class ProductSaveServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String priceRub = req.getParameter("price-rub");
		String priceKop = req.getParameter("price-kop");
		try {
			Product product = new Product();
			if(id != null) {
				product.setId(Long.valueOf(id));
			}
			if(name == null || name.isEmpty()) {
				throw new IllegalArgumentException();
			}
			product.setName(name);
			product.setPrice(Long.parseLong(priceRub) * 100 + Long.parseLong(priceKop));
			ProductDao productDao = ProductDaoFactory.getInstance();
			if(id != null) {
				productDao.update(product);
			} else {
				productDao.create(product);
			}
			resp.sendRedirect(req.getContextPath() + "/product/list.html");
		} catch(IllegalArgumentException e) {
			resp.sendError(400);
		}
	}
}
