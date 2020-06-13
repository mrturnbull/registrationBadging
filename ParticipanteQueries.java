import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
//import org.sqlite.JDBC;

public class ParticipanteQueries {

	//private static final String URL = "jdbc:derby:Credenciamento";
	private static final String URL = "jdbc:sqlite:Credenciamento.db";
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	
	private Connection connection = null;
	private PreparedStatement selectTodosParticipantes = null;
	private PreparedStatement selectParticipantePorNome = null;
	private PreparedStatement insereNovoParticipante = null;
	private PreparedStatement updateParticipante = null;
	
	public ParticipanteQueries() throws ClassNotFoundException{

		Class.forName("org.sqlite.JDBC");
	
		try{		
		
			connection = DriverManager.getConnection(URL);
			
			selectTodosParticipantes = connection.prepareStatement("SELECT ID, Nome, Empresa, Email, Categoria, Fone, LocalTrabalho, SupervisaoSaude FROM Participantes");
			
			selectParticipantePorNome = connection.prepareStatement("SELECT ID, Nome, Empresa, Email, Categoria, Fone, LocalTrabalho, SupervisaoSaude FROM Participantes WHERE Nome LIKE ?");
						
			insereNovoParticipante = connection.prepareStatement("INSERT INTO Participantes (Nome, Empresa, Email, Categoria, Fone, LocalTrabalho, SupervisaoSaude) VALUES (?, ?, ?, ?, ?, ?, ?)");		
			
			updateParticipante = connection.prepareStatement("UPDATE Participantes SET Nome = ?, Empresa = ?, Email = ? , Categoria = ?, Fone = ?, LocalTrabalho = ?, SupervisaoSaude = ? WHERE ID = ?");
		
		}
		catch (SQLException sqlException){
		
			sqlException.printStackTrace();
			System.exit(1);
			
		}
	
	}
	
	public List<Participante> getTodosParticipantes(){
	
		List<Participante> results = null;
		ResultSet resultSet = null;
	
		try{
		
			resultSet = selectTodosParticipantes.executeQuery();
			results = new ArrayList<Participante>();	
		
			while (resultSet.next()){
			
				results.add(new Participante(
						   resultSet.getInt("ID"),
						   resultSet.getString("Nome"),
						   resultSet.getString("Empresa"),
						   resultSet.getString("Email"),
						   resultSet.getString("Categoria"),
						   resultSet.getString("Fone"),
						   resultSet.getString("LocalTrabalho"),
						   resultSet.getString("SupervisaoSaude")						   
						   ));
						   
			}

		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		finally{
			try{
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();		
			}		
		}
		
		return results;
	
	}
	
	public List<Participante> getParticipantesPorNome(String nome){
	
		//Statement st = null;
		List<Participante> results = null;
		ResultSet resultSet = null;
		
		try{
		
			selectParticipantePorNome.setString(1, "%" + nome + "%"); 
			//st = connection.createStatement();
			
			//st.executeQuery("SELECT * FROM Participantes WHERE Nome = A");
			
			resultSet = selectParticipantePorNome.executeQuery();			
			
			results = new ArrayList<Participante>();
			
			while(resultSet.next()){
				
					results.add(new Participante(
					   resultSet.getInt("ID"),
					   resultSet.getString("Nome"),
					   resultSet.getString("Empresa"),
					   resultSet.getString("Email"),
					   resultSet.getString("Categoria"),
					   resultSet.getString("Fone"),
					   resultSet.getString("LocalTrabalho"),
				       resultSet.getString("SupervisaoSaude")				   
					   ));
					   
			}
		}
		catch(SQLException sqlException){
			sqlException.printStackTrace();
		}
		finally{
			try{
				resultSet.close();
			}
			catch (SQLException sqlException){
				sqlException.printStackTrace();
				close();		
			}		
		}
		
		return results;

	}
	
	public int insereParticipante(String nome, String empresa, String email, String categoria, String fone, String localTrabalho, String supervisaoSaude){
	
		int result = 0;
		
		try{
		
			insereNovoParticipante.setString(1, nome);
			insereNovoParticipante.setString(2, empresa);
			insereNovoParticipante.setString(3, email);
			insereNovoParticipante.setString(4, categoria);
			insereNovoParticipante.setString(5, fone);
			insereNovoParticipante.setString(6, localTrabalho);
			insereNovoParticipante.setString(7, supervisaoSaude);			
			
			result = insereNovoParticipante.executeUpdate();
		
		}
		catch (SQLException sqlException){
		
			sqlException.printStackTrace();
			close();
		
		}
		
		return result;
	
	}
	
	public int atualizaParticipante(String strID, String nome, String empresa, String email, String categoria, String fone, String localTrabalho, String supervisaoSaude){
	
		int result = 0;
		
		try{
		
			updateParticipante.setString(1, nome);
			updateParticipante.setString(2, empresa);
			updateParticipante.setString(3, email);
			updateParticipante.setString(4, categoria);
			updateParticipante.setString(5, fone);
			updateParticipante.setString(6, localTrabalho);
			updateParticipante.setString(7, supervisaoSaude);			
			
			updateParticipante.setInt(8, Integer.parseInt(strID));	
			
			result = updateParticipante.executeUpdate();
		
		}
		catch (SQLException sqlException){
		
			sqlException.printStackTrace();
			close();
		
		}
		
		return result;
	
	}
	
	public void close(){
	
		try{
			connection.close();
		}
		catch( SQLException sqlException){
			sqlException.printStackTrace();
		}
	
	}

}