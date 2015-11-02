package until.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

import pojo.HarePopulation;
import pojo.Population;
import pojo.PumaPopulation;

public class Output {
	static DecimalFormat df = new DecimalFormat("00000");
	static DecimalFormat dd = new DecimalFormat("#####.##");
	static String path = System.getProperty("user.dir");

	/**
	 *  it will generate one ppm file according two arrays and the landscpae 
	 * @param pumas
	 * @param hares
	 * @param T    current timestep 
	 */
	public static void generateFile(Population animal,String species,int T){
		
		String dir = "output4"+species;
		File outputDir = new File(path+File.separatorChar+dir);
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
		File file = new File(path+File.separatorChar+dir+File.separatorChar+df.format(T)+".ppm");
		
		double[][] animalData = animal.getDensities();
		int row = animalData.length;
		int column = animalData[0].length;
		Double maxValue = animal.getMaxValue();
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			// A 'magic number' 
			fw.write("P3\n");
			
			fw.write("#"+T+".ppm\n");
			
			//with whitespace height, again in ASCII decimal.
			fw.write(row+" "+column+"\n");
			
			// The  maximum  color  value (Maxval), again in ASCII decimal
			fw.write("25\n");
			
			for (int i = 0; i < row; i++) {
				for(int j = 0; j< column; j++){
					String tmp = "";

					if(species.startsWith("puma")) tmp = trans(animalData[i][j],maxValue)+" "+ 0+" "+ 0;
					if(species.startsWith("hare")) tmp = 0+" "+ trans(animalData[i][j],maxValue) +" "+ 0;
					
					fw.write(tmp+"\t");
				}
				fw.write("\n");
			}
			
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	public static void generateFileS(Population pumas,Population hares,int T){
		
		File outputDir = new File(path+File.separatorChar+"output");
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}
		File file = new File(path+File.separatorChar+"output"+File.separatorChar+df.format(T)+".ppm");
		
		double[][] pumasData = pumas.getDensities();
		double[][] haresData = hares.getDensities();
		double maxPuma = pumas.getMaxValue();
		double maxHare = hares.getMaxValue();
		int row = pumasData.length;
		int column = pumasData[0].length;
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			// A 'magic number' 
			fw.write("P3\n");
			
			fw.write("#"+T+".ppm\n");
			
			//with whitespace height, again in ASCII decimal.
			fw.write(row+" "+column+"\n");
			
			// The  maximum  color  value (Maxval), again in ASCII decimal
			fw.write("50\n");
			
			for (int i = 0; i < row; i++) {
				for(int j = 0; j< column; j++){

					double red = pumasData[i][j];// set red value using densities of pumas
					double green = haresData[i][j];// set green value using densities of hares
					double blue = 0d; // set blue value using status landScpae
					
					String tmp = trans(red, maxPuma)+" "+ trans(green,maxHare)+" "+ 0;
					
					fw.write(tmp+"\t");
				}
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	private static int trans(double d,double max) {
		Double aa = (d/max)*25; 
		return aa.intValue();
	}
	
	public static void main(String[] args) {
//		double[][] pumas = {{0,0,0,15},{0,0,0,0},{0,0,0,0},{15,0,0,0}};
//		double[][] hares = {{0,0,0,0},{0,15,0,0},{0,0,15,0},{0,0,0,0}};
//		double[][] landscape = {{0,0,0,15},{0,7,0,0},{0,0,7,0},{15,0,0,0}};
		
//		int T = 6;
		
//		generateFile(pumas, hares, T);
		
		
		
	}
}