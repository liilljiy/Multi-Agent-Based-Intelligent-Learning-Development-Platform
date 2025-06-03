package com.ruanko.mapper;

import com.ruanko.pojo.entity.Student;
import com.ruanko.pojo.vo.StudentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

	StudentVO findStudentVOByStuno(int stuno);
	Student findStudentByClassId(int classId);

	Student findStudentByStuno(int stuno);
	List<Student> findStudentList();
	int insertStudent(Student stu);
	int updateStudent(Student stu);
	int deleteStudent(int stuno);
	//分页查询
	List<Student> findStudentPage(Student student,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);
}
