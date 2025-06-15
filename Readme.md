# CookIn🍳🍽️

---

**CookIn** 은 사용자의 식재료 관리 웹 서비스입니다.
사용자들은 간편하게 자신의 식재료를 등록하고 유통기한를 쉽게 관리할 수 있습니다.
이를 통해 냉장고에서 버려지는 재료를 최소화하고 식재료를 낭비 없이 활용할 수 있습니다.

---

### ✔️필수 소프트웨어 설치

- JDK 17 설치: [Downloads for Amazon Corretto 17 - Amazon Corretto 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- MySQL + MySQL Workbench 설치: [MySQL :: Download MySQL Installer](https://dev.mysql.com/downloads/installer/)
- InteliJ IDEA 설치(VS code도 가능): [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/?section=windows)
---
### ✔️데이터베이스 설정(MySQL)

1. MySQL Worbrench 접속 후 ‘+’  버튼을 이용하여 데이터 베이스 및 사용자 계정 설정 후 Test Connection 클릭
    - **Connection Name:** `cookindb`
    - **Username:** `localmaster`
    - **Password:** `qwer1234`

   (Hostname, Port는 초기설정 유지해주세요 → **Hostname:** `127.0.0.1` , **Port:** `3306` )

2. 접속후 쿼리 실행

    ```sql
    CREATE DATABASE cookin;
    
    CREATE USER 'localmaster'@'localhost' IDENTIFIED BY 'qwer1234';
    
    GRANT ALL PRIVILEGES ON cookin.* TO 'localmaster'@'localhost';
    
    FLUSH PRIVILEGES;
    ```
---
### ✔️프로젝트 설정(IntlliJ 기준)

1. 프로젝트 열기
2.  ‘project structure’메뉴 → **SDK**: Amazon Corretto 17 선택
3. resources 패키지 안에 application.yml  확인

    ```java
    spring:
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
        defer-datasource-initialization: true
    
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/cookin
        username: localmaster
        password: qwer1234
    ```

4. CookInApplicaion.java에서 실행 버튼 클릭
---
### 📌서버 주소로 접속해주세요. →  [http://localhost:8080](http://localhost:8080/fridge.html)
