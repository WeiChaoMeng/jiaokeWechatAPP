package com.jiaoke.bean;

import lombok.Data;
/*
 * 该类用于切换城市时返回带有云麻点与城市定位数据
 * 
 */
@Data
public class CityCode {
	
	private int id;
	private int citycode;
	private double latitude;
	private double longitude;
	private int prostatus;
	private String proname;
	private double cityLat;
	private double cityLng;
	
}
