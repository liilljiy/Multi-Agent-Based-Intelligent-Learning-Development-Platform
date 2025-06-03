package com.ruanko.pojo.entity;


/**
 * Title: User
 * Description:用户pojo类
 * Version:1.0.0
 */
public class User<user> {
    /**
     * 编号
     */
    private Integer id;
    /**
     * 姓名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 年龄
     */
    private Integer age;

    /**
     * 获取编号
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置编号
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置姓名
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取年龄
     *
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @paramage
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
