#  Acceso a Datos – Ejercicio 5

Tarea para AD05.
 Detalles de la tarea de esta unidad.
 Enunciado.
 EJERCICIO 1
 Crear una base de datos llamada DBJefeHijo con DB40 con la siguiente información:
 public static void main(String[] args)
 {
 File fichero=new File("baseDatos");
 fichero.delete();
 /*Este código anterior lo ponemos por si la base de datos ya existiera y quisiéramos 
empezar desde el principio.*/
 ObjectContainer baseDatos=Db4oEmbedded.openFile("BDJefeHijo ");
 baseDatos.store(new Jefe("Ángel", 5, 53,new Hijo("Gustavo", 7)));
 baseDatos.store(new Jefe("Nieves", 3, 45,new Hijo("Iván", 3)));
 baseDatos.store(new Jefe("Jesús", 3, 5,new Hijo("Noelia", 3)));
 baseDatos.store(new Jefe("Dolores", 5,63,new Hijo("Sergio", 7)));
 baseDatos.store(new Jefe("Vicki", 3, 5,null));
 baseDatos.store(new Jefe("Fátima", 5,63,new Hijo("Lidia", 27)));
 baseDatos.store(new Jefe("Juan Luís", 3, 5,null));
 baseDatos.store(new Jefe("Elena", 1,42,new Hijo("David", 19)));
 baseDatos.store(new Jefe("Miguel", 20,45,new Hijo("Paula", 3)));
 baseDatos.store(new Jefe("Jesús", 19, 44,new Hijo("Rubén", 12)));
 baseDatos.close();
 }
 Realizar las siguientes consultas:
 1. Visualizar los jefes que tengan más de 55 años.
 2. Modificar la edad de Miguel incrementando su edad un año más.
 3. Borrar los jefes que llevan más de 6 años en la empresa.
 4. Visualizar todos los jefes que quedan, incluidos sus hijos, que no han sido borrados 
anteriormente.
 EJERCICIO 2
 Dado el siguiente modelo de datos
 CREATE TABLE CLIENTES
 (
 IDCLIENTE NUMBER PRIMARY KEY,
 NOMBRE VARCHAR2(50),
 DIRECCION VARCHAR2(50),
 POBLACION VARCHAR2(50),
 CODPOSTAL NUMBER(5),
 PROVINCIA VARCHAR2(40),
 NIF VARCHAR2(9) UNIQUE,
 TELEFONO1 VARCHAR2(15),
 TELEFONO2 VARCHAR2(15),
 TELEFONO3 VARCHAR2(15)
 );
 CREATE TABLE PRODUCTOS
 (
 IDPRODUCTO NUMBER PRIMARY KEY,
 DESCRIPCION VARCHAR2(80),
 PVP NUMBER,
 STOCKACTUAL NUMBER
 );
CREATE TABLE VENTAS
 (
 IDVENTA NUMBER PRIMARY KEY,
 IDCLIENTE NUMBER NOT NULL REFERENCES CLIENTES,
 FECHAVENTA DATE
 );
 CREATE TABLE LINEASVENTAS
 (
 IDVENTA NUMBER,
 NUMEROLINEA NUMBER,
 IDPRODUCTO NUMBER,
 CANTIDAD NUMBER,
 FOREIGN KEY (IDVENTA) REFERENCES VENTAS (IDVENTA),
 FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTOS (IDPRODUCTO),
 PRIMARY KEY (IDVENTA, NUMEROLINEA)
 );
 1. Definir un tipo varray de dimensión 3 para contener los teléfonos
 2. Crear los tipos dirección, cliente, producto y línea de venta
 3. Crear un tipo tabla anidada para contener las líneas de una venta:
 4. Crear un tipo venta para los datos de las ventas, cada venta tendrá un atributo LINEAS del 
tipo tabla anidada definida anteriormente:
 5. Crea el cuerpo del tipo anterior, teniendo en cuenta que se definirá la función miembro 
