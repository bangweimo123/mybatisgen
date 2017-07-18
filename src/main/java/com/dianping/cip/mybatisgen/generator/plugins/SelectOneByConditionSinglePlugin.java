package com.dianping.cip.mybatisgen.generator.plugins;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * 查询单个接口
 * 
 * @author mobangwei
 * 
 */
public class SelectOneByConditionSinglePlugin extends MybatisPluginAdapter {
    private static final String EXAMPLE_DEFAULT = "Example";
    private String selectOneByConditionStatementIdPrefix = "selectOneBy";

    private String getSelectOneByExampleStatementId() {
        String exampleAlias = (String) properties.get("exampleAlias");
        String EXAMPLE_NAME = StringUtils.isBlank(exampleAlias) ? EXAMPLE_DEFAULT : exampleAlias;
        String selectOneByConditionStatementId = selectOneByConditionStatementIdPrefix + EXAMPLE_NAME;
        return selectOneByConditionStatementId;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        SelectOneByConditionJavaMapperMethodGenerator methodGenerator = new SelectOneByConditionJavaMapperMethodGenerator();
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.addInterfaceElements(interfaze);
        return true;
    }



    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        SelectOneByConditionXmlElementGenerator elementGenerator = new SelectOneByConditionXmlElementGenerator();
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.addElements(document.getRootElement());
        return true;
    }

    class SelectOneByConditionXmlElementGenerator extends AbstractXmlElementGenerator {

        @Override
        public void addElements(XmlElement parentElement) {

            String fqjt = introspectedTable.getExampleType();

            XmlElement answer = new XmlElement("select"); //$NON-NLS-1$

            answer.addAttribute(new Attribute("id", //$NON-NLS-1$
                            getSelectOneByExampleStatementId()));
            answer.addAttribute(new Attribute("resultMap", introspectedTable.getBaseResultMapId())); //$NON-NLS-1$
            answer.addAttribute(new Attribute("parameterType", fqjt)); //$NON-NLS-1$

            context.getCommentGenerator().addComment(answer);

            answer.addElement(new TextElement("select")); //$NON-NLS-1$
            XmlElement ifElement = new XmlElement("if"); //$NON-NLS-1$
            ifElement.addAttribute(new Attribute("test", "distinct")); //$NON-NLS-1$ //$NON-NLS-2$
            ifElement.addElement(new TextElement("distinct")); //$NON-NLS-1$
            answer.addElement(ifElement);

            StringBuilder sb = new StringBuilder();
            if (stringHasValue(introspectedTable.getSelectByExampleQueryId())) {
                sb.append('\'');
                sb.append(introspectedTable.getSelectByExampleQueryId());
                sb.append("' as QUERYID,"); //$NON-NLS-1$
                answer.addElement(new TextElement(sb.toString()));
            }
            answer.addElement(getBaseColumnListElement());

            sb.setLength(0);
            sb.append("from "); //$NON-NLS-1$
            sb.append(introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime());
            answer.addElement(new TextElement(sb.toString()));
            answer.addElement(getExampleIncludeElement());
            parentElement.addElement(answer);
        }

    }
    class SelectOneByConditionJavaMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {

        public SelectOneByConditionJavaMapperMethodGenerator() {
            super();
        }

        @Override
        public void addInterfaceElements(Interface interfaze) {
            Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
            FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getExampleType());
            importedTypes.add(type);

            Method method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);

            FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
            method.setReturnType(returnType);

            method.setName(getSelectOneByExampleStatementId());
            method.addParameter(new Parameter(type, "example")); //$NON-NLS-1$

            context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);

            addMapperAnnotations(interfaze, method);

            if (context.getPlugins().clientSelectByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable)) {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
        }

        public void addMapperAnnotations(Interface interfaze, Method method) {}
    }

}
