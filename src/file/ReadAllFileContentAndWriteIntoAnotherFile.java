package file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class ReadAllFileContentAndWriteIntoAnotherFile {
	
	//Attention Problem:Only the last file content is saved,if not append file contend into the target written file,the other file content are covered/overridden by the last file.
	public static File readAllFileAndWriteIntoAnotherFile(String rdir,File file) {
		
		//Test special character.
		/*System.out.println("".length());
		System.out.println("\r".length());
		System.out.println("\n".length());
		System.out.println("\r\n".length());
		System.out.println(" ".length());
		System.out.println("	".length());	/*I type a character of "Tap" key on computer
		--press the "Tap" keys on the keyboard so that key it in.*/
		
		File rdirfile=new File(rdir);
		//String fileState="OldFile";
		if(!rdirfile.exists()) {
			//fileState="NewCreated";
			File fileParent=rdirfile.getParentFile();
			if(!fileParent.exists()){	//若欲创建文件的目录不存在，则创建之
				//fileParent.mkdir();		//根据抽象路径创建目录，若指定的路径中包含不存在的父目录，则会创建失败
				System.out.println("We will create the parent directories that new file:'"+rdir+"' needs.");
				fileParent.mkdirs();		//根据抽象路径创建目录，包括创建不存在的父目录
				try {
					rdirfile.createNewFile();		//需抛出异常或捕获异常
				}catch(IOException e) {
					System.out.println("创建新文件时出现了错误！");
					e.printStackTrace();
				}
				System.out.println("欲创建文件的目录已建立，欲创建文件的路径为："+rdir);
			}
		}else {
			System.out.println(rdirfile.getName()+" is already exists.");
		}
		if(!file.exists()) {
			System.out.println("The given destination file to be written don't exist!");
			return null;
		}else {
			BufferedReader reader=null;
			BufferedWriter writer=null;
			if(rdirfile.isDirectory()) {
				String[] filelist=rdirfile.list();
				for(int i=0;i<filelist.length;i++) {	//The part of code repetition can be made into a recursive method代码重复部分可以做成一个递归方法
					try {
						reader=new BufferedReader(new FileReader(new File(rdir+"\\"+filelist[i])));		//If run on Windows,the file path separator is "\\".
						//reader=new BufferedReader(new FileReader(new File(rdir+"/"+filelist[i])));	//If run on Linux,the file path separator is "/" but "\\". 
						
						//writer=new BufferedWriter(new FileWriter(file));
						writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,true)));		//Append instead of override the file content追加而不是覆盖重写文件内容,the content of the written target file will be covered when alter the "true" into "false"当将true改为false时，目标写入文件的内容就会被覆盖.
						/*The declaration of constructor method "FileOutputStream(file,true)":"java.io.FileOutputStream.FileOutputStream(File file, boolean append) throws FileNotFoundException",
						 * the function of this method:"Creates a file output stream to write to the file represented bythe specified File object. If the second argument is true, then bytes will be written to the end of the filerather than the beginning. A new FileDescriptor object iscreated to represent this file connection". */
						
						
						String line;
						List<String> filecontent;
						while((line=reader.readLine())!=null) {
							writer.write(line+"\n");	//Write each line with a break line,if on the Windows file system,replaces the "\n" with "\r\n".Otherwise,implement the same function by following two statements instead:
							/*writer.write(line);
							 *writer.newLine(); */
							writer.flush();
							//System.out.println(line);
						}
					}catch(IOException e) {
						e.printStackTrace();
					}finally {
						try {
							if(reader!=null) {
								reader.close();
							}
							if(writer!=null) {
								writer.close();
							}
						}catch(IOException e) {
							e.printStackTrace();
						}
					}
				}
				System.out.println(rdirfile.getName()+" is a directory.");
			}
			if(rdirfile.isFile()) {
				//高效缓冲输入输出流
				//BufferedReader reader=null;
				//BufferedWriter writer=null;
				try {
					//传入的是Reader的子类
					reader=new BufferedReader(new FileReader(rdirfile));	//创建高效缓冲输入流
					writer=new BufferedWriter(new FileWriter(file));		//创建高效缓冲输出流
					String line;
					//如果还有下一行就一直读下去，readLine()用于读取一行
					while((line=reader.readLine())!=null) {
						writer.write(line+"\n");	//Write each line with a break line,if on the Windows file system,replaces the "\n" with "\r\n".Otherwise,implement the same function by following two statements instead:
						/*writer.write(line);
						 *writer.newLine(); */
						writer.flush();
						System.out.println(line);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}finally {
					try {
						if(reader!=null) {
							reader.close();
						}
						if(writer!=null) {
							writer.close();
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			
			
			
			System.out.println("The operation of reading the source file and writing to the target file is completed!");
		}
		
		
		return file;
	}
	
	public static File readAllFileAndWriteIntoAnotherFile(String sdir,String tdir) {
		//Test special character.
		/*System.out.println("".length());
		System.out.println("\r".length());
		System.out.println("\n".length());
		System.out.println("\r\n".length());
		System.out.println(" ".length());
		System.out.println("	".length());	/*I type a character of "Tap" key on computer
		--press the "Tap" keys on the keyboard so that key it in.*/
		
		//Problem:If the parent directory of file to be written is in the file(Not normal file) or in the parent directory of the file to be read,how?若要写入的目标文件与要读取的源文件处于同一目录，或要写入的文件在要读取的源文件的父目录下，这时就会没有完全将给定目录包括其子目录下所有文件的内容写入到目标写入文件。该如何处理？		
		File sdirfile=new File(sdir);
		File tdirfile=new File(tdir);
		//String fileState="OldFile";
		System.out.println("Please pay attention to check the source file(Designate an alias: A) to be read and the target file(Designate an alias: B) to be written are not the following circumstances for sure:");
		System.out.println("1.A and B are in the same path;");
		System.out.println("2.A's parent directories(including all ancestor directories) are not the parent directory of B.");
		System.out.println("Otherwise,the prcocess of reading the source file and writing to the target file may be errors!");
		if(!sdirfile.exists()) {
			//fileState="NewCreated";
			File fileParent=sdirfile.getParentFile();
			if(!fileParent.exists()){	//若欲创建文件的目录不存在，则创建之
				//fileParent.mkdir();		//根据抽象路径创建目录，若指定的路径中包含不存在的父目录，则会创建失败
				System.out.println("We will create the parent directories that new file:'"+sdir+"' needs.");
				fileParent.mkdirs();		//根据抽象路径创建目录，包括创建不存在的父目录
				try {
					sdirfile.createNewFile();		//需抛出异常或捕获异常
				}catch(IOException e) {
					System.out.println("创建新文件时出现了错误！");
					e.printStackTrace();
				}
				System.out.println("欲创建文件的目录已建立，欲创建文件的路径为："+sdir);
			}
		}else {
			System.out.println(sdirfile.getName()+" is already exists.");
		}
		if(!tdirfile.exists()) {
			//fileState="NewCreated";
			File fileParent=tdirfile.getParentFile();
			if(!fileParent.exists()){	//若欲创建文件的目录不存在，则创建之
				//fileParent.mkdir();		//根据抽象路径创建目录，若指定的路径中包含不存在的父目录，则会创建失败
				System.out.println("We will create the parent directories that new file:'"+tdir+"' needs.");
				fileParent.mkdirs();		//根据抽象路径创建目录，包括创建不存在的父目录
				try {
					tdirfile.createNewFile();		//需抛出异常或捕获异常
				}catch(IOException e) {
					System.out.println("An error occurred while creating a new file创建新文件时出现了错误！");
					e.printStackTrace();
				}
				System.out.println("The directory of the file to be created has been established, and the path of the file to be created is欲创建文件的目录已建立，欲创建文件的路径为："+tdir);
			}
		}else {
			System.out.println(tdirfile.getName()+" is already exists.");
		}
		if(sdirfile==null||tdirfile==null) {
			System.out.println("The given file name to be read and written is null!Unknow error!");
			return null;
		}
		if(tdirfile.isDirectory()) {
			System.out.println("The given file to be written is a directory,please specify a normal file.");
			return null;
		}else {
			BufferedReader reader=null;
			BufferedWriter writer=null;
			if(sdirfile.isDirectory()) {
				String[] filelist=sdirfile.list();
				for(int i=0;i<filelist.length;i++) {	//The part of code repetition can be made into a recursive method代码重复部分可以做成一个递归方法
					String lreadfilename=sdir+"/"+filelist[i];		//If run on Linux
					String wreadfilename=sdir+"\\"+filelist[i];		//If run on Windows
					File readfile=new File(wreadfilename);
					if(readfile.isDirectory()) {
						ReadAllFileContentAndWriteIntoAnotherFile.readAllFileAndWriteIntoAnotherFile(wreadfilename,tdir);
					}/*else {
						try {
							reader=new BufferedReader(new FileReader(new File(sdir+"\\"+filelist[i])));		//If run on Windows,the file path separator is "\\".
							//reader=new BufferedReader(new FileReader(new File(sdir+"/"+filelist[i])));	//If run on Linux,the file path separator is "/" but "\\". 
							
							//writer=new BufferedWriter(new FileWriter(sdirfile));
							writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tdirfile,true)));		//Append instead of override the file content追加而不是覆盖重写文件内容,the content of the written target file will be covered when alter the "true" into "false"当将true改为false时，目标写入文件的内容就会被覆盖.
							/*The declaration of constructor method "FileOutputStream(file,true)":"java.io.FileOutputStream.FileOutputStream(File file, boolean append) throws FileNotFoundException",
							 * the function of this method:"Creates a file output stream to write to the file represented bythe specified File object. If the second argument is true, then bytes will be written to the end of the filerather than the beginning. A new FileDescriptor object iscreated to represent this file connection". */
							
							/*String line;
							List<String> filecontent;
							while((line=reader.readLine())!=null) {
								//writer.write(line+"\n");	//Write each line with a break line,if on the Windows file system,replaces the "\n" with "\r\n".Otherwise,implement the same function by following two statements instead:
								writer.write(line);
								writer.newLine();
								writer.flush();
								//System.out.println(line);
							}
						}catch(IOException e) {
							e.printStackTrace();
						}finally {
							try {
								if(reader!=null) {
									reader.close();
								}
								if(writer!=null) {
									writer.close();
								}
							}catch(IOException e) {
								e.printStackTrace();
							}
						}
					}*/
				}
				System.out.println(sdirfile.getName()+" is a directory.");
			}
			if(sdirfile.isFile()) {
				//高效缓冲输入输出流
				//BufferedReader reader=null;
				//BufferedWriter writer=null;
				try {
					//传入的是Reader的子类
					reader=new BufferedReader(new FileReader(sdirfile));	//创建高效缓冲输入流
					writer=new BufferedWriter(new FileWriter(tdirfile));		//创建高效缓冲输出流
					String line;
					//如果还有下一行就一直读下去，readLine()用于读取一行
					while((line=reader.readLine())!=null) {
						//writer.write(line+"\n");	//Write each line with a break line,if on the Windows file system,replaces the "\n" with "\r\n".Otherwise,implement the same function by following two statements instead:
						writer.write(line);
						writer.newLine();
						writer.flush();
						System.out.println(line);
					}
				}catch(IOException e) {
					e.printStackTrace();
				}finally {
					try {
						if(reader!=null) {
							reader.close();
						}
						if(writer!=null) {
							writer.close();
						}
					}catch(IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			
			
			
			System.out.println("The operation of reading the source file and writing to the target file is completed!");
		}
		
		
		return tdirfile;
	}
	
	public ReadAllFileContentAndWriteIntoAnotherFile() {
		// TODO Auto-generated constructor stub
	}

}
