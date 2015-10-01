import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */

/**
 * @author b.christol
 *
 */
public class LoginEventForget implements ActionListener{

	private LoginController _loginController;
	
	public LoginEventForget(LoginController _loginController) {
		// TODO Auto-generated constructor stub
		this._loginController = _loginController;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Bouton oubli ...");
		_loginController.boutonOubli();
	}
}
