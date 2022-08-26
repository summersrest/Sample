package com.sum.generatorlib.utils;

import java.io.File;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

/**
 * @author liujiang
 * created at: 2021/10/20 16:09
 * Desc:
 */
public class TemplateUtils {

    /**
     * 获取模板
     *
     * @param TEMPLATE
     * @return
     */
    public Template getTemplate(String TEMPLATE) throws Exception {
        Configuration config = new Configuration(Configuration.VERSION_2_3_23);
        config.setClassForTemplateLoading(getClass(), "/");
        try {
            config.getTemplate(TEMPLATE);
        } catch (TemplateNotFoundException e) {
            File dir = new File("src/main/resources/");
            if (dir.exists() && new File(dir, TEMPLATE).exists()) {
                config.setDirectoryForTemplateLoading(dir);
                config.getTemplate(TEMPLATE);
            } else {
                throw e;
            }
        }
        return config.getTemplate(TEMPLATE);
    }
}
