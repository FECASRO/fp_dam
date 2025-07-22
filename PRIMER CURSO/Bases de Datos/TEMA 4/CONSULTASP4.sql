-- CONSULTA 1 - Obtener los nombres y salarios de los empleados con más de 1000 euros de salario por orden alfabético.

SELECT NOMBRE, SALARIO FROM EMPLEADO
WHERE SALARIO > 1000 
ORDER BY NOMBRE ASC; 

-- CONSULTA 2 - Obtener el nombre de los empleados cuya comisión es superior al 20% de su salario.

SELECT NOMBRE FROM EMPLEADO
WHERE COMISION > SALARIO * 0.2 AND IS NOT NULL; 

-- ESTA CONSULTA ES CORRECTA PERO NINGUN EMPLEADO CUMPLE LA CONDICIÓN POR LO QUE NO SELECCIONA NINGUNA FILA.

/* CONSULTA 3 - Obtener el código de empleado, código de departamento, nombre y sueldo total en pesetas, 
de aquellos empleados cuyo sueldo total (salario más comisión) supera los 1800 euros. 
Presentarlos ordenados por código de departamento y, dentro de éstos, por orden alfabético.*/

SELECT CODEMPLE, CODDPTO, NOMBRE, 
(SALARIO + NVL (COMISION,0)) * 187 AS SUELDO_PESETAS FROM EMPLEADO
WHERE (SALARIO + NVL(COMISION,0)) > 1800 
ORDER BY CODDPTO,NOMBRE ASC ; 

/* CONSULTA 4 - Obtener, por orden alfabético, los nombres de empleados cuyo salario igualen o superen en más de un 5% 
al salario de la empleada ‘MARIA JAZMIN’.*/

SELECT NOMBRE 
FROM EMPLEADO 
WHERE SALARIO >= (
    SELECT SALARIO * 1.05
    FROM EMPLEADO
    WHERE NOMBRE || ' ' || APE1 = 'MARIA JAZMIN'
)
ORDER BY NOMBRE ASC;

-- SEGUNDA FORMA DE HACERLO DE LA PRIMERA EN LAS COMILLAS DEL WHERE FUNDAMENTAL EL ESPACIO ' ' 
SELECT NOMBRE 
FROM EMPLEADO 
WHERE SALARIO >= (
    SELECT SALARIO * 1.05
    FROM EMPLEADO
    WHERE CONCAT(CONCAT(NOMBRE, ' '), APE1) = 'MARIA JAZMIN'
)
ORDER BY NOMBRE ASC;


-- CONSULTA 5 Obtener una listado ordenado por años en la empresa con los nombres, y apellidos de los empleados, 
-- y los años de antigüedad en la empresa.

SELECT NOMBRE, APE1, APE2, 
TRUNC(MONTHS_BETWEEN(SYSDATE,FECHAINGRESO)/12) AS ANIOS 
FROM EMPLEADO
ORDER BY ANIOS ASC
/*SYSDATE: devuelve la fecha actual del sistema.

FECHAINGRESO: suponemos que es un campo DATE con la fecha de alta del empleado.

MONTHS_BETWEEN(SYSDATE, FECHAINGRESO): devuelve el número de meses entre dos fechas.

Dividir entre 12 da los años de antigüedad.

TRUNC(...): elimina decimales (años completos solamente).

AS ANIOS: da nombre a la columna calculada.*/

-- CONSULTA 6 - Obtener el nombre de los empleados que trabajan en un departamento con presupuesto superior a 50.000 euros. 
-- Hay que usar predicado cuantificado.

SELECT NOMBRE FROM EMPLEADO
WHERE coddpto IN (
    SELECT CODDPTO
    FROM DPTO
    WHERE PRESUPUESTO > 50000
)

-- CONSULTA 7 - Obtener los nombres y apellidos de empleados que más cobran en la empresa. Considerar el salario más la comisión.
SELECT NOMBRE, APE1, APE2
FROM EMPLEADO
WHERE (SALARIO + NVL(COMISION, 0)) >
      (SELECT AVG(SALARIO + NVL(COMISION, 0)) FROM EMPLEADO);

-- CONSULTA 8 - Obtener en orden alfabético los nombres de empleado cuyo salario es inferior al mínimo de los empleados del departamento 1.

SELECT NOMBRE, APE1, APE2 FROM EMPLEADO 
WHERE SALARIO < (SELECT MIN(SALARIO) 
FROM EMPLEADO WHERE CODDPTO = '1')
ORDER BY NOMBRE ASC;

-- Obtener los nombres de empleados que trabajan en el departamento del cuál es jefe el empleado con código 1.

