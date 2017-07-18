package com.dianping.cip.mybatisgen.generator;

import java.sql.Types;
import java.util.Date;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl.JdbcTypeInformation;

/**
 * 解决BLOB的问题
 * 
 * @author mobangwei
 * 
 */
public class JavaTypeResolverPluginImpl extends JavaTypeResolverDefaultImpl {
    public JavaTypeResolverPluginImpl() {
        super();
        typeMap.put(Types.BLOB, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(String.class.getName()))); //$NON-NLS-1$ 
        typeMap.put(Types.CLOB, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(String.class.getName()))); //$NON-NLS-1$
        typeMap.put(Types.NCLOB, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(String.class.getName()))); //$NON-NLS-1$
        typeMap.put(Types.BINARY, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(Integer.class.getName()))); //$NON-NLS-1$
        typeMap.put(Types.LONGVARCHAR, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(String.class.getName()))); //$NON-NLS-1$
        typeMap.put(Types.LONGNVARCHAR, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(String.class.getName()))); //$NON-NLS-1$
        typeMap.put(Types.VARBINARY, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(Integer.class.getName()))); //$NON-NLS-1$
        typeMap.put(Types.LONGVARBINARY, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(Integer.class.getName()))); //$NON-NLS-1$
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(Integer.class.getName())));
        typeMap.put(Types.TIMESTAMP, new JdbcTypeInformation("OTHER", //$NON-NLS-1$
                        new FullyQualifiedJavaType(Date.class.getName())));
    }
}
