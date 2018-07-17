package com.jiaoke.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class LocationUtil {
	
	private static final String Key ="3c2dbf98d968e2a3a458237529dd6ea0";

	public static Map<String, String> getAreaLongAndDimen(String addr){
		
		Map<String, String> map = new HashMap<>();
		

		HttpURLConnection con = null;
		InputStream inputStream = null;
		
		try {
			URL addrUrl = new URL("http://restapi.amap.com/v3/geocode/geo?address="+ addr +"&output=JSON&key="+Key);
			con = (HttpURLConnection)addrUrl.openConnection();
			con.setRequestMethod("GET");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.connect();
			
			inputStream = con.getInputStream();
			JsonNode jsonNode = new ObjectMapper().readTree(inputStream);
			

			if (jsonNode.findValue("geocodes").size() > 0 ) {
				
			    String[] degree = jsonNode.findValue("geocodes").findValue("location").textValue().split(",");
				
			    map.put("longitude", degree[0]);
			    map.put("dimension", degree[1]);
			    
			}else {
				
			    map.put("longitude", "0");
			    map.put("dimension", "0");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	 return map;
	}

}