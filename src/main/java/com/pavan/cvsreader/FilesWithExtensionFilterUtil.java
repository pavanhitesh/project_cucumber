package com.pavan.cvsreader;

import java.io.File;
import java.io.FilenameFilter;

public class FilesWithExtensionFilterUtil implements FilenameFilter{

	  String ext; 
	 public FilesWithExtensionFilterUtil(String ext){
	  
	  this.ext="." + ext;
	  }

	  public boolean accept(File dir,String name){
	  return name.endsWith(ext);
	  }
	}