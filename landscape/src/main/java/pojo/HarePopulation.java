package pojo;

import model.Landscape;

/**
 * entity of Hare
 * Same as superclass except for including predators
 * in deathrate
 * @author Gaolu
 * @author jklebes
 */

public class HarePopulation extends Population {
	
	
	/**
	 * Constructor with no densities given.
	 * Sets all densities to 0.
	 * @param grid Landscape object the population is on
	 */
	public HarePopulation(Landscape grid){
		super(grid);
	}
	
	/**
	 * Constructor with single density value.
	 * Sets all densities to this value.
	 * @param grid Lndscape grid
	 * @param density value for uniform density
	 */
	public HarePopulation(Landscape grid, double density) {
		super(grid,density);
	}

	/**
	 * Constructor with array of density values.
	 * Copies array to population density for land squares.
	 * @param grid Landscape grid
	 * @param density array of density values
	 */
	public HarePopulation(Landscape grid, double[][] densityarray) {
		super(grid,densityarray);
	}

	
	public HarePopulation(Population copy){
		super(copy);
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
