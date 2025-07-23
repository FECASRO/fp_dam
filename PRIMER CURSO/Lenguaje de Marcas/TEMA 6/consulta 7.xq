(:Marca y modelo de las impresoras con más de un tamaño :)
for $x in doc("impresoras.xml")/impresoras/impresora
where count ($x/tamaño)>1
return <impresora>{$x/marca,$x/modelo}</impresora>

(:primer uso de la funcion count con una variable
antes la use para $y para contar numero de repeticiones :)