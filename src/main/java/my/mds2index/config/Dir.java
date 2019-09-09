package my.mds2index.config;

import java.util.List;

/*
 * 文件夹层级
 */
public class Dir {
	private List<Dir> children; //文件夹中的文件夹或文件
	//private TreeSet<String> file;
	private String dirName;//文件夹或文件名
	/**
	 * 文件类型 表示文件夹或文件名(0表示问价夹，1表示文件,其他表示不存在)
	 */
	private int fileType=2;//文件类型
	
	private int permissions;//文件权限
	
	private String path;//文件路径
	
	private int state = 0; //路径状态是否存在(存在为0，不存在为1)
	
	private int hierarchy = -1;
	

	
	public List<Dir> getChildren() {
		return children;
	}

	public void setChildren(List<Dir> children) {
		this.children = children;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public int getPermissions() {
		return permissions;
	}

	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	

	public int getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(int hierarchy) {
		this.hierarchy = hierarchy;
	}

	
	@Override
	public String toString() {
		return "Dir [children=" + children + ", dirName=" + dirName + ", fileType=" + fileType + ", permissions="
				+ permissions + ", path=" + path + ", state=" + state + ", hierarchy=" + hierarchy + "]";
	}

	
	
}