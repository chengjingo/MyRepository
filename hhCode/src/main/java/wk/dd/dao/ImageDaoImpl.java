package wk.dd.dao;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import wk.dd.domain.Image;


@Repository
public class ImageDaoImpl extends SqlSessionDaoSupport implements ImageDao {

	// mybatis-spring 1.0无需此方法；mybatis-spring1.2必须注入。
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public int insertInfo(Image image) {
		this.getSqlSession().insert("ty_image.insertInfo", image);
		Image i = (Image) this.getSqlSession().selectOne("ty_image.findBybase");
		Blob blobXML = (Blob) i.getBase64();
        OutputStream ops = null;
        try {  
            byte[] data = (byte[])image.getBase64();
            ops = blobXML.setBinaryStream(0);
            //data = "insertXml".getBytes();
            ops.write(data);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if(ops!=null){  
                    ops.close();  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }
        return 1;
	}
	
	public Image findMyImage(String imageId) {
		Image image = (Image) this.getSqlSession().selectOne("ty_image.findById",imageId);
		//String ss  = (String) image.getBase64();
		//System.out.println("dao===="+ss);
		return image;
	}

}
