package ru.nou.classes;
import ru.nou.Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


// этот класс хранит в себе данные созданной карты
public class Card {
    private static final Random random = new Random();

    long cardNumber;
    short cardPin;
    long balance;


    /**
     *
     * @param number Номер карта
     */
    public Card(long number) {
        cardNumber = number;
        balance = 0;

        StringBuilder pinString = new StringBuilder();

        // генерация пин-кода
        for (int i = 1; i <= 4; i++) {
            // если 1-ое число 0, то при переводе в long пин-код превратится в 3-х
            // значное число. это условние убирает этот баг
            if (i == 1) {
                pinString.append(1 + random.nextInt(9));
                continue;
            }
            pinString.append(random.nextInt(10));
        }

        cardPin = Short.parseShort(pinString.toString());
    }

    /**
     *
     * @param number Номер карты
     * @param pin Пин-код
     */
    public Card(long number, short pin) {
        cardNumber = number;
        balance = 0;
        cardPin = pin;
    }

    public long getNumber() {
        return cardNumber;
    }

    public short getPin() {
        return cardPin;
    }

    /**
     *
     * @param count Кол-во средств
     */
    public void addBalance(long count) {
        balance += count;
    }

    /**
     *
     * @param count Кол-во средств
     */
    public void setBalance(long count) {
        balance = (int) count;
    }

    /**
     *
     * @return Существует такая карта или нет
     * @throws SQLException
     *
     * Проверка карты на существование
     */
    public boolean isExists() throws SQLException {
        Statement stmt = Main.session.getConnection().createStatement();

        String sql = "SELECT * FROM card";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            if (rs.getString("number").equals(String.valueOf(cardNumber))) {
                if (rs.getString("pin").equals(String.valueOf(cardPin))) {
                    stmt.close();
                    return true;
                } else {
                    stmt.close();
                    return false;
                }
            }
        }
        stmt.close();
        return false;
    }

    /**
     *
     * @param cardNumber Номер проверяемой карты
     * @return Существует такая карта или нет
     * @throws SQLException
     *
     * Проверка карты на существование, но уже без экземпляра класса
     */
    public static boolean isExists(long cardNumber) throws SQLException {
        Statement stmt = Main.session.getConnection().createStatement();

        String sql = "SELECT * FROM card";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            if (rs.getString("number").equals(String.valueOf(cardNumber))) {
                stmt.close();
                return true;
            }
        }
        stmt.close();
        return false;
    }
}
