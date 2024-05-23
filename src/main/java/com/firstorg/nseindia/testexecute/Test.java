package com.firstorg.nseindia.testexecute;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

import com.firstorg.nseindia.test.TestBaseClass;
import com.firstorg.nseindia.utility.TestDataJsonParser;

public class Test {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {
		
		
		String xmlPath = TestDataJsonParser.getTestData("$.driver-config.testNgxml");
		TestNG testNg= new TestNG();
		List<String> suite = Lists.newArrayList();
		suite.add(TestBaseClass.cDir+xmlPath);
		//testNg.setUseDefaultListeners(false);
		testNg.setTestSuites(suite);
		testNg.run();
		
	}
	
}
