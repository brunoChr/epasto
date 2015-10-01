/**
 * 
 */

/**
 * @author b.christol
 *
 */

import java.sql.*;

public class LoginModel
{
	private Connection _conn;

	public LoginModel()
	{
		String nomUser = "bruno"; // Utilisateur de la BD
		String passwd = "Bruno@1552"; // Password de l'utilisateur de la BD
		String url = "jdbc:mysql://localhost/"; // Serveur de la BD
		String nomBase = "epasto"; // Nom de la BD sur laquelle nous allons accéder
		
		_conn = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			_conn=DriverManager.getConnection(url+nomBase, nomUser, passwd);
			System.out.println("Log: connected to database " + nomBase);
		}

		catch (SQLException ex1)
		{
			System.out.println("Error: erreur de type SQL: " + ex1.getMessage());
		}
		
		catch (Exception ex2)
		{
			System.out.println("Error: erreur de type lang: " + ex2.getMessage());
		}	
	}
	
	public void fermerConnexion()
	{
		try
		{
			if (_conn != null)
				this._conn.close();
		}
		catch (SQLException ex1)
		{
			System.out.println("Error: erreur de type SQL: " + ex1.getMessage());
		}
	}

	public boolean estValide(String login, String mdp)
	{
		boolean trouve=false;
		try
		{
			String requete = new String("SELECT id, nom, login FROM utilisateur WHERE login=? AND mdp=MD5(?);");
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, login);
			stmt.setString(2, mdp);
			ResultSet rs = stmt.executeQuery();

			if (rs.next ())
			{
				trouve = true;
			}		
			
			rs.close();
			stmt.close();
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
		return trouve;
	}
	
	public String oubliMP(String login)
	{

		try
		{
			String requete = new String("SELECT id, nom, login,mdp FROM utilisateur where login=?;"); //AND mdp=MD5(?)
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, login);
			//stmt.setString(2, mdp);
			ResultSet rs = stmt.executeQuery();
			
			//On récupère les MetaData
		    //ResultSetMetaData resultMeta = rs.getMetaData();
		      
			if (rs.next ())
			{
				return rs.getString("mdp");
			}		
			
			rs.close();
			stmt.close();
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
		return "yes";
	}
	
	
}



