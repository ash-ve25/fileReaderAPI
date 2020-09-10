
package com.ashish.fileinputoutput.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ashish.fileinputoutput.BaseResponse;
import com.ashish.fileinputoutput.entity.FileDTO;

@RestController
@RequestMapping("/")
public class HomeController {

	private static Logger log = (Logger) LoggerFactory.getLogger(HomeController.class);

	
	@RequestMapping("/")
	public ModelAndView check() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/getAllFiles")
	public BaseResponse getAllFiles(@RequestBody FileDTO fileDTO) {

		
		BaseResponse response = new BaseResponse(true);
		log.info("getAllFiles api called");
		try {
			File dir = new File(fileDTO.getPath());
			File[] files = dir.listFiles();
			
			List<FileDTO> fileList=getFileDetails(files, fileDTO);

			response.addResult("FilesList",fileList);
			response.addMessage("File are Successfully Fetched...");

		} catch (Exception e) {
			e.printStackTrace();
			response.addMessage(e.toString());
			response.setSuccess(false);
			return response;
		}
		return response;
	}

	@RequestMapping("/lastModifiedFiles")
	public BaseResponse lastModiFiles(@RequestBody FileDTO fileDTO) {
		
		BaseResponse response = new BaseResponse(true);
		log.info("lastModifiedFiles api called");
		try {
			File file = new File(fileDTO.getPath());
			File[] files = file.listFiles();
			File[] fileArray=new File[5];
			
			Arrays.sort(files, new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					
					Long date1=o1.lastModified();
					Long date2=o2.lastModified();
					int check=date2.compareTo(date1);
					return check;
				}

			});
			
			
			
			int modifiedFilesToBeDisplayed=5;
			for (int filecounter = 0; filecounter < modifiedFilesToBeDisplayed; filecounter++) {
				
				fileArray[filecounter] = files[filecounter];
			}
			
			
			List<FileDTO> fileList=getFileDetails(fileArray, fileDTO);

			response.addResult("FilesList",fileList);

			response.addMessage("Last Modified Files are Successfully Fetched...");
		} catch (Exception e) {
			e.printStackTrace();
			response.addMessage(e.toString());
			response.setSuccess(false);
			return response;
		}
		return response;
	}

	@RequestMapping("/read")
	public BaseResponse read(@RequestBody FileDTO fileDTO) {
		BaseResponse response = new BaseResponse(true);
		log.info("read api called");
		
		
		try {

			String data = getFileInfo(fileDTO.getName(), fileDTO);
			response.addResult(fileDTO.getName(), data);
			response.addMessage("successfully fetch the data...");

		} catch (IOException e) {
			e.printStackTrace();
			response.addMessage(e.toString());
			response.setSuccess(false);
			return response;
		}
		return response;
	}

	@RequestMapping(value = "/write")
	public BaseResponse write(@RequestBody FileDTO fileDTO) {
		BaseResponse response = new BaseResponse(true);
		log.info("write api called");
		try {

			FileOutputStream fileOutputStream = new FileOutputStream(fileDTO.getPath() + "//" + fileDTO.getName());
			byte[] b = fileDTO.getData().getBytes();
			fileOutputStream.write(b);
			fileOutputStream.close();

			response.addMessage("successfully Update the data..");
			response.addResult(fileDTO.getName(), fileDTO.getData());
		} catch (Exception e) {
			e.printStackTrace();
			response.addMessage(e.toString());
			response.setSuccess(false);
			return response;
		}

		return response;

	}

	private String getFileInfo(String fileName, FileDTO fileDTO) throws IOException {
		
		String data = "";
		FileInputStream fileInputStream = new FileInputStream(fileDTO.getPath() + "//" + fileName);
		int i = 0;
		while ((i = fileInputStream.read()) != -1) {
			data += (char) i;
		}

		fileInputStream.close();
		return data;
	}

	private List<FileDTO> getFileDetails(File[] files, FileDTO fileDTO) throws IOException {
		
		List<FileDTO> fileList =new ArrayList<>();
		log.info("getFileDetails Called");

		for(File file:files)
		{
			FileDTO obj=new FileDTO();
			
			obj.setName(file.getName());
			obj.setPath(fileDTO.getPath() +"//"+ file.getName());
			
			Date modifiedDate = new Date(file.lastModified());
			DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS ");
			String datetime = dateFormat.format(modifiedDate);
			
			obj.setModifiedDate(datetime);
			obj.setData(getFileInfo(file.getName(),fileDTO));
			
			fileList.add(obj);
		}
		
		return fileList;

	}
	
	

}
