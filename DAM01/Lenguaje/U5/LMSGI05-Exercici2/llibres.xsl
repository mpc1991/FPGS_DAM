<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <head>
        <style>
          .titulo {
            font-size: 20px;
            color: blue;
          }
          .autor {
            font-size: 10px;
            font-weight: bold;
          }
        </style>
      </head>
      <body>
        <h1>Biblioteca</h1>
        <!-- Aplicar la ordenación por título -->
        <xsl:apply-templates select="biblioteca/llibre">
          <xsl:sort select="títol"/>
        </xsl:apply-templates>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="llibre">
    <div>
      <span class="titulo">
        <xsl:value-of select="títol"/>
      </span>. Escrit per <span class="autor">
        <xsl:value-of select="autor/nom"/>
      </span> <span class="autor">
        <xsl:value-of select="autor/llinatges"/>
      </span> en <xsl:value-of select="títol/@idioma"/> i publicat l'any <xsl:value-of select="data_de_publicació/@any"/>.
    </div>
  </xsl:template>
</xsl:stylesheet>
