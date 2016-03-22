import java.io.*;
import java.util.*;

/*
 * @Author Manny 
 */

public class Parse{
  
  //instance  Variables
  ArrayList<Process> list;
  Process process;
  int pid;
  int burst_time;
  int arrival_time;
  int priority;
  
  //constructor
  public Parse(){
         list = new ArrayList<Process>();
    
  }
  ////////////////////////////METHODS//////////////////////////////////
  //start of methods
  
  /*
   * @return ArrayList<Process>
   * gets the ArrayList list
   */
  public ArrayList<Process> getList(){
    
      return this.list;
    } //end of size method  
  
  /*
   * @return size of arraylist
   * gets the size of the arraylist
   */
  public int size(){
    
      return list.size();
    } //end of size method  
  
  /*
   * @param file is the file to read from 
   * reads a file and creates process depending on the file
   */
  public void addToDataBase(File file){

      try{

        //assign the file to the scanner
        Scanner sc = new Scanner(file);

        //this loop goes through the file and scans until the very end
        while (sc.hasNextInt()){ 
   
            pid = sc.nextInt(); 
            //System.out.println("pid: "+pid);
            
            burst_time = sc.nextInt(); 
            //System.out.println("burst_time: "+burst_time);

            arrival_time = sc.nextInt(); 
            //System.out.println("arrival_time: "+arrival_time);
  
            priority = sc.nextInt(); 
            //System.out.println("priority: "+priority);

            process = new Process(pid, burst_time, arrival_time, priority);
            list.add(process);

        }
        //will catch the the file if the program doesnt find the file
      }catch (FileNotFoundException e) {
        
        System.out.println("Invalid file path.");
        
      }catch (NumberFormatException e) {   
      } 
  }//end of addToDataBase
  
  //end of Methods
  
}//end of Parse