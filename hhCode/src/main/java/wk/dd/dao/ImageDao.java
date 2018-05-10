package wk.dd.dao;

import wk.dd.domain.Image;

public interface ImageDao {
	/**
	 * 上传图片
	 * @param object
	 * @return int
	 */
	public int insertInfo(Image image);
	
	/**
	 * 查询图片
	 * @param imageId
	 * @return
	 */
	public Image findMyImage(String imageId);
}
