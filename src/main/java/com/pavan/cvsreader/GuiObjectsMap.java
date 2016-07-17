package com.pavan.cvsreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.pavan.utill.WebPage;




public class GuiObjectsMap {
	
	private static final Logger logger = LogManager.getLogger(GuiObjectsMap.class);
	private static Map<String, String> guiMap = new HashMap<String, String>();
	private static Map<String, String> dataMap = new HashMap<String, String>();

	public static Map<String, String> getGuiMap() {
		return guiMap;
	}

	public static Map<String, String> getDataMap() {
		return dataMap;
	}

	private static final String guiMapFilenameRegex = ".*?_gui_map.csv$";
	private static final String dataMapFilenameRegex = ".*?_data.csv$";

	/**
	 * Returns the list of gui map files from the search base path .The search
	 * base path is the folder named library .That is typically where the gui
	 * map files go ,typically into different page action folders
	 * 
	 * @param searchBasePath
	 * @throws Exception
	 */
	public static void loadGuiMapFiles(String searchBasePath) throws Exception {
		ArrayList<File> sortedCSVFiles = new ArrayList<File>();

		if (searchBasePath == null || searchBasePath.equals("")) {
			throw new FileNotFoundException(
					"The path provided to loadGuiMapFiles() was null or empty");
		}

		File basePath = new File(searchBasePath);
		FilenameFilterUtils csvFilenameFilter = new FilenameFilterUtils();

		parseCsvFilesAndLoad(sortedCSVFiles, basePath, csvFilenameFilter,
				guiMapFilenameRegex, guiMap);

	}

	/**
	 * Load the data files from the base path and put it into a hash map
	 * 
	 * @param searchBasePath
	 * @throws Exception
	 */
	public static void loadDataFiles(String searchBasePath) throws Exception {
		ArrayList<File> sortedCSVFiles = new ArrayList<File>();
		if (searchBasePath == null || searchBasePath.equals("")) {
			throw new FileNotFoundException(
					"The path provided to loadGuiMapFiles() was null or empty");
		}

		File basePath = new File(searchBasePath);
		FilenameFilterUtils csvFilenameFilter = new FilenameFilterUtils();
		parseCsvFilesAndLoad(sortedCSVFiles, basePath, csvFilenameFilter,
				dataMapFilenameRegex, dataMap);
	}

	private static void parseCsvFilesAndLoad(ArrayList<File> sortedCSVFiles,
			File basePath, FilenameFilterUtils csvFilenameFilter,
			String filefilterPrefix, Map<String, String> maps)
					throws FileNotFoundException, IOException {
		csvFilenameFilter.setAcceptFilterRegex(filefilterPrefix);

		List<File> csvFiles = FileUtils.getFilteredFileListing(basePath,csvFilenameFilter);
		Iterator<File> csvFileit = csvFiles.iterator();

		while (csvFileit.hasNext()) {
			File csvFile = csvFileit.next();

			sortedCSVFiles.add(0, csvFile);
		}

		Iterator<File> sortedCSVFileIt = sortedCSVFiles.iterator();

		// parse the content of csv file and put it in the hash map
		while (sortedCSVFileIt.hasNext()) {
			parseUIMapCsvFile(sortedCSVFileIt.next(), maps);
		}
	}

	/**
	 * Parse the actual csv file from the list of files and put it into the hash
	 * map.
	 * 
	 * @param csvFile
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void parseUIMapCsvFile(File csvFile,
			Map<String, String> myMap) throws FileNotFoundException,
	IOException {
		String line = null;
		int lineNumber = 0;
		if (csvFile == null) {
			throw new IOException(
					"CSV File object passed to parseUIMapCsvFile() was null.");
		}

		BufferedReader bufRdr = new BufferedReader(new FileReader(
				csvFile.getAbsolutePath()));

		String pageObject = "";

		while ((line = bufRdr.readLine()) != null) {
			++lineNumber;

			/*
			 * Remove any trailing commas that Excel will place on each entry in
			 * the file.
			 */
			line = RemoveTrailingCharacter(line, ",");

			/*
			 * Limit the split to 2 elements, since commas can be used in
			 * locators (i.e. GUI object physical descriptions).
			 */
			String[] lineElements = line.split(",", 2);
			int numLineElements = lineElements.length;

			// Skip comment lines and blank lines
			if (lineElements[0].matches("^#.*")
					|| lineElements[0].matches("^\\s*$")) {
				continue;
			}

			if (numLineElements != 1 && numLineElements != 2) {
				throw new IOException("CSV File Parsing Error: line #"
						+ lineNumber + " in \"" + csvFile.getName()
						+ "\" contains either too few or too many elements!");
			}

			if (numLineElements == 1) {
				int leftBracketLoc = lineElements[0].indexOf("[");
				int rightBracketLoc = lineElements[0].indexOf("]");
				if (leftBracketLoc == -1 || rightBracketLoc == -1) {
					throw new IOException(
							"CSV File Parsing Error: the section name \""
									+ lineElements[0]
											+ "\" in line #"
											+ lineNumber
											+ " in \""
											+ csvFile.getName()
											+ "\" is missing one or both of its brackets!");
				}
			}

			if (numLineElements == 1) {
				pageObject = lineElements[0].trim();
				// Remove brackets around section name
				pageObject = pageObject.substring(1, pageObject.length() - 1);
				// Logical name / locator (physical description) line was found
			} else {
				lineElements[0] = lineElements[0];
				// This is the locator, so we don't want to make it lower case
				lineElements[1] = lineElements[1].trim();
				if (lineElements[1].equals("")) {
					/*
					 * System.out
					 * .println("WARNING: the xpath description for GUI object \""
					 * + lineElements[0] + "\" on line #" + lineNumber +
					 * " in file \"" + csvFile.getName() + "\" is empty.");
					 */
					logger.warn(
							("WARNING: the xpath description for GUI object \""
									+ lineElements[0] + "\" on line #"
									+ lineNumber + " in file \""
									+ csvFile.getName() + "\" is empty."));
				}
				String hashmapKey = pageObject + "." + lineElements[0];
				myMap.put(hashmapKey, lineElements[1]);

			}
		}

		// close the file
		bufRdr.close();
	}

	/**
	 * Helper function to remove the trailing spaces from the csv file
	 * 
	 * @param string
	 * @param charToRemove
	 * @return
	 */
	public static String RemoveTrailingCharacter(String string,
			String charToRemove) {
		int stringLength = string.length();

		while (stringLength > 0
				&& string.lastIndexOf(charToRemove) == (stringLength - 1)) {
			string = string.substring(0, (stringLength - 1));
			stringLength = string.length();
		}

		return (string);
	}

}
