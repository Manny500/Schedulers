//Imports
import java.io.*;
import java.util.*;

/*
 * @Author Manny + Conrad
 */

public class Algorithms{
  
  //Instance  Variables
  Parse parse = new Parse();
  Process process;
  double weightedWaitingTime;
  double weightedResponseTime;
  double avgWaiting = 0;
  double avgResponse = 0;
  double responseTime;
  int arrival_time;
  int quantum = 2;
  int burst_time;
  int addPriority;
  int waitingTime;
  int numProcesses;
  int priority;
  int arrival;
  int burst;
  int pid;
  int time;
  
  //Constructor
  public Algorithms(){
    
  }
  ////////////////////////////METHODS/////////////////////////////////////////////
  
  /*
   * @param file is the file name to write to 
   * @param num is the number of process to be generated
   * will generate a list with random process with random attributes
   */
  public void randomProcessGenerator(int num, String file){
    
    //write inside a try to maake sure all errors are handled
    try{
      
      //write the the specified file
      PrintWriter outFile = new PrintWriter(file);
      numProcesses = num;
      
      //for loop calculates the new process details
      for(int i = 1000; i < numProcesses + 1000; i++){
        
        pid = i;
        burst = (int)(Math.random() * 50 + 1);
        arrival = (int)(Math.random() * 50);
        priority = (int)(Math.random() * 50 + 1);
        
        //write new process to output file
        outFile.println(pid+"\t "+burst+"\t "+arrival+"\t "+priority);
      }
      
      outFile.close();
      
    }catch(Exception e){
      
      System.out.println(e);
      
    }
    
  }//end of randomProcessGenerator
  
  /*
   * @param algorthm specifies what scheduling algorithm to use
   * @param fileName specifies what file to get the process information from
   * Will get the processes and apply the scheduling algorithm to them
   */
  public void scheduler(String algorithm, String fileName){
    
    File processFile = new File(fileName); 
    
    //create a datastructure to store the new processes from database
    ArrayList<Process> list = new ArrayList<Process>();
    
    //add the process to database
    parse.addToDataBase(processFile);
    
    //get the process from the database
    list = parse.getList();
    
    //performs the different scheduling algorithms
    //depending on the parameters specifications
    if(algorithm == "fcfs"){
      
      fcfs(list);
      
    }else if(algorithm == "sjf"){
      
      sjf(list);
      
    }else if(algorithm == "srtf"){
      
      srtf(list);
      
    }else if(algorithm == "nonpreprior"){
      
      nonpreprior(list);
      
    }else if(algorithm == "preprior"){
      
      preprior(list);
      
    }else if(algorithm == "rr"){
      
      rr(list);
      
    }else if(algorithm == "hybrid"){
      
      hybrid(list);
      
    }
    
  }//end of scheduler
  
