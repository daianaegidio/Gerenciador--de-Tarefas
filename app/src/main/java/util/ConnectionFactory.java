package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionFactory {
    
public static final String DRIVER= "com.mysql.jdbc.Driver"; // indica qual o banco de dados vamos usar
	public static final String URL= "jdbc:mysql://localhost:3306/todoapp"; //caminho do banco de dados
	public static final String USER="root";
	public static final String PASS="";
        
        public static Connection getConnection(){
        
            try{
           Class.forName(DRIVER);
           return DriverManager.getConnection(URL,USER, PASS);
            }catch (Exception ex){
            
            throw new RuntimeException("Erro na conexão com Banco de Dados", ex);
            }
        }
        
        public static void closeConnection(Connection connection){
        
         try{
            if(connection != null){connection.close();}
         }catch (Exception ex){ throw new RuntimeException("Erro ao fechar a conexão com Banco de Dados", ex);}
        }
        
        
       public static void closeConnection(Connection connection, PreparedStatement Statement){
        
         try{
            if(connection != null){connection.close();
            if(Statement!= null){Statement.close();}
            
            }
         }catch (Exception ex){ throw new RuntimeException("Erro ao fechar a conexão com Banco de Dados", ex);}
        }  
   
       
        public static void closeConnection(Connection connection, PreparedStatement Statement, ResultSet resultset){
        
         try{
            if(connection != null){connection.close();
            if(Statement!= null){Statement.close();}
            if(resultset != null){ resultset.close();}
            
            }
         }catch (Exception ex){ throw new RuntimeException("Erro ao fechar a conexão com Banco de Dados", ex);}
        }  
        
}

