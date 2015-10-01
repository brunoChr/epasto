import java.sql.*;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class AppliModel {
	
private Connection _conn;
//private AccueilView _accueilView;	
//private ArrayList cache;

	public AppliModel()
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
	
	// SEB CODE
	/**********seb*************/
	/**********creation arbre zone*************/
	
	public void rafraichirTree(JTree tree){
		try {  
			
			String requete = new String("SELECT nom FROM gererzones");
			PreparedStatement stmt = _conn.prepareStatement(requete);
	        ResultSet rs = stmt.executeQuery(requete);
		    ResultSetMetaData resultMeta = rs.getMetaData();
		    
		    DefaultMutableTreeNode root = new DefaultMutableTreeNode("GERER ZONE"); //Premier dossier de l'arbre
		   
		    while(rs.next()) {
	             root.add(new DefaultMutableTreeNode(rs.getString("nom")));
	           }
			DefaultTreeModel model = new DefaultTreeModel(root);
			tree.setModel(model);
		}	
		catch(SQLException e)
		    {  
		    	  System.out.println("Error " + e);
		    }
		 
	}
	public void ajouterZone(String nom, String localisation){
		try
		{	String requete = new String("INSERT INTO gererzones (`id`, `nom`, `localisation`) VALUES (NULL, ? , ?);");

			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, nom);
			stmt.setString(2, localisation);
			
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
			/** 25/11/14 seb **/
			try{
				String requete = new String("create table if not exists "+nom+" (id INTEGER PRIMARY KEY AUTO_INCREMENT, idPoint int, latitute double, longitude double, altitude double);");
				Statement stmt = _conn.createStatement();
				System.out.println(stmt.toString());
				stmt.executeUpdate(requete);
			}
			catch(SQLException ex5){
				while (ex5 !=null)
				{
					System.out.println(ex5.getSQLState());
					System.out.println(ex5.getMessage());
					System.out.println(ex5.getErrorCode());
					ex5=ex5.getNextException();
				}
			
			}
			/********************/
	}
		
		
	
	public void supprimerZone(String nom){
		try
		{	
			String requete = new String("DELETE FROM `gererzones` WHERE `nom`=?;");
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, nom);
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
		/** 25/11/14 seb **/
		try{
			String requete = new String("drop table "+nom);
			Statement stmt = _conn.createStatement();
			System.out.println(stmt.toString());
			stmt.executeUpdate(requete);
		}
		catch(SQLException ex5){
			while (ex5 !=null)
			{
				System.out.println(ex5.getSQLState());
				System.out.println(ex5.getMessage());
				System.out.println(ex5.getErrorCode());
				ex5=ex5.getNextException();
			}
		
		}
		/********************/
		
	}
	/*************seb fin**********/
	
	/************pilou code**********/
	
//	public void rafraichirTreeAlerte(JTree tree){
//		try {  
//			
//			String requete = new String("SELECT type FROM gereralerte");
//			PreparedStatement stmt = _conn.prepareStatement(requete);
//	        ResultSet rs = stmt.executeQuery(requete);
//		    ResultSetMetaData resultMeta = rs.getMetaData();
//		    
//		    DefaultMutableTreeNode root = new DefaultMutableTreeNode("GERER ALERTES"); //Premier dossier de l'arbre
//		   
//		    while(rs.next()) {
//	             root.add(new DefaultMutableTreeNode(rs.getString("type")));
//	           }
//			DefaultTreeModel model = new DefaultTreeModel(root);
//			tree.setModel(model);
//		}	
//		catch(SQLException e)
//		    {  
//		    	  System.out.println("Error " + e);
//		    }
//		 
//	}
//	public void ajouterAlertes(String type, String alertes){
//		try
//		{	String requete = new String("INSERT INTO gereralertes (`id`, `type`, `alertes`, 'date') VALUES (NULL, ? , ?, Now());");
//
//			PreparedStatement stmt = _conn.prepareStatement(requete);
//			stmt.setString(1, type);
//			stmt.setString(2, alertes);
//			
//			System.out.println(stmt.toString());
//			
//			stmt.executeUpdate();
//		}
//				
//		catch (SQLException ex4)
//		{
//			while (ex4 !=null)
//			{
//				System.out.println(ex4.getSQLState());
//				System.out.println(ex4.getMessage());
//				System.out.println(ex4.getErrorCode());
//				ex4=ex4.getNextException();
//			}
//		}
//		
//	}
//	public void supprimerAlertes(String type){
//		try
//		{	
//			String requete = new String("DELETE FROM `gereralertes` WHERE `type`=?;");
//			PreparedStatement stmt = _conn.prepareStatement(requete);
//			stmt.setString(1, type);
//			System.out.println(stmt.toString());
//			stmt.executeUpdate(); 
//		}
//		catch (SQLException ex4)
//		{
//			while (ex4 !=null)
//			{
//				System.out.println(ex4.getSQLState());
//				System.out.println(ex4.getMessage());
//				System.out.println(ex4.getErrorCode());
//				ex4=ex4.getNextException();
//			}
//		}
//		
//	}
//
//	
	
	public void insererCoord(Integer numPoint, Double latitude, Double longitude, Double altitude) {
		
		try {  
			
			String requete = new String("INSERT INTO `epasto`.`zone` (`id`, `idPoint`, `latitude`, `longitude`, `altitude`) VALUES (NULL, ? , ?, ? , ?);");
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setInt(1, numPoint);
			stmt.setDouble(2, latitude);
			stmt.setDouble(3, longitude);
			stmt.setDouble(4, altitude);
			
			System.out.println(stmt.toString());

			stmt.executeUpdate(); 
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
	public void rafraichirTreeAlerte(JTree tree){
		try {  
			
			String requete = new String("SELECT type FROM gereralerte");
			PreparedStatement stmt = _conn.prepareStatement(requete);
	        ResultSet rs = stmt.executeQuery(requete);
		    ResultSetMetaData resultMeta = rs.getMetaData();
		    
		    DefaultMutableTreeNode root = new DefaultMutableTreeNode("GERER ALERTES"); //Premier dossier de l'arbre
		   
		    while(rs.next()) {
	             root.add(new DefaultMutableTreeNode(rs.getString("type")));
	           }
			DefaultTreeModel model = new DefaultTreeModel(root);
			tree.setModel(model);
		}	
		catch(SQLException e)
		    {  
		    	  System.out.println("Error " + e);
		    }
		 
	}
	public void ajouterAlertes(String type, String alertes){
		try
		{	String requete = new String("INSERT INTO gereralerte (`id`, `type`, `alerte`, `date`) VALUES (NULL, ?, ?, now());");

			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, type);
			stmt.setString(2, alertes);
			
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
	public void supprimerAlertes(String type){
		try
		{	
			String requete = new String("DELETE FROM `gereralerte` WHERE `type`=?;");
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, type);
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
	public boolean isValid(String type)
    {
		boolean trouve = false;
    	try
        {
    		String requete = new String("SELECT type FROM gereralerte WHERE type=? ;");
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, type);
			
			ResultSet rs = stmt.executeQuery();
            
			if ( rs.next() ) 
			{
				trouve = true;
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
		return trouve;
	}
	/************pilou fin*********/
	
	public void remplirTable(JTable table, String Query) {
		
		try {  
			
			//String requete = new String("SELECT * FROM utilisateur;");
			PreparedStatement stmt = _conn.prepareStatement(Query);
	        ResultSet rs = stmt.executeQuery(Query);
		    ResultSetMetaData resultMeta = rs.getMetaData();
		    System.out.println(Query);
		    
	        int columns = resultMeta.getColumnCount();
	        String[] headers;

		      headers = new String[columns];
		      for (int h = 1; h <= columns; h++) {
		        headers[h - 1] = resultMeta.getColumnName(h);
		      }
		      
		    //To remove previously added rows
	        while(table.getRowCount() > 0) 
	        {
	            ((DefaultTableModel) table.getModel()).removeRow(0);
	        }

	        while(rs.next())
	        {  
	            Object[] row = new Object[columns];
	            for (int i = 1; i <= columns; i++)
	            {  
	                row[i - 1] = rs.getObject(i);
	            }
	            ((DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
	        }
	      }
		
	      catch(SQLException e)
	      {  
	    	  System.out.println("Error " + e);
	      }
			
	}
	   
	/** seb code ***/
	public boolean estValide(String nom)
    {
		boolean trouve = false;
    	try
        {
    		String requete = new String("SELECT nom FROM gererzones WHERE nom=? ;");
			PreparedStatement stmt = _conn.prepareStatement(requete);
			stmt.setString(1, nom);
			
			ResultSet rs = stmt.executeQuery();
            
			if ( rs.next() ) 
			{
				trouve = true;
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
		return trouve;
	}
	/**** seb fin code ***/
	/**LoginModel**/
//	public boolean estValide(String login, String mdp)
//	{
//		boolean trouve=false;
//		try
//		{
//			String requete = new String("SELECT id, nom, login FROM utilisateur WHERE login=? AND mdp=MD5(?);");
//			PreparedStatement stmt = _conn.prepareStatement(requete);
//			stmt.setString(1, login);
//			stmt.setString(2, mdp);
//			ResultSet rs = stmt.executeQuery();
//
//			if (rs.next ())
//			{
//				trouve = true;
//			}		
//			
//			rs.close();
//			stmt.close();
//		} 
//
//		catch (SQLException ex3)
//		{
//			while (ex3 != null)
//			{
//				System.out.println(ex3.getSQLState());
//				System.out.println(ex3.getMessage());
//				System.out.println(ex3.getErrorCode());
//				ex3=ex3.getNextException();			
//			}	
//		}     
//		return trouve;
//	}
	
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
	/**LoginModel fin**/
	/****debut UserModel*****/
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
			String requete = new String("INSERT INTO utilisateur (`id`, `nom`, `login`, `mdp`, `date`,`droit` ) VALUES (NULL, ? , ? , MD5(?) , NOW(), ?);");

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
    public String fonctionTest(){
	    	return "fonctionTest"; 
	    }
	    /****fin UserModel*****/
	    public void requeteSQL(String Query) {
			
			try {  
				
				//String requete = new String("SELECT * FROM utilisateur;");
				Statement stmt = _conn.createStatement();
				//stmt.setString(2, mdp);
				System.out.println(Query);
				System.out.println("execution de la requete");
				stmt.executeUpdate(Query);
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
