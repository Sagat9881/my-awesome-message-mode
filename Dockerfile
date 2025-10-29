# Используем официальный образ OpenJDK 21
FROM openjdk:21-jdk-slim

# Устанавливаем Gradle
RUN apt-get update && \
    apt-get install -y --no-install-recommends curl xz-utils && \
    curl -fsSL https://downloads.gradle-dn.com/distributions/gradle-8.7-bin.zip -o /tmp/gradle.zip && \
    unzip -d /opt/gradle /tmp/gradle.zip && \
    ln -s /opt/gradle/gradle-8.7/bin/gradle /usr/local/bin/gradle

# Устанавливаем Minecraft Forge или Fabric (если нужно)
# Здесь мы не устанавливаем отдельно, т.к. всё собирается через Gradle

# Рабочая директория
WORKDIR /workspace

# Копируем файлы проекта
COPY . .

# Собираем проект
RUN gradle build

# Запускаем сервер
CMD ["./gradlew", "runServer"]