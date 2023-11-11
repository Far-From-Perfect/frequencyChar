package com.example.frequencychar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CharCounterService {

    /**
     * Метод, вычисляющий  частоту встречи символов по заданной строке.
     * Поддерживаемые символы: Латиница (регистр имеет значение), Кириллица (регистр имеет значение), цифры(0-9)
     * @param str
     * @return ResponseEntity<?>
     */
    public ResponseEntity<?> charCounter(String str) {
        char[] charArray = str.toCharArray();
        HashMap<String, Integer> result = new HashMap<>();

        try {
            for (Character value: charArray) {
                checkConstraints(value);
                String strValue = value.toString();
                if (result.containsKey(strValue)) {
                    result.put(strValue, result.get(strValue) + 1);
                } else {
                    result.put(strValue, 1);
                }
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ErrorMessage(HttpStatus.BAD_REQUEST.value(), "Неподдерживаемый элемент строки"),
                    HttpStatus.BAD_REQUEST);
        }

        Collection<Map.Entry<String, Integer>> sortedResult = result.entrySet();
        return new ResponseEntity<>(sortedResult.stream()
                .sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).toList(),
                HttpStatus.OK);
    }

    private void checkConstraints(Character chr) {
        if (!isSupportedCharacter(chr)) throw new IllegalArgumentException();
    }

    /**
     * Поддержка символов по таблице <a href="https://www.charset.org/utf-8">utf-8</a>
     * Значение 65-90 - Latin Capital Letters
     * Значение 97-122 - Latin Small Letters
     * Значение 1040-1105 - Cyrillic Letters
     * Значение 48-57 - Digits
     * @param chr
     * @return boolean
     */
    private boolean isSupportedCharacter(Character chr) {
        int value = (int) chr;
        return (value >= 65 && value <= 90) || (value >= 97 && value <= 122)
                || (value >= 1040 && value <= 1105
                || (value >= 48 && value <= 57));
    }
}
