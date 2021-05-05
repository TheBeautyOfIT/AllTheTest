package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {

	public CreateFile() {
		// TODO Auto-generated constructor stub
	}

	public static File CreateFileByPath(String fileWithPath) {
		//StringBuffer fileBuff=new StringBuffer();
		File file=new File(fileWithPath);
		String fileState="OldFile";
		if(!file.exists()) {
			fileState="NewCreated";
			File fileParent=file.getParentFile();
			if(!fileParent.exists()){	//若欲创建文件的目录不存在，则创建之
				//fileParent.mkdir();		//根据抽象路径创建目录，若指定的路径中包含不存在的父目录，则会创建失败
				System.out.println("We will create the parent directories that new file:'"+fileWithPath+"' needs.");
				fileParent.mkdirs();		//根据抽象路径创建目录，包括创建不存在的父目录
				try {
					file.createNewFile();		//需抛出异常或捕获异常
				}catch(IOException e) {
					System.out.println("An error occurred while creating a new file创建新文件时出现了错误！");
					e.printStackTrace();
				}
				System.out.println("The directory of the file to be created has been established, and the path of the file to be created is欲创建文件的目录已建立，欲创建文件的路径为："+fileWithPath);
			}
		}else {
			System.out.println(file.getName()+" is already exists.");
		}
		/*try {	//异常处理，若欲创建文件的父目录完整存在，则开始新建文件
			FileWriter fw=new FileWriter(fileWithPath,true);	//FileWriter就是牛，如果文件名 的文件不存在，则先创建再读写；;存在的话直接追加写,关键字true表示追加。
			System.out.println("");
			//fw.write("");		//向文件中写入指定的字符串
			fw.close();		//关闭写文件,每次仅仅写一行数据，因为一个读文件中仅仅一个唯一的oid。
		}catch(IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return file;
	}

	public static File CreateFileByPath(String parentDir,String child) {
		/*若parentDir指定的文件目录不存在，则创建parentDir指定的文件目录（即使不存在），同时在parentDir文件目录下创建以child命名的文件
		 *若parentDir指定的文件目录存在，则将child视作文件名，创建child指定的文件*/
		
		File file=new File(parentDir,child);		
		/*The “java.io.File.File(String parent, String child)” method will creates a new File instance from a parent pathname stringand a child pathname string. 
		*If parent is null then the new File instance is created as if by invoking thesingle-argument File constructor on the given child pathname string.*/ 
		
		if(file.exists()){
			System.out.println(file.getName()+" is already exists.");
			System.out.println(file.getName()+" under directory:'"+file.getAbsolutePath()+"' is with the length "+file.length());
			//System.out.println(file.getName());
			//System.out.println(file.length());
		}else{
			System.out.println("We will create the parent directories that new file:'"+child+"' needs.");
			file.getParentFile().mkdirs();		//根据抽象路径创建目录，包括创建不存在的父目录
			try {
				file.createNewFile();
			} catch(IOException e) {
				// TODO: handle exception
				System.out.println("创建新文件目录或新文件时出错！");
				e.printStackTrace();
			}
		}
		return file;
	}

}
