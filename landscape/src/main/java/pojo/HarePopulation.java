package pojo;

import model.Landscape;

/**
 * entity of Hare
 * @author Gaolu
 * @author jklebes
 */

public class HarePopulation extends Population {
	
	//default values
	//birth rate of hares
	private double birthrate = 0.08d;
	
	//predation rate at which pumas eat hares
	private double deathrate = 0.04d;
	
	//diffusion rates for hares
	private double diffusionrate = 0.2d;
	
	Landscape grid;
	int gridwith; //include water halo
	int gridheight; //, = dimensions of densities array +2
	
	//constructor 
	public HarePopulation(Landscape grid){
		super(grid);
	}
	

	public HarePopulation(Landscape grid, double density) {
		super(grid,density);
	}

	

	public HarePopulation(Landscape grid, double[][] densityarray) {
		super(grid,densityarray);
	}

	
	public void timeStepSquare(int i, int j, double dt, double pumadensity) {
		//update population in square, referencing initDensities array
		//for densities of neighboring squares
		super.timeStepSquare(i, j, dt, pumadensity);
		double old_density = this.initDensities[i][j];
		double new_density= old_density + dt* birthrate * old_density - dt * deathrate *old_density * pumadensity;
		new_density = super.diffuseSquare( i,j, new_density, dt);
		densities[i][j]=new_density;
	}

	
	
}
