package tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.*;
import until.io.Input;

public class TestInput {
	
	static String path = System.getProperty("user.dir");
	
	
	@Test
	public void testloadFile(){
		int[][] expected = {{1,1},{0,0}};

		FileWriter fw;
		File ff = null;
		try {
			ff = new File(path+File.separatorChar+"test.dat");
			fw = new FileWriter(ff);
			fw.write("2 2\n");
			fw.write("1 1\n");
			fw.write("0 0\n");
			fw.close();
			assertArrayEquals(expected, Input.loadFile(ff));
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			ff.delete();
		}
	}
	
	@Test
	public void testLoadproperties(){
		Map<String,Double> expected = new HashMap<String, Double>();
		expected.put("a", 1d);
		expected.put("b", 2d);

		FileWriter fw;
		File ff = null;
		try {
			ff = new File(path+File.separatorChar+"test.properties");
			fw = new FileWriter(ff);
			fw.write("a=1\n");
			fw.write("b=2\n");
			fw.close();
			assertEquals(expected, Input.loadProperties(ff));
			
			ff.delete();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			ff.delete();
		}
		
	} 
	
	
}
