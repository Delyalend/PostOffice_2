Левченко Данил

Тестовое задание на Java EE

Описание проекта:
Система по отслеживанию передвижений почтовых отправлений (далее - посылок). JSON-сервис. 

Пользователь системы имеет возможность зарегистрировать посылку с 
указанием:
- индекс почтового отделения, откуда будет отправлена посылка
- индекса почтового отделения, куда посылка должна быть доставлена

Посылка может быть доставлена в указанное почтовое отделение не напрямую, а через
промежуточные почтовые отделения. 

Все перемещения посылки от первого почтового отделения до последнего
фиксируются - их можно получить в виде истории перемещений.

Стек технологий: Spring, Maven, Hibernate, Jpa, Tomcat, База данных in memory - для тестового задания то, что надо
Для документации API использовал soapUI.

Подготовительные действия:

Приложение будет размещаться на Tomcat. Подразумевается, что у вас он уже установлен.

Для простого запуска приложения используйте IDE Intellij IDEA. 
Запускаем IDEA. File -> Open... -> Выбираем мой проект.
- После запуска проект в IDEA может появиться окно "Maven projects need to be imported" - нажмите Enable Auto-Import.
- Имеет смысл проверить работу плагина Lombok:
	- File -> Settings -> Plugins -> Ищем "Lombok" -> устанавливаем/обновляем 
- Возможно будут проблемы с версией Java:
	File -> Settings -> Java Compiler -> Target bytecode version = 8
	File -> Project Structure -> Project -> Project SDK = 1.8, Project language level = 8
	File -> Project Structure -> Modules -> language level = 8


Сборка проекта:
(Если вам не хочется всё это проделывать - пролистайте вниз, там есть более
простой вариант запуска приложения)
Около кнопки закрытия IDE есть кнопка "Maven". Кликаем по "Maven". 
Открывается меню. В шапке меню есть значок "m" - execute maven goal. Кликаем.
В поиске вводим package - дважды кликаем по "package".
После этого war-ник должен появиться в папке target. 
Кликните по "com.postalSystem.war" правой кнопкой мыши -> copy Path -> absolutePath
Зайдите в папку с установленным tomcat и запустите startup.bat
Мой путь:  D:\apache-tomcat-9.0.39\webapps\bin
В браузере введите: localhost:8080. Должна открыться стартовая страница Apache Tomcat.
Кликните по ManagerApp. Введите логин и пароль. 
Найдите меню "Развернуть серверный WAR файл": 
- В поле "Путь" напишите "/postSystem" 
- В поле "WAR или путь до директории" укажите путь к war, который скопировали ранее.
- Остальные поля оставьте пустыми
- Кликните "Развернуть".
Вы должны получить следующее сообщение: "OK - Приложение успешно развёрнуто в контекстном пути [/postSystem]"
Запустите мой проект "soapWithTomcat.xml" в SoapUI. Готово


ПРОСТОЙ ВАРИАНТ ЗАПУСКА:
В задании указано, что приложение должно размещаться на любом сервере приложений.
В Intellij IDEA есть встроенный tomcat - если вам не хочется вручную разворачивать
war-ник на сервере приложений, скачайте проект отсюда - https://github.com/Delyalend/PostOffice_2.
Затем найдите класс Application и запустите его. Всё)
Запустите мой проект "soap.xml" в SoapUI.
Для доступа к базе данных: localhost:8080/h2-console/

Тестирование в SoapUI:
1. Registration post office:
{"index":1,"address":"address","name":"name"}
{"index":1,"address":"address","name":"name"}

2. Registration post item:
{"currentPostOfficeIndex":1,"targetPostOfficeIndex":2,"type":"box","nameRecipient":"name","addressRecipient":"address"}

3. Send post item to post office
{"id":1,"targetPostOfficeIndex":2}

4. Arrival post item to post office
{"id":1,"targetPostOfficeIndex":2}

5. Delivery post item to client
{"id":1}

6. Get post item histories

7. Get post item status

Что я бы добавил ещё:
1. Безопасность
2. Тесты
3. Возможно стоит использовать кеширование?
4. ...

Если будут вопросы:
- https://t.me/DelAlen
- levchenko.d2015@yandex.ru