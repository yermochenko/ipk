package by.vsu.ipk.web.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.vsu.ipk.domain.Product;
import by.vsu.ipk.storage.ProductDao;
import by.vsu.ipk.storage.ProductDaoFactory;

public class ProductRestServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ProductDaoFactory factory = new ProductDaoFactory()) {
			ProductDao productDao = factory.getInstance();
			ObjectMapper mapper = new ObjectMapper();
			String id = req.getParameter("id");
			if(id == null) {
				List<Product> products = productDao.read();
				resp.setStatus(200);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				mapper.writeValue(resp.getOutputStream(), products);
			} else {
				try {
					Product product = productDao.read(Long.valueOf(id));
					if(product != null) {
						resp.setStatus(200);
						resp.setContentType("application/json");
						resp.setCharacterEncoding("UTF-8");
						mapper.writeValue(resp.getOutputStream(), product);
					} else {
						throw new IllegalArgumentException();
					}
				} catch(IllegalArgumentException e) {
					resp.sendError(404);
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ProductDaoFactory factory = new ProductDaoFactory()) {
			ObjectMapper mapper = new ObjectMapper();
			Product product = mapper.readValue(req.getInputStream(), Product.class);
			ProductDao productDao = factory.getInstance();
			Long id = productDao.create(product);
			product.setId(id);
			resp.setStatus(201);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			mapper.writeValue(resp.getOutputStream(), product);
		}
	}


	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try(ProductDaoFactory factory = new ProductDaoFactory()) {
			ObjectMapper mapper = new ObjectMapper();
			Product product = mapper.readValue(req.getInputStream(), Product.class);
			ProductDao productDao = factory.getInstance();
			productDao.update(product);
			resp.setStatus(204);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = null;
		try {
			id = Long.valueOf(req.getParameter("id"));
		} catch(NumberFormatException e) {}
		if(id != null) {
			try(ProductDaoFactory factory = new ProductDaoFactory()) {
				ProductDao productDao = factory.getInstance();
				productDao.delete(id);
			}
		}
		resp.setStatus(204);
	}
}
