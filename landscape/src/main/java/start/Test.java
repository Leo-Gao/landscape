package start;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.filechooser.FileSystemView;

import model.Landscape;
import model.Model;
import pojo.HarePopulation;
import pojo.PumaPopulation;
import until.io.Input;
import until.io.Output;


public class Test {
	
	public static void main(String[] args) throws IOException {
		//load file  and init landscpae
		String path = System.getProperty("user.dir");
		File dat = new File(path+File.separatorChar+"property.properties");
		int[][] landScape = Input.loadFile(dat);
		
		Landscape grid=new Landscape(landScape);
		PumaPopulation pumas = new PumaPopulation(grid);
		HarePopulation hares = new HarePopulation(grid);
		
		Model model = new Model(hares, pumas);
		
		int T = 10;
		
		model.evolve(T);
		
		//output pictures
//		Output.generateFile(pumas.getDensities(), hares.getDensities());
		
		//it only create one ppm file  , so there shoule be a loop to generate a number of files including all timesteps;
		// and the third parameter means the the current timestep
		Output.generateFile(pumas.getDensities(),hares.getDensities(),T);
	
	}
}
