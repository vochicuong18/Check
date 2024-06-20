package company.demo.ultis;

import com.github.javafaker.Faker;

public class StringUtility {
    private static final int RANGE = 4;
    private static final String kata = "アイウエオカキクケコガギグゲゴサシスセソザジズゼゾタチツテトダヂヅデドナニヌネノハヒフヘホバビブベボパピプペポマミムメモヤユヨラリルレロワヲン";
    private static final String latin = "qQwWeErRtTyYuUiIoOpPaAsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM";
    private static final String doubleByte = "qQwWeErRtTyYuUiIoOpPaAsSdDfFgGhHjJkKlLzZxXcCvVbBnNmM";
    private static final String number = "0123456789";
    private static Faker faker = new Faker();

    public static String getRandomName() {
        return faker.name().fullName();
    }

    public static String getRandomKataKana() {
        return getRandomKataKana(RANGE);
    }

    public static String getRandomKataKana(int range) {
        StringBuilder randomKata = new StringBuilder();
        for (int i = 0; i < range; i++) {
            randomKata.append(kata.charAt((int) (Math.random() * kata.length())));
        }
        return randomKata.toString();
    }

    public static String getRandomCharacter() {
        return getRandomCharacter(RANGE);
    }

    public static String getRandomCharacter(int range) {
        StringBuilder randomKata = new StringBuilder();
        for (int i = 0; i < range; i++) {
            randomKata.append(latin.charAt((int) (Math.random() * latin.length())));
        }
        return randomKata.toString();
    }

    public static String getRandomDoubleByte() {
        return getRandomDoubleByte(RANGE);
    }

    public static String getRandomDoubleByte(int range) {
        StringBuilder randomDouble = new StringBuilder();
        for (int i = 0; i < 4; i++)
            randomDouble.append(doubleByte.charAt((int) (Math.random() * doubleByte.length())));
        return randomDouble.toString();
    }

    public static String getRandomNumber(int range) {
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < range; i++)
            randomNumber.append(number.charAt((int) (Math.random() * number.length())));
        return randomNumber.toString();
    }

    public static String capitalizeFirstLetter(String data) {
        String[] letters = data.split("");
        letters[0] = letters[0].toUpperCase();
        return String.join("", letters);
    }
}
