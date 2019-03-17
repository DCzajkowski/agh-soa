<%@ page import="App.Domain.ErrorBag" %>
<% ErrorBag errors = (ErrorBag) request.getAttribute("errors"); %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login to the app</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="w-full h-screen flex items-center justify-center">
        <form action="/login" method="post" class="w-1/3 bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
            <div class="mb-4">
                <label class="block text-grey-darker text-sm font-bold mb-2" for="username">
                    Username
                </label>
                <input class="<%= errors.hasFor("username") ? "border-red mb-3" : "" %> shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline" id="username" name="username" type="text" placeholder="Username">
                <% if (errors.hasFor("username")) { %>
                    <p class="text-red text-xs italic"><% out.print(errors.getFirstFor("username")); %></p>
                <% } %>
            </div>
            <div class="mb-6">
                <label class="block text-grey-darker text-sm font-bold mb-2" for="password">
                    Password
                </label>
                <input class="<%= errors.hasFor("password") ? "border-red mb-3" : "" %> shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline" id="password" name="password" type="password" placeholder="******************">
                <% if (errors.hasFor("password")) { %>
                    <p class="text-red text-xs italic"><% out.print(errors.getFirstFor("password")); %></p>
                <% } %>
            </div>
            <div class="flex items-center justify-between">
                <button class="bg-blue hover:bg-blue-dark text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
                    Sign In
                </button>
            </div>
        </form>
    </body>
</html>
<% errors.clear(); %>
