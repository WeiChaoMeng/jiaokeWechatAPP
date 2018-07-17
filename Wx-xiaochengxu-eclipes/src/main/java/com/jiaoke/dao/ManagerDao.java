package com.jiaoke.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiaoke.bean.AddCity;
import com.jiaoke.bean.City;
import com.jiaoke.bean.CityLocation;
import com.jiaoke.bean.IndexCity;
import com.jiaoke.bean.IndexHeard;
import com.jiaoke.bean.IndexLocation;
import com.jiaoke.bean.ProStatus;

public interface ManagerDao {

	Boolean getUser(@Param("username") String username, @Param("password") String password);

	IndexHeard getIndexHeard();

	List<IndexCity> getIndexCity();

	List<IndexLocation> getIndexProject();

	List<ProStatus> getProStatus();

	List<City> getAllCity();

	int addLocation(CityLocation cityLocations);

	int addCity(AddCity addCity);

}
