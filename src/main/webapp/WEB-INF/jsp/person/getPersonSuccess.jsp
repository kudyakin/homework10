<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8" />
    <title>View person By ID</title>
</head>
<body>
<div align="center">
<a href="${pageContext.request.contextPath}/">&#8592 Back to main menu</a>
</div>
<h1 align="center">View person By ID</h1>
<div align="center">
    <%--@elvariable id="personById" type="com.kudiukin.homework10.dto.PersonDto"--%>
    <table>
        <tr>
            <td>ID: </td>
            <td>${personById.id}</td>
        <tr>
            <td>First name: </td>
            <td>${personById.firstName}</td>
        <tr>
            <td>Last name: </td>
            <td>${personById.lastName}</td>
        <tr>
            <td>Email: </td>
            <td>${personById.email}</td>
        <tr>
            <td>Phone: </td>
            <td>${personById.phone}</td>
    </table>
</div>
</body>
</html>