package my.mds2index.test;

import java.io.File;
import my.mds2index.config.Dir;
import my.mds2index.utils.FilesUtils;

public class file {
	public static void main(String[] args) {
		//List<String> list = FilesUtils.getAllFileNames("E:\\gitregistry");
		Dir dir = FilesUtils.getAllDirName(new File("E:\\\\gitregistry"),0);
		//System.out.println(dir);
		
		FilesUtils.getDirName(dir);
		
		//System.out.println();
	}
}
