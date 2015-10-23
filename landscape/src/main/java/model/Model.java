package model;

import java.util.Map;

import pojo.HarePopulation;
import pojo.PumaPopulation;

public class Model {
	
	PumaPopulation pumas;
	HarePopulation hares;
	
	public Model(HarePopulation hares, PumaPopulation pumas){
		this.hares=hares;
		this.pumas=pumas;
	}
	
	public void evolve(int t) {
		for (int i=0; i<t; i++){
			timestep();
		}
	}

	private void timestep() {
		pumas.timeStepAll(hares);
		hares.timeStepAll(pumas);
	}
	
}
