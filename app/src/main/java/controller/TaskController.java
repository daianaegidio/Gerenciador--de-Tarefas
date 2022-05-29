
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

public class TaskController {
    
 public void save(Task task){
    
        String SQL="INSERT INTO tasks (idProject,name,description, completed,notes,deadline, createAt, updateAt)VALUES (?,?,?,?,?,?,?,?)";
        Connection connection=null;
        PreparedStatement Statement = null;
        
        try{
        
         connection=ConnectionFactory.getConnection();
         Statement=connection.prepareStatement(SQL);
         
         Statement.setInt(1,task.getIdProject());
         Statement.setString(2, task.getName());
         Statement.setString(3, task.getDescription());
         Statement.setBoolean(4, task.isCompleted());
         Statement.setString(5, task.getNotes());
         Statement.setDate(6, new Date(task.getDeadline().getTime()));
         Statement.setDate(7, new Date(task.getCreatedAt().getTime()));
         Statement.setDate(8, new Date(task.getUpdatedAt().getTime()));  
         //E necessario fazer a conversao da data, pelo que é esperado pelo Sql, é retornado um long
         
         Statement.execute();// executa e insere os dados
        }catch (Exception ex){throw new RuntimeException ("Erro ao Salvar a Tarefa" +ex.getMessage(),ex);
        
        }finally{ConnectionFactory.closeConnection(connection, Statement);// fecha o banco de dados}
        
        }
    
    }
    
    public void upadate (Task task){
    
    String SQL= "UPDATE tasks SET idProject= ?,"
            + "name= ?,"
            + "description= ?, "
            + "completed= ?,"
            + "notes= ?,"
            + "deadline=?,"
            + "createAt=?,"
            + "updateAt=?"
            + "WHERE id = ?";
    
        Connection connection=null;
        PreparedStatement Statement = null;
        
         try{
             //estabelecendo a conexao com o banco de dados
               connection= ConnectionFactory.getConnection();
               
               //preparando a Query
               Statement=connection.prepareStatement(SQL);
               
         //setando os valores de Statement
         Statement.setInt(1,task.getIdProject());
         Statement.setString(2, task.getName());
         Statement.setString(3, task.getDescription());
         Statement.setBoolean(4, task.isCompleted());
         Statement.setString(5, task.getNotes());
         Statement.setDate(6, new Date(task.getDeadline().getTime()));
         Statement.setDate(7, new Date (task.getCreatedAt().getTime()));
         Statement.setDate(8, new Date (task.getUpdatedAt().getTime()));
         Statement.setInt(9, task.getId());
         
         //executando a Query
         Statement.execute();
         
         }catch(Exception e){   throw new RuntimeException(" Erro ao alterar a tarefa" + e.getMessage(),e);
         }finally{
          //finally é executado sempre indepente se houver um erro ou nao
          
          ConnectionFactory.closeConnection(connection,Statement);
          }
    }
    
    public void removeById (int taskId){
    
        String sql="DELETE FROM tasks WHERE id= ?";
        
       
        Connection connection=null;
        PreparedStatement Statement = null;
        
          try{
               //criacao da conexao com o bando de dados
               connection= ConnectionFactory.getConnection();
               Statement=connection.prepareStatement(sql);  // é um objeto que ajuda a preparar o comodado Sql,
        //que vai ser executado
            
              Statement.setInt(1, taskId);// nessa linha seta o valor numerico
              Statement.execute();
               
            }catch (Exception e){
            
              throw new RuntimeException(" Erro ao deletar a tarefa" + e.getMessage(),e);
            }finally{
          //finally é executado sempre indepente se houver um erro ou nao
          
          ConnectionFactory.closeConnection(connection,Statement);
          }
    }
    
    public List<Task> getAll (int idProject){
        
        String SQL="SELECT * FROM tasks WHERE idProject= ?";// nao é uma boa pratica fazer a contanecatcao
    
         Connection connection=null; // cria uma conexao com o banco de dados
         PreparedStatement Statement = null; // classe para executar instruçoes SQL
         ResultSet resultset=null;// quando fazer uma consulta no bd, esta classe guarda a resposta do select
         
         // lista de tarefas que será devolvida quando a chamada do metodo acontecer
         List <Task> task= new ArrayList <Task>();
         
         
            try{
               connection= ConnectionFactory.getConnection();
               Statement=connection.prepareStatement(SQL);  // é um objeto que ajuda a preparar o comodado Sql,
        //que vai ser executado
        
                Statement.setInt(1, idProject);
              resultset=Statement.executeQuery(); // é chamado esse metodo pq ele vai executar e receber o valor retornado do banco de dados
           
            // dentro do resultset tem os resultados retornados do bd
            
            while(resultset.next()){//enquanto houver um próximo ele irá rodar
            
               Task taskA= new Task();
               taskA.setId(resultset.getInt("id"));
               taskA.setIdProject(resultset.getInt("idProject"));
               taskA.setName(resultset.getString("name"));
               taskA.setDescription(resultset.getString("description"));
               taskA.setCompleted(resultset.getBoolean("completed"));
               taskA.setNotes(resultset.getString("notes"));
               taskA.setDeadline(resultset.getDate("deadline"));
               taskA.setCreatedAt(resultset.getDate("createAt"));
               taskA.setUpdatedAt(resultset.getDate("updateAt"));
                
            task.add(taskA);
            }
            
            }catch(Exception e){ throw new RuntimeException(" Erro ao consultar a tarefa" + e.getMessage(),e);
            
            } finally{
          //finally é executado sempre indepente se houver um erro ou nao
          
          ConnectionFactory.closeConnection(connection,Statement, resultset);// fecha as conexoes
          }
            // lista de tarefas que foi criada e carregada do banco de dados
        return task;
    }
}

