<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="by.vsu.ipk.domain.Product"%>
<%
	@SuppressWarnings("unchecked")
	List<Product> products = (List<Product>) request.getAttribute("products");
	int size = products.size();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Продукты</title>
<style>
	table {
		border-collapse: collapse;
	}
	th {
		background: #CCFFCC;
	}
	th, td {
		border: 1px solid black;
		padding: 3px 50px 3px 10px;
	}
</style>
</head>
<body>
	<h1>Продукты</h1>
	<table>
		<tr>
			<th>Название</th>
			<th>Цена</th>
		</tr>
		<%
		for(Product product : products) {
		%>
		<tr>
			<td><%= product.getName() %></td>
			<td><%= product.getPrice() / 100 %> руб. <%= product.getPrice() % 100 %> коп.</td>
		</tr>
		<%
		}
		%>
	</table>
	<p>Итого продуктов: <%= size %></p>
</body>
</html>