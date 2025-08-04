# Sistema de Gestión de Inventario

Este proyecto es una aplicación de consola en Java que permite gestionar un inventario básico, incluyendo usuarios, stock, productos, categorias de productos, asignaciones, operaciones, roles de usuario, proveedores. Está estructurado con buenas prácticas de programación orientada a objetos y divide las responsabilidades en paquetes como `SERVICE`,`DAO`, `MODEL`, y `MAINS`.

El proyecto implementa el patrón de diseño DAO (Data Access Object) para separar la lógica de acceso a los datos de la lógica de negocio. Esta arquitectura permite que el código sea más mantenible, modular y reutilizable.

Cada entidad del sistema (como productos, asignaciones, compras, etc.) cuenta con su propio archivo DAO, el cual encapsula todas las operaciones relacionadas con la base de datos (como consultas, inserciones, actualizaciones y eliminaciones). Esto facilita el desacoplamiento del código y permite realizar cambios en la lógica de acceso a los datos sin afectar otras partes del sistema.


## Características

- **Gestión de Bases de datos:** Crear, listar, actualizar y eliminar usuarios.
- **Diseño modular:** Separación clara de responsabilidades por capas.
- **Interfaz de consola:** Menús interactivos para cada módulo.

## Estructura del Proyecto

```
├── DAO/
│   ├── AssignmentsDAO.java
│   ├── CategoriesDAO.java
│   ├── Connect_DB.java
│   ├── OperationsDAO.java
│   ├── ProductsDAO.java
│   ├── RoleDAO.java
│   ├── StockDAO.java
│   ├── SuppliersDAO.java
│   └── UserDAO.java
├── MAINS/
│   ├── mainAssignments.java
│   ├── mainCategories.java
│   ├── mainOperation.java
│   ├── mainProducts.java
│   ├── mainRole.java
│   ├── mainStock.java
│   ├── mainSuppliers.java
│   └── mainUsers.java
├── MODEL/
│   ├── Assignments.java
│   ├── Categories.java
│   ├── Operations.java
│   ├── Products.java
│   ├── Role.java
│   ├── Stock.java
│   ├── Suppliers.java
│   └── User.java
├── SERVICE/
│   ├── AssignmentsService.java
│   ├── CategoriesService.java
│   ├── OperationsService.java
│   ├── ProductsService.java
│   ├── RoleService.java
│   ├── StockService.java
│   ├── SuppliersService.java
│   └── UserService.java
├── utils/
│   ├── PasswordUtils.java
├── mainDef.java
└── README.md
```

## Explicacion del programa

### DAO (Data Access Object)
El patrón **DAO** encapsula todas las operaciones con la base de datos (CRUD). Cada entidad (como Usuario, Producto, etc.) tiene su propio DAO. Esto permite separar la lógica de negocio del acceso a datos, lo que facilita el mantenimiento, la escalabilidad y las pruebas del sistema.

### MODEL
Contiene las clases que representan las entidades del sistema. Cada clase es una abstracción directa de una tabla en la base de datos: `User`, `Products`, `Role`, etc.

### SERVICE
Es la capa intermedia entre los `MAINS` y los `DAO`. Contiene la lógica del negocio y maneja la interacción del usuario a través de consola (lectura de inputs, validaciones básicas, etc.).

### UTILS - Hasheo de Contraseñas
La carpeta `utils/` contiene `PasswordUtils.java`, que implementa el hasheo de contraseñas usando **PBKDF2 con HMAC SHA-256**. Este sistema genera un `salt` aleatorio para cada contraseña y combina `salt:hash` para almacenamiento seguro.

**Ejemplo de uso:**

```java
String password = "1234";
String hashed = PasswordUtils.hashPassword(password);
boolean isValid = PasswordUtils.verifyPassword("1234", hashed);
```

Esto asegura que incluso si dos usuarios tienen la misma contraseña, sus hashes almacenados serán diferentes.

### MAINS
Cada archivo en `MAINS/` representa un menú individual para cada entidad del sistema. Permite realizar acciones como crear, listar, actualizar y eliminar registros.

### mainDef.java
Es el **menú principal** del sistema. Desde aquí se accede a cada módulo (`Usuarios`, `Roles`, `Proveedores`, etc.) usando una interfaz de texto. Sirve como punto de entrada (`main`) de la aplicación.


## Requisitos

- Java JDK 11 o superior
- IDE como IntelliJ IDEA, Eclipse o VSCode (opcional pero recomendado)

## Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu_usuario/tu_repositorio.git
   ```

2. Compila y ejecuta el archivo de entrada (`mainDef`) desde tu IDE o terminal.

## Contribuciones

Las contribuciones son bienvenidas. Puedes enviar *pull requests* o reportar problemas mediante *issues*.

---

 *Proyecto simple pero funcional para fines educativos o como base para algo más grande.*
