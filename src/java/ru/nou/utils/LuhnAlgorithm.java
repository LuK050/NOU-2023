package ru.nou.utils;
import ru.nou.Main;

import java.util.*;


public class LuhnAlgorithm {
    private static final Random random = new Random();
    private static ArrayList<Integer> cardStartNumbers = null;


    /**
     *
     * @param list Список цифр
     * @return Целое число
     *
     * Перевод массива чисел в целое число
     */
    private static Long convertListToLong(List<Integer> list) {
        StringBuilder numberStringBuilder = new StringBuilder();

        for (int i : list) {
            numberStringBuilder.append(i);
        }

        return Long.parseLong(numberStringBuilder.toString());
    }

    /**
     *
     * @return Сгенерированный номер карты
     *
     * Генерация номера карты соответствуя алгоритму Луна
     */
    public static long generate() {
        // для получения начальный чисел из конфига
        if (cardStartNumbers == null) {
            cardStartNumbers = new ArrayList<>();

            short counter = 0;
            for (long i : (ArrayList<Long>) Main.config.get("cardStartNumbers")) {
                counter++;

                if (counter > 6) {
                    break;
                } else {
                    cardStartNumbers.add((int) i);
                }
            }
        }

        // обозначение переменных
        ArrayList<Integer> cardNumberList = (ArrayList<Integer>) cardStartNumbers.clone(),
                multipliedOddNumbersCardNumberList = new ArrayList<>();
        short sum, index = 0;
        int finalNumber = 0;

        // добавление к начальным 6 числам ещё 9 рандомных
        for (int i = cardNumberList.toArray().length; i < 15; i++) {
            cardNumberList.add(random.nextInt(10));
        }

        // умножение нечёт. чисел на 2. если результат больше 9, то результат равен сумме цифр в этом числе
        for (int i : cardNumberList) {
            if (index % 2 == 0 || index == 0) {
                if (i * 2 > 9) {
                    i = ((i * 2 - 10) + 1);
                } else {
                    i *= 2;
                }
            }
            multipliedOddNumbersCardNumberList.add(i);
            index++;
        }

        // сумма всех чисел в списке с умноженными нечётными
        sum = (short) multipliedOddNumbersCardNumberList.stream().mapToInt(x -> x).sum();

        // вычисление последнего 16-го числа. если проверка не прошла, значит последнее число 0, ведь сумма уже кратна 10
        if (sum % 10 != 0) {
            while (sum % 10 != 0) {
                sum++; finalNumber++;
            }
        }

        // добавление последнего числа и возврат числа
        cardNumberList.add(finalNumber);
        return convertListToLong(cardNumberList);
    }



    /**
     *
     * @param number
     * @return Соответствует число алгоритму Луна, или нет
     *
     * Проверка на соответствие числа с алгоритмом луна
     */
    public static boolean numberLuhnAlgorithmCheck(long number) {
        ArrayList<Integer> cardNumberList = new ArrayList<>(),
                multipliedOddNumbersCardNumberList = new ArrayList<>();;
        short sum, index = 0;

        // разбиение числа на список для дальнейшей работы с ним
        for (char i : String.valueOf(number).toCharArray()) {
            cardNumberList.add(Integer.parseInt(String.valueOf(i)));
        }

        // умножение нечёт. чисел на 2. если результат больше 9, то результат равен сумме цифр в этом числе
        for (int i : cardNumberList) {
            if (index % 2 == 0 || index == 0) {
                if (i * 2 > 9) {
                    i = ((i * 2 - 10) + 1);
                } else {
                    i *= 2;
                }
            }
            multipliedOddNumbersCardNumberList.add(i);
            index++;
        }

        // сумма всех чисел в списке с умноженными нечётными
        sum = (short) multipliedOddNumbersCardNumberList.stream().mapToInt(a -> a).sum();

        // кратна сумма 10, или нет. т.е соответствует число алгоритму, или нет
        return sum % 10 == 0;
    }
}
