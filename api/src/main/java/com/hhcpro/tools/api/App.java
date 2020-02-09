package com.hhcpro.tools.keepalive;

import java.io.File;
import com.hhcpro.tools.keepalive.HBStatus.modes;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	String path = "C:\\Users\\Oleg\\eclipse-workspace-java\\keepalive\\tests\\";
    	
    	HBStatus hb = new HBStatus();
        
        hb.init(path + "G.C.S.O",modes.WRITE);
        System.out.println("Result = " + hb.IsUp());
        hb.up();
        
        System.out.println("Result = " + hb.IsUp());
        
        hb.down();
        System.out.println("Result = " + hb.IsUp());
        hb.up(); 
    	
    	File folder = new File(path);
    	File[] listOfFiles = folder.listFiles();
    	
    	for(int i=0; i< listOfFiles.length; i++)
    	{
    		if(listOfFiles[i].isFile())
    		{
    			System.out.println("File " + listOfFiles[i].getName());
    			HBStatus check = new HBStatus();
    	        check.init(path + listOfFiles[i].getName(), modes.READ);
    	        System.out.println("Verify: " + check.IsUp());
    	        hb.down();
    	        System.out.println("Verify: " + check.IsUp());
    	        hb.up();
    	        System.out.println("Verify: " + check.IsUp());
    	        //check.down();
    		}
    		else if(listOfFiles[i].isDirectory()) {
    			System.out.println("Directory " + listOfFiles[i].getName());
    		}
    	}
    	
    	
        
        
        
        
    }
}
