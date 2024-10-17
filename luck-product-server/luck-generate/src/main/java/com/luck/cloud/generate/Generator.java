package com.luck.cloud.generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.luck.cloud.generate.utils.CustomMap;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author luck
 * @date 2024-07-29
 */
public class Generator {

    /**
     * 数据库类型
     */
    private static String dbType = DbType.MYSQL.getDb();
    /**
     * 文件输出路径
     */
    private static String path = "/luck-core/src/main/java";
    /**
     * 父包目录
     */
    private static String parentPackage = "com.luck.cloud";
    /**
     * 模块名
     */
    private static String modulesName = "core";

    /**
     * mapper.xml输出路径
     */
    private static String mapperPath = "/luck-core/src/main/resources/mapper/" + dbType + "/";

    /**
     * 需要生成的表(注意数据库类型是否区分大小写)
     */
    private static String[] include = {"sys_dict_type","sys_dict_item"};

    /**
     * 需要去除的表名字前缀
     */
    private static String tablePrefix = "sys";

    /**
     * 创建作者
     */
    private static String author = "luck";

    /**
     * 项目路径
     */
    private static String projectPath = System.getProperty("user.dir") + File.separator + "luck-product-server";


    public static void main(String[] args) {
        FastAutoGenerator
                .create(new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3306/luck_product?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC","root","root")
                        .typeConvert(new MySqlTypeConvert()))
                .globalConfig(builder -> {
                    builder.outputDir(projectPath + path)
                            .disableOpenDir()
                            .author(author)
                            .dateType(DateType.SQL_PACK)
                            .commentDate("yyyy-MM-dd")
                            .enableSwagger();
                })
                .packageConfig(builder -> {
                    builder.parent(parentPackage)
                            .moduleName(modulesName)
                            .mapper("dao")
                            // xml文件输出路径
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + mapperPath + modulesName.replaceAll("\\.", "/")));
                })
                .templateConfig(builder -> {
                    builder.entity("template/entity.java")
                            .service("template/service.java")
                            .serviceImpl("template/serviceImpl.java")
                            .mapper("template/mapper.java")
                            .controller("template/controller.java")
                            .xml(null);
                })
                .strategyConfig(builder -> {
                    builder.addInclude(include)
                            .addTablePrefix(tablePrefix + "_")

                            .entityBuilder()
                            .disableSerialVersionUID()
                            .enableLombok()
                            .superClass(com.luck.cloud.base.entity.DataEntity.class)
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .formatFileName("%s")

                            .serviceBuilder()
                            .superServiceClass(com.luck.cloud.base.service.IBaseService.class)
                            .superServiceImplClass(com.luck.cloud.base.service.impl.BaseServiceImpl.class)
                            .formatServiceFileName("I%sService")
                            .formatServiceImplFileName("%sServiceImpl")

                            .mapperBuilder()
                            .superClass(com.luck.cloud.base.dao.BaseDao.class)
                            .formatMapperFileName("%sDao")

                            .controllerBuilder()
                            .superClass(com.luck.cloud.base.controller.BaseController.class)
                            .formatFileName("%sController")
                            .enableRestStyle();
                })
                .injectionConfig(builder -> {
                    // 自定义xml模板
                    builder.customFile(CustomMap.create().build("Dao.xml", "template/mapper.xml.btl").build("VO.java", "template/vo.java.btl"))
                            .build();
                })
                .templateEngine(new BeetlTemplateEngine() {
                    @Override
                    protected void outputCustomFile(@NotNull Map<String, String> customFile, TableInfo tableInfo, Map<String, Object> objectMap) {
                        String entityName = tableInfo.getEntityName();
                        //自定义xml文件输出
                        customFile.forEach((key, value) -> {
                            String parentPath;
                            if(key.startsWith("Dao")){
                                // 自定义xml文件输出
                                parentPath = this.getPathInfo(OutputFile.xml);
                            }else{
                                // 自定义vo文件输出
                                parentPath = projectPath + path + File.separator + (parentPackage + File.separator + modulesName + File.separator + "vo").replaceAll("\\.", "/");
                            }
                            String fileName = String.format(parentPath + File.separator + entityName + "%s", key);
                            this.outputFile(new File(fileName), objectMap, value, this.getConfigBuilder().getInjectionConfig().isFileOverride());
                        });
                    }
                }).execute();


    }

}
