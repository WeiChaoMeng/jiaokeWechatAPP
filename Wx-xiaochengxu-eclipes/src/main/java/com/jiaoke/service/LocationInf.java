package com.jiaoke.service;

import java.util.List;

import com.jiaoke.bean.CityCode;
import com.jiaoke.bean.CityLocation;

public interface LocationInf {

	//查询返回所有坐标
	List<CityLocation> getLocation();
	//根据城市代码返回该城云点
	List<CityCode> getcityLocalion(int citycode);
	//根据工程状态选取云点
	List<CityLocation> getProject(int prostatus);
	//根据点击云点获取工程信息
	CityLocation getProMessage(Integer valueOf);

	
}
