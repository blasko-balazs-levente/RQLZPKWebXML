<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs" version="2.0">
<xsl:template match = "/">
<html>
    <body>
        <h2>Hallgatok adatai</h2>
        <table border = "4">
        <tr bgcolor = "#9acd32">
        <th>Vezeteknev</th>
        <th>Kerersztnev</th>
        <th>Becenev</th>
        <th>Kor</th>
        <th>Osztondij</th>
        </tr>
        <xsl:for-each select="class/student">
        <tr>
            <td>
                <xsl:value-of select = "@id"/>
            </td>
            <td><xsl:value-of select = "vezeteknev"/></td>
            <td><xsl:value-of select = "keresztnev"/></td>
            <td><xsl:value-of select = "becenev"/></td>
            <td><xsl:value-of select = "kor"/></td>
            <td><xsl:value-of select = "osztondij"/></td>
        </tr>
        </xsl:for-each>
        </table>
    </body>
</html>
</xsl:template>
<xsl:output method="html" encoidng="utf-8" indent"yes"></xsl:output>
</xsl:stylesheet>