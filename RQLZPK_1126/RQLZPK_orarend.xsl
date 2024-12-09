<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"></xsl:output>

    <xsl:template match="/">
        <htlm>
            <head>
                <title>SZK Órarend - 2024/25. I. félév</title>
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
                <h1>SZK Órarend - 2024/25. I. félév</h1>
                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Típus</th>
                             <th>Tantárgy</th>
                            <th>Nap</th>
                            <th>Időpont</th>
                            <th>Helyszín</th>
                            <th>Oktató</th>
                            <th>Szak</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:foreach select="BB_orarend/ora">
                            <tr>
                                <td><xsl:value-of select="@id"/></td>
                                <td><xsl:value-of select="@tipus"/></td>
                                <td><xsl:value-of select="targy"/></td>
                                <td><xsl:value-of select="idopont/nap"/></td>
                                <td>
                                    <xsl:value-of select="idopont/tol"/> - <xsl:value-of select="select/ig"/>
                                </td>
                                <td><xsl:value-of select="helyszin"/></td>
                                <td><xsl:value-of select="oktato"/></td>
                                <td><xsl:value-of select="szak"/></td>
                            </tr>
                        </xsl:foreach>
                    </tbody>
                </table>
            </body>
        </htlm>
    </xsl:template>
</xsl:stylesheet>