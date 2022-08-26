package com.sum.generatorlib.mvp;

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
 * created at: 2021/10/22 11:29
 * Desc:
 */
public class MvpPresenter {

    public void generator(String temp) throws Exception {
        Template template = new TemplateUtils().getTemplate(temp);
        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        //基础包名
        root.put("basePackage", Generator.BASE_PACKAGE);
        //拼接类引入包路径
        String packageImport = ToolUtils.getImportPackage(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.PRESENTER_PATH);
        root.put("packageImport", packageImport);
        //model引入路径
        String modelImport = ToolUtils.getImportPackage(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.MODEL_PATH) + "." + Generator.MODEL_NAME;
        root.put("modelImport", modelImport);
        //view引入路径
        String viewImport = ToolUtils.getImportPackage(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.VIEW_PATH) + "." + Generator.VIEW_NAME;
        root.put("viewImport", viewImport);
        //presenter名字
        root.put("presenterName", Generator.PRESENTER_NAME);
        root.put("modelName", Generator.MODEL_NAME);
        root.put("viewName", Generator.VIEW_NAME);
        //注释
        root.put("desc", Generator.DESC);
        //文件生成路径
        String filePath = ToolUtils.getFilePath(Generator.BASE_PACKAGE, Generator.PATH_APP, Generator.PATH, Generator.PRESENTER_PATH);
        File file = new File(filePath, Generator.PRESENTER_NAME + ".java");
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
