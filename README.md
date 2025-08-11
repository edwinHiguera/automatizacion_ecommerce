# Proyecto de Automatización de Pruebas para E-Commerce

## 1. Descripción General

Este proyecto contiene un conjunto de pruebas funcionales automatizadas para un sitio web de comercio electrónico de demostración. El objetivo es simular el recorrido completo de un cliente, desde la búsqueda de productos hasta la finalización de la compra, garantizando que las funcionalidades críticas de la plataforma operen correctamente.

La automatización se ha desarrollado utilizando **Selenium WebDriver**, **Java** y **TestNG**, siguiendo el patrón de diseño **Page Object Model (POM)** para asegurar un código mantenible, reutilizable y fácil de entender.

## 2. Características Probadas

El conjunto de pruebas cubre las siguientes áreas funcionales del e-commerce:

-   **Autenticación de Usuarios:** Pruebas para el inicio de sesión.
-   **Búsqueda y Navegación:** Búsqueda de productos por categoría y por marca.
-   **Gestión de Productos:** Visualización de detalles de productos y adición al carrito.
-   **Carrito de Compras:** Validación del contenido y los precios en el carrito.
-   **Flujo de Compra:** Proceso completo de checkout y pago.
-   **Confirmación de Orden:** Verificación de que la orden ha sido procesada y registrada correctamente.

## 3. Prerrequisitos

Para poder ejecutar este proyecto, es necesario tener instalado el siguiente software:

-   **Java Development Kit (JDK):** Versión 17 o superior.
-   **Apache Maven:** Para la gestión de dependencias y la ejecución de las pruebas.
-   **Google Chrome:** El navegador utilizado para la ejecución de las pruebas (aunque puede adaptarse a otros).

## 4. Instalación

Sigue estos pasos para configurar el proyecto en tu entorno local:

1.  **Clona el repositorio:**
    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd automatizacion_ecommerce
    ```

2.  **Instala las dependencias:**
    Maven se encargará de descargar y gestionar todas las librerías necesarias (Selenium, TestNG, etc.) definidas en el archivo `pom.xml`.
    ```bash
    mvn install
    ```

## 5. Ejecución de Pruebas

Para ejecutar el conjunto completo de pruebas definido en `testng.xml`, utiliza el siguiente comando de Maven:

```bash
mvn test
```

Maven ejecutará las pruebas utilizando el plugin **Surefire**, y los resultados se guardarán en el directorio `target/surefire-reports` y `allure-results`.

## 6. Visualización de Reportes

Este proyecto utiliza **Allure Framework** para generar reportes de prueba detallados e interactivos.

1.  **Genera el reporte:**
    Una vez que las pruebas hayan finalizado, ejecuta el siguiente comando para procesar los resultados:
    ```bash
    mvn allure:report
    ```
    Esto generará un sitio web estático con el reporte en el directorio `target/site/allure-maven-plugin`.

2.  **Visualiza el reporte:**
    Para abrir el reporte en tu navegador, ejecuta:
    ```bash
    mvn allure:serve
    ```
    Este comando iniciará un servidor web local y mostrará el reporte, permitiéndote explorar los resultados de cada prueba, ver capturas de pantalla en caso de fallos y analizar el comportamiento de la ejecución.

## 7. Estructura del Proyecto

El proyecto sigue el patrón de diseño **Page Object Model (POM)** para separar la lógica de las pruebas de la implementación de las páginas web:

-   `src/test/java/pages`: Contiene las clases que modelan las páginas de la aplicación. Cada clase representa una página (ej. `LoginPage`, `HomePage`) y encapsula los localizadores de los elementos y los métodos para interactuar con ellos.
-   `src/test/java/tests`: Contiene las clases de prueba (ej. `LoginTest`). Estas clases utilizan los objetos de las páginas (`pages`) para realizar las acciones y verificaciones (aserciones) necesarias.
-   `src/test/java/utils`: Clases de utilidad o helpers que pueden ser compartidas entre diferentes pruebas (ej. manejo del driver, listeners).
-   `pom.xml`: El archivo de configuración de Maven. Define las dependencias, plugins y configuración de la compilación y ejecución.
-   `testng.xml`: El archivo de configuración de TestNG. Define las suites de prueba, el orden de ejecución y los parámetros de los tests.