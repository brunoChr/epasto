import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

//import de.micromata.opengis.kml.v_2_2_0.Lod;











import javax.swing.tree.TreeModel;

import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactory;

import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;


@SuppressWarnings("serial")
public class AccueilView extends JFrame  {
	
	/**seb**/
	private JPanel contentPane;
	//private AppliModel _appliModel;
	private JTable table_1;
    BufferedImage image;
    private DefaultMutableTreeNode racine;
    private DefaultMutableTreeNode racine_Alerte;
    private final JTree tree;
    private final JTree tree_1;
    private AppliController _appliController;
    private Maps _maps;
    private JTable table;
    private JComboBox combo;
    
    /**fin seb***/
    
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AccueilView frame = new AccueilView();
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
	@SuppressWarnings("serial")
	public AccueilView(AppliController appliController)  {
		this._appliController = appliController;				 //seb CODE
		//Définit un titre pour notre fenêtre
		this.setTitle("E-pasto");
		//Définit sa taille : 400 pixels de large et 100 pixels de haut
		this.setSize(800, 600);
		//Nous demandons maintenant à notre objet de se positionner au centre
		this.setLocationRelativeTo(null);
		
		//Termine le processus lorsqu'on clique sur la croix rouge
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		//setBounds(0, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 769, 546);
		contentPane.add(tabbedPane);
        
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Accueil", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane_1.setBounds(10, 368, 754, 139);
		panel.add(scrollPane_1);
		
		
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
			
		setTable(new JTable());
		scrollPane_1.setViewportView(getTable());
		getTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTable().setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"Point n\u00B0", "Latitude", "Longitude", "Altitude"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		
		
		_appliController.getAppliModel().remplirTable(table, "SELECT idPoint, latitude, longitude,altitude FROM zone ");

		_maps = new Maps(_appliController);
		_maps.afficherMaps("PratAlbis.kml");

		JInternalFrame internalFrame = new JInternalFrame("");
		internalFrame.setBorder(null);
		internalFrame.setFrameIcon(null);
		internalFrame.getContentPane().add(_maps.getMapViewer());		
		internalFrame.setBounds(10, 30, 744, 327);
		panel.add(internalFrame);
		internalFrame.setVisible(true);
		
		
	
		/*
		 * 		_maps = new Maps(_appliController);

		JInternalFrame internalFrame = new JInternalFrame("Carte");
		internalFrame.setFrameIcon(null);
		internalFrame.getContentPane().add(_maps.getMapViewer());		
		internalFrame.setBounds(10, 11, 349, 270);
		panel.add(internalFrame);
		internalFrame.setVisible(true);
	
		 */
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("G\u00E9rer zone", null, panel_1, null);
		panel_1.setLayout(null);
		

		tree = new JTree(this.racine);
		/*********************/
		/******seb debut*****/
		/*********************/
		_appliController.getAppliModel().rafraichirTree(tree);

		tree.setBounds(10, 11, 380, 496);
		panel_1.add(tree);
		this.racine = new DefaultMutableTreeNode(); 
		
		// Récupère la valeur de l'arbre cliquer
//		tree.addTreeSelectionListener(new TreeSelectionListener(){
//
//		      public void valueChanged(TreeSelectionEvent event) {
//		        if(tree.getLastSelectedPathComponent() != null){
//		          System.out.println(tree.getLastSelectedPathComponent().toString());
//		        }
//		      }
//		}); 
		
		
		/*boutons de gérerzone*/
		
		JButton btnAjouter = new JButton("Ajouter");
		
		
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked.");
				_appliController.boutonAjouterZone();
			}
		});
		
		btnAjouter.setBounds(400, 10, 152, 23);
		panel_1.add(btnAjouter);

		/** seb 25/11/14 **/
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked.");
				_appliController.boutonModifierZone();
				verifierSelectionModifier(tree, 1);
			}
		});
		/** seb 25/11/14 fin**/
		btnModifier.setBounds(400, 50, 152, 23);
		panel_1.add(btnModifier);

		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verifierSelection(tree, 1);
				_appliController.getAppliModel().rafraichirTree(tree);
			}
		});
		btnSupprimer.setBounds(400, 90, 152, 23);
		panel_1.add(btnSupprimer);
		/***********************/
		/**********seb fin******/
		/***********************/
		
		/*** 25/11 bruno **/
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 0, 744, 30);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Changer de zones :");
		lblNewLabel.setBounds(10, 0, 126, 30);
		panel_4.add(lblNewLabel);

		String[] tfLabels = new String[getTreeZone().getRowCount()];
		//System.out.println("zone:"+ tree.getPathForRow(5).toString().substring(12));
		
		for (int i = 1; i < getTreeZone().getRowCount(); i++)
		{
			tfLabels[i-1] = tree.getPathForRow(i).toString();
		}
		
        final GeoPosition pratAlbis = new GeoPosition(42.917452, 1.564703); 
        final GeoPosition urola = new GeoPosition( 43.182110, -2.354716); 

		combo = new JComboBox(tfLabels);
		combo.setSelectedIndex(0);
		combo.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				//TileFactory factory = factories.get(combo.getSelectedIndex());
				//mapViewer.setTileFactory(factory);
				switch(combo.getSelectedIndex()){
				case 0 :
					_maps.getMapViewer().setAddressLocation(pratAlbis);
					break;
				case 1 :
					_maps.getMapViewer().setAddressLocation(urola);
					break;
				default :
					_maps.getMapViewer().setAddressLocation(pratAlbis);
					break;
				}				
			}
		});
		
		panel_4.setLayout(new GridLayout());
		panel_4.add(combo);
		
		panel.add(panel_4);
		
		
		/*** 25/11 bruno fin **///
		
		
		/***********************/
		/**********pilou start******/
		/***********************/

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("G\u00E9rer alertes", null, panel_2, null);
		panel_2.setLayout(null);

		tree_1 = new JTree(this.racine_Alerte);
		_appliController.getAppliModel().rafraichirTreeAlerte(tree_1);
		panel_2.add(tree_1);
		this.racine_Alerte = new DefaultMutableTreeNode(); 
		
		tree_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tree_1.setBounds(10, 11, 380, 496);

		JButton button = new JButton("Supprimer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verifierSelection(tree_1, 2);
				_appliController.getAppliModel().rafraichirTreeAlerte(tree_1);
			}
		});
		button.setBounds(400, 90, 152, 23);
		panel_2.add(button);

		JButton button_1 = new JButton("Modifier");
		button_1.setBounds(400, 50, 152, 23);
		panel_2.add(button_1);

		JButton button_2 = new JButton("Ajouter");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Clicked.");
				_appliController.boutonAjouterAlerte();
			}
		});
		button_2.setBounds(400, 10, 152, 23);
		panel_2.add(button_2);
		
		/***********************/
		/**********pilou end******/
		/***********************/

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Param\u00E8tres", null, panel_3, null);
		panel_3.setLayout(null);
		
		
		JButton btnAjouterUnNouvel = new JButton(
				"Ajouter un nouvel utilisateur");
		btnAjouterUnNouvel.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent a) {
				_appliController.boutonAjouterUser();
//				System.out.println("Nouvel utilisateur bouton");
//				try {
//					popupAddUser dialog = new popupAddUser(appliController);
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//					dialog.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
		});
		btnAjouterUnNouvel.setBounds(207, 11, 349, 23);
		panel_3.add(btnAjouterUnNouvel);

		JButton btnNewButton = new JButton("Modifier utilisateur");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				System.out.println("Modifier utilisateur bouton");

			}
		});
		
		btnNewButton.setBounds(207, 45, 349, 23);
		panel_3.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Supprimer utilisateur");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				_appliController.boutonSupprimerUser();
			}
		});
		
		btnNewButton_1.setBounds(207, 79, 349, 23);
		panel_3.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 108, 744, 399);
		panel_3.add(scrollPane);
				
		setTable_1(new JTable());
		scrollPane.setViewportView(getTable_1());
		getTable_1().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getTable_1().setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"id", "Nom", "Login", "Password", "Date", "Droit"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(3).setResizable(false);
		table_1.getColumnModel().getColumn(4).setResizable(false);
		table_1.getColumnModel().getColumn(5).setResizable(false);
	
		_appliController.getAppliModel().remplirTable(table_1,"SELECT * FROM utilisateur;");

		this.setVisible(true);
	}

	public JTable getTable_1() {
		return table_1;
	}

	
	public void setTable_1(JTable table_1) {
		this.table_1 = table_1;
		/*table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Point p = e.getPoint ();
				int colonne = getTable_1().columnAtPoint (p)+1;
				int ligne =  getTable_1().rowAtPoint (p)+1;

				// si la souris n'a pas clic sur une cellule :
				if ( (0 == colonne) || (0 == ligne) )
				{
					
					System.out.println("   ligne :    colonne :  ");
				}
				else
				{

					System.out.println("   ligne : "+ligne+"  colonne : "+colonne);
				}
			}
		});*/
	}
	/**seb CODE***/
	public JTree getTreeZone(){
		return tree;
	}
	public void start(){
		this.setVisible(true);
	}
	public void stop(){
		this.setVisible(false);
	}
	
	public void verifierSelectionModifier(JTree tree, int a){
		try {
			if(tree.getLastSelectedPathComponent() != null){
//			int n = JOptionPane.showConfirmDialog(null,"Are you sure you want delete – " + tree.getLastSelectedPathComponent() + "?","",JOptionPane.YES_NO_CANCEL_OPTION);          
//					           
//					//the user has clicked the cross
//					if(n == -1)
//					{
//					   return;
//					}
//					           
//					//the user has clicked cancel
//					if(n == 2)
//					{
//					   return;
//					}
//					 
//					//yes
//					if(n == 0){
						if(a==1){ //ZONE
							_appliController.getAppliModel().supprimerZone(tree.getLastSelectedPathComponent().toString());
						   	System.out.println("La zone :" +tree.getLastSelectedPathComponent().toString()+"a été supprimer");
						}
						if(a==2){ //ALERTE
							_appliController.getAppliModel().supprimerAlertes(tree.getLastSelectedPathComponent().toString());
						   	System.out.println("La zone :" +tree.getLastSelectedPathComponent().toString()+"a été supprimer");
						}
						
					//}
					//else System.out.println("Veuillez selectionner une zone pour la modifier !");
					     
					
					           
					//no
//					if(n == 1){
//					    return;
//					}
			} else {
				System.out.println("Veuillez selectionner une zone à modifier !");
				
			}
					
			System.out.println("Modifier utilisateur bouton");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void verifierSelection(JTree tree, int a){
		try {
			if(tree.getLastSelectedPathComponent() != null){
			int n = JOptionPane.showConfirmDialog(null,"Etes-vous sur de vouloir supprimer – " + tree.getLastSelectedPathComponent() + "?","",JOptionPane.YES_NO_CANCEL_OPTION);          
					           
					//the user has clicked the cross
					if(n == -1)
					{
					   return;
					}
					           
					//the user has clicked cancel
					if(n == 2)
					{
					   return;
					}
					 
					//yes
					if(n == 0){
						if(a==1){
							_appliController.getAppliModel().supprimerZone(tree.getLastSelectedPathComponent().toString());
						   	System.out.println("La zone :" +tree.getLastSelectedPathComponent().toString()+"a été supprimer");
						}
						if(a==2){
							_appliController.getAppliModel().supprimerAlertes(tree.getLastSelectedPathComponent().toString());
						   	System.out.println("La zone :" +tree.getLastSelectedPathComponent().toString()+"a été supprimer");
						}
						
					}
					else System.out.println("Veuillez selectionner une zone pour la supprimer !");
					     
					
					           
					//no
					if(n == 1){
					    return;
					}
			} else {
				System.out.println("Veuillez selectionner une zone à supprimer !");
				
			}
					
			System.out.println("Supprimer utilisateur bouton");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public JTable getTable() {
		return table;
	}


	
	/**seb CODE fin***/
	
	/**pilou CODE***/
	public JTree getTreeAlerte(){
		return tree_1;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public Maps get_maps() {
		return _maps;
	}

	
	private static String getTreeText(TreeModel model, Object object, String indent) {
	    String myRow = indent + object + "\n";
	    for (int i = 0; i < model.getChildCount(object); i++) {
	        myRow += getTreeText(model, model.getChild(object, i), indent + "  ");
	    }
	    return myRow;
	}

	public JComboBox getCombo() {
		return combo;
	}

	public void setCombo(JComboBox combo) {
		this.combo = combo;
	}
}