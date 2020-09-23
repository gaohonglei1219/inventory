package com.admin.util;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by Administrator on 2015/9/1.
 */
public class FormResponse extends Response {
    private static final long serialVersionUID = 282985055346144170L;
    private Map<String, String> errors = Maps.newHashMap();

    public FormResponse() {
    }

    public Map<String, String> getErrors() {
        return this.errors;
    }

    public void addError(String name, String Message) {
        this.errors.put(name, Message);
    }

    public void addErrors(Map<String, String> errors) {
        errors.putAll(errors);
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
