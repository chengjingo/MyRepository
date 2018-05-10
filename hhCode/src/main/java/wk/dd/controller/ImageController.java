package wk.dd.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.util.Date;
import oracle.sql.BLOB;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import wk.dd.domain.Image;
import wk.dd.service.ImageService;
import wk.dd.util.ResponseWriteUtil;

@Controller
@RequestMapping("/upload")
public class ImageController {
	
	@SuppressWarnings("restriction")
	@Resource
	ImageService imageService;

	@RequestMapping("/image.do")
	public String saveImage(@RequestParam CommonsMultipartFile[] file,HttpServletRequest request){
		//同时多个文件/图片上传
		for(int i=0;i<file.length;i++){
            System.out.println("fileName---------->" + file[i].getOriginalFilename());  
            if(!file[i].isEmpty()){  
                int pre = (int) System.currentTimeMillis();  
                try {  
                    //拿到输出流，同时重命名上传的文件  
                    FileOutputStream os = new FileOutputStream("E:/" + new Date().getTime() + file[i].getOriginalFilename());  
                    //拿到上传文件的输入流  
                    FileInputStream in = (FileInputStream) file[i].getInputStream();  
                      
                    //以写字节的方式写文件  
                    int b = 0;  
                    while((b=in.read()) != -1){  
                        os.write(b);  
                    }  
                    os.flush();  
                    os.close();  
                    in.close();  
                    int finaltime = (int) System.currentTimeMillis();  
                    System.out.println(finaltime - pre);
                      
                } catch (Exception e) {  
                    e.printStackTrace();  
                    System.out.println("上传出错");  
                }  
            }  
        }  
        return "test.jsp";
	}
	
	@RequestMapping("/test.do")
	public String saveTest(HttpServletRequest request,HttpServletResponse response){
		return null;
	}
	
    @RequestMapping("/file.do")
    public String test(@RequestParam MultipartFile file,HttpServletRequest request) throws IOException{  
        System.out.println("comming!");
        String path = request.getSession().getServletContext().getRealPath("/images");  
        System.out.println("path>>"+path);  
  
        String fileName = file.getOriginalFilename();  
        System.out.println("fileName>>"+fileName);  
          
        File dir = new File(path, fileName);  
        System.out.println("dir.exists()>>"+dir.exists());  
        if(!dir.exists()){ 
            dir.mkdirs();  
        }  
        System.out.println("dir.exists()>>"+dir.exists());  
//      MultipartFile自带的解析方法  
        file.transferTo(dir);  
        return "ok";
    }
    
    /**
     * 上传图片
     * @param base64
     * @param response
     * @return
     */
    @RequestMapping("/myImage.do")
    public String uploadMyImage(String base64,HttpServletResponse response){
    	//自定义返回前台数据格式  
    	JSONObject result = new JSONObject();
        //去掉base64数据头部data:image/png;base64,和尾部的” " “     
        String[] ww= base64.split(",");  
        base64 = ww[1];  
        String[] aa = base64.split("\"");  
        base64 = aa[0];  
        System.out.println("====="+base64);
        try {  
        	byte[] bytes = getStringImage(base64);
        	//将图片插入数据库  
        	Image image = new Image();
        	image.setBase64(bytes);
        	int resultTotal = imageService.insertInfo(image); 
            //图片保存到本地  
            //String path = "D:/asdfasdf.jpg";  
            //Base64File file = new Base64File();  
            //file.decoderBase64File(base64, path);  
            //成功标识  
        	if(resultTotal>0){
        		result.put("success", true);
        	}else{
        		result.put("success", false);  
        	}
        	ResponseWriteUtil.writeJson(response, result);
        } catch (Exception e) { 
        	e.printStackTrace();
        }
        return null;
    }
    /**
     * 查看图片
     * @param imageId
     * @param response
     * @return
     */
    @RequestMapping("findMyImage.do")
    public String findMyImage(String imageId,HttpServletResponse response){
    	/**
    	 * 
    	try {
    		JSONObject result = new JSONObject();
    		Image image = this.imageService.findMyImage(imageId);
    		java.sql.Blob blob = (Blob) image.getBase64();
    		byte[] bytes = blobToBytes(blob);
			String base64 = getImageString(bytes);
			System.out.println("======2"+base64);
			result.put("base64", base64);
			ResponseWriteUtil.writeJson(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	 */
    	String base64 ="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";
    	JSONObject result = new JSONObject();
    	result.put("base64", base64);
    	try {
			ResponseWriteUtil.writeJson(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }
    /**
     * Base64字符串转 二进制流
     *
     * @param base64String Base64
     * @return base64String
     * @throws IOException 异常
     */
    @SuppressWarnings("restriction")
	public byte[] getStringImage(String base64String) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return base64String != null ? decoder.decodeBuffer(base64String) : null;

    }
    
    /**
     * 二进制流转Base64字符串
     *
     * @param data 二进制流
     * @return data
     * @throws IOException 异常
     */
    @SuppressWarnings("restriction")
	public String getImageString(byte[] data) throws IOException {
        BASE64Encoder encoder = new BASE64Encoder();
        return data != null ? encoder.encode(data) : "";
    }
    
    /**  
     * 把Blob类型转换为byte数组类型  
     *   
     * @param blob  
     * @return  
     */  
    public static byte[] blobToBytes(Blob blob){
        BufferedInputStream is = null;  
        try {  
            is = new BufferedInputStream(blob.getBinaryStream());  
            byte[] bytes = new byte[(int) blob.length()];  
            int len = bytes.length;  
            int offset = 0;  
            int read = 0;  
            while (offset < len  
                    && (read = is.read(bytes, offset, len - offset)) >= 0) {  
                offset += read;  
            }  
            return bytes;  
        } catch (Exception e) {  
            return null;  
        } finally {  
            try {  
                is.close();  
                is = null;  
            } catch (IOException e) {  
                return null;  
            }  
        }  
    } 
}
