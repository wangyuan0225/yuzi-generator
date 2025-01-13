package com.yupi.generator;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.PrettyPrinter;
import com.yupi.model.MainTemplateConfig;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class MainGenerator {
    /**
     * 生成代码
     *
     * @param model 模型对象
     * @throws TemplateException 模板异常
     * @throws IOException       IO 异常
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "yuzi-generator-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;

        StaticGenerator.copyFilesByHutool(inputPath, outputPath);

        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = projectPath + File.separator + "acm-template/src/com/yupi/acm/MainTemplate.java";
        DynamicGenerator.doGenerate(inputDynamicFilePath, outputDynamicFilePath, model);
        // 格式化 Java 文件
        javaFileFormatter(outputDynamicFilePath);
    }

    /**
     * 格式化 Java 文件
     *
     * @param filePath Java 文件路径
     * @throws IOException IO 异常
     */
    public static void javaFileFormatter(String filePath) throws IOException {
        // 读取文件内容
        Path path = Paths.get(filePath);
        String sourceCode = new String(Files.readAllBytes(path));

        // 解析为 CompilationUnit
        CompilationUnit compilationUnit = StaticJavaParser.parse(sourceCode);

//        // 配置格式化选项
//        PrettyPrinterConfiguration config = new PrettyPrinterConfiguration();
//        config.setIndentSize(4); // 设置缩进为 4 个空格
//        config.setPrintComments(true); // 保留注释

        // 使用 PrettyPrinter 生成格式化后的代码
        PrettyPrinter printer = new PrettyPrinter();
        String formattedCode = printer.print(compilationUnit);

        // 将格式化后的代码写回文件
        Files.write(path, formattedCode.getBytes());
        System.out.println("文件已格式化成功！");
    }

    public static void main(String[] args) {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        mainTemplateConfig.setAuthor("yupi");
        mainTemplateConfig.setLoop(false);
        mainTemplateConfig.setOutputText("求和结果：");

        try {
            doGenerate(mainTemplateConfig);
        } catch (TemplateException e) {
            log.error("模板异常", e);
        } catch (IOException e) {
            log.error("IO 异常", e);
        }
    }
}
