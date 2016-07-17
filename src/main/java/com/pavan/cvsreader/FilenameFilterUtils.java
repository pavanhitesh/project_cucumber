package com.pavan.cvsreader;

import java.io.File;
import java.io.FileFilter;

/**
 * 
 * @author Gayathri 
 * Prevents certain files from
 *         appearing when doing a directory listing.We use this
 *     	   when filtering for the list of .csv files in the source code for loading
 *     	   the gui maps .The actual filtering is done in the {@link FileUtils} class 	
 */
public class FilenameFilterUtils implements FileFilter {
	private String acceptFilterRegex = null;

	public boolean accept(File file) {
		boolean acceptFile = true;

		if (acceptFilterRegex != null && !acceptFilterRegex.equals("")) {
			if (file.isFile()) {
				acceptFile = file.getName().matches(acceptFilterRegex);
			}
		}

		return acceptFile;
	}

	public void setAcceptFilterRegex(String regex) {
		acceptFilterRegex = regex;
	}
}