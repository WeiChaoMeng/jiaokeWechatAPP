package com.jiaoke.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiaoke.bean.AddCity;
import com.jiaoke.bean.City;
import com.jiaoke.bean.CityLocation;
import com.jiaoke.bean.IndexCity;
import com.jiaoke.bean.IndexHeard;
import com.jiaoke.bean.IndexLocation;
import com.jiaoke.bean.ProStatus;
import com.jiaoke.dao.ManagerDao;

@Service
public class ManagerIpm implements ManagerInf {

	@Autowired
	ManagerDao managerDao;

	@Override
	public Boolean getUser(String username, String password) {
		return managerDao.getUser(username,password);
	}

	@Override
	public IndexHeard getIndexHeard() {
		return managerDao.getIndexHeard();
	}

	@Override
	public List<IndexCity> getIndexCity() {
		return managerDao.getIndexCity();
	}

	@Override
	public List<IndexLocation> getIndexProject() {
		return managerDao.getIndexProject();
	}

	@Override
	public List<ProStatus> getProStatus() {
		return managerDao.getProStatus();
	}

	@Override
	public List<City> getAllCity() {
		return managerDao.getAllCity();
	}

	@Override
	public int addLocation(CityLocation cityLocations) {
		return managerDao.addLocation(cityLocations);
	}

	@Override
	public int addCity(AddCity addCity) {
		return managerDao.addCity(addCity);
	}


}
