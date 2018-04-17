package com.csg.statistics.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 文件操作工具类工具类
 * Company: Syni
 * @version 1.0
 * @author 李达才
 * @since 2017-12-01
 */
public class FileUtil {

	public static List<Map<Integer,String>> readFile(String pathname,String split, String charsetName){
		List<Map<Integer,String>> list = new ArrayList<Map<Integer,String>>();
		Map<Integer,String> map = null;
		try {
			File filename = new File(pathname);
			InputStreamReader reader;
			reader = new InputStreamReader(  
			        new FileInputStream(filename),charsetName);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();  
			while (line != null) {  
				map = new HashMap<Integer,String>();
				String[] strs = line.split(split);
				for(int i=0;i<strs.length;i++){
					map.put(i, strs[i]);
				}
				list.add(map);
				line = br.readLine();
			}  
			reader.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Map<Integer,String>> readInputStream(InputStream inputStream,String split, String charsetName){
		List<Map<Integer,String>> list = new ArrayList<Map<Integer,String>>();
		Map<Integer,String> map = null;
		try {
			InputStreamReader reader = new InputStreamReader(inputStream, charsetName);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();  
			while (line != null) {  
				map = new HashMap<Integer,String>();
				String[] strs = line.split(split);
				for(int i=0;i<strs.length;i++){
					map.put(i, strs[i]);
				}
				list.add(map);
				line = br.readLine();
			}  
			reader.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public synchronized static void witerFile(String pathname,List<String> list,List<Map<String,Object>> data,String split,String charset, boolean append){
		try{
			File file = new File(pathname);
			if(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append),charset));  
			StringBuffer sb = new StringBuffer();
			for(Map<String,Object> map :data){
				for(String str : list){
					sb.append((map.get(str)==null?"":map.get(str))+split);
				}
				sb.append("\r\n");
			}
			out.write(sb.toString());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String witerToString(List<String> list,List<Map<String,Object>> data,String split){
		StringBuffer sb = new StringBuffer();
		for(Map<String,Object> map :data){
			for(String str : list){
				sb.append((map.get(str)==null?"":map.get(str))+split);
			}
			sb.append("\r\n");
		}
		return sb.toString();
	}
}
