# frequencyChar
Тестовое задание.\
В основе используется Java 17 и Spring boot 3.1 

> # Описание задания
> > Спроектировать(продумать формат и ограничения входящих/исходящих параметров) и реализовать REST API, вычисляющее частоту встречи символов по заданной строке. Результат должен быть отсортирован по убыванию количества вхождений символа в заданную строку.\
> > Пример входной строки: “aaaaabcccc”\
> > Пример выходного результата: “a”: 5, “c”: 4, “b”: 1
> > ## Требования к решению:
> > - Java 8+
> > - Spring boot 2+
> > - Решение должно быть покрыто тестами
> > - У решения должна быть документация по запуску и формату входящих/исходящих параметров
> > - Код решения необходимо разместить в публичном Github репозитории.

Склонируйте репозиторий: `gh repo clone Far-From-Perfect/frequencyChar`\
Находясь в корневой директории проекта запустите: `gradlew clean build`, а затем `gradlew bootRun`

Приложение использует 8088 порт.\
Реализация задания происходит по адресу: <http://localhost:8088/frequency_char>

Используется передающий параметр "input" содержащий в себе строку, состоящую из цифр/латинских букв/кириллицы согласно [utf-8][utf]

Остальные символы не поддерживаются.\
Максимальная длина строки ограничена 10 000 символами.
Пустая строка вернет [].

На попытку отправить неподдерживаемый символ в строке, вы получите ошибку Bad_request с сообщением: "Неподдерживаемый элемент строки"\
На попытку отправить строку длинной, превышающей допустимое значение, вы получите ошибку Bad_request с сообщением: "Длина строки превышает максимально допустимое значение"


[utf]: https://www.charset.org/utf-8
