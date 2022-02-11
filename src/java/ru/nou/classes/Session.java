package ru.nou.classes;

import java.sql.*;


// этот класс хранит информацию о текущей сессии: экземпляр подключения,
// залогинен ли пользователь. в случае логина, хранится и экземпляр пользователя
public class Session {
    Connection connection;
    boolean loggedIn = false;
    Account account = null;


    /**
     *
     * @param sessionConnection Экземпляр подключения к БД
     */
    public Session(Connection sessionConnection) {
        connection = sessionConnection;
    }

    public Account getAccount() {
        return account;
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     *
     * @param sessionAccount Аккаунт юзера
     *
     * Метод служит для указания юзера в сессии
     */
    public void login(Account sessionAccount) {
        account = sessionAccount;
        loggedIn = true;
    }

    /**
     *
     *
     * Выход из аккаунта
     */
    public void unLogin() {
        account = null;
        loggedIn = false;
    }
}
