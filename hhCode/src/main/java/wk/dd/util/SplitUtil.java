package wk.dd.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SplitUtil {
	
	/**
	 * 分割图片
	 * @throws IOException
	 */
	public static void splitImage() throws IOException {
        String originalImg = "C:\\Users\\Public\\Pictures\\Sample Pictures\\1.jpg"; 
        
      
        // 读入大图  
        File file = new File(originalImg);  
        FileInputStream fis = new FileInputStream(file);  
        BufferedImage image = ImageIO.read(fis);  
      
        // 分割成4*4(16)个小图  
        int rows = 4;  
        int cols = 4;  
        int chunks = rows * cols;  
      
        // 计算每个小图的宽度和高度  
        int chunkWidth = image.getWidth() / cols;  
        int chunkHeight = image.getHeight() / rows;  
      
        int count = 0;  
        BufferedImage imgs[] = new BufferedImage[chunks];  
        for (int x = 0; x < rows; x++) {  
            for (int y = 0; y < cols; y++) {  
                //设置小图的大小和类型  
                imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());  
      
                //写入图像内容  
                Graphics2D gr = imgs[count++].createGraphics();  
                gr.drawImage(image, 0, 0,   
                        chunkWidth, chunkHeight,   
                        chunkWidth* y, chunkHeight * x,   
                        chunkWidth * y + chunkWidth,  
                        chunkHeight * x + chunkHeight, null);  
                gr.dispose();  
            }  
        }  
      
        // 输出小图  
        for (int i = 0; i < imgs.length; i++) {  
            ImageIO.write(imgs[i], "jpg", new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\" + i + ".jpg"));  
            
        }  
        System.out.println("完成分割！");  
    }
	
	/**
	 * 拼接图片
	 * @throws IOException
	 */
	public static void mergeImage() throws IOException {  
        
        int rows = 4;  
        int cols = 4;  
        int chunks = rows * cols;  
      
        int chunkWidth, chunkHeight;  
        int type;  
      
        //读入小图  
        File[] imgFiles = new File[chunks];  
        for (int i = 0; i < chunks; i++) {  
            imgFiles[i] = new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\" + i + ".jpg");  
        }  
      
        //创建BufferedImage  
        BufferedImage[] buffImages = new BufferedImage[chunks];  
        for (int i = 0; i < chunks; i++) {  
            buffImages[i] = ImageIO.read(imgFiles[i]);  
        }  
        type = buffImages[0].getType();  
        chunkWidth = buffImages[0].getWidth();  
        chunkHeight = buffImages[0].getHeight();  
      
        //设置拼接后图的大小和类型  
        BufferedImage finalImg = new BufferedImage(chunkWidth * cols, chunkHeight * rows, type);  
       //http://10.1.174.3/svn/tpl-wblr/trunk/
        //写入图像内容  
        int num = 0;  
        for (int i = 0; i < rows; i++) {  
            for (int j = 0; j < cols; j++) {  
                finalImg.createGraphics().drawImage(buffImages[num], chunkWidth * j, chunkHeight * i, null);  
                num++;  
            }  
        }
      
        //输出拼接后的图像  
        ImageIO.write(finalImg, "jpeg", new File("C:\\Users\\Administrator\\Desktop\\新建文件夹\\finalImg.jpg"));  
      
        System.out.println("完成拼接！");  
    }  
	
}
