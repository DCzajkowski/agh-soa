<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  $END$

  <%
    Date date = new Date();
    out.print("<h2>" + date.toString() + "</h2>");
  %>
  </body>
</html>
