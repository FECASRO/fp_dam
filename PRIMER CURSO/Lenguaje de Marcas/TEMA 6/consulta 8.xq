(:Marca y modelo de las impresoras con tamaño A3 (pueden tener otros:)
for $x in doc("impresoras.xml")/impresoras/impresora
where($x/tamaño="A3" and $x/tamaño !="A3")
return <impresora>{$x/impresora/marca, $x/modelo}</impresora>

(:Aunque parezca una evidencia hasta que encontre la manera de reflejarlo
me costo bastante porque no sabia la estructura comparativa con and y or:)