<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Beer proposition</title>
    </head>
    <body>
        Proposed beer is: <% out.print(request.getAttribute("beer")); %>
    </body>
</html>
