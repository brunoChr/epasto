import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;


/**
 * 
 */

/**
 * @author b.christol
 *
 */
public class LoginEvent implements ActionListener{

	private LoginController _loginController;
	
	public LoginEvent(LoginController _loginController) {
		// TODO Auto-generated constructor stub
		this._loginController = _loginController;
		
	}
	
	@Override
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
}
