package com.FileReaderClass;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DataListTest {

	@Test
	public void dataTest() throws Exception {
		ReadDataFile dataTest = new ReadDataFile();
		List<String> list = dataTest
				.fetchData(
						"F18",
						"C:\\Users\\ADMIN\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\PatientTaskSWIV\\WEB-INF\\data.txt");
		assertEquals(1, list.size());
	}

}
