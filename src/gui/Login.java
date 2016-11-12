/*==================================================*/
/*=====================Package======================*/
/*==================================================*/
package gui;
/*==================================================*/
/*=====================Imports======================*/
/*==================================================*/
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import buttons.LoginButton;
import controlador.Controlador;
import exceptions.ConnectionException;
import exceptions.ParameterException;
import exceptions.UsuarioException;
/*==================================================*/
/*===================End Imports====================*/
/*==================================================*/
/**
 * JFrame principal que solicitara credenciales.
 * @version 1.0
 * @author ezequiel.de-luca 
 */
public class Login
{
	/*==================================================*/
	/*====================Constants=====================*/
	/*==================================================*/
	private final int intWidth = 400;
	private final int intHeight = 250;
	/*==================================================*/
	/*====================Variables=====================*/
	/*==================================================*/
	private JFrame objFrame;
	private JLabel objLabelLogo;
	private JLabel objLabelTitle1;
	private JLabel objLabelTitle2;
	private JLabel objLabelUserName;
	private JLabel objLabelPassword;
	private JButton objButtonLogin;
	private JTextField objTextBoxUserName;
	private JPasswordField objTextBoxPassword;

	private File objFile;
	private BufferedImage objImage;
	/*==================================================*/
	/*===================Constructor====================*/
	/*==================================================*/
	/**
	 * Constructor for main JFrame. It will require log in credentials.
	 * @throws IOException file not found
	 */
	public Login () throws IOException
	{
		/*==================================================*/
		/*================Create Java Frame=================*/
		/*==================================================*/
		this.createJavaFrame();
		/*==================================================*/
		/*=================Create Elements==================*/
		/*==================================================*/
		this.createElements();
		/*==================================================*/
		/*=======Add Created Elements and Show JFrame=======*/
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
	/*=================Create Elements==================*/
	/*==================================================*/
	/**
	 * Create each ones of the individual elements to be inserted on the JFrame: EYLogo; Main Title and Sub-Title; Box Label and it´s related Entry Text Fields; Login Button.
	 * @throws IOException file not found
	 */
	private void createElements() throws IOException
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Dimension objTitleDimension;
		Dimension objLabelDimension;
		Font objTitleFont;
		Font objLabelFont;
		/*==================================================*/
		/*===============Set Title Dimension================*/
		/*==================================================*/
		objTitleDimension = new Dimension(250, 30);
		/*==================================================*/
		/*==================Set Title Font==================*/
		/*==================================================*/
		objTitleFont = new Font("Arial", Font.PLAIN, 20);
		/*==================================================*/
		/*===============Set Label Dimension================*/
		/*==================================================*/
		objLabelDimension = new Dimension(75, 14);
		/*==================================================*/
		/*==================Set Label Font==================*/
		/*==================================================*/
		objLabelFont = new Font("Arial", Font.BOLD, 14);
		/*==================================================*/
		/*===================Create Logo====================*/
		/*==================================================*/
		this.createLogo();
		/*==================================================*/
		/*==================Create Title====================*/
		/*==================================================*/
		this.objLabelTitle1 = this.createLabel("Aplicaciones Interactivas", objTitleDimension, objTitleFont, 115, 10);
		this.objLabelTitle1.setHorizontalAlignment(JLabel.CENTER);
		/*==================================================*/
		/*=================Create SubTitle==================*/
		/*==================================================*/
		this.objLabelTitle2 = this.createLabel("2C2016 Equipo N° 4", objTitleDimension, objTitleFont, 115, 40);
		this.objLabelTitle2.setHorizontalAlignment(JLabel.CENTER);
		/*==================================================*/
		/*================Create User Label=================*/
		/*==================================================*/
		this.objLabelUserName = this.createLabel("User:", objLabelDimension, objLabelFont, 130, 105);
		/*==================================================*/
		/*==============Create Password Label===============*/
		/*==================================================*/
		this.objLabelPassword = this.createLabel("Password:", objLabelDimension, objLabelFont, 110, 132);
		/*==================================================*/
		/*=============Create UsetName TextBox==============*/
		/*==================================================*/
		this.createUserTextBox();
		/*==================================================*/
		/*=============Create Password TextBox==============*/
		/*==================================================*/
		this.createPasswordBox();
		/*==================================================*/
		/*===============Create Login Button================*/
		/*==================================================*/
		this.createLoginButton();
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*================Create UADE Logo==================*/
	/*==================================================*/
	/**
	 * Created UADE Logo and set it on the top left corner of the JFrame.
	 * @throws IOException - EYLogo not found.
	 */
	private void createLogo() throws IOException
	{
		/*==================================================*/
		/*===================Get EY Icon====================*/
		/*==================================================*/
		this.objFile = new File("images\\uade.jpg");
		this.objImage = ImageIO.read(this.objFile);
		/*==================================================*/
		/*================Create Logo Label=================*/
		/*==================================================*/
		this.objLabelLogo = new JLabel(new ImageIcon(this.objImage));
		/*==================================================*/
		/*==================Set Logo Size===================*/
		/*==================================================*/
		this.objLabelLogo.setSize(new Dimension(100, 100));
		/*==================================================*/
		/*================Set Logo Location=================*/
		/*==================================================*/
		this.objLabelLogo.setLocation(0, 0);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*==============Create Label To Frame===============*/
	/*==================================================*/
	/**
	 * Creates a new JLabel with the defined parameters (Text: String, Size: Dimension, Font Type: Font and Location--> x, y coordinates: Integer)
	 * @param strTitle Label Text
	 * @param objDimension Label Dimension
	 * @param objFont Label Font
	 * @param intXLocation Label Row Location
	 * @param intYLocation Label Column Location
	 * @return JLabel
	 */
	private JLabel createLabel(String strTitle, Dimension objDimension, Font objFont, int intXLocation, int intYLocation)
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		JLabel objLabel;
		/*==================================================*/
		/*================Create Title Label================*/
		/*==================================================*/
		objLabel = new JLabel(strTitle);
		/*==================================================*/
		/*==================Set Title Size==================*/
		/*==================================================*/
		objLabel.setSize(objDimension);
		/*==================================================*/
		/*==================Set Title Font==================*/
		/*==================================================*/
		objLabel.setFont(objFont);
		/*==================================================*/
		/*================Set Title Location================*/
		/*==================================================*/
		objLabel.setLocation(intXLocation, intYLocation);
		/*==================================================*/
		/*===================Return Label===================*/
		/*==================================================*/
		return objLabel;
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*===============Create User TextBox================*/
	/*==================================================*/
	/**
	 * Creates a JTextField box for typing the Username.
	 */
	private void createUserTextBox()
	{
		/*==================================================*/
		/*=============Create UserName TextBox==============*/
		/*==================================================*/
		this.objTextBoxUserName = new JTextField();
		/*==================================================*/
		/*============Set UserName TextBox Size=============*/
		/*==================================================*/
		this.objTextBoxUserName.setSize(new Dimension(150, 20));
		/*==================================================*/
		/*============Set UserName TextBox Font=============*/
		/*==================================================*/
		this.objTextBoxUserName.setFont(new Font("Arial", Font.PLAIN, 15));
		/*==================================================*/
		/*==========Set UserName TextBox Location===========*/
		/*==================================================*/
		this.objTextBoxUserName.setLocation(200, 105);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===============Create Password Box================*/
	/*==================================================*/
	/**
	 * Creates a JPasswordField box for typing the Password.
	 */
	private void createPasswordBox()
	{
		/*==================================================*/
		/*=============Create Password TextBox==============*/
		/*==================================================*/
		this.objTextBoxPassword = new JPasswordField();
		/*==================================================*/
		/*============Set Password TextBox Size=============*/
		/*==================================================*/
		this.objTextBoxPassword.setSize(new Dimension(150, 20));
		/*==================================================*/
		/*============Set Password TextBox Font=============*/
		/*==================================================*/
		this.objTextBoxPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		/*==================================================*/
		/*==========Set Password TextBox Location===========*/
		/*==================================================*/
		this.objTextBoxPassword.setLocation(200, 130);
	}
	/*==================================================*/
	/*==================End Procedure===================*/
	/*==================================================*/
	/*==================================================*/
	/*===============Create Login Button================*/
	/*==================================================*/
	/**
	 * Created Login Button and set it on a pre-defined position.
	 * @throws IOException SAM Controls Master file not found
	 */
	private void createLoginButton() throws IOException
	{
		/*==================================================*/
		/*=============Create Password TextBox==============*/
		/*==================================================*/
		this.objButtonLogin = new JButton("Login");
		/*==================================================*/
		/*============Set Password TextBox Size=============*/
		/*==================================================*/
		this.objButtonLogin.setSize(100, 20);
		/*==================================================*/
		/*============Set Password TextBox Font=============*/
		/*==================================================*/
		this.objButtonLogin.setFont(new Font("Arial", Font.PLAIN, 15));
		/*==================================================*/
		/*==========Set Password TextBox Location===========*/
		/*==================================================*/
		this.objButtonLogin.setLocation(160, 170);
		/*==================================================*/
		/*===============Add Action Listener================*/
		/*==================================================*/
		this.objButtonLogin.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					if (Controlador.getInstance().Connect(objTextBoxUserName.getText(), objTextBoxPassword.getText()))
					{
						MainScreen ms= new MainScreen(Controlador.getInstance().currentUser());
						objFrame.dispose();
						objFrame.setVisible(false);

					}
					else
					{
						JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta.", "Error", JOptionPane.WARNING_MESSAGE);
					}
				} catch (ConnectionException | ParameterException | UsuarioException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
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
		this.objFrame.getContentPane().add(this.objLabelLogo);
		this.objFrame.getContentPane().add(this.objLabelTitle1);
		this.objFrame.getContentPane().add(this.objLabelTitle2);
		this.objFrame.getContentPane().add(this.objLabelUserName);
		this.objFrame.getContentPane().add(this.objLabelPassword);
		this.objFrame.getContentPane().add(this.objTextBoxUserName);
		this.objFrame.getContentPane().add(this.objTextBoxPassword);
		this.objFrame.getContentPane().add(this.objButtonLogin);
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
	/*==================================================*/
	/*==================Get User Name===================*/
	/*==================================================*/
	/**
	 * Retrieve the Username typed on the respective text box.
	 * @return String Username
	 */
	public String getUserName()
	{
		/*==================================================*/
		/*=================Return User Name=================*/
		/*==================================================*/
		return this.objTextBoxUserName.getText();
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*===================Get Password===================*/
	/*==================================================*/
	/**
	 * Retrieves the Password typed on the respective box.
	 * @return String Password
	 */
	public String getPassword()
	{
		/*==================================================*/
		/*=================Return Password==================*/
		/*==================================================*/
		return new String(this.objTextBoxPassword.getPassword());
	}
	/*==================================================*/
	/*===================End Function===================*/
	/*==================================================*/
	/*==================================================*/
	/*=======================Main=======================*/
	/*==================================================*/
	/**
	 * Launch the JFrame.
	 * @param args
	 * @throws Throwable
	 */
	public static void main(String[] args) throws Throwable
	{
		/*==================================================*/
		/*====================Variables=====================*/
		/*==================================================*/
		Login obj;
		/*==================================================*/
		/*===================Create Login===================*/
		/*==================================================*/
		obj = new Login();
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
/*==================================================*/