/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package exceptions;
/**
 * Database Connection Exception
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class ConnectionException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3372527732718553833L;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Displays an error Message
	 * @param strMessage Message to be displayed
	 */
	public ConnectionException(String strMessage)
	{
		/*==================================================*/
		/*==============Exceptions Constructor==============*/
		/*==================================================*/
		super(strMessage);
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/