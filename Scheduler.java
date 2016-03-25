//Imports
import java.io.*;
import java.util.*;

/*
 * @Author Manny + Conrad
 */

public class Scheduler{
  public static void main(String[] args){
    
    //Instantiate classes
    Parse parse = new Parse();
    Algorithms alg = new Algorithms();
    
    //generate Large process file, analysis purposes
    alg.randomProcessGenerator("Large-Processes.txt");
    
    //start the different scheduling algorithms
    //They will write to an output file
    //alg.scheduler("fcfs", "Small-Processes.txt");
    //alg.scheduler("sjf", "Small-Processes.txt");
    alg.scheduler("srtf", "Small-Processes.txt");
    //alg.scheduler("nonpreprior", "Small-Processes.txt");
    //alg.scheduler("preprior", "Small-Processes.txt");
    //alg.scheduler("rr", "Small-Processes.txt");
    //alg.scheduler("hybrid", "Small-Processes.txt");
    
  }//end of Main
}//end of Scheduler