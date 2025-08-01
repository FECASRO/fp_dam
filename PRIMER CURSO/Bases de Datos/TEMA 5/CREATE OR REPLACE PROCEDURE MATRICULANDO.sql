-- PROCEDIMIENTO PARA MATRICULAR ALUMNOS

CREATE OR REPLACE PROCEDURE MATRICULANDO(
P_NOMBRE VARCHAR2,
P_APELLIDOS VARCHAR2,
P_SEXO VARCHAR2,
P_FECHA_NAC DATE,
P_COD_CURSO NUMBER
)IS
BEGIN
INSERT INTO ALUMNADO (NOMBRE,APELLIDOS,SEXO,FECHA_NAC,COD_CURSO)
VALUES(P_NOMBRE,P_APELLIDOS,P_SEXO,P_FECHA_NAC,P_COD_CURSO);
END;


-- LLAMADA AL CODIGO DEL PROCEDIMIENTO MEJOR ASI QUE CON EXECUTE

BEGIN
MATRICULANDO('LUCIA','MARTÍN','M',TO_DATE('08/04/2012','DD/MM/YYYY'),3);
END;

-- PROCEDIMIENTO PARA BORRAR ALUMNO ASOCIADO A UN TRIGGER QUE GUARDA ALUMNO BORRADO EN TABLA CREADA ALUMNOS_BORRADOS

create or replace PROCEDURE BORRAALUMNOS(
P_CODIGO NUMBER 
)IS
BEGIN
DELETE FROM ALUMNADO WHERE CODIGO=P_CODIGO;
END;

-- LLAMADA AL PROCEDIMIENTO BORRAALUMNOS

BEGIN 
BORRAALUMNOS(223);
END;

--TRIGGER QUE SALTA AL BORRAR ALUMNOS INTRODUCE LO BORRADO EN LA TABLA alumnos_borrados

create or replace TRIGGER SEGURO_BD BEFORE DELETE ON ALUMNADO 
FOR EACH ROW 
BEGIN 
INSERT INTO alumnos_borrados(NOMBRE,APELLIDOS,SEXO,FECHA_NAC,COD_CURSO,USUARIO,F_MODIF)
VALUES(:OLD.NOMBRE,:OLD.APELLIDOS,:OLD.SEXO,:OLD.FECHA_NAC,:OLD.COD_CURSO,USER,SYSDATE);
END;

-- PROCEDIMIENTO RESTAURAR ALUMNO BORRADO DESDE LA TABLA QUE CREE PARA ALUMNOS BORRADOS

create or replace PROCEDURE RESTAURALUMNO(
P_NOMBRE VARCHAR2,
P_FECHANACIMIENTO DATE
)
IS
BEGIN
INSERT INTO alumnado (NOMBRE,APELLIDOS,SEXO,FECHA_NAC,COD_CURSO)
SELECT NOMBRE,APELLIDOS,SEXO,FECHA_NAC,COD_CURSO 
FROM ALUMNOS_BORRADOS
WHERE NOMBRE=P_NOMBRE AND FECHA_NAC=P_FECHANACIMIENTO;
END;

-- LLAMAR PROCEDIMIENTO RESTAURAR ALUMNO BORRADO DESDE LA TABLA QUE CREE PARA ALUMNOS BORRADOS
BEGIN
restauralumno('LUCIA','08/04/12');
END;

-- LA FUNCION ES IDEAL PARA CREAR CONSULTAS MAS ELABORADAS DE FORMA SENCILLA 

create or replace FUNCTION CALCULA_EDAD (
P_CODIGO_ALUMNO NUMBER
)RETURN NUMBER
IS 
V_EDAD NUMBER;
BEGIN 
SELECT TRUNC(MONTHS_BETWEEN (SYSDATE, FECHA_NAC)/12)
INTO V_EDAD
FROM ALUMNADO
WHERE CODIGO=P_CODIGO_ALUMNO;
RETURN V_EDAD ;
END;

-- CONSULTA  

SELECT NOMBRE,APELLIDOS,CALCULA_EDAD(CODIGO) AS EDAD
FROM ALUMNADO;

/*RESULTADO:
NOMBRE                         APELLIDOS                            EDAD
------------------------------ ------------------------------ ----------
MANUELA                        SUAREZ IBAÑEZ                          34
MILAGROSA                      DIAZ PEREZ                             40
JOSE                           CRESPO DE HERMOSO                      32
ANTONIO JESUS                  MARTIN BOLLO                           25
BARBARA                        PELAEZ VALENCIA                        57
JUAN PEDRO                     GALVE GONZALEZ                         33
MARIA ISABEL                   PEREZ GUILLEN                          62
PATRICIA                       ROMAN GOMEZ                            46
DAVID                          TAPIA SOLANS                           45
MARIA                          FERREIRO SANTOS                        56
ALEJANDRA                      CALDERON VALDIVIA                      64
*/


