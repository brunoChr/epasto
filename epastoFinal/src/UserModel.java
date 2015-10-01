import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class UserModel 
{
    private Connection _conn;
    
    public UserModel()
    {
        String nomUser = "root"; // Utilisateur de la BD
        String passwd = "root"; // Password de l'utilisateur de la BD
        String url = "jdbc:mysql://localhost/"; // Serveur de la BD
        String nomBase = "epasto"; // Nom de la BD sur laquelle nous allons acceder
        
        _conn = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            _conn=DriverManager.getConnection(url+nomBase, nomUser, passwd);
            System.out.println("Je me connecte a la base de donnees: " + nomBase);
        }
        
        catch (SQLException ex1)
        {
            System.out.println("J'ai detecté une erreur de type SQL: " + ex1.getMessage());
        }
        
        catch (Exception ex2)
        {
            System.out.println("J'ai detecté une erreur de type lang: " + ex2.getMessage());
        }
        System.out.println("Database connected");
    }
   
	// fonction pour montrer 
    public void montrer()
    {
        try
        {
            String requete = new String("SELECT id, nom, login FROM utilisateur;");
            Statement stmt = _conn.createStatement();
            ResultSet rs = stmt.executeQuery(requete);
            
    		System.out.println(requete);
    		
            while ( rs.next() ) 
			{
					String nom = rs.getString("login");
                	System.out.println(nom);
					// Ã  affiner
            }
        }
        catch (SQLException ex3)
        {
            while (ex3 != null)
            {
                System.out.println(ex3.getSQLState());
                System.out.println(ex3.getMessage());
                System.out.println(ex3.getErrorCode());
                ex3=ex3.getNextException();			
            }	
        }
        
    }
    
	public boolean verifier(String login, String mdp)
	{	try
		{	String requete = new String("SELECT login FROM utilisateur WHERE login like ? ;"); //OR mdp like MD5(?)
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString (1,login);
			//stmt.setString (2,mdp);
			ResultSet rs = stmt.executeQuery();
			if (rs.next())
				{	
				System.out.println("Utilisateur existant");
				return true;
				}
			else
			{	System.out.println("Utilisateur non existant");
				return false;
			}				
		}
		
		catch (SQLException ex5)
		{
			while (ex5 !=null)
			{
				System.out.println(ex5.getSQLState());
				System.out.println(ex5.getMessage());
				System.out.println(ex5.getErrorCode());
				ex5=ex5.getNextException();
			}
			return false;
		}
					
	}

    public void inserer(String nom, String login, String mdp, Integer droit)
	{	try
		{	
			String requete = new String("INSERT INTO utilisateur (`id`, `nom`, `login`, `mdp`, `date`,`droit` ) VALUES (NULL, ? , MD5(?) , ? , NOW(), ?);");

			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, nom);
			stmt.setString(2, login);
			stmt.setString(3, mdp);
			stmt.setInt(4, droit);
			System.out.println(stmt.toString());

			stmt.executeUpdate(); 
						
		}
				
		catch (SQLException ex4)
		{
			while (ex4 !=null)
			{
				System.out.println(ex4.getSQLState());
				System.out.println(ex4.getMessage());
				System.out.println(ex4.getErrorCode());
				ex4=ex4.getNextException();
			}
		}				
	}
	
    
    public void fermerConnexion()
    {
        try
        {
            if (_conn != null)
                _conn.close();
        }
        catch (SQLException ex1)
        {
            System.out.println("J'ai detecté une erreur de type SQL: " + ex1.getMessage());
        }
    }
}
