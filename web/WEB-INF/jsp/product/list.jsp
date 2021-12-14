<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
			<th></th>
		</tr>
		<c:forEach var="product" items="${products}">
		<tr>
			<td>${product.name}</td>
			<td><fmt:formatNumber maxFractionDigits="0" value="${product.price div 100}"/>&nbsp;руб. <fmt:formatNumber minIntegerDigits="2" value="${product.price mod 100}"/>&nbsp;коп.</td>
			<td>
				<c:url var="editUrl" value="/product/edit.html">
					<c:param name="id" value="${product.id}"/>
				</c:url>
				<a href="${editUrl}">Редактировать</a>
			</td>
		</tr>
		</c:forEach>
	</table>
	<p>Итого продуктов: ${fn:length(products)}</p>
	<c:url var="editUrl" value="/product/edit.html"/>
	<p><a href="${editUrl}">Добавить</a></p>
</body>
</html>