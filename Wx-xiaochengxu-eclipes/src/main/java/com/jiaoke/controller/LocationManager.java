package com.jiaoke.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiaoke.bean.AddCity;
import com.jiaoke.bean.City;
import com.jiaoke.bean.CityLocation;
import com.jiaoke.bean.IndexCity;
import com.jiaoke.bean.IndexHeard;
import com.jiaoke.bean.IndexLocation;
import com.jiaoke.bean.ProStatus;
import com.jiaoke.service.ManagerInf;
import com.jiaoke.util.LocationUtil;

@RestController
public class LocationManager {
	
	@Autowired
	ManagerInf managerInf;
 	
	@RequestMapping(value="/login.do")
    public String login(HttpServletRequest request,HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password ) {
    	
		Map<String, String> map = new HashMap<>();
		
		String resoure = "";
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");

		
		if (username == null || password == null ) {
			try {
				resoure =  mapper.writeValueAsString(map.put("resource", "false" ));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else {
			
			try {
				map.put("resource",Boolean.toString(managerInf.getUser(username,password)) );
				resoure += mapper.writeValueAsString(map);
				System.out.println(resoure);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
		}
		
		return resoure;
    }
	
	//返回主页头部各项目数量
	@RequestMapping("/getIndexHeard.do")
	public String getIndexHeard(HttpServletRequest request,HttpServletResponse response) {
		
		String resoure = "";
		
		ObjectMapper mapper = new ObjectMapper();
		
		IndexHeard heard =  managerInf.getIndexHeard();
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		
		try {
			resoure += mapper.writeValueAsString(heard);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resoure;
	}
	
	//返回主页各城市项目数
	
	@RequestMapping("/getIndexCity.do")
	public String getIndexCity(HttpServletRequest request,HttpServletResponse response) {
		
		String resoure = "";
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		
		List<IndexCity>  indexCity  = managerInf.getIndexCity();
		
		try {
			resoure = mapper.writeValueAsString(indexCity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return resoure;
	}
	
	//获取所有工程
	@RequestMapping("/getIndexProject.do")
	public String getIndexProject(HttpServletRequest request,HttpServletResponse response) {
		
		String resoure = "";
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		
		List<IndexLocation> list = managerInf.getIndexProject();
		
		try {
			resoure = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return resoure;
		
	}
	

	//获取所有城市
	@RequestMapping("/getAllCity.do")
	public String getAllCity(HttpServletRequest request,HttpServletResponse response) {
		
		String resoure = "";
		
		ObjectMapper mapper = new ObjectMapper();
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		
		List<City> list = managerInf.getAllCity();
		
		try {
			resoure += mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return resoure;
		
	}
	
	
	//获取所有工程状态
	@RequestMapping("/getProStatus.do")
	public String getProStatus(HttpServletRequest request,HttpServletResponse response) {
			
		String resoure = "";
			
		ObjectMapper mapper = new ObjectMapper();
			
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
			
		List<ProStatus> list = managerInf.getProStatus();
			
		try {
			resoure = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
			
		return resoure;
			
	}
	
	//添加项目基本信息
	@RequestMapping("/addlocation.do")
	public String addLocation(String cityLocation,String proName,String proMessage,int proStatus, int cityName,HttpServletResponse response) {
		
		String resoure = "";
		
		CityLocation cityLocations = new CityLocation();
		
		ObjectMapper mapper = new ObjectMapper();
			
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		
		Map<String, String> map = new HashMap<>();
		
		if (cityLocation == null || proName == null || proMessage == null || proStatus == 0 || cityName == 0) {
			
			map.put("Message", "请输入相关项目信息");
			try {
				return mapper.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			
		}
		
		Map<String, String> mapLocation = LocationUtil.getAreaLongAndDimen(cityLocation);
		
		double longitude = Double.valueOf(mapLocation.get("longitude"));
		double latitude = Double.valueOf(mapLocation.get("dimension"));
		
		if (longitude == 0 || latitude == 0) {
			try {
				map.put("Message", "添加失败，请添加正确的地址");
				resoure = mapper.writeValueAsString(map);
				return resoure;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		cityLocations.setCitycode(Integer.valueOf(cityName));
		cityLocations.setPromessage(proMessage);
		cityLocations.setProname(proName);
		cityLocations.setProstatus(proStatus);
		cityLocations.setLatitude(latitude);
		cityLocations.setLongitude(longitude);
		
		
		int success = managerInf.addLocation(cityLocations);
		if (success > 0) {
			try {
				map.put("Message", "添加成功");
				resoure = mapper.writeValueAsString(map);
				
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			try {
				map.put("Message", "添加失败");
				resoure = mapper.writeValueAsString(map);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		return resoure;

	}
	
	@RequestMapping("/addCity.do")
	public String addCity(@RequestParam("cityName") String cityName ,HttpServletResponse response ) {
		
		String resoure = "";
		

		ObjectMapper mapper = new ObjectMapper();
			
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setCharacterEncoding("utf-8");
		
		Map<String, String> map = new HashMap<>();
		
		if (cityName == null) {
			map.put("message", "城市添加有误");
			try {
				resoure = mapper.writeValueAsString(map);
				return resoure;
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}else {
			
			Map<String, String> cityLocation = LocationUtil.getAreaLongAndDimen(cityName);
			
			double longitude = Double.valueOf(cityLocation.get("longitude"));
			double latitude = Double.valueOf(cityLocation.get("dimension"));

			if (longitude == 0 || latitude == 0) {
				
				map.put("message", "请添加正确的城市");
				try {
					resoure = mapper.writeValueAsString(map);
					return resoure;
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}else {
				
				AddCity addCity = new AddCity();
				addCity.setCityLat(latitude);
				addCity.setCityLng(longitude);
				addCity.setCityName(cityName);
				
				int success =managerInf.addCity(addCity);
				
				if (success > 0) {
					
					map.put("message", "添加城市成功");
					try {
						resoure = mapper.writeValueAsString(map);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}else {
					map.put("message", "添加城市失败");
					try {
						resoure = mapper.writeValueAsString(map);
					} catch (JsonProcessingException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		return resoure;
	}
	
}
