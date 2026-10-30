# Análisis del enunciado (Fase 0)

Corte 1 · Tarea 1 · Responsables: todos · Borrador: Inti, pendiente de revisión de Carli

Este documento resume qué pide el enunciado, qué datos maneja el sistema y qué puntos no quedan claros. Las dudas abiertas se resuelven con el profesor tutor; mientras tanto se trabaja con las suposiciones indicadas al final.

---

## 1. Qué es el sistema

Una aplicación de escritorio en Java para que ETECSA controle a sus clientes y los servicios que cada uno tiene contratados. El único usuario es un trabajador de ETECSA (operador): registra clientes y servicios, registra el consumo y consulta estados de cuenta y reportes. Los clientes no usan la aplicación.

## 2. Clientes

| Tipo | Datos propios | Datos comunes | Límite de servicios |
| --- | --- | --- | --- |
| Persona natural | Nombre(s), apellidos, carné de identidad | Dirección, municipio, provincia | 1 fijo, 2 móviles, 1 cuenta Nauta |
| Persona jurídica | Nombre de la entidad, organismo, representante | Dirección, municipio, provincia | Varios fijos y móviles, ninguna cuenta Nauta |
| Entidad no estatal | Nombre, representante | Dirección, municipio, provincia | Varios servicios de cada tipo |

El representante de una persona jurídica o de una entidad no estatal tiene nombre(s), apellidos y carné de identidad.

## 3. Servicios y lo que se guarda por mes

Cada servicio guarda 12 registros, uno por mes del año.

| Servicio | Identificador | Qué se guarda cada mes |
| --- | --- | --- |
| Teléfono fijo | Número | Factura: tarifa fija, monto de llamadas locales, llamadas de larga distancia desglosadas (municipio, tiempo, monto), atraso y total |
| Teléfono móvil | Número | Llamadas realizadas y monto total del mes |
| Cuenta Nauta | Nick | KB de correo enviado y recibido (nacional e internacional), KB de navegación, sus montos y el total |

## 4. Llamadas

- Llegan desde una interfaz externa `Llamador`, dada por el enunciado, que devuelve objetos `Llamada`. El sistema no la implementa: la usa.
- Una llamada que sale de un fijo hacia un móvil **se carga al móvil**, no al fijo.
- Las demás llamadas de un fijo van a su factura del mes: como locales si el destino está en el mismo municipio y como larga distancia en otro caso (con el desglose por municipio).

## 5. Salidas que pide el enunciado

1. **Estado de cuenta de un teléfono fijo** entre un mes inicial y un mes final, **sin** desglose de larga distancia.
2. **Estado de cuenta de una cuenta Nauta** entre un mes inicial y un mes final, **con** todos los datos.

La guía de la asignatura exige además al menos cuatro reportes (dos con información de al menos dos clases y dos de listas de listas), al menos tres patrones de diseño, validación de todos los datos de entrada, división en capas, Ayuda y Créditos. La propuesta de reportes y patrones está en el plan de trabajo.

## 6. Reglas de negocio identificadas

1. Un cliente no puede pasar de su límite de servicios por tipo.
2. Una persona jurídica nunca tiene cuenta Nauta.
3. El nick de una cuenta Nauta es único en todo el sistema.
4. Un número de teléfono es único en todo el sistema.
5. Un fijo tiene exactamente una factura por mes.
6. Las llamadas de fijo a móvil se cargan al móvil.
7. En un estado de cuenta, el mes inicial es menor o igual que el mes final, y ambos están entre 1 y 12.
8. Duraciones, KB y montos no son negativos.

## 7. Dudas para el profesor

| # | Duda | Suposición mientras no se aclare |
| --- | --- | --- |
| 1 | El enunciado no da tarifas | Se definen como constantes en una clase `Tarifas` y se explica en el informe |
| 2 | ¿Cómo se calcula el atraso de una factura? | Es lo que quedó sin pagar del mes anterior; se registra a mano |
| 3 | ¿Se registra si una factura está pagada? | Sí, con un indicador de pagada; si no se paga, pasa como atraso al mes siguiente |
| 4 | ¿Qué parámetros recibe `realizarLlamada(...)`? | Número de origen, número de destino, duración y mes |
| 5 | ¿Cuándo una llamada de fijo es local? | Cuando el destino está en el mismo municipio que el fijo |
| 6 | ¿Se trabaja con un solo año? | Sí: los 12 registros corresponden al año en curso |
| 7 | ¿La interfaz se hace con Swing u otro "software suministrado"? | Swing con WindowBuilder; solo cambiaría el paquete `interfaz` |
| 8 | ¿Hay que guardar los datos al cerrar la aplicación? | No es obligatorio; se usan datos de prueba precargados. Guardar en fichero queda para la Fase 5 |
