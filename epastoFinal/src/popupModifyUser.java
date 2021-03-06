import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;


public class popupModifyUser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nomTextField;
	private JTextField loginTextField;

	UserModel _userModel = new UserModel();
	AppliModel _appliModel = new AppliModel();
	AccueilView _accueilView;
	private JTextField droitTextField;
	private JLabel infoLabel = new JLabel("") ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			popupModifyUser dialog = new popupModifyUser();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		  this.setVisible(true);
		}
	
	public void stop() {
		  this.setVisible(false);
		}
	
	/**
	 * Create the dialog.
	 */
	public popupModifyUser() {
		
		//D�finit un titre pour notre fen�tre
		this.setTitle("Ajouter un utilisateur");
		//D�finit sa taille : 400 pixels de large et 100 pixels de haut
		this.setSize(600, 600);
		//Nous demandons maintenant � notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel nomNewLabel = new JLabel("Nom utilisateur");
		nomNewLabel.setBounds(33, 42, 100, 23);
		contentPanel.add(nomNewLabel);
		
		nomTextField = new JTextField();
		nomTextField.setBounds(164, 42, 86, 20);
		contentPanel.add(nomTextField);
		nomTextField.setColumns(10);
		
		JLabel loginNewLabel = new JLabel("Login");
		loginNewLabel.setBounds(33, 107, 100, 23);
		contentPanel.add(loginNewLabel);
		
		loginTextField = new JTextField();
		loginTextField.setBounds(164, 104, 86, 20);
		contentPanel.add(loginTextField);
		loginTextField.setColumns(10);
		
		JLabel lblDroit = new JLabel("Droit");
		lblDroit.setToolTipText("1 : tout les droits \r\n : droit limit  \r\n : droit restreint   ");
		lblDroit.setBounds(33, 172, 102, 14);
		contentPanel.add(lblDroit);
		
		droitTextField = new JTextField();
		droitTextField.setBounds(164, 166, 86, 20);
		contentPanel.add(droitTextField);
		droitTextField.setColumns(10);
		
		infoLabel.setBounds(33, 209, 275, 20);
		contentPanel.add(infoLabel);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						/*if((!getNomTextField().isEmpty()) && (!getLoginTextField().isEmpty()) && (getDroitTextField()!=0))
						{
							if(!_userModel.verifier(getLoginTextField(), getMdpPasswordField()))
							{
								_userModel.inserer(getNomTextField(), getLoginTextField(), getDroitTextField());
								_appliModel.remplirTable(_accueilView.getTable_1(),"SELECT * FROM utilisateur;");
								setInfoLabel("Utilisateur ajout� ...");
							}
							else
							{
								setInfoLabel("Utilisateur d�ja existant ...");
							}
						}
						else setInfoLabel("Tout les champs doivent �tres rempli ... ");*/
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent a) {
						stop();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getNomTextField() {
		return nomTextField.getText().toString();
	}

	public void setNomTextField(String nomTextField) {
		this.nomTextField.setText(nomTextField);
	}

	public String getLoginTextField() {
		return loginTextField.getText().toString();
	}

	public void setLoginTextField(String loginTextField) {
		this.loginTextField.setText(loginTextField);
	}


	public Integer getDroitTextField() {
		
		if(droitTextField.getText().toString().isEmpty()) return 0;
		else return Integer.parseInt(droitTextField.getText().toString());
	}

	public void setDroitTextField(Integer droitTextField) {
		this.droitTextField.setText(droitTextField.toString());
	}
	
	public void setInfoLabel(String _infoLabel){
		infoLabel.setText(_infoLabel);
	}
	
	public String getInfoLabel() {
		return infoLabel.getText().toString();
	}
}
