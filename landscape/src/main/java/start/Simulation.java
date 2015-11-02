package start;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import model.Landscape;
import model.Model;
import pojo.HarePopulation;
import pojo.PumaPopulation;
import until.io.Input;
import until.io.Output;


public class Simulation {
	
	public static void main(String[] args) throws IOException {
		
		//load file  and init landscpae
		String path = System.getProperty("user.dir");
//		String path = tt.getClass().getResource("/small.dat").getPath();
//		File dat = new File(path+File.separatorChar+args[0]);
		File dat = new File(path+File.separatorChar+"small.dat");
		int[][] landScape = Input.loadFile(dat);
		Landscape grid=new Landscape(landScape);

		//load file for parameters
		File property = new File(path+File.separatorChar+"pro.properties");
		HashMap<String, Double> paras = Input.loadProperties(property);
		
		PumaPopulation pumas = new PumaPopulation(grid);
		pumas.setBirthRate(paras.get("birth_p"));
		pumas.setDeathRate(paras.get("death_p"));
		pumas.setDiffusionRate(paras.get("diffusion_p"));
		
//		pumas.setBirthRate(.1);
//		pumas.setDeathRate(.5);
//		pumas.setDiffusionRate(.4);
		long seed1 = 539872L;
		pumas.setRandomDensities(5, seed1);
		HarePopulation hares = new HarePopulation(grid);

		hares.setBirthRate(paras.get("birth_h"));
		hares.setDeathRate(paras.get("death_h"));
		hares.setDiffusionRate(paras.get("diffusion_h"));
		long seed2 = 348987L;
//		hares.setBirthRate(.8);
//		hares.setDeathRate(.4);
//		hares.setDiffusionRate(.1);
		hares.setRandomDensities(5, seed2);
		
		Model model = new Model(hares, pumas);
		
		int Tmax = paras.get("Tmax").intValue();
		int Tmin = paras.get("Tmin").intValue();
		int T = paras.get("T").intValue();
		int aveT = paras.get("aveT").intValue();
		double dt= paras.get("dt");
		int simuCount = 0;
		//issue start time
		double startTime = System.currentTimeMillis();
		
		//loop: update model and create ppm file
		for (double i=Tmin; i<Tmax; i=i+dt){
			
			model.timestep(dt);
			simuCount++;
			
			if (simuCount % T == 0) {
				//output pictures
				
				//it only create one ppm file  , so there shoule be a loop to generate a number of files including all timesteps;
				// and the third parameter means the the current timestep
				Output.generateFileS(pumas, hares,simuCount/T);
				
				//seperately create file
				Output.generateFile(pumas, "puma", simuCount/T);
				Output.generateFile(hares, "hare", simuCount/T);
			}
			
			// set interval as 100 : every 100 timestep  printout the average value
			if (simuCount % aveT == 0) {
				System.out.println("Average of puma densities "+"for simulation times "+simuCount+ " across the wohle grid is : "+ pumas.getAvgDensityGrid());
				System.out.println("Average of hare densities "+"for simulation times "+simuCount+ " across the wohle grid is : "+ hares.getAvgDensityGrid());
			}
		}
		
		//issue end time
		double endTime = System.currentTimeMillis();
		
		System.out.println("Entire simulation time : "+(endTime-startTime)/1000+"s");
	}
}
