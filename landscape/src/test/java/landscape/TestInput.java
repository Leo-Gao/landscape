package landscape;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import until.io.Input;

public class TestInput {
	
	private String path = System.getProperty("user.dir");
	private File fftest = null;
	private File ffproperty = null;
	private FileWriter fw = null;
	@Before
	public void beforeCreatefile(){
		
		
		//create test.dat for the data of landscpae
		try {
			fftest = new File(path+File.separatorChar+"test.dat");
			fw = new FileWriter(fftest);
			fw.write("2 2\n");
			fw.write("1 1\n");
			fw.write("0 0\n");	
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// create test.properties to test the method of loading the properties
		try {
			ffproperty = new File(path+File.separatorChar+"test.properties");
			fw = new FileWriter(ffproperty);
			fw.write("a=1\n");
			fw.write("b=2\n");
			fw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testloadFile(){
		int[][] expected = {{1,1},{0,0}};
		
		File ff = new File(path+File.separatorChar+"test.dat");
		try {
			assertArrayEquals(expected, Input.loadFile(ff));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testLoadproperties(){
		Map<String,Double> expected = new HashMap<String, Double>();
		expected.put("a", 1d);
		expected.put("b", 2d);
		File ff = new File(path+File.separatorChar+"test.properties");
		assertEquals(expected, Input.loadProperties(ff));
		
	} 
	
	@After public void endOfTest(){

		fftest = new File(path+File.separatorChar+"test.dat");
		fftest.delete();
		
		ffproperty = new File(path+File.separatorChar+"test.properties");
		ffproperty.delete();
	}
}
