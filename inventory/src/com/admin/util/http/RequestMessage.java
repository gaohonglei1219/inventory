package com.admin.util.http;

/**
 * Created by ${lrj} on 2016/8/31.
 * Don't ask me anything  I don't know anything  Don't  look   Find by Youself
 */

    public class RequestMessage<T> {
        private T requestContext;

        public T getRequestContext() {
            return requestContext;
        }

        public void setRequestContext(T requestContext) {
            this.requestContext = requestContext;
        }

    }
