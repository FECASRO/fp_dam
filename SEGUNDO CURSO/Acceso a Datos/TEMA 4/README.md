#  Acceso a Datos – Ejercicio 4

Enunciado.
 Una empresa dispone de una base de datos contiene las tablas con la información necesaria para su 
gestión. Las tablas son las siguientes:
 • Tabla DEPT que contiene información de los diferentes departamentos que tiene la 
empresa. La clave principal DEPTNO.
 • Tabla EMP que contiene la información de los diferentes empleados que tiene la empresa. 
Tiene como clave principal EMPNO y ajenas DEPTNO que relaciona con la tabla 
departamentos y MGR que establece la relación con la misma tabla mostrando ser jefe de.
 La creación de las tablas y sus relaciones se dan en el fichero tablas.sql que se adjunta.
 La base de datos que se utilizará será Oracle.
 Mapea las tablas utilizando Hibernate con NetBeans y realiza un proyecto Java llamado 
HibernateOracle que obtenga lo siguiente:
 1. Crea la base de datos.
 2. Configura y crea la ORM Hibernate.
 3. Realiza una inserción y un borrado sobre la tabla EMP.
 4. Obtener un listado sobre las tablas EMP y DEPT que visualice empno, ename, sal, dname y 
loc.
 5. Redactar un documento donde se explique el proceso seguido para la realización de la 
práctica. 

## Dificultades del ejercicio 

finalmente tuve que realizarlo con eclipse que tiene mas compatibilidad con pluggins que en este caso para Netbeans no estaban bien actualizados, dando problemas e incompatibilidades de todo tipo.