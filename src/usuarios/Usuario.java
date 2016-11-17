/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package usuarios;
/**
 * @author ezequiel.de-luca
 * @version 1.0
 */
public class Usuario
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private String strUsername;
	private String strPassword;
	private Rol objRol;
	private String strRol;
	
	

	public Usuario(String strUsername, String strPassword, String strRol){
		this.setPassword(strPassword);
		this.setUsername(strUsername);
		this.setRol(strRol);
	}
	
	/**
//	 * Devuelve el rol de un usuario.
//	 * @return String strRol
//	 */
	public String getRol() {
		return strRol;
	}

	/**
//	 * Establece elrol de un usuario.
//	 * @param strRol
//	 */
	public void setRol(String strRol) {
		this.strRol = strRol;
	}
	
	/**
	 * Devuelve el rol de un usuario.
	 * @return Rol objRol
	 */
	public Rol getRol1() {
		return this.objRol;
	}

	/**
	 * Establece elrol de un usuario.
	 * @param objRol
	 */
	public void setRol(Rol objRol) {
		this.objRol = objRol;
	}

	public String getStrUsername() {
		return strUsername;
	}

	public void setStrUsername(String strUsername) {
		this.strUsername = strUsername;
	}

	public String getStrPassword() {
		return strPassword;
	}

	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}

	/**
	 * Establece la contraseña de un usuario.
	 * @param strValue
	 */
	protected void setPassword(String strValue)
	{
		/*==================================================*/
		/*=============Establece la contraseña==============*/
		/*==================================================*/
		this.strPassword = strValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Devuelve la contraseña==============*/
	/*==================================================*/
	/**
	 * Devuelve la contraseña de un usuario.
	 * @return String strPassword
	 */
	protected String getPassword()
	{
		/*==================================================*/
		/*==============Devuelve la contraseña==============*/
		/*==================================================*/
		return this.strPassword;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Set Username===================*/
	/*==================================================*/
	/**
	 * Establece el nombre de usuario de la persona.
	 * @param strValue
	 */
	protected void setUsername(String strValue)
	{
		/*==================================================*/
		/*===================Set Username===================*/
		/*==================================================*/
		this.strUsername = strValue;
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Get Username===================*/
	/*==================================================*/
	/**
	 * @return Devuleve el nombre de usuario.
	 */
	protected String getUsername()
	{
		/*==================================================*/
		/*=================Return Username==================*/
		/*==================================================*/
		return this.strUsername;
	}
	
	public boolean soyUsuario(String strUsuario){
		return this.strUsername==strUsuario;
	}
	
	public boolean passwordVerificacion(String strUsuario, String strPassword){
		return (this.strUsername.equals(strUsuario) && this.strPassword.equals(strPassword));
	}

	
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/