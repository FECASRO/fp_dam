#  Acceso a Datos – Ejercicio 7

 Enunciado.
 Los chicos de BK están aprendiendo a hacer componentes de acceso a datos. Están practicando con 
los datos de la matrícula de los alumnos de la base de datos con la que has estado trabajando 
durante esta unidad y necesitan que les eches una mano, en concreto te piden que hagas lo 
siguiente:
 • Debes añadir una tabla a la base de datos alumnos que represente las matrículas de los 
alumnos. Consta de los siguientes campos:
 • DNI: varchar(9).
 • NombreMódulo: varchar(60).
 • Curso: varchar(5), el curso se forma con los dos años que lo componen separados 
por un guión, por ejemplo 11-12.
 • Nota: double.
 Recuerda rellenar la tabla con algunos datos para que puedas hacer pruebas.
 • Crea un componente nuevo en el proyecto Alumno que para gestionar toda esta 
información. Además del código necesario para gestionar las propiedades del componente y 
mantener la información de la base de datos en un vector interno, es preciso que incluyas 
los siguientes métodos:
 • seleccionarFila(i): recupera en las propiedades del componente el registro número i 
del vector.
 • RecargarDNI(): recarga la estructura interna del componente con las matrículas de 
un DNI en particular.
 • AddMatricula(): añade un registro nuevo a la base de datos con la información 
almacenada en las propiedades del componente.
 • Dado que el componente puede funcionar en dos modos diferentes (todos los 
alumnos o un alumno concreto) se generará un evento cada vez que se cambie de 
modo, es decir, cuando se carguen todas las matrículas se lanzará un evento que lo 
señale y cuando se carguen las matrículas para un solo alumno también.
 • Tendrás que crear un proyecto de prueba del componente en el que hagas un listado de 
todas las matrículas que hay en el sistema, y luego hagas un listado de las matrículas de un 
alumno concreto.
 • Cuando cargues la matricula del usuario concreto deberás capturar el evento generado al 
cambiar de modo.
 • Añade el código necesario para añadir una matrícula nueva a la base de datos

 