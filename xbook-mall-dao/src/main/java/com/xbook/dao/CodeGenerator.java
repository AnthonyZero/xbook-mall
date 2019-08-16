package com.xbook.dao;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.xbook.common.utils.PropertiesFileUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 */
public class CodeGenerator {

    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("jdbc").get("jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("jdbc").get("jdbc.url");
    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("jdbc").get("jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("jdbc").get("jdbc.password");
    private static String[] projects = {"xbook-mall-entity", "xbook-mall-dao"}; //项目名
    private static String[] parentNames = {"com.xbook.entity", "com.xbook.dao"}; //代码放置位置 包的父目录

    public static void main(String[] args) {
        String moduleName = scanner("模块名");
        String tableName = scanner("表名");
        for(int i = 0; i < projects.length; i++) {
            generator(projects[i], parentNames[i], moduleName, tableName);
        }
    }

    private static void generator(String projectName, String parentName, String moduleName, String tableName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/" + projectName;
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("anthonyzero");
        gc.setOpen(false); //是否打开输出目录
        gc.setBaseResultMap(false); //开启 BaseResultMap
        gc.setBaseColumnList(false); //开启 BaseColumnList
        //gc.setServiceName("%sService"); //service 命名方式
        //gc.setServiceImplName("%sServiceImpl");
        //gc.setFileOverride(true); //是否覆盖已有文件 默认不覆盖 慎用
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(JDBC_URL);
        dsc.setDriverName(JDBC_DRIVER);
        dsc.setUsername(JDBC_USERNAME);
        dsc.setPassword(JDBC_PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        /*pc.setModuleName(moduleName);*/ //在哪个模块下 比如pay
        pc.setParent(parentName);
        pc.setEntity(moduleName); // 默认为entity
        pc.setMapper(moduleName); // 默认为mapper
        mpg.setPackageInfo(pc);


        //TemplateConfig 可自定义代码生成的模板
        // 配置模板
        if ("xbook-mall-dao".equals(projectName)) {
            // 可注入自定义参数等操作以实现个性化操作
            InjectionConfig cfg = new InjectionConfig() {
                //自定义属性注入:abc
                //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
                @Override
                public void initMap() {
                    // to do nothing
                }
            };

            // 如果模板引擎是 freemarker
            String templatePath = "/templates/mapper.xml.ftl"; //此文件是mybatis-plus-generator自带模板
            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出xml文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                    return projectPath + "/src/main/resources/mapper/" + moduleName
                            + "/" + tableInfo.getMapperName() + StringPool.DOT_XML;
                }
            });
            cfg.setFileOutConfigList(focList);
            mpg.setCfg(cfg);

            TemplateConfig templateConfig = new TemplateConfig();
            templateConfig.setEntity(null); //不生成entity代码
            templateConfig.setService(null);  //不生成service代码
            templateConfig.setServiceImpl(null); //不生成serviceImpl代码
            templateConfig.setController(null); //不生成controller代码
            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);
        } else {
            TemplateConfig templateConfig = new TemplateConfig();
            templateConfig.setMapper(null); //不生成mapper代码
            templateConfig.setService(null);  //不生成service代码
            templateConfig.setServiceImpl(null); //不生成serviceImpl代码
            templateConfig.setController(null); //不生成controller代码
            templateConfig.setXml(null);
            mpg.setTemplate(templateConfig);
        }

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel); //表映射到实体的命名策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);// lombok注解
        // 写于父类中的公共字段
        strategy.setInclude(tableName); //需要包含的表名
        /*strategy.setSuperEntityColumns("id");*/ //主键为id的时候 生成的代码不会包含主键id 去掉
        strategy.setControllerMappingHyphenStyle(true);
        /*strategy.setTablePrefix(pc.getModuleName() + "_");*/  //决定生成的实体类是否带前缀 如果设置了值 会去掉前缀（值） 这里不去掉前缀
        strategy.setEntityTableFieldAnnotationEnable(true); //是否生成实体时，生成字段注解
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
