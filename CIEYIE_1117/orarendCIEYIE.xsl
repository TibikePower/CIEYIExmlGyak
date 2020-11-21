<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html"/>
	<xsl:template match="/">
		<html>
			<body>
				<table border="1">
				    <tr bgcolor="#9acd32">
				        <th>Nap</th>
				        <th>Ido</th>
				        <th>Targy</th>
				        <th>Oktato</th>
				        <th>Helyszin</th>
				        <th>Szak</th>
				    </tr>
				    <xsl:for-each select="orarend/ora">
				    <tr>
						<td><xsl:value-of select="idopont/nap"/></td>
						<td><xsl:value-of select="idopont/tol"/>-<xsl:value-of select="idopont/ig"/></td>
						<td><xsl:value-of select="targy"/></td>
						<td><xsl:value-of select="oktato"/></td>
						<td><xsl:value-of select="helyszin"/></td>
						<td><xsl:value-of select="szak"/></td>
				    </tr>
				    </xsl:for-each>
				</table>
			</body>
		</html>		
	</xsl:template>
</xsl:stylesheet>