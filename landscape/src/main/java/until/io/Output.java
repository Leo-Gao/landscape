package until.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	public static void generateFile(double[][] pumas,double[][] hares,int T){
		String path = System.getProperty("user.dir");
//		String path = Class.class.getResource("/").toString();

		double[][] landscpae = {{0,0,0,15},{0,7,0,0},{0,0,7,0},{15,0,0,0}};
		
		File file = new File(path+File.separatorChar+"output"+File.separatorChar+T+".ppm");

		FileWriter fw = null;
		
		int row = pumas[0].length;
		int column = pumas.length;
		
		try {
			fw = new FileWriter(file);
			// A 'magic number' 
			fw.write("P3\n");
			
			fw.write("#"+T+".ppm\n");
			
			//with whitespace height, again in ASCII decimal.
			fw.write(row+" "+column+"\n");
			
			// The  maximum  color  value (Maxval), again in ASCII decimal
			fw.write("255\n");
			
			double sumPuma = 0d;
			double sumHare = 0d;
			
			int n = 0;
			for (int i = 0; i < row; i++) {
				for(int j = 0; j< column; j++){
					n++;
					
					double red = pumas[i][j];// set red value using densities of pumas
					double green = hares[i][j];// set green value using densities of hares
					double blue = 200 * landscpae[i][j]; // set blue value using status landScpae
					
					sumPuma += red;sumHare += green;
					
					String tmp = red+"  "+ green+"  "+blue;
					
					if((n%4) == 0){
						fw.write(tmp+"\n");
					}else{
						fw.write(tmp+"    ");
					}
				}
			}
			
			// set interval as 100 : every 100 T timestamp printout the average value
			if (T % 100 == 0) {
				System.out.println("Average of puma densities across the wohle grid is : "+sumPuma/(row*column));
				System.out.println("Average of hare densities across the wohle grid is : "+sumHare/(row*column));
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	
	public static void main(String[] args) {
		double[][] pumas = {{0,0,0,15},{0,0,0,0},{0,0,0,0},{15,0,0,0}};
		double[][] hares = {{0,0,0,0},{0,15,0,0},{0,0,15,0},{0,0,0,0}};
		double[][] landscpae = {{0,0,0,15},{0,7,0,0},{0,0,7,0},{15,0,0,0}};
		
		int T = 6;
		
		generateFile(pumas, hares, T);
		
		
		
	}
}