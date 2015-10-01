import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA._PolicyStub;

// SEB * CODE
public class AppliController {
	
	private AccueilView _accueilView;
	private AppliModel _appliModel;
	private popupAddZone _zoneAddZone;
	private popupAddAlerte _alerteAddAlerte;
	private popupAddUser _userAddUser;
	private popupModifierZone _zoneModifierZone;

	//private Maps _maps;
	
	public AppliController(){
		_appliModel = new AppliModel();
		//_maps = new Maps(this);
		_accueilView = new AccueilView(this);
		startAccueilView();
	}
	public void boutonAjouterZone(){
		System.out.println("Nouvelle zone bouton");
		_zoneAddZone = new popupAddZone(this);
		_zoneAddZone.start();
	}
	public void boutonValiderZone(){
		System.out.println(_zoneAddZone.getNomTextField()+ _zoneAddZone.getLocalisationTextField());
		_appliModel.ajouterZone(_zoneAddZone.getNomTextField(), _zoneAddZone.getLocalisationTextField());
		_appliModel.rafraichirTree(_accueilView.getTreeZone());
		
		//**//
		_accueilView.getCombo(). addItem(_zoneAddZone.getNomZone());
		_accueilView.get_maps().afficherMaps(_zoneAddZone.getPathFile());
		_zoneAddZone.stop();
	}
	
	public void boutonModifierZone(){
		System.out.println("boutonModifierZone");
		_zoneModifierZone = new popupModifierZone(this);
		_zoneModifierZone.start();
	}
	public void boutonValiderModifierZone(){
		_appliModel.ajouterZone(_zoneModifierZone.getNomTextField(), _zoneModifierZone.getLocalisationTextField());
		_appliModel.rafraichirTree(_accueilView.getTreeZone());
		//**//
		_accueilView.get_maps().afficherMaps(_zoneModifierZone.getPathFile());
		_zoneModifierZone.stop();
	}
	
	/**********pilou Code*********/
	
	public void boutonAjouterAlerte(){
		System.out.println("Nouvelle alerte bouton");
		_alerteAddAlerte = new popupAddAlerte(this);
		_alerteAddAlerte.start();
	}
	public void boutonValiderAlerte(){
		System.out.println(_alerteAddAlerte.getTypeTextField()+ "  " + _alerteAddAlerte.getMsgTextField());
		_appliModel.ajouterAlertes(_alerteAddAlerte.getTypeTextField(), _alerteAddAlerte.getMsgTextField());
		_appliModel.rafraichirTreeAlerte(_accueilView.getTreeAlerte());
		_alerteAddAlerte.stop();
	}
	/**********pilou fin*********/
	
	
	public void startAccueilView(){
		_accueilView.start();
	}
	public AppliModel getAppliModel(){
		return _appliModel;
	}
	/********* ajouter user **********/
	public void boutonAjouterUser(){
		_userAddUser = new popupAddUser(this);
		startPopupAddUser();
	}
	public void boutonSupprimerUser(){
		try {
			if(getAccueilView().getTable_1().getSelectedRow() != -1){
			int n = JOptionPane.showConfirmDialog(null,"Etes-vous sur de vouloir supprimer – " + getAccueilView().getTable_1().getValueAt(getAccueilView().getTable_1().getSelectedRow(), 2) + "?","",JOptionPane.YES_NO_CANCEL_OPTION);          
					           
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
					   
					    //CREATE MODEL INSTANCE FROM EXISTING TABLE
					    DefaultTableModel model = new DefaultTableModel();
					    model = (DefaultTableModel) getAccueilView().getTable_1().getModel();
					    Integer ligneSelec;
					    //DELETE THE SELECTED ROW
					    if((ligneSelec=getAccueilView().getTable_1().getSelectedRow()) != -1){
					    	//model.removeRow(table_1.getSelectedRow());
					    	String id = getAccueilView().getTable_1().getValueAt(ligneSelec, 0).toString();
					    	model.removeRow(ligneSelec);
					    	getAppliModel().requeteSQL("DELETE FROM `epasto`.`utilisateur` WHERE `utilisateur`.`id` ="+id+";");
					    	System.out.println("La ligne :" +ligneSelec.toString()+"a été supprimer");
					    }
					    else System.out.println("Veuillez selectionner une ligne pour la supprimer !");
					               
					    //INSERT A NEW EMPTY ROW
					    //model.addRow(new Object[]{"","",""});
					}
					           
					//no
					if(n == 1){
					    return;
					}
			} else System.out.println("Veuillez selectionner une ligne pour la supprimer !");
					
			System.out.println("Supprimer utilisateur bouton");
			//_appliModel.remplirTable(table_1,"SELECT * FROM utilisateur;");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void startPopupAddUser(){
		_userAddUser.start();
	}
	public AccueilView getAccueilView(){
		return _accueilView;
	}
	public popupAddZone get_zoneAddZone() {
		return _zoneAddZone;
	}
	public void set_zoneAddZone(popupAddZone _zoneAddZone) {
		this._zoneAddZone = _zoneAddZone;
	}
	
	/*public Maps getMaps() {
		return _maps;
	}
	public void setMaps(Maps maps) {
		this._maps = maps;
	}*/
	
}
