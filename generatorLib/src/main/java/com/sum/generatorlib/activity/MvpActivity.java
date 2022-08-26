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
 * created at: 2021/10/22 13:17
 * Desc: 生成Activity
 */
public class MvpActivity {


    public void generator(String temp) throws Exception {
        Template template = new TemplateUtils().getTemplate(temp);
        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        //基础包名
        root.put("basePackage", Generator.BASE_PACKAGE);
        //拼接类引入包路径
        String packageImport = ToolUtils.getImportPackage(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.ACTIVITY_PATH);
        root.put("packageImport", packageImport);
        //presenter导包路径
        String presenterImport = ToolUtils.getImportPackage(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.PRESENTER_PATH) + "." + Generator.PRESENTER_NAME;
        root.put("presenterImport", presenterImport);
        //view导包路径
        String viewImport = ToolUtils.getImportPackage(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.VIEW_PATH) + "." + Generator.VIEW_NAME;
        root.put("viewImport", viewImport);
        //binding名称
        root.put("bindingName", Generator.BINGING_NAME_ACTIVITY);
        root.put("activityName", Generator.ACTIVITY_NAME);
        root.put("presenterName", Generator.PRESENTER_NAME);
        root.put("viewName", Generator.VIEW_NAME);
        root.put("desc", Generator.DESC);
        //文件路径
        String filePath = ToolUtils.getFilePath(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.ACTIVITY_PATH);
        File file = new File(filePath, Generator.ACTIVITY_NAME + ".java");
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
