package my.mds2index.test;

import java.io.File;

import com.alibaba.fastjson.JSONObject;

import my.mds2index.config.Dir;
import my.mds2index.utils.FilesUtils;

public class file {
	public static void main(String[] args) {
		//List<String> list = FilesUtils.getAllFileNames("E:\\gitregistry");
		Dir dir = FilesUtils.getAllDirName(new File("E:\\gitregistry"));
		JSONObject jsonObj = (JSONObject) JSONObject.toJSON(dir);
		System.out.println(jsonObj);
		System.out.println(jsonObj.toJSONString());
		
		//FilesUtils.getDirName(dir);
		
		//System.out.println();
	}
}
