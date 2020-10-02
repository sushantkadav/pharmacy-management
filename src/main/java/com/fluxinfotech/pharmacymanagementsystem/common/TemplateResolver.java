package com.fluxinfotech.pharmacymanagementsystem.common;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
public class TemplateResolver {

    private final TemplateEngine templateEngine;
    private final TemplateEngine stringTemplateEngine;

    public TemplateResolver(TemplateEngine templateEngine, TemplateEngine stringTemplateEngine) {
        this.templateEngine = templateEngine;
        this.stringTemplateEngine = stringTemplateEngine;
    }

    public String resolveTemplate(String templateName, Map<String, Object> params) {
        return resolve(templateEngine, templateName, params);
    }

    public String resolveString(String stringContent, Map<String, Object> params) {
        return resolve(stringTemplateEngine, stringContent, params);
    }

    private String resolve(TemplateEngine templateEngine, String content, Map<String, Object> params) {
        try {
            Context ctx = new Context();
            params.forEach(ctx::setVariable);
            return templateEngine.process(content, ctx);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }

    }

}