package com.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.FileReaderClass.ReadDataFile;

@Path("/task")
public class RestClass {

	@POST
	@Path("/add")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String addUser(@FormDataParam("code") String code,
			@Context ServletContext context,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail)
			throws Exception {

		String fileLocation = "D://" + fileDetail.getFileName();

		try {
			FileOutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
				String output = "File successfully uploaded to : "
						+ fileLocation;
				System.out.println(output);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String pathToData = context.getRealPath("/WEB-INF/data.txt");
		// String pathToCSV = context.getRealPath(fileLocation);
		ReadDataFile dataFile = new ReadDataFile();
		List<Integer> listInt = null;
		String result = "Pateint ID : ";

		try {
			List<String> dataList = dataFile.fetchData(code, pathToData);

			if (dataList.isEmpty()) {
				return "Incorrect Code. <h4><a href='/PatientTaskSWIV/'>Try again!</a></h4>";
			}

			listInt = dataFile.matchinCSV(dataList, fileLocation);

			if (listInt.isEmpty()) {
				return "No relevant patient information found. <h4><a href='/PatientTaskSWIV/'>Try again!</a></h4>";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (int j : listInt) {
			result += j + ",";
		}
		return result + "<h5><a href='/PatientTaskSWIV/'>Go again!</a></h5>";

	}
}
