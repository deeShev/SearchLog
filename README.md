# Клиент-серверное веб-приложение поиска заданного текста в лог файлах "SearchLog".
### В разработке использовались следующие технологии:
- Java EE, Spring MVC;
- Frontend: JS, HTML, CSS;
- Инструмент сборки проекта: Maven;
- Тестирование: Junit, Mockito;
- VCS: Git;
- IDE: Intellij IDEA.
### Описание работы
- Пользователь имеет возможность указать путь к папке, в которой будет происходить поиск заданного текста, включая все вложенные папки. Текст задается в поле "TEXT", путь к папке из которой будет происходить поиск "ROOT" и третье поле указание лога файла "EXTENSION"(расширение по умолчанию *.log).

- Результат поиска выводится в левой части приложения в виде дерева. При нажатии на необходимый файл в правой части приложения отображается содержимое файла с возможностью навигации (выделить все "Select all", вперед/назад "Next/Back").

- P.S.: Поиск может производиться, как на Windows, так и на Linux системах. Верстка не адаптированная.

### Интерфейс веб-приложения:
![](https://pp.userapi.com/c848636/v848636729/4e433/EK2xMDgJPJM.jpg)
