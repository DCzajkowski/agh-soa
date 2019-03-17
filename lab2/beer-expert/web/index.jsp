<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Age Verification</title>
    </head>
    <body>
        <form action="/verify-age" method="post">
            <label for="age">
                How old are you?
                <input type="text" name="age" id="age" />

                <button type="submit">Go!</button>
            </label>
        </form>
    </body>
</html>
