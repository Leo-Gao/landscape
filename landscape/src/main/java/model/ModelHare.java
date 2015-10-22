package model;

import java.util.Map;

import pojo.Hare;


public interface ModelHare {
	/**
	 * @param initDentity original densities of hare
	 * @param T  number of timeStamp
	 * @return  key:value   which number of timestamp : current hare with densities
	 */
	public Map<Integer,Hare> getAll(Hare initHare,int T);	
	
	
}
