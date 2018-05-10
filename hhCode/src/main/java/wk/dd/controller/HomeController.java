package wk.dd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/image.do")
	public String getImage(){
		return "image.jsp";
	}
	
	@RequestMapping("/test.do")
	public String getTest(){
		return "test.jsp";
	}
	
	@RequestMapping("/myImage.do")
	public String getMyImage(){
		return "myImage.jsp";
	}
	
}
