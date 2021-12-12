///*------------------------------------------------------------------------------
// -  Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
// -----------------------------------------------------------------------------*/
//
//package top.yangbuyi.service_pay;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
//import com.baomidou.mybatisplus.generator.config.GlobalConfig;
//import com.baomidou.mybatisplus.generator.config.PackageConfig;
//import com.baomidou.mybatisplus.generator.config.StrategyConfig;
//import com.baomidou.mybatisplus.generator.config.rules.DateType;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import lombok.RequiredArgsConstructor;
//
///**
// * @program: yangbuyi_video
// * @ClassName: test1
// * @create: 2021-10-20 02:30
// * @author: Yang Shuai
// * @since： JDK1.8
// * @test1: $
// **/
//
//public class CodeGenerator  {
//
//    @Test
//    public void main() {
//        // 1、创建代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//        // 2、全局配置
//        GlobalConfig gc = new GlobalConfig();
//        //配置输出路径
//        gc.setOutputDir("D:\\27-前后端分离-微服务项目实战班级训练营\\CloudVideo\\yangbuyi_video\\yangbuyi_service\\service_pay\\src\\main\\java");
//        gc.setAuthor("yangbuyiya");
//        gc.setOpen(false); //生成后是否打开资源管理器
//        gc.setFileOverride(false); //重新生成时文件是否覆盖
//        /*
//         * mp生成service层代码，默认接口名称第一个字母有 I
//         * */
//        gc.setControllerName("%sController");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
//        gc.setIdType(IdType.ID_WORKER_STR); //主键策略
//        gc.setDateType(DateType.ONLY_DATE);//定义生成的实体类中日期类型
//        gc.setSwagger2(false);//开启Swagger2模式
//        mpg.setGlobalConfig(gc);
//
//        // 3、数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://www.yangbuyi.top:3310/video_db?serverTimezone=GMT%2B8");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("yangbuyiya");
//        dsc.setDbType(DbType.MYSQL);
//        mpg.setDataSource(dsc);
//
//        // 4、包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName("service_pay"); //模块名
//        pc.setParent("top.yangbuyi");      //top.yangbuyi.模块名称.功能-》  top.yangbuyi.service_auth.controller
//        pc.setController("controller");
//        pc.setEntity("entity");
//        pc.setService("service");
//        pc.setMapper("mapper");
//        mpg.setPackageInfo(pc);
//
//        // 5、策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setInclude("pay_log","pay_order");
//        //数据库表映射到实体的命名策略 驼峰命名
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        //生成实体时去掉表前缀
//        strategy.setTablePrefix("_");
//        //数据库表字段映射到实体的命名策略
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        // lombok 模型 @Accessors(chain = true) setter链式操作
//        strategy.setEntityLombokModel(true);
//        //restful api风格控制器
//        strategy.setRestControllerStyle(true);
//        //url中驼峰转连字符
//        strategy.setControllerMappingHyphenStyle(true);
//        mpg.setStrategy(strategy);
//
//        // 6、执行
//        mpg.execute();
//    }
//
//}
