# JPA-GestionHospitalaria
Este proyecto en JPA nos permite mejorar el mapeo delas relaciones entre las entidades que tenemos en un hospital, el objetivo es implementar un sistema de gestión hospitalaria utilizando JPA y Hibernate para la persistencia de datos y el mapeo de objetos.
# Sistema de Gestión Hospitalaria (JPA/Hibernate)

### Tabla de Contenidos
- [Objetivo del Proyecto](#objetivo-del-proyecto)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Tecnologías y Versiones](#tecnologias-y-versiones)
- [Patrones de Diseño](#patrones-de-diseño)
- [Comandos para Compilar y Ejecutar](#comandos-para-compilar-y-ejecutar)
- [Autor](#autor)

---

### Objetivo del Proyecto

[cite_start]El sistema está diseñado para gestionar pacientes, médicos, departamentos, salas, historias clínicas y citas médicas, demostrando el dominio de conceptos avanzados de persistencia, relaciones entre entidades y patrones de diseño orientados a objetos[cite: 5].

---

### Estructura del Proyecto

La estructura del proyecto sigue una organización limpia y modular, como se detalla a continuación:
src/
└── main/
├── java/org/example/
│   ├── entidades/           # 9 entidades JPA + 3 enums + 1 embeddable
│   └── Main.java            # Demostración completa
└── resources/
└── META-INF/
└── persistence.xml  # Configuración JPA
---

### Tecnologías y Versiones

El proyecto utiliza las siguientes tecnologías y herramientas:
* **Java**: 8+
* **Build Tool**: Gradle
* [cite_start]**JPA**: Jakarta Persistence API 3.1.0 [cite: 328]
* [cite_start]**Hibernate ORM**: 6.4.4 [cite: 329]
* [cite_start]**H2 Database**: 2.2.x (base de datos file-based) [cite: 329]
* [cite_start]**Lombok**: 1.18.38 (para reducir código repetitivo) [cite: 331]

---

### Patrones de Diseño

Se han implementado los siguientes patrones de diseño para resolver problemas de la persistencia y el dominio del negocio:
* [cite_start]**Aggregate Root**: `Hospital`, con `cascade = CascadeType.ALL` para gestionar la persistencia de sus entidades dependientes[cite: 310].
* [cite_start]**Value Object**: `Matricula`, implementada con la anotación `@Embeddable`[cite: 311].
* [cite_start]**Template Method**: `Persona` como clase abstracta con `@MappedSuperclass`[cite: 312].
* [cite_start]**Builder Pattern**: Utilizando las anotaciones `@Builder` y `@SuperBuilder` de Lombok para la creación de objetos de manera legible[cite: 313].

---

### Comandos para Compilar y Ejecutar

Para compilar y ejecutar el proyecto, utiliza los siguientes comandos desde la terminal en la raíz del proyecto.

1.  **Compilar el proyecto**:
    ```bash
    ./gradlew build          # Linux/Mac
    gradlew.bat build        # Windows
    ```
    [cite_start]El resultado esperado es: `BUILD SUCCESSFUL`[cite: 298].

2.  **Ejecutar el programa**:
    ```bash
    ./gradlew run            # Linux/Mac
    gradlew.bat run          # Windows
    ```
    [cite_start]El resultado esperado en la consola es: `SISTEMA EJECUTADO EXITOSAMENTE`[cite: 302].

3.  **Limpiar y resetear la base de datos**:
    ```bash
    ./gradlew clean
    rm -rf data              # Linux/Mac
    rmdir /s /q data         # Windows
    ```
---
### Autor

**Lautaro Montenegro**
- GitHub: ldslm1101-art
- Correo electrónico: lds.lm1101@gmail.com
