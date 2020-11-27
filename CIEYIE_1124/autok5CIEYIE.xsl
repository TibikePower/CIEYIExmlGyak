<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs">
	<xsl:template match="/">
		<xsl:element name="eredmeny">
			<xsl:for-each-group select="//auto" group-by="tipus/text()">
				<xsl:sort select="count(current-group())"/>
				<xsl:element name="tipus">
					<xsl:element name="tip">
						<xsl:value-of select="current-group-key()"/>
					</xsl:element>
					<xsl:element name="db"><xsl:value-of select="count(current-group())"/>
					</xsl:element>
				</xsl:element>
			</xsl:for-each-group>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>