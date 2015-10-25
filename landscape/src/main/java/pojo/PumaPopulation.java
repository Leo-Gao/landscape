package pojo;

import model.Landscape;

/**
 * entity of puma
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

	public void timeStepSquare(int i, int j, double dt, double haredensity) {
		double old_density = initDensities[i][j];
		
		//puma equation
		double new_density= old_density + dt* birthrate * haredensity*old_density - dt * deathrate *old_density;
		new_density = super.diffuseSquare( i,j, new_density, dt);
		
		densities[i][j]=new_density;
	}

}