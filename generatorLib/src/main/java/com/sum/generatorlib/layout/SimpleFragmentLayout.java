package com.sum.generatorlib.layout;

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
 * created at: 2021/10/20 15:30
 * Desc: 生成Fragment layout
 */
public class SimpleFragmentLayout {


    public void generator(String temp) throws Exception {
        String modular = Generator.MODULAR;
        Template template = new TemplateUtils().getTemplate(temp);
        // 创建数据模型
        Map<String, Object> root = new HashMap<>();
        //生成的layout名称
        String name = "fragment" + ToolUtils.camelToUnderline(modular) + ".xml";
        //生成的layout路径
        String path = System.getProperty("user.dir") + "\\app\\src\\main\\res\\layout";
        File file = new File(path, name);
        boolean success = file.getParentFile().mkdirs();
        try (Writer writer = new FileWriter(file)) {
            template.process(root, writer);
            writer.flush();
            System.out.println("Written " + file.getCanonicalPath());
        }
    }
}