SELECT NOMBRE FROM EMPLEADO WHERE CODCATE = '1'; 

-- 10 - Obtener los nombres de los empleados cuyo primer apellido empiece por las letras p, q, r, s.
SELECT NOMBRE FROM EMPLEADO 
WHERE APE1 LIKE 'P%' OR 
      APE1 LIKE 'Q%' OR
      APE1 LIKE 'R%' OR
      APE1 LIKE 'S%' ;

-- 11 - Obtener los empleados cuyo nombre de pila contenga el nombre JUAN.

SELECT NOMBRE FROM EMPLEADO 
WHERE NOMBRE LIKE '%JUAN%';

/*IMPORTANTE ACLARACION , CADA VEZ QUE BUSCAMOS QUE CONTENGA UNA PALABRA, LETRA ETC , USAMOS LIKE 
Y EL % ES EL COMODIN QUE REPRESENTA LA CADENA DE TEXTO QUE ANTECEDE O SIGUE AL TERMINO QUE BUSCAMOS */

-- 12 - Obtener los nombres de los empleados que viven en ciudades en las que hay algún centro de trabajo.

SELECT NOMBRE FROM EMPLEADO 
WHERE LOCALIDAD IN (
    SELECT LOCALIDAD 
    FROM CENTRO 
);   

-- NO DABA RESULTADO PQ LAS LOCALIDADES POR EJEMPLO ERAN TARRAGONA EN UNA TABLA Y Tarragona EN LA OTRA, ASI QUE ....

SELECT NOMBRE 
FROM EMPLEADO 
WHERE UPPER(TRIM(LOCALIDAD)) IN (
    SELECT UPPER(TRIM(LOCALIDAD)) 
    FROM CENTRO
);  
-- UPPER PASA TODO A MAYUSCULAS , TRIM QUITA ESPACIOS DELANTE Y DETRAS , LOWER TODO MINUSCULAS , REPLACE QUITA TODOS LOS ESPACIOS 

-- CONSULTA 13 - Obtener el nombre del jefe de departamento que tiene mayor salario de entre los jefes de departamento.

SELECT NOMBRE FROM EMPLEADO 
WHERE CODCATE='1'
AND SALARIO = (
    SELECT MAX(SALARIO)
    FROM EMPLEADO
    WHERE CODCATE='1'
);

-- CONSULTA 14 - Obtener en orden alfabético los salarios y nombres de los empleados cuyo salario sea superior 
-- al 60% del máximo salario de la empresa.         PRIMERA CORRECTA 

SELECT SALARIO , NOMBRE FROM EMPLEADO
WHERE SALARIO >(SELECT MAX(SALARIO * 0.6 )
FROM EMPLEADO) 
ORDER BY NOMBRE ASC;

-- CONSULTA 15 - Obtener en cuántas ciudades distintas viven los empleados.

SELECT COUNT(DISTINCT LOCALIDAD) 
AS NUMERO_LOCALIDADES FROM EMPLEADO; 

-- CONSULTA 16 - El nombre y apellidos del empleado que más salario cobra.

SELECT NOMBRE,APE1,APE2 WHERE MAX(SALARIO) FROM EMPLEADO ; 
 
-- CONSULTA 17 - Obtener las localidades y número de empleados de aquellas en las que viven más de 3 empleados.
SELECT LOCALIDAD, COUNT(*) AS NUM_EMPLEADOS
FROM EMPLEADO
GROUP BY LOCALIDAD
HAVING COUNT(*) > 3;
/*Explicación:
GROUP BY LOCALIDAD: agrupa empleados por ciudad.
COUNT(*): cuenta cuántos empleados hay en cada ciudad.
HAVING COUNT(*) > 3: filtra para mostrar solo las ciudades con más de 3 empleados.*/

/* CONSULTA 18 - Obtener, para cada departamento, cuántos empleados trabajan, la suma de sus salarios 
y la suma de sus comisiones, para aquellos departamentos en los que hay algún empleado cuyo salario 
es superior a 1700 euros. */

SELECT CODDPTO, 
       COUNT(*) AS NUM_EMPLEADOS,
       SUM(SALARIO) AS SUMA_SALARIOS,
       SUM(NVL(COMISION, 0)) AS SUMA_COMISIONES
FROM EMPLEADO
WHERE CODDPTO IN (
    SELECT DISTINCT CODDPTO
    FROM EMPLEADO
    WHERE SALARIO > 1700
)
GROUP BY CODDPTO;

