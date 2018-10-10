package au.com.shortcuts.live.functionLibrary.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.openqa.selenium.WebDriver;

import com.csvreader.CsvWriter;

import au.com.FMDP.live.pageObjects.common.CommonPO;
import au.com.FMDP.live.pageObjects.common.ItemListPO;
import au.com.FMDP.live.pageObjects.tools.ClientImportPO;
import au.com.FMDP.utilities.JavaHelpers;
import au.com.FMDP.utilities.SeleniumHelpers;

public class ClientImportLib
{
	WebDriver driver;
	SeleniumHelpers selenium;
	CommonPO help;
	ClientImportPO clientimport;
	ItemListPO itemlist;
	JavaHelpers java;
	
	
	public ClientImportLib(WebDriver driver)
	{	 
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
        clientimport =  new ClientImportPO(driver);
        help = new CommonPO(driver);
        itemlist = new ItemListPO(driver);
        java = new JavaHelpers();
	}

	
	/**
	 * Download client import template.
	 * @throws InterruptedException
	 */
	public void downloadNewTemplate() throws InterruptedException
	{
		clientimport.downloadTemplate();
		java.sleep(3);
	}
	
	
	/**
	 * Verify file is downloaded or not
	 * @param filedownloadpath	File download location
	 * @param filename	file name
	 * @return	true or false
	 */
	public boolean isFileDownloaded(String filedownloadpath, String filename)
	{
		boolean isFilePresent = false;
	    File dir = new File(filedownloadpath);
	    File[] dir_contents = dir.listFiles();
	  	    
	    for (int i = 0; i < dir_contents.length; i++)
	    {
	    	if (dir_contents[i].getName().equals(filename))
	    	{
	    		isFilePresent=true;	
	    		break;
	    	}
	    }
	    return isFilePresent;
	}
	
	
	/**
	 * Write client details in downloaded template at specific row
	 * @param csvfile		File name along with location	i.e. client import.csv
	 * @param clientvalue	Client details i.e. First name, last name, mobile, etc...
	 * @param rownumber		Row number i.e. 1, 5 ,10 -- 0 is reserved for header
	 */
	public void insertClientDetailsInDownloadedTemplate(String csvfile, List<String> clientvalue, int rownumber)
	{
		try
		{
			CsvWriter csvOutput = new CsvWriter(new FileWriter(csvfile, true), ',');
			for (int i = 1; i <= rownumber; i++)
			{
				csvOutput.endRecord();
			}
			for (String e : clientvalue)
			{
				csvOutput.write(e);
			}
			csvOutput.endRecord();
			csvOutput.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Retrive data from CSV file/template row wise
	 * @param csvfile	File name along with location i.e. client import.csv
	 * @param delimiter	Delimiter format i.e. ","
	 * @param rownumber Row number i.e. 1, 5 ,10 -- 0 is reserved for header
	 * @return	data for given row
	 */
	public String getCSVData(String csvfile, String delimiter, int rownumber)
	{
		String line = "";
		String rowdata = "";
		String[] tempArr;
		
		try
		{
			line = Files.readAllLines(Paths.get(csvfile)).get(rownumber);
			tempArr = line.split(delimiter);
			if (tempArr.length > 0)
				{
					StringBuilder sb = new StringBuilder();
					for (String header : tempArr)
					{
						sb.append(header).append(delimiter);
					}
					rowdata = sb.deleteCharAt(sb.length() - 1).toString();
				}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		return rowdata;
	}
	
	
	/**
	 * Upload CSV template
	 * @param csvfile	File name along with location i.e. client import.csv
	 * @param confirmationmessage	File upload confirmation message
	 * @param successmessage	File upload success message
	 * @throws InterruptedException
	 */
	public void uploadCSVTemplate(String csvfile, String confirmationmessage, String successmessage) throws InterruptedException
	{
		clientimport.uploadCSVTemplate(csvfile, confirmationmessage, successmessage);
	}
	
	
	
}
