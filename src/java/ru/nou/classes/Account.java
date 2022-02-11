package ru.nou.classes;


// этот класс хранит информацию о пользователе: карту и имя
public class Account {
    String name;
    Card card;

    /**
     *
     * @param accountCard Экземпляр класса карты юзера
     * @param accountName Имя юзера
     */
    public Account(Card accountCard, String accountName) {
        card = accountCard;
        name = accountName;
    }

    public String getName() {
        return name;
    }

    public Card getCard() {
        return card;
    }
}
