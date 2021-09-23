package by.vsu.ipk.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int x = (int)(Math.random() * 256);
		int y = (int)(Math.random() * 256);
		int z = (int)(Math.random() * 256);
		resp.getWriter().printf("<p style=\"color: #%02X%02X%02X; background-color: #%02X%02X%02X; padding: 20px; border-radius: 20px; font-size: 20pt;\">Hello, World!!!</p>", x, y, z, 255 - x, 255 - y, 255 - z);
	}

}
