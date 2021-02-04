# Zdalne przesyłanie obiektów między klientem a serwerem Java

## Założenia

- autor: Wojciech Sitek
- najpierw należy uruchomić serwer, następnie klienta równolegle.
- klient i serwer działają na localhost na porcie 9999. Jeżeli chcemy to zmienić, to należy to zrobić i na serwerze, i na kliencie.
- zadaniem projektu jest ukazanie przykładowego sposobu działania bezpiecznego zdalnego przesyłania obiektów w języku Java.
- do projektu wykorzystywana jest prosta klasa Student.
- aby wysyłać obiekty, ich klasy muszą implementować interfejs Serializable.
- nie zaleca się wysyłania obiektów złożonych z obiektów złożonych.
- klucz został nazwany "mojklucz" i ma przykładowe hasło "1qaz2wsx". Został załączony do repozytorium.

## Kroki instalacji

### Kompilacja klas java:

```bash
javac Student.java
javac SSLSocketServer.java
javac SSLSocketClient.java
```

### Generacja klucza RSA

Klucz RSA dołączony do repozytorium. Gdyby go nie było, proszę wykonać komendę:
```bash
keytool -genkey -keystore mojklucz -keyalg RSA
```

### Uruchomienie serwera

```bash
java -Djavax.net.ssl.keyStore=mojklucz -Djavax.net.ssl.keyStorePassword=1qaz2wsx -Djava.protocol.handler.pkgs=com.sun.net.ssl.internal.www.protocol -Djavax.net.debug=ssl SSLSocketServer
```
### Uruchomienie klienta

```bash
java -Djavax.net.ssl.trustStore=mojklucz -Djavax.net.ssl.trustStorePassword=1qaz2wsx SSLSocketClient
```

## Bibliografia

- [StackOverflow](https://stackoverflow.com/questions/13874387/create-app-with-sslsocket-java)
- [InfoWorld](https://www.infoworld.com/article/2075291/build-secure-network-applications-with-ssl-and-the-jsse-api.html)
- [Dokumentacja Oracle](https://docs.oracle.com/javase/10/security/sample-code-illustrating-secure-socket-connection-client-and-server.htm#JSSEC-GUID-A4D59ABB-62AF-4FC0-900E-A795FDC84E41)