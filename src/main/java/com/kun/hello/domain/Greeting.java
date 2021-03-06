package com.kun.hello.domain;

/**
 * Created by jzhangkun on 23/06/2017.
 */
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id).append(" ").append(content);
        return builder.toString();
    }
}
