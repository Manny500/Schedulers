import java.io.*;
import java.util.*;

/*
 * @Author Manny + Conrad
 */

public class Algorithms{
  
  //instance  Variables
  Process process;
  Parse parse = new Parse();
  int burst_time;
  int arrival_time;
  int time;
  int addPriority;
  int waitingTime;
  double avgWaiting = 0;
  double weightedWaitingTime;
  double responseTime;
  double weightedResponseTime;
  int pid;
  int burst;
  int arrival;
  int priority;
  int numProcesses;
  
  //constructor
  public Algorithms(){
    
  }
  ////////////////////////////METHODS/////////////////////////////////////////////
  //start of methods
  
  /*
   * @ file is the file name to write to 
   */
  public void randomProcessGenerator(String file){
    
    try{
      
      PrintWriter outFile = new PrintWriter(file);
      numProcesses = 50;
      
      for(int i = 1000; i < numProcesses+1000; i++){
        
        pid = i;
        burst = (int)(Math.random() * 50 + 1);
        arrival = (int)(Math.random() * 50);
        priority = (int)(Math.random() * 50 + 1);
        
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
    
    ArrayList<Process> list = new ArrayList<Process>();
    
    //add the process to database
    parse.addToDataBase(processFile);
    
    //get the process from the database
    list = parse.getList();
    
    if(algorithm == "fcfs"){
      
      fcfs(list);
      
    }else if(algorithm == "sjf"){
      
      sjf(list);
      
    }else if(algorithm == "srtf"){
      
      srtf(list);
      
    }else if(algorithm == "pnna"){
      
      pnna(list);
      
    }else if(algorithm == "pppa"){
      
      pppa(list);
      
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
    
    if(order == "arrival_time"){
      
      for (int i = 1; i < list.size(); i++){
        
        process = list.get(i);
        int index = process.getArrival_time(); 
        int j = i;
        
        while (j > 0 && list.get(j-1).getArrival_time() > index){
          
          Process process1 =list.get(j-1);
          //list.remove(j);
          list.set(j-1, process);
          list.set(j,process1);
          j--;
          
        }
        
      }
      
    }else if(order == "priority"){
      
      for (int i = 1; i < list.size(); i++){
        
        process = list.get(i);
        int index = process.getPriority(); 
        int j = i;
        
        while (j > 0 && list.get(j-1).getPriority() < index){
          
          Process process1 =list.get(j-1);
          //list.remove(j);
          list.set(j-1, process);
          list.set(j,process1);
          
          j--;
          
        }
        
      }
      
    }else if(order == "burst_time"){
      
      for (int i = 1; i < list.size(); i++){
        
        process = list.get(i);
        int index = process.getBurst_time(); 
        int j = i;
        
        while (j > 0 && list.get(j-1).getBurst_time() > index){
          
          Process process1 =list.get(j-1);
          //list.remove(j);
          list.set(j-1, process);
          list.set(j,process1);
          
          j--;
          
        }
        
      }
      
    }
    
    /*for (int i = 0; i < list.size(); i++){
     * 
     int t = list.get(i).getPriority();
     
     System.out.println("order? : " +t);
     
     }*/
    
    return list;
  }//end of sort
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the fcfs schedule to
   * First Come First serve (fcfs) (nonPreemptive) scheduling algorithm
   */
  public void fcfs(ArrayList<Process> list){
    
    list = sort(list, "arrival_time");
    
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
      outFile.println("Average response time is: "+ responseTime);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      outFile.close();
      
    }catch(FileNotFoundException e){
    }
  }//end of fcfs
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the sjf schedule to
   * Shortest job first (sjf) (nonPreemptive) scheduling algorithm
   */
  public void sjf(ArrayList<Process> list){
    
    list = sort(list, "burst_time");
    ArrayList<Process> queue = new ArrayList<Process>();
    
    time = 0;
    
    try{
      
      PrintWriter outFile = new PrintWriter("Process-Results.txt");
      outFile.println("Output (sjf)");
      outFile.println("");
      
      while(!list.isEmpty()){
        
        int x = 0;
        
        for(x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time ){
            
            process = list.get(x);
            
            break;
          }
        }
        
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
        
        queue.add(process);
        list.remove(x);
        
      }
      
      for(int y = 0; y < queue.size(); y++){
        
        double wait = queue.get(y).getWaitingTime();
        int priority = queue.get(y).getPriority();
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        avgWaiting = avgWaiting + wait;
        
      }
      
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      responseTime = avgWaiting;
      weightedResponseTime = weightedWaitingTime;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ responseTime);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      outFile.close();
      
    }catch(FileNotFoundException e){
    }
    
  }//end of sjf
  
  
  /*
   * @param list is the ArrayList<Process> list of process to apply the srtf schedule to
   * Shortest Remaining Time First (srtf)  (Preemptive) scheduling algorithm
   */
  public void srtf(ArrayList<Process> list){
    
    list = sort(list, "burst_time");
    ArrayList<Process> queue = new ArrayList<Process>();
    
    time = 0;
    
    try{
      
      PrintWriter outFile = new PrintWriter("Process-Results.txt");
      outFile.println("Output (sjf)");
      outFile.println("");
      
      while(!list.isEmpty()){
        
        for(int x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0){
            
            process = list.get(x);
            
            break;
          }else if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() == 0){
            
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            queue.add(process);
            list.remove(x);
            
          }
        }
        
        arrival_time = process.getArrival_time();
        burst_time = process.getBurst_time();       
        burst_time --;        
        
        outFile.println("Time: " + time + " process: " + process.getPid() + " running");
        outFile.println("");
        
        //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
        
        process.setBurst_time(burst_time);
        
        time ++;  
        
      }
      
