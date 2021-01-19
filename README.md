# bgbilling-servlet-demo-scala

## Что это?

bgbilling-servlet-demo-scala - это Scala-версия демонстрационной реализации сервлетов и фильтров для использования совместно с сервером [BGBilling](https://bgbilling.ru/).

## Требования

* git
* JDK 8
* [sbt](https://www.scala-sbt.org/)

## Как это установить? 

```
git clone https://github.com/alexanderfefelov/bgbilling-servlet-demo-scala
cd bgbilling-servlet-demo-scala
sbt assembly
```

Скопируйте jar-файл, созданный в результате в каталоге `target/scala-2.13`, в каталог `lib/custom` вашего экземпляра BGBilling.

## Привет, мир!

- [HelloWorldScala.scala](src/main/scala/com/github/alexanderfefelov/bgbilling/servlet/demo/HelloWorldScala.scala)
- [TerryPratchettFilterScala.scala](src/main/scala/com/github/alexanderfefelov/bgbilling/servlet/demo/TerryPratchettFilterScala.scala)

В конфигурацию BGBilling добавьте:

```properties
# Servlet: Привет, мир!
#
custom.servlet.keys=HelloWorldScala
#                   │             │
#                   └───┬─────────┘
#                       │
#                 Ключ сервлета                               Класс сервлета
#                       │                                            │
#              ┌────────┴────┐       ┌───────────────────────────────┴────────────────────────────────┐
#              │             │       │                                                                │
custom.servlet.HelloWorldScala.class=com.github.alexanderfefelov.bgbilling.servlet.demo.HelloWorldScala
custom.servlet.HelloWorldScala.mapping=/demo-servlet/hello-world-scala
#                                      │                             │
#                                      └──────────────┬──────────────┘
#                                                     │
#                                         Часть URL после контекста
#
custom.servlet.HelloWorldScala.filter.keys=TerryPratchettScala
#                                          │                 │
#                                          └─────┬───────────┘
#                                                │
#                                           Ключ фильтра
#                                                │
#                                     ┌──────────┴──────┐
#                                     │                 │
custom.servlet.HelloWorldScala.filter.TerryPratchettScala.name=TerryPratchettScala
custom.servlet.HelloWorldScala.filter.TerryPratchettScala.class=com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterScala
#                                                               │                                                                          │
#                                                               └──────────────────────────────────┬───────────────────────────────────────┘
#                                                                                                  │
#                                                                                            Класс фильтра
```

Перезапустите BGBilling.

Если всё в порядке, в логах можно будет увидеть:

```
01-16/12:09:52  INFO [main] Server - Add custom servlet from setup...
01-16/12:09:52  INFO [main] Server - Custom.servlet.keys => HelloWorldScala
01-16/12:09:52  INFO [main] Server - Custom.servlet.class => com.github.alexanderfefelov.bgbilling.servlet.demo.HelloWorldScala
01-16/12:09:52  INFO [main] Server - Custom.servlet.mapping => /demo-servlet/hello-world-scala
01-16/12:09:52  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.HelloWorldScala to /demo-servlet/hello-world-scala
01-16/12:09:52  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterScala to /demo-servlet/hello-world-scala
```

Теперь выполните:

```
http --verbose --check-status \
  GET http://bgbilling-server.backpack.test:63081/billing/demo-servlet/hello-world-scala
```

В результате на запрос:

```
GET /billing/demo-servlet/hello-world-scala HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: bgbilling-server.backpack.test:63081
User-Agent: HTTPie/1.0.3
```

будет получен ответ:

```
HTTP/1.1 200 OK
Content-Length: 14
Date: Sat, 16 Jan 2021 09:11:05 GMT
X-Clacks-Overhead: GNU Terry Pratchett

Hello, World!
```

## О системе

- [SysInfoScala.scala](src/main/scala/com/github/alexanderfefelov/bgbilling/servlet/demo/SysInfoScala.scala)
- [UptimePuncherFilterScala.scala](src/main/scala/com/github/alexanderfefelov/bgbilling/servlet/demo/UptimePuncherFilterScala.scala)

Добавьте в конфигурацию BGBilling:

```properties
# Servlet: О системе
#
custom.servlet.keys=SysInfoScala
#                   │          │
#                   └──┬───────┘
#                      │
#                Ключ сервлета                            Класс сервлета
#                      │                                         │
#              ┌───────┴──┐       ┌──────────────────────────────┴──────────────────────────────┐
#              │          │       │                                                             │
custom.servlet.SysInfoScala.class=com.github.alexanderfefelov.bgbilling.servlet.demo.SysInfoScala
custom.servlet.SysInfoScala.mapping=/demo-servlet/sys-info-scala
#                                   │                          │
#                                   └─────────────┬────────────┘
#                                                 │
#                                     Часть URL после контекста
#
custom.servlet.SysInfoScala.filter.keys=UptimePuncherScala,TerryPratchettScala,CORS
#                                       │                │ │                 │ │  │
#                                       └─────┬──────────┘ └────────┬────────┘ └─┬┘
#                                             │                     │            │
#                                        Ключ фильтра          Ещё фильтр      И ещё один
#                                             │
#                                  ┌──────────┴─────┐
#                                  │                │
custom.servlet.SysInfoScala.filter.UptimePuncherScala.name=UptimePuncherScala
custom.servlet.SysInfoScala.filter.UptimePuncherScala.class=com.github.alexanderfefelov.bgbilling.servlet.demo.UptimePuncherFilterScala
#                                                           │                                                                         │
#                                                           └──────────────────────────────────┬──────────────────────────────────────┘
#                                                                                              │
#                                                                                        Класс фильтра
custom.servlet.SysInfoScala.filter.TerryPratchettScala.name=TerryPratchettScala
custom.servlet.SysInfoScala.filter.TerryPratchettScala.class=com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterScala
custom.servlet.SysInfoScala.filter.CORS.name=CORS
custom.servlet.SysInfoScala.filter.CORS.class=org.apache.catalina.filters.CorsFilter
custom.servlet.SysInfoScala.filter.CORS.init-param.keys=AllowedOrigins
#                                                       │            │
#                                                       └───┬────────┘
#                                                           │
#                                                     Ключ параметра    Название параметра
#                                                           │                    │
#                                                  ┌────────┴───┐      ┌─────────┴────────┐
#                                                  │            │      │                  │
custom.servlet.SysInfoScala.filter.CORS.init-param.AllowedOrigins.name=cors.allowed.origins
custom.servlet.SysInfoScala.filter.CORS.init-param.AllowedOrigins.value=*
#                                                                       │
#                                                                       │
#                                                              Значение параметра
```

Перезапустите BGBilling.

Теперь в логах будет так:

```
01-16/12:57:43  INFO [main] Server - Add custom servlet from setup...
01-16/12:57:43  INFO [main] Server - Custom.servlet.keys => SysInfoScala
01-16/12:57:43  INFO [main] Server - Custom.servlet.class => com.github.alexanderfefelov.bgbilling.servlet.demo.SysInfoScala
01-16/12:57:43  INFO [main] Server - Custom.servlet.mapping => /demo-servlet/sys-info-scala
01-16/12:57:43  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.SysInfoScala to /demo-servlet/sys-info-scala
01-16/12:57:43  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.UptimePuncherFilterScala to /demo-servlet/sys-info-scala
01-16/12:57:43  INFO [main] Server - Add mapping: com.github.alexanderfefelov.bgbilling.servlet.demo.TerryPratchettFilterScala to /demo-servlet/sys-info-scala
01-16/12:57:43  INFO [main] Server - Add mapping: org.apache.catalina.filters.CorsFilter to /demo-servlet/sys-info-scala
```

и в ответ на запрос:

```
http --verbose --check-status \
  GET http://bgbilling-server.backpack.test:63081/billing/demo-servlet/sys-info-scala \
    "Origin: http://example.com"
```

```
GET /billing/demo-servlet/sys-info-scala HTTP/1.1
Accept: */*
Accept-Encoding: gzip, deflate
Connection: keep-alive
Host: bgbilling-server.backpack.test:63081
Origin: http://example.com
User-Agent: HTTPie/1.0.3
```

вы получите:

```
HTTP/1.1 200 OK
Access-Control-Allow-Credentials: true
Access-Control-Allow-Origin: http://example.com
Content-Length: 499
Date: Sat, 16 Jan 2021 10:27:52 GMT
Vary: Origin
X-BGBilling-Server-Uptime: Started: 16.01.2021 13:27:39 Uptime: 0 d 00:00:13
X-Clacks-Overhead: GNU Terry Pratchett

Modules
--------------------------------------------------

0 kernel 8.0.1320 / 16.12.2020 18:10:08
2 inet 8.0.832 / 15.12.2020 17:06:32
1 card 8.0.307 / 06.10.2020 01:52:21
3 npay 8.0.287 / 19.11.2020 18:41:17
5 subscription 8.0.128 / 06.10.2020 01:52:39
4 rscm 8.0.272 / 06.10.2020 01:52:36

Runtime
--------------------------------------------------

Hostname/IP address: bgbilling-server.backpack.test/172.17.0.8
Available processors: 8
Memory free / max / total, MB: 306 / 444 / 343
```

## Логи

Для того, чтобы логи собирались в отдельном файле, необходимо изменить `data/log4j.xml`.

Добавьте новый аппендер:

```xml
<appender name="SERVLET" class="org.apache.log4j.RollingFileAppender">
    <param name="File" value="log/servlet.log"/>
    <param name="MaxFileSize" value="50MB"/>
    <param name="MaxBackupIndex" value="3"/>
    <param name="Append" value="true"/>

    <layout class="org.apache.log4j.PatternLayout">
        <param name="ConversionPattern" value="%d{MM-dd/HH:mm:ss} %5p [%t] %c{1} - %m%n"/>
    </layout>

    <filter class="ru.bitel.common.logging.Log4JMDCFilter">
        <param name="key" value="nestedContext"/>
        <param name="value" value="servlet"/>
    </filter>
</appender>
```

и исправьте имеющийся:

```xml
<appender name="ASYNC" class="ru.bitel.common.logging.Log4jAsyncAppender">
    <appender-ref ref="APPLICATION"/>
    <appender-ref ref="ERROR"/>
    <appender-ref ref="MQ"/>
    <appender-ref ref="SCRIPT"/>
    <appender-ref ref="SERVLET"/>
</appender>
```

В результате после перезапуска BGBilling в файле `log/servlet.log` можно будет увидеть что-то вроде:

```
01-16/13:18:07 TRACE [localhost.localdomain-startStop-1] UptimePuncherFilterScala - init
01-16/13:18:07 TRACE [localhost.localdomain-startStop-1] TerryPratchettFilterScala - init
01-16/13:18:16 TRACE [http-nio-0.0.0.0-8080-exec-1] SysInfoScala - init
01-16/13:18:16 TRACE [http-nio-0.0.0.0-8080-exec-1] UptimePuncherFilterScala - doFilter
01-16/13:18:16 TRACE [http-nio-0.0.0.0-8080-exec-1] TerryPratchettFilterScala - doFilter
01-16/13:18:16 TRACE [http-nio-0.0.0.0-8080-exec-1] SysInfoScala - doGet
```

## Что дальше?

* Ознакомьтесь с [описанием технологии Servlet](https://docs.oracle.com/javaee/7/tutorial/servlets.htm).
* Изучите [список фильтров, встроенных в Tomcat 8.5](https://tomcat.apache.org/tomcat-8.5-doc/config/filter.html).
* Посмотрите аналогичные проекты на других языках:
  * Clojure - https://github.com/alexanderfefelov/bgbilling-servlet-demo-clojure,
  * Groovy - https://github.com/alexanderfefelov/bgbilling-servlet-demo-groovy,
  * Java - https://github.com/alexanderfefelov/bgbilling-servlet-demo,
  * Kotlin - https://github.com/alexanderfefelov/bgbilling-servlet-demo-kotlin.
* Посмотрите, как можно расширить функционал BGBilling с помощью других механизмов:
  * Dynaction - https://github.com/alexanderfefelov/bgbilling-dynaction-demo,
  * Dynservice - https://github.com/alexanderfefelov/bgbilling-dynservice-demo.
