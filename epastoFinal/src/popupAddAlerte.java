import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Pilou CODE
public class popupAddAlerte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldType;
	private JTextField textFieldMsg;
	private JLabel _labelVide; 					//Pilou code
	//private AppliModel _appliModel;				//Pilou code
	private AppliController _appliController;	//Pilou code
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			popupAddAlerte dialog = new popupAddAlerte();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public popupAddAlerte(AppliController appliController) {
		this._appliController= appliController;
		setBounds(100, 100, 409, 270);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 373, 210);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblAjouterUneAlerte = new JLabel("Ajouter une alerte");
		lblAjouterUneAlerte.setBounds(64, 11, 105, 14);
		panel.add(lblAjouterUneAlerte);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(10, 30, 44, 24);
		panel.add(lblType);
		
		JLabel lblMessage = new JLabel("Message");
		lblMessage.setBounds(10, 87, 44, 24);
		panel.add(lblMessage);
		
		textFieldType = new JTextField();
		textFieldType.setBounds(64, 32, 286, 24);
		panel.add(textFieldType);
		textFieldType.setColumns(10);
		
		textFieldMsg = new JTextField();
		textFieldMsg.setBounds(64, 65, 286, 68);
		panel.add(textFieldMsg);
		textFieldMsg.setColumns(10);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textFieldType.getText().isEmpty() && !textFieldMsg.getText().isEmpty()){
					if(!_appliController.getAppliModel().isValid(textFieldType.getText())){
						_appliController.boutonValiderAlerte();
					}
				}
				else _labelVide.setText("Remplir tous les champs..");
				
				
			}
		});
		btnValider.setBounds(232, 176, 89, 23);
		panel.add(btnValider);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		btnAnnuler.setBounds(94, 176, 89, 23);
		panel.add(btnAnnuler);
		
		_labelVide = new JLabel("");
		_labelVide.setForeground(Color.RED);
		_labelVide.setBounds(64, 144, 286, 14);
		panel.add(_labelVide);
	}
	
	public String getTypeTextField() {
		return textFieldType.getText().toString();
	}
	public String getMsgTextField() {
		return textFieldMsg.getText().toString();
	}
	public void start(){
		this.setVisible(true);
	}
	public void stop(){
		this.setVisible(false);
	}
	
}

//import java.awt.BorderLayout;
//import java.awt.FlowLayout;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.SwingConstants;
//import javax.swing.JTextField;
//
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//// Pilou CODE
//public class popupAddAlerte extends JDialog {
//
//	private final JPanel contentPanel = new JPanel();
//	private JTextField textFieldType;
//	private JTextField textFieldMsg;
//	private JLabel _labelVide; 					//Pilou code
//	private AppliModel _appliModel;			//Pilou code
//	
//	/**
//	 * Launch the application.
//	 */
////	public static void main(String[] args) {
////		try {
////			popupAddAlerte dialog = new popupAddAlerte();
////			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
////			dialog.setVisible(true);
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
////	}
//
//	/**
//	 * Create the dialog.
//	 */
//	public popupAddAlerte(AppliController appliController) {
//		setBounds(100, 100, 409, 270);
//		getContentPane().setLayout(new BorderLayout());
//		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//		getContentPane().add(contentPanel, BorderLayout.CENTER);
//		contentPanel.setLayout(null);
//		
//		JPanel panel = new JPanel();
//		panel.setBounds(10, 11, 373, 210);
//		contentPanel.add(panel);
//		panel.setLayout(null);
//		
//		JLabel lblAjouterUneAlerte = new JLabel("Ajouter une alerte");
//		lblAjouterUneAlerte.setBounds(64, 11, 105, 14);
//		panel.add(lblAjouterUneAlerte);
//		
//		JLabel lblType = new JLabel("Type");
//		lblType.setBounds(10, 30, 44, 24);
//		panel.add(lblType);
//		
//		JLabel lblMessage = new JLabel("Message");
//		lblMessage.setBounds(10, 87, 44, 24);
//		panel.add(lblMessage);
//		
//		textFieldType = new JTextField();
//		textFieldType.setBounds(64, 32, 286, 24);
//		panel.add(textFieldType);
//		textFieldType.setColumns(10);
//		
//		textFieldMsg = new JTextField();
//		textFieldMsg.setBounds(64, 65, 286, 68);
//		panel.add(textFieldMsg);
//		textFieldMsg.setColumns(10);
//		
//		JButton btnAjouter = new JButton("Ajouter");
//		btnAjouter.setBounds(232, 176, 89, 23);
//		panel.add(btnAjouter);
//		
//		JButton btnAnnuler = new JButton("Annuler");
//		btnAnnuler.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				stop();
//			}
//		});
//		btnAnnuler.setBounds(94, 176, 89, 23);
//		panel.add(btnAnnuler);
//		
//		_labelVide = new JLabel("");
//		_labelVide.setForeground(Color.RED);
//		_labelVide.setBounds(64, 144, 286, 14);
//		panel.add(_labelVide);
//	}
//	
//	public String getTypeTextField() {
//		return textFieldType.getText().toString();
//	}
//	public String getMsgTextField() {
//		return textFieldMsg.getText().toString();
//	}
//	public void start(){
//		this.setVisible(true);
//	}
//	public void stop(){
//		this.setVisible(false);
//	}
//	
//}
