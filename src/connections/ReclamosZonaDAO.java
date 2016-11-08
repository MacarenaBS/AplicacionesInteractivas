/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package connections;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import connections.Database;
import exceptions.ClienteException;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.ReclamoException;
import reclamos.ReclamoZona;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * Reclamos Zona Data Access Object
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class ReclamosZonaDAO
{
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private static ReclamosZonaDAO objInstance;
	private Database objConnection;
	private List<ReclamoZona> colReclamos;
	/*==================================================*/
	/*===================Get Instance===================*/
	/*==================================================*/
	/**
	 * @return Instance of the Data Access Object.
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	public static ReclamosZonaDAO getInstance() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*==========Check If Instance Is Created============*/
		/*==================================================*/
		if (objInstance == null)
		{
			objInstance = new ReclamosZonaDAO();
		}
		/*==================================================*/
		/*==================Return Instance=================*/
		/*==================================================*/
		return objInstance;
	}
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Create a new Reclamo Zona Data Object Connection
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	private ReclamosZonaDAO() throws ConnectionException, ParameterException
	{
		super();
		this.colReclamos = new ArrayList<ReclamoZona>();
		this.setConnection();
	}
	/*==================================================*/
	/*===============End Constructor====================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Set Database===================*/
	/*==================================================*/
	/**
	 * Connection to the Database
	 * @throws ConnectionException 
	 * @throws ParameterException 
	 */
	private void setConnection() throws ConnectionException, ParameterException
	{
		/*==================================================*/
		/*===================Set Database===================*/
		/*==================================================*/
		this.objConnection = new Database(ConfigurationFileDAO.getInstance().getParameter("Server Name"), ConfigurationFileDAO.getInstance().getParameter("Database"), ConfigurationFileDAO.getInstance().getParameter("Username"), ConfigurationFileDAO.getInstance().getParameter("Password"));
		/*==================================================*/
		/*=================Check Connection=================*/
		/*==================================================*/
		if (this.getConnection() == null)
		{
			/*==================================================*/
			/*==========No Se Pudo Connectar a la Base==========*/
			/*==================================================*/
			throw new ConnectionException("No se pudo establecer la conexión con la base de datos");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Get Database===================*/
	/*==================================================*/
	/**
	 * @return Connection to the Database
	 */
	private Connection getConnection()
	{
		/*==================================================*/
		/*==================Return Database=================*/
		/*==================================================*/
		return this.objConnection.getConnection();
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Get Reclamo====================*/
	/*==================================================*/
	/**
	 * Busca un Reclamo de Zona por el número. Primero Busca en cache y si no lo encuentra lo busca en la base de datos.
	 * @param strNumero Número de Reclamo a buscar
	 * @return Reclamo Zona
	 * @throws ReclamoException No se encontró ningún reclamo con el número especificado
	 * @throws ConnectionException La query no se pudo ejecutar en la base de datos
	 * @throws ParameterException 
	 * @throws ClienteException 
	 */
	public ReclamoZona getReclamo(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		ReclamoZona objReclamo;
		/*==================================================*/
		/*===========Recuperar Reclamo Del Cache============*/
		/*==================================================*/
		objReclamo = this.getFromCache(strNumero);
		/*==================================================*/
		/*==============Verificar Si Encontro===============*/
		/*==================================================*/
		if (objReclamo == null)
		{
			/*==================================================*/
			/*===========Recuperar Reclamo De La Base===========*/
			/*==================================================*/
			objReclamo = this.getFromDatabase(strNumero);
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objReclamo;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Get Reclamo From Cache==============*/
	/*==================================================*/
	/**
	 * Recupera un reclamo de la cache.
	 * @param strNumero Número de reclamo a recuperar
	 * @return ReclamoZona
	 */
	private ReclamoZona getFromCache(String strNumero)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolEncontro;
		Iterator<ReclamoZona> objIterator;
		ReclamoZona objReclamo;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objReclamo = null;
		bolEncontro = false;
		objIterator = this.colReclamos.iterator();
		/*==================================================*/
		/*=================Loop de Reclamos=================*/
		/*==================================================*/
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			/*==================================================*/
			/*=================Obtener Reclamo==================*/
			/*==================================================*/
			objReclamo = objIterator.next();
			/*==================================================*/
			/*==============Verificar Si Coincide===============*/
			/*==================================================*/
			bolEncontro = (objReclamo.getNumero().equalsIgnoreCase(strNumero));
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objReclamo;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*============Get Reclamo From Database=============*/
	/*==================================================*/
	/**
	 * Recupera un reclamo de una tabla específica de la base de datos.
	 * @param strNumero Número de reclamo a recuperar
	 * @return ReclamoZona
	 * @throws ReclamoException No se encontró ningún registro con el número especificado
	 * @throws ConnectionException No se pudo ejecutar la query a la base de datos
	 * @throws ParameterException
	 * @throws ClienteException 
	 */
	private ReclamoZona getFromDatabase(String strNumero) throws ReclamoException, ConnectionException, ClienteException, ParameterException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		ReclamoZona objReclamo;
		ResultSet objReclamos;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		objReclamo = null;
		/*==================================================*/
		/*==============Attempt Reclamos Query==============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*===================Get Reclamos===================*/
			/*==================================================*/
			objReclamos = this.objConnection.getResultSet("SELECT * FROM ReclamosZona WHERE strNumero = ".concat(strNumero));
			/*==================================================*/
			/*==============Verificar Si Encontró===============*/
			/*==================================================*/
			if (objReclamos.next())
			{
				/*==================================================*/
				/*==================Crear Reclamo===================*/
				/*==================================================*/
				objReclamo = new ReclamoZona(objReclamos.getString("strNumero"), objReclamos.getString("strDescripcion"), objReclamos.getString("strEstado"), ClientesDAO.getInstance().getCliente(objReclamos.getInt("intCliente")), objReclamos.getString("strZona"));
			}
			else
			{
				/*==================================================*/
				/*No se Encontró Ningún Registro con el Número Especificado*/
				/*==================================================*/
				throw new ReclamoException("No existe ningún reclamo de zona con el número ".concat(strNumero).concat(" en los registros almacenados."));
			}
		}
		/*==================================================*/
		/*=========Catch Contact List Query Errors==========*/
		/*==================================================*/
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===========No se pudo ejecutar la query===========*/
			/*==================================================*/
			throw new ConnectionException("Error con la ejecución de la query en la base de datos.");
		}
		/*==================================================*/
		/*==================Return Results==================*/
		/*==================================================*/
		return objReclamo;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Remover De Cache=================*/
	/*==================================================*/
	/**
	 * Remuevo un reclamo de la cache.
	 * @param objReclamo Reclamo a remover
	 */
	private void removerDeCache(ReclamoZona objReclamo)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Boolean bolEncontro;
		Iterator<ReclamoZona> objIterator;
		ReclamoZona objActual;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		bolEncontro = false;
		objIterator = this.colReclamos.iterator();
		/*==================================================*/
		/*==================Loop Reclamos===================*/
		/*==================================================*/
		while ((objIterator.hasNext()) && (!bolEncontro))
		{
			/*==================================================*/
			/*================Obtiene Un Reclamo================*/
			/*==================================================*/
			objActual = objIterator.next();
			/*==================================================*/
			/*===============Verifica Si Coincide===============*/
			/*==================================================*/
			if (objActual.equals(objReclamo))
			{
				/*==================================================*/
				/*================Remueve El Reclamo================*/
				/*==================================================*/
				this.colReclamos.remove(objActual);
				/*==================================================*/
				/*============Cambia El Falg De Búsqueda============*/
				/*==================================================*/
				bolEncontro = true;
			}
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Actualizar Base==================*/
	/*==================================================*/
	/**
	 * Actualiza el reclamo en la base de datos.
	 * @param objReclamo Reclamo a actualizar
	 */
	private void actualizarBase(ReclamoZona objReclamo)
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Update=================*/
			/*==================================================*/
			this.objConnection.executeQuery("UPDATE ReclamosZona ".concat(
												"SET strDescripcion = '").concat(objReclamo.getDescripción()).concat("', ").concat(
												"strEstado = '").concat(objReclamo.getEstado().toString()).concat("', ").concat(
												"strZona = '").concat(objReclamo.getZona()).concat(
											"' WHERE strNumero = ").concat(String.valueOf(objReclamo.getNumero())));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un Reclamo de Zona");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*====================Actualizar====================*/
	/*==================================================*/
	/**
	 * Actualiza la información de un reclamo tanto en la cache como en la base de datos.
	 * @param objReclamo Reclamo a actualizar
	 */
	public void actualizar(ReclamoZona objReclamo)
	{
		/*==================================================*/
		/*==========Remueve El Reclamo De La Cache==========*/
		/*==================================================*/
		this.removerDeCache(objReclamo);
		/*==================================================*/
		/*==========Agrega El Reclamo Actualizado===========*/
		/*==================================================*/
		this.colReclamos.add(objReclamo);
		/*==================================================*/
		/*================Actualiza La Base=================*/
		/*==================================================*/
		this.actualizarBase(objReclamo);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=================Insertar en Base=================*/
	/*==================================================*/
	/**
	 * Inserta el Reclamo en la tabla Reclamos Zona de la base de datos
	 * @param objReclamo Reclamo a insertar
	 */
	private void insertarEnBase(ReclamoZona objReclamo)
	{
		try
		{
			/*==================================================*/
			/*================Ejecuta el Insert=================*/
			/*==================================================*/
			this.objConnection.executeQuery("INSERT INTO ReclamosZona (strNumero, strDescripcion, strEstado, strZona, intCliente)".concat(
											"VALUES ( ").concat(
												String.valueOf(objReclamo.getNumero())).concat(", '").concat(
												objReclamo.getDescripción()).concat("', '").concat(
												objReclamo.getEstado().toString()).concat("', '").concat(
												objReclamo.getZona()).concat("', ").concat(
												String.valueOf(objReclamo.getCliente().getCodigoPersona())).concat(")"));
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*===============Error Al Actualizar================*/
			/*==================================================*/
			JOptionPane.showMessageDialog(null, "Error al actualizar la base de datos con un nuevo Reclamo de Zona");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=====================Insertar=====================*/
	/*==================================================*/
	/**
	 * Inserta un reclamo de zona en el cache y la tabla correspondiente de la base de datos.
	 * @param objReclamo Reclamo a insertar
	 */
	public void insertar(ReclamoZona objReclamo)
	{
		/*==================================================*/
		/*==========Agrega El Reclamo a la Cache============*/
		/*==================================================*/
		this.colReclamos.add(objReclamo);
		/*==================================================*/
		/*==========Inserta el Reclamo en la Tabla==========*/
		/*==================================================*/
		this.insertarEnBase(objReclamo);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*======================New ID======================*/
	/*==================================================*/
	/**
	 * Get an ID for a new record.
	 * @return ID
	 * @throws ConnectionException Error con la conexión o la consulta a la base de datos.
	 */
	public String newId() throws ConnectionException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		String strAnswer;
		ResultSet objAnswer;
		/*==================================================*/
		/*===============Initialize Variables===============*/
		/*==================================================*/
		strAnswer = null;
		try
		{
			/*==================================================*/
			/*==========Obtener Cantidad de Registros===========*/
			/*==================================================*/
			objAnswer = this.objConnection.getResultSet("SELECT COUNT(*) AS Cantidad FROM ReclamosZona");
			/*==================================================*/
			/*==========Verificar si Ejecuto con Éxito==========*/
			/*==================================================*/
			if (objAnswer == null)
			{
				/*==================================================*/
				/*===========No se encontraron resultados===========*/
				/*==================================================*/
				throw new ConnectionException("No se pudo recuperar la consulta de la base de datos");
			}
			else
			{
				/*==================================================*/
				/*==================Leer Resultado==================*/
				/*==================================================*/
				objAnswer.next();
				/*==================================================*/
				/*=================Obtener Cantidad=================*/
				/*==================================================*/
				strAnswer = "RECZONA".concat(String.valueOf(objAnswer.getInt("Cantidad")));
			}
		}
		catch (SQLException objException)
		{
			/*==================================================*/
			/*================Error de Conexion=================*/
			/*==================================================*/
			throw new ConnectionException("Error en la conexión con la base de datos");
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
	/*======================Close=======================*/
	/*==================================================*/
	/**
	 * Close the connection to the database. In Addition it resets the Instance.
	 * @throws ConnectionException No se puede cerrar la conexión con la base de datos
	 */
	public void close() throws ConnectionException
	{
		/*==================================================*/
		/*============Attempts Connection Close=============*/
		/*==================================================*/
		try
		{
			/*==================================================*/
			/*============Close Database Connection=============*/
			/*==================================================*/
			this.objConnection.close();
			/*==================================================*/
			/*==================Reset Instance==================*/
			/*==================================================*/
			objInstance = null;
		}
		/*==================================================*/
		/*===============Catch Closing Errors===============*/
		/*==================================================*/
		catch (SQLException objException)
		{
			/*==================================================*/
			/*=============Error al Cerrar la Base==============*/
			/*==================================================*/
			throw new ConnectionException("No se puede cerrar la conexión con la base de Datos");
		}
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/