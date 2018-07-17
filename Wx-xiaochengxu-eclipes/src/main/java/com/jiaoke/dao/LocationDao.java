package com.jiaoke.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiaoke.bean.CityCode;
import com.jiaoke.bean.CityLocation;

public interface LocationDao {
	
	
	List<CityLocation> getLocation();

	List<CityCode> getcityLocalion(int citycode);

	List<CityLocation> getProject(int prostatus);

	CityLocation getProMessage(@Param("valueOf") int valueOf);


	
}
