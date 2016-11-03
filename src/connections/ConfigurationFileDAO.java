/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package connections;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

import exceptions.ParameterException;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * Configuration File Data Access Object
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class ConfigurationFileDAO
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private BufferedReader objConfigurationFile;
	private static ConfigurationFileDAO objInstance;
	private List<ConfigurationParameter> colParameters;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Configuration File Data Access Object Instance.
	 * @return Instance to retrieve data from the configuration file
	 */
	private ConfigurationFileDAO()
	{
		/*==================================================*/
		/*==============Set Configuration File==============*/
		/*==================================================*/
		this.setConfigurationFile("files".concat(File.separator).concat("config.cfg"));
		/*==================================================*/
		/*=================Initialize List==================*/
		/*==================================================*/
		this.colParameters = new ArrayList<ConfigurationParameter>();
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Get Instance===================*/
	/*==================================================*/
	/**
	 * @return An instance of the file connection object.
	 */
	public static ConfigurationFileDAO getInstance()
	{
		/*==================================================*/
		/*=============Check For Active Instance============*/
		/*==================================================*/
		if (objInstance == null)
		{
			/*==================================================*/
			/*===============Create New Instance================*/
			/*==================================================*/
			objInstance = new ConfigurationFileDAO();
		}
		/*==================================================*/
		/*=================Return Instance==================*/
		/*==================================================*/
		return objInstance;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Get Configuration File==============*/
	/*==================================================*/
	/**
	 * @return Configuration File Buffer Reader
	 */
	private BufferedReader getConfigurationFile()
	{
		/*==================================================*/
		/*===================Return File====================*/
		/*==================================================*/
		return this.objConfigurationFile;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Set Configuration File==============*/
	/*==================================================*/
	/**
	 * Set Configuration File based on the provided File Path.
	 * @param strFilePath Configuration File Path
	 */
	private void setConfigurationFile(String strFilePath)
	{
		/*==================================================*/
		/*============Attempt InputFile Opening=============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*===============Set InputFileStream================*/
			/*==================================================*/
			this.objConfigurationFile = new BufferedReader(new FileReader(new File(strFilePath)));
		}
		/*==================================================*/
		/*============Catch File Not Found Error============*/
		/*==================================================*/
		catch (FileNotFoundException objException)
		{
			/*==================================================*/
			/*===================Show Message===================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Configuration file not found. Please reinstall the application or contact you System Administrator if the error persist.");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==================Get Parameter===================*/
	/*==================================================*/
	/**
	 * Retrieves a specified parameter.
	 * @param strParameter Parameter Description / Name
	 * @return Parameter Value
	 * @throws ParameterException
	 */
	public String getParameter(String strParameter) throws ParameterException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		String strAnswer;
		/*==================================================*/
		/*============Search Parameter In Cache=============*/
		/*==================================================*/
		strAnswer = this.strSearchInCache(strParameter);
		/*==================================================*/
		/*================Check If Not Found================*/
		/*==================================================*/
		if (strAnswer == null)
		{
			/*==================================================*/
			/*======Search Parameter In Configuration File======*/
			/*==================================================*/
			strAnswer = this.strSearchInFile(strParameter);
			/*==================================================*/
			/*================Check If Not Found================*/
			/*==================================================*/
			if (strAnswer == null)
			{
				/*==================================================*/
				/*========Throw Missing Parameter Exception=========*/
				/*==================================================*/
				throw new ParameterException(strParameter);
			}
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return strAnswer;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====Search Configuration Parameter In Cache======*/
	/*==================================================*/
	/**
	 * Retrieve for a specific configuration parameter in the cache. If it is not found it returns null
	 * @param strParameter
	 * @return
	 */
	private String strSearchInCache(String strParameter)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolFound;
		ConfigurationParameter objConfigurationParameter;
		Iterator<ConfigurationParameter> objIterator;
		String strAnswer;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		strAnswer = null;
		bolFound = false;
		objIterator = this.colParameters.iterator();
		/*==================================================*/
		/*=================Parameters Loop==================*/
		/*==================================================*/
		while (objIterator.hasNext() && !bolFound)
		{
			/*==================================================*/
			/*==================Get Parameter===================*/
			/*==================================================*/
			objConfigurationParameter = objIterator.next();
			/*==================================================*/
			/*===========Check If Searched Parameter============*/
			/*==================================================*/
			if (objConfigurationParameter.equals(strParameter))
			{
				/*==================================================*/
				/*====================Get Value=====================*/
				/*==================================================*/
				strAnswer = objConfigurationParameter.getValue();
				/*==================================================*/
				/*================Change Found Flag=================*/
				/*==================================================*/
				bolFound = true;
			}
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return strAnswer;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*======Search Configuration Parameter In File======*/
	/*==================================================*/
	/**
	 * Retrieve for a specific configuration parameter. The parameters that are not "desired" are loaded in the cache.
	 * @param strParameter Desired configuration parameter name
	 * @return Configuration parameter value
	 */
	private String strSearchInFile(String strParameter)
	{	
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolFound;
		ConfigurationParameter objConfigurationParameter;
		String strAnswer;
		String strLine;
		String[] colTemp;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		strAnswer = null;
		bolFound = false;
		/*==================================================*/
		/*===============Attempt File Reading===============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*====================File Loop=====================*/
			/*==================================================*/
			this.setConfigurationFile("files".concat(File.separator).concat("config.cfg")); //Arreglo villero Maca
			
			while (((strLine = this.getConfigurationFile().readLine()) != null) && !bolFound)
			{
				/*==================================================*/
				/*================Check Line Content================*/
				/*==================================================*/
				if (strLine.contains(strParameter))
				{
					/*==================================================*/
					/*==================Split Content===================*/
					/*==================================================*/
					colTemp = strLine.split("=");
					/*==================================================*/
					/*==========Create Configuration Parameter==========*/
					/*==================================================*/
					objConfigurationParameter = new ConfigurationParameter(colTemp[0].replace("[", "").replace("]", ""), colTemp[1]);
					/*==================================================*/
					/*===================Add To List====================*/
					/*==================================================*/
					this.colParameters.add(objConfigurationParameter);
					/*==================================================*/
					/*==================Get Parameter===================*/
					/*==================================================*/
					strAnswer = colTemp[1];
					/*==================================================*/
					/*================Change Found Flag=================*/
					/*==================================================*/
					bolFound = true;
				}
				else
				{
					/*==================================================*/
					/*================Check If Parameter================*/
					/*==================================================*/
					if (strLine.contains("["))
					{
						/*==================================================*/
						/*==================Split Content===================*/
						/*==================================================*/
						colTemp = strLine.split("=");
						/*==================================================*/
						/*==========Create Configuration Parameter==========*/
						/*==================================================*/
						objConfigurationParameter = new ConfigurationParameter(colTemp[0].replace("[", "").replace("]", ""), colTemp[1]);
						/*==================================================*/
						/*===================Add To List====================*/
						/*==================================================*/
						this.colParameters.add(objConfigurationParameter);
					}
				}
			}
		}
		/*==================================================*/
		/*================Catch File Errors=================*/
		/*==================================================*/
		catch (IOException objException)
		{
			/*==================================================*/
			/*===================Show Message===================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Configuration file corrupted. Please reinstall the application or contact you System Administrator if the error persist.");
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return strAnswer;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Close Connection=================*/
	/*==================================================*/
	/**
	 * Terminate the connection with the configuration file.
	 * @throws IOException 
	 */
	public void close() throws IOException
	{
		/*==================================================*/
		/*====================Close File====================*/
		/*==================================================*/
		this.getConfigurationFile().close();
		/*==================================================*/
		/*=================Delete Instance==================*/
		/*==================================================*/
		objInstance = null;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/