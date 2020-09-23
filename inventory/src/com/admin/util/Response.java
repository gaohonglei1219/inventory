package com.admin.util;

import com.google.common.base.Strings;

import java.io.Serializable;

/**
 * 返回标识
 * Created by Administrator on 2015/9/1.
 */
public class Response implements Serializable {
        public static final long SUCCESS = 0L;
        public static final long FAILURE = 1L;
        public static final long NOT_FOUND = 404L;
        private static final long serialVersionUID = 5403139346949689896L;
        private long errorCode = 0L;
        private long id = 0L;
        private String description = "";
        private String redirect = "";
        private String message = "";
        private String str = "";//new
        private Object obj = "";

        public Response() {
        }

        public long getId() {
            return this.id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getErrorCode() {
            return this.errorCode;
        }

        public void setErrorCode(long errorCode) {
            this.errorCode = errorCode;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDescription(String description) {
            if(!Strings.isNullOrEmpty(description)) {
                this.description = description;
            }

        }

        public String getRedirect() {
            return this.redirect;
        }

        public void setRedirect(String redirect) {
            if(!Strings.isNullOrEmpty(redirect)) {
                this.redirect = redirect;
            }

        }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if(!Strings.isNullOrEmpty(message)) {
            this.message = message;
        }
    }

    //new
    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        if(!Strings.isNullOrEmpty(str)) {
            this.str = str;
        }
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}

