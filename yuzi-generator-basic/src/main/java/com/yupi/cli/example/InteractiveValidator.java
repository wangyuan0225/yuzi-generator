package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangy
 */
public class InteractiveValidator {
    /**
     * 验证并补充交互式选项
     * @param command 命令对象
     * @param args 原始参数数组
     * @return 补充后的参数数组
     */
    public static String[] validateAndAddInteractiveOptions(Object command, String[] args) {
        List<String> argList = new ArrayList<>(Arrays.asList(args));

        // 获取所有字段
        Field[] fields = command.getClass().getDeclaredFields();

        for (Field field : fields) {
            Option option = field.getAnnotation(Option.class);
            if (option != null && option.interactive()) {
                // 获取选项的所有可能名称
                String[] optionNames = option.names();
                boolean hasOption = false;

                // 如果没有找到选项，添加首个选项名
                if (!hasOption) {
                    argList.add(optionNames[0]);
                }
            }
        }

        return argList.toArray(new String[0]);
    }
}