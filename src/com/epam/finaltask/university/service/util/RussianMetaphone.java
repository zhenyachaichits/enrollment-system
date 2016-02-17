package com.epam.finaltask.university.service.util;

import java.util.Arrays;

public class RussianMetaphone {

    private static final String ALPHABET = "ЁАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЫЭЮЯ";
    private static final String SONANTS = "БЗДВГ";
    private static final String BREATH_CONSONANTS = "ПСТФК";
    private static final String SONANTS_TO_BREATH_CONSONANTS = "ПСТКБВГДЖЗФХЦЧШЩ";
    private static final String VOWEL_PATTERN = "ОЮЕЭЯЁЫ";
    private static final String VOWEL_REPLACE = "АУИИАИА";

    private static final char[] ALPHABET_CHAR_ARRAY = ALPHABET.toCharArray();
    private static final char[] SONANTS_TO_BREATH_CONSONANTS_CHAR_ARRAY = SONANTS_TO_BREATH_CONSONANTS.toCharArray();

    static {
        Arrays.sort(ALPHABET_CHAR_ARRAY);
        Arrays.sort(SONANTS_TO_BREATH_CONSONANTS_CHAR_ARRAY);
    }

    public String encode(String string) {
        if ((string == null) || (string.length() == 0)) return "";

        StringBuilder stringBuilder = normalize(string);
        replaceLastSonant(stringBuilder);

        StringBuilder resultBuilder = new StringBuilder(stringBuilder.length());

        char oldCh = 0;
        for (int i = 0; i < stringBuilder.length(); ++i) {
            char ch = stringBuilder.charAt(i);

            int vowelIndex = VOWEL_PATTERN.indexOf(ch);
            if (vowelIndex >= 0) {
                if ((oldCh == 'Й' || oldCh == 'И') && (ch == 'О' || ch == 'Е'))
                    resultBuilder.setCharAt(resultBuilder.length() - 1, 'И');
                else resultBuilder.append(VOWEL_REPLACE.charAt(vowelIndex));
            } else {
                if (Arrays.binarySearch(SONANTS_TO_BREATH_CONSONANTS_CHAR_ARRAY, ch) >= 0) {
                    int sonantIndex = SONANTS.indexOf(oldCh);
                    if (sonantIndex >= 0) {
                        oldCh = BREATH_CONSONANTS.charAt(sonantIndex);
                        resultBuilder.setCharAt(resultBuilder.length() - 1, oldCh);
                    }
                }
                if (oldCh == 'Т' && ch == 'С')
                    resultBuilder.setCharAt(resultBuilder.length() - 1, 'Ц');
                else if (ch != oldCh) resultBuilder.append(ch);
            }
            oldCh = ch;
        }
        return resultBuilder.toString();
    }

    private static StringBuilder normalize(String string) {
        StringBuilder stringBuilder = new StringBuilder(string.length());

        char oldCh = 0;
        for (int i = 0; i < string.length(); ++i) {
            char ch = Character.toUpperCase(string.charAt(i));
            if (ch != oldCh) if (Arrays.binarySearch(ALPHABET_CHAR_ARRAY, ch) >= 0) stringBuilder.append(ch);
            oldCh = ch;
        }
        return stringBuilder;
    }

    private static void replaceLastSonant(StringBuilder stringBuilder) {
        if (stringBuilder.length() > 0) {
            int lastSonantIndex = SONANTS.indexOf(stringBuilder.charAt(stringBuilder.length() - 1));

            if (lastSonantIndex >= 0)
                stringBuilder.setCharAt(stringBuilder.length() - 1, BREATH_CONSONANTS.charAt(lastSonantIndex));
        }
    }
}
