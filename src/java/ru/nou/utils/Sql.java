package ru.nou.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Sql {

    /**
     *
     * @param filename Имя файла БД
     * @return Экземпляр класса подключения
     */
    public static Connection connect(String filename) {
        String url = "jdbc:sqlite:./resources/" + filename;
        Connection connection;

        try {
            String request = """
                    CREATE TABLE IF NOT EXISTS card (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        number INTEGER NOT NULL,
                        pin INTEGER NOT NULL,
                        balance INTEGER DEFAULT 0
                    );""";

            connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            stmt.execute(request);

            return connection;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
