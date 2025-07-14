<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
  <xsl:template match="/">
  <html>
    <head>
      <title>Resultat Atles en HTML</title>
    </head>
    <body>
      <table border="1">
        <tr>
          <th>Nom del Pais</th>
          <th>Capital</th>
          <th>Govern</th>
        </tr>
        
        <xsl:for-each select="atles/pais">
        <tr>
          <td><xsl:value-of select="nom"/></td>
          <td><xsl:value-of select="capital"/></td>
          <td><xsl:value-of select="@govern"/></td>
        </tr>
        </xsl:for-each>
        
        
      </table>
    </body>
  </html>
  </xsl:template>
</xsl:stylesheet>