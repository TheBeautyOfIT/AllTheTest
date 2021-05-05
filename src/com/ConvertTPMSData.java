/**
 * Function: Transforming TPMS data into given location and given format.
 */
package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import file.ReadAllFileContentAndWriteIntoAnotherFile;
import file.ReadFileAsString;

/**
 * @author wxi
 *
 */
public class ConvertTPMSData {

	/**
	 * 
	 */
	public ConvertTPMSData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("测试starts：");
		String lpath="/data/TPMS";		//Linux system file path: lpath
		String wpath="D:\\log\\stat.txt";		//Windows system file path: wpath
		String sfilepath="D:\\log";
		String dfilename="D:\\tmp\\log\\writer1.txt";
		File file=new File(wpath);
		String output="";
		if(file.exists()) 
		{
			if(file.isFile()) 
			{
				System.out.println(file.getName()+" is a file!");
				try 
				{
					BufferedReader input=new BufferedReader(new FileReader(file));
					StringBuffer buffer=new StringBuffer();
					String text;
					//while((text=input.readLine())!=null)
					//while((text=input.readLine())!=null&&text.length()>0)
					while((text=input.readLine())!=null&&!(text==""))
						buffer.append(text+"\n");
					output=buffer.toString();
				}
				catch(IOException ioException) 
				{
					System.out.println("File error!");
				}
			}else if(file.isDirectory()) 
			{
				System.out.println(wpath+" is a Directory!");
				String[] dir=file.list();
				output+="Directory contents are as follow:\n";
				for(int i=0;i<dir.length;i++) 
					output+=dir[i]+"\n";
			}
		}
		else 
		{
			System.out.println("File doesn't exist!");
		}
		System.out.println(output);
		
		//Test special character.
		/*System.out.println("".length());
		System.out.println("\r".length());
		System.out.println("\n".length());
		System.out.println("\r\n".length());
		System.out.println(" ".length());
		System.out.println("	".length());	/*I type a character of "Tap" key on computer
		--press the "Tap" keys on the keyboard so that key it in.*/

		System.out.println();
		
		//System.out.println(ReadFileAsString.readFile(wpath));
		//System.out.println(ReadFileAsString.readFile("D:\\log"));
		
		//Create file
		String filename="rawTPMS.txt";
		File wfile=new File(filename); 
		try {
		    if(!wfile.exists()) {
		        wfile.createNewFile();
		    }
		    FileWriter resultFile = new FileWriter(wfile);
		    PrintWriter myFile = new PrintWriter(resultFile);
		    myFile.println(filename);
		    resultFile.close();
		}
		catch(Exception e) {
		    System.out.println("新建文件操作出错！");
		    e.printStackTrace();
		}
		System.out.println(wfile.getAbsolutePath());
		
		//System.out.println(ReadFileAsString.readFile(CreateFile.CreateFileByPath("D:\\tmp\\log","1.log")));
		//System.out.println(ReadFileAsString.readFile(CreateFile.CreateFileByPath("D:\\tmp\\log\\1.log")));
		
		//Read specified files and write all the file content into another file
		file=new File("D:\\tmp\\log\\writer.txt");
		file=ReadAllFileContentAndWriteIntoAnotherFile.readAllFileAndWriteIntoAnotherFile("D:\\log\\stat_log", file);
		file=ReadAllFileContentAndWriteIntoAnotherFile.readAllFileAndWriteIntoAnotherFile(sfilepath, dfilename);
		
		/*BufferedReader reader=null;
			try {
				//传入的是Reader的子类
				//reader=new BufferedReader(new FileReader("/data/TPMS/*.txt"));
				reader=new BufferedReader(new FileReader("D:\\tmp\\log\\writer.txt"));
				String line;
				//如果还有下一行就一直读下去，readLine()用于读取一行
				while((line=reader.readLine())!=null) {
					System.out.println(line);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(reader!=null) {
					try {
						reader.close();
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
			//高效缓冲输出流
			BufferedReader reader1=null;
			BufferedWriter writer=null;
			try {
				//创建高效缓冲输出流
				writer=new BufferedWriter(new FileWriter("D:\\tmp\\log\\writer.txt"));
				//创建高效缓冲输入流
				reader1=new BufferedReader(new FileReader("D:\\tmp\\log\\reader.txt"));
				String line;
				while((line=reader1.readLine())!=null) {
					writer.write(line);
					writer.newLine();
					writer.flush();
					System.out.println(line);
				}
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				try {
					if(reader1!=null) {
						reader1.close();
					}
					if(writer!=null) {
						writer.close();
					}
				}catch(IOException e) {
					e.printStackTrace();
				}
			}*/
			
			
			
			System.out.print("测试Ends.");

		}
}
/*public static String readFile(String fileName) {
	String output = "";
	File file = new File(fileName);
	if(file.exists()){
	    if(file.isFile()){
	        try{
	            BufferedReader input = new BufferedReader (new FileReader(file));
	            StringBuffer buffer = new StringBuffer();
	            String text;
	               
	            while((text = input.readLine()) != null)
	                buffer.append(text +"/n");
	               
	            output = buffer.toString();                    
	        }
	        catch(IOException ioException){
	            System.err.println("File Error!");
	        }
	    }
	    else if(file.isDirectory()){
	        String[] dir = file.list();
	        output += "Directory contents:/n";
	        
	        for(int i=0; i<dir.length; i++){
	            output += dir[i] +"/n";
	        }
	    }
	}
	else{
	    System.err.println("Does not exist!");
	}
	return output;
}*/
/*File[] tempList=file.listFiles();
for(int i=0;i<=tempList.length;i++) {
	if(tempList[i].isFile()) {
		//FileInputStream fis = new FileInputStream("fileName");
		//InputStreamReader isr = new InputStreamReader(fis,"utf-8");
		StringBuffer buffer=new StringBuffer();
		String text;
		BufferedReader input=new BufferedReader (new FileReader(tempList[i]));
		while((text=input.readLine()) != null)
			buffer.append(text +"/n"); }
		if (tempList[i].isDirectory()){
		System.out.println("文件夹："+tempList[i]);
		}*/
