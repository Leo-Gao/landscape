package pojo;

import model.Landscape;

/**
 * entity of puma
 * Same as Population superclass except for timestep equation
 *  including interactions with prey population
 * @author Gaolu
 *
 */
public class PumaPopulation extends Population {

	
    public PumaPopulation(Landscape grid){
    	super(grid);
    }
	
    public PumaPopulation(Landscape grid, double density){
    	super(grid, density);
    }
    
    public PumaPopulation(Landscape grid, double[][] densityarray){
    	super(grid, densityarray);
    }
    
    /**
	 * Constructor makes new population as copy of given population
	 * @param copy population to copy
	 */
    public PumaPopulation(Population copy){
    	super(copy);
    }
	
    /**
     * evolves square one step according to predator equation
     * @param i index of square
     * @param j index of square
     * @param dt length of step
     * @param haredensity density of prey population at this square
     */
	public void timeStepSquare(int i, int j, double dt, double haredensity) {
		double old_density = initDensities[i][j];
		
		//puma equation
		double new_density= old_density + dt* birthrate * haredensity*old_density - dt * deathrate *old_density;
		new_density = super.diffuseSquare( i,j, new_density, dt);
		
		densities[i][j]=new_density;
	}

}