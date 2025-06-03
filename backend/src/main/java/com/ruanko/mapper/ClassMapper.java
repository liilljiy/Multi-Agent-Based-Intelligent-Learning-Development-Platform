package com.ruanko.mapper;

import com.ruanko.pojo.entity.Clazz;
import com.ruanko.pojo.vo.ClazzVO;

public interface ClassMapper {

	ClazzVO findClazzVOById(int id);
	Clazz findClassById(int id);
}
