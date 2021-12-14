package by.vsu.ipk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.ipk.storage.ProductDao;
import by.vsu.ipk.storage.ProductDaoFactory;

public class ProductDeleteServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			ProductDao productDao = ProductDaoFactory.getInstance();
			productDao.delete(Long.valueOf(req.getParameter("id")));
			resp.sendRedirect(req.getContextPath() + "/product/list.html");
		} catch(IllegalArgumentException e) {
			resp.sendError(400);
		}
	}
}
