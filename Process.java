//Imports
import java.util.*;

/*
 * @Author Manny + Conrad 
 */

public class Process{
  
  //Instance Variables
  int originalBurst_time;
  int originalPriority;
  int arrival_time;
  int waitingTime;
  int responseTime;
  int burst_time;
  int priority;
  int pid;
  
  //Constructors
  public Process(){
    
  }
  
  public Process( int pid, int burst_time, int arrival_time, int priority){
    
    this.pid = pid;
    this.burst_time = burst_time;
    this.arrival_time = arrival_time;
    this.priority = priority;
    this.waitingTime = 0;
    this.originalBurst_time = burst_time;
    this.originalPriority = priority;
    this.responseTime = 0;
    
  }
////////////////////////////METHODS//////////////////////////////////
  //start of methods
  
  /*
   * @return int gets the responseTime of the process
   * will get the Burst_time of the process
   */
  public int getResponseTime(){
    
    return this.responseTime;
  }//end of getBurst_time
  
  /*
   * @param time sets the responseTime of the process
   * will set the Burst_time of the process
   */
  public void setResponseTime(int time){
    
    this.responseTime = time;
  }//end of getBurst_time
  
  /*
   * @return int gets the pid of the process
   * will get the pid of the process
   */
  public int getPid(){
    
    return this.pid = pid;
  }//end of getPid
  
  /*
   * @return int gets the Burst_time of the process
   * will get the Burst_time of the process
   */
  public int getBurst_time(){
    
    return this.burst_time;
  }//end of getBurst_time
  
  /*
   * @param time sets the Burst_time of the process
   * will set the Burst_time of the process
   */
  public void setBurst_time(int time){
    
    this.burst_time = time;
  }//end of getBurst_time
  
  /*
   * @return int gets the Burst_time of the process
   * will get the Burst_time of the process
   */
  public int getOriginalBurst_time(){
    
    return this.originalBurst_time;
  }//end of getBurst_time
  
  /*
   * @param time sets the OriginalBurst_time of the process
   * will set the OriginalBurst_time of the process
   */
  public void setOriginalBurst_time(int time){
    
    this.originalBurst_time = time;
  }//end of getBurst_time
  
  /*
   * @return int gets the Burst_time of the process
   * will get the Burst_time of the process
   */
  public int getOriginalPriority(){
    
    return this.originalPriority;
  }//end of getBurst_time
  
  /*
   * @param time sets the OriginalBurst_time of the process
   * will set the OriginalBurst_time of the process
   */
  public void setOriginalPriority(int time){
    
    this.originalPriority = time;
  }//end of getBurst_time
  
  /*
   * @return int gets the Arrival_time of the process
   * will get the Arrival_time of the process
   */
  public int getArrival_time(){
    
    return this.arrival_time;
  }//end of getArrival_time
  
  /*
   * @return int will get the waiting time
   * will get the waitingTime of the process
   */
  public int getWaitingTime(){
    
    return this.waitingTime;
  }//end of getArrival_time
  
  /*
   * @param waiting will set the waiting time
   * will set the waitingTime of the process
   */
  public void setWaitingTime(int waiting){
    
    this.waitingTime = waiting;
  }//end of getArrival_time
  
  /*
   * @return int gets the Priority of the process
   * will get the Priority of the process
   */
  public int getPriority(){
    
    return this.priority;
  }//end of getPriority
  
  /*
   * @param p sets the Priority of the process
   * will get the Priority of the process
   */
  public void setPriority(int p){
    
    this.priority = p;
  }//end of getPriority
  
////////////////////////End of Methods//////////////////////////////////////////////////// 
}//end of process