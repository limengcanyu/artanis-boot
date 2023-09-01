package com.spring.boot.mybatis.plus;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://192.168.136.128:3306/db";
        String username = "root";
        String password = "PassW0rd321";

        String modulePath = "spring-boot-mybatis-plus";
        String parentName = "com.spring.boot.mybatis.plus";

        String include = "t_address";

        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("rock") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
                            .dateType(DateType.TIME_PACK)
                            .commentDate("yyyy-MM-dd")
                            .outputDir(modulePath + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent(parentName) // 设置父包名
//                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, modulePath + "\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    // 修改生成的Service类名不带I前缀
                    builder.serviceBuilder().convertServiceFileName((entityName -> entityName + ConstVal.SERVICE));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(include) // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
