<%@ page import="Models.Book" %>
<%@ page import="Services.DbService" %>
<%@ page import="java.util.List" %>
<%@ page import="Models.Author" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%
    DbService dbService = new DbService();

    String id = request.getParameter("id");
    String filter = request.getParameter("filter");

    List<Book> books;
    List<Author> authors;
    if (id == null || filter == null || filter.isEmpty() || id.isEmpty()) {
        books = dbService.getBooksFromDb(DbService.QUERY_STANDARD);
        authors = dbService.getAuthorsFromDb(DbService.QUERY_AUTHORS);
    } else {
        books = dbService.getBooksFromDb(DbService.createQuery(id, filter));
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" type="text/css" href="Resources/styles.css" />

        <title>S20567</title>
    </head>

    <body>
        <form method="GET">
            <label>
                <select class="button" name="id">
                    <option value="Publisher">Publisher</option>
                    <option value="Author">Author</option>
                    <option value="Title">Title</option>
                    <option value="Price">Price</option>
                    <option value="ISBN">ISBN</option>
                    <option value="Year">Year</option>
                </select>
            </label>
            <label>
                <input type="text" name="filter" class="button"/>
            </label>
            <input type="submit" value="Filter" class="button"/>
        </form>

        <% if (!books.isEmpty()) { %>
            <table>
                <caption>Result table</caption>
                <thead>
                    <tr>
                        <th>Publisher</th>
                        <th>Author</th>
                        <th>Title</th>
                        <th>Price</th>
                        <th>ISBN</th>
                        <th>Year</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Book book : books) { %>
                        <tr>
                            <td><%=book.getPublisher()%></td>
                            <td><%=book.getAuthor()%></td>
                            <td><%=book.getTitle()%></td>
                            <td><%=book.getPrice()%></td>
                            <td><%=book.getIsbn()%></td>
                            <td><%=book.getYear()%></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        <% } else %> <h3>It seems like there are no books</h3>
    </body>
</html>