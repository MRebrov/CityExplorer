<h1>Инструкия для запуска</h1>

<b>Требования</b>
<ol>
    <li>Компьютер</li>
    <li>Интернет</li>
    <li>IntelliJ IDEA(просто пока проект не проверялся в других IDE). Ходят слухи, что нужна обязательно версия Ultimate, не Community</li>
</ol>

<b>Загрузить проект к себе на компьютер</b><br>
Расскажу как это сделать в идее. Есть несколько способов. Можно просто скачать архив, распаковать его и затем открыть в идее. 
Но можно и подтянуть его автоматически: 
<ol>
	<li>В идее Нажать <b>File -> New -> Project from Version Control -> Github</b></li>
	<li>Откроется окно. В поле <b>Git repository URL</b> вставить ссылку https://github.com/pasha9797/NetcrackerProject.git</li>
	<li>Выбрать папку для сохранения и нажать <b>Clone</b></li>
</ol>

<b>Подключение к БД</b><br>
База общая на весь проект, располагается в облаке(elephantsql.com). Для того, чтобы
подключиться к базе, необходимо указать следующие данные(в application.properties уже все настроено):
<ol>
    <li>URL: jdbc:postgresql://packy.db.elephantsql.com:5432/vrbqprvx</li>
    <li>Username: vrbqprvx</li>
    <li>Password: tyOiYQzSoH8ucADBSAkMcUCYC62AiF-6</li>
</ol>

<b>Проверить настройки</b><br>
<ol>
	<li>Откыть файл src/main/resources/application.properties</li>
	<li>Посмотреть на параметр <b>spring.datasource.url</b></li>
	Проверить, что в ссылке указан правильный порт и название базы данных
	<li>Проверить указанные логин и пароль</li>
</ol>

<b>Запуск</b>
Теперь приложение можно запускать.
<ol>
	<li>Запустить приложение</li>
	<li>В браузере перейти по ссылке http://localhost:8081</b></li>
</ol>

<b>Возможно, что-то забыл. При проблемах пишите в беседу ВК или Telegram ;)</b>
