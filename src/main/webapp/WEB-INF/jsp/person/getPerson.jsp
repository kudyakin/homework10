<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>View person by ID</title>
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
    <h2>View person by ID</h2>
    <%--@elvariable id="personById" type="com.kudiukin.homework9.dto.PersonDto"--%>
    <form:form action="get" method="post" modelAttribute="personById">
        <form:label path="id">ID:</form:label>
        <form:input required="required" path="id"/><br/>
        <form:button>Get</form:button>
    </form:form>
</div>
</body>
</html>
