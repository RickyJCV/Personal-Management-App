# Proyecto Gestión Empleados y Usuarios

#### Proyecto Final de Programación en Java, usando el Modelo, Vista, Controlador. Aplicación con Interfaz Gráfica de Gestión de Empleados y Usuarios.

>Ricardo Jesús Cabrera Valero

1. __**LOGIN**__
 * El usuario puede iniciar sesión, registrarse o restablecer su contraseña . Si pulsa en restablecer contraseña debe introducir su correo, y si está en la Base de datos automáticamente genera una nueva clave, introduce cifrada en la Base de datos y se la muestra al usuario.
 
2. __**HOME**__
 * Aparece el home del administrador o usuario, dependiendo del login.
    * **Home usuario:** Puede ver una tabla con los turnos, sueldo, etc... Y puede realizar una búsqueda por código de empleado para buscar su ficha concreta. También cuenta con un botón para imprimir por impresora o PDF.
    
    * **Home administrador:** Puede administrar a los usuarios y a los empleados.
      * **Ver usuarios:** Muestra una tabla con los usuarios y cuenta con dos botones, uno para buscar por código de usuario y otro para generar un archivo CSV (Usado en excel) de la tabla.
      * **Dar alta usuarios:** Muestra una ventana para rellenar los datos de un usuario y registrarlo en la BBDD.
      * **Administrar usuarios:** Muestra una ventana para modificar o eliminar usuarios, buscando previamente por su código. También cuenta con un botón para limpiar las casillas de texto.
      
      * **Ver empleados:** Muestra una tabla con los empleados y cuenta con un botón para buscar por código de empleado y otro para imprimir por impresora o PDF la tabla.
      * **Administrar empleados:** Muestra una ventana para registrar, modificar o eliminar empleados, buscando previamente por código de empleado si se quiere modificar o eliminar. También cuenta con un botón para limpiar las casillas de texto.
