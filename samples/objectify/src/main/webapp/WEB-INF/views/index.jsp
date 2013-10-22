<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body style="font-family: verdana;">
    <ul>
        <c:forEach items="${todos}" var="todo">
            <li>${todo}</li>
        </c:forEach>
    </ul>
</body>
