<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add product to cart</title>
    <style type="text/css">
        label {
            display: inline-block;
            width: 200px;
            margin: 5px;
            text-align: left;
        }
        input[type=text], input[type=password], select {
            width: 200px;
        }
        button {
            padding: 10px;
            margin: 10px;
        }
    </style>
</head>
<body>
<div align="center">
    <a href="${pageContext.request.contextPath}/">&#8592 Back to main menu</a>
</div>
<div align="center">
    <h2>Add product to cart:</h2>
    <%--@elvariable id="cart" type="com.kudiukin.homework7.dto.CartDto"--%>
    <form:form action="add" method="put" modelAttribute="cart">

        <form:label path="cartId">Cart ID:</form:label>
        <form:input required="required" path="cartId"/><br/>

        <form:label path="productId">Product ID:</form:label>
        <form:input required="required" path="productId"/><br/>

        <form:button>Add</form:button>
    </form:form>
</div>
</body>
</html>