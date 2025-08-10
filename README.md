# automatizacion_ecommerce
Proyecto de automatización de pruebas funcionales para un ecommerce de demostración, utilizando Selenium WebDriver, Java, TestNG y Allure para la ejecución y reporte de tests. Incluye pruebas de búsqueda, carrito de compras, checkout y pago, con estructura modular basada en el patrón Page Object Model (POM).

# Proyecto Automatización Ecommerce

Este proyecto implementa pruebas automatizadas para un ecommerce de ejemplo utilizando las siguientes tecnologías:

- **Java** como lenguaje principal.
- **Selenium WebDriver** para la automatización de pruebas UI.
- **Maven** como herramienta de gestión y construcción del proyecto.
- **Page Object Model (POM)** para una mejor organización y mantenimiento del código.
- **TestNG** para la gestión y ejecución de casos de prueba.
- **Allure Report** para generación de reportes visuales.
- **GitHub Actions** para integración continua y ejecución automática de pruebas.

---

## Casos de Prueba Ejecutados

- **Search Tests**
  - `testSearchBrandHM`
  - `testSearchBrandPolo`
  - `testSearchWomenDress`
  - `testSearchWomenTops`

- **Cart Validation Tests**
  - `testValidateCartPoloTshirt`

- **Checkout Tests**
  - `testCheckoutPoloTshirt`

- **Login Tests**
  - `failedLogin`
  - `logoutTest`
  - `successfulLogin`

- **Order Placed Tests**
  - `testPaymentPoloTshirt`

- **Payment Tests**
  - `testPaymentPoloTshirt`

- **View/Add Product Tests**
  - `testViewAddPoloTshirt`

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/edwinHiguera/automatizacion_ecommerce.git

   
2. Ejecutar las pruebas con Maven:
mvn test -DsuiteXmlFile=testng.xml


3. Generar reporte Allure (local):
allure serve target/allure-results

Configuración CI/CD con GitHub Actions
Se ejecutan pruebas automáticamente en cada push o pull request a la rama main, y se genera un reporte Allure que se despliega en la rama gh-pages.
