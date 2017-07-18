package com.dianping.cip.mybatisgen.generator.plugins;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.OutputUtilities;
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
import org.mybatis.generator.codegen.mybatis3.ListUtilities;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * 批量添加接口
 * 
 * @author mobangwei
 * 
 */
public class BatchInsertPlugin extends MybatisPluginAdapter {
    private String batchInsertStatementId = "batchInsert";


    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        BatchInsertJavaMapperMethodGenerator methodGenerator = new BatchInsertJavaMapperMethodGenerator();
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.addInterfaceElements(interfaze);
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        BatchInsertXmlElementGenerator elementGenerator = new BatchInsertXmlElementGenerator();
        elementGenerator.setContext(context);
        elementGenerator.setIntrospectedTable(introspectedTable);
        elementGenerator.addElements(document.getRootElement());
        return true;
    }

    class BatchInsertXmlElementGenerator extends AbstractXmlElementGenerator {

        @Override
        public void addElements(XmlElement parentElement) {
            XmlElement answer = new XmlElement("insert"); //$NON-NLS-1$

            answer.addAttribute(new Attribute("id", batchInsertStatementId)); //$NON-NLS-1$
            answer.addAttribute(new Attribute("parameterType", "map")); //$NON-NLS-1$ //$NON-NLS-2$

            answer.addAttribute(new Attribute("useGeneratedKeys", "true"));
            answer.addAttribute(new Attribute("keyProperty", introspectedTable.getPrimaryKeyColumns().get(0).getActualColumnName()));
            context.getCommentGenerator().addComment(answer);

            context.getCommentGenerator().addComment(answer);

            StringBuilder insertClause = new StringBuilder();
            StringBuilder valuesClause = new StringBuilder();

            insertClause.append("insert into "); //$NON-NLS-1$
            insertClause.append(introspectedTable.getFullyQualifiedTableNameAtRuntime());
            insertClause.append(" (");
            XmlElement iterateElement = new XmlElement("foreach");
            iterateElement.addAttribute(new Attribute("separator", ","));
            iterateElement.addAttribute(new Attribute("collection", "list"));
            iterateElement.addAttribute(new Attribute("item", "record"));
            valuesClause.append(" ( "); //$NON-NLS-1$

            List<String> valuesClauses = new ArrayList<String>();
            List<IntrospectedColumn> columns = ListUtilities.removeIdentityAndGeneratedAlwaysColumns(introspectedTable.getAllColumns());
            for (int i = 0; i < columns.size(); i++) {
                IntrospectedColumn introspectedColumn = columns.get(i);

                insertClause.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
                valuesClause.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn, "record."));
                if (i + 1 < columns.size()) {
                    insertClause.append(", "); //$NON-NLS-1$
                    valuesClause.append(", "); //$NON-NLS-1$
                }

                if (valuesClause.length() > 80) {
                    answer.addElement(new TextElement(insertClause.toString()));
                    insertClause.setLength(0);
                    OutputUtilities.xmlIndent(insertClause, 1);

                    valuesClauses.add(valuesClause.toString());
                    valuesClause.setLength(0);
                    OutputUtilities.xmlIndent(valuesClause, 1);
                }
            }

            insertClause.append(')');
            insertClause.append(" values");
            answer.addElement(new TextElement(insertClause.toString()));

            valuesClause.append(')');
            valuesClauses.add(valuesClause.toString());

            for (String clause : valuesClauses) {
                iterateElement.addElement(new TextElement(clause));
            }
            answer.addElement(iterateElement);

            if (context.getPlugins().sqlMapInsertElementGenerated(answer, introspectedTable)) {
                parentElement.addElement(answer);
            }
        }

    }
    class BatchInsertJavaMapperMethodGenerator extends AbstractJavaMapperMethodGenerator {

        public BatchInsertJavaMapperMethodGenerator() {
            super();
        }

        @Override
        public void addInterfaceElements(Interface interfaze) {
            Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
            Method method = new Method();

            method.setReturnType(new FullyQualifiedJavaType("void"));
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName(batchInsertStatementId);

            FullyQualifiedJavaType parameterType = introspectedTable.getRules().calculateAllFieldsClass();

            importedTypes.add(parameterType);
            FullyQualifiedJavaType listType = new FullyQualifiedJavaType("List");
            listType.addTypeArgument(parameterType);
            method.addParameter(new Parameter(listType, "records", "@Param(\"list\")"));

            importedTypes.add(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Param")); //$NON-NLS-1$
            importedTypes.add(new FullyQualifiedJavaType("java.util.List"));
            context.getCommentGenerator().addGeneralMethodComment(method, introspectedTable);


            if (context.getPlugins().clientInsertMethodGenerated(method, interfaze, introspectedTable)) {
                interfaze.addImportedTypes(importedTypes);
                interfaze.addMethod(method);
            }
        }

        public void addMapperAnnotations(Interface interfaze, Method method) {}
    }
}
