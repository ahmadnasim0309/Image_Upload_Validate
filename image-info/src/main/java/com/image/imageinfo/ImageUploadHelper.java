package com.image.imageinfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component  //@Component allows Spring to automatically detect our custom beans
public class ImageUploadHelper {
	
	//Saving image in system
	
//public final String UPLOAD_DIR = "C:\\Users\\a1grdlg5\\Desktop\\APB PROMOTER APP\\image-info\\image-info\\src\\main\\resources\\static\\image";
//---------------------------------------------------------------------------------------------------------
	//Saving image dynamically, to view using url
	
	public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();
	
	public ImageUploadHelper() throws IOException{
	}
//---------------------------------------------------------------------------------------------------------	
	public boolean uploadFile(MultipartFile mf) {
		boolean b = false;
		
		try {
			
			Files.copy(mf.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+mf.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			b = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return b;
	}

}
