package com.yupi.maker.generator.main;

public class MainGenerator extends GenerateTemplate {
    @Override
    protected void buildDist(String outputPath, String jarPath, String shellOutputFilePath, String sourceCopyDestPath) {
        System.out.println("不要给我输出 dist 啦！");
    }
}
