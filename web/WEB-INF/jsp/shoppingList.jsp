<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>   
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping list</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <div>
            <p>Hello, ${sessionScope.username}</p>
            <a href="ShoppingList?action=logout">Log out</a>
        </div>
        <br>
        <h3>List</h3>
        <form method="POST" action="ShoppingList">
            <label>Add item: </label>
            <input type="text" name="item" required>
            <input type="submit" value="Add">
            <input type="hidden" name="action" value="add">
        </form>
        <div>
            <form method="POST" action="ShoppingList" >
                <c:forEach items="${sessionScope.itemList}" var="item">
                    <div>
                        <input type="checkbox" name="userItems" value="${item}">
                        <label><c:out value="${item}" /></label>
                    </div>                 
                </c:forEach>
                <p>${message}</p>
                <input type="submit" value="Delete">
                <input type="hidden" name="action" value="delete">
            </form>
        </div>
    </body>
</html>