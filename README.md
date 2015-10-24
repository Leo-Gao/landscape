# landscape

		//load file  and init landscpae
		File dat = new File("");
		int[][] landScape = Input.loadLandscape(dat);
		
		//temp 
		// add file
		Puma initPuma = new Puma();
		Hare initHare = new Hare();
		
		//put landscape to initObject
		initPuma.landScape = landScape;
		initHare.landScpage = landScape;
		
		//according to oringal landscape to  create first densities randomly
		initPuma.setDensities(initPuma.generateIntDentities());
		initHare.setDensities(initHare.generateIntDentities());

		//use modelObject to simulate puma,hare ,then create a number of status of densities 
		ModelPuma modelPuma = new ModelPumaImpl();
		ModelHare modelHare = new ModelHareImpl();
		int T = 10;
		HashMap<Integer, Puma> pumas = (HashMap<Integer, Puma>) modelPuma.getAll(initPuma, T);
		HashMap<Integer, Hare> hares = (HashMap<Integer, Hare>) modelHare.getAll(initHare, T);
		
		//output pictures
		Output.generateFile(pumas, hares);
	
