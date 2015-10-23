package start;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.filechooser.FileSystemView;

import model.ModelGeneration;
import model.ModelGenerationImpl;
import model.ModelHare;
import model.ModelPuma;
import pojo.Hare;
import pojo.Puma;
import until.io.Input;
import until.io.Output;


public class Test {
	
	public static void main(String[] args) throws IOException {
		//load file  and init landscpae
		String path = Class.class.getResource("/").toString();
		File dat = new File(path+"property.properties");
		int[][] landScape = Input.loadFile(dat);
		
		//temp 
		// add file
		Puma initPuma = new Puma();
		Hare initHare = new Hare();
		
		//put landscape to initObject
		initPuma.landScape = landScape;
		initHare.landScpage = landScape;
		
		//according to oringal landscape to  create first densities randomly
		initPuma.setDensities(initPuma.generateIntDentities());
		initHare.setDensities(initHare.generateIntDentities());

		//use modelObject to simulate puma,hare ,then create a number of status of densities 

		int T = 10;
		ModelGeneration gene = new ModelGenerationImpl();
		gene.generateDensities(initPuma, initHare, T);
		
		HashMap<Integer, Puma> pumas = gene.getPumas();
		HashMap<Integer, Hare> hares = gene.getHares();
		
		
		//output pictures
		Output.generateFile(pumas, hares);
		
	
	}
}
