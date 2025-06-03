package com.ruanko.pojo.entity;
/**
 * 学生持久化类
 */
public class Student {
	protected Integer stuno;       // 主键学号
	private String stuname; // 学生姓名
	private Integer grade;		// 年级
	private String dept;      // 系部
	private String classname;     // 班级名称
	private Integer classId;     // 班级ID
	public Integer getStuno() {
		return stuno;
	}
	public void setStuno(Integer stuno) {
		this.stuno = stuno;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Override
	public String toString() {
		return "Student{" +
				"stuno=" + stuno +
				", stuname='" + stuname + '\'' +
				", grade=" + grade +
				", dept='" + dept + '\'' +
				", classname='" + classname + '\'' +
				", classId='" + classId + '\'' +
				'}';
	}

}