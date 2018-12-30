# bgbilling-servlet-demo-scala

## Что это?

bgbilling-servlet-demo -- это Scala-версия демонстрационной реализации сервлета для использования совместно
с сервером [BGBilling](https://bgbilling.ru/). 

## Требования

* git
* JDK 8
* [sbt](https://www.scala-sbt.org/)

## Как это запустить? 

```
git clone https://github.com/alexanderfefelov/bgbilling-servlet-demo-scala.git
cd bgbilling-servlet-demo-scala
sbt assembly
```

jar-файл, созданный в результате в каталоге `target/scala-2.12`, скопируйте в каталог `lib/ext` сервера BGBilling.

В конфигурацию BGBilling добавьте:

```
custom.servlet.keys=DemoServletScala
#                    /
#                   |
#                   v
custom.servlet.DemoServletScala.class=com.github.alexanderfefelov.bgbilling.servlet.demo.DemoServletScala
custom.servlet.DemoServletScala.mapping=/demo-servlet-scala
```
Перезапустите сервер BGBilling.

Для проверки выполните

```
curl --request GET --include http://YOUR.BGBILLING.HOST:8080/bgbilling/demo-servlet-scala
```

В ответ вы получите что-то вроде такого:

```
HTTP/1.1 200 OK
Content-Length: 14
Date: Sun, 30 Dec 2018 06:54:46 GMT

Hello, World!
```

## Что дальше?

* Ознакомьтесь с [описанием технологии Servlet](https://docs.oracle.com/javaee/7/tutorial/servlets.htm).
* Посмотрите [аналогичный проект](https://github.com/alexanderfefelov/bgbilling-servlet-demo) на языке Java.
