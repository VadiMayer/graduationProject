<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!--Сделать как Meals, не как Users-->
<html>
<head>
    <title>Restaurant</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<section>
    <h3><a href="index.jsp">Home</a></h3>
    <hr/>
    <h2>Restaurant</h2>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Restaurant</th>
            <th>Rating</th>
            <th>Menu</th>
            <th>Price</th>
            <th>Date of update</th>
        </tr>
        </thead>
    </table>
</section>
</body>
</html>