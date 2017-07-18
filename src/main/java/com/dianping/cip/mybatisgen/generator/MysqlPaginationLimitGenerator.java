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
public class MysqlPaginationLimitGenerator extends AbstractXmlElementGenerator {

    public static final String MYSQL_PAGINATION_LIMIT = "Mysql_Pagination_Limit";

    public MysqlPaginationLimitGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", MyBatisConstants.COMMON + "." + MYSQL_PAGINATION_LIMIT)); //$NON-NLS-1$

        context.getCommentGenerator().addComment(answer);
        XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
        ifElement.addAttribute(new Attribute("test", "mysqlOffset !=null")); //$NON-NLS-1$ //$NON-NLS-2$ 
        XmlElement if2Element = new XmlElement("if"); //$NON-NLS-1$
        if2Element.addAttribute(new Attribute("test", "mysqlLength !=null")); //$NON-NLS-1$ //$NON-NLS-2$
        if2Element.addElement(new TextElement("<![CDATA[ limit #{mysqlOffset} , #{mysqlLength} ]]>"));
        ifElement.addElement(if2Element);
        answer.addElement(ifElement);
        // 在第二个地方增加
        parentElement.addElement(answer);
    }
}
