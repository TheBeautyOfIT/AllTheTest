package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileAsString {

	public ReadFileAsString() {
		// TODO Auto-generated constructor stub
	}
	public static String readFile(String filename) {
		
		/*若filename为目录，则读取目录下的文件名列表并返回
		 *若filename为文件，则读取文件内容 */
		
		File file=new File(filename);
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
					
					//while((text=input.readLine())!=null&&text.length()>0)		//Only save the string which is not null,"\n","\r" or "\r\n".
					//while((text=input.readLine())!=null&&!"".equals(text))	//Only save the string which is not null,"\n","\r" or "\r\n".
					//while((text=input.readLine())!=null&&!(text==""))		//Only save the string which is not "".
					while((text=input.readLine())!=null)		//Only save the string which is not null.
						buffer.append(text+"\n");
					output=buffer.toString();
				}
				catch(IOException ioException) 
				{
					System.out.println("File error!");
				}
			}else if(file.isDirectory()) 	//If given file name is a directory,list the file name in this directory.
			{
				System.out.println(filename+" is a Directory!");
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
		//System.out.println(output);
		return output;
	}
	public static String readFile(File file) {
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
					//while((text=input.readLine())!=null&&text.length()>0)		//Only save the string which is not null,"\n","\r" or "\r\n".
					//while((text=input.readLine())!=null&&!"".equals(text))	//Only save the string which is not null,"\n","\r" or "\r\n".
					//while((text=input.readLine())!=null&&!(text==""))		//Only save the string which is not "".
					while((text=input.readLine())!=null)		//Only save the string which is not null.
						buffer.append(text+"\n");
					output=buffer.toString();
				}
				catch(IOException ioException) 
				{
					System.out.println("File error!");
				}
			}else if(file.isDirectory()) 	//If given file name is a directory,list the file name in this directory.
			{
				System.out.println(file.toString()+" is a Directory!");
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
		//System.out.println(output);
		return output;
	}
}
