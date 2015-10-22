package model;

import java.util.Map;

import pojo.Puma;

public interface ModelPuma {
	/**
	 * @param initDentity original densities of hare
	 * @param T  number of timeStamp
	 * @return  key:value   which number of timestamp : current puma with densities
	 */
	public Map<Integer,Puma> getAll(Puma initPuma,int T);
	
	
}
