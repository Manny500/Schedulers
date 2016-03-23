//Imports
import java.io.*;
import java.util.*;

/*
 * @Author Conrad 
 * creates a file called Processes.txt with a random number of processes between 500 and 900 with 
 * randomized burst, arrival, priority numbers. the pid starts at 1000
 */

public class RandomProcessGenerator{
  public static void main(String[] args){
    
    int pid, burst, arrival, priority, numProcesses;
    
    try{
      
      PrintWriter outFile = new PrintWriter("Processes.txt");
      //outFile.println("Pid\tBurst Time\tArrival Time\tPriority");
      
      //numProcesses = (int)(Math.random() * 500 + 400);//random number of processes between 500 and 900
      numProcesses = 50;
      
      for(int i = 1000; i< numProcesses+1000; i++)
      {
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
    
  }//end of Main
}//end of RandomProcessGenerator