/* Explicación:
COUNT(*): cuenta empleados por departamento.

SUM(SALARIO): suma los salarios por departamento.

SUM(NVL(COMISION, 0)): suma las comisiones (si alguna es NULL, la considera 0).

WHERE CODDPTO IN (...): filtra solo los departamentos donde al menos un empleado tiene salario > 1700.

GROUP BY CODDPTO: agrupamos los datos por departamento.*/

-- CONSULTA 19 - Obtener el departamento que más empleados tiene.

SELECT CODDPTO
FROM EMPLEADO
GROUP BY CODDPTO
HAVING COUNT(*) = (
    SELECT MAX(COUNT(*))
    FROM EMPLEADO
    GROUP BY CODDPTO
);

/*  Explicación:
GROUP BY CODDPTO: agrupa los empleados por departamento.

COUNT(*): cuenta cuántos empleados hay en cada departamento.

La subconsulta SELECT MAX(COUNT(*)) ... saca el máximo de empleados por departamento.

HAVING se usa en lugar de WHERE porque estamos trabajando con agregados (COUNT).
En SQL, la cláusula WHERE filtra filas antes de que se hagan agrupaciones (GROUP BY), 
mientras que HAVING filtra los grupos después de que se ha hecho la agregación (como COUNT, SUM, AVG, etc.).

*/

/* CONSULTA 20 - Obtener los nombres de todos los centros y los departamentos que se ubican en cada uno, así como 
aquellos centros que no tienen departamentos.*/

SELECT C.DIRECCION AS NOMBRE_CENTRO, D.DENOMINACION AS NOMBRE_DEPTO
FROM CENTRO C
LEFT JOIN DPTO D ON C.CODCENTRO = D.CODCENTRO;
/* La consulta que planteas tiene varios problemas de sintaxis y de enfoque. Para obtener:
"los nombres de todos los centros y los departamentos que se ubican en cada uno, así como 
aquellos centros que no tienen departamentos"
…lo más adecuado es usar un LEFT JOIN entre CENTRO y DPTO, uniendo por el código de centro. 
El LEFT JOIN permite que aparezcan también los centros aunque no tengan departamentos asociados, 
que es lo que pide el enunciado.
Explicación paso a paso:
La tabla CENTRO tiene información sobre los centros (ej. nombre o dirección).
La tabla DPTO tiene los departamentos, con un campo que indica a qué centro pertenecen.
Queremos mostrar el nombre del centro y el nombre (o código) del departamento, aunque no haya ningún 
departamento en ese centro.*/

-- CONSULTA 21 - Obtener el nombre del departamento de más alto nivel, es decir, aquel que no depende de ningún otro.

SELECT DENOMINACION FROM DPTO
WHERE CODDPTODEPENDE IS NULL;

-- CONSULTA 22 - Obtener todos los departamentos existentes en la empresa y los empleados (si los tiene) que pertenecen a él.

SELECT D.DENOMINACION AS NOMBRE_DPTO , E.NOMBRE AS NOMBRE_EMPLEADO 
FROM DPTO D 
RIGHT JOIN DPTO D ON D.CODDPTO = E.CODDPTO ; 

/* SELECT D.DENOMINACION AS NOMBRE_DPTO , E.NOMBRE AS NOMBRE_EMPLEADO 
FROM DPTO D 
RIGHT JOIN DPTO D ON D.CODDPTO = E.CODDPTO ; 
En tu consulta hay un error de referencia y de alias: estás haciendo un RIGHT JOIN de DPTO con DPTO de nuevo, cuando deberías unir DPTO con EMPLEADO. También falta incluir la tabla EMPLEADO en la cláusula FROM.
Aquí tienes la versión corregida y funcional:
SELECT D.DENOMINACION AS NOMBRE_DPTO, E.NOMBRE AS NOMBRE_EMPLEADO
FROM DPTO D
LEFT JOIN EMPLEADO E ON D.CODDPTO = E.CODDPTO;
Explicación:
Se usa LEFT JOIN (o RIGHT JOIN invertido) para obtener todos los departamentos, aunque no tengan empleados.
La tabla DPTO es la principal (izquierda), y EMPLEADO se une por CODDPTO.*/

/* CONSULTA 23 - Obtener un listado en el que aparezcan todos los departamentos existentes 
y el departamento del cual depende, si depende de alguno.Obtener un listado en el que aparezcan 
todos los departamentos existentes y el departamento del cual depende, si depende de alguno.*/

SELECT DENOMINACION AS NOMBRE_DPTO 
FROM DPTO 
GROUP BY CODDPTO;

-- CORRECTO : 
SELECT 
  D1.DENOMINACION AS NOMBRE_DPTO,
  D2.DENOMINACION AS DPTO_DEPENDE
