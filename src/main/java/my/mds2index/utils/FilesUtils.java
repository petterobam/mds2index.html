package my.mds2index.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import my.mds2index.config.Dir;
import my.mds2index.config.Mds2indexConfig;

/**
 * FilesUtil
 *
 * @author robin
 * @author refactor by Sevan Joe
 */
public class FilesUtils {

	/**
	 * read text file content, return string split by "\n"
	 *
	 * @param filePathAndName
	 *            String file name with absolute path
	 *
	 * @return String text content
	 */
	public static String readAll(String filePathAndName) {
		return readAll(filePathAndName, "UTF-8");
	}

	/**
	 * read text file content, return string split by "\n"
	 *
	 * @param filePathAndName
	 *            String file name with absolute path
	 * @param encoding
	 *            String file encoding
	 *
	 * @return String text content
	 */
	public static String readAll(String filePathAndName, String encoding) {
		String string = "";
		StringBuilder stringBuilder = new StringBuilder("");
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(URLDecoder.decode(filePathAndName, encoding));
			InputStreamReader inputStreamReader;
			if ("".equals(encoding)) {
				inputStreamReader = new InputStreamReader(fileInputStream);
			} else {
				inputStreamReader = new InputStreamReader(fileInputStream, encoding);
			}
			try {
				String data;
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				while ((data = bufferedReader.readLine()) != null) {
					stringBuilder.append(data).append("\n");
				}
				bufferedReader.close();

			} catch (Exception e) {
				return "";
			}
			string = stringBuilder.toString();
		} catch (IOException es) {
			string = "";
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}

