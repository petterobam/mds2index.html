package my.mds2index.config;

import java.io.File;
import java.io.FileInputStream;
import java.lang.annotation.Documented;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.alibaba.fastjson.JSONObject;

import my.mds2index.test.file;
import my.mds2index.utils.FilesUtils;
import my.mds2index.utils.Md2HtmlUtils;

public class Mds2indexConfig {

	private static String currentConfigYmlPath;
	private static Map ymlInfo;

	static {
		loadConfig(null);
	}

	/**
	 * 从文件中载入生成markdown文件的配置
	 *
	 * @param ymlPath
	 */
	public static void loadConfig(String ymlPath) {
		try {
			if (Md2HtmlUtils.isBlank(ymlPath)) {
				System.out.println("----------------------加载默认配置文件内容开始--------------------------");
			} else {
				System.out.println("----------------------加载自定义配置文件内容开始--------------------------");
			}
			Yaml yaml = new Yaml();
			URL url = null;
			FileInputStream inputStream = null;
			if (Md2HtmlUtils.isBlank(ymlPath)) {
				url = Mds2indexConfig.class.getClassLoader().getResource("config.yml");
			} else {
				inputStream = new FileInputStream(ymlPath);
			}

			if (url != null) {
				currentConfigYmlPath = url.getPath();
				System.out.println(">>>>>>>>>>>初始化用默认配置文件,当前解析参照的配置文件路径：" + currentConfigYmlPath);
				// 获取test.yaml文件中的配置数据，然后将值转换为Map
				ymlInfo = (Map) yaml.load(new FileInputStream(url.getFile()));
				System.out.println(ymlInfo);
			} else if (null != inputStream) {
				currentConfigYmlPath = ymlPath;
				System.out.println(">>>>>>>>>>>自定义配置文件覆盖，当前解析参照的配置文件路径：" + currentConfigYmlPath);
				// 获取test.yaml文件中的配置数据，然后将值转换为Map
				ymlInfo = (Map) yaml.load(inputStream);
				System.out.println(ymlInfo);
			}
			System.out.println("----------------------加载配置文件内容结束--------------------------");
		} catch (Exception e) {
			if (Md2HtmlUtils.isBlank(ymlPath)) {
				System.out.println("----------------------加载默认配置文件内容失败--------------------------");
			} else {
				System.out.println("----------------------加载自定义配置文件内容失败--------------------------");
			}
			e.printStackTrace();
			ymlInfo = new HashMap();
		}
	}

	/**
	 * 获取当前解析
	 *
	 * @return
	 */
	public static String getConfigFilePath() {
		return currentConfigYmlPath;
	}

	/**
	 * 返回YML配置文件内容
	 *
	 * @return
	 */
	public static Map getYmlInfo() {
		return ymlInfo;
	}

