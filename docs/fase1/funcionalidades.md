# Lista de funcionalidades (Fase 1)

Corte 1 · Tarea 4 · Responsables: todos · Borrador: Inti, pendiente de revisión de Carli

Funcionalidades que debe ofrecer el sistema al operador de ETECSA. La columna **Resp.** sigue el reparto del plan de trabajo (Inti: clientes, fijo y llamadas; Carli: móvil, Nauta, validaciones y creación de servicios). La prioridad indica qué se necesita para el núcleo (Alta), qué completa el enunciado (Media) y qué es opcional (Baja).

## Clientes

| Id | Funcionalidad | Resp. | Prioridad |
| --- | --- | --- | --- |
| F01 | Registrar una persona natural | Inti | Alta |
| F02 | Registrar una persona jurídica con su representante | Inti | Alta |
| F03 | Registrar una entidad no estatal con su representante | Inti | Alta |
| F04 | Modificar los datos de un cliente | Inti | Media |
| F05 | Eliminar un cliente (y sus servicios) | Inti | Media |
| F06 | Listar los clientes, con filtro por tipo, provincia y municipio | Inti | Alta |
| F07 | Buscar un cliente por CI o por nombre | Inti | Media |
| F08 | Ver los servicios de un cliente | Inti | Alta |

## Servicios

| Id | Funcionalidad | Resp. | Prioridad |
| --- | --- | --- | --- |
| F09 | Dar de alta un teléfono fijo, móvil o cuenta Nauta respetando el límite del tipo de cliente | Carli | Alta |
| F10 | Dar de baja un servicio | Carli | Media |
| F11 | Buscar un servicio por número de teléfono o por nick | Carli | Media |

## Teléfono fijo y llamadas

| Id | Funcionalidad | Resp. | Prioridad |
| --- | --- | --- | --- |
| F12 | Ver la factura de un fijo en un mes (tarifa fija, locales, larga distancia desglosada, atraso, total) | Inti | Alta |
| F13 | Registrar el atraso o marcar como pagada la factura de un mes | Inti | Media |
| F14 | Registrar una llamada recibida desde el `Llamador` (se factura en el fijo o se carga al móvil) | Inti | Alta |
| F15 | Simular llamadas desde la interfaz para probar el sistema | Inti | Media |

## Teléfono móvil y Nauta

| Id | Funcionalidad | Resp. | Prioridad |
| --- | --- | --- | --- |
| F16 | Ver el registro de un móvil en un mes (llamadas y monto) | Carli | Alta |
| F17 | Registrar el consumo mensual de una cuenta Nauta (correo nacional/internacional, navegación) | Carli | Alta |
| F18 | Ver el consumo de una cuenta Nauta en un mes | Carli | Alta |

## Estados de cuenta y reportes

| Id | Funcionalidad | Resp. | Prioridad |
| --- | --- | --- | --- |
| F19 | R1: estado de cuenta de un fijo entre dos meses, sin desglose de larga distancia | Inti | Alta |
| F20 | R2: estado de cuenta de una cuenta Nauta entre dos meses, con todos los datos | Carli | Alta |
| F21 | R3: municipios más llamados en larga distancia en un mes, con minutos y monto | Inti | Alta |
| F22 | R4: móviles con mayor gasto en un mes y su cantidad de llamadas, por tipo de cliente | Carli | Alta |

## Generales

| Id | Funcionalidad | Resp. | Prioridad |
| --- | --- | --- | --- |
| F23 | Validar todos los datos de entrada y mostrar mensajes de error claros | Carli | Alta |
| F24 | Cargar datos de prueba al iniciar | Inti | Media |
| F25 | Ayuda y Créditos | Carli | Media |
| F26 | Exportar reportes a PDF o guardar los datos en fichero (Fase 5) | Carli | Baja |
