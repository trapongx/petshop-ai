# Pet Shop (minimal CRUD)

Minimal Spring Boot + Kotlin CRUD REST API using H2.

## Requirements

- Java 17
- Maven

## Run

If you use a custom `~/.m2/settings.xml` (e.g. Artifactory) and see errors like *"Expected root element 'project' but found 'html'"*, your local cache may have a bad POM. Remove it and use the project settings so Maven uses Maven Central:

```bash
rm -rf ~/.m2/repository/org/springframework/boot/spring-boot-starter-parent/3.2.12
mvn -s maven-settings.xml spring-boot:run
```

Otherwise:

```bash
mvn spring-boot:run
```

Web UI will be available at `http://localhost:8081/` (served from `src/main/resources/static/index.html`).

API base: `http://localhost:8081`

H2 console: `http://localhost:8081/h2-console`

JDBC URL: `jdbc:h2:mem:petshop`

## Endpoints

- `GET /pets`
- `GET /pets/{id}`
- `POST /pets`
- `PUT /pets/{id}`
- `DELETE /pets/{id}`

## cURL examples

Create:

```bash
curl -i -X POST http://localhost:8081/pets \
  -H 'content-type: application/json' \
  -d '{"name":"Milo","type":"cat","age":3}'
```

List:

```bash
curl -s http://localhost:8081/pets
```

Update:

```bash
curl -i -X PUT http://localhost:8081/pets/1 \
  -H 'content-type: application/json' \
  -d '{"name":"Milo","type":"cat","age":4}'
```

Delete:

```bash
curl -i -X DELETE http://localhost:8081/pets/1
```