	/**
	 * 根据键获取对象值
	 *
	 * @param keyStr
	 *
	 * @return
	 */
	public static <T> T get(String keyStr) {
		if ("page.article".equals(keyStr)) {
			return (T) getPageArticle();
		} else if ("page.nav".equals(keyStr)) {

		} else if ("nav.date".equals(keyStr)) {

		}
		if (keyStr == null || keyStr.trim().length() == 0) {
			return null;
		}
		try {
			String[] keyArr = keyStr.split("\\.");
			Object curObj = ymlInfo;
			for (String key : keyArr) {
				if (null == curObj) {
					return null;
				} else if (curObj instanceof Map) {
					curObj = ((Map) curObj).get(key);
				} else {
					return null;
				}
			}
			if (null != curObj) {
				return (T) curObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * 获取替换信息 @Title: getSingleFile @Description: TODO() @param: @param
	 * keyStr @param: @param filePath @param: @param
	 * rootPath @param: @return @return: T @throws
	 */
	public static <T> T getSingleFile(String keyStr, String filePath, String rootPath) {
		if ("page.navData".equals(keyStr)) {
			// 返回json数据作为目录信息JSON
			return (T) getMenuJsonString(filePath, rootPath);
		}else if ("page.article".equals(keyStr)) {
			return (T) getPageArticleSingleFile(filePath);
		}else {
			return get(keyStr);
		}

	}

	/**
	 * 根据键获取对象值
	 *
	 * @param keyStr
	 *
	 * @return
	 */
	public static <T> void put(String keyStr, T value) {
		try {
			String[] keyArr = keyStr.split("\\.");
			Object curObj = ymlInfo;
			int count = 1;
			for (String key : keyArr) {
				if (null == curObj) {
					return;
				} else if (curObj instanceof Map) {
					if (count++ == keyArr.length) {
						((Map) curObj).put(key, value);
					} else {
						curObj = ((Map) curObj).get(key);
					}
				} else {
					return;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取markdown所在的目录
	 *
	 * @return
	 */
	public static String getMarkdownBasePath() {
		String mdBasePath = Mds2indexConfig.get("page.mdBasePath");
		if (Md2HtmlUtils.isBlank(mdBasePath)) {
			mdBasePath = Mds2indexConfig.class.getResource("/markdowns/").getPath().toString();
		}
		if (mdBasePath == null) {
			return null;
		}
		if (!mdBasePath.endsWith("/")) {
			mdBasePath = mdBasePath + "/";
		}
		return mdBasePath;
	}

	/**
	 * 获取html模板所在的目录
	 *
	 * @return
	 */
	public static String getTplPath() {
		String tplPath = Mds2indexConfig.get("page.tplPath");
		if (Md2HtmlUtils.isBlank(tplPath)) {
			tplPath = Mds2indexConfig.class.getResource("/template/index.html").getPath().toString();
		}
		return tplPath;
	}

	/**
	 * 获取html模板所在的目录
	 *
	 * @return
	 */
	public static String getMenuTplPath() {
		String tplPath = Mds2indexConfig.get("page.tplPath");
		if (Md2HtmlUtils.isBlank(tplPath)) {
			tplPath = Mds2indexConfig.class.getResource("/template/index1.html").getPath().toString();
		}
		return tplPath;
	}

	/**
	 * 获取markdown文件要生成的文件所在的目录
	 *
	 * @return
	 */
	public static String getIndexPath() {
		String indexPath = Mds2indexConfig.get("page.indexPath");
		if (Md2HtmlUtils.isBlank(indexPath)) {
			indexPath = Mds2indexConfig.class.getResource("/output/").getPath().toString();
			return indexPath + "index.html";
		} else {
			return indexPath;
		}
	}

	/**
	 * 生成markdown文件要生成的文件所在的目录
	 *
	 * @return
	 */
	public static String getHtmlPath(String path) {

		String htmlPath = "";

		if (Md2HtmlUtils.isBlank(path)) {

		} else {
			File file = new File(path);
			if (file.exists() && file.isFile()) {

			}

		}

		return htmlPath;
	}

	/**
	 * 根据配置获取markdown文件转换后的html
	 *
	 * @return
	 */
	public static String getPageArticle() {
		List<String> mdNameList = get("page.markdowns");
		StringBuffer articleSb = new StringBuffer("");
		if (null != mdNameList) {
			String mdPath = getMarkdownBasePath();
			int count = 1;
			System.out.println("----------------------[批量转化markdown文件开始]---------------------------------");
			for (String mdName : mdNameList) {
				String mdPathAndName = mdPath + mdName;
				System.out.println("[" + count + "]正在读取并转化文件[" + mdPathAndName + "]...");
				String mdStr = FilesUtils.readAll(mdPathAndName);
				System.out.println("[" + count + "]读取文件[" + mdPathAndName + "]结束...");
				String mdHtml = Md2HtmlUtils.parseHtml(mdStr);
				System.out.println("[" + count + "]转化文件[" + mdPathAndName + "]结束...");
				articleSb.append(mdHtml);
				count++;
			}
			System.out.println("----------------------[批量转化markdown文件结束]---------------------------------");
		}
		return articleSb.toString();
	}

	/**
	 * 根据项目路径获取markdown文件转换后的html字符串
	 *
	 * @return
	 */
	public static String getPageArticleSingleFile(String filePath) {
		StringBuffer articleSb = new StringBuffer("");
		System.out.println("----------------------[批量转化markdown文件开始]---------------------------------");
		String mdPathAndName = filePath;
		System.out.println("正在读取并转化文件[" + mdPathAndName + "]...");
		String mdStr = FilesUtils.readAll(mdPathAndName);
		System.out.println("读取文件[" + mdPathAndName + "]结束...");
		String mdHtml = Md2HtmlUtils.parseHtml(mdStr);
		System.out.println("转化文件[" + mdPathAndName + "]结束...");
		articleSb.append(mdHtml);
		System.out.println("----------------------[批量转化markdown文件结束]---------------------------------");
		return articleSb.toString();
	}

	/**
	 * 返回json对象字符串
	 */
	public static String getMenuJsonString(String filePath, String rootPath) {

		//定义相对路径前缀
		String prefixPath = "";
		//根据路径进行文件夹层级的划分
		filePath = new File(filePath).getPath();
	    rootPath = new File(rootPath).getPath();
		String[] filePathArr = filePath.split(File.separator+File.separator);
		String[] rootPathArr = rootPath.split(File.separator+File.separator);
		
		//
		if(filePathArr.length>= rootPathArr.length) {
			int prefixLength = filePathArr.length - rootPathArr.length;
			for(int i=1;i<prefixLength;i++) {
				prefixPath = prefixPath  + ".." + File.separator;
			}	
		}
		
		
		Dir dir = getMenuJsonDir(new File(rootPath),prefixPath);
		JSONObject jsonObj = (JSONObject) JSONObject.toJSON(dir);
		
		
		return jsonObj.toJSONString();
	}

	// 根据主目录信息，递归得到目录结构
	public static Dir getMenuJsonDir(File file,String prefixPath) {
		// 根路径
		Dir dir = new Dir();
		dir.setDirName(file.getName());
		dir.setFileType(0);

		List<Dir> listDir = new ArrayList<Dir>();
		//String path = prefix + "#";
		if (!file.exists()) {
			dir.setState(1);
			return dir;
		} else {
			File[] files = file.listFiles();

			if (files.length > 0) {
				for (File children : files) {
					Dir dir1 = new Dir();
					String path="";
					try {
						
						if (children.isDirectory()) {
							dir1 = getMenuJsonDir(children,prefixPath + File.separator + children.getName());
							prefixPath = prefixPath + File.separator + children.getName();
							path = prefixPath;
							
						} else {
							// 获取文件后缀
							String suffix = children.getName().substring(children.getName().lastIndexOf(".") + 1);
							//获取文件名字
							String prefix = children.getName().substring(0,children.getName().lastIndexOf("."));
							
							if("md".equals(suffix)){
								if("".equals(prefixPath)) {
									path = prefixPath + File.separator + prefix + ".html";
								}else {
									path =prefix + ".html";
								}
								
								dir1.setPath(path);//设置文件相对路径
								dir1.setDirName(prefix);
								dir1.setFileType(1);
							}
							
							
						}
						listDir.add(dir1);
					} catch (Exception e) {
						continue;
					}

				}
				dir.setChildren(listDir);
			}

		}
		return dir;
	}

	/**
	 * 根据配置获取时间
	 *
	 * @return
	 */
	public static String getPageNav() {
		return "";
	}
}
