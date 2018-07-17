package com.jiaoke.bean;

import lombok.Data;
/*
 * 该类用于返回所有云麻点
 */
@Data
public class CityLocation {

	private int id;
	private int citycode;
	private double latitude;
	private double longitude;
	private int prostatus;
	private String proname;
	private String promessage;

}
