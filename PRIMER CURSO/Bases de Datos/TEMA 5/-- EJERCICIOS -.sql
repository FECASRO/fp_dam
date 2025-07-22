-- EJERCICIO 2 - INTRODUCIR DATOS EN LA TABLA PROFESORES
INSERT INTO PROFESORADO(Codigo,Nombre,Apellidos,DNI,Especialidad,Fecha_Nac,Antiguedad)
VALUES(1,'NURIA','ANERO GONZALEZ','58328033X','MATEMATICAS','22/02/1972',9);
INSERT INTO PROFESORADO(Codigo,Nombre,Apellidos,DNI,Especialidad,Fecha_Nac,Antiguedad)
VALUES(2,'MARIA LUISA','FABRE BERDUN','51083099F','TECNOLOGIA','31/03/1975',4);
INSERT INTO PROFESORADO(Codigo,Nombre,Apellidos,DNI,Especialidad,Fecha_Nac,Antiguedad)
VALUES(3,'JAVIER','JIMENEZ HERNANDO',NULL,'LENGUA','04/05/1969',10);
INSERT INTO PROFESORADO(Codigo,Nombre,Apellidos,DNI,Especialidad,Fecha_Nac,Antiguedad)
VALUES(4,'ESTEFANIA','FERNANDEZ MARTINEZ','19964324W','INGLES','22/06/1973',5);
INSERT INTO PROFESORADO(Codigo,Nombre,Apellidos,DNI,Especialidad,Fecha_Nac,Antiguedad)
VALUES(5,'JOSE M.','ANERO PAYAN',NULL,NULL,NULL,NULL);

/* EJERCICIO 4 - Modifica los registros de la tabla CURSOS para asignar a cada curso un profesor o 
profesora. Utiliza para ello la herramienta gráfica, entregando con la tarea una captura de pantalla 
de la pestaña Datos de esa tabla, donde se aprecien todos los cambios que has realizado. El 
profesorado que debes asignar a cada curso es: */

UPDATE cursos SET cod_profe = '4' WHERE CODIGO = '1';
UPDATE cursos SET cod_profe = '2' WHERE CODIGO = '2';
UPDATE cursos SET cod_profe = '2' WHERE CODIGO = '3';
UPDATE cursos SET cod_profe = '1' WHERE CODIGO = '4';
UPDATE cursos SET cod_profe = '1' WHERE CODIGO = '5';
UPDATE cursos SET cod_profe = '3' WHERE CODIGO = '6';

/* EJERCICIO 5 - Modifica el registro del profesor cuyo Codigo es 3  usando sentencias SQL, y cambia 
su especialidad a Informática  y su DNI a 9876543C. En la entrega de la tarea debes copiar la 
sentencia que has utilizado.
*/

UPDATE PROFESORADO SET especialidad = 'INFORMATICA' WHERE CODIGO = '3';
UPDATE PROFESORADO SET DNI = '9876543C' WHERE CODIGO = '3';

/* EJERCICIO 6 - Modifica las antigüedades de todos los profesores y profesoras, decrementándolas en 2 
en todos los registros en los que la antigüedad sea superior a 2. Debes hacerlo usando una sola 
sentencia SQL, que debes copiar para la entrega de la tarea.*/

UPDATE profesorado SET antiguedad = antiguedad-2 
WHERE antiguedad >2

/* EJERCICIO 7 - Elimina, de la tabla CURSOS, el registro del curso que tiene el código 6. 
Debes realizar esta acción desde la herramienta gráfica. 
Debes entregar una captura de pantalla de la ventana en la que vas a borrar el registro, 
justo antes de pulsar el botón Aceptar para confirmar el borrado.*/

DELETE cursos WHERE CODIGO = '6';

/* CONSULTA 8 - Elimina, de la tabla ALUMNADO, aquellos registros asociados al curso con 
código 2 cuyo sexo sea H. Debes hacerlo usando un sola sentencia SQL que debes copiar para 
la entrega de la tarea.*/
DELETE FROM ALUMNADO WHERE COD_CURSO = '2' AND SEXO='H';

/* CONSULTA 9 - Inserta los registros de la tabla ALUMNADO_NUEVO en la tabla ALUMNADO, 
asociándole el código de curso 1, es decir, cod_curso con el valor 1. Debes hacerlo usando 
una sola sentencia SQL, que debes copiar para la entrega de la tarea.
*/

INSERT INTO ALUMNADO (Nombre,Apellidos,Sexo,Fecha_Nac,Cod_Curso)
SELECT Nombre,Apellidos,Sexo,Fecha_Nac,1
FROM ALUMNADO_NUEVO;

/* CONSULTA 10 - En la tabla CURSOS, actualiza el campo Max_Alumn del registro del curso con 
código 1, asignándole el valor correspondiente al número total de alumnos y alumnas que hay 
en la tabla ALUMNADO y que tienen asignado ese mismo curso.*/

UPDATE MAX_ALUMN WHERE Codigo = '1' FROM CURSOS
SET SELECT SUM WHERE COD_CURSO='1'
FROM ALUMNADO;

-- EL BUENO ES : 

UPDATE CURSOS
SET Max_Alumn = (
    SELECT COUNT(*)
    FROM ALUMNADO
    WHERE COD_CURSO = 1
)
WHERE Codigo = 1;

/* CONSULTA 11 - Elimina de la tabla ALUMNADO todos los registros asociados a los cursos que 
imparte el profesor cuyo código es 3. */

DELETE row from ALUMNADO WHERE COD_CURSO='3';

-- CORRECTA ES: 
DELETE FROM ALUMNADO
WHERE COD_CURSO IN (
    SELECT CODIGO
    FROM CURSOS
    WHERE COD_PROFE = 3
);

