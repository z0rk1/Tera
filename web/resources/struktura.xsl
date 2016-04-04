<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.1"
                xmlns:xsl= "http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
        exclude-result-prefixes="fo">
<xsl:template match="root">
<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <fo:layout-master-set>
        <fo:simple-page-master master-name="my-page">
            <fo:region-body margin="1in"/>
        </fo:simple-page-master>
    </fo:layout-master-set>

    <fo:page-sequence master-reference="my-page">
        <fo:flow flow-name="xsl-region-body">
            <fo:block>Hello,<xsl:value-of select="name"/>!
            </fo:block>
            <fo:block>
                <fo:table>
                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell border="solid 1px black"
                                           text-align="center" font-weight="bold">
                                <fo:block>
                                    No.
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="solid 1px black"
                                           text-align="center" font-weight="bold">
                                <fo:block>
                                    Name
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell border="solid 1px black"
                                           text-align="center" font-weight="bold">
                                <fo:block>
                                    Phone Number
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <xsl:for-each select="./friend">
                            <fo:table-row>
                                <fo:table-cell border="solid 1px black" text-align="center">
                                    <fo:block>
                                        <xsl:value-of select="position()"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="solid 1px black" text-align="center">
                                    <fo:block>
                                        <xsl:value-of select="name"/>
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell border="solid 1px black" text-align="center">
                                    <fo:block>
                                        <xsl:value-of select="phNo"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </xsl:for-each>
                    </fo:table-body>
                </fo:table>
            </fo:block>
        </fo:flow>
    </fo:page-sequence>
</fo:root>
</xsl:template>
        </xsl:stylesheet>