      for(int y = 0; y < queue.size(); y++){
        
        double wait = queue.get(y).getWaitingTime();
        int priority = queue.get(y).getPriority();
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        avgWaiting = avgWaiting + wait;
        
      }
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      responseTime = avgWaiting;
      weightedResponseTime = weightedWaitingTime;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ responseTime);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
    }
    
  }//end of srtf
  
  /*
   * @
   * @
   * Priority (nonpreprior) (nonPreemptive with aging) scheduling algorithm
   */
  public void pnna(ArrayList<Process> list){
    
    ArrayList<Process> queue = new ArrayList<Process>();
    
    time = 0;
    
    try{
      
      PrintWriter outFile = new PrintWriter("Process-Results.txt");
      outFile.println("Output (pnna)");
      outFile.println("");
      
      while(!list.isEmpty()){
        
        //list = sort(list, "priority");
        int x = 0;
        
        for(x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time ){
            
            process = list.get(x);
            
            break;
          }
        }
        
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
        
        queue.add(process);
        list.remove(x);
        
        for(x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time ){
            
            int aging = list.get(x).getPriority();
            
            if(aging > 0){
              
              list.get(x).setPriority(aging + 1);
              
            }
          }
        }
        
      }
      
      for(int y = 0; y < queue.size(); y++){
        
        double wait = queue.get(y).getWaitingTime();
        int priority = queue.get(y).getPriority();
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getPriority()) + weightedWaitingTime;
        avgWaiting = avgWaiting + wait;
        
      }
      
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      responseTime = avgWaiting;
      weightedResponseTime = weightedWaitingTime;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ responseTime);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      outFile.close();
      
    }catch(FileNotFoundException e){
    }
    
  }//end of pnna
  
  /*
   * @
   * @
   * Priority (preprior) (Preemptive with aging) scheduling algorithm
   */
  public void pppa(ArrayList<Process> list){
    
    ArrayList<Process> queue = new ArrayList<Process>();
    time = 0;
    
    try{
      
      PrintWriter outFile = new PrintWriter("Process-Results.txt");
      outFile.println("Output (pppa)");
      outFile.println("");
      
      while(!list.isEmpty()){
        
        list = sort(list, "priority");
        
        for(int x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0){
            
            process = list.get(x);
            
            break;
          }else if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() == 0){
            
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            queue.add(process);
            list.remove(x);
            
          }
        }
        
        arrival_time = process.getArrival_time();
        burst_time = process.getBurst_time();       
        burst_time --;        
        
        outFile.println("Time: " + time + " process: " + process.getPid() + " running");
        outFile.println("");
        
        //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
        
        process.setBurst_time(burst_time);
        
        time ++;  
        
        for(int x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time ){
            
            int aging = list.get(x).getPriority();
            
            if(aging > 0){
              
              list.get(x).setPriority(aging + 1);
              
            }
          }
        }
        
      }
      
      for(int y = 0; y < queue.size(); y++){
        
        double wait = queue.get(y).getWaitingTime();
        int priority = queue.get(y).getOriginalPriority();
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getOriginalPriority()) + weightedWaitingTime;
        avgWaiting = avgWaiting + wait;
        
      }
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      responseTime = avgWaiting;
      weightedResponseTime = weightedWaitingTime;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ responseTime);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
    }
  }//end of pppa
  
  /*
   * @
   * @ li
   * Round Robin (rr) (Different Times) scheduling algorithm
   */
  public void rr(ArrayList<Process> list){
    
    list = sort(list, "arrival_time");
    ArrayList<Process> queue = new ArrayList<Process>();
    
    time = 0;
    
    try{
      
      PrintWriter outFile = new PrintWriter("Process-Results.txt");
      outFile.println("Output (rr)");
      outFile.println("");
      
      while(!list.isEmpty()){
        
        for(int x = 0; x < list.size(); x++){
          
          if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0){
            
            process = list.get(x);
            list.remove(x);
            list.add(process);
            
            break;
          }else if(list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() == 0){
            
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            queue.add(process);
            list.remove(x);
            
          }else if(x==list.size()-1 && list.get(x).getArrival_time() > time)
          {
            time++;
          }
        }
        
        arrival_time = process.getArrival_time();
        burst_time = process.getBurst_time(); 
        
        for(int x = 0; x < 2; x++){
          
          if(burst_time > 0){
            
            burst_time --;        
            
            outFile.println("Time: " + time + " process: " + process.getPid() + " running");
            outFile.println("");
            
            System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
            
            process.setBurst_time(burst_time);
            
            time ++;  
          }else{
            
            break;
          }
        }
      }
      
      for(int y = 0; y < queue.size(); y++){
        
        double wait = queue.get(y).getWaitingTime();
        int priority = queue.get(y).getOriginalPriority();
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getOriginalPriority()) + weightedWaitingTime;
        avgWaiting = avgWaiting + wait;
        
      }
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      responseTime = avgWaiting;
      weightedResponseTime = weightedWaitingTime;
      
      System.out.println(avgWaiting);
      System.out.println(weightedWaitingTime);
      System.out.println(responseTime);
      System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ responseTime);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
    }
    
  }//end of rr
  
  /*
   * @param list is the list with the process to schedule
   * An additional scheduling algorithm of your choice - 
   * this can be some sort of hybrid of the other approaches scheduling algorithm
   */
  public void hybrid(ArrayList<Process> list){
    
    list = sort(list, "priority");
    ArrayList<Process> queue = new ArrayList<Process>();
    
    time = 0;
    
    try{
      
      PrintWriter outFile = new PrintWriter("Process-Results.txt");
      outFile.println("Output (hybrid)");
      outFile.println("");
      

      while(!list.isEmpty()){
        
        int hipri = 0;//keeps track of where the highest priority is
        boolean firstprocess = true;
        int loopsize = list.size()/2 +1;
        
        for(int x = 0; x < loopsize; x++){
          
          if(firstprocess && list.get(x).getArrival_time() <= time && list.get(x).getBurst_time() > 0){
            
            //takes the highest priority from the first half of the list
            process = list.get(x);
            hipri = x;
            firstprocess = false;
            
          }else if(!firstprocess && list.get(x).getPriority() > process.getPriority() 
                     && list.get(x).getArrival_time() <= time 
                     && list.get(x).getBurst_time() > 0){
            
            process = list.get(x);
            hipri = x;
            
            
          }else if(x == loopsize-1 
                     && process.getArrival_time() <= time 
                     && process.getBurst_time() > 0){
            
            list.remove(hipri);
            list.add(process);
            break;
            
          }else if(list.get(x).getArrival_time() <= time 
                     && list.get(x).getBurst_time() == 0){
            
            waitingTime = (time - list.get(x).getArrival_time()) - list.get(x).getOriginalBurst_time();
            list.get(x).setWaitingTime(waitingTime);
            process = list.get(x);
            queue.add(process);//adds to queue
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
        
        //set quantum to 3
        for(int x = 0; x < 3; x++){
          
          if(burst_time > 0){
            
            burst_time --;        
            
            outFile.println("Time: " + time + " process: " + process.getPid() + " running");
            outFile.println("");
            
            //System.out.println("Time: "+ time + " process: " + process.getPid() + " running");
            
            process.setBurst_time(burst_time);
            
            time ++;  
          }else{
            
            break;
          }
        }
      }
      
      for(int y = 0; y < queue.size(); y++){
        
        double wait = queue.get(y).getWaitingTime();
        int priority = queue.get(y).getOriginalPriority();
        addPriority = addPriority + priority;
        weightedWaitingTime = (wait * queue.get(y).getOriginalPriority()) + weightedWaitingTime;
        avgWaiting = avgWaiting + wait;
        
      }
      avgWaiting = avgWaiting / queue.size();
      weightedWaitingTime =  weightedWaitingTime / addPriority;
      responseTime = avgWaiting;
      weightedResponseTime = weightedWaitingTime;
      
      //System.out.println(avgWaiting);
      //System.out.println(weightedWaitingTime);
      //System.out.println(responseTime);
      //System.out.println(weightedResponseTime);
      
      outFile.println("Average waiting time is: "+ avgWaiting);
      outFile.println("Average weighted waiting time is: "+ weightedWaitingTime);
      outFile.println("Average response time is: "+ responseTime);
      outFile.println("Average weighted response time is: "+ weightedResponseTime);
      
      outFile.close();
      
    }catch(FileNotFoundException e){
    }
    
  }//end of hybrid
  
  //end of Methods
  
}//end of Algorithms class