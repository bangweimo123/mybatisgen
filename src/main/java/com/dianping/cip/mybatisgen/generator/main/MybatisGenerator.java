package com.dianping.cip.mybatisgen.generator.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 
 * @author mobangwei
 * 
 */
public class MybatisGenerator {

    public static void main(String[] args) {
        String propertyFilePath = "/data/mybatisgen/mybatisgen.properties";
        if (null != args && args.length > 0) {
            propertyFilePath = args[0];
        }
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        InputStream stream = MybatisGenerator.class.getClassLoader().getResourceAsStream("config/mybatis-generator.xml");
        ConfigurationParser cp = null;
        Configuration config = null;
        try {
            Properties extraProperties = new Properties();
            extraProperties.load(MybatisGenerator.class.getClassLoader().getResourceAsStream("config/base-config.properties"));
            extraProperties.load(new FileInputStream(propertyFilePath));
            cp = new ConfigurationParser(extraProperties, warnings);
            config = cp.parseConfiguration(stream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = null;
        try {
            myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        try {
            myBatisGenerator.generate(null);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
