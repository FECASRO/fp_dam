# Programación de Servicios y Procesos - Ejercicio 2

## Ejercicio 1

 De igual manera a lo visto en el tema, ahora te proponemos un ejercicio del tipo productor
consumidor que mediante un hilo productor almacene datos (15 caracteres) en un búfer 
compartido, de donde los debe recoger un hilo consumidor (consume 15 caracteres). La capacidad 
del búfer ahora es de 6 caracteres, de manera que el consumidor podrá estar cogiendo caracteres del 
búfer siempre que éste no esté vacío. El productor sólo podrá poner caracteres en el búfer, cuando 
esté vacío o haya espacio.
 Te mostramos una posible salida del programa que debes realiza

##  Ejercicio 2

 De igual manera a lo visto en el tema, ahora te proponemos que resuelvas el clásico problema 
denominado "La cena de los filósofos" utilizando la clase Semaphore del 
paquete java.util.concurrent.
 El problema es el siguiente: Cinco filósofos se sientan alrededor de una mesa y pasan su vida 
comiendo y pensando. Cada filósofo tiene un plato de arroz chino y un palillo a la izquierda de su 
plato. Cuando un filósofo quiere comer arroz, cogerá los dos palillos de cada lado del plato y 
comerá. El problema es el siguiente: establecer un ritual (algoritmo) que permita comer a los 
filósofos. El algoritmo debe satisfacer la exclusión mutua (dos filósofos no pueden emplear el 
mismo palillo a la vez), además de evitar el interbloqueo y la inanición.