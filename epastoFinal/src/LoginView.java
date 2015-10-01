/**
 * 
 */

/**
 * @author b.christol
 *
 */

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class LoginView extends JFrame  {

	private JTextField _loginTextField = new JTextField("");
	private JPasswordField _passwdPasswordField = new JPasswordField();
	private LoginController _loginController;
	
	public LoginView(LoginController loginController) {
		this._loginController = loginController;
		JLabel loginLabel = new JLabel("Login");
		JLabel passwdLabel = new JLabel("Password");
		
		JButton validerButton = new JButton("Valider");
		JButton forgetButton = new JButton("Forget Password");

		//Définit un titre pour notre fenêtre
		this.setTitle("Connexion");
	    //Définit sa taille : 400 pixels de large et 100 pixels de haut
		this.setSize(400, 100);
	    //Nous demandons maintenant à notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
	    //Termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		Container pan = this.getContentPane();
		pan.setLayout(new GridLayout(3,2));
		
		pan.add(loginLabel);
		pan.add(_loginTextField);
		pan.add(passwdLabel);
		pan.add(_passwdPasswordField);
		pan.add(forgetButton);
		pan.add(validerButton);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			} catch (InstantiationException e1) {
			e1.printStackTrace();
			} catch (IllegalAccessException e1) {
			e1.printStackTrace();	
			} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
			}
		
			SwingUtilities.updateComponentTreeUI(this);
			
		this.pack();
		
		validerButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Bouton valider ...");
				try {
					_loginController.boutonValider();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	
		forgetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Bouton oubli ...");
				_loginController.boutonOubli();
			}
		});
	}	
	public void afficher(boolean b) {
		// TODO Auto-generated method stub
		if(b) this.setVisible(true);
		else this.setVisible(false);
	}
	
	public void afficherMessage(String msg)
	{
		JOptionPane.showMessageDialog(this, msg, "Information", JOptionPane.INFORMATION_MESSAGE);		
	}
	
	public String getLogin()
	{
		return _loginTextField.getText().toString();
	}
	
	@SuppressWarnings("deprecation")
	public String getPassword()
	{
		return _passwdPasswordField.getText().toString();
	}
}
