package com.pavan.cvsreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class FileUtils {

	/**
	 * Walk a directory and return all files found based on the filter passed.
	 * 
	 * @return List of files found within the aStartingDir directory hierarchy
	 *         that match the filter
	 * @param aStartingDir
	 * @param filter
	 * @throws FileNotFoundException
	 */

	static public List<File> getFilteredFileListing(File aStartingDir,
			FileFilter filter) throws FileNotFoundException {
		validateDirectory(aStartingDir);
		List<File> result = new ArrayList<File>();
		List<File> filesDirs = Arrays.asList(aStartingDir.listFiles(filter));

		for (File file : filesDirs) {
			if (!file.exists()) {
				System.out
						.println("#### WARNING: file or directory path returned by listFiles() within getFilteredFileListing() could not be found!");
				continue;
			}

			if (file.isFile()) {
				result.add(file);
			}

			if (!file.isFile()) {
				// Skip SVN directories
				if (file.getName().equals(".svn")) {
					continue;
				}

				List<File> deeperList = getFilteredFileListing(file, filter);
				result.addAll(deeperList);
			}
		}
		Collections.sort(result);

		return result;
	}

	/**
	 * Directory is valid if it exists, does not represent a file, and can be
	 * read.
	 * 
	 * @param aDirectory
	 *            file object to check
	 */
	static private void validateDirectory(File aDirectory)
			throws FileNotFoundException {
		if (aDirectory == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!aDirectory.exists()) {
			throw new FileNotFoundException("Directory does not exist: "
					+ aDirectory);
		}
		if (!aDirectory.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: "
					+ aDirectory);
		}
		if (!aDirectory.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: "
					+ aDirectory);
		}
	}

    public static void writeStringToFile(String stringToWrite, String filePath, String encoding) throws Exception
    {
        OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(filePath), encoding);
        output.write(stringToWrite);
        output.close();
    }

    public static void writeStringToFileUsingUTF8Encoding(String stringToWrite, String filePath) throws Exception
    {
        writeStringToFile(stringToWrite, filePath, "UTF-8");
    }
    
	
	public File getLastModifiedFile(String dfolder, String type) throws Exception {
		//System.out.println("1--->"+environment(dfolder));
		System.out.println("1--->"+dfolder);
		File folder = new File(dfolder);
		FilenameFilter ff = new FilesWithExtensionFilterUtil("csv");
		File[] files = folder.listFiles(ff);
		File file= lastModifiedFile(files);
		System.out.println("2--->"+file.toString());
		return file;
	}
	
	public void deleteLastModifiedFile(String dfolder, String type) throws Exception {
		
		
//		System.out.println(environment(dfolder));
		String result=System.getenv(dfolder);
		File folder = new File(result);
		FilenameFilter ff = new FilesWithExtensionFilterUtil("csv");
		File[] files = folder.listFiles(ff);
		File deletefile= lastModifiedFile(files);
		deletefile.delete();
		
	}
	public File lastModifiedFile(File[] files) {
		Arrays.sort(files, new Comparator<Object>(){
			public int compare(Object o1, Object o2) {
				return compare( (File)o1, (File)o2);
			}
			private int compare( File f1, File f2){
				long result = f2.lastModified() - f1.lastModified();
				if( result > 0 ){
					return 1;
				} else if( result < 0 ){
					return -1;
				} else {
					return 0;
				}
			}
		} );
		return Arrays.asList(files).get(0); 
	}

	public String environment (String env) throws Exception {
        String value = System.getenv(env);
        if (value != null) {
        	System.out.println(env + "=======>,"+value);
        	return value;

        } else {
        	System.out.println("in else--->"+env);
        	return null;
        }
   }
	
	public String[] getTitlesInCSV(File latestfile, int expectedTitleLength) throws Exception {
		String[] titledata = new String[expectedTitleLength];
		BufferedReader bufRdr = null;
		try {
			bufRdr = new BufferedReader(new FileReader(latestfile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String line = null;
		String[] data;
		line = bufRdr.readLine();
		data = line.split(",");
		for (int col = 0; col < data.length; col++)
			titledata[col] = data[col].trim();
		bufRdr.close();
		return titledata;
	}
	
	public String[][] getContentsFromCSV(File latestfile,int rows,int columns) throws Exception {
		BufferedReader bufRdr = null;
		bufRdr = new BufferedReader(new FileReader(latestfile));
		String line = null;
		int row = 0;
		String[] data;
		String[][] csvdata= new String[rows][columns];
		//Reading the title row and ignoring those contents.
		bufRdr.readLine();
		while ((line = bufRdr.readLine()) != null) {
				data = line.split("\",\"");
				for (int col = 0; col < data.length; col++) {
					csvdata[row][col] = data[col].trim();
				}
				row++;
		}       
		//close the file
		bufRdr.close();
		return csvdata;
	}

	public void deleteFile(String Folder) throws Exception {
		File file = new File(Folder);
		File[] files = file.listFiles();
		for (File f : files) {
			if (f.getName().startsWith("Data")) {
				f.delete();
			}
		}
	}
}