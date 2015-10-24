package pojo;

import model.Landscape;

/**
 * entity of Hare
 * @author Gaolu
 * @author jklebes
 */

public class HarePopulation extends Population {
	
	
	//constructors
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
		double old_density = this.initDensities[i][j];
		double new_density= old_density + dt* birthrate * old_density - dt * deathrate *old_density * pumadensity;
		if (new_density < 0){new_density=0;}
		new_density = super.diffuseSquare( i,j, new_density, dt);
		densities[i][j]=new_density;
	}

	
	
}
