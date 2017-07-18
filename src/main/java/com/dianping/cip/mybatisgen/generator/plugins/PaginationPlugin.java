package com.dianping.cip.mybatisgen.generator.plugins;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.DefaultXmlFormatter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

import com.dianping.cip.mybatisgen.generator.MysqlPaginationLimitGenerator;
import com.dianping.cip.mybatisgen.generator.OraclePaginationHeadGenerator;
import com.dianping.cip.mybatisgen.generator.OraclePaginationTailGenerator;
import com.dianping.cip.mybatisgen.generator.PluginExampleWhereClauseElementGenerator;
import com.dianping.cip.mybatisgen.generator.constants.MyBatisConstants;


/**
 * 数据库分页插件 把example by where的xml和分页的提取到公用的xml里面去。
 * 
 * @author QQ:34847009
 * @date 2010-10-21 下午09:33:48
 */
public class PaginationPlugin extends MybatisPluginAdapter {
    /** 数据库类型 */
    private String databaseType;
    /** 表 */
    private IntrospectedTable introspectedTable;

    @Override
    public boolean validate(List<String> warnings) {
        databaseType = context.getJdbcConnectionConfiguration().getDriverClass();
        return true;
    }

    protected String getDatabaseType() {
        return databaseType;
    }

    protected IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (databaseType.contains("oracle")) {
            XmlElement oracleHeadIncludeElement = new XmlElement("include");
            oracleHeadIncludeElement.addAttribute(new Attribute("refid", MyBatisConstants.COMMON + "." + OraclePaginationHeadGenerator.ORACLE_PAGINATION_HEAD));
            // 在第几个地方增加
            element.addElement(0, oracleHeadIncludeElement);

            XmlElement oracleTailIncludeElement = new XmlElement("include");
            oracleTailIncludeElement.addAttribute(new Attribute("refid", MyBatisConstants.COMMON + "." + OraclePaginationTailGenerator.ORACLE_PAGINATION_TAIL));
            // 在最后增加
            element.addElement(element.getElements().size(), oracleTailIncludeElement);
        } else if (databaseType.contains("mysql")) {
            XmlElement mysqlLimitIncludeElement = new XmlElement("include");
            mysqlLimitIncludeElement.addAttribute(new Attribute("refid", MyBatisConstants.COMMON + "." + MysqlPaginationLimitGenerator.MYSQL_PAGINATION_LIMIT));
            // 在最后增加
            element.addElement(element.getElements().size(), mysqlLimitIncludeElement);
        }

        return super.sqlMapExampleWhereClauseElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        if (databaseType.contains("oracle")) {
            XmlElement oracleHeadIncludeElement = new XmlElement("include");
            oracleHeadIncludeElement.addAttribute(new Attribute("refid", MyBatisConstants.COMMON + "." + OraclePaginationHeadGenerator.ORACLE_PAGINATION_HEAD));
            // 在第一个地方增加
            element.addElement(0, oracleHeadIncludeElement);

            XmlElement oracleTailIncludeElement = new XmlElement("include");
            oracleTailIncludeElement.addAttribute(new Attribute("refid", MyBatisConstants.COMMON + "." + OraclePaginationTailGenerator.ORACLE_PAGINATION_TAIL));
            // 在最后增加
            element.addElement(element.getElements().size(), oracleTailIncludeElement);
        } else if (databaseType.contains("mysql")) {
            XmlElement mysqlLimitIncludeElement = new XmlElement("include");
            mysqlLimitIncludeElement.addAttribute(new Attribute("refid", MyBatisConstants.COMMON + "." + MysqlPaginationLimitGenerator.MYSQL_PAGINATION_LIMIT));
            // 在最后增加
            element.addElement(element.getElements().size(), mysqlLimitIncludeElement);
        }

        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (databaseType.contains("oracle")) {
            // 增加开始处
            // 增加oracleStart、oracleEnd、mysqlOffset、mysqlLength
            // add field, getter, setter for oracleStart clause
            Field field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(getNewIntegerInstance());
            field.setName("oracleStart"); //$NON-NLS-1$
            topLevelClass.addField(field);

            Method method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setOracleStart"); //$NON-NLS-1$
            method.addParameter(new Parameter(getNewIntegerInstance(), "oracleStart")); //$NON-NLS-1$
            method.addBodyLine("this.oracleStart = oracleStart;"); //$NON-NLS-1$
            addSetterComment(method, "开始记录数", "oracleStart");
            topLevelClass.addMethod(method);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setReturnType(getNewIntegerInstance());
            method.setName("getOracleStart"); //$NON-NLS-1$
            method.addBodyLine("return oracleStart;"); //$NON-NLS-1$
            topLevelClass.addMethod(method);
            // add field, getter, setter for oracleEnd clause
            field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(getNewIntegerInstance());
            field.setName("oracleEnd"); //$NON-NLS-1$
            topLevelClass.addField(field);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setOracleEnd"); //$NON-NLS-1$
            method.addParameter(new Parameter(getNewIntegerInstance(), "oracleEnd")); //$NON-NLS-1$
            method.addBodyLine("this.oracleEnd = oracleEnd;"); //$NON-NLS-1$
            addSetterComment(method, "结束记录数", "oracleEnd");
            topLevelClass.addMethod(method);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setReturnType(getNewIntegerInstance());
            method.setName("getOracleEnd"); //$NON-NLS-1$
            method.addBodyLine("return oracleEnd;"); //$NON-NLS-1$
            topLevelClass.addMethod(method);

        } else if (databaseType.contains("mysql")) {
            // add field, getter, setter for mysqlOffset clause
            Field field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(getNewIntegerInstance());
            field.setName("mysqlOffset"); //$NON-NLS-1$
            topLevelClass.addField(field);

            Method method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setMysqlOffset"); //$NON-NLS-1$
            method.addParameter(new Parameter(getNewIntegerInstance(), "mysqlOffset")); //$NON-NLS-1$
            method.addBodyLine("this.mysqlOffset = mysqlOffset;"); //$NON-NLS-1$
            addSetterComment(method, "指定返回记录行的偏移量<br> mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15", "mysqlOffset");
            topLevelClass.addMethod(method);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setReturnType(getNewIntegerInstance());
            method.setName("getMysqlOffset"); //$NON-NLS-1$
            method.addBodyLine("return mysqlOffset;"); //$NON-NLS-1$
            topLevelClass.addMethod(method);
            // add field, getter, setter for mysqlLength clause
            field = new Field();
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setType(getNewIntegerInstance());
            field.setName("mysqlLength"); //$NON-NLS-1$
            topLevelClass.addField(field);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setName("setMysqlLength"); //$NON-NLS-1$
            method.addParameter(new Parameter(getNewIntegerInstance(), "mysqlLength")); //$NON-NLS-1$
            method.addBodyLine("this.mysqlLength = mysqlLength;"); //$NON-NLS-1$
            addSetterComment(method, "指定返回记录行的最大数目<br> mysqlOffset= 5,mysqlLength=10;  // 检索记录行 6-15", "mysqlLength");
            topLevelClass.addMethod(method);

            method = new Method();
            method.setVisibility(JavaVisibility.PUBLIC);
            method.setReturnType(getNewIntegerInstance());
            method.setName("getMysqlLength"); //$NON-NLS-1$
            method.addBodyLine("return mysqlLength;"); //$NON-NLS-1$
            topLevelClass.addMethod(method);
            // 增加结束处
        }

        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    public static final FullyQualifiedJavaType getNewIntegerInstance() {
        // always return a new instance because the type may be parameterized
        return new FullyQualifiedJavaType("java.lang.Integer"); //$NON-NLS-1$
    }

    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {

        Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID, XmlConstants.MYBATIS3_MAPPER_SYSTEM_ID);
        XmlElement answer = new XmlElement("mapper"); //$NON-NLS-1$
        document.setRootElement(answer);
        answer.addAttribute(new Attribute("namespace", //$NON-NLS-1$
                        MyBatisConstants.COMMON));

        AbstractXmlElementGenerator elementGeneratorUpdateWhere = new PluginExampleWhereClauseElementGenerator(true);
        elementGeneratorUpdateWhere.setContext(context);
        elementGeneratorUpdateWhere.setIntrospectedTable(introspectedTable);
        elementGeneratorUpdateWhere.addElements(answer);

        AbstractXmlElementGenerator elementGeneratorWhere = new PluginExampleWhereClauseElementGenerator(false);
        elementGeneratorWhere.setContext(context);
        elementGeneratorWhere.setIntrospectedTable(introspectedTable);
        elementGeneratorWhere.addElements(answer);
        if (databaseType.contains("oracle")) {
            // 增加手写的尾sql
            AbstractXmlElementGenerator oraclePaginationHeadGenerator = new OraclePaginationHeadGenerator();
            oraclePaginationHeadGenerator.setContext(context);
            oraclePaginationHeadGenerator.addElements(answer);
            // 增加手写的头sql
            AbstractXmlElementGenerator oraclePaginationTailGenerator = new OraclePaginationTailGenerator();
            oraclePaginationTailGenerator.setContext(context);
            oraclePaginationTailGenerator.addElements(answer);
        } else if (databaseType.contains("mysql")) {
            // 增加mysql
            AbstractXmlElementGenerator mysqlPaginationLimitGenerator = new MysqlPaginationLimitGenerator();
            mysqlPaginationLimitGenerator.setContext(context);
            mysqlPaginationLimitGenerator.addElements(answer);
        }

        GeneratedXmlFile gxf = new GeneratedXmlFile(document, properties.getProperty("fileName", MyBatisConstants.COMMON + "_Mapper.xml"), context.getSqlMapGeneratorConfiguration().getTargetPackage(), context.getSqlMapGeneratorConfiguration().getTargetProject(), false, new DefaultXmlFormatter());

        List<GeneratedXmlFile> files = new ArrayList<GeneratedXmlFile>(1);
        files.add(gxf);

        return files;
    }

    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }
}
