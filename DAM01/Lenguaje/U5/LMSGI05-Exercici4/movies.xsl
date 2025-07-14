<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <head>
        <style>
          table {
            border-collapse: collapse;
            width: 100%;
          }
          th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
          }
          th {
            background-color: #f2f2f2;
          }
        </style>
      </head>
      <body>
        <h2>Movies</h2>
        <table>
          <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Date Published</th>
            <th>Duration</th>
            <th>Country</th>
            <th>Worldwide Gross Income</th>
            <th>Currency</th>
            <th>Languages</th>
            <th>Production Company</th>
          </tr>
          <xsl:for-each select="resultset/row">
            <tr>
              <td><xsl:value-of select="field[@name='id']"/></td>
              <td><xsl:value-of select="field[@name='title']"/></td>
              <td><xsl:value-of select="field[@name='date_published']"/></td>
              <td><xsl:value-of select="field[@name='duration']"/></td>
              <td><xsl:value-of select="field[@name='country']"/></td>
              <td><xsl:value-of select="field[@name='worldwide_gross_income']"/></td>
              <td><xsl:value-of select="field[@name='currency']"/></td>
              <td><xsl:value-of select="field[@name='languages']"/></td>
              <td><xsl:value-of select="field[@name='production_company']"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
