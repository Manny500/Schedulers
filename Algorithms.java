import java.io.*;
import java.util.*;

/*
 * @Author Manny 
 */

public class Algorithms{
  
  //instance  Variables
  Process process;
  int burst_time;
  int arrival_time;
  int time;
  int addPriority;
  int waitingTime;
  double avgWaiting = 0;
  double weightedWaitingTime;
  double responseTime;
  double weightedResponseTime;
  
  //constructor
  public Algorithms(){
    
  }
  ////////////////////////////METHODS/////////////////////////////////////////////
  //start of methods
  
  /*
   * @param takes in a list of process
   * @returns a sorted list of process according to their arrival time
   */ 
  public ArrayList<Process> sort(ArrayList<Process> list){
   
    for (int i = 1; i < list.size(); i++){
      
      process = list.get(i);
      int index = process.getArrival_time(); 
      int j = i;
      
      while (j > 0 && list.get(j-1).getArrival_time() > index){
        
        Process process1 =list.get(j-1);
        list.remove(j);
        list.set(j-1, process);
        list.add(process1);
        j--;
        
      }

    }
    
   /* for (int i = 0; i < list.size(); i++){
      
      int t = list.get(i).getArrival_time();
      
      System.out.println("order? : " +t);
      
    }*/
    
    return list;
  }//end of sort
  
  /*
   * @param
   * @return
   * First Come First serve (fcfs) (nonPreemptive) scheduling algorithm
   */
  public void fcfs(ArrayList<Process> list){
    
   //ArrayList<Process> queue = new ArrayList<Process>();
      
   try{
     
     PrintWriter outFile = new PrintWriter("Process-Results.txt");
     outFile.println("Output (fcfs)");
     outFile.println("");
     
     for(int x = 0; x < list.size(); x++){
 
         
         process = list.get(x);
         
         arrival_time = process.getArrival_time();
         burst_time = process.getBurst_time();
         waitingTime = time - arrival_time;
         process.setWaitingTime(waitingTime);
         
       while(burst_time != 0){
         
         burst_time --;
         
         outFile.println("Time: " + time + " process: " + process.getPid() + " running");
         outFile.println("");
         
         //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
         time ++;
         
       }
     }
     
     
     for(int x = 0; x < list.size(); x++){
       
       double wait = list.get(x).getWaitingTime();
       int priority = list.get(x).getPriority();
       addPriority = addPriority + priority;
       weightedWaitingTime = (wait * list.get(x).getPriority()) + weightedWaitingTime;
       avgWaiting = avgWaiting + wait;
      
       
     }
     avgWaiting = avgWaiting / list.size();
     weightedWaitingTime =  weightedWaitingTime / addPriority;
     responseTime = avgWaiting;
     weightedResponseTime = weightedWaitingTime;
       
     //System.out.println(avgWaiting);
     //System.out.println(weightedWaitingTime);
     //System.out.println(responseTime);
     //System.out.println(weightedResponseTime);
     
     outFile.println("Average waiting time is: "+ avgWaiting);
     outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
     outFile.println("Average reponse time is: "+ responseTime);
     outFile.println("Average weighted reponse time is: "+ weightedResponseTime);
     outFile.close();
     
   }catch(FileNotFoundException e){
   }
  }//end of fcfs
  
  /*
   * @
   * @
   * Shortest job first (sjf) (nonPreemptive) scheduling algorithm
   */
  public void sjf(){
    
  }//end of sjf
  
  
  /*
   * @
   * @
   * Shortest Remaining Time First (srtf)  (Preemptive) scheduling algorithm
   */
  public void srtf(){
    
  }//end of srtf
  
  /*
   * @
   * @
   * Priority (nonpreprior) (nonPreemptive with aging) scheduling algorithm
   */
  public void pnna(){
    
  }//end of pnna
  
  /*
   * @
   * @
   * Priority (preprior) (Preemptive with aging) scheduling algorithm
   */
  public void pppa(){
    
  }//end of pppa
  
  /*
   * @
   * @
   * Round Robin (rr) (Different Times) scheduling algorithm
   */
  public void rr(){
    
  }//end of rr
    
  /*
   * @
   * @
   * An additional scheduling algorithm of your choice - 
   * this can be some sort of hybrid of the other approaches scheduling algorithm
   */
  public void authorsChoice(){
    
  }//end of authorsChoice
  
  //end of Methods
  
}//end of Algorithms class