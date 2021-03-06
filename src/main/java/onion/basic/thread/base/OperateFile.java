package onion.basic.thread.base;

import java.io.*;

public class OperateFile implements Runnable
{
	private StringReader sr;
	private char[] charArray;
	private String threadName;
	private Writer out;
	
	public OperateFile(String threadName, Writer out)
	{
		this.threadName = threadName;
		this.out = out;
	}
	
	public void writeFile()
	{
		try
		{
			sr = new StringReader(threadName);
			charArray = new char[20];
			sr.read(charArray);

			System.out.println("----- before synchronized " + threadName);
			//以共享自由out作为监视器
			synchronized (out) 
			{
				System.out.println("----- wirte " + threadName);
				out.write(charArray);
			}
			System.out.println("----- after synchronized " + threadName);
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	

	public void run()
	{
		writeFile();
	}
}
