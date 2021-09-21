package com.image.imageinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ImageUploadController {
	
	@Autowired
	private ImageUploadHelper iuh;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadfile(@RequestParam("myfile") MultipartFile file) {
		
		//FILE INFO.
		
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
        System.out.println(file.getContentType());
    	System.out.println(file.getName());  //Name we have provided in postman
    	
    	try {
    	
    	//VALIDATION
    	
    	if(file.isEmpty()) {
    		return ResponseEntity
    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body("Request must contain some file");
    	}
    	
    	if(!(file.getContentType().equals("image/jpeg") || file.getContentType().equals("image/png"))) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body("File extension not met, File must be in JPEG/PNG format");
    	}
    	
    	//File Upload Code
    	
    	boolean b = iuh.uploadFile(file);
    	if(b) {
    		
    		//To save file in the System, use below code 
    		
    		//return ResponseEntity.ok("File is Successfully Uploaded...!");
   //----------------------------------------------------------------------------------------------------
    	//To save file dynamically & view as url, use below code 
    		
    		return ResponseEntity.ok(ServletUriComponentsBuilder
    				.fromCurrentContextPath()
    				.path("/image/").path(file.getOriginalFilename())
    				.toUriString());
    	}
    	}
    	
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong please try again..!");
	}
}