  /*
   * @param takes list in a list of process
   * @param order specifies the way to sort the list
   * @returns ArrayList<Process> a sorted list of process according to their arrival time
   */ 
  public ArrayList<Process> sort(ArrayList<Process> list, String order){
    
    //lower values are first: 0 1 2 3 4 5 
    if(order == "arrival_time"){
      
      //start of the insertion sort algorithm
      for (int i = 1; i < list.size(); i++){
        
        process = list.get(i);
        int index = process.getArrival_time(); 
        int j = i;
        
        //if neighbors are out of order swap them
        while (j > 0 && list.get(j-1).getArrival_time() > index){
          
          Process process1 =list.get(j-1);
          list.set(j-1, process);
          list.set(j,process1);
          j--;
          
        }
        
      }
      
      //higher value priority is first: 10 9 8 7 6 5 4 3 2 1
    }else if(order == "priority"){
      
      for (int i = 1; i < list.size(); i++){
        
        process = list.get(i);
        int index = process.getPriority(); 
        int j = i;
        
        while (j > 0 && list.get(j-1).getPriority() < index){
          
          Process process1 =list.get(j-1);
          list.set(j-1, process);
          list.set(j,process1);
          
          j--;
          
        }
        
      }
      
      //lower values are first: 0 1 2 3 4 5 
    }else if(order == "burst_time"){
      
      for (int i = 1; i < list.size(); i++){
        
        process = list.get(i);
        int index = process.getBurst_time(); 
        int j = i;
        
        while (j > 0 && list.get(j-1).getBurst_time() > index){
          
          Process process1 =list.get(j-1);
          list.set(j-1, process);
          list.set(j,process1);
          
          j--;
          
        }
        
      }
    }
    
    //loop to print out the sorted list
    /*for (int i = 0; i < list.size(); i++){
     * 
     System.out.println(" process: " + list.get(i).getPid() + "  ordddddddddddddd: " +list.get(i).getPriority());
     
     }
     System.out.println("\n");*/
    
    return list;
  }//end of sort
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the fcfs schedule to
   * First Come First serve (fcfs) (nonPreemptive) scheduling algorithm
   */
  public void fcfs(ArrayList<Process> list){
    
    //instance variables
    list = sort(list, "arrival_time");
    
    weightedResponseTime = 0;
    weightedWaitingTime = 0;
    avgResponse = 0;
    addPriority = 0;
    avgWaiting = 0;
    time = 0;
    
    //write inside a try to maake sure all errors are handled
    try{
      
      //print results to specific txt file
      PrintWriter outFile = new PrintWriter("FCFS-Output.txt");
      outFile.println("Output (fcfs)");
      outFile.println("");
      
      //will get the first process to arrive and complete it
      for(int x = 0; x < list.size(); x++){
        
        if(list.get(x).getArrival_time() <= time){
          
          //list was ordered by arrival time
          process = list.get(x);
          
          arrival_time = process.getArrival_time();
          burst_time = process.getBurst_time();
          waitingTime = time - arrival_time;
          process.setWaitingTime(waitingTime);
          process.setResponseTime(time - arrival_time);
          
          //if process has not arrived yet
        }else{
          
          time ++;
          //makes sure it starts back where it left off
          x --;
          
        }
        
        //make sure that the process still has burst time
        while(burst_time != 0){
          
          burst_time --;
          
          outFile.println("Time: " + time + " process: " + process.getPid() + " running");
          outFile.println("");
          
          //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
          time ++;
          
        }
      }
      
      //Calculates the wait, and response time of each process
      for(int y = 0; y < list.size(); y++){
        
        //get wait and response of each process
        double wait = list.get(y).getWaitingTime();
        double response = list.get(y).getResponseTime();
        int priority = list.get(y).getPriority();
        
        //each iteration add the waiting and response times to get avg
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * list.get(y).getPriority()) + weightedWaitingTime;
        weightedResponseTime = (response * list.get(y).getPriority()) + weightedResponseTime;
        avgWaiting = avgWaiting + wait;
        avgResponse = avgResponse + response;
        
      }
      
      //divide by amount of priority of number of process to get avg
      avgWaiting = avgWaiting / list.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      avgResponse = avgResponse / list.size();
      weightedResponseTime = weightedResponseTime / addPriority;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ avgResponse);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
      //clearing the list so that other scheduling algorithms may use it
      while(list.size() > 0)
      {
        list.remove(0);
      }
      
    }catch(FileNotFoundException e){
      
      System.out.println(e);
      
    }
    
  }//end of fcfs
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the sjf schedule to
   * Shortest job first (sjf) (nonPreemptive) scheduling algorithm
   */
  public void sjf(ArrayList<Process> list){
    
    weightedResponseTime = 0;
    weightedWaitingTime = 0;
    avgResponse = 0;
    addPriority = 0;
    avgWaiting = 0;
    time = 0;
    
    //instance variables
    list = sort(list, "burst_time");
    ArrayList<Process> queue = new ArrayList<Process>();
    
    
    
    //write inside a try to make sure all errors are handled
    try{
      
      //print results to specific txt file
      PrintWriter outFile = new PrintWriter("SJF-Output.txt");
      outFile.println("Output (sjf)");
      outFile.println("");
      
      //every time a process finishes it gets removed from the list
      while(!list.isEmpty()){
        
        int x = 0;
        
        for(x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time ){
            
            process = list.get(x);
            
            //set the respose time
            process.setResponseTime(time - arrival_time);
            
            break;
          }
        }
        
        //calculate the waiting times and get burst time
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
        
        //add the finished process to the queue and reove from list
        queue.add(process);
        list.remove(x);
        
      }
      
      //Calculates the wait, and response time of each process
      for(int y = 0; y < queue.size(); y++){
        
        //get wait and response of each process
        double wait = queue.get(y).getWaitingTime();
        double response = queue.get(y).getResponseTime();
        int priority = queue.get(y).getPriority();
        
        //each iteration add the waiting and response times to get avg
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        weightedResponseTime = (response * queue.get(y).getPriority()) + weightedResponseTime;
        avgWaiting = avgWaiting + wait;
        avgResponse = avgResponse + response;
        
      }
      
      //divide by amount of priority of number of process to get avg
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      avgResponse = avgResponse / queue.size();
      weightedResponseTime = weightedResponseTime / addPriority;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ avgResponse);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
      
      System.out.println(e);
      
    }
    
  }//end of sjf
  
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the srtf schedule to
   * Shortest Remaining Time First (srtf)  (Preemptive) scheduling algorithm
   */
  public void srtf(ArrayList<Process> list){
    
    ArrayList<Process> queue = new ArrayList<Process>();
    
    weightedResponseTime = 0;
    weightedWaitingTime = 0;
    avgResponse = 0;
    addPriority = 0;
    avgWaiting = 0;
    time = 0;
    
    //write inside a try to maake sure all errors are handled
    try{
      
      //print results to specific txt file
      PrintWriter outFile = new PrintWriter("SRTF-Output.txt");
      outFile.println("Output (srtf)");
      outFile.println("");
      
      //everytime a process finishes it is removed from the list
      while(!list.isEmpty()){   
        
        for(int x = 0; x < list.size(); x++){
          
          //sort list every iteration
          list = sort(list, "burst_time");
          
          if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0){
            
            process = list.get(x);
            
            //set the response time
            if(process.getBurst_time() == process.getOriginalBurst_time()){
              
              process.setResponseTime((time - process.getArrival_time()));
              
            }
            break;
            
          }else if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() == 0){
            
            //calculate overall waiting time and remove from list add to queue
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            queue.add(process);
            list.remove(x);
            
            //in order for for loop to start at zero again
            x --;
            
          }
        }
        
        //prevent last process from going more than its share
        if(list.size() > 0){
          
          arrival_time = process.getArrival_time();
          burst_time = process.getBurst_time();  
          
          burst_time --;        
          
          outFile.println("Time: " + time + " process: " + process.getPid() + " running");
          outFile.println("");
          
          //System.out.println("Time: "+ time + " process: " + process.getPid() + " running" + "  " +burst_time);
          
          process.setBurst_time(burst_time);
          
          time ++;  
          
        }
        
      }
      
      //Calculates the wait, and response time of each process
      for(int y = 0; y < queue.size(); y++){
        
        //get wait and response of each process
        double wait = queue.get(y).getWaitingTime();
        double response = queue.get(y).getResponseTime();
        int priority = queue.get(y).getPriority();
        
        //each iteration add the waiting and response times to get avg
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        weightedResponseTime = (response * queue.get(y).getPriority()) + weightedResponseTime;
        avgWaiting = avgWaiting + wait;
        avgResponse = avgResponse + response;
        
      }
      
      //divide by amount of priority of number of process to get avg
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      avgResponse = avgResponse / queue.size();
      weightedResponseTime = weightedResponseTime / addPriority;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ avgResponse);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
      
      System.out.println(e);
      
    }
    
  }//end of srtf
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the nonpreprior schedule to
   * Priority (nonpreprior) (nonPreemptive with aging) scheduling algorithm
   */
  public void nonpreprior(ArrayList<Process> list){
    
    ArrayList<Process> queue = new ArrayList<Process>();
    
    weightedResponseTime = 0;
    weightedWaitingTime = 0;
    avgResponse = 0;
    addPriority = 0;
    avgWaiting = 0;
    time = 0;
    
    //write inside a try to maake sure all errors are handled
    try{
      
      //print results to specific txt file
      PrintWriter outFile = new PrintWriter("Nonpreprior-Output.txt");
      outFile.println("Output (Nonpreprior)");
      outFile.println("");
      
      //everytime process finishes it is removed from the list
      while(!list.isEmpty()){
        
        //sort the list every iteration
        list = sort(list, "priority");
        int x = 0;
        
        for(x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time ){
            
            process = list.get(x);
            
            //set the response time
            if(process.getBurst_time() == process.getOriginalBurst_time()){
              
              process.setResponseTime((time - process.getArrival_time()));
              
            }
            
            break;
          }
        }
        
        //calculate the waiting time and get the burst time
        arrival_time = process.getArrival_time();
        burst_time = process.getBurst_time();
        waitingTime = time - arrival_time;
        process.setWaitingTime(waitingTime);
        
        //run the process until it finishes
        while(burst_time != 0){
          
          burst_time --;
          
          outFile.println("Time: " + time + " process: " + process.getPid() + " running");
          outFile.println("");
          
          //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
          time ++;
          
        }
        
        /*this loop ages the prioriy of the processs by one each 
         * "CPU" cycle
         */
        for(int y = 0; y < list.size(); y++){
          
          //get each process
          if(list.get(y).getArrival_time() <= time ){
            
            int aging = list.get(y).getPriority();
            
            //if it is not the current process
            if(y != x){
              
              //add the amount of cycles that the current process was in the CPU to each of the priority of the 
              //processes that were not in the CPU
              list.get(y).setPriority(aging + (time - list.get(x).getResponseTime() - list.get(y).getArrival_time()));
              
            }
          }
        }
        
        //add finished process to queue and remove from list
        queue.add(process);
        list.remove(x);
        
      }
      
      //Calculates the wait, and response time of each process
      for(int y = 0; y < queue.size(); y++){
        
        //get wait and response of each process
        double wait = queue.get(y).getWaitingTime();
        double response = queue.get(y).getResponseTime();
        int priority = queue.get(y).getPriority();
        
        //each iteration add the waiting and response times to get avg
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        weightedResponseTime = (response * queue.get(y).getPriority()) + weightedResponseTime;
        avgWaiting = avgWaiting + wait;
        avgResponse = avgResponse + response;
        
      }
      
      //divide by amount of priority of number of process to get avg
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      avgResponse = avgResponse / queue.size();
      weightedResponseTime = weightedResponseTime / addPriority;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ avgResponse);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
      
      System.out.println(e);
      
    }
    
  }//end of pnna
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the preprior schedule to
   * Priority (preprior) (Preemptive with aging) scheduling algorithm
   */
  public void preprior(ArrayList<Process> list){
    
    ArrayList<Process> queue = new ArrayList<Process>();
    
    weightedResponseTime = 0;
    weightedWaitingTime = 0;
    avgResponse = 0;
    addPriority = 0;
    avgWaiting = 0;
    time = 0;
    
    //write inside a try to maake sure all errors are handled
    try{
      
      //print results to specific txt file
      PrintWriter outFile = new PrintWriter("Preprior-Output.txt");
      outFile.println("Output (Preprior)");
      outFile.println("");
      
      //everytime a process finishes it is removed from the list
      while(!list.isEmpty()){
        
        //sort list each iteration
        list = sort(list, "priority");
        int x = 0;
        
        for(x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0){
            
            process = list.get(x);
            
            //set the response time
            if(process.getBurst_time() == process.getOriginalBurst_time()){
              
              process.setResponseTime((time - process.getArrival_time()));
              
            }
            
            break;
          }else if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() == 0){
            
            //calculate overall waiting time and remove from list add to queue
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            queue.add(process);
            list.remove(x);
            
            //makes sure that the next iteration starts from 0 again
            x--;
            
          }
        }
        
        //prevents last process from going more than its share
        if(list.size() > 0)
        {
          
          arrival_time = process.getArrival_time();
          burst_time = process.getBurst_time();       
          burst_time --;        
          
          outFile.println("Time: " + time + " process: " + process.getPid() + " running");
          outFile.println("");
          
          //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
          
          process.setBurst_time(burst_time); 
          
          //this loop ages the priority of all the waiting process by one each CPU cycle
          for(int y = 0; y < list.size(); y++){
            
            if(list.get(y).getArrival_time() <= time ){
              
              int aging = list.get(y).getPriority();
              
              //make sure it is not the current process
              if(y != x){
                
                list.get(y).setPriority(aging + 1);
                
              }
            }
          }
          
          time ++; 
        }
        
      }
      
      //Calculates the wait, and response time of each process
      for(int y = 0; y < queue.size(); y++){
        
        //get wait and response of each process
        double wait = queue.get(y).getWaitingTime();
        double response = queue.get(y).getResponseTime();
        int priority = queue.get(y).getPriority();
        
        //each iteration add the waiting and response times to get avg
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        weightedResponseTime = (response * queue.get(y).getPriority()) + weightedResponseTime;
        avgWaiting = avgWaiting + wait;
        avgResponse = avgResponse + response;
        
      }
      
      //divide by amount of priority of number of process to get avg
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      avgResponse = avgResponse / queue.size();
      weightedResponseTime = weightedResponseTime / addPriority;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ avgResponse);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
      
      System.out.println(e);
      
    }
    
  }//end of pppa
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the rr schedule to
   * Round Robin (rr) (Different Times) scheduling algorithm
   */
  public void rr(ArrayList<Process> list)
  {
    
    list = sort(list, "arrival_time");
    ArrayList<Process> queue = new ArrayList<Process>();
    
    weightedResponseTime = 0;
    weightedWaitingTime = 0;
    avgResponse = 0;
    addPriority = 0;
    avgWaiting = 0;
    time = 0;
    
    //write inside a try to maake sure all errors are handled
    try{
      
      //print results to specific txt file
      PrintWriter outFile = new PrintWriter("RR-Output.txt");
      outFile.println("Output (RR)");
      outFile.println("");
      //everytime a process finishes it is removed from the list
      while(!list.isEmpty()){
        
        for(int x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0){
            
            //get process and put it add the end of list
            process = list.get(x);
            list.remove(x);
            list.add(process);
            //set the response time
            if(process.getBurst_time() == process.getOriginalBurst_time()){
              
              process.setResponseTime((time - process.getArrival_time()));
              
            }
            
            break;
          }else if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() == 0){
            
            //if the burst time has finished, remove from list and add to queue
            //calculate waiting time
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            queue.add(process);
            list.remove(x);
            
            
            
          }else if(x==list.size()-1 && list.get(x).getArrival_time() > time){
            
            time++;
          }
        }
        
        arrival_time = process.getArrival_time();
        burst_time = process.getBurst_time(); 
        
        //run the process for a specific quantum
        for(int x = 0; x < quantum; x++)
        {
          
          if(burst_time > 0){
            
            burst_time --;        
            
            outFile.println("Time: " + time + " process: " + process.getPid() + " running");
            outFile.println("");
            
            //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
            
            process.setBurst_time(burst_time);
            
            if(process.getBurst_time() == 0)
            {
              waitingTime = (time - process.getArrival_time()) - process.getOriginalBurst_time();
              process.setWaitingTime(waitingTime);
              queue.add(process);
              list.remove(list.indexOf(process));
              
              //System.out.println("Pid: "+process.getPid()+"\ntime: "+ time+"\nwaiting time: "+waitingTime);
            }
            
            time ++;  
            
          }else
          {
            
            //System.out.println(time+" = time, waiting time: "+waitingTime);
            break;
          }
        }
      }
      
      //Calculates the wait, and response time of each process
      for(int y = 0; y < queue.size(); y++){
        
        //get wait and response of each process
        double wait = queue.get(y).getWaitingTime();
        //System.out.println(wait+" "+queue.get(y).getPid());
        
        double response = queue.get(y).getResponseTime();
        int priority = queue.get(y).getPriority();
        
        //each iteration add the waiting and response times to get avg
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        weightedResponseTime = (response * queue.get(y).getPriority()) + weightedResponseTime;
        avgWaiting = avgWaiting + wait;
        avgResponse = avgResponse + response;
        
        
        
      }
      
      //divide by amount of priority of number of process to get avg
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      avgResponse = avgResponse / queue.size();
      weightedResponseTime = weightedResponseTime / addPriority;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ avgResponse);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
      
      System.out.println(e);
      
    }
    
  }//end of rr
  
  /*
   * @param list is the list with the process to schedule
   * An additional scheduling algorithm of your choice - 
   * this can be some sort of hybrid of the other approaches scheduling algorithm
   */
  public void hybrid(ArrayList<Process> list){
    
    list = sort(list, "arrival_time");
    ArrayList<Process> queue = new ArrayList<Process>();
    
    weightedResponseTime = 0;
    weightedWaitingTime = 0;
    avgResponse = 0;
    addPriority = 0;
    avgWaiting = 0;
    time = 0;
    
    //write inside a try to maake sure all errors are handled
    try{
      
      //print results to specific txt file
      PrintWriter outFile = new PrintWriter("Hybrid-Output.txt");
      outFile.println("Output (Hybrid)");
      outFile.println("");
      
      int first = 0;//only used for printing/testing
      while(!list.isEmpty()){
        
        int hipri = 0;//keeps track of where the highest priority is
        boolean firstprocess = true;
        int loopsize = list.size()/2 + 1;
        
        for(int x = 0; x < loopsize; x++)
        {
          
          loopsize = list.size()/2 + 1;
          
          if(firstprocess && list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0)
          {
            
            //takes the highest priority from the first half of the list
            process = list.get(x);
            hipri = x;
            firstprocess = false;
            
            
            
          }else if(!firstprocess && list.get(x).getPriority() > process.getPriority() 
                     && list.get(x).getArrival_time() <= time 
                     && list.get(x).getBurst_time() > 0){
            
            process = list.get(x);
            hipri = x;
            
            
          }else if(!firstprocess && x == loopsize-1 
                     && process.getArrival_time() <= time 
                     && process.getBurst_time() > 0){
            
            process = list.remove(hipri);
            
            
            if(process.getBurst_time() == process.getOriginalBurst_time())
            {
              
              process.setResponseTime((time - process.getArrival_time()));
              
            }
            
            list.add(process);
            
            break;
            
          }else if(list.get(x).getArrival_time() <= time 
                     && list.get(x).getBurst_time() == 0){
            
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            
            
            
            queue.add(list.get(x));//adds to queue
            list.remove(x);//removes from list
            
            break;
            
            
          }else if(firstprocess && x==loopsize-1 && loopsize<list.size()-1){
            
            
            loopsize++;
            
          }else if(x==list.size()-1 && list.get(x).getArrival_time() > time){
            
            time++;
          }
        }
        
        arrival_time = process.getArrival_time();
        burst_time = process.getBurst_time(); 
        
        //set quantum to quantum
        for(int x = 0; x < quantum; x++){
          
          if(burst_time > 0){
            
            burst_time --;        
            
            outFile.println("Time: " + time + " process: " + process.getPid() + " running");
            outFile.println("");
            
            //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
            
            process.setBurst_time(burst_time);
            //process.setPriority(process.getPriority()-1);
            
            if(process.getBurst_time() == 0)
            {
              waitingTime = (time - process.getArrival_time()) - process.getOriginalBurst_time();
              process.setWaitingTime(waitingTime);
              queue.add(process);
              list.remove(list.indexOf(process));
              
              //System.out.println("Pid: "+process.getPid()+"\ntime: "+ time+"\nwaiting time: "+waitingTime);
            }
            
            time ++;  
          }else{
            
            break;
          }
        }
      }
      
      for(int y = 0; y < queue.size(); y++){
        
        double wait = queue.get(y).getWaitingTime();
        double response = queue.get(y).getResponseTime();
        int priority = queue.get(y).getPriority();
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        weightedResponseTime = (response * queue.get(y).getPriority()) + weightedResponseTime;
        avgWaiting = avgWaiting + wait;
        avgResponse = avgResponse + response;
        
        
      }
      
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      avgResponse = avgResponse / queue.size();
      weightedResponseTime = weightedResponseTime / addPriority;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ avgResponse);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
      
      System.out.println(e);
      
    }
    
  }//end of hybrid
  
////////////////////////End of Methods//////////////////////////////////////////////////// 
}//end of Algorithms class