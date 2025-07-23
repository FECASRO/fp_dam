(:El número de artistas nacidos antes de 1600:)
for $x in doc("artistas.xml")/artistas/artista
let $y:= $x /nacimiento<=1600
return count ($y)
(:En esta consulta intente que en vez de contar los sucesos consultados me diera
el numero de repeticiones con return fn:sum ($y) pero me daba un problema de tipo
boolean:)