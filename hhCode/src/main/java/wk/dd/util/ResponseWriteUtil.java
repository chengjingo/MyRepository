package wk.dd.util;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import wk.dd.split.CodeDemo;

public class ResponseWriteUtil {
	/** 
	 * 以字符流的方式，将json字符串输出到客户端
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void writeJson(HttpServletResponse response, Object jsonStr)throws Exception {
		//设置编码和 返回json的格式text/html:JsonReslut默认返回给浏览器的content-type类型是：application/json
		response.setContentType("text/html;charset=utf-8");  
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(jsonStr);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				out.close();
			}

		}
	}
	
	public static void getInfo(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("1", "11");
		map.put("2", "22");
		map.put("3", "22");
		map.put("4", "22");
		map.put("5", "22");
		map.put("6", "22");
		map.put("7", "22");
		map.put("8", "22");
		map.put("9", "22");
		map.put("10", "22");
		map.put("11", "22");
		int flag = 0;
		int size = map.size();
		for(Entry<String, String> entry : map.entrySet()){
			flag++;
			size--;
			String code = entry.getKey();
			String codeAddress = entry.getValue();
			System.out.println(code+"---"+codeAddress);
			/**
			 * 这里写放入指定文件夹中的代码
			 */
			if((flag==10&&size!=0)||size==0){
				/**
				 * 这里写打包的代码
				 */
				System.out.println(flag+"----"+size);
				flag = 0; //复0
				System.out.println(1);
			}
		}
		
		Map<String,String> typeMap =  new HashMap<String,String>();
		typeMap.put("02", "96");
		typeMap.put("03", "38");
		typeMap.put("04", "57");
		
		
		
	}
}
