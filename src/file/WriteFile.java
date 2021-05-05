package file;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import file.CreateFile;

public class WriteFile {

	public WriteFile() {
		// TODO Auto-generated constructor stub
	}

	public static File writeStringIntoFile(String str,File file) {
		if(file.exists()&&file!=null) {
			try {
				FileWriter fw=new FileWriter(file,true);
			    //FileWriter fw=new FileWriter(file,true);	//append为true，则追加字符数据到file文件中
				fw.write(str+"\n");		//Linux系统下，追加数据到文件末尾并换行，换行即在欲追加字符数据的基础上再添加一个"\n"字符。
				//fw.write(str+"\n");		//MacOS系统下，追加数据到文件末尾并换行，早期换行符为"\r"，现在也是"\n"。
				//fw.write(str+"\r\n");		//Windows系统下，追加数据到文件末尾并换行。
				fw.flush();		//清空流
				fw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("The given file '"+file.toString()+"' do not exist or is null");
		}
		return file;
	}

	public static File writeStringIntoFile(String str,String fileWithPath) {
		File file=CreateFile.CreateFileByPath(fileWithPath);
		if(file.exists()&&file!=null) {
			try {
				FileWriter fw=new FileWriter(file,true);
			    //FileWriter fw=new FileWriter(file,true);	//append为true，则追加字符数据到file文件中
				fw.write(str+"\n");		//Linux系统下，追加数据到文件末尾并换行，换行即在欲追加字符数据的基础上再添加一个"\n"字符。
				//fw.write(str+"\n");		//MacOS系统下，追加数据到文件末尾并换行，早期换行符为"\r"，现在也是"\n"。
				//fw.write(str+"\r\n");		//Windows系统下，追加数据到文件末尾并换行。
				fw.flush();		//清空流
				fw.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("The given file '"+file.toString()+"' do not exist or is null");
		}
		return file;
	}

}
