(:Marca y modelo de las impresoras con tamaño A3 como único tamaño:)
for $x in doc("impresoras.xml")/impresoras/impresora
where($x/tamaño="A3")
return <impresora>{$x/marca,$x/modelo}</impresora>

(:Este ejercicio lo realize antes que el anterior al ser casi lo mismo 
pero con mas sencillez, fue la base:)