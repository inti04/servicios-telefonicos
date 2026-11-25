# Servicios Telefónicos (DPOO)

Aplicación de escritorio en Java (Eclipse, Swing) para que ETECSA controle sus clientes
(persona natural, persona jurídica y entidad no estatal) y sus servicios (telefonía fija,
telefonía móvil y cuentas Nauta).

Autores: Inti y Carli.

## Abrir en Eclipse

1. `git clone https://github.com/inti04/servicios-telefonicos.git`
2. En Eclipse: **File → Import → General → Existing Projects into Workspace** y elegir la carpeta clonada.
3. El proyecto usa JavaSE-1.8, codificación UTF-8 y JUnit 4 (carpeta `test/`).

## Estructura

```
src/cu/cujae/serviciostelefonicos/
├── logica/        capa lógica (Etecsa, clientes, servicios, llamadas, tarifas, fabricas, reportes)
├── excepciones/   excepciones propias
├── utiles/        Validador, Provincias, DatosPrueba
└── interfaz/      capa de presentación (Swing): clientes, fijo, movil, nauta, reportes, ayuda
test/              pruebas JUnit de la capa lógica
docs/              informe, tarjetas CRC, diagramas, capturas
```

La interfaz solo llama a `Etecsa`; nunca crea objetos del modelo ni hace cálculos.

## Flujo de trabajo

- `main`: versión entregable, etiquetada en cada corte (`corte-1`, `corte-2`, `corte-3`, `final`).
- `develop`: integración; siempre debe compilar.
- `inti/<tema>` y `carli/<tema>`: una rama por funcionalidad, salen de `develop` y vuelven por pull request.
- Nadie hace merge de su propio pull request.
- Commits en español con prefijo: `feat:`, `fix:`, `docs:`, `test:`, `refactor:`.
