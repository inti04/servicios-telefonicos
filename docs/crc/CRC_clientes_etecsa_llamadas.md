# Tarjetas CRC: clientes, Etecsa y llamadas

Corte 1 · Tarea 6 · Responsable: Inti · Participante: Carli

Cada tarjeta indica la clase, su posición en la jerarquía, sus responsabilidades (lo que sabe y lo que hace) y las clases con las que colabora para cumplirlas. Las tarjetas de servicios, facturas y consumos corresponden a la tarea 7.

---

## Etecsa

| | |
| --- | --- |
| **Tipo** | Clase controladora (Singleton) |
| **Superclase** | — |
| **Subclases** | — |

| Responsabilidades | Colaboradores |
| --- | --- |
| Garantizar que exista una única instancia de la empresa (`getInstancia()`) | — |
| Conocer la lista de todos los clientes | `Cliente` |
| Registrar, modificar, eliminar y buscar clientes (por CI, nombre o entidad) | `Cliente`, `PersonaNatural`, `PersonaJuridica`, `EntidadNoEstatal` |
| Dar de alta un servicio a un cliente, respetando sus límites | `Cliente`, `FabricaServicio`, `Servicio` |
| Buscar un servicio por número de teléfono o por nick | `Cliente`, `Servicio` |
| Registrar las llamadas recibidas desde el exterior | `LlamadorAdapter`, `Llamada` |
| Generar el estado de cuenta de un fijo y de una cuenta Nauta entre dos meses | `EstadoCuenta`, `TelefonoFijo`, `CuentaNauta` |
| Resolver los reportes R1 a R4 delegando los cálculos en cada servicio | `Cliente`, `Servicio`, `FacturaFijo`, `RegistroMovil`, `ConsumoNauta` |
| Ser el único punto de entrada de la interfaz a la capa lógica | Clases del paquete `interfaz` |

---

## Cliente

| | |
| --- | --- |
| **Tipo** | Clase abstracta |
| **Superclase** | — |
| **Subclases** | `PersonaNatural`, `PersonaJuridica`, `EntidadNoEstatal` |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer su dirección, municipio y provincia | `Provincias`, `Validador` |
| Conocer sus servicios: teléfonos fijos, teléfonos móviles y cuentas Nauta | `TelefonoFijo`, `TelefonoMovil`, `CuentaNauta` |
| Agregar y quitar servicios, comprobando antes si puede tenerlos | `Servicio`, `LimiteServiciosException` |
| Decidir si puede tener otro fijo, móvil o Nauta (`puedeTenerFijo()`, `puedeTenerMovil()`, `puedeTenerNauta()`, abstractos) | Subclases |
| Devolver su nombre para mostrar (`getNombreMostrar()`, abstracto) | Subclases |
| Devolver sus listas de servicios sin permitir que se modifiquen desde fuera | — |

---

## PersonaNatural

| | |
| --- | --- |
| **Tipo** | Clase concreta |
| **Superclase** | `Cliente` |
| **Subclases** | — |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer su nombre o nombres, apellidos y carné de identidad | `Validador` |
| Validar que el CI tenga 11 dígitos con fecha válida y que los nombres no tengan dígitos | `Validador`, `ExcepcionValidacion` |
| Permitir como máximo 1 teléfono fijo, 2 móviles y 1 cuenta Nauta | — |
| Devolver nombre y apellidos como nombre para mostrar | — |

---

## PersonaJuridica

| | |
| --- | --- |
| **Tipo** | Clase concreta |
| **Superclase** | `Cliente` |
| **Subclases** | — |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer el nombre de la entidad y el organismo al que pertenece | `Validador` |
| Conocer a su representante | `Representante` |
| Permitir varios teléfonos fijos y varios móviles | — |
| No permitir cuentas Nauta (`puedeTenerNauta()` devuelve `false`) | — |
| Devolver el nombre de la entidad como nombre para mostrar | — |

---

## EntidadNoEstatal

| | |
| --- | --- |
| **Tipo** | Clase concreta |
| **Superclase** | `Cliente` |
| **Subclases** | — |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer su nombre | `Validador` |
| Conocer a su representante | `Representante` |
| Permitir varios servicios de cada tipo (fijo, móvil y Nauta) | — |
| Devolver su nombre como nombre para mostrar | — |

---

## Representante

| | |
| --- | --- |
| **Tipo** | Clase concreta |
| **Superclase** | — |
| **Subclases** | — |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer su nombre o nombres, apellidos y carné de identidad | `Validador` |
| Validar sus datos al crearse y al modificarse | `Validador`, `ExcepcionValidacion` |
| Existir de forma independiente de la entidad que representa (agregación) | `PersonaJuridica`, `EntidadNoEstatal` |

---

## Llamador

| | |
| --- | --- |
| **Tipo** | Interfaz (dada por el enunciado) |
| **Superclase** | — |
| **Implementada por** | Sistema externo; se usa a través de `LlamadorAdapter` |

| Responsabilidades | Colaboradores |
| --- | --- |
| Realizar una llamada y devolverla como objeto (`Llamada realizarLlamada(...)`) | `Llamada` |

---

## Llamada

| | |
| --- | --- |
| **Tipo** | Clase abstracta |
| **Superclase** | — |
| **Subclases** | `LlamadaFijo`, `LlamadaMovil` |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer el número de destino | `Validador` |
| Conocer la duración de la llamada (no negativa) | `Validador`, `ExcepcionValidacion` |
| Conocer el mes en que se realizó | — |

---

## LlamadaFijo

| | |
| --- | --- |
| **Tipo** | Clase concreta |
| **Superclase** | `Llamada` |
| **Subclases** | — |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer la provincia y el municipio de destino | `Provincias` |
| Indicar si es local o de larga distancia respecto al fijo que la origina | `TelefonoFijo` |
| Facturarse en la factura del mes del fijo que la origina | `FacturaFijo`, `LlamadaLargaDistancia` |

---

## LlamadaMovil

| | |
| --- | --- |
| **Tipo** | Clase concreta |
| **Superclase** | `Llamada` |
| **Subclases** | — |

| Responsabilidades | Colaboradores |
| --- | --- |
| Conocer el monto de la llamada | `EstrategiaTarifa` |
| Cargarse al registro del mes del móvil (también cuando sale de un fijo hacia un móvil) | `TelefonoMovil`, `RegistroMovil` |

---

## LlamadorAdapter

| | |
| --- | --- |
| **Tipo** | Clase concreta (patrón Adapter) |
| **Superclase** | — |
| **Implementa / adapta** | `Llamador` |

| Responsabilidades | Colaboradores |
| --- | --- |
| Recibir las llamadas que produce el `Llamador` externo | `Llamador`, `Llamada` |
| Encontrar el servicio de origen y el de destino de cada llamada | `Etecsa`, `Servicio` |
| Si la llamada sale de un fijo hacia un móvil, cargarla al móvil | `TelefonoMovil`, `LlamadaMovil` |
| Registrar las demás llamadas en la factura del mes del fijo (local o larga distancia) | `TelefonoFijo`, `FacturaFijo`, `LlamadaFijo` |
| Aislar al modelo de cambios en la interfaz externa | — |
