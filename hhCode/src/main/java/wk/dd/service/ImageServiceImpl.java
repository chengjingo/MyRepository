package wk.dd.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wk.dd.dao.ImageDao;
import wk.dd.domain.Image;

@Service("imageService")
public class ImageServiceImpl implements ImageService {
	
	@Resource
	ImageDao imageDao;
	
	public int insertInfo(Image image) {
		return this.imageDao.insertInfo(image);
	}

	public Image findMyImage(String imageId) {
		return this.imageDao.findMyImage(imageId);
	}
	
}
