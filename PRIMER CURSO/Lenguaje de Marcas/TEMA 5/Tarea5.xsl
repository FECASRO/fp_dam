<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
 <xsl:strip-space elements="*" />  
  
  <!-- Plantilla raíz -->
 
  <xsl:template match="/"> 
    
<html>
  
   <head>
     <title>Artistas</title>     
   </head>
  <body>
    <table border="1">
      
      <tr>
        <th>Codigo</th>
        <th>Nombre</th>
        <th>Año de nacimiento</th>
        <th>Año de fallecimiento</th>
        <th>pais</th>
        <th>pagina web</th>
      </tr>
     
    <xsl:for-each select="//artista">
      <xsl:sort select="nacimiento" order="ascending" data-type="number"/> 
      <xsl:if test="nacimiento &gt; 1500"><tr>  
        
          <td><xsl:value-of select="@cod"/></td>      
          <td><xsl:value-of select="nombreCompleto"/></td>
          <td><xsl:value-of select="nacimiento"/></td>
          <td><xsl:value-of select="fallecimiento"/>
           <xsl:if test="fallecimiento= null">
     <xsl:text> Desconocido</xsl:text>
    </xsl:if>
          </td>
          <td><xsl:value-of select="pais"/></td>
          <td>
            <a>    
              <xsl:attribute name="href">
                <xsl:value-of select="fichaCompleta" />
              </xsl:attribute>
              saber mas...                                                       
            </a></td>
        
        </tr>
        
      </xsl:if>
       </xsl:for-each>
    </table>
    
  </body> 
</html>
 </xsl:template>
  
 
   

</xsl:stylesheet>