TOTAL_VENTA que calcula el total de la venta de las líneas de venta que forman parte de 
una venta, contará el número de elementos de una tabla o de un array y devolverá el número 
de líneas que tiene la venta.
 6. Crear las tablas donde almacenar los objetos de la aplicación. Se creará una tabla para 
clientes, otra para productos y otra para las ventas, en dichas tablas se definirán las 
oportunas claves primarias.
 7. Inserta dos clientes y cinco productos.
 8. Insertar en TABLA_VENTAS la venta con IDVENTA 1 para el IDCLIENTE 1
 9. Insertar en TABLA_VENTAS dos líneas de venta para el IDVENTA 1 para los productos 1 
(la CANTIDAD es 1) y 2 (la CANTIDAD es 2)
 10.Insertar en TABLA_VENTAS la venta con IDVENTA 2 para el IDCLIENTE
 11.Insertar en TABLA_VENTAS tres líneas de venta para el IDVENTA 2 para los productos 1 
(la CANTIDAD es 2), 4 (la CANTIDAD es 1) y 5 (la CANTIDAD es 4)
 12.Realizar un procedimiento que recibiendo el identificador visualice los datos de la venta.


 ## Respuestas del ejercicio 2 

 1.Definir un tipo varray de dimensión 3 para contener los teléfonos
 CREATE TYPE Telefonos_Typ AS VARRAY(3) OF VARCHAR2(15);
 2. Crear los tipos dirección, cliente, producto y línea de venta
 CREATE TYPE Direccion_Typ AS OBJECT (
  Direccion VARCHAR2(50),
  Poblacion VARCHAR2(50),
  CodigoPostal NUMBER(5),
  Provincia VARCHAR2(40)
 );
 CREATE TYPE Cliente_Typ AS OBJECT (
  IdCliente NUMBER,
  Nombre VARCHAR2(50),
  Direccion Direccion_Typ,
  Nif VARCHAR2(9),
 Telefonos Telefonos_Typ
 );
 CREATE TYPE Producto_Typ AS OBJECT (
  IdProducto NUMBER,
  Descripcion VARCHAR2(80),
  Pvp NUMBER,
  StockActual NUMBER
 );
 CREATE TYPE LineaVenta_Typ AS OBJECT (
  IdVenta NUMBER,
  NumeroLinea NUMBER,
  Producto Producto_Typ,
  Cantidad NUMBER
 );
 3. Crear un tipo tabla anidada para contener las líneas de una venta:
 CREATE TYPE LineasVenta_Tbl AS TABLE OF LineaVenta_Typ;
 4.Crear un tipo venta para los datos de las ventas, cada venta tendrá un atributo LINEAS del tipo 
tabla anidada definida anteriormente:
 CREATE TYPE Venta_Typ AS OBJECT (
  IdVenta NUMBER,
  Cliente Cliente_Typ,
  FechaVenta DATE,
  Lineas LineasVenta_Tbl
 );
5.Crea el cuerpo del tipo anterior, teniendo en cuenta que se definirá la función miembro 
TOTAL_VENTA que calcula el total de la venta de las líneas de venta que forman parte de una 
venta, contará el número de elementos de una tabla o de un array y devolverá el número de líneas 
que tiene la venta.
 CREATE OR REPLACE TYPE BODY Venta_Typ AS
  MEMBER FUNCTION Total_Venta RETURN NUMBER IS
    total NUMBER := 0;
  BEGIN
    FOR i IN 1..Lineas.COUNT LOOP
      total := total + (Lineas(i).Producto.Pvp * Lineas(i).Cantidad);
    END LOOP;
    RETURN total;
  END Total_Venta;
 END;
 6.Crear las tablas donde almacenar los objetos de la aplicación. Se creará una tabla para clientes, 
