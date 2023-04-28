package com.pug.generator.tag;

import freemarker.template.TemplateModelException;

public class TmParameterMustBooleanException extends TemplateModelException {

    private static final long serialVersionUID = 1L;

    public TmParameterMustBooleanException(String paramName) {
        super("[Exmay Freemarker Parameter Exception]��The \"" + paramName + "\" parameter must be a boolean.");
    }
}