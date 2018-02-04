package com.FileReaderClass;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IDTest {

	@Test
	public void IDListTest() {

		ReadDataFile dataList = new ReadDataFile();
		List<String> checkWords = new ArrayList<String>();
		checkWords.add("Inhalant");
		List<Integer> num = dataList
				.matchinCSV(
						checkWords,
						"C:\\Users\\ADMIN\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\PatientTaskSWIV\\WEB-INF\\patientData.csv");
		assertEquals(2, num.size());
	}

}
