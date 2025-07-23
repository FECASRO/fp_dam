(:Modelo de las impresoras en red:)
for $x in doc("impresoras.xml")/impresoras/impresora//enred
return <impresora>{$x/modelo}</impresora>

(:Seguro que es sencillisimo, sin duda al ejercicio que mas tiempo le dedique
no he conseguido sacarlo pese a navegar y leer mil cosas he intentado colar 
enred como atributo , he intentado con contains y algo se me ha escapado....
mil gracias por todo :)