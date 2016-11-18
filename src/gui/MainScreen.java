/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package gui;
import java.awt.Container;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connections.ClientesDAO;
import connections.ProductosDAO;
import connections.UsuariosDAO;
import controlador.Controlador;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.UsuarioException;
import model.Producto;
import usuarios.Cliente;
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
	private final int intHeight = 350;
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
	private Font title, labels;
	private Usuario usuario;
	
	
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Constructor for main JFrame. It will require log in credentials.
	 * @throws IOException file not found
	 */
	public MainScreen (Usuario us)
	{
		this.usuario= us;
		this.title= new Font("Georgia", Font.BOLD, 30);
		this.labels=new Font("Arial",Font.BOLD,12);
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
		
		objAlta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Alta cliente");
				CreateClientWindow();
				objFrame.repaint();
				
			}
			
		});
		
		objModificacion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Modificar Cliente");
				ModifyClientWindow();
				objFrame.repaint();
				
			}
			
		});
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
		
		objAlta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Crear Producto");
				CreateProductWindow();
				objFrame.repaint();
			}
			
		});
		
		objModificacion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Modificar Producto");
				ModifyProductWindow();
				objFrame.repaint();
				
			}
			
		});
		
		objBaja.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Eliminar Producto");
				DeleteProductWindow();
				objFrame.repaint();
			}
			
		});
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
		JFrame objFrame= this.objFrame;
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
		/*=================Agregar Comportamiento=================*/
		/*==================================================*/
		objAlta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ALTA USUARIO");
				createUserWindow();
				objFrame.repaint();
			}
			
		});
		
		objModificacion.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("MODIFICAR USUARIO");				
				ModifyUserWindow();
				objFrame.repaint();
				
			}
			
		});
		
		objBaja.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ELIMINAR USUARIO");
				DeleteUserWindow();
				objFrame.repaint();
				
			}
			
		});
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
	
	private void createUserWindow(){
		JLabel lblTitulo, lblUsername, lblPassword, lblReingresarPassword, lblPermisos, lblError;
		JTextField txtFieldUsername; 
		JComboBox cbPermisos;
		JPasswordField passFieldPassword, passFieldReingresarPassword;
		JButton btnAceptar, btnCancelar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		lblError= new JLabel("<html><font color=\"red\">Las contraseñas no coinciden.</font></html>");
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Crear usuario");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(190, 5, 300, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////USERNAME////////////////
		lblUsername =new JLabel("Nombre del usuario:");
		lblUsername.setFont(this.labels);
		lblUsername.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblUsername);
		
		txtFieldUsername= new JTextField();
		txtFieldUsername.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldUsername);
	
		////////////////////////////////////////
		
		
		///////////PASSWORD//////////////////
		lblPassword= new JLabel ("Password:");
		lblPassword.setFont(this.labels);
		lblPassword.setBounds(150, 105, 150, 10);
		this.objFrame.getContentPane().add(lblPassword);
		
		passFieldPassword= new JPasswordField();
		passFieldPassword.setBounds(280, 100, 150, 25);
		this.objFrame.getContentPane().add(passFieldPassword);
		
		lblReingresarPassword= new JLabel ("Reingreso:");
		lblReingresarPassword.setFont(this.labels);
		lblReingresarPassword.setBounds(150, 135, 150, 15);
		this.objFrame.getContentPane().add(lblReingresarPassword);
		
		passFieldReingresarPassword= new JPasswordField();
		passFieldReingresarPassword.setBounds(280, 130, 150, 25);
		passFieldReingresarPassword.setToolTipText("Reingrese la contraseña para confirmación");
		this.objFrame.getContentPane().add(passFieldReingresarPassword);
		
		///////////////////////////////////
		
		////////Permisos////////////////
		lblPermisos= new JLabel ("Permiso:");
		lblPermisos.setFont(this.labels);
		lblPermisos.setBounds(150, 165, 150, 15);
		this.objFrame.getContentPane().add(lblPermisos);
		
		cbPermisos= new JComboBox();
		cbPermisos.addItem("Administrador");
		cbPermisos.addItem("CallCenter");
		cbPermisos.addItem("Consulta");
		cbPermisos.addItem("ResponsableDistribucion");
		cbPermisos.addItem("ResponsableFacturacion");
		cbPermisos.addItem("ResponsableZonaDeEntrega");
		
		cbPermisos.setBounds(280, 160, 150, 25);
		this.objFrame.getContentPane().add(cbPermisos);
		
		////////////////////////////////////
		
		///////////Botones////////////////
		btnAceptar = new JButton("Crear");
		
		btnAceptar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				objFrame.repaint();
				
				if (txtFieldUsername.getText().isEmpty() || txtFieldUsername.getText() == null || 
						passFieldPassword.getText().isEmpty() || passFieldPassword.getText() == null ||
						passFieldReingresarPassword.getText().isEmpty() || passFieldReingresarPassword.getText()==null)
				{
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if (!passFieldPassword.getText().equals(passFieldReingresarPassword.getText()))
				{
					System.out.println("NO MATCH");
					File objFile = new File("images\\ErrorIcon.png");
					Image objImage;
					try {
						objImage = ImageIO.read(objFile).getScaledInstance(20, 20, 0);
						JLabel lblErrorIcon = new JLabel(new ImageIcon(objImage));
						lblErrorIcon.setBounds(370, 130, 150, 25);
						objFrame.getContentPane().add(lblErrorIcon);
						objFrame.getContentPane().add(lblError).setBounds(465, 130, 150, 30);
						objFrame.repaint();
						
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error. Contacte al administrador.", "Error 304", JOptionPane.ERROR_MESSAGE);
					}
					return;
				}
				
				if (txtFieldUsername.getText().equals(passFieldPassword.getText())){
					JOptionPane.showMessageDialog(null, "El usuario y la contraseña no pueden ser iguales.", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(Controlador.getInstance().crearUsuario(txtFieldUsername.getText(), passFieldPassword.getText(), cbPermisos.getSelectedItem().toString()))
				{
					JOptionPane.showMessageDialog(null, "¡Usuario creado con éxito!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
					createUserWindow();
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Error creando el usuario", "Error", JOptionPane.ERROR_MESSAGE);
					createUserWindow();
				}
				
			}
			
		});
		
		this.objFrame.getContentPane().add(btnAceptar).setBounds(150, 200, 140, 25);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				objFrame.getContentPane().removeAll();
				objFrame.repaint();
				
			}
			
		});
		this.objFrame.getContentPane().add(btnCancelar).setBounds(290, 200, 140, 25);

		this.objFrame.repaint();
	
	}
	
	private void ModifyUserWindow(){
		JLabel lblTitulo, lblUsername, lblPassword, lblReingresarPassword, lblPermisos, lblError;
		JTextField txtFieldUsername; 
		JComboBox cbPermisos;
		JPasswordField passFieldPassword, passFieldReingresarPassword;
		JButton btnAceptar, btnCancelar, btnBuscar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		lblError= new JLabel("<html><font color=\"red\">Las contraseñas no coinciden.</font></html>");
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Modificar usuario");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(190, 5, 300, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////USERNAME////////////////
		lblUsername =new JLabel("Nombre del usuario:");
		lblUsername.setFont(this.labels);
		lblUsername.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblUsername);
		
		txtFieldUsername= new JTextField();
		txtFieldUsername.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldUsername);
	
		////////////////////////////////////////
		
		btnBuscar= new JButton("Buscar");
		btnBuscar.setBounds(447, 70, 100, 25);
		this.objFrame.getContentPane().add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Buscar  el usuario
				if (txtFieldUsername.getText().isEmpty() | txtFieldUsername.getText()== null)
				{
					JOptionPane.showMessageDialog(null, "Ingrese un usuario por favor", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try 
				{
					Usuario s= Controlador.getInstance().getUser(txtFieldUsername.getText());
				} catch (ConnectionException e) {
					JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					return;
				} catch (ParameterException e2)
				{
					e2.printStackTrace();
					return;
				}
				catch (UsuarioException e3)
				{
					JOptionPane.showMessageDialog(null, "No se encontro un usuario con ese nombre", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//Si  lo encuentra
				txtFieldUsername.setEditable(false);
				JLabel lblPassword= new JLabel ("Password:"); //*
				lblPassword.setBounds(150, 105, 150, 10);
				objFrame.getContentPane().add(lblPassword);
				
				JPasswordField passFieldPassword= new JPasswordField();
				passFieldPassword.setBounds(280, 100, 150, 25);
				objFrame.getContentPane().add(passFieldPassword);
				
				JLabel lblReingresarPassword= new JLabel ("Reingreso:");
//				lblReingresarPassword.setFont(this.labels);
				lblReingresarPassword.setBounds(150, 135, 150, 15);
				objFrame.getContentPane().add(lblReingresarPassword);
				
				JPasswordField passFieldReingresarPassword= new JPasswordField();
				passFieldReingresarPassword.setBounds(280, 130, 150, 25);
				passFieldReingresarPassword.setToolTipText("Reingrese la contraseña para confirmación");
				objFrame.getContentPane().add(passFieldReingresarPassword);
				
				///////////////////////////////////
				
				////////Permisos////////////////
				JLabel lblPermisos= new JLabel ("Permiso:");
//				lblPermisos.setFont(this.labels);
				lblPermisos.setBounds(150, 165, 150, 15);
				objFrame.getContentPane().add(lblPermisos);
				
				JComboBox cbPermisos= new JComboBox();
				cbPermisos.addItem("Administrador");
				cbPermisos.addItem("CallCenter");
				cbPermisos.addItem("Consulta");
				cbPermisos.addItem("ResponsableDistribucion");
				cbPermisos.addItem("ResponsableFacturacion");
				cbPermisos.addItem("ResponsableZonaDeEntrega");
				
				cbPermisos.setBounds(280, 160, 150, 25);
				objFrame.getContentPane().add(cbPermisos);
				
				////////////////////////////////////
				JButton btnAceptar = new JButton("Modificar");
				
				btnAceptar.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						objFrame.repaint();
						
						if (txtFieldUsername.getText().isEmpty() || txtFieldUsername.getText() == null || 
								passFieldPassword.getText().isEmpty() || passFieldPassword.getText() == null ||
								passFieldReingresarPassword.getText().isEmpty() || passFieldReingresarPassword.getText()==null)
						{
							JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						if (!passFieldPassword.getText().equals(passFieldReingresarPassword.getText()))
						{
							System.out.println("NO MATCH");
							File objFile = new File("images\\ErrorIcon.png");
							Image objImage;
							try {
								objImage = ImageIO.read(objFile).getScaledInstance(20, 20, 0);
								JLabel lblErrorIcon = new JLabel(new ImageIcon(objImage));
								lblErrorIcon.setBounds(370, 130, 150, 25);
								objFrame.getContentPane().add(lblErrorIcon);
								objFrame.getContentPane().add(lblError).setBounds(465, 130, 150, 30);
								objFrame.repaint();
								
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, "Error. Contacte al administrador.", "Error 304", JOptionPane.ERROR_MESSAGE);
							}
							return;
						}
						
						if (txtFieldUsername.getText().equals(passFieldPassword.getText())){
							JOptionPane.showMessageDialog(null, "El usuario y la contraseña no pueden ser iguales.", "Error", JOptionPane.WARNING_MESSAGE);
							return;
						}
						/**Controlador.getInstance().crearUsuario(txtFieldUsername.getText(), passFieldPassword.getText(), cbPermisos.getSelectedItem().toString())
/**Modificar**/			if(Controlador.getInstance().modificarUsuario(txtFieldUsername.getText(), passFieldPassword.getText(), cbPermisos.getSelectedItem().toString()))
						{
							JOptionPane.showMessageDialog(null, "¡Usuario modificado con exito!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
							ModifyUserWindow();
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Error modificando el usuario", "Error", JOptionPane.ERROR_MESSAGE);
							ModifyUserWindow();
						}
						
					}
					
				});
				
				objFrame.getContentPane().add(btnAceptar).setBounds(150, 200, 140, 25);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						objFrame.getContentPane().removeAll();
						objFrame.repaint();
						
					}
					
				});
				objFrame.getContentPane().add(btnCancelar).setBounds(290, 200, 140, 25);
				objFrame.repaint();
			}
			
			
		});
		
		
		
		///////////Botones////////////////
		

		this.objFrame.repaint();
	
	}
	
	private void DeleteUserWindow(){
		JLabel lblTitulo, lblUsername;
		JTextField txtFieldUsername; 

		JButton btnEliminar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Eliminar usuario");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(190, 5, 300, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////USERNAME////////////////
		lblUsername =new JLabel("Nombre del usuario:");
		lblUsername.setFont(this.labels);
		lblUsername.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblUsername);
		
		txtFieldUsername= new JTextField();
		txtFieldUsername.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldUsername);
	
		////////////////////////////////////////
		
		btnEliminar= new JButton("Eliminar");
		btnEliminar.setBounds(447, 70, 100, 25);
		this.objFrame.getContentPane().add(btnEliminar);
		
		btnEliminar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Buscar  el usuario
				if (txtFieldUsername.getText().isEmpty() | txtFieldUsername.getText()== null)
				{
					JOptionPane.showMessageDialog(null, "Ingrese un usuario por favor", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try 
				{
					Usuario s= Controlador.getInstance().getUser(txtFieldUsername.getText());
				} catch (ConnectionException e) {
					JOptionPane.showMessageDialog(null, "Error de conexión", "Error", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					return;
				} catch (ParameterException e2)
				{
					e2.printStackTrace();
					return;
				}
				catch (UsuarioException e3)
				{
					JOptionPane.showMessageDialog(null, "No se encontro un usuario con ese nombre", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(Controlador.getInstance().eliminarUsuario(txtFieldUsername.getText()))
				{
					JOptionPane.showMessageDialog(null, "Usuario eliminado con exito", "¡Enhorabuena!", JOptionPane.INFORMATION_MESSAGE);
					DeleteUserWindow();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Hubo un problema al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
					DeleteUserWindow();
				}

				
				////////////////////////////////////
				
				}	
				});
				
	}
	
	private void CreateProductWindow(){
		JLabel lblTitulo, lblProducto, lblDescripcion, lblPrecio, lblEstado;
		JTextField txtFieldProducto,txtFieldDescripcion,txtFieldPrecio,txtFieldEstado; 
		JCheckBox cbEstado;
		JButton btnAceptar, btnCancelar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Crear Producto");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(190, 5, 300, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////PRODUCTO////////////////
		lblProducto =new JLabel("Nombre del producto:");
		lblProducto.setFont(this.labels);
		lblProducto.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblProducto);
		
		txtFieldProducto= new JTextField();
		txtFieldProducto.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldProducto);
	
		////////////////////////////////////////
		
		
		///////////DESCRIPCION//////////////////
		lblDescripcion= new JLabel ("Descripción:");
		lblDescripcion.setFont(this.labels);
		lblDescripcion.setBounds(150, 105, 150, 10);
		this.objFrame.getContentPane().add(lblDescripcion);
		
		txtFieldDescripcion= new JTextField();
		txtFieldDescripcion.setBounds(280, 100, 150, 25);
		this.objFrame.getContentPane().add(txtFieldDescripcion);
		
		///////////////////////////////////
		
		////////PRECIO////////////////
		lblPrecio= new JLabel ("Precio:");
		lblPrecio.setFont(this.labels);
		lblPrecio.setBounds(150, 135, 150, 15);
		this.objFrame.getContentPane().add(lblPrecio);
		
		txtFieldPrecio= new JTextField();
		txtFieldPrecio.setBounds(280, 130, 150, 25);
		this.objFrame.getContentPane().add(txtFieldPrecio);
		////////////////////////////////////
		
		////////ESTADO////////////////////
		lblEstado= new JLabel ("Estado:");
		lblEstado.setFont(this.labels);
		lblEstado.setBounds(150, 165, 150, 15);
		this.objFrame.getContentPane().add(lblEstado);
		
		cbEstado= new JCheckBox();
		cbEstado.setBounds(343, 165, 150, 15);
		cbEstado.setToolTipText("Si la casilla se encuentra marcada, significa que esta ACTIVO. En caso contrario, INACTIVO.");
		cbEstado.setSelected(true);
		this.objFrame.getContentPane().add(cbEstado);
		
		///////////Botones////////////////
		btnAceptar = new JButton("Crear");
		
		btnAceptar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				objFrame.repaint();
				
				if (txtFieldProducto.getText().isEmpty() || txtFieldProducto.getText() == null || 
						txtFieldDescripcion.getText().isEmpty() || txtFieldDescripcion.getText() == null ||
								txtFieldPrecio.getText().isEmpty() || txtFieldPrecio.getText()==null)
				{
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				try {
					if(Controlador.getInstance().crearProducto(txtFieldProducto.getText(), txtFieldDescripcion.getText(), Float.parseFloat(txtFieldPrecio.getText().replace(",", ".")), cbEstado.isSelected()))
					{
						JOptionPane.showMessageDialog(null, "¡Producto creado con éxito!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
						CreateProductWindow();
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error creando el producto", "Error", JOptionPane.ERROR_MESSAGE);
						CreateProductWindow();
					}
				} catch (NumberFormatException | HeadlessException | ConnectionException | ParameterException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error creando el producto", "Error", JOptionPane.ERROR_MESSAGE);
					CreateProductWindow();
				}
				
			}
			
		});
		
		this.objFrame.getContentPane().add(btnAceptar).setBounds(150, 200, 140, 25);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				objFrame.getContentPane().removeAll();
				objFrame.repaint();
				
			}
			
		});
		this.objFrame.getContentPane().add(btnCancelar).setBounds(290, 200, 140, 25);

		this.objFrame.repaint();
	
	}
	
	private void ModifyProductWindow()
	{
		JLabel lblTitulo, lblCodigo, lblProducto, lblDescripcion, lblPrecio, lblEstado;
		JTextField txtFieldCodigo, txtFieldProducto,txtFieldDescripcion,txtFieldPrecio,txtFieldEstado; 
		JCheckBox cbEstado;
		JButton btnAceptar, btnCancelar, btnBuscar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Modificar Producto");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(170, 5, 350, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////USERNAME////////////////
		lblCodigo =new JLabel("Codigo del producto:");
		lblCodigo.setFont(this.labels);
		lblCodigo.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblCodigo);
		
		txtFieldCodigo= new JTextField();
		txtFieldCodigo.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldCodigo);
	
		////////////////////////////////////////
		
		btnBuscar= new JButton("Buscar");
		btnBuscar.setBounds(447, 70, 100, 25);
		this.objFrame.getContentPane().add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Buscar  el usuario
				if (txtFieldCodigo.getText().isEmpty() | txtFieldCodigo.getText()== null)
				{
					JOptionPane.showMessageDialog(null, "Ingrese un codigo de producto por favor", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Producto p= Controlador.getInstance().getProducto(Integer.parseInt(txtFieldCodigo.getText()));
				if (p == null){
					JOptionPane.showMessageDialog(null, "No hay un producto que coincida con dicho codigo", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//Si  lo encuentra
				txtFieldCodigo.setEditable(false);
				
				JLabel lblProducto= new JLabel ("Producto:"); //*
				lblProducto.setBounds(150, 105, 150, 10);
				objFrame.getContentPane().add(lblProducto);
				
				JTextField txtFieldProducto= new JTextField(p.getTitulo());
				txtFieldProducto.setBounds(280, 100, 150, 25);
				objFrame.getContentPane().add(txtFieldProducto);
				
				JLabel lblDescripcion= new JLabel ("Descripcion:");
//				lblReingresarPassword.setFont(this.labels);
				lblDescripcion.setBounds(150, 135, 150, 15);
				objFrame.getContentPane().add(lblDescripcion);
				
				JTextField txtFieldDescripcion= new JTextField(p.getDescripcion());
				txtFieldDescripcion.setBounds(280, 130, 150, 25);
				objFrame.getContentPane().add(txtFieldDescripcion);
				
				///////////////////////////////////
				
				////////Permisos////////////////
				JLabel lblPrecio= new JLabel ("Precio:");
//				lblPermisos.setFont(this.labels);
				lblPrecio.setBounds(150, 165, 150, 15);
				objFrame.getContentPane().add(lblPrecio);
				
				JTextField txtFieldPrecio= new JTextField(""+p.getPrecio());
				txtFieldPrecio.setBounds(280, 165, 150, 25);
				objFrame.getContentPane().add(txtFieldPrecio);
				////////////////////////////////////
				
				////////ESTADO////////////////////
				JLabel lblEstado= new JLabel ("Estado:");
				lblEstado.setBounds(150, 195, 150, 15);
				objFrame.getContentPane().add(lblEstado);
				
				JCheckBox cbEstado= new JCheckBox();
				cbEstado.setBounds(343, 195, 150, 15);
				cbEstado.setToolTipText("Si la casilla se encuentra marcada, significa que esta ACTIVO. En caso contrario, INACTIVO.");
				cbEstado.setSelected(true);
				objFrame.getContentPane().add(cbEstado);
				////////////////////////////////////
				JButton btnAceptar = new JButton("Modificar");
				
				btnAceptar.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						p.setDescripcion(txtFieldDescripcion.getText());
						p.setPrecio(Float.parseFloat(txtFieldPrecio.getText().replace(",", ".")));
						p.setTitulo(txtFieldProducto.getText());
						p.setBolActivo(cbEstado.isSelected());
						try {
							ProductosDAO.getInstance().modificarProducto(p);
							JOptionPane.showMessageDialog(null, "¡Producto modificado con éxito!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
							ModifyProductWindow();
						} catch (ConnectionException | ParameterException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error modificando el producto", "Error", JOptionPane.ERROR_MESSAGE);
							ModifyProductWindow();
						}
						
					}
				});
				
				objFrame.getContentPane().add(btnAceptar).setBounds(150, 215, 140, 25);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						objFrame.getContentPane().removeAll();
						objFrame.repaint();
					}
				});
				objFrame.getContentPane().add(btnCancelar).setBounds(290, 215, 140, 25);
				objFrame.repaint();
			}
		});
		
		
		
		///////////Botones////////////////
		

		this.objFrame.repaint();
	}
	
	private void DeleteProductWindow(){
		JLabel lblTitulo, lblCodigo, lblProducto, lblDescripcion, lblPrecio, lblEstado;
		JTextField txtFieldCodigo, txtFieldProducto,txtFieldDescripcion,txtFieldPrecio,txtFieldEstado; 
		JCheckBox cbEstado;
		JButton btnAceptar, btnCancelar, btnBuscar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Eliminar Producto");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(170, 5, 350, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////USERNAME////////////////
		lblCodigo =new JLabel("Codigo del producto:");
		lblCodigo.setFont(this.labels);
		lblCodigo.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblCodigo);
		
		txtFieldCodigo= new JTextField();
		txtFieldCodigo.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldCodigo);
	
		////////////////////////////////////////
		
		btnBuscar= new JButton("Buscar");
		btnBuscar.setBounds(447, 70, 100, 25);
		this.objFrame.getContentPane().add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Buscar  el usuario
				if (txtFieldCodigo.getText().isEmpty() | txtFieldCodigo.getText()== null)
				{
					JOptionPane.showMessageDialog(null, "Ingrese un codigo de producto por favor", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Producto p= Controlador.getInstance().getProducto(Integer.parseInt(txtFieldCodigo.getText()));
				if (p == null){
					JOptionPane.showMessageDialog(null, "No hay un producto que coincida con dicho codigo", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//Si  lo encuentra
				txtFieldCodigo.setEditable(false);
				
				JLabel lblProducto= new JLabel ("Producto:"); //*
				lblProducto.setBounds(150, 105, 150, 10);
				objFrame.getContentPane().add(lblProducto);
				
				JTextField txtFieldProducto= new JTextField(p.getTitulo());
				txtFieldProducto.setBounds(280, 100, 150, 25);
				objFrame.getContentPane().add(txtFieldProducto);
				
				JLabel lblDescripcion= new JLabel ("Descripcion:");
//				lblReingresarPassword.setFont(this.labels);
				lblDescripcion.setBounds(150, 135, 150, 15);
				objFrame.getContentPane().add(lblDescripcion);
				
				JTextField txtFieldDescripcion= new JTextField(p.getDescripcion());
				txtFieldDescripcion.setBounds(280, 130, 150, 25);
				objFrame.getContentPane().add(txtFieldDescripcion);
				
				///////////////////////////////////
				
				////////Permisos////////////////
				JLabel lblPrecio= new JLabel ("Precio:");
//				lblPermisos.setFont(this.labels);
				lblPrecio.setBounds(150, 165, 150, 15);
				objFrame.getContentPane().add(lblPrecio);
				
				JTextField txtFieldPrecio= new JTextField();
				txtFieldPrecio.setBounds(280, 165, 150, 25);
				objFrame.getContentPane().add(txtFieldPrecio);
				////////////////////////////////////
				
				////////ESTADO////////////////////
				JLabel lblEstado= new JLabel ("Estado:");
				lblEstado.setBounds(150, 195, 150, 15);
				objFrame.getContentPane().add(lblEstado);
				
				JCheckBox cbEstado= new JCheckBox();
				cbEstado.setBounds(343, 195, 150, 15);
				cbEstado.setToolTipText("Si la casilla se encuentra marcada, significa que esta ACTIVO. En caso contrario, INACTIVO.");
				cbEstado.setSelected(true);
				objFrame.getContentPane().add(cbEstado);
				////////////////////////////////////
				JButton btnAceptar = new JButton("Eliminar");
				
				btnAceptar.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if (Controlador.getInstance().eliminarProducto(Integer.parseInt(txtFieldCodigo.getText())))
						{
							JOptionPane.showMessageDialog(null, "¡Producto eliminado con exito!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
							DeleteProductWindow();
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Hubo un error eliminando el producto.", "Error", JOptionPane.ERROR_MESSAGE);
							DeleteProductWindow();
						}
						objFrame.repaint();
					}
				});
				
				objFrame.getContentPane().add(btnAceptar).setBounds(150, 215, 140, 25);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						objFrame.getContentPane().removeAll();
						objFrame.repaint();
					}
				});
				objFrame.getContentPane().add(btnCancelar).setBounds(290, 215, 140, 25);
				objFrame.repaint();
			}
		});
		
		
		
		///////////Botones////////////////
		

		this.objFrame.repaint();
				
	}
	
	private void CreateClientWindow(){
		JLabel lblTitulo, lblNombre, lblDNI, lblMail, lblTelefono, lblDomicilio,lblEstado;
		JTextField txtFieldNombre,txtFieldDNI,txtFieldMail,txtFieldTelefono, txtFieldDomicilio; 
		JCheckBox cbEstado;
		JButton btnAceptar, btnCancelar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Crear Cliente");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(190, 5, 300, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////PRODUCTO////////////////
		lblNombre =new JLabel("Nombre:");
		lblNombre.setFont(this.labels);
		lblNombre.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblNombre);
		
		txtFieldNombre= new JTextField();
		txtFieldNombre.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldNombre);
	
		////////////////////////////////////////
		
		
		///////////DESCRIPCION//////////////////
		lblDNI= new JLabel ("DNI:");
		lblDNI.setFont(this.labels);
		lblDNI.setBounds(150, 105, 150, 10);
		this.objFrame.getContentPane().add(lblDNI);
		
		txtFieldDNI= new JTextField();
		txtFieldDNI.setBounds(280, 100, 150, 25);
		this.objFrame.getContentPane().add(txtFieldDNI);
		
		///////////////////////////////////
		
		////////PRECIO////////////////
		lblDomicilio= new JLabel ("Domicilio:");
		lblDomicilio.setFont(this.labels);
		lblDomicilio.setBounds(150, 135, 150, 15);
		this.objFrame.getContentPane().add(lblDomicilio);
		
		txtFieldDomicilio= new JTextField();
		txtFieldDomicilio.setBounds(280, 130, 150, 25);
		this.objFrame.getContentPane().add(txtFieldDomicilio);
		////////////////////////////////////
		
		////////TELEFONO////////////////////
		lblTelefono= new JLabel ("Telefono:");
		lblTelefono.setFont(this.labels);
		lblTelefono.setBounds(150, 165, 150, 15);
		this.objFrame.getContentPane().add(lblTelefono);
		
		txtFieldTelefono= new JTextField();
		txtFieldTelefono.setBounds(280, 160, 150, 25);
		this.objFrame.getContentPane().add(txtFieldTelefono);
		
		lblMail= new JLabel ("Mail:");
		lblMail.setFont(this.labels);
		lblMail.setBounds(150, 195, 150, 15);
		this.objFrame.getContentPane().add(lblMail);
		
		txtFieldMail= new JTextField();
		txtFieldMail.setBounds(280, 190, 150, 25);
		this.objFrame.getContentPane().add(txtFieldMail);
		
		lblEstado= new JLabel ("Estado:");
		lblEstado.setFont(this.labels);
		lblEstado.setBounds(150, 225, 150, 15);
		this.objFrame.getContentPane().add(lblEstado);
		
		cbEstado= new JCheckBox();
		cbEstado.setBounds(343, 220, 150, 25);
		cbEstado.setToolTipText("Si la casilla se encuentra marcada, significa que esta ACTIVO. En caso contrario, INACTIVO.");
		cbEstado.setSelected(true);
		this.objFrame.getContentPane().add(cbEstado);
		
		///////////Botones////////////////
		btnAceptar = new JButton("Crear");
		
		btnAceptar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				objFrame.repaint();
				
				if (txtFieldNombre.getText().isEmpty() || txtFieldNombre.getText() == null || 
						txtFieldDNI.getText().isEmpty() || txtFieldDNI.getText() == null ||
								txtFieldMail.getText().isEmpty() || txtFieldMail.getText()==null ||
								txtFieldTelefono.getText().isEmpty() || txtFieldTelefono.getText()==null ||
								txtFieldMail.getText().isEmpty() || txtFieldMail.getText()==null
								)
				{
					JOptionPane.showMessageDialog(null, "Debe completar todos los campos para continuar", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				try {
					if(Controlador.getInstance().crearCliente(txtFieldNombre.getText(),Integer.parseInt(txtFieldDNI.getText()),txtFieldMail.getText(), txtFieldTelefono.getText(),txtFieldMail.getText()))
					{
						JOptionPane.showMessageDialog(null, "¡Cliente creado con éxito!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
						CreateClientWindow();
						
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Error creando el cliente", "Error", JOptionPane.ERROR_MESSAGE);
						CreateClientWindow();
					}
				} catch (NumberFormatException | HeadlessException | ConnectionException | ParameterException e1) {
					JOptionPane.showMessageDialog(null, "Error creando el cliente", "Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
			}
			
		});
		
		this.objFrame.getContentPane().add(btnAceptar).setBounds(150, 250, 140, 25);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				objFrame.getContentPane().removeAll();
				objFrame.repaint();
				
			}
			
		});
		this.objFrame.getContentPane().add(btnCancelar).setBounds(290, 250, 140, 25);

		this.objFrame.repaint();
	
	}
	
	private void ModifyClientWindow()
	{
		JLabel lblTitulo, lblCodigo;
		JTextField txtFieldCodigo; 
		JCheckBox cbEstado;
		JButton btnAceptar, btnCancelar, btnBuscar;
		JFrame objFrame= this.objFrame;
		Usuario us= this.usuario;
		
		this.objFrame.getContentPane().removeAll();
		
	
		lblTitulo= new JLabel ("Modificar Cliente");
		lblTitulo.setFont(this.title);
		lblTitulo.setBounds(170, 5, 350, 50);
		this.objFrame.getContentPane().add(lblTitulo);
		
		//////////////USERNAME////////////////
		lblCodigo =new JLabel("Codigo del Cliente:");
		lblCodigo.setFont(this.labels);
		lblCodigo.setBounds(150, 75, 150, 10);
		this.objFrame.getContentPane().add(lblCodigo);
		
		txtFieldCodigo= new JTextField();
		txtFieldCodigo.setBounds(280, 70, 150, 25);
		this.objFrame.getContentPane().add(txtFieldCodigo);
	
		////////////////////////////////////////
		
		btnBuscar= new JButton("Buscar");
		btnBuscar.setBounds(447, 70, 100, 25);
		this.objFrame.getContentPane().add(btnBuscar);
		
		btnBuscar.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Buscar  el usuario
				if (txtFieldCodigo.getText().isEmpty() | txtFieldCodigo.getText()== null)
				{
					JOptionPane.showMessageDialog(null, "Ingrese un codigo de producto por favor", "Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Cliente c= Controlador.getInstance().getCliente(Integer.parseInt(txtFieldCodigo.getText()));
				if (c == null){
					JOptionPane.showMessageDialog(null, "No hay un cliente que coincida con dicho codigo", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//Si  lo encuentra
				txtFieldCodigo.setEditable(false);
				
				JLabel lblNombre= new JLabel ("Nombre:"); //*
				lblNombre.setBounds(150, 105, 150, 10);
				objFrame.getContentPane().add(lblNombre);
				
				JTextField txtFieldNombre= new JTextField(c.getNombre());
				txtFieldNombre.setBounds(280, 100, 150, 25);
				objFrame.getContentPane().add(txtFieldNombre);
				
				JLabel lblDni= new JLabel ("Dni:");
//				lblReingresarPassword.setFont(this.labels);
				lblDni.setBounds(150, 135, 150, 15);
				objFrame.getContentPane().add(lblDni);
				
				JTextField txtFieldDni= new JTextField(c.getDNI().toString());
				txtFieldDni.setBounds(280, 130, 150, 25);
				objFrame.getContentPane().add(txtFieldDni);
				
				///////////////////////////////////
				
				////////Permisos////////////////
				JLabel lblDomicilio= new JLabel ("Domicilio:");
//				lblPermisos.setFont(this.labels);
				lblDomicilio.setBounds(150, 165, 150, 15);
				objFrame.getContentPane().add(lblDomicilio);
				
				JTextField txtFieldDomicilio= new JTextField(c.getDomicilio());
				txtFieldDomicilio.setBounds(280, 165, 150, 25);
				objFrame.getContentPane().add(txtFieldDomicilio);
				
				JLabel lblTelefono= new JLabel ("Telefono:");
//				lblPermisos.setFont(this.labels);
				lblTelefono.setBounds(150, 195, 150, 15);
				objFrame.getContentPane().add(lblTelefono);
				
				JTextField txtFieldTelefono= new JTextField(""+c.getTelefono());
				txtFieldTelefono.setBounds(280, 195, 150, 25);
				objFrame.getContentPane().add(txtFieldTelefono);
				
				JLabel lblMail= new JLabel ("Mail:");
//				lblPermisos.setFont(this.labels);
				lblMail.setBounds(150, 225, 150, 15);
				objFrame.getContentPane().add(lblMail);
				
				JTextField txtFieldMail= new JTextField(""+c.getMail());
				txtFieldMail.setBounds(280, 225, 150, 25);
				objFrame.getContentPane().add(txtFieldMail);
				////////////////////////////////////
				
				////////ESTADO////////////////////
				JLabel lblEstado= new JLabel ("Estado:");
				lblEstado.setBounds(150, 255, 150, 15);
				objFrame.getContentPane().add(lblEstado);
				
				JCheckBox cbEstado= new JCheckBox();
				cbEstado.setBounds(343, 255, 150, 15);
				cbEstado.setToolTipText("Si la casilla se encuentra marcada, significa que esta ACTIVO. En caso contrario, INACTIVO.");
				cbEstado.setSelected(true);
				objFrame.getContentPane().add(cbEstado);
				////////////////////////////////////
				JButton btnAceptar = new JButton("Modificar");
				
				btnAceptar.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
//						c.(txtFieldDescripcion.getText());
//						p.setPrecio(Float.parseFloat(txtFieldPrecio.getText().replace(",", ".")));
//						p.setTitulo(txtFieldProducto.getText());
//						p.setBolActivo(cbEstado.isSelected());
						try {
							Controlador.getInstance().modificarCliente(txtFieldNombre.getText(), Integer.parseInt(txtFieldDni.getText()), txtFieldDomicilio.getText(), txtFieldTelefono.getText(), txtFieldMail.getText());
							JOptionPane.showMessageDialog(null, "¡Cliente modificado con éxito!", "Enhorabuena", JOptionPane.INFORMATION_MESSAGE);
							ModifyClientWindow();
						} catch (ConnectionException | ParameterException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error modificando el cliente", "Error", JOptionPane.ERROR_MESSAGE);
							ModifyClientWindow();
						}
						
					}
				});
				
				objFrame.getContentPane().add(btnAceptar).setBounds(150, 215, 140, 25);
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						objFrame.getContentPane().removeAll();
						objFrame.repaint();
					}
				});
				objFrame.getContentPane().add(btnCancelar).setBounds(290, 215, 140, 25);
				objFrame.repaint();
			}
		});
		
		
		
		///////////Botones////////////////
		

		this.objFrame.repaint();
	}
	
	public static void main(String[] args) throws Throwable
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		MainScreen obj;
		Usuario u= new Usuario("admin","admin","Administrador");
		/*==================================================*/
		/*===================Create Login===================*/
		/*==================================================*/
		obj = new MainScreen(u);
		/*==================================================*/
		/*===================End Objects====================*/
		/*==================================================*/
		obj.finalize();
	}
	/*==================================================*/
	/*=====================End Main=====================*/
	/*==================================================*/
}
/*==================================================*/
/*====================End Class=====================*/