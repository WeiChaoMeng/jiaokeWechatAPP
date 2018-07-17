package com.jiaoke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaoke.bean.CityCode;
import com.jiaoke.bean.CityLocation;
import com.jiaoke.dao.LocationDao;

@Service
public class LocationImp implements LocationInf {

	@Autowired
	LocationDao locationDao;
	
	@Override
	public List<CityLocation> getLocation() {
		
		return locationDao.getLocation();
	}

	@Override
	public List<CityCode> getcityLocalion(int citycode) {
		return locationDao.getcityLocalion(citycode);
	}

	@Override
	public List<CityLocation> getProject(int prostatus) {
		return locationDao.getProject(prostatus);
	}

	@Override
	public CityLocation getProMessage(Integer valueOf) {
		return locationDao.getProMessage(valueOf);
	}



}
