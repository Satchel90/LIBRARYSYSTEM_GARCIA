import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class Database {
    private Database() {
    }

    public static void initialize(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS categories ("
                            + "category_name VARCHAR(100) PRIMARY KEY"
                            + ")");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS books ("
                            + "book_id VARCHAR(100) PRIMARY KEY,"
                            + "title VARCHAR(255) NOT NULL,"
                            + "author VARCHAR(255) NOT NULL,"
                            + "category_name VARCHAR(100) NOT NULL,"
                            + "borrowed BOOLEAN NOT NULL DEFAULT FALSE,"
                            + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                            + "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
                            + "FOREIGN KEY (category_name) REFERENCES categories(category_name)"
                            + ")");
            statement.executeUpdate(
                    "INSERT IGNORE INTO categories (category_name) VALUES ('General')");
        }
    }
}
