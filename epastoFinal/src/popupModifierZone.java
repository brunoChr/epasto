import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;



import java.awt.Color;

// SEB * CODE
public class popupModifierZone extends JDialog {

	private JPanel contentPane;
	//private AccueilView _accueilView;
	private AppliController _appliController;
	private JLabel _label; 								//seb code
	//private AppliModel _appliModel;						//seb CODE
	private JTextField textField;						//seb code
	private JTextField textField_1;						//seb code
	//private FileChooser _fileChooser;					//pilou code
	private String pathKML;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					popupAddZone frame = new popupAddZone();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public popupModifierZone(AppliController appliController) {
		this._appliController= appliController;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 372, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textField_1.getText().isEmpty() && !textField.getText().isEmpty()){
					if(!_appliController.getAppliModel().estValide(textField_1.getText())){
						_appliController.boutonValiderModifierZone();
						
					}
					else{
						_label.setText("Cette zone existe déjà..");
					}
				}
				else _label.setText("Remplir tous les champs..");
				
				
			}
		});	
		
		btnValider.setBounds(215, 165, 89, 23);
		panel.add(btnValider);
		
		textField = new JTextField();
		textField.setBounds(87, 68, 217, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(87, 37, 217, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 39, 31, 14);
		panel.add(lblNom);
		
		JLabel lblLocalisation = new JLabel("Localisation");
		lblLocalisation.setBounds(10, 70, 77, 14);
		panel.add(lblLocalisation);
		
		JLabel lblAjouterUneZone = new JLabel("Ajouter une Zone");
		lblAjouterUneZone.setBounds(87, 11, 110, 14);
		panel.add(lblAjouterUneZone);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stop();
			}
		});
		btnAnnuler.setBounds(90, 165, 89, 23);
		panel.add(btnAnnuler);
		
		_label = new JLabel("");
		_label.setForeground(Color.RED);
		_label.setBounds(87, 126, 217, 14);
		panel.add(_label);
		
		JLabel lblFichierKml = new JLabel("Fichier KML");
		lblFichierKml.setBounds(10, 102, 77, 14);
		panel.add(lblFichierKml);
		
		
		final JLabel labelFile = new JLabel("");
		labelFile.setBounds(204, 99, 100, 23);
		panel.add(labelFile);
		
		JButton btnParcourir = new JButton("Parcourir...");
		btnParcourir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			    JFileChooser chooser = new JFileChooser();
			    FileNameExtensionFilter filter = new FileNameExtensionFilter(
			        "KML file","kml");
			    chooser.setFileFilter(filter);
			    int returnVal = chooser.showOpenDialog(chooser);
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			       labelFile.setText(chooser.getSelectedFile().getName());
			       System.out.println(chooser.getSelectedFile().getAbsolutePath());
			       pathKML = chooser.getSelectedFile().getAbsolutePath();
			    }
//				_fileChooser = new FileChooser();
//				_fileChooser.run();
				
			}
		});

//		btnParcourir.addActionListener(new ActionListener()){
//			public void actionPerformed(ActionEvent e) {
//
//				int returnVal = fc.showOpenDialog(FileChooserDemo.this);
//
//		        if (returnVal == JFileChooser.APPROVE_OPTION) {
//		        	File file = fc.getSelectedFile();
//		            //This is where a real application would open the file.
//		            log.append("Opening: " + file.getName() + "." + newline);
//		        } else {
//		            log.append("Open command cancelled by user." + newline);
//		        }
//		        log.setCaretPosition(log.getDocument().getLength());
//		    }	
//		}
		
		
		btnParcourir.setBounds(97, 99, 100, 23);
		panel.add(btnParcourir);

	}
	
	public String getNomTextField() {
		return textField_1.getText().toString();
	}
	public String getLocalisationTextField() {
		return textField.getText().toString();
	}
	public void start(){
		this.setVisible(true);
	}
	public void stop(){
		this.setVisible(false);
	}
	public String getPathFile(){
		return pathKML;
	}
}


/** VERSION SEB **/
//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//import javax.swing.border.EmptyBorder;
//import java.awt.Color;
//
//// SEB * CODE
//public class popupAddZone extends JDialog {
//
//	private JPanel contentPane;
//	
//	private AppliController _appliController;
//	private JLabel _label; 								//seb code
//	
//	private JTextField textField;						//seb code
//	private JTextField textField_1;						//seb code
//	//
////	/**
////	 * Launch the application.
////	 */
////	public static void main(String[] args) {
////		EventQueue.invokeLater(new Runnable() {
////			public void run() {
////				try {
////					popupAddZone frame = new popupAddZone();
////					frame.setVisible(true);
////				} catch (Exception e) {
////					e.printStackTrace();
////				}
////			}
////		});
////	}
//
//	/**
//	 * Create the frame.
//	 */
//	public popupAddZone(AppliController appliController) {
//		this._appliController= appliController;
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, 372, 220);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane);
//		
//		JPanel panel = new JPanel();
//		contentPane.add(panel, BorderLayout.CENTER);
//		panel.setLayout(null);
//		
//		JButton btnValider = new JButton("Valider");
//		btnValider.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				if (!textField_1.getText().isEmpty() && !textField.getText().isEmpty()){
//					if(!_appliController.getAppliModel().estValide(textField_1.getText())){
//						_appliController.boutonValiderZone();
//					}
//					else{
//						_label.setText("Cette zone existe déjà..");
//					}
//				}
//				else _label.setText("Remplir tous les champs..");
//				
//				
//			}
//		});	
//		
//		btnValider.setBounds(215, 138, 89, 23);
//		panel.add(btnValider);
//		
//		textField = new JTextField();
//		textField.setBounds(87, 67, 217, 20);
//		panel.add(textField);
//		textField.setColumns(10);
//		
//		textField_1 = new JTextField();
//		textField_1.setBounds(87, 36, 217, 20);
//		panel.add(textField_1);
//		textField_1.setColumns(10);
//		
//		JLabel lblNom = new JLabel("Nom");
//		lblNom.setBounds(25, 39, 31, 14);
//		panel.add(lblNom);
//		
//		JLabel lblLocalisation = new JLabel("Localisation");
//		lblLocalisation.setBounds(10, 70, 80, 14);
//		panel.add(lblLocalisation);
//		
//		JLabel lblAjouterUneZone = new JLabel("Ajouter une Zone");
//		lblAjouterUneZone.setBounds(87, 11, 110, 14);
//		panel.add(lblAjouterUneZone);
//		
//		JButton btnAnnuler = new JButton("Annuler");
//		btnAnnuler.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				stop();
//			}
//		});
//		btnAnnuler.setBounds(90, 138, 89, 23);
//		panel.add(btnAnnuler);
//		
//		_label = new JLabel("");
//		_label.setForeground(Color.RED);
//		_label.setBounds(87, 98, 217, 14);
//		panel.add(_label);
//	}
//	
//	public String getNomTextField() {
//		return textField_1.getText().toString();
//	}
//	public String getLocalisationTextField() {
//		return textField.getText().toString();
//	}
//	public void start(){
//		this.setVisible(true);
//	}
//	public void stop(){
//		this.setVisible(false);
//	}
//}
