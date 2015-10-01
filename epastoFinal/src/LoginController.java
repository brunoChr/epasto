import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 
 */

/**
 * @author b.christol
 *
 */
public class LoginController {

	private LoginView _loginView;
	private LoginModel _loginModel;
	private AppliController _applicontroller;
	private AppliModel _applimodel;
	private AccueilView _accueilView;
	
	
	public LoginController() {
		// TODO Auto-generated constructor stub
		_loginView = new LoginView(this);
		_loginModel = new LoginModel();
	}
	
	public void start() {
		// TODO Auto-generated method stub
		_loginView.afficher(true);
	}
	
	public void stop() {
		// TODO Auto-generated method stub
		_loginView.afficher(false);
	}
	
	public void boutonValider() throws Exception
	{
		if(_loginModel.estValide(_loginView.getLogin(),_loginView.getPassword()))
		{
			_loginView.afficherMessage("Login Correct !!");
			
			_applicontroller = new AppliController();
			
			this.stop();
			
			/*if ( Desktop.isDesktopSupported() ) {
				// On récupère l'instance du desktop :
				Desktop desktop = Desktop.getDesktop();
			 
				// On vérifie que la fonction browse est bien supportée :
				if (desktop.isSupported(Desktop.Action.BROWSE)) {
					// Et on lance l'application associé au protocole :
					desktop.browse(new URI("https://smsapi.free-mobile.fr/sendmsg?user=10517589&pass=F79pUES5PIZu7g&msg=Conneectéé%20...%20"));
				}
			}*/
		}
		else _loginView.afficherMessage("Login incorrect !! c'est pas ton compte");
	}
	
	public void boutonOubli()
	{
		System.out.println("oubli");
		_loginView.afficherMessage(_loginModel.oubliMP(_loginView.getLogin()));
	}
	
	
	
	
	public AppliModel getAppliModel(){
		return _applimodel;
	}
	public AccueilView getAccueilView(){
		return _accueilView;
	}
}
