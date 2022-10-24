<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Clean cart by ID</title>
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
    <h2>Clean cart by ID</h2>
    <%--@elvariable id="cart" type="com.kudiukin.homework9.dto.CartDto"--%>
    <form:form action="clean" method="post" modelAttribute="cart">

        <form:label path="cartId">ID:</form:label>
        <form:input required="required" path="cartId"/><br/>

        <form:button>Delete</form:button>
    </form:form>
</div>
</body>
</html>
