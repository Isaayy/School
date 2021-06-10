<%--
  Created by IntelliJ IDEA.
  User: Jakub
  Date: 6/10/2021
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List JSP</title>
</head>
<body>
    <h1>Roster list</h1>
    <ul>
        <c:forEach var="member" items="${memberList}" varStatus="status">
            <li>
                <a href="member.do?id=${status.index}">
                    <c:out value="${member}"></c:out>
                </a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
