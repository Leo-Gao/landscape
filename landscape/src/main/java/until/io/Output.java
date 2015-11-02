package until.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import pojo.Population;

public class Output {
	static DecimalFormat df = new DecimalFormat("00000");
	static DecimalFormat dd = new DecimalFormat("#####.##");
	static String path = System.getProperty("user.dir");

	/**
	 * in order to create ppm files with only one array  for puma or hare individually
	 * @param animal   the population inside which densities, maxValue, grid are stored
	 * @param species   identify the species of current animal to create correct file 
	 * e.g.  puma /  hare
	 * if it is only puma, then , produce only pictures for pumas
	 * @param T   set the interval value  , read from property file
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
	/**
	 * in order to create ppm files with both of two arrays. 
	 * @param pumas   the population of puma inside which densities, maxValue, grid are stored
	 * @param hares   the population of puma inside which densities, maxValue, grid are stored
	 * @param T   set the interval value  , read from property file
	 */
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
//					double blue = 0d; // set blue value using status landScpae
					
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
	/**
	 * according to the current density and maxValue to determine the percent
	 * that way, ensure the corret number of color
	 * @param d
	 * @param max
	 * @return 
	 */
	private static int trans(double d,double max) {
		Double aa = (d/max)*25; 
		return aa.intValue();
	}
	
}