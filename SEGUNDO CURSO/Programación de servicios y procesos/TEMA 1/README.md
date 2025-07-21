# Programación de Servicios y Procesos - Ejercicio 2

## Ejercicio 1
 Se compone de 3 partes:
 •Primera parte: implementa una aplicación que ordena un conjunto indeterminado de 
números que recibe a través de su entrada estándar; y muestra el resultado de la ordenación 
en su salida estándar. La aplicación se llamará 'ordenarNumeros'.
 •Segunda parte: implementa una aplicación, llamada 'aleatorios', que genere al menos 40 
números aleatorios (entre 0 y 100), y que los escriba en su salida estándar.
 •Tercera parte: Realiza un pequeño manual (tipo "¿Cómo se hace?" o "HowTo"), utilizando 
un editor de textos (tipo word o writer) en el que indiques, con pequeñas explicaciones y 
capturas, cómo has probado la ejecución de las aplicaciones que has implementado en este 
ejercicio. Entre las pruebas que hayas realizado, debes incluir una prueba en la que 
utilizando el operador "|" (tubería) redirijas la salida de la aplicación 'aleatorios' a la entrada 
de la aplicación 'ordenarNumeros'.

##  Ejercicio 2

Se compone de 3 partes:
 •Primera parte: implementa una aplicación que escriba en un fichero indicado por el usuario 
conjuntos de letras generadas de forma aleatoria (sin sentido real). Escribiendo cada 
conjunto de letras en una línea distinta. El número de conjuntos de letras a generar por el 
proceso, también será dado por el usuario en el momento de su ejecución. Esta aplicación se 
llamará "lenguaje" y como ejemplo, podrá ser invocada así:
 java -jar lenguaje 40 miFicheroDeLenguaje.txt
 Indicando que se generarán 40 palabras del lenguaje y serán guardadas 
en miFicheroDeLenguaje.txt
 •Segunda parte: implementa una aplicación, llamada 'colaborar', que lance al menos 10 
instancias de la aplicación "lenguaje". Haciendo que todas ellas, colaboren en generar un 
gran fichero de palabras. Cada instancia generará un número creciente de palabras de 10, 20, 
30, … Por supuesto, cada proceso seguirá escribiendo su palabra en una línea independiente 
de las otras. Es decir, si lanzamos 10 instancias de "lenguaje", al final, debemos tener en el 
fichero 10 + 20 + 30 + … + 100 = 550 líneas.
 •Tercera parte: Realiza un pequeño manual (tipo "¿Cómo se hace?" o "HowTo"), utilizando 
un editor de textos (tipo word o writer) en el que indiques, con pequeñas explicaciones y 
capturas, cómo has probado la ejecución de las aplicaciones que has implementado en este 
ejercicio