package com.ruanko.pojo.entity;

public class Clazz {
    private Integer id;
    private String className;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", className='" + className + '\'' +
                '}';
    }
}
