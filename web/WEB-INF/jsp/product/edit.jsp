<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Продукты</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty product}">
			<h1>Редактирование продукта &laquo;${product.name}&raquo;</h1>
		</c:when>
		<c:otherwise>
			<h1>Добавление нового продукта</h1>
		</c:otherwise>
	</c:choose>
	<c:url var="saveUrl" value="/product/save.html"/>
	<form action="${saveUrl}" method="post">
		<c:if test="${not empty product}">
			<input type="hidden" name="id" value="${product.id}">
		</c:if>
		<label>
			<div>Название:</div>
			<div><input type="text" name="name" value="${product.name}"></div>
		</label>
		<label>
			<div>Цена:</div>
			<div>
				<fmt:formatNumber var="priceRub" maxFractionDigits="0" value="${product.price div 100}"/>
				<fmt:formatNumber var="priceKop" minIntegerDigits="2" value="${product.price mod 100}"/>
				<input type="text" name="price-rub" value="${priceRub}">&nbsp;руб.
				<input type="text" name="price-kop" value="${priceKop}">&nbsp;коп.
			</div>
		</label>
		<div>
			<button type="submit">Сохранить</button>
			<c:if test="${not empty product}">
				<c:url var="deleteUrl" value="/product/delete.html"/>
				<button type="submit" formaction="${deleteUrl}">Удалить</button>
			</c:if>
		</div>
	</form>
	<c:url var="listUrl" value="/product/list.html"/>
	<p><a href="${listUrl}">Вернуться назад</a></p>
</body>
</html>