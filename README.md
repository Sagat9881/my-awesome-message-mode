# Minecraft Mod - My Awesome Mode (Fabric)

Этот проект представляет собой мод для Minecraft, построенный на библиотеке [Fabric](https://fabricmc.net/). В отличие от стандартных модов, он использует дополнительные зависимости, такие как **Hibernate**, **PostgreSQL** и **Protobuf**, что делает его подходящим для сложных сценариев, например, баз данных внутри игры или обработки сообщений.

---

## Требования

### Для запуска без Docker:

- Java 21+
- [Gradle 8.x+](https://gradle.org/install/)
- [Minecraft с Fabric Loader](https://fabricmc.net/use/)

### Для запуска через Docker:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## Сборка и запуск без IDE

### Шаг 1: Клонирование репозитория
bash git clone https://github.com/ваше-имя/ваш-проект.git cd ваш-проект
### Шаг 2: Установка зависимостей
bash ./gradlew build
Эта команда скачает все необходимые библиотеки, включая:
- Minecraft версии, указанной в `gradle.properties`
- Fabric API
- Hibernate ORM
- PostgreSQL JDBC
- Protobuf библиотеки

### Шаг 3: Запуск игры с модом
bash ./gradlew runClient
> Это запустит Minecraft с вашим модом. Вы можете увидеть результат работы мода в игре и проверить интеграцию с базой данных и другими компонентами.

---

## Сборка и запуск через Docker Compose

### Шаг 1: Клонирование репозитория
bash git clone https://github.com/ваше-имя/ваш-проект.git cd ваш-проект
### Шаг 2: Сборка и запуск контейнеров
bash docker-compose up --build
> ⚠️ Первый запуск может занять некоторое время, так как будет происходить загрузка образов и сборка проекта.

### Шаг 3: Доступ к игре

После запуска контейнеров Minecraft будет запущен с вашим модом внутри контейнера. Логи игры будут выводиться в терминал.
## Полезные команды Docker Compose

- **Остановить контейнеры**:
  bash docker-compose down
- **Пересобрать образы и перезапустить**:
  bash docker-compose up --build
- **Зайти внутрь контейнера**:
  bash docker-compose exec mod bash
---

## Особенности проекта

- Используется **Protobuf** для генерации классов из `.proto` файлов. Путь: `src/main/resources/protobuf`.
- Поддерживается **Hibernate** для работы с базами данных (например, PostgreSQL).
- Мод разделён на **общую логику** (`main`) и **клиентскую часть** (`client`), благодаря конфигурации в `loom {}`.

---

## Настройка Protobuf

Все `.proto` файлы должны находиться в следующих директориях:

- `src/main/resources/protobuf/`
- `src/client/resources/protobuf/`

При сборке они будут автоматически скомпилированы в Java-классы с помощью `protobuf-java`.

---
