package by.vsu.ipk.web.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.vsu.ipk.domain.Product;

public class ProductListViewServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) req.getAttribute("products");
		int size = products.size();
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<meta charset=\"UTF-8\">");
		pw.println("<title>Продукты</title>");
		pw.println("<style>");
		pw.println("table {border-collapse: collapse;}");
		pw.println("th {background: #CCFFCC;}");
		pw.println("th, td {border: 1px solid black;}");
		pw.println("</style>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<p>Продукты</p>");
		pw.println("<table>");
		pw.println("<tr>");
		pw.println("<th>Название</th>");
		pw.println("<th>Цена</th>");
		pw.println("</tr>");
		for(Product product : products) {
			pw.println("<tr>");
			pw.printf("<td>%s</td>\n", product.getName());
			pw.printf("<td>%d руб. %02d коп.</td>\n", product.getPrice() / 100, product.getPrice() % 100);
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.printf("<p>Итого продуктов: %d</p>", size);
		pw.println("</body>");
		pw.println("</html>");
	}
}
