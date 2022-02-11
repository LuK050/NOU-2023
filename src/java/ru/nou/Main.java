package ru.nou;
import ru.nou.classes.Session;
import ru.nou.menu.UserMenu;
import ru.nou.utils.Sql;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static Session session;
    public static JSONObject config;


    public static void main(String[] args) throws SQLException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser(); // объект парсера json
        FileReader reader = new FileReader(".\\resources\\config.json"); // считывание json файла
        config = (JSONObject) jsonParser.parse(reader); // парсинг json конфига

        Connection connection = Sql.connect((String) config.get("databaseName")); // подключение к БД
        session = new Session(connection); // создание экземпляра класса сессии
        UserMenu.main(); // вывод главного меню (запуск всего)
    }
}
