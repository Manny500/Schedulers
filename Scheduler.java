//Imports
import java.io.*;
import java.util.*;

 /*
  * @Author Manny 
  */

public class Scheduler{
  public static void main(String[] args){
  
   //Instanciate classes
    Parse parse = new Parse();
    Algorithms alg = new Algorithms();

   //Instance Variables
    File processFile = new File("Process.txt");
    ArrayList<Process> list = new ArrayList<Process>();
    
    parse.addToDataBase(processFile);
   
    list = parse.getList();
    list = alg.sort(list);
    
    alg.fcfs(list);
   //write output to file
    
  }//end of Main
}//end of Scheduler