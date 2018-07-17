package com.jiaoke.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiaoke.bean.CityCode;
import com.jiaoke.bean.CityLocation;
import com.jiaoke.service.LocationInf;

@RestController
public class LocationController {
	
	@Autowired
	LocationInf locationInf;
	
	/**
	 * 进入小程序时显示所有云点
	 * @return
	 */
	@RequestMapping("/getAllLocation.do")
	public String getLocation() {
		
		String Locationjson = "";
		
		ObjectMapper mapper = new ObjectMapper();
		
		List<CityLocation> location = locationInf.getLocation();
		
		try {
			 Locationjson = mapper.writeValueAsString(location);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return Locationjson;
		
	};
	
	/**
	 * 获取城市码后返回该城市所有云点
	 * @param citycode
	 * @return
	 */
	@RequestMapping("/getcityLocation.do")
	public String getcityLocalion(@RequestParam("citycode") Integer citycode ) {
		
		System.out.println(citycode);
		
		StringBuffer citylistjson = new StringBuffer(100);
		
		ObjectMapper mapper = new ObjectMapper();
		
		if (citycode != null  ) {
			
			//数据库id从1开始，而小程序从0开始，so 加了一
			citycode += 1;
		
			List<CityCode> citylist = locationInf.getcityLocalion(citycode);
			try {
				//放入该城市所有坐标包括城市定位坐标
				citylistjson.append(mapper.writeValueAsString(citylist));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		System.out.println(citylistjson.toString());
		return citylistjson.toString();
	}
	
	/**
	 * 根据不同的工程状态显示云点
	 * 
	 */
	
	@RequestMapping("/getProjiect.do")
	public String getProject(@RequestParam("prostatus") Integer prostatus  ) {
		
		StringBuffer projectJson = new StringBuffer(100);
		
		ObjectMapper mapper = new ObjectMapper();
		
		if (prostatus != null) {
			try {
				projectJson.append(mapper.writeValueAsString(locationInf.getProject(prostatus)));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		return projectJson.toString();
	}

	@RequestMapping("/getProMessage.do")
	public String getProMessage( @RequestParam("markerId") String makerId  ) {
		

		
		StringBuffer projectJson = new StringBuffer(100);
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, String> map =new HashMap<>();
		
		if (makerId != null) {
			
			CityLocation cityLocation = locationInf.getProMessage(Integer.valueOf(makerId));
			
			if (null != cityLocation) {
				
				try {
					map.put("message", cityLocation.getPromessage());
					projectJson.append(mapper.writeValueAsString(map));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}else {
				
				try {
					map.put("message", "工程信息获取失败");
					projectJson.append(mapper.writeValueAsString(map));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
			}
			
		}else {
			try {
				map.put("message", "获取工程失败");
				projectJson.append(mapper.writeValueAsString(map));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		System.out.println(projectJson.toString());
		return projectJson.toString();
	}
}
