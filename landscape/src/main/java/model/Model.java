package model;

import pojo.HarePopulation;
import pojo.PumaPopulation;

/**
 * Model object holds landscape, populations; 
 * executes one or multiple timesteps.
 * Maybe unnecessary and could be done in main method
 * Integrate file generation?
 * 
 * @author jsk
 *
 */

public class Model {
	
	PumaPopulation pumas;
	HarePopulation hares;
	
	/**
	 * Constructor 
	 * @param hares
	 * @param pumas
	 */
	public Model(HarePopulation hares, PumaPopulation pumas){
		this.hares=hares;
		this.pumas=pumas;
	}
	
	/**
	 * Loops evolving both populations
	 *  over t timesteps of length dt.
	 *  @param t number of steps
	 *  @param dt length of step
	 */
	public void evolve(int t, double dt) {
		for (int i=0; i<t; i++){
			timestep(dt);
		}
	}

	/**
	 * Changes all populations on landscape 
	 * to next state.
	 * @param dt length of timestep
	 */
	public void timestep(double dt) {
		//save copy of initial state of hares and pumas
		HarePopulation hares_init=new HarePopulation(hares);
		PumaPopulation pumas_init=new PumaPopulation(pumas);
		
		//evolve referencing initial state of other population
		pumas.timeStepAll(dt, hares_init);
		hares.timeStepAll(dt, pumas_init);
	}
	
}
