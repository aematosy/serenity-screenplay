# Imagen base con Maven y Java 11
FROM maven:3.8-eclipse-temurin-11

# Establece directorio de trabajo
WORKDIR /app

# Copia el proyecto dentro del contenedor
COPY . /app

# Comando por defecto
CMD ["mvn", "verify", "-Denvironment=grid"]


