package com.FileReaderClass;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadDataFile {

	public List<String> fetchData(String code, String path) throws Exception {

		String thisString = null;
		BufferedReader br = null;
		FileReader file = null;
		List<String> firstWordList = new ArrayList<String>();

		String firstWord = "";

		try {
			file = new FileReader(path);
			br = new BufferedReader(file);
			while ((thisString = br.readLine()) != null) {
				int i = 0;
				StringBuffer buffer = new StringBuffer(thisString);
				String ICDcode = buffer.substring(0, code.length());

				if (code.equals(ICDcode)) {

					buffer.delete(0, 9);

					while (buffer.charAt(i) != ' ') {
						firstWord += buffer.charAt(i);
						i++;

					}
					if (!firstWordList.contains(firstWord)) {
						firstWordList.add(firstWord);
					}

					firstWord = "";
				}

			}
		}

		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return firstWordList;
	}

	public List<Integer> matchinCSV(List<String> checkWords, String path) {

		BufferedReader br = null;
		String line = null;
		String[] diagnosis = null;

		Map<Integer, String> map = new HashMap<Integer, String>();
		List<Integer> idList = new ArrayList<Integer>();

		try {
			br = new BufferedReader(new FileReader(path));

			while ((line = br.readLine()) != null) {

				String[] patientData = line.split(",");
				int i = Integer.parseInt(patientData[0]);

				map.put(i, patientData[1]);

			}
			for (Map.Entry<Integer, String> m : map.entrySet()) {

				diagnosis = m.getValue().split(" ");

				for (String d : diagnosis) {
					for (String list : checkWords) {
						if (d.equalsIgnoreCase(list)) {

							idList.add(m.getKey());
							break;
						}

					}
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(idList);
		return idList;
	}

}
