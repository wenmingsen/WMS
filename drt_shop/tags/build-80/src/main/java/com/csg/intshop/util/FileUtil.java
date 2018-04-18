package com.csg.intshop.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 文件操作工具类工具类
 * Company: Syni
 * @version 1.0
 * @author 李达才
 * @since 2017-12-01
 */
public class FileUtil {

	public static List<String[]> readFile(String pathname,String split, String charsetName){
		List<String[]> list = new ArrayList<String[]>();
		try {
			File filename = new File(pathname);
			InputStreamReader reader;
			reader = new InputStreamReader(  
			        new FileInputStream(filename),charsetName);
			BufferedReader br = new BufferedReader(reader);
			String line = br.readLine();  
			while (line != null) {  
				if(!line.trim().isEmpty()){
					String[] strs = line.split(split);
					list.add(strs);
				}
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
	
	public synchronized static void witerFile(String realPath,String fileAllPath,List<String[]> list,String split,String charset, boolean append){
		try{
			File parentFile = new File(realPath);
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			File file = new File(fileAllPath);
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append),charset));  
			StringBuffer sb = new StringBuffer();
			for(String[] strs :list){
				for(String str : strs){
					sb.append((str==null?"":str)+split);
				}
				sb.append("\n");
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
}
