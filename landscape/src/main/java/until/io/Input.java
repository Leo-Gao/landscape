package until.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
	        	column = Integer.parseInt(ss[0]);
	        	row = Integer.parseInt(ss[1]);
	        	result = new int[row][column];
	        	firstLine = false;
	        	continue;
	        }
	        //to put the number in to one line of the array
	        for(int i = 0; i < ss.length; i++){
	        	result[n-1][i] = Integer.parseInt(ss[i]);
	        }
		}
		
//		for (int i = 0; i < row; i++) {
//			StringBuffer aa = new StringBuffer();
//			for (int j = 0; j < column; j++) {
//				aa.append(result[i][j]+" ");
//			}
//			System.out.println(aa.toString());
//		}
		
		return result;
	}
	
	public static void loadProperties(PumaPopulation puma,HarePopulation hare, File properties){
		
		FileInputStream in;
		try {
			in = new FileInputStream(properties);
			pro.load(in);
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Double r = Double.parseDouble(pro.getProperty("r"));
		Double a = Double.parseDouble(pro.getProperty("a"));
		Double k = Double.parseDouble(pro.getProperty("k"));
		
		Double b = Double.parseDouble(pro.getProperty("b"));
		Double m = Double.parseDouble(pro.getProperty("m"));
		Double l = Double.parseDouble(pro.getProperty("l"));
		
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
//		InputStream in = Class.class.getResourceAsStream("property.properties");
		Input.loadFile(new File("d://small.dat"));
		
	}
	
}