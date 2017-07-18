package com.dianping.cip.mybatisgen.generator;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

public class DPIntrospectedTableMyBatis3Impl extends IntrospectedTableMyBatis3Impl {
    @Override
    public List<GeneratedXmlFile> getGeneratedXmlFiles() {
        List<GeneratedXmlFile> answer = new ArrayList<GeneratedXmlFile>();
        boolean isMergeable = false;
        if (context.getProperty("mergeable") != null) {
            isMergeable = Boolean.valueOf(context.getProperty("mergeable"));
        }
        if (xmlMapperGenerator != null) {
            Document document = xmlMapperGenerator.getDocument();
            GeneratedXmlFile gxf = new GeneratedXmlFile(document, getMyBatis3XmlMapperFileName(), getMyBatis3XmlMapperPackage(), context.getSqlMapGeneratorConfiguration().getTargetProject(), isMergeable, context.getXmlFormatter());
            if (context.getPlugins().sqlMapGenerated(gxf, this)) {
                answer.add(gxf);
            }
        }

        return answer;
    }
}
