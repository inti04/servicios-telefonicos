# Software similares (Fase 0)

Corte 1 · Tarea 2 · Responsables: todos · Borrador: Inti, pendiente de revisión de Carli

Se revisaron aplicaciones que muestran consumo y facturación de servicios de telecomunicaciones, para tomar ideas de qué datos mostrar y cómo organizarlos. Ninguna resuelve el problema completo: todas están pensadas para que el **cliente** consulte lo suyo, mientras que este sistema es para que el **operador de ETECSA** gestione a todos los clientes.

> Antes de entregar, revisar cada aplicación de nuevo y añadir capturas en `docs/capturas/`, ya que sus funciones cambian con el tiempo.

| Aplicación | Para quién | Qué ofrece | Idea que se toma |
| --- | --- | --- | --- |
| Portal de usuario Nauta (ETECSA) | Cliente de Nauta | Consultar saldo, recargar e historial de conexiones con su duración | Mostrar el consumo de la cuenta Nauta por período, en una tabla con totales |
| Mi Cubacel (ETECSA) | Cliente móvil | Consultar saldo, bonos y planes del móvil | Separar el consumo del móvil por mes y mostrar el monto total |
| Transfermóvil | Cliente bancario | Pagar la factura del teléfono fijo y recargar móviles | La factura del fijo se identifica por el número y tiene un monto por mes; el atraso se acumula si no se paga |
| Factura impresa del teléfono fijo | Cliente fijo | Tarifa fija, llamadas locales, larga distancia desglosada, atraso y total del mes | Estructura de `FacturaFijo` y del reporte R1 |
| Portales de autogestión de otras operadoras | Cliente | Historial de facturas, detalle de llamadas, gráficos de consumo | Estado de cuenta entre dos meses y filtros por período |

## Conclusiones para el proyecto

1. **Usuario distinto.** Las aplicaciones existentes son de autoservicio; la nuestra necesita además altas de clientes y servicios, búsquedas y reportes globales (por ejemplo, municipios más llamados).
2. **Consumo por mes.** Todas organizan la información por período mensual, igual que los 12 registros por servicio del enunciado.
3. **Tablas con totales.** El formato más claro para un estado de cuenta es una tabla con una fila por mes y una fila final de totales.
4. **Búsqueda por identificador.** El usuario busca un servicio por número de teléfono o por nick; la interfaz debe permitir eso directamente.
5. **Mensajes claros.** Ante datos incorrectos, explicar qué campo falla y por qué, sin cerrar la ventana.
