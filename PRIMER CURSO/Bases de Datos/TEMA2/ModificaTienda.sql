/* Añadir a la tabla STOCK Una columna de tipo fecha, 
llamada FechaUltimaEntrada, que por defecto tome el valor de la fecha actual.*/
ALTER TABLE STOCK ADD FechaUltimaEntrada DATE DEFAULT SYSDATE;

/*Una columna llamada Beneficio, que contendrá el tipo de porcentaje de beneficio 
que esa tienda aplica en ese producto.Se debe controlar que el valor que almacene sea 1,2, 3, 4 o 5.*/
ALTER TABLE PRODXTIENDAS ADD( 
Beneficio NUMBER,
CONSTRAINT CHK_BENEFICIO CHECK (Beneficio IN(1,2,3,4,5))
); 

/*En la tabla PRODUCTO Eliminar de la tabla producto la columna Descripción.
Añadir una columna llamada perecedero, que únicamente acepte los valores: S o N.
Modificar el tamaño de la columna Denoproducto a 50.
*/

ALTER TABLE producto DROP COLUMN DESCRIPCION; 
ALTER TABLE producto ADD( 
perecedero CHAR(1),
CONSTRAINT SI_NOPERECE CHECK (perecedero IN ('S','N')) 
);
ALTER TABLE producto MODIFY Denoproducto VARCHAR2(50);

/*En la tabla FAMILIA Añadir una columna llamada IVA, que represente el porcentaje de IVA
 y únicamente pueda contener los valores 21,10,ó 4.*/

 ALTER TABLE FAMILIA ADD ( 
 IVA NUMBER,
 CONSTRAINT TIPOSIVA CHECK (IVA IN (21,10,4))
 );

 /*En la tabla tienda La empresa desea restringir el número de tiendas con las que trabaja, de forma que no pueda 
haber más de una tienda en una misma zona (la zona se identifica por el código postal). Definir 
mediante DDL las restricciones necesarias para que se cumpla en el campo correspondiente.*/

ALTER TABLE TIENDA ADD CONSTRAINT ZONA_UNICA UNIQUE (CODIGOPOSTAL);

-- B) Renombra la tabla STOCK por PRODXTIENDAS.

ALTER TABLE STOCK RENAME TO PRODXTIENDAS;

-- C) Elimina la tabla FAMILIA y su contenido si lo tuviera.

DROP TABLE FAMILIA CASCADE CONSTRAINT;

-- D) Crea un usuario llamado C##INVITADO siguiendo los pasos de la unidad 1 y dale todos los privilegios sobre la tabla PRODUCTO.

CREATE USER C##INVITADO IDENTIFY BY invitado123;
GRANT CREATE SESSION TO C##INVITADO;
GRANT SELECT,INSERT,UPDATE,DELETE ON PRODUCTO TO C##INVITADO;

-- E) Retira los permisos de modificar la estructura de la tabla y borrar contenido de la tabla PRODUCTO al usuario anterior.

REVOKE DELETE ON PRODUCTO FROM C##INVITADO;

