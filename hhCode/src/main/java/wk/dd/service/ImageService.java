package wk.dd.service;

import wk.dd.domain.Image;

public interface ImageService {
	/**
	 * 上传图片
	 * @param image
	 * @return
	 */
	public int insertInfo(Image image);
	
	/**
	 * 查询图片
	 * @param imageId
	 * @return
	 */
	public Image findMyImage(String imageId);
}
