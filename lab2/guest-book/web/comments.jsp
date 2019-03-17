<%@ page import="App.Domain.ErrorBag" %>
<%@ page import="App.Domain.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="App.Domain.OldInput" %>
<% ErrorBag errors = (ErrorBag) request.getAttribute("errors"); %>
<% List<Comment> comments = (List<Comment>) request.getAttribute("comments"); %>
<% OldInput old = (OldInput) request.getAttribute("old"); %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Guest Book</title>
        <link href="https://cdn.jsdelivr.net/npm/tailwindcss/dist/tailwind.min.css" rel="stylesheet">
    </head>
    <body class="w-full h-screen flex pt-32 justify-center">
        <div class="w-full max-w-xl">
            <form action="/comments" method="post" class="flex mb-16">
                <div class="flex-1">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="name">
                        Name:
                    </label>
                    <input
                        class="<%= errors.hasFor("name") ? "border-red mb-3" : "" %> shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                        type="text"
                        name="name"
                        id="name"
                        value="<%= old.get("name") %>"
                    />
                    <% if (errors.hasFor("name")) { %>
                        <span class="text-red text-xs italic"><%= errors.getFirstFor("name") %></span>
                    <% } %>
                </div>
                <div class="flex-1 ml-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="email">
                        Email:
                    </label>
                    <input
                        class="<%= errors.hasFor("email") ? "border-red mb-3" : "" %> shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                        type="email"
                        name="email"
                        id="email"
                        value="<%= old.get("email") %>"
                    />
                    <% if (errors.hasFor("email")) { %>
                        <span class="text-red text-xs italic"><%= errors.getFirstFor("email") %></span>
                    <% } %>
                </div>
                <div class="flex-1 ml-4">
                    <label class="block text-grey-darker text-sm font-bold mb-2" for="comment">
                        Comment:
                    </label>
                    <input
                        class="<%= errors.hasFor("comment") ? "border-red mb-3" : "" %> shadow appearance-none border rounded w-full py-2 px-3 text-grey-darker leading-tight focus:outline-none focus:shadow-outline"
                        type="text"
                        name="comment"
                        id="comment"
                        value="<%= old.get("comment") %>"
                    />
                    <% if (errors.hasFor("comment")) { %>
                        <span class="text-red text-xs italic"><%= errors.getFirstFor("comment") %></span>
                    <% } %>
                </div>

                <button class="<%= errors.hasAny() ? "self-center" : "self-end" %> ml-4 h-10 flex-1 bg-blue hover:bg-blue-dark text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">Add comment</button>
            </form>

            <div>
                <h1>Comments</h1>
                <% for (Comment comment : comments) { %>
                    <div class="mt-4 pt-4 border-t border-grey">
                        <h3><%= comment.getName() %> <span class="text-grey-darkest">(<%= comment.getEmail() %>)</span></h3>
                        <span class="text-sm text-grey-darker"><%= comment.getDate().toLocaleString() %></span>
                        <p class="mt-3"><%= comment.getComment() %></p>
                    </div>
                <% } %>
            </div>
        </div>
    </body>
</html>
<% errors.clear(); %>
