# Núcleo del software y distribución de funcionalidades (Fase 3)

Corte 2 · Tareas 10 y 11 · Responsables: todos · Borrador: Inti, pendiente de revisión de Carlos

Este documento fija qué se programa primero (el núcleo) y quién hace cada funcionalidad. Parte del diagrama de clases entregado en el Corte 1 (`docs/diagramas/`) y de la lista de funcionalidades (`docs/fase1/funcionalidades.md`).

## 1. Núcleo del software

El núcleo es lo mínimo que tiene que existir y compilar para que los dos puedan trabajar en paralelo sin esperarse. Se programa en la semana 5 (tareas 17 y 18), juntos si hace falta.

| Paquete | Clases del núcleo | Responsable |
| --- | --- | --- |
| `logica` | `Etecsa` (Singleton) con la lista de clientes y las altas, bajas y búsquedas de clientes | Inti |
| `logica.clientes` | `Cliente` (abstracta), `PersonaNatural`, `PersonaJuridica`, `EntidadNoEstatal`, `Representante`, `TipoCliente` | Inti |
| `logica.servicios` | `Servicio` (abstracta) con `calcularMontoMes`, `calcularMontoPeriodo`, `puedeAsignarseA`, `getIdentificador` y `getTipo`; `TipoServicio` | Inti |
| `excepciones` | Excepciones propias (validación, límite de servicios, cliente o servicio no encontrado, identificador duplicado) | Carlos |
| `utiles` | `Validador` y `Provincias` (listas cerradas de provincias y municipios) | Carlos |

Criterio: está en el núcleo todo lo que usan las clases de los dos integrantes. `TelefonoFijo`, `TelefonoMovil`, `CuentaNauta` y sus registros mensuales ya no son núcleo, porque cada uno depende solo de `Servicio` y los hace su responsable.

Al terminar el núcleo, `develop` debe compilar y debe poder crearse un cliente de cada tipo desde una prueba JUnit.

## 2. Orden de construcción

1. Núcleo (sección 1).
2. Servicios concretos con sus registros de 12 meses: fijo (Inti); móvil y Nauta (Carlos).
3. Llamadas y `Tarifas`; `Etecsa.registrarLlamada(...)`.
4. Conectar las pantallas ya diseñadas (`docs/pantallas/`) a `Etecsa`.
5. Reportes R1 a R4 y Ayuda y Créditos.
6. Datos de prueba, pruebas y `.jar`.

## 3. Distribución de funcionalidades

Sigue la Tabla 3 del documento del Corte 1. Cada uno hace la lógica **y** las pantallas de lo suyo.

| Funcionalidades | Lógica | Pantallas | Responsable |
| --- | --- | --- | --- |
| F01–F08 Clientes (alta, modificación, baja, listado, búsqueda, ver servicios) | `Etecsa`, jerarquía de `Cliente`, `Representante` | `DialogoCliente`, `VentanaListadoClientes` | Inti |
| F09–F11 Alta, baja y búsqueda de servicios con límites | `puedeTenerX()`, `puedeAsignarseA(...)`, `Etecsa.asignarServicio(...)` | Diálogos de asignación de servicio | Carlos |
| F12–F13 Factura del fijo y atraso | `TelefonoFijo`, `FacturaFijo` | `DialogoFacturaFijo` | Inti |
| F14–F15 Registro de llamadas | `Llamada`, `LlamadaFijo`, `LlamadaMovil`, `Etecsa.registrarLlamada(...)` | `DialogoRegistrarLlamada` | Inti |
| F16 Registro mensual del móvil | `TelefonoMovil`, `RegistroMovil` | Pantallas de `interfaz.movil` | Carlos |
| F17–F18 Consumo Nauta | `CuentaNauta`, `ConsumoNauta` | Pantallas de `interfaz.nauta` | Carlos |
| F19, F21 Reportes R1 y R3 | `EstadoCuentaFijo`, `ResumenMunicipio`, métodos de `Etecsa` | Ventanas de R1 y R3 | Inti |
| F20, F22 Reportes R2 y R4 | `EstadoCuentaNauta`, `ResumenMovil`, métodos de `Etecsa` | Ventanas de R2 y R4 | Carlos |
| F23 Validación y excepciones | `Validador`, paquete `excepciones` | Mensajes con `JOptionPane` en todas las pantallas | Carlos |
| F24 Datos de prueba | `DatosPrueba` | — | Carlos (Tabla 3 del Corte 1) |
| F25 Ayuda y Créditos | — | Ventanas de ayuda | Carlos |
| Tarifas | `Tarifas` y montos de móvil y Nauta | — | Carlos |

## 4. Diferencias con el plan de trabajo inicial

El documento entregado en el Corte 1 cambió algunas cosas del plan del 20/10/2026. Se deja constancia para que el informe final sea coherente:

1. **`Llamador` y `LlamadorAdapter`.** Como el tema de interfaces todavía no se ha dado, las llamadas se registran por ahora con `Etecsa.registrarLlamada(...)`. Cuando se estudie el tema, se añade el Adapter sin tocar la interfaz gráfica.
2. **Patrones.** El Corte 1 no menciona fábricas ni estrategias de tarifa; los precios están en la clase `Tarifas`. Hay que decidir juntos si se mantienen Factory Method y Strategy (plan inicial) o se buscan otros, porque la guía exige al menos tres patrones.
3. **`DatosPrueba`.** En el plan era de Inti; en la Tabla 3 del Corte 1 quedó con Carlos (tarea 14). Queda con Carlos salvo que se acuerde otra cosa.
4. **Estados de cuenta.** El modelo usa `EstadoCuenta` con dos subclases (`EstadoCuentaFijo`, `EstadoCuentaNauta`) y las clases de fila `ResumenMunicipio` y `ResumenMovil` para R3 y R4.
5. **Listado de clientes.** Las pantallas necesitan un método de `Etecsa` para listar y filtrar clientes, que no está en el diagrama (ver `docs/pantallas/diseno_pantallas.md`).
