package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

public class ProjectController {
    
 public void save (Project project){
    
        String sql="INSERT INTO projects( name,"
                + "descripton,"
                + "createAt,"
                + "updateAt)"
                + "VALUES (?,?,?,?)";
        
         Connection connection= null;
         PreparedStatement Statement = null;
         
         try{
              // criar uma conexao com banco de dados
              connection=ConnectionFactory.getConnection();
              
              //cria uma PreparedStatement, classe usada para executar a Query
              Statement=connection.prepareStatement(sql);
              
           Statement.setString(1, project.getName());
           Statement.setString(2, project.getDescription());
           Statement.setDate(3, new Date(project.getCreatedAt().getTime()));
           Statement.setDate(4, new Date(project.getUpdatedAt().getTime()));   
              
           //executa o Sql para inserir os dados
           Statement.execute();
           
           
         }catch (Exception e){
             
             throw new RuntimeException ("Erro ao Salvar o Projeto" +e.getMessage(),e);
         
         }finally{ConnectionFactory.closeConnection(connection, Statement);// fecha o banco de dados}
        
        }
    
        
    }
    
    public void update(Project project){
    
       String sql = "UPDATE projects SET name = ?, descripton = ?, createAt = ?, updateAt = ? WHERE id = ?";
         Connection connection= null;
         PreparedStatement Statement = null;
         
         try{
         
               //estabelecendo a conexao com o banco de dados
               connection= ConnectionFactory.getConnection();
               
               //preparando a Query
               Statement=connection.prepareStatement(sql);
               
           Statement.setString(1, project.getName());
           Statement.setString(2, project.getDescription());
           Statement.setDate(3, new Date(project.getCreatedAt().getTime()));
           Statement.setDate(4, new Date(project.getUpdatedAt().getTime()));  
           Statement.setInt(5, project.getId());
         
           //executa o sql
           Statement.execute();
           
         }catch(Exception e){
             throw new RuntimeException(" Erro ao alterar o projeto" + e.getMessage(),e);

         }finally{
          //finally é executado sempre indepente se houver um erro ou nao
          
          ConnectionFactory.closeConnection(connection,Statement);
          }
    }
    
    public List<Project> getAll(){
        
        String sql="SELECT * FROM projects";
        
         List <Project> projects= new ArrayList <Project>();
         Connection connection= null;
         PreparedStatement Statement = null;
         
         ResultSet resultset= null;
         
         try{
         
          //estabelecendo a conexao com o banco de dados
               connection= ConnectionFactory.getConnection();
               
               //preparando a Query
               Statement=connection.prepareStatement(sql);
               
               resultset= Statement.executeQuery();
               
               //enquanto exisit no banco de dados faca
               while(resultset.next()){
               
                Project project = new Project();
                  
                project.setId(resultset.getInt("id"));
                project.setName(resultset.getString("name"));
                project.setDescription(resultset.getString("descripton"));
                project.setCreatedAt(resultset.getDate("createAt"));
                project.setCreatedAt(resultset.getDate("updateAt"));

                //Adiciono o contato recuperado, a lista de contatos
                projects.add(project);
                   
               }
         
         }catch(Exception e){ throw new RuntimeException(" Erro ao pesquisar o Projeto" + e.getMessage(),e);
         
         }finally{
          //finally é executado sempre indepente se houver um erro ou nao
          
          ConnectionFactory.closeConnection(connection,Statement, resultset);
          }
    
    return projects;
    }
    
      public void removeById(int idProject) {
      
       String sql = "DELETE FROM projects WHERE id = ?";

        Connection connection = null;
        PreparedStatement Statement = null;
        
        try{
        
        //estabelecendo a conexao com o banco de dados
               connection= ConnectionFactory.getConnection();
               
               //preparando a Query
               Statement=connection.prepareStatement(sql);
               
               Statement.setInt(1, idProject);
               Statement.execute();
        
        
        }catch(Exception e){ throw new RuntimeException(" Erro ao excluir o Projeto" + e.getMessage(),e);
         
         }finally{
          //finally é executado sempre indepente se houver um erro ou nao
          
          ConnectionFactory.closeConnection(connection,Statement);
          }
    
      }
}

