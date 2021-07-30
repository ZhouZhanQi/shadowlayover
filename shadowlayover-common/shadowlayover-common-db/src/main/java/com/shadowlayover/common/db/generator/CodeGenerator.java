package com.shadowlayover.common.db.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.SimpleAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.GeneratorBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.shadowlayover.common.db.model.BaseModel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author zhouzq
 * @date 2020/3/17
 * @desc 代码生成器
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CodeGenerator {

    public static void generatorCode(String author, String dbUrl, String username, String password) {
        new SimpleAutoGenerator() {
            @Override
            public IConfigBuilder<GlobalConfig> globalConfigBuilder() {
                return  GeneratorBuilder.globalConfigBuilder()
                        .outputDir(scannerNext("\n请输入项目路径") + "/src/main/java")
                        .author(author)
                        .openDir(false)
                        .fileOverride();
            }

            @Override
            public IConfigBuilder<PackageConfig> packageConfigBuilder() {
                return new PackageConfig.Builder()
                        .parent(scannerNext("\n请输入项目包名"))
                        .entity("model.domain")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .controller("api")
                        .xml("mappers");
            }

            @Override
            public IConfigBuilder<StrategyConfig> strategyConfigBuilder() {
                 return new StrategyConfig.Builder()
                        .addInclude(scannerNext("\n请输入表名多个英文逗号分隔：").split(","))
                        .entityBuilder()
                        .superClass(BaseModel.class)
                        .enableLombok()
                        .enableSerialVersionUID()
                        .naming(NamingStrategy.underline_to_camel)
//                        .addTableFills(new Column("creator", FieldFill.INSERT))
//                        .addTableFills(new Column("creator_id", FieldFill.INSERT))
//                        .addTableFills(new Column("created_time", FieldFill.INSERT))
//                        .addTableFills(new Column("updater", FieldFill.INSERT_UPDATE))
//                        .addTableFills(new Column("updater_id", FieldFill.INSERT_UPDATE))
//                        .addTableFills(new Column("updated_time", FieldFill.INSERT_UPDATE))
//                        .addTableFills(new Column("trace_id", FieldFill.INSERT))
//                        .addTableFills(new Column("is_deleted", FieldFill.INSERT))
                        .mapperBuilder()
                        .enableBaseColumnList()
                        .enableBaseResultMap()
                        .controllerBuilder()
                        .enableRestStyle();
            }

            @Override
            public IConfigBuilder<DataSourceConfig> dataSourceConfigBuilder() {
                return new DataSourceConfig.Builder(dbUrl, username, password);
            }
        }.execute();
    }

    public static void main(String[] args) {
        CodeGenerator.generatorCode("zhouzhanqi", "jdbc:mysql://localhost:13306/adminuser", "root", "123456");
    }
}
