/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package gui;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import usuarios.Usuario;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * JFrame para usuario de Call Center
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class MainScreen
{
	/*==================================================*/
	/*====================Constants=====================*/
	/*==================================================*/
	private final int intWidth = 600;
	private final int intHeight = 300;
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private JFrame objFrame;
	private JMenuBar objMenuBar;
	private JMenu objAdministradorMenu;
	private JMenu objCallCenterMenu;
	private JMenu objConsultaMenu;
	private JMenu objResponsableDistribucionMenu;
	private JMenu objResponsableFactuacionMenu;
	private JMenu objResponsableZonaDeEntregaMenu;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Constructor for main JFrame. It will require log in credentials.
	 * @throws IOException file not found
	 */
	public MainScreen (Usuario us)
	{
		/*==================================================*/
		/*================Create Java Frame=================*/
		/*==================================================*/
		this.createJavaFrame();
		/*==================================================*/
		/*===================Create Menus===================*/
		/*==================================================*/
		this.createMenus(us);
		/*==================================================*/
		/*============Add Elements To Java Frame============*/
		/*==================================================*/
		this.addElements();
		/*==================================================*/
		/*==============Define Exit Operation===============*/
		/*==================================================*/
		this.objFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/*==================================================*/
	/*=================End Constructor==================*/
	/*==================================================*/
	/*==================================================*/
	/*==================Create JFrame===================*/
	/*==================================================*/
	/**
	 * Create the JFrame, set the defined sizes (once defined resizing is not allowed) and title.
	 * No layout is defined, elements will be displayed on matrix position schema.
	 */
	private void createJavaFrame()
	{
		/*==================================================*/
		/*==================Create JFrame===================*/
		/*==================================================*/
		this.objFrame = new JFrame();
		/*==================================================*/
		/*==================Set Frame Size==================*/
		/*==================================================*/
		this.objFrame.setPreferredSize(new Dimension(this.intWidth, this.intHeight));
		/*==================================================*/
		/*=================Prevent Resizing=================*/
		/*==================================================*/
		this.objFrame.setResizable(false);
		/*==================================================*/
		/*==================Set No Layout===================*/
		/*==================================================*/
		this.objFrame.setLayout(null);
		/*==================================================*/
		/*=================Set Frame Title==================*/
		/*==================================================*/
		this.objFrame.setTitle("Aplicaciones Interactivas 2C2016");
		/*==================================================*/
		/*================Set Frame Location================*/
		/*==================================================*/
		this.objFrame.setLocationByPlatform(true);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===========Administrador Clientes Menu============*/
	/*==================================================*/
	/**
	 * Administrador Clientes Menu
	 */
	private void clientesMenu()
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		JMenu objMenu;
		JMenuItem objAlta;
		JMenuItem objBaja;
		JMenuItem objModificacion;
		/*==================================================*/
		/*==================Crear Sub Menu==================*/
		/*==================================================*/
		objMenu = new JMenu("Clientes");
		/*==================================================*/
		/*===================Crear Items====================*/
		/*==================================================*/
		objAlta = new JMenuItem("Alta");
		objBaja = new JMenuItem("Baja");
		objModificacion = new JMenuItem("Modificación");
		/*==================================================*/
		/*==================Agregar Items===================*/
		/*==================================================*/
		objMenu.add(objAlta);
		objMenu.add(objBaja);
		objMenu.add(objModificacion);
		/*==================================================*/
		/*=================Agregar Sub Menu=================*/
		/*==================================================*/
		this.objAdministradorMenu.add(objMenu);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===========Administrador Productos Menu===========*/
	/*==================================================*/
	/**
	 * Administrador Productos Menu
	 */
	private void productosMenu()
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		JMenu objMenu;
		JMenuItem objAlta;
		JMenuItem objBaja;
		JMenuItem objModificacion;
		/*==================================================*/
		/*==================Crear Sub Menu==================*/
		/*==================================================*/
		objMenu = new JMenu("Productos");
		/*==================================================*/
		/*===================Crear Items====================*/
		/*==================================================*/
		objAlta = new JMenuItem("Alta");
		objBaja = new JMenuItem("Baja");
		objModificacion = new JMenuItem("Modificación");
		/*==================================================*/
		/*==================Agregar Items===================*/
		/*==================================================*/
		objMenu.add(objAlta);
		objMenu.add(objBaja);
		objMenu.add(objModificacion);
		/*==================================================*/
		/*=================Agregar Sub Menu=================*/
		/*==================================================*/
		this.objAdministradorMenu.add(objMenu);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===========Administrador Usuarios Menu============*/
	/*==================================================*/
	/**
	 * Administrador Usuarios Menu
	 */
	private void usuariosMenu()
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		JMenu objMenu;
		JMenuItem objAlta;
		JMenuItem objBaja;
		JMenuItem objModificacion;
		/*==================================================*/
		/*==================Crear Sub Menu==================*/
		/*==================================================*/
		objMenu = new JMenu("Usuarios");
		/*==================================================*/
		/*===================Crear Items====================*/
		/*==================================================*/
		objAlta = new JMenuItem("Alta");
		objBaja = new JMenuItem("Baja");
		objModificacion = new JMenuItem("Modificación");
		/*==================================================*/
		/*==================Agregar Items===================*/
		/*==================================================*/
		objMenu.add(objAlta);
		objMenu.add(objBaja);
		objMenu.add(objModificacion);
		/*==================================================*/
		/*=================Agregar Sub Menu=================*/
		/*==================================================*/
		this.objAdministradorMenu.add(objMenu);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*=============Crear Menu Administrador=============*/
	/*==================================================*/
	/**
	 * Menu Administrador
	 */
	private void createMenuAdministrator()
	{
		/*==================================================*/
		/*====================Crear Menu====================*/
		/*==================================================*/
		this.objAdministradorMenu = new JMenu("Administrador");
		/*==================================================*/
		/*=================Crear Sub Menus==================*/
		/*==================================================*/
		this.clientesMenu();
		this.productosMenu();
		this.usuariosMenu();
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Crear Menu Call Center==============*/
	/*==================================================*/
	/**
	 * Menu Call Center
	 */
	private void createMenuCallCenter()
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		JMenuItem objReclamoFacturacion;
		JMenuItem objReclamoZonaDeEntrega;
		JMenuItem objReclamoProducto;
		JMenuItem objReclamoFaltante;
		JMenuItem objReclamoCantidad;
		JMenuItem objReclamoCompuesto;
		/*==================================================*/
		/*====================Crear Menu====================*/
		/*==================================================*/
		this.objCallCenterMenu = new JMenu("CallCenter");
		/*==================================================*/
		/*===================Crear Items====================*/
		/*==================================================*/
		objReclamoFacturacion = new JMenuItem("Nuevo Reclamo Facturación");
		objReclamoZonaDeEntrega = new JMenuItem("Nuevo Reclamo Zona de Entrega");
		objReclamoProducto = new JMenuItem("Nuevo Reclamo Producto");
		objReclamoFaltante = new JMenuItem("Nuevo Reclamo Faltante");
		objReclamoCantidad = new JMenuItem("Nuevo Reclamo Cantidad");
		objReclamoCompuesto = new JMenuItem("Nuevo Reclamo Compuesto");
		/*==================================================*/
		/*==================Agregar Items===================*/
		/*==================================================*/
		this.objCallCenterMenu.add(objReclamoFacturacion);
		this.objCallCenterMenu.add(objReclamoZonaDeEntrega);
		this.objCallCenterMenu.add(objReclamoProducto);
		this.objCallCenterMenu.add(objReclamoFaltante);
		this.objCallCenterMenu.add(objReclamoCantidad);
		this.objCallCenterMenu.add(objReclamoCompuesto);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Create Menus===================*/
	/*==================================================*/
	/**
	 * Create Menu for actions.
	 */
	private void createMenus(Usuario us)
	{
		/*==================================================*/
		/*=================Create Menu Bar==================*/
		/*==================================================*/
		this.objMenuBar = new JMenuBar();
		/*==================================================*/
		/*============Create Administrator Menu=============*/
		/*==================================================*/
		
		switch (us.getRol()){
		case "Administrador":
			this.createMenuAdministrator();
			this.objMenuBar.add(this.objAdministradorMenu);
			break;
		case "CallCenter":
			this.createMenuCallCenter();
			this.objMenuBar.add(this.objCallCenterMenu);
			break;
		case "Consulta":
			this.objConsultaMenu = new JMenu("Consulta");
			this.objMenuBar.add(this.objConsultaMenu);
			break;
		case "ResponsableDistribucion":
			this.objResponsableDistribucionMenu = new JMenu("Resp Distribución");
			this.objMenuBar.add(this.objResponsableDistribucionMenu);
			break;
		case "ResponsableFacturacion":
			this.objResponsableFactuacionMenu = new JMenu("Resp Facturación");
			this.objMenuBar.add(this.objResponsableFactuacionMenu);
			break;
		case "ResponsableZonaDeEntrega":
			this.objResponsableZonaDeEntregaMenu = new JMenu("Resp Zona De Entrega");
			this.objMenuBar.add(this.objResponsableZonaDeEntregaMenu);
			break;
		default:
			JOptionPane.showMessageDialog(null, "El usuario se encuentra mal configurado y no posee un rol acorde al sistema.", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
//		this.createMenuAdministrator();
		/*==================================================*/
		/*=============Create Call Center Menu==============*/
		/*==================================================*/
//		this.createMenuCallCente();
//		this.objConsultaMenu = new JMenu("Consulta");
//		this.objResponsableDistribucionMenu = new JMenu("Resp Distribución");
//		this.objResponsableFactuacionMenu = new JMenu("Resp Facturación");
//		this.objResponsableZonaDeEntregaMenu = new JMenu("Resp Zona De Entrega");
//		this.objMenuBar.add(this.objAdministradorMenu);
//		this.objMenuBar.add(this.objCallCenterMenu);
//		this.objMenuBar.add(this.objConsultaMenu);
//		this.objMenuBar.add(this.objResponsableDistribucionMenu);
//		this.objMenuBar.add(this.objResponsableFactuacionMenu);
//		this.objMenuBar.add(this.objResponsableZonaDeEntregaMenu);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Add Elements To JFrame==============*/
	/*==================================================*/
	/**
	 * Add each one of the created elements to the JFrame, pack them and set the JFrame as visible.
	 */
	private void addElements()
	{
		/*==================================================*/
		/*===================Add Elements===================*/
		/*==================================================*/
		this.objFrame.setJMenuBar(this.objMenuBar);
		/*==================================================*/
		/*================Pack All Elements=================*/
		/*==================================================*/
		this.objFrame.pack();
		/*==================================================*/
		/*================Make Frame Visible================*/
		/*==================================================*/
		this.objFrame.setVisible(true);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
//	public static void main(String[] args) throws Throwable
//	{
//		/*==================================================*/
//		/*====================Variables=====================*/
//		/*==================================================*/
//		MainScreen obj;
//		/*==================================================*/
//		/*===================Create Login===================*/
//		/*==================================================*/
//		obj = new MainScreen();
//		/*==================================================*/
//		/*===================End Objects====================*/
//		/*==================================================*/
//		obj.finalize();
//	}
	/*==================================================*/
	/*=====================End Main=====================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/
/*==================================================*/