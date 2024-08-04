## Creadores
Topicos Avanzados de programación veranoa
Lopez Gonzalez Eder Denilson - Estudiante del ITO - [Pooh2017](https://github.com/Pooh2017)

Eduardo Jiménez Mendoza- Estudiante del ITO

# Gestor de Biblioteca

Este es un sistema de gestión de biblioteca desarrollado en Java utilizando el framework Swing para la interfaz gráfica de usuario (GUI). El sistema permite a los usuarios registrar, gestionar y prestar libros, así como llevar un control de los préstamos.

## Índice

- [Descripción](#descripción)
- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Cómo Ejecutar](#cómo-ejecutar)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Descripción

El proyecto es una aplicación de escritorio para gestionar una biblioteca. Permite registrar usuarios, gestionar libros, realizar préstamos y devolver libros. La aplicación sigue el patrón de diseño Modelo-Vista-Controlador (MVC) para separar la lógica de negocio, la interfaz de usuario y el control de eventos.

## Características

- **Registro de Usuarios:** Permite registrar nuevos usuarios en la biblioteca.
- **Gestión de Libros:** Agregar, editar y eliminar libros de la base de datos.
- **Préstamos de Libros:** Realizar préstamos y registrar las fechas de devolución.
- **Interfaz Gráfica:** Utiliza Swing para una interfaz gráfica de usuario intuitiva.
- **Paginación en Tablas:** Muestra un número limitado de registros por página para facilitar la navegación.

## Tecnologías Utilizadas

- **Java:** Lenguaje de programación principal.
- **Swing:** Framework para la interfaz gráfica de usuario.
- **MySQL:** Base de datos para almacenar información sobre usuarios, libros y préstamos.
- **Maven:** Herramienta de gestión de proyectos y construcción (si aplica).

## Estructura del Proyecto

### Controladores

- **controladorRegistrarUsuario:** Maneja la vista de registro de usuarios.
- **controladorPrestamo:** Gestiona los préstamos de libros, incluyendo la verificación de disponibilidad y actualización de copias.
- **controladorLogin:** Controla el inicio de sesión de los usuarios y la validación de credenciales.
- **controladorLibro:** Administra la gestión de libros, incluyendo inserción, edición, eliminación y búsqueda.

### Modelos

- **modeloRegistrarUsuario:** Modelo para gestionar los datos de registro de usuarios.
- **modeloPrestamo:** Modelo para gestionar los datos de los préstamos de libros.
- **modeloLogin:** Modelo para manejar la autenticación de usuarios.
- **modeloLibro:** Modelo para gestionar los datos de los libros.

### Vistas

- **RegistarUsuario:** Interfaz para el registro de nuevos usuarios.
- **Prestamos:** Interfaz para gestionar los préstamos de libros.
- **Login:** Interfaz para el inicio de sesión de los usuarios.
- **Libros:** Interfaz para gestionar los libros de la biblioteca.

## Cómo Ejecutar

1. **Clona el repositorio:**
   ```sh
   git clone https://github.com/tu-usuario/gestor-biblioteca.git

## Funcionalidades

- **Inicio de Sesión:** Los usuarios pueden iniciar sesión con credenciales válidas.
- **CRUD de Libros:** Permite crear, leer, actualizar y eliminar libros en la biblioteca.
- **CRUD de Usuarios:** Permite crear, leer, actualizar y eliminar usuarios en el sistema.
- **Préstamo y Devolución de Libros:** Los usuarios pueden prestar y devolver libros.

## Api
# Biblioteca - Gestión de Préstamos y Libros

Este proyecto es un sistema para la gestión de préstamos y libros en una biblioteca. Está implementado en Java utilizando la biblioteca Swing para la interfaz gráfica y SQL para la gestión de datos.

## Clases y Métodos

### `modeloDevoluciones`

Esta clase maneja la lógica relacionada con los préstamos y devoluciones de libros.

#### Campos
| **Tipo**    | **Campo**    | **Descripción**                           |
|-------------|--------------|-------------------------------------------|
| `int`       | `idP`        | Identificador del préstamo.               |
| `int`       | `idU`        | Identificador del usuario.                |
| `int`       | `IdL`        | Identificador del libro.                  |
| `String`    | `nombre`     | Nombre del usuario.                       |
| `String`    | `apellidoP`  | Apellido paterno del usuario.             |
| `String`    | `apellidoM`  | Apellido materno del usuario.             |
| `String`    | `titulo`     | Título del libro.                         |
| `String`    | `fechaP`     | Fecha del préstamo.                       |
| `String`    | `fechaD`     | Fecha de devolución estimada.             |

#### Métodos
| **Tipo**    | **Nombre**                | **Tipo de Dato que Retorna** | **Tipo de dato que recibe** | **Descripción**                               |
|-------------|---------------------------|------------------------------|-----------------------------|-----------------------------------------------|
| `List<modeloDevoluciones>` | `listaPrestamos` | `List<modeloDevoluciones>` | -                           | Devuelve una lista de préstamos.             |
| `boolean`   | `eliminarPrestamo`        | `boolean`                    | `int idPrestamo`            | Elimina un préstamo por ID.                  |
| `void`      | `actualizarNumeroCopias`  | `void`                       | `int idLibro, int nuevoNumeroCopias` | Actualiza el número de copias de un libro.    |
| `int`       | `obtenerNumeroCopias`     | `int`                        | `int idLibro`               | Obtiene el número de copias de un libro.     |

### `modeloLibro`

Esta clase maneja la lógica relacionada con los libros en la biblioteca.

#### Campos
| **Tipo**    | **Campo**            | **Descripción**                           |
|-------------|----------------------|-------------------------------------------|
| `int`       | `id`                 | Identificador del libro.                  |
| `String`    | `isbn`               | ISBN del libro.                           |
| `int`       | `numeroCopias`       | Número de copias disponibles.             |
| `String`    | `titulo`             | Título del libro.                         |
| `String`    | `autor`              | Autor del libro.                          |
| `String`    | `fechaPublicacion`   | Fecha de publicación del libro.           |
| `String`    | `editorial`          | Editorial del libro.                      |

#### Métodos
| **Tipo**    | **Nombre**                | **Tipo de Dato que Retorna** | **Tipo de dato que recibe** | **Descripción**                               |
|-------------|---------------------------|------------------------------|-----------------------------|-----------------------------------------------|
| `List<modeloLibro>` | `listaLibro` | `List<modeloLibro>` | -                           | Devuelve una lista de libros.                |
| `boolean`   | `registrarLibro`          | `boolean`                    | -                           | Registra un nuevo libro en la base de datos. |
| `boolean`   | `actualizarLibro`         | `boolean`                    | `int id`                    | Actualiza los detalles de un libro.          |
| `boolean`   | `eliminarLibro`           | `boolean`                    | `int id`                    | Elimina un libro por ID.                     |
| `List<modeloLibro>` | `buscarLibros` | `List<modeloLibro>` | `String busqueda`           | Busca libros en la base de datos.            |

### `modeloLogin`

Esta clase maneja la autenticación de usuarios y la gestión de sesiones.

#### Campos
| **Tipo**    | **Campo**            | **Descripción**                           |
|-------------|----------------------|-------------------------------------------|
| `String`    | `correo`             | Correo del usuario.                       |
| `String`    | `contraseña`         | Contraseña del usuario.                   |
| `String`    | `usuario`            | Nombre de usuario.                       |
| `String`    | `tipoUsuario`        | Tipo de usuario (Administrador o Usuario).|
| `String`    | `mensajeValidacion`  | Mensaje de validación para errores.       |

#### Métodos
| **Tipo**    | **Nombre**                | **Tipo de Dato que Retorna** | **Tipo de dato que recibe** | **Descripción**                               |
|-------------|---------------------------|------------------------------|-----------------------------|-----------------------------------------------|
| `boolean`   | `verificarContrasenaExistente` | `boolean`                    | -                           | Verifica si el correo y contraseña son válidos.|
| `boolean`   | `verificarCorreoExistente` | `boolean`                    | -                           | Verifica si el correo está registrado.       |
| `void`      | `verOjo`                  | `void`                       | `JToggleButton toglebtnOjo, JPasswordField passwordField` | Muestra u oculta la contraseña en el campo de texto. |
| `void`      | `showWindows`            | `void`                       | `JPanel vista, JPanel panelVista` | Muestra una vista en un panel.               |

## Modelo `modeloPrestamo`

### Campos

| Campo                       | Descripción                                    |
|-----------------------------|------------------------------------------------|
| `idPrestamo`                | Identificador del préstamo.                   |
| `idUsuario`                 | Identificador del usuario que realizó el préstamo. |
| `idLibro`                   | Identificador del libro prestado.             |
| `fechaPrestamo`             | Fecha en que se realizó el préstamo.          |
| `fechaDevolucionEstimada`   | Fecha estimada para la devolución del libro.  |

### Métodos

| Método                               | Descripción                                             |
|--------------------------------------|---------------------------------------------------------|
| `registrarPrestamo()`                | Registra un nuevo préstamo en la base de datos. Retorna un `boolean` indicando el éxito de la operación. |
| `obtenerNumeroCopiasDisponibles(int idLibro)` | Obtiene el número de copias disponibles de un libro. Retorna un `int` con la cantidad disponible. |
| `actualizarNumeroCopias(int idLibro, int nuevoNumeroCopias)` | Actualiza el número de copias disponibles de un libro. Recibe dos `int`: el ID del libro y el nuevo número de copias. No retorna valor. |

---

## Modelo `modeloRegistrarUsuario`

### Campos

| Campo            | Descripción                                      |
|------------------|--------------------------------------------------|
| `nombre`         | Nombre del usuario.                             |
| `apellidoP`      | Apellido paterno del usuario.                   |
| `apellidoM`      | Apellido materno del usuario.                   |
| `fechaNacimiento`| Fecha de nacimiento del usuario.                |
| `telefono`       | Teléfono del usuario.                           |
| `domicilio`      | Domicilio del usuario.                          |
| `nombreUsuario`  | Nombre de usuario para el login.                |
| `correo`         | Correo electrónico del usuario.                 |
| `contraseña`     | Contraseña del usuario.                         |
| `tipoUsuario`    | Tipo de usuario (Administrador o Usuario).      |

### Métodos

| Método                           | Descripción                                             |
|----------------------------------|---------------------------------------------------------|
| `existeUsuario()`                | Verifica si el nombre de usuario ya existe. Retorna un `boolean`. |
| `existeCorreo()`                 | Verifica si el correo electrónico ya existe. Retorna un `boolean`. |
| `registrarUsuario()`             | Registra un nuevo usuario en la base de datos. Retorna un `boolean` indicando el éxito de la operación. |

---

## Modelo `modeloUsuario`

### Campos

| Campo            | Descripción                                      |
|------------------|--------------------------------------------------|
| `id`             | Identificador del usuario.                      |
| `nombre`         | Nombre del usuario.                             |
| `apellidoP`      | Apellido paterno del usuario.                   |
| `apellidoM`      | Apellido materno del usuario.                   |
| `fechaNacimiento`| Fecha de nacimiento del usuario.                |
| `telefono`       | Teléfono del usuario.                           |
| `domicilio`      | Domicilio del usuario.                          |
| `nombreUsuario`  | Nombre de usuario para el login.                |
| `correo`         | Correo electrónico del usuario.                 |
| `contraseña`     | Contraseña del usuario.                         |
| `tipoUsuario`    | Tipo de usuario (Administrador o Usuario).      |

### Métodos

| Método                           | Descripción                                             |
|----------------------------------|---------------------------------------------------------|
| `existeUsuario()`                | Verifica si el nombre de usuario ya existe. Retorna un `boolean`. |
| `existeCorreo()`                 | Verifica si el correo electrónico ya existe. Retorna un `boolean`. |
| `listaUsuario()`                 | Obtiene una lista de todos los usuarios. Retorna una `List<modeloUsuario>`. |
| `registrarUsuario()`             | Registra un nuevo usuario en la base de datos. Retorna un `boolean` indicando el éxito de la operación. |
| `actualizarUsuario(int id)`      | Actualiza la información del usuario con el ID dado. Recibe un `int` (ID del usuario) y retorna un `boolean`. |
| `eliminarUsuario(int id)`        | Elimina el usuario con el ID dado. Recibe un `int` (ID del usuario) y retorna un `boolean`. |
| `buscarUsuarios(String busqueda)`| Busca usuarios según una cadena de texto. Recibe un `String` (cadena de búsqueda) y retorna una `List<modeloUsuario>`. |


### Clases de Controladores

#### `controladorRegistrarUsuario`

- **Descripción**: Controlador para la ventana de registro de usuarios.
- **Atributos**:
  - `modelo`: Instancia de `modeloRegistrarUsuario`.
  - `vista`: Instancia de `RegistarUsuario`.
- **Métodos**:
  - `controladorRegistrarUsuario(modeloRegistrarUsuario modelo, RegistarUsuario vista)`: Constructor que inicializa el modelo y la vista.
  - `iniciar()`: Hace visible la vista de registro de usuario.

#### `controladorPrestamo`

- **Descripción**: Controlador para la gestión de préstamos de libros.
- **Atributos**:
  - `modelo`: Instancia de `modeloPrestamo`.
  - `vista`: Instancia de `Prestamos`.
  - `vista2`: Instancia de `Usuarios2`.
  - `controladorUsuario`: Controlador para la vista de usuarios.
  - `vista3`: Instancia de `Libros`.
  - `conLibro`: Controlador para la vista de libros.
  - `fechaActual`: Fecha actual.
  - `fechaFormateada`: Fecha actual en formato `yyyy-MM-dd`.
  - `fechaDespuesDeCincoDias`: Fecha 5 días después de la fecha actual.
  - `fechaDespuesDeCincoDiasFormateada`: Fecha 5 días después en formato `yyyy-MM-dd`.
- **Métodos**:
  - `controladorPrestamo(Prestamos vista)`: Constructor que inicializa la vista y los controladores asociados.
  - `actionPerformed(ActionEvent e)`: Maneja los eventos de los botones para prestar libros.
  - `prestamoLibro()`: Registra el préstamo del libro.
  - `numeroCopias()`: Verifica y actualiza el número de copias disponibles del libro.

#### `controladorLogin`

- **Descripción**: Controlador para la ventana de inicio de sesión.
- **Atributos**:
  - `modelo`: Instancia de `modeloLogin`.
  - `vista`: Instancia de `Login`.
  - `funciones`: Instancia de `Utilerias` para funcionalidades adicionales.
- **Métodos**:
  - `controladorLogin(modeloLogin modelo, Login vista)`: Constructor que inicializa el modelo y la vista.
  - `actionPerformed(ActionEvent e)`: Maneja los eventos de inicio de sesión y visibilidad de la contraseña.
  - `verificarUsuario()`: Verifica las credenciales del usuario y muestra la ventana correspondiente.

#### `controladorLibro`

- **Descripción**: Controlador para la gestión de libros.
- **Atributos**:
  - `formatoFecha`: Formato de fecha `yyyy-MM-dd`.
  - `utilerias`: Instancia de `Utilerias` para funcionalidades adicionales.
  - `tablad`: Modelo de tabla para la vista de libros.
  - `modelo`: Instancia de `modeloLibro`.
  - `vista`: Instancia de `Libros`.
- **Métodos**:
  - `controladorLibro(Libros vista)`: Constructor que inicializa la vista y los eventos asociados.
  - `actionPerformed(ActionEvent e)`: Maneja los eventos de insertar, editar y eliminar libros.
  - `limpiarTabla()`: Limpia los datos de la tabla de libros.
  - `valoresLista(JTable tabla)`: Llena la tabla con los datos de libros.
  - `alta()`: Registra un nuevo libro.
  - `editar()`: Edita la información de un libro existente.
  - `eliminar()`: Elimina un libro.
  - `buscarLibros()`: Busca libros en la tabla basada en un criterio de búsqueda.
  - `actualizarTabla(List<modeloLibro> libros)`: Actualiza la tabla con una lista de libros.

#### `controladorDevoluciones`

- **Descripción**: Controlador para la gestión de devoluciones de libros.
- **Atributos**:
  - `vista3`: Instancia de `Libros`.
  - `moL`: Instancia de `modeloLibro`.
  - `conLibro`: Controlador para la vista de libros.
  - `modeloP`: Instancia de `modeloPrestamo`.
  - `formatoFecha`: Formato de fecha `yyyy-MM-dd`.
  - `tablad`: Modelo de tabla para la vista de devoluciones.
  - `modelo`: Instancia de `modeloDevoluciones`.
  - `vista`: Instancia de `Devoluciones`.
- **Métodos**:
  - `controladorDevoluciones(Devoluciones vista)`: Constructor que inicializa la vista y los eventos asociados.
  - `actionPerformed(ActionEvent e)`: Maneja los eventos de devolución de libros.
  - `limpiarTabla()`: Limpia los datos de la tabla de devoluciones.
  - `valoresLista(JTable tabla)`: Llena la tabla con los datos de devoluciones.
  - `devolver()`: Registra la devolución del libro y actualiza el número de copias disponibles.

#### `ControladorUsuario`

- **Descripción**: Controlador para la gestión de usuarios.
- **Atributos**:
  - `formatoFecha`: Formato de fecha `yyyy-MM-dd`.
  - `utilerias`: Instancia de `Utilerias` para funcionalidades adicionales.
  - `modelo`: Instancia de `modeloUsuario`.
  - `vista`: Instancia de `Usuarios2`.
  - `tablad`: Modelo de tabla para la vista de usuarios.
- **Métodos**:
  - `ControladorUsuario(Usuarios2 vista)`: Constructor que inicializa la vista y los eventos asociados.
  - `actionPerformed(ActionEvent e)`: Maneja los eventos de insertar, editar y eliminar usuarios.
  - `limpiarTabla()`: Limpia los datos de la tabla de usuarios.
  - `valoresLista(JTable tabla)`: Llena la tabla con los datos de usuarios.
  - `alta()`: Registra un nuevo usuario.
  - `editar()`: Edita la información de un usuario existente.
  - `eliminar()`: Elimina un usuario.
  - `buscarUsuarios()`: Busca usuarios en la tabla basada en un criterio de búsqueda.
  - `actualizarTabla(List<modeloUsuario> usuarios)`: Actualiza la tabla con una lista de usuarios.

## Uso

1. Inicia el sistema.
2. Utiliza las diferentes vistas para gestionar usuarios, libros, préstamos y devoluciones.
3. Sigue las instrucciones en pantalla para realizar operaciones como agregar, editar o eliminar registros.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, sigue estos pasos para contribuir:
1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad o corrección.
3. Realiza los cambios y prueba tus modificaciones.
4. Envía un pull request detallando los cambios realizados.


## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tuusuario/tu-repositorio.git
