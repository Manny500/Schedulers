import java.util.*;

/*
 * @Author Manny 
 */

public class Process{
  
  //instance Variables
  int pid;
  int burst_time;
  int arrival_time;
  int priority;
  int waitingTime;
  
  //constructors
  public Process(){
  }
  
  public Process( int pid, int burst_time, int arrival_time, int priority){
    
    this.pid = pid;
    this.burst_time = burst_time;
    this.arrival_time = arrival_time;
    this.priority = priority;
    this.waitingTime = 0;
   
  }
////////////////////////////METHODS//////////////////////////////////
  //start of methods

  /*
   * @return gets the pid of the process
   * will get the pid of the process
   */
  public int getPid(){
    
    return this.pid = pid;
  }//end of getPid
  
   /*
   * @return gets the Burst_time of the process
   * will get the Burst_time of the process
   */
  public int getBurst_time(){
    
    return this.burst_time;
  }//end of getBurst_time
  
   /*
   * @param sets the Burst_time of the process
   * will set the Burst_time of the process
   */
  public void setBurst_time(int time){
    
    this.burst_time = time;
  }//end of getBurst_time
    
   /*
   * @return gets the Arrival_time of the process
   * will get the Arrival_time of the process
   */
  public int getArrival_time(){
    
    return this.arrival_time;
  }//end of getArrival_time
      
  /*
   * @return will get the waiting time
   * will get the waitingTime of the process
   */
  public int getWaitingTime(){
    
    return this.waitingTime;
  }//end of getArrival_time
  
  /*
   * @param will set the waiting time
   * will set the waitingTime of the process
   */
  public void setWaitingTime(int waiting){
    
    this.waitingTime = waiting;
  }//end of getArrival_time
  
  /*
   * @return gets the Priority of the process
   * will get the Priority of the process
   */
  public int getPriority(){
    
    return this.priority;
  }//end of getPriority
  
  //End of Methods
  
}//end of process