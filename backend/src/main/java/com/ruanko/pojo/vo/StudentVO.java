package com.ruanko.pojo.vo;

import com.ruanko.pojo.entity.Clazz;
import com.ruanko.pojo.entity.Student;

/**
 * 学生持久化类
 */
public class StudentVO extends Student {
	private Clazz clazz;     // 班级实体

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	@Override
	public String toString() {
		return "StudentVO{" +
				"stuno=" + getStuno() +
				", stuname='" + getStuname() + '\'' +
				", grade=" + getGrade() +
				", dept='" + getDept() + '\'' +
				", classname='" + getClassname() + '\'' +
				", clazz=" + clazz +
				'}';
	}
}