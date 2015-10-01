import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class AppliEvent implements ActionListener {
	private AppliController _appliController;
	
	public AppliEvent(AppliController appliController){
		this._appliController = appliController;
	}
	public void actionPerformed(ActionEvent e){
		System.out.println("Clicked.");
		_appliController.boutonAjouterZone();
	}
}