	/**
	 * read the specified line content of text file
	 *
	 * @param filePathAndName
	 *            String file name with absolute path
	 * @param encoding
	 *            String file encoding
	 *
	 * @return String text content of the line
	 */
	public static String readLine(String filePathAndName, long lineIndex, String encoding) {
		String string = "";
		StringBuilder stringBuilder = new StringBuilder("");
		long i = 0;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(URLDecoder.decode(filePathAndName, encoding));
			InputStreamReader inputStreamReader;
			if ("".equals(encoding)) {
				inputStreamReader = new InputStreamReader(fileInputStream);
			} else {
				inputStreamReader = new InputStreamReader(fileInputStream, encoding);
			}
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			try {
				String data;
				while ((data = bufferedReader.readLine()) != null) {
					if (lineIndex == i) {
						stringBuilder.append(data);
						break;
					} else {
						i++;
					}
				}
				// 进行流的关闭
				bufferedReader.close();
			} catch (Exception e) {
				return "";
			}
			string = stringBuilder.toString();
		} catch (IOException es) {
			return "";
		} finally {
			try {
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return string;
	}

	/**
	 * read the first line content of text file
	 *
	 * @param filePathAndName
	 *            String file name with absolute path
	 *
	 * @return String text content of the first line
	 */
	public static String readLine(String filePathAndName) {
		return readLine(filePathAndName, 0, "UTF-8");
	}

	/**
	 * read the specified line content of text file
	 *
	 * @param filePathAndName
	 *            String file name with absolute path
	 *
	 * @return String text content of the line
	 */
	public static String readLine(String filePathAndName, long rowIndex) {
		return readLine(filePathAndName, rowIndex, "UTF-8");
	}

	/**
	 * create file
	 *
	 * @param filePathAndName
	 *            String file path and name
	 * @param fileContent
	 *            String file content
	 *
	 * @return boolean flag to indicate create success or not
	 */
	public static boolean newFile(String filePathAndName, String fileContent) {
		return newFile(filePathAndName, fileContent, false);
	}

	/**
	 * create file
	 *
	 * @param filePathAndName
	 *            String file path and name
	 * @param fileContent
	 *            String file content
	 * @param flag
	 *            boolean flag to indicate is append, true to append, false to
	 *            create
	 *
	 * @return boolean flag to indicate create success or not
	 */
	public static boolean newFile(String filePathAndName, String fileContent, boolean flag) {
		try {
			File file = new File(filePathAndName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file, flag);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(fileContent);
			fileWriter.close();
			return true;
		} catch (Exception e) {
			System.out.println("create file failed");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * create file
	 *
	 * @param filePathAndName
	 *            String file path and name
	 * @param fileContent
	 *            String file content
	 *
	 * @return boolean flag to indicate create success or not
	 */
	public static boolean newFileUtf8(String filePathAndName, String fileContent) {
		try {
			File file = new File(filePathAndName);
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(filePathAndName);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			osw.write(fileContent);
			osw.flush();
			// 进行流的关闭
			osw.close();
			return true;
		} catch (Exception e) {
			System.out.println("create file failed");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * create file with specified encoding
	 *
	 * @param filePathAndName
	 *            String file path and name
	 * @param fileContent
	 *            String file content
	 * @param encoding
	 *            the specified encoding, such as GBK or UTF-8
	 *
	 * @return boolean flag to indicate create success or not
	 */
	public static boolean newFile(String filePathAndName, String fileContent, String encoding) {
		try {
			File file = new File(filePathAndName);
			if (!file.exists()) {
				file.createNewFile();
			}
			PrintWriter printWriter = new PrintWriter(file, encoding);
			printWriter.println(fileContent);
			printWriter.close();
			return true;
		} catch (Exception e) {
			System.out.println("create file failed");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * delete file
	 *
	 * @param filePathAndName
	 *            String file path and name
	 */
	public static boolean delFile(String filePathAndName) {
		try {
			File file = new File(filePathAndName);
			return file.delete();
		} catch (Exception e) {
			System.out.println("delete file failed");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * create folder
	 *
	 * @param folderPath
	 *            String folder path
	 *
	 * @return String created folder path
	 */
	public static String newFolder(String folderPath) {
		String filePath = folderPath;
		try {
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("create folder failed");
			filePath = "";
			e.printStackTrace();
		}
		return filePath;
	}

	/**
	 * delete folder
	 *
	 * @param folderPath
	 *            String folder path
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // delete all files inside
			File file = new File(folderPath);
			file.delete(); // delete the empty folder
		} catch (Exception e) {
			System.out.println("delete folder failed");
			e.printStackTrace();
		}
	}

	/**
	 * delete all files inside folder
	 *
	 * @param path
	 *            String folder path
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		if (file.getAbsolutePath().equalsIgnoreCase("/")) {
			System.out.println("this is a root directory, you cannot delete all files in it!");
			System.out.println("please change the path!");
			return;
		}
		if (file.getAbsolutePath().equalsIgnoreCase("/root")) {
			System.out.println("this is a root directory, you cannot delete all files in it!");
			System.out.println("please change the path!");
			return;
		}
		if (file.getAbsolutePath().equalsIgnoreCase("/usr") || file.getAbsolutePath().equalsIgnoreCase("/opt")
				|| file.getAbsolutePath().equalsIgnoreCase("/bin") || file.getAbsolutePath().equalsIgnoreCase("/sbin")
				|| file.getAbsolutePath().equalsIgnoreCase("/etc")
				|| file.getAbsolutePath().equalsIgnoreCase("/selinux")
				|| file.getAbsolutePath().equalsIgnoreCase("/sys") || file.getAbsolutePath().equalsIgnoreCase("/var")
				|| file.getAbsolutePath().equalsIgnoreCase("/home")
				|| file.getAbsolutePath().equalsIgnoreCase("/net")) {
			System.out.println("this is a root directory, you cannot delete all files in it!");
			System.out.println("please change the path!");
			return;
		}
		if (file.getAbsolutePath().equalsIgnoreCase("C://") || file.getAbsolutePath().equalsIgnoreCase("C:\\\\")) {
			System.out.println("this is a root directory, you cannot delete all files in it!");
			System.out.println("please change the path!");
			return;
		}
		String[] tempList = file.list();
		File temp;
		if (tempList == null) {
			return;
		}
		for (String aTempList : tempList) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + aTempList);
			} else {
				temp = new File(path + File.separator + aTempList);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + aTempList);// delete all files inside
				delFolder(path + "/" + aTempList);// delete the empty folder
			}
		}
	}

	/**
	 * copy a file
	 *
	 * @param srcPath
	 *            String the source path
	 * @param dstPath
	 *            String the destination path
	 */
	public static void copyFile(String srcPath, String dstPath) {
		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			int byteRead;
			File srcFile = new File(srcPath);

			if (srcFile.exists()) { // file exists
				inputStream = new FileInputStream(srcPath); // read the source file
				fileOutputStream = new FileOutputStream(dstPath);
				byte[] buffer = new byte[1444];
				while ((byteRead = inputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, byteRead);
				}
			}
		} catch (Exception e) {
			System.out.println("copy file failed");
			e.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * copy a folder
	 *
	 * @param srcPath
	 *            String the source path
	 * @param dstPath
	 *            String the destination path
	 */
	public static void copyFolder(String srcPath, String dstPath) {

		try {
			(new File(dstPath)).mkdirs(); // if the folder does not exits, create it
			File file = new File(srcPath);
			String[] fileList = file.list();
			File tempFile;
			for (String fileName : fileList) {
				if (srcPath.endsWith(File.separator)) {
					tempFile = new File(srcPath + fileName);
				} else {
					tempFile = new File(srcPath + File.separator + fileName);
				}

				if (tempFile.isFile()) {
					FileInputStream fileInputStream = new FileInputStream(tempFile);
					FileOutputStream fileOutputStream = new FileOutputStream(dstPath + "/" + (tempFile.getName()));
					byte[] bytes = new byte[1024 * 5];
					int length;
					while ((length = fileInputStream.read(bytes)) != -1) {
						fileOutputStream.write(bytes, 0, length);
					}
					fileOutputStream.flush();
					fileOutputStream.close();
					fileInputStream.close();
				}
				if (tempFile.isDirectory()) { // it is a subdirectory
					copyFolder(srcPath + "/" + fileName, dstPath + "/" + fileName);
				}
			}
		} catch (Exception e) {
			System.out.println("copy folder failed");
			e.printStackTrace();
		}
	}

	/**
	 * move a file
	 *
	 * @param srcPath
	 *            String the source path
	 * @param dstPath
	 *            String the destination path
	 */
	public static void moveFile(String srcPath, String dstPath) {
		copyFile(srcPath, dstPath);
		delFile(srcPath);
	}

	/**
	 * move a folder
	 *
	 * @param srcPath
	 *            String the source path
	 * @param dstPath
	 *            String the destination path
	 */
	public static void moveFolder(String srcPath, String dstPath) {
		copyFolder(srcPath, dstPath);
		delFolder(srcPath);
	}

	/**
	 * create multi-level directory
	 *
	 * @param folderPath
	 *            the path to create multi-level directory
	 * @param paths
	 *            directories, split by '|'
	 *
	 * @return String the created directory path
	 */
	public static String createFolders(String folderPath, String paths) {
		String pathString;
		try {
			String path;
			pathString = folderPath;
			StringTokenizer stringTokenizer = new StringTokenizer(paths, "|");
			for (; stringTokenizer.hasMoreTokens();) {
				path = stringTokenizer.nextToken();
				if (pathString.lastIndexOf("/") != -1) {
					pathString = newFolder(pathString + path);
				} else {
					pathString = newFolder(pathString + path + "/");
				}
			}
		} catch (Exception e) {
			System.out.println("create multi-level directory failed");
			pathString = "";
			e.printStackTrace();
		}
		return pathString;
	}

	/**
	 * 检查文件夹并创建
	 *
	 * @param absoultOutputFilePath
	 *
	 * @return
	 */
	public static boolean checkFolderAndCreate(String absoultOutputFilePath) {
		int index = absoultOutputFilePath.lastIndexOf("/");
		if (index > 0) {
			String dirPath = absoultOutputFilePath.substring(0, index);
			File file = new File(dirPath);
			int i = 0;
			while (!file.exists()) {
				file.mkdirs();
				if (i++ > 100) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * create multi-level directory
	 *
	 * @param folderPath
	 *            the multi-level directory to create
	 *
	 * @return String the created directory path
	 */
	public static String createFolders(String folderPath) {
		String pathString = folderPath;
		try {
			File file = new File(pathString);
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception e) {
			System.out.println("create multi-level directory failed");
			pathString = "";
			e.printStackTrace();
		}
		return pathString;
	}

	/**
	 * check if the specified file exists
	 *
	 * @param fileName
	 *            the name of the file to be checked
	 *
	 * @return boolean true if exits, false if not
	 */
	public static boolean isFileExist(String fileName) {
		return new File(fileName).isFile();
	}

	/**
	 * get all files in a folder
	 *
	 * @param path
	 *            String folder path
	 *
	 * @return List<File>
	 */
	public static List<File> getAllFiles(String path) {
		List<File> fileList = new ArrayList<File>();
		File file = new File(path);
		if (!file.exists()) {
			return fileList;
		}
		if (!file.isDirectory()) {
			return fileList;
		}
		String[] tempList = file.list();
		File tempFile;
		for (String fileName : tempList) {
			if (path.endsWith(File.separator)) {
				tempFile = new File(path + fileName);
			} else {
				tempFile = new File(path + File.separator + fileName);
			}
			if (tempFile.isFile()) {
				fileList.add(tempFile);
			}
			if (tempFile.isDirectory()) {
				List<File> allFiles = getAllFiles(tempFile.getAbsolutePath());
				fileList.addAll(allFiles);
			}
		}
		return fileList;
	}

	/**
	 * get all files with specified suffix in a folder
	 *
	 * @param path
	 *            String folder path
	 * @param suffix
	 *            String the specified suffix
	 *
	 * @return List<File>
	 */
	public static List<File> getAllFiles(String path, String suffix) {
		List<File> fileList = new ArrayList<File>();
		File file = new File(path);
		if (!file.exists()) {
			return fileList;
		}
		if (!file.isDirectory()) {
			return fileList;
		}
		String[] tempList = file.list();
		File tempFile;
		for (String fileName : tempList) {
			if (path.endsWith(File.separator)) {
				tempFile = new File(path + fileName);
			} else {
				tempFile = new File(path + File.separator + fileName);
			}
			if (tempFile.isFile()) {
				if (suffix == null || "".equals(suffix)) {
					fileList.add(tempFile);
				} else {
					String filePath = tempFile.getAbsolutePath();
					if (!suffix.equals("")) {
						int beginIndex = filePath.lastIndexOf("."); // the last '.' index before suffix
						String tempSuffix;

						if (beginIndex != -1) {
							tempSuffix = filePath.substring(beginIndex + 1, filePath.length());
							if (tempSuffix.equals(suffix)) {
								fileList.add(tempFile);
							}
						}
					}
				}
			}
			if (tempFile.isDirectory()) {
				List<File> allFiles = getAllFiles(tempFile.getAbsolutePath(), suffix);
				fileList.addAll(allFiles);
			}
		}
		return fileList;
	}

	/**
	 * get all names of file with specified suffix in a folder
	 *
	 * @param path
	 *            String folder path
	 * @param suffix
	 *            String the specified suffix
	 * @param isDepth
	 *            boolean is need to scan all subdirectories
	 *
	 * @return List<String>
	 */
	public static List<String> getAllFileNames(String path, String suffix, boolean isDepth) {
		List<String> fileNamesList = new ArrayList<String>();
		File file = new File(path);
		return listFileName(fileNamesList, file, suffix, isDepth);
	}

	private static List<String> listFileName(List<String> fileNamesList, File file, String suffix, boolean isDepth) {
		// if is directory, scan all subdirectories by recursion
		if (file.isDirectory()) {
			File[] fileList = file.listFiles();

			if (fileList != null) {
				for (File tempFile : fileList) {
					if (isDepth || tempFile.isFile()) {
						listFileName(fileNamesList, tempFile, suffix, isDepth);
					}
				}
			}
		} else {
			String filePath = file.getAbsolutePath();
			if (!suffix.equals("")) {
				int begIndex = filePath.lastIndexOf("."); // the last '.' index before suffix
				String tempSuffix;

				if (begIndex != -1) {
					tempSuffix = filePath.substring(begIndex + 1, filePath.length());
					if (tempSuffix.equals(suffix)) {
						fileNamesList.add(filePath);
					}
				}
			} else {
				fileNamesList.add(filePath);
			}
		}
		return fileNamesList;
	}

	/**
	 * get all file names in a folder
	 *
	 * @param path
	 *            String folder path
	 *
	 * @return List<String>
	 */
	public static List<String> getAllFileNames(String path) {
		List<String> fileNamesList = new ArrayList<String>();
		File file = new File(path);
		if (!file.exists()) {
			return fileNamesList;
		}
		if (!file.isDirectory()) {
			return fileNamesList;
		}
		String[] tempList = file.list();
		File tempFile;
		for (String fileName : tempList) {
			if (path.endsWith(File.separator)) {
				tempFile = new File(path + fileName);
			} else {
				tempFile = new File(path + File.separator + fileName);
			}
			if (tempFile.isFile()) {
				fileNamesList.add(tempFile.getName());
			}
		}
		return fileNamesList;
	}

	/**
	 * get all file names in a folder
	 *
	 * @param path
	 *            String folder path
	 *
	 * @return Map<String, String>
	 */
	public static Map<String, String> getAllFileNamesByMap(String path) {
		Map<String, String> fileNamesMap = new HashMap<String, String>();
		File file = new File(path);
		if (!file.exists()) {
			return fileNamesMap;
		}
		if (!file.isDirectory()) {
			return fileNamesMap;
		}
		String[] tempList = file.list();
		File tempFile;
		for (String fileName : tempList) {
			if (path.endsWith(File.separator)) {
				tempFile = new File(path + fileName);
			} else {
				tempFile = new File(path + File.separator + fileName);
			}
			if (tempFile.isFile()) {
				fileNamesMap.put(tempFile.getName(), tempFile.getName());
			}
		}
		return fileNamesMap;
	}

	/**
	 * get all file names in a folder
	 *
	 * @param path
	 *            String folder path
	 *
	 * @return String[]
	 */
	public static String[] getAllFileNamesByPath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return null;
		}
		if (!file.isDirectory()) {
			return null;
		}
		String[] tempList = file.list();
		List<String> fileList = new ArrayList<String>();
		File tempFile;
		for (String fileName : tempList) {
			if (path.endsWith(File.separator)) {
				tempFile = new File(path + fileName);
			} else {
				tempFile = new File(path + File.separator + fileName);
			}
			if (tempFile.isFile()) {
				fileList.add(tempFile.getName());
			}
		}
		return fileList.toArray(new String[fileList.size()]);
	}

	/**
	 * remove suffix of a file
	 *
	 * @param fileName
	 *            file name
	 *
	 * @return String file name without suffix
	 */
	public static String getNameNoSuffix(String fileName) {
		if (fileName.lastIndexOf(".") >= 0) {
			return fileName.substring(0, fileName.lastIndexOf("."));
		} else {
			return fileName;
		}
	}

	/**
	 * return file name with suffix
	 *
	 * @param fileName
	 *            file path and name
	 *
	 * @return String file name with suffix
	 */
	public static String getFileName(String fileName) {
		String shortFileName = fileName;
		shortFileName = shortFileName.replace("\\", "/");
		if (shortFileName.contains("/")) {
			shortFileName = shortFileName.substring(shortFileName.lastIndexOf("/") + 1, shortFileName.length());
		}
		return shortFileName;
	}

	/**
	 * check if directory exists, if not exist, create it, return false if create
	 * failed
	 *
	 * @param path
	 *            folder path
	 *
	 * @return boolean
	 */
	public static boolean isExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			boolean isSuccess = file.mkdir();
			if (!isSuccess) {
				createFolders(path);
			}
			return isSuccess;
		} else {
			return true;
		}
	}

	/**
	 * check if directory exists
	 *
	 * @param path
	 *            folder path
	 *
	 * @return boolean
	 */
	public static boolean isExistNotCreate(String path) {
		File file = new File(path);
		return file.exists();
	}

	/**
	 * copy a file
	 *
	 * @param fileName
	 *            file name
	 * @param srcPath
	 *            source path
	 * @param dstPath
	 *            destination path
	 *
	 * @return boolean
	 */
	public boolean copyTheFile(String fileName, String srcPath, String dstPath) {
		boolean isSucceed = false;

		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			int byteRead;
			File srcFile = new File(srcPath + "/" + fileName);
			File dstFile = new File(dstPath);
			if (!dstFile.exists()) {
				dstFile.mkdirs();
			}
			if (!srcFile.exists()) {
				throw new Exception("the file to copy do not exist");
			}
			if (srcFile.exists()) {
				inputStream = new FileInputStream(srcPath + "/" + fileName);
				fileOutputStream = new FileOutputStream(dstPath + "/" + fileName);
				byte[] buffer = new byte[1444];
				while ((byteRead = inputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, byteRead);
				}
			}
			isSucceed = true;
		} catch (Exception e) {
			System.out.println("copy file failed");
			e.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isSucceed;
	}

	/**
	 * move a file
	 *
	 * @param fileName
	 *            file name
	 * @param srcPath
	 *            source path
	 * @param dstPath
	 *            destination path
	 *
	 * @return boolean
	 */
	public boolean moveTheFile(String fileName, String srcPath, String dstPath) {
		boolean isSucceed = false;

		InputStream inputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			int byteRead;
			File srcFile = new File(srcPath + "/" + fileName);
			File dstFile = new File(dstPath);
			if (!dstFile.exists()) {
				dstFile.mkdirs();
			}
			if (!srcFile.exists()) {
				throw new Exception("the file to move do not exist");
			}
			if (srcFile.exists()) {
				inputStream = new FileInputStream(srcPath + "/" + fileName);
				fileOutputStream = new FileOutputStream(dstPath + "/" + fileName);
				byte[] buffer = new byte[1444];
				while ((byteRead = inputStream.read(buffer)) != -1) {
					fileOutputStream.write(buffer, 0, byteRead);
				}
			}
			isSucceed = true;
		} catch (Exception e) {
			System.out.println("move file failed");
			e.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			File deleteFile = new File(srcPath + "/" + fileName);
			if (isSucceed) {
				isSucceed = deleteFile.delete();
			}
		}
		return isSucceed;
	}

	/**
	 * 获取目录结构信息 @Title: getDir @Description: TODO() @param: @return @return:
	 * Dir @throws
	 */
	public static Dir getAllDirName(File file) {
		// 根路径
		Dir dir = new Dir();
		dir.setFileType(0);
		// 设置文件夹的层级，0表示根路径

		List<Dir> listDir = new ArrayList<Dir>();
		String path = file.getPath();
		if (!file.exists()) {
			dir.setState(1);
			return dir;
		} else {
			dir.setDirName(file.getName());
			dir.setPath(path);
			File[] files = file.listFiles();

			if (files.length > 0) {
				for (File children : files) {
					Dir dir1 = new Dir();
					try {
						dir1.setDirName(children.getName());
						dir1.setPath(children.getPath());
						if (children.isDirectory()) {
							dir1 = getAllDirName(children);
						} else {
							dir1.setFileType(1);
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
	 * 获取层级目录文件名（打印输出效果） @Title: getDirName @Description: TODO() @param: @param
	 * dir @return: void @throws
	 */
	public static void getDirName(Dir dir) {
		System.out.println("文件夹路径:---------" + dir.getPath());
		System.out.println("文件夹名称:---------" + dir.getDirName());
		System.out.println("文件夹层级：---------" + dir.getHierarchy());
		System.out.println("文件夹类型:---------" + dir.getFileType());
		for (Dir children : dir.getChildren()) {
			try {
				// children.getPath();
				if (children.getFileType() == 0) {
					getDirName(children);
				} else {
					System.out.println("文件路径++++++++++++" + children.getPath());
					System.out.println("文件路径++++++++++++" + children.getDirName());
					System.out.println("文件层级++++++++++++" + children.getHierarchy());
					System.out.println("文件夹类型:++++++++++" + children.getFileType());
				}
			} catch (Exception e) {
				continue;
			}

		}

	}

	/**
	 * 使用递归根据md根路径和html根路径生成html文件 @Title: createHtmlByRootPath @Description:
	 * TODO(根据路径信息) @param: @param rootPath @param: @param
	 * filePath @param: @return @return: boolean @throws
	 */
	private static boolean createHtml(String rootPath, String filePath,String projectPath) {
		boolean flag = true;
		FilesUtils.newFolder(filePath);
		File file = new File(rootPath);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			for (File fileChildren : files) {// 遍历文件夹下的文件和文件夹
				if (fileChildren.isFile()) {// 如果得到的是文件，则生成HTML文件
					// 获取文件后缀
					String suffix = fileChildren.getName().substring(fileChildren.getName().lastIndexOf(".") + 1);

					if ("md".equals(suffix)) {// 如果是markdown文件
						// 读取html模板
						String tplPath = Mds2indexConfig.getMenuTplPath();
						System.out.println("模板路径：" + tplPath);
						System.out.println(">>>>>>>>>>>>>>>>>>>>读取模板文件...");
						String tplStr = FilesUtils.readAll(tplPath);
						// 根据html模板生成包含数据的html字符串
						System.out.println(">>>>>>>>>>>>>>>>>>>>解析渲染为带索引的单页面index.html内容...");
						String mdIndexHtml = Md2HtmlUtils.parsePageByParamSingleFile(tplStr, fileChildren.getPath(),
								projectPath);
						System.out.println(">>>>>>>>>>>>>>>>>>>>解析渲染为带索引的单页面index.html内容结束！！！");

						// 生成的html文件存放目录
						String childHtmlFilePath = filePath + File.separator + fileChildren.getName().replace(".md", ".html");
						System.out.println("生成文件路径" + childHtmlFilePath);
						newFileUtf8(childHtmlFilePath, mdIndexHtml);
					}
				} else {//

					createHtml(rootPath +File.separator+ fileChildren.getName(), filePath + File.separator + fileChildren.getName(),projectPath);

				}
			}

		} else {
			// 出错
			flag = false;
			System.out.println("转换文件" + filePath + "出错");
		}

		return flag;

	}

	/**
	 * 根据md根路径生成html @Title: createHtmlByRootPath @Description:
	 * TODO() @param: @param rootPath @param: @return @return: boolean @throws
	 */
	public static boolean createHtmlByRootPath(String rootPath) {
		// 拼接html存放根目录
		rootPath = new File(rootPath).getPath();
		if(rootPath.endsWith(File.separator)) {
			
		}else {
			
		}
		String filePath = rootPath + "html";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dstPath = rootPath + "html_back" + sdf.format(new Date());
		//备份原始目录
		FilesUtils.copyFolder(filePath, dstPath);
		//删除原始文件
		FilesUtils.delFolder(filePath);
		
		//FilesUtils.newFolder(filePath);
		// 调用生成html方法，生成文件
		return createHtml(rootPath, filePath,rootPath);

	}
	
	public static void cleanSortNumber(List<Dir> list) {
		for(Dir dir : list) {
			try {
				String dirName = dir.getDirName().split("_")[1];
				dir.setDirName(dirName);	
			}catch(Exception e){
				continue;
			}
		}
	}

}