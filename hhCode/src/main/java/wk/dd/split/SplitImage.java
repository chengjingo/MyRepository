package wk.dd.split;

import java.io.IOException;

import wk.dd.util.ResponseWriteUtil;
import wk.dd.util.SplitUtil;

public class SplitImage {
	public static void main(String[] args) {
		/*
		try {
			SplitUtil.splitImage();
			SplitUtil.mergeImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		//ResponseWriteUtil.getInfo();
		String s = "111111111111111";
		Long l = 111111111111L;
		String a = String.format("%015d", l);
		System.out.println(a);
		System.out.println(s.length());
	}
}
