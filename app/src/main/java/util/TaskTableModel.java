package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Task;

public class TaskTableModel extends AbstractTableModel {

    String[] columns = {"Nome", "Descrição", "Prazo", "Tarefa Concluída", "Editar", "Excluir"}; //colunas que aparece na table

    List<Task> tasks = new ArrayList();//lista que guarda tarefas

    @Override
    public int getRowCount() {//informa quantas tarefas tem

        return tasks.size();
    }//informar quantas linhas

    @Override
    public int getColumnCount() {// informa quantas colunas

        return columns.length;
    }
    
     @Override
     public String getColumnName(int columnIndex){
     
         return columns[columnIndex];
     }

    @Override
     public boolean isCellEditable(int rowIndex, int columnIndex) {
     return columnIndex==3;
         // deixar o indice da coluna editdavel ou nao
      /**   if(columnIndex==3){
         return true;}
     else{
     return false;
     }*/
     }
     
      @Override
      public Class<?> getColumnClass (int columnIndex){
          
          if(tasks.isEmpty()){// verifica se é vazia a list de tarefas
          
              return Object.class;
          
          }
          
          return this.getValueAt(0, columnIndex).getClass();
      }
     
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {//informa qual o valorvai ser exevibdo em determinda linha

        switch (columnIndex) {
            case 0:

                return tasks.get(rowIndex).getName();
            //retorna o campo nome da linha solicitada
            case 1:

                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
                return dateFormat.format(tasks.get(rowIndex).getDeadline());

            case 3:
                return tasks.get(rowIndex).isCompleted();
            case 4:
                return "";
            case 5:
                return "";

            default:
            return "Dados não encontrados!";  
        }
    }

     @Override
     public void setValueAt(Object aValue,int rowIndex, int columnIndex){
     
         //o object aValue é o valor que está cetado o campo
         tasks.get(rowIndex).setCompleted((boolean) aValue);// faz uma conversao
     }
    
    
    public String[] getColumns() {
        return columns;
    }

    public List<Task> getTasks() {
        return tasks;
    }
// as colunas nao tem o set pq nao é permitdo alterar
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    
}
