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
public class DynamicSqlGenerator extends AbstractXmlElementGenerator {

    public static final String DYNAMICSQL = "DynamicSql";

    public DynamicSqlGenerator() {
        super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
        XmlElement answer = new XmlElement("sql"); //$NON-NLS-1$

        answer.addAttribute(new Attribute("id", MyBatisConstants.COMMON + "." + DYNAMICSQL)); //$NON-NLS-1$
        answer.addAttribute(new Attribute("parameterClass", "java.lang.String")); //$NON-NLS-1$
        answer.addAttribute(new Attribute("resultClass", "java.util.HashMap")); //$NON-NLS-1$
        answer.addAttribute(new Attribute("remapResults", "true")); //$NON-NLS-1$
        answer.addElement(new TextElement("<![CDATA[ $value$ ]]>"));
        // 在第二个地方增加
        parentElement.addElement(answer);
    }
}
