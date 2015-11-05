package start;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

import model.Landscape;
import model.Model;
import pojo.HarePopulation;
import pojo.PumaPopulation;
import until.io.Input;
import until.io.Output;

/**
 * It's the main entry of the whole program it has two main part, one of them is
 * initialize kinds of data and object like load property files and make new
 * Object of puma and hare another part refers to proceed the iterations of
 * simulation and set the numbers of timestep to generate files.
 * 
 * during the time of simulation , it will print out the average density accross
 * the whole grid
 * 
 * @author Gaolu
 *
 */
public class Simulation {

	public Landscape grid; // store the data of gird
	public HashMap<String, Double> paras;// store kinds of parameters
	public PumaPopulation pumas; // object of puma
	public HarePopulation hares; // object of hare
	public Model model; // simulating model
	
	public Simulation() {
		super();
	}
	/**
	 * initialize kinds of data and object like load property files and make new  Object of puma
	 * @param datFile  input the dat file to indicate the landscape
	 * @throws IOException
	 */
	public void init(String datFile) throws IOException {
		String path = System.getProperty("user.dir");

		// load file and init landscpae
		File dat = new File(path + File.separatorChar + datFile);
		int[][] landScape = Input.loadFile(dat);
		grid = new Landscape(landScape);

		// load file for parameters
		File property = new File(path + File.separatorChar + "pro.properties");
		paras = Input.loadProperties(property);

		// initialize puma
		pumas = new PumaPopulation(grid);
		pumas.setBirthRate(paras.get("birth_p"));
		pumas.setDeathRate(paras.get("death_p"));
		pumas.setDiffusionRate(paras.get("diffusion_p"));
		long seed1 = 539872L;
		pumas.setRandomDensities(5, seed1);

		// initialize hare
		hares = new HarePopulation(grid);
		hares.setBirthRate(paras.get("birth_h"));
		hares.setDeathRate(paras.get("death_h"));
		hares.setDiffusionRate(paras.get("diffusion_h"));
		long seed2 = 348987L;
		hares.setRandomDensities(5, seed2);

		model = new Model(hares, pumas);
	}
	
	/**
	 *  proceed the iterations of simulation and set the numbers of timestep to generate files.
	 *  it will produce pictures in order according to the setting of properties
	 */
	public void process() {
		
		DecimalFormat df = new DecimalFormat("###.#####");

		int Tmax = paras.get("Tmax").intValue();
		int Tmin = paras.get("Tmin").intValue();
		int T = paras.get("T").intValue();
		int aveT = paras.get("aveT").intValue();
		double dt = paras.get("dt");
		int simuCount = 0;
		// issue start time
		double startTime = System.currentTimeMillis();

		// loop: update model and create ppm file
		for (double i = Tmin; i < Tmax; i = i + dt) {

			model.timestep(dt);
			simuCount++;

			if (simuCount % T == 0) {

				// it only create one ppm file , so there shoule be a loop to
				// generate a number of files including all timesteps;
				// and the third parameter means the the current timestep
				Output.generateFileS(pumas, hares, simuCount / T);

				// seperately create file
				Output.generateFile(pumas, "puma", simuCount / T);
				Output.generateFile(hares, "hare", simuCount / T);
			}

			// set interval as 100 : every 100 timestep printout the average
			// value
			if (simuCount % aveT == 0) {
				System.out.println("Average of puma densities "
						+ "for simulation times " + simuCount
						+ " across the wohle grid is : "
						+ df.format(pumas.getAvgDensityGrid()));
				System.out.println("Average of hare densities "
						+ "for simulation times " + simuCount
						+ " across the wohle grid is : "
						+ df.format(hares.getAvgDensityGrid()));
			}
		}

		// issue end time
		double endTime = System.currentTimeMillis();

		System.out.println("Entire simulation time : " + (endTime - startTime)
				/ 1000 + "s");
	}
	
	/**
	 * Start main method
	 * @param args
	 */
	public static void main(String[] args) {
		
		Simulation simulation = new Simulation();
		try {
			
			simulation.init(args[0]);
			simulation.process();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
