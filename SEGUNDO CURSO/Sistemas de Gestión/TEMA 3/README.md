# Sistemas de Gestión Empresarial - Ejercicio 3

En esta unidad hemos aprendido cómo están organizados los datos en una aplicación ERP, y cómo 
poder consultarlos y hacer algún tipo de tratamiento con ellos. Hemos aprendido que podemos 
conectarnos directamente con la base de datos para consultar o modificar la información de tablas, 
vistas y otros objetos, además de poder hacer todo esto a través de la aplicación. Nos hemos 
adentrado en la funcionalidad de un ERP para crear consultas de acceso a datos, formularios e 
informes, y exportar esa información a archivos. Finalmente, como aspecto importante de nuestro 
servidor, hemos aprendido que existen herramientas para controlar el rendimiento y la actividad del 
mismo.
 Ahora se trata de que pongamos en práctica estos conocimientos, ayudándonos de los contenidos 
de la unidad así como de los distintos enlaces y recursos facilitados. Es importante que sigas las 
indicaciones de cada ejercicio a la hora de nombrar los objetos, teniendo en cuenta que las 
letras AA y BB corresponden a las dos primeras letras de tu primer y segundo apellido, 
respectivamente.
 Práctica a realizar con Odoo:
 ## 1. Instala PgAdmin y conéctate con la base de datos. 
Después crea una consulta que muestre 
los siguientes datos:
 • Código o id de cada tabla en la que se base la consulta.
 • Nombre:nombre de la empresa.
 • Título: Tipo de empresa (SL, SA, etc.).
 • Idioma.
 • Crédito concedido.
 • Calle.
 • Código postal.
 • Ciudad.
 • Teléfono de la empresa.
 ## 2. Crea una vista de tipo formulario del objeto Empresa (res.partner) que incluya los campos 
que a continuación se relacionan. Indica y explica el código XML necesario para crear la 
vista.
 • Nombre de la empresa.
 • Título.
 • Idioma.
 • Crédito concedido.
 • Si es cliente o proveedor.
 • Contactos de la empresa. Definir dos vistas sobre este campo (form y tree):
 • La vista form tendrá los siguientes campos: Nombre del contacto, Función o 
cargo, Calle, Calle2, Código Postal, Ciudad, Provincia, País, Teléfono, Fax, 
Móvil, email.
 • La vista tree tendrá los siguientes campos: Nombre, Código postal, Ciudad, 
País, Teléfono, Email.
 Nombre de la vista: formAABB.
 ## 3. Haz que la vista formulario generada en el apartado anterior sea accesible a través de un 
menú secundario llamado VerEmpresas, situado dentro de un menú llamado MenuAABB.
 ## 4. Introduce los siguientes datos:
 • 4 ciudades o provincias, al menos una de un país distinto al resto.
 • 1 título nuevo además de los que se cargan con la aplicación.
 • 3 empresas de España y 1 de Portugal, desde la vista creada en el segundo ejercicio. 
Rellenar con datos ficticios todos los campos de la empresa y al menos cinco de los 
campos de los contactos.
 ## 5. Genera un archivo CSV con los siguientes datos procedentes de la aplicación:
 • Nombre de la empresa.
 • Título.
 • Idioma.
 • Crédito concedido.
 • Si es cliente.
 • Nombre del contacto.
 • Cargo o función del contacto
 • Calle.
 • Código Postal.
 • Ciudad.
 • Provincia.
 • Teléfono.
 • Email.