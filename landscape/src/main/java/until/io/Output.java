package until.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import pojo.HarePopulation;
import pojo.PumaPopulation;

public class Output {
	
	/**
	 * generate files with 
	 * @param pumas
	 * @param hares
	 */
	public static void generateFile(Map<Integer,PumaPopulation> pumas,Map<Integer,HarePopulation> hares){
		
		
		 
	}
	/**
	 *  it will generate one ppm file according two arrays and the landscpae 
	 * @param pumas
	 * @param hares
	 * @param T    current timestep 
	 */
	public static void generateFile(double[][] pumas,double[][] hares,int[][] landscape,int T){
//		String path = Class.class.getResource("/").getPath();
		String path = System.getProperty("user.dir");
		DecimalFormat df = new DecimalFormat("00000");
		File outputDir = new File(path+File.separatorChar+"output");
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
		File file = new File(path+File.separatorChar+"output"+File.separatorChar+df.format(T)+".ppm");
		
		int row = pumas[0].length;
		int column = pumas.length;
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			// A 'magic number' 
			fw.write("P3\n");
			
			fw.write("#"+T+".ppm\n");
			
			//with whitespace height, again in ASCII decimal.
			fw.write(row+" "+column+"\n");
			
			// The  maximum  color  value (Maxval), again in ASCII decimal
			fw.write("15\n");
			
			int n = 0;
			for (int i = 0; i < row; i++) {
				for(int j = 0; j< column; j++){
					n++;
					
					double red = pumas[i][j];// set red value using densities of pumas
					double green = hares[i][j];// set green value using densities of hares
					double blue = landscape[i][j]; // set blue value using status landScpae
					
					String tmp = red+"  "+ green+"  "+ blue;
					
					if((n%4) == 0){
						fw.write(tmp+"\n");
					}else{
						fw.write(tmp+"    ");
					}
				}
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	
	public static void main(String[] args) {
//		double[][] pumas = {{0,0,0,15},{0,0,0,0},{0,0,0,0},{15,0,0,0}};
//		double[][] hares = {{0,0,0,0},{0,15,0,0},{0,0,15,0},{0,0,0,0}};
//		double[][] landscape = {{0,0,0,15},{0,7,0,0},{0,0,7,0},{15,0,0,0}};
		
//		int T = 6;
		
//		generateFile(pumas, hares, T);
		
		
		
	}
}