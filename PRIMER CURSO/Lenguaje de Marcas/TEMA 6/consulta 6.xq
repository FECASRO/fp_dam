(:Modelo de las impresoras de tipo “láser”:)
for $x in doc("impresoras.xml")/impresoras/impresora
where $x[@tipo="láser"]
return <impresora>{$x/modelo}</impresora>
(:Me esta gustando mucho esta practica, creo que entiendo mas o menos como funciona:)