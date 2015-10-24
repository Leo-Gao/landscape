package start;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.filechooser.FileSystemView;

import model.Landscape;
import model.Model;
import model.ModelGeneration;
import model.ModelGenerationImpl;
import model.ModelHare;
import model.ModelPuma;
import pojo.Hare;
import pojo.HarePopulation;
import pojo.Puma;
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
		Output.generateFile(pumas.getDensities(), hares.getDensities());
		
	
	}
}
