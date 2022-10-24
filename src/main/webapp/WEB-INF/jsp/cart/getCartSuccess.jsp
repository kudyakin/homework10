<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>View cart By ID</title>
</head>
<body>
<div align="center">
<a href="${pageContext.request.contextPath}/">&#8592 Back to main menu</a>
</div>
<h1 align="center">View cart By ID</h1>
<div align="center">
    <%--@elvariable id="cartById" type="com.kudiukin.homework9.dto.CartDto"--%>
        <h2>Cart</h2>
        <span>ID:</span><span>${cartById.cartId}</span><br/>
        <span>Sum:</span><span>${cartById.sum}</span><br/>
        <span>Person:</span>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
            <tr>
                <th>${cartById.person.id}</th>
                <th>${cartById.person.firstName}</th>
                <th>${cartById.person.lastName}</th>
                <th>${cartById.person.email}</th>
                <th>${cartById.person.phone}</th>
            </tr>
        </table>
        <span>Products:</span><table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Shop ID</th>
        </tr>
        <c:forEach items="${cartById.products}" var="item">
        <tr>
            <td><c:out value="${item.id}"/></td>
            <td><c:out value="${item.name}"/></td>
            <td><c:out value="${item.price}"/></td>
            <td><c:out value="${item.shop.getId()}"/></td>
        </tr>
        </c:forEach>
</div>
</body>
</html>