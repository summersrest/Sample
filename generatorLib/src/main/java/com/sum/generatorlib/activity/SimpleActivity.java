package com.sum.generatorlib.activity;


import com.sum.generatorlib.Generator;
import com.sum.generatorlib.utils.TemplateUtils;
import com.sum.generatorlib.utils.ToolUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Template;

/**
 * @author liujiang
 * created at: 2021/10/20 11:05
 * Desc: 生成简单的Activity
 */
public class SimpleActivity {

    public void generator(String temp) throws Exception {
        //包名
        String basePackage = Generator.BASE_PACKAGE;
        //应用代码路径 (application)
        String appPath = Generator.PATH_APP;
        //模块代码目录
        String path = Generator.PATH;
        //注释
        String desc = Generator.DESC;
        //Activity文件目录
        String activityPath = Generator.ACTIVITY_PATH;
        //Activity名字
        String activityName = Generator.ACTIVITY_NAME;

        Template template = new TemplateUtils().getTemplate(temp);

        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        //基础包名
        root.put("basePackage", basePackage);
        //拼接类引入包路径
        String packageImport = ToolUtils.getImportPackage(basePackage, appPath, path, activityPath);
        root.put("packageImport", packageImport);
        root.put("activityName", activityName);
        root.put("bindingName", Generator.BINGING_NAME_ACTIVITY);
        root.put("desc", desc);

        //activity生成文件路径
        String filePath = ToolUtils.getFilePath(basePackage, appPath, path, activityPath);
        File file = new File(filePath, activityName + ".java");
        boolean success = file.getParentFile().mkdirs();
        Writer writer = new FileWriter(file);
        try {
            template.process(root, writer);
            writer.flush();
            System.out.println("Written " + file.getCanonicalPath());
        } finally {
            writer.close();
        }

    }

}
