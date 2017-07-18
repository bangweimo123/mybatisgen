/*
 * Copyright 2008 The Apache Software Foundation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.dianping.cip.mybatisgen.generator;

import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import com.dianping.cip.mybatisgen.generator.constants.MyBatisConstants;


/**
 * @author matychen
 * 
 */
public class OraclePaginationTailGenerator extends AbstractXmlElementGenerator {
    public static final String ORACLE_PAGINATION_TAIL = "Oracle_Pagination_Tail";

    public OraclePaginationTailGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", MyBatisConstants.COMMON + "." + ORACLE_PAGINATION_TAIL)); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);

        XmlElement dynamicElement = new XmlElement("dynamic");
        XmlElement outerisNotEmptyElement = new XmlElement("isNotNull");
        outerisNotEmptyElement.addAttribute(new Attribute("property", "oracleStart"));
        XmlElement innerisNotEmptyElement = new XmlElement("isNotNull");
        innerisNotEmptyElement.addAttribute(new Attribute("property", "oracleEnd"));
        innerisNotEmptyElement.addElement(new TextElement("<![CDATA[ ) row_ where rownum <= #oracleEnd# ) where rownum_ > #oracleStart# ]]>"));
        outerisNotEmptyElement.addElement(innerisNotEmptyElement);
        dynamicElement.addElement(outerisNotEmptyElement);
        answer.addElement(dynamicElement);
        // 在第二个地方增加
        parentElement.addElement(answer);
    }
}
