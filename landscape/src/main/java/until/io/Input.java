package until.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import pojo.HarePopulation;
import pojo.PumaPopulation;

public class Input {
	public static Properties pro = new Properties();
	
	/**
	 * process init dat to initialise the landscape 
	 * @return a 2-d array with 1 & 0 represent to dry or water 
	 * @throws IOException 
	 */
	public static int[][] loadFile(File dat) throws IOException{
		
		// use fileinputstream to load dat file to String
		FileInputStream fi = new FileInputStream(dat);
		byte[] temp = new byte[1024];
		int count = 0;
		StringBuffer sb = new StringBuffer();
		while( (count = fi.read(temp)) > 0){  
            sb.append(new String(temp,0,count));
        }
		fi.close();
		
		// process the String to 2-d Array 
		boolean firstLine = true;
		int row = 0;
		int column = 0;
		int[][] result = null;
		
		String[] string = sb.toString().split("\\n");
		for(int n = 0; n<string.length ; n++){
			String oneLine = string[n];
			String[] ss = oneLine.split("\\s");

			//check the firsrt line to identify the row and column 
	        if(firstLine){
	        	column = Integer.parseInt(ss[0]);//Nx
	        	row = Integer.parseInt(ss[1]);//Ny
	        	result = new int[row][column]; //int[row][column]
	        	firstLine = false;
	        	continue;
	        }
	        //to put the number in to one line of the array
	        for(int i = 0; i < ss.length; i++){
	        	result[n-1][i] = Integer.parseInt(ss[i]);
	        }
		}
		
		return result;
	}
	
	public static HashMap<String, Double> loadProperties(File properties){
		HashMap<String, Double> paras = new HashMap<String, Double>();
		
		FileInputStream in;
		try {
			in = new FileInputStream(properties);
			pro.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Set<String> pros = pro.stringPropertyNames();
		for(String ss:pros){
			Double temp = Double.parseDouble(pro.getProperty(ss));
			paras.put(ss, temp);
		}
		
		return paras;
		
	}
	
}