FROM DPTO D1
LEFT JOIN DPTO D2 ON D1.CODDPTODEPENDE = D2.CODDPTO;

/* Explicación:
D1 es cada departamento.
D2 es el departamento del que depende D1.
Se usa LEFT JOIN para que también salgan los que no dependen de ninguno 
(es decir, DPTO_DEPENDE quedará como NULL en esos casos*/

/* CONSULTA 24 - Obtener un listado, ordenado alfabéticamente, donde aparezcan los nombres de los 
empleados y a continuación el literal "tiene comisión" si la tiene, y "no tiene comisión" si no la tiene.*/

SELECT NOMBRE FROM EMPLEADO ORDER BY NOMBRE ASC;

-- CORRECTO : 
SELECT 
  NOMBRE || ' ' || 
  CASE 
    WHEN COMISION IS NOT NULL THEN 'tiene comisión'
    ELSE 'no tiene comisión'
  END AS INFO_COMISION
FROM EMPLEADO
ORDER BY NOMBRE;
/* Esta es la estructura CASE:
WHEN COMISION IS NOT NULL: Evalúa si la columna COMISION tiene un valor (es decir, el empleado sí tiene comisión).
THEN 'tiene comisión': Si la condición se cumple, devuelve ese texto.
ELSE 'no tiene comisión': Si no se cumple (es decir, COMISION es NULL), devuelve otro texto.
END: Cierra la estructura CASE*/

-- CONSULTA 25 - Obtener un listado de las localidades en las que hay centros y no vive ningún empleado, ordenado alfabéticamente.

SELECT DISTINCT C.LOCALIDAD
FROM CENTRO C
LEFT JOIN EMPLEADO E ON C.LOCALIDAD = E.LOCALIDAD
WHERE E.LOCALIDAD IS NULL
ORDER BY C.LOCALIDAD ASC;

/*SELECT DISTINCT C.LOCALIDAD: recupera las ciudades únicas donde hay centros.
NOT IN (...): filtra aquellas localidades que no están en el listado de localidades donde viven empleados.
ORDER BY: para ordenarlas alfabéticamente.
Si usas Oracle y quieres evitar problemas con NULL, puedes hacer una versión con LEFT JOIN ... WHERE ... IS NULL, que es más robusta:*/

-- CONSULTA 26 - Obtener un listado de las localidades en las que hay centros y además vive al menos un empleado, ordenado alfabéticamente.
SELECT DISTINCT C.LOCALIDAD
FROM CENTRO C
LEFT JOIN EMPLEADO E ON C.LOCALIDAD = E.LOCALIDAD
WHERE E.LOCALIDAD IS NOT NULL
ORDER BY C.LOCALIDAD ASC;


/* CONSULTA 27 - Esta cuestión puntúa  doble. Se desea dar una gratificación por navidades en función de la antigüedad en la empresa 
siguiendo estas pautas:

Si lleva entre 1 y 5 años, se le dará 100 euros
Si lleva entre 6 y 10 años, se le dará 50 euros por año
Si lleva entre 11 y 20 años, se le dará 70 euros por año
Si lleva más de 21 años, se le dará 100 euros por año*/


SELECT 
  NOMBRE,
  APE1,
  APE2,
  TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) AS ANIOS_ANTIGUEDAD,
  CASE 
    WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) BETWEEN 1 AND 5 THEN 100
    WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) BETWEEN 6 AND 10 THEN TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) * 50
    WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) BETWEEN 11 AND 20 THEN TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) * 70
    WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) > 21 THEN TRUNC(MONTHS_BETWEEN(SYSDATE, FECHAINGRESO) / 12) * 100
    ELSE 0
  END AS GRATIFICACION_EUROS
FROM EMPLEADO
ORDER BY NOMBRE;

/* EXPLICACION: Se elimina el alias ANIOS intermedio porque no puede usarse dentro del mismo SELECT en el CASE. Se recalcula directamente.
Se usa BETWEEN para definir los rangos correctamente.
Se multiplica por el número de años donde corresponde, como pedía el enunciado.
Se añade un ELSE 0 por claridad (por ejemplo, si alguien lleva menos de 1 año). */

-- CONSULTA 28 - Obtener un listado de los empleados, ordenado alfabéticamente, indicando cuánto le corresponde de gratificación.

-- HECHO EN EL ANTERIOR

-- CONSULTA 29 - Obtener  los nombres y apellidos de los empleados que no son jefes de departamento.

SELECT NOMBRE,APE1,APE2 FROM EMPLEADO
WHERE CODEMPLE NOT IN (SELECT CODEMPLEJEFE FROM DPTO) ;


