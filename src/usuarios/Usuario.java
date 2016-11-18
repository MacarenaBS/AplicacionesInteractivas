package usuarios;
public class Usuario
{
	private String strUsername;
	private String strPassword;
	private Rol objRol;
	private String strRol;
	public Usuario(String strUsername, String strPassword, String strRol)
	{
		this.setPassword(strPassword);
		this.setUsername(strUsername);
		this.setRol(strRol);
	}
	public String getRol()
	{
		return strRol;
	}
	public void setRol(String strRol)
	{
		this.strRol = strRol;
	}
	public Rol getRol1()
	{
		return this.objRol;
	}
	public void setRol(Rol objRol)
	{
		this.objRol = objRol;
	}
	public void setPassword(String strValue)
	{
		this.strPassword = strValue;
	}
	public String getPassword()
	{
		return this.strPassword;
	}
	public void setUsername(String strValue)
	{
		this.strUsername = strValue;
	}
	public String getUsername()
	{
		return this.strUsername;
	}
	public boolean soyUsuario(String strUsuario)
	{
		return this.strUsername == strUsuario;
	}
	public boolean passwordVerificacion(String strUsuario, String strPassword)
	{
		return (this.strUsername.equals(strUsuario) && this.strPassword.equals(strPassword));
	}
}