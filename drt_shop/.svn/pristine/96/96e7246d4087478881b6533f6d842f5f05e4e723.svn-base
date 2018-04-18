package com.csg.intshop.controller.mall;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.csg.intshop.javacommon.SystemConstants;
import com.csg.intshop.util.ResultMapHelper;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest req,HttpServletResponse res, Exception e) {
		String requestType=req.getHeader("X-Requested-With");
		if(StringUtils.isNotBlank(requestType)){
			try {
				Map<String,Object> resultMap=ResultMapHelper.result(SystemConstants.ERROR_STATUS_500, "哎呦，出错了！！");
			    String json=JSONObject.fromObject(resultMap).toString();
			    res.setCharacterEncoding("UTF-8");
			    res.setContentType("application/json;charset=utf-8");
			    PrintWriter write;
				write = res.getWriter();
				write.write(json);
				write.flush();
				write.close();
				return null;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}		
		return"redirect:/pages/error/error.html";
    }
	
}
