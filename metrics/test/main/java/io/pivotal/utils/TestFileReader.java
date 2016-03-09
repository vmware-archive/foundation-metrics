package io.pivotal.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import org.cloudfoundry.client.lib.util.JsonUtil;

public class TestFileReader {
	
	private String fileContents;

	public TestFileReader getFileContents(String fileName) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(
				new InputStreamReader(TestFileReader.class.getClassLoader().getResourceAsStream(fileName)));
		String s = null;
		try {
			while(br.ready() && (s = br.readLine()) != null) {
				sb.append(s);
			}
		} catch (IOException e) {
		}
		
		fileContents = sb.toString();
		
		return this;
	}
	
	public Map<String, Object> getResponseMap() {
		Map<String, Object> respMap = JsonUtil.convertJsonToMap(fileContents);
		return respMap;
	}
}
