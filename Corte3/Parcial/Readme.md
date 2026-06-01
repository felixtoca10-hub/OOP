# Sistema de Monitoreo Remoto de Sensores Simulados

Este proyecto integra los fundamentos de la Programación Orientada a Objetos en Java con operaciones concurrentes por sockets de red, interfaces gráficas (GUI) y persistencia en disco de formato estructurado.

## Integrantes
* Estudiante de Ingeniería

## Conceptos de POO Aplicados
* **Abstracción**: Definida en la clase abstracta `SensorSimulado`, moldeando el comportamiento estándar de un nodo de telemetría.
* **Encapsulamiento**: Modificadores de acceso `private` con métodos consultores getters y modificadores setters en el modelo de datos.
* **Herencia**: Las variantes `SensorTemperatura`, `SensorVoltaje` y `SensorDistancia` extienden de la superclase base reutilizando código.
* **Polimorfismo**: Sobrescritura del método `evaluarEstado(double)` para resolver lógicas particulares dinámicamente en tiempo de ejecución.

## Requisitos de Ejecución
* **Java SDK**: Versión 8 o superior.
* **Puerto de red por defecto**: `5000`

## Instrucciones para Pruebas del Sistema
1. **Lanzar el Servidor Central**: Ejecutar la clase `principal.MainServidor`. Configurar el puerto deseado y presionar **"Iniciar Servidor"**.
2. **Lanzar Estaciones Clientes**: Ejecutar la clase `principal.MainCliente`. Puede ejecutar este Main múltiples veces de forma simultánea para simular concurrencia multisensor.
3. **Establecer Enlace**: Digitar la IP (`localhost` para entornos locales) y puerto correspondiente en el cliente, luego pulsar **"Conectar"**.
4. **Ciclo de Simulación**: Seleccionar el tipo de sensor, presionar **"Generar Medición"** (creará el objeto en memoria) y finalmente presionar **"Enviar por Socket"**.
5. **Verificación**: Corroborar el despliegue automático en la tabla visual del servidor y comprobar la creación histórica de las filas en el archivo físico local `mediciones.csv`.