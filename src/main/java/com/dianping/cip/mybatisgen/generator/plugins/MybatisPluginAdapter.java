package com.dianping.cip.mybatisgen.generator.plugins;

import java.util.List;

import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;

public class MybatisPluginAdapter extends PluginAdapter {

    public void addSetterComment(Method method, String doc, String paramName) {
        StringBuilder sb = new StringBuilder();
        if (doc != null && doc.length() != 0) {
            method.addJavaDocLine("/**");
            sb.append(" * @param " + paramName + " ");
            doc = doc.replaceAll("\n", "<br>\n\t * ");
            sb.append(doc);
            method.addJavaDocLine(sb.toString());
            method.addJavaDocLine(" */");
        }
    }

    public void addFieldComment(Field field, String doc) {
        StringBuilder sb = new StringBuilder();
        if (doc != null && doc.length() != 0) {
            field.addJavaDocLine("/**");
            sb.append(" * ");
            doc = doc.replaceAll("\n", "<br>\n\t * ");
            sb.append(doc);
            field.addJavaDocLine(sb.toString());
            field.addJavaDocLine(" */");
        }
    }

    public void addGetterComment(Method method, String doc, String paramName) {
        StringBuilder sb = new StringBuilder();
        if (doc != null && doc.length() != 0) {
            method.addJavaDocLine("/**");
            sb.append(" * @return ");
            doc = doc.replaceAll("\n", "<br>\n\t * ");
            sb.append(doc);
            method.addJavaDocLine(sb.toString());
            method.addJavaDocLine(" */");
        }
    }

    public void addMethodComment(Method method, String doc, String paramName) {
        StringBuilder sb = new StringBuilder();
        if (doc != null && doc.length() != 0) {
            method.addJavaDocLine("/**");
            sb.append(" * ");
            doc = doc.replaceAll("\n", "<br>\n\t * ");
            sb.append(doc);
            method.addJavaDocLine(sb.toString());
            method.addJavaDocLine(" */");
        }
    }

    @Override
    public boolean validate(List<String> warnings) {
        // TODO Auto-generated method stub
        return true;
    }
}
