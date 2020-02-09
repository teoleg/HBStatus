package com.hhcpro.tools.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class HBStatus {
	
	private FileChannel fc;
	int length = 2;
	private final char UP = 'U';
	private final char DOWN = 'D';
	private final char EMPTY = 'F';
	private MapMode mode = FileChannel.MapMode.READ_WRITE;
	public enum modes { READ, WRITE };
	
	
	public boolean init(String _n, modes _mode) {
		
		
		mode = (_mode == modes.READ) ? FileChannel.MapMode.READ_ONLY : FileChannel.MapMode.READ_WRITE;
		
		try 
		{
			fc = extracted(_n).getChannel();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	synchronized public boolean up()
	{
		
		if(mode == FileChannel.MapMode.READ_ONLY) {
			throw new IllegalArgumentException("up() and down() calls are illegal for READ_ONLY access");
		}
		
		try 
		{
			MappedByteBuffer out = fc.map(mode,0, length); 
			out.clear();
			out.putChar(UP);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	synchronized public boolean down() 
	{
		if(mode == FileChannel.MapMode.READ_ONLY) {
			throw new IllegalArgumentException("up() and down() calls are illegal for READ_ONLY access");
		}

		try 
		{
			MappedByteBuffer out = fc.map(mode,0, length);
			out.clear();
			out.putChar(DOWN);
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private char current() 
	{
		char v = EMPTY;
		try 
		{
			MappedByteBuffer out = fc.map(mode,0, length);
			v = out.getChar();
			if(v != UP && v != DOWN)
				return EMPTY;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return v;
		}
        return v;
	}
	
	synchronized public boolean IsUp() 
	{
		switch(this.current()) {
		case EMPTY:
		case DOWN:
		default:
			return false;
		case UP:
			return true;
		}
	}
	
	private RandomAccessFile extracted(String n) throws FileNotFoundException {
		return new RandomAccessFile(n, "rw");
	}
	
	// This API will be used to write status into mmap
	public void alive() {
		System.out.println("status");
	}

}
