package Services;

import Models.Author;
import Models.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbService {
    public static final String QUERY_STANDARD = "SELECT s20567.dbo.wydawca.NAME, s20567.dbo.autor.NAME, TYTUL, CENA, ISBN, ROK FROM s20567.dbo.pozycje INNER JOIN s20567.dbo.autor ON s20567.dbo.pozycje.AUTID = s20567.dbo.autor.AUTID INNER JOIN s20567.dbo.wydawca ON s20567.dbo.pozycje.WYDID = s20567.dbo.wydawca.WYDID";

    public static final String QUERY_AUTHORS = "SELECT NAME FROM s20567.dbo.autor";

    private static final String URL = "jdbc:jtds:sqlserver://db-mssql.pjwstk.edu.pl;database=s20567.dbo;integratedSecurity=true";

    private static final HashMap<String, String> translations = new HashMap<String, String>() {{
        put("Publisher", "wydawca.NAME");
        put("Author", "autor.NAME");
        put("Title", "TYTUL");
        put("Price", "CENA");
        put("ISBN", "ISBN");
        put("Year", "ROK");
    }};

    private Connection connection;

    public DbService() {
        try {
            DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
            connection = DriverManager.getConnection(URL);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String createQuery(String id, String filter) {
        StringBuilder query = new StringBuilder(QUERY_STANDARD + " WHERE ");
        for (Map.Entry<String, String> entry : translations.entrySet())
            if (entry.getKey().equals(id))
                query.append(entry.getValue());
        if (isNumeric(filter))
            query.append(" = ").append(filter.trim());
        else
            query.append(" LIKE ").append("'%").append(filter.trim()).append("%'");
        return query.toString();
    }

    public List<Book> getBooksFromDb(String query) {
        ArrayList<Book> books = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            while (resultSet.next())
                books.add(new Book(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return books;
    }

    public List<Author> getAuthorsFromDb(String query) {
        ArrayList<Author> authors = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery(query)) {
            while (resultSet.next())
                authors.add(new Author(
                        resultSet.getString(1)
                ));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return authors;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}