otra para productos y otra para las ventas, en dichas tablas se definirán las oportunas claves 
primarias.
 CREATE TABLE CLIENTES OF Cliente_Typ (
  CONSTRAINT pk_clientes PRIMARY KEY (IdCliente)
 );
 CREATE TABLE PRODUCTOS OF Producto_Typ (
  CONSTRAINT pk_productos PRIMARY KEY (IdProducto)
 );
 CREATE TABLE VENTAS OF Venta_Typ (
  CONSTRAINT pk_ventas PRIMARY KEY (IdVenta)
 );
 7.Inserta dos clientes y cinco productos.
 CLIENTES
 INSERT INTO CLIENTES VALUES (
  Cliente_Typ(1, 'Juan Pérez', Direccion_Typ('Calle Falsa 123', 'Madrid', 28080, 'Madrid'), 
'123456789', Telefonos_Typ('123456789', '987654321', '555555555'))
 );
 INSERT INTO CLIENTES VALUES (
  Cliente_Typ(2, 'Ana Gómez', Direccion_Typ('Avenida del Sol 456', 'Barcelona', 08001, 
'Barcelona'), '987654321', Telefonos_Typ('654321987', '112233445', '998877665'))
 );
 PRODUCTOS
 INSERT INTO PRODUCTOS VALUES (1, 'Producto A', 10.5, 100);
 INSERT INTO PRODUCTOS VALUES (2, 'Producto B', 20.0, 200);
 INSERT INTO PRODUCTOS VALUES (3, 'Producto C', 15.0, 150);
 INSERT INTO PRODUCTOS VALUES (4, 'Producto D', 30.0, 50);
 INSERT INTO PRODUCTOS VALUES (5, 'Producto E', 25.0, 75);
8.Insertar en TABLA_VENTAS la venta con IDVENTA 1 para el IDCLIENTE 1
 INSERT INTO VENTAS VALUES (
 Venta_Typ(1, Cliente_Typ(1, 'Juan Pérez', Direccion_Typ('Calle Falsa 123', 'Madrid', 28080, 
'Madrid'), '123456789', Telefonos_Typ('123456789', '987654321', '555555555')), 
           SYSDATE, LineasVenta_Tbl())
 );
 9.Insertar en TABLA_VENTAS dos líneas de venta para el IDVENTA 1 para los productos 1 (la 
CANTIDAD es 1) y 2 (la CANTIDAD es 2)
 INSERT INTO LINEASVENTAS (IDVENTA, NUMEROLINEA, IDPRODUCTO, 
CANTIDAD) 
VALUES (1, 1, 1, 1);
 INSERT INTO LINEASVENTAS (IDVENTA, NUMEROLINEA, IDPRODUCTO, 
CANTIDAD) 
VALUES (1, 2, 2, 2);
 10. Insertar en TABLA_VENTAS la venta con IDVENTA 2 para el IDCLIENTE
 INSERT INTO VENTAS VALUES (
 Venta_Typ(2, Cliente_Typ(2, 'Ana Gómez', Direccion_Typ('Avenida del Sol 456', 'Barcelona', 
08001, 'Barcelona'), '987654321', Telefonos_Typ('654321987', '112233445', '998877665')), 
           SYSDATE, LineasVenta_Tbl())
 );
 11. Insertar en TABLA_VENTAS tres líneas de venta para el IDVENTA 2 para los productos 1 (la 
CANTIDAD es 2), 4 (la CANTIDAD es 1) y 5 (la CANTIDAD es 4)
 INSERT INTO LINEASVENTAS (IDVENTA, NUMEROLINEA, IDPRODUCTO, 
CANTIDAD) VALUES (2, 1, 1, 2); 
INSERT INTO LINEASVENTAS (IDVENTA, NUMEROLINEA, IDPRODUCTO, 
CANTIDAD) VALUES (2, 2, 4, 1); 
INSERT INTO LINEASVENTAS (IDVENTA, NUMEROLINEA, IDPRODUCTO, 
CANTIDAD) VALUES (2, 3, 5, 4); 
12.Realizar un procedimiento que recibiendo el identificador visualice los datos de la venta.
 SELECT V.TOTAL_VENTA() FROM TABLA_VENTAS V;