<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Internet Shop (Homework 9)</title>
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div align="center">
    <h1>${message}</h1>

    <h3><c:choose>
        <c:when test="${pageContext.request.userPrincipal.name == null}">Welcome! Log in or register, please:</c:when>
        <c:otherwise>Welcome, ${pageContext.request.userPrincipal.name}!</c:otherwise>
    </c:choose></h3>

    <sec:authorize access="isAuthenticated()">
        <h4><a href="/logout">Log out</a></h4>
    </sec:authorize>

    <sec:authorize access="!isAuthenticated()">
        <a href="${pageContext.request.contextPath}/login">Log in</a><br>
        <a href="${pageContext.request.contextPath}/registration">Registration</a>
    </sec:authorize>

    <sec:authorize access="hasRole('ADMIN')">
        <h4>
        <a href="${pageContext.request.contextPath}api/person/all">-->>View all customers<<--</a><br>
        </h4>
    </sec:authorize>

        <h2>Persons</h2>
<%--    <a href="${pageContext.request.contextPath}api/person/create">Create new person</a><br>--%>
        <a href="${pageContext.request.contextPath}api/person/all">View all persons (access only for admin)</a><br>
        <a href="${pageContext.request.contextPath}api/person/get">View person by ID (access only for admin)</a><br>
        <a href="${pageContext.request.contextPath}api/person/update">Update person</a><br>
        <a href="${pageContext.request.contextPath}api/person/delete">Delete person (access only for admin)</a><br>

        <h2>Products</h2>
        <a href="${pageContext.request.contextPath}api/product/create">Create new product (access only for admin)</a><br>
        <a href="${pageContext.request.contextPath}api/product/all">View all products</a><br>
        <a href="${pageContext.request.contextPath}api/product/get">View product by ID</a><br>
        <a href="${pageContext.request.contextPath}api/product/update">Update product (access only for admin)</a><br>
        <a href="${pageContext.request.contextPath}api/product/delete">Delete product (access only for admin)</a><br>

        <h2>Carts</h2>
        <a href="${pageContext.request.contextPath}api/cart/create">Create new cart</a><br>
        <a href="${pageContext.request.contextPath}api/cart/all">View all carts (access only for admin)</a><br>
        <a href="${pageContext.request.contextPath}api/cart/get">View cart by ID</a><br>
        <a href="${pageContext.request.contextPath}api/cart/delete">Delete cart (access only for admin)</a><br>
        <a href="${pageContext.request.contextPath}api/cart/add">Add product to cart</a><br>
        <a href="${pageContext.request.contextPath}api/cart/remove">Remove product from cart</a><br>
        <a href="${pageContext.request.contextPath}api/cart/clean">Clean cart</a><br>

        <h2>Shops</h2>
        <a href="${pageContext.request.contextPath}api/shop/create">Create new shop (access only for admin)</a><br>
        <a href="${pageContext.request.contextPath}api/shop/all">View all shops</a><br>
        <a href="${pageContext.request.contextPath}api/shop/get">View shop by ID</a><br>
        <a href="${pageContext.request.contextPath}api/shop/delete">Delete shop (access only for admin)</a><br>

</div>
</body>
</html>