package model;

import pojo.HarePopulation;
import pojo.PumaPopulation;

public class Model {
	
	PumaPopulation pumas;
	HarePopulation hares;
	
	public Model(HarePopulation hares, PumaPopulation pumas){
		this.hares=hares;
		this.pumas=pumas;
	}
	
	public void evolve(int t, double dt) {
		for (int i=0; i<t; i++){
			timestep(dt);
		}
	}

	private void timestep(double dt) {
		//save copy of initial state of hares and pumas
		HarePopulation hares_init=hares;
		PumaPopulation pumas_init=pumas;
		
		//evolve referencing initial state of other population
		pumas.timeStepAll(dt, hares_init);
		hares.timeStepAll(dt, pumas_init);
	}
	
}
