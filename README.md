# Schedulers
Understand the different scheduling algorithms, and be able to measure how efficient they are

Introduction
This purpose of this assignment is to write a few scheduling algorithms and analyze their effectiveness.
(this project must be done in pairs unless given permission otherwise)

Logistics
Since the goal is only to understand the different scheduling algorithms, and be able to measure how efficient they are, you will not create actual processes. Instead you will just simulate a scheduler and display the behaviour of your scheduler. There is no starter code, and all the necessary details are given in the following sections

Input
Your program will read the incoming processes from a text file. Each line in the text file will represent a process and will have the following information:
pid, burst_time, arrival_time, priority.
For example your text file might look like the following:
101, 10, 0, 1
102, 8, 0, 3
103, 5, 5, 5
104, 4, 7, 10
note: Higher priority value implies higher priority.

Scheduling Algorithms
Your code should be able to simulate the following scheduling algorithms:
First Come First serve (fcfs) (nonPreemptive)
Shortest job first (sjf) (nonPreemptive)
Shortest Remaining Time First (srtf)  (Preemptive)
Priority (nonpreprior) (nonPreemptive with aging)
Priority (preprior) (Preemptive with aging)
Round Robin (rr) (Different Times)
An additional scheduling algorithm of your choice - this can be some sort of hybrid of the other approaches (see book for examples).

Design
Your design should be as follows:
Write your scheduler in java
Your main function should accept two strings. One which states which scheduler to us (e.g. fcfs) and the other which states the input file name.

First read in the text file, storing the information in some data structure.
Then use a loop to simulate time units. As each time unit elapses (each iteration of the loop) do the necessary operations required. This can include adding a process to the ready queue, calling the scheduler, or no operations.
At each time step print out which process is currently running on the CPU.
At the end print the analysis details.
Note that I do not care about efficiency for this program as long as the output is correct.

Analysis
For each algorithm you are required to provide the following information:
Average waiting time: The average time each process spent in the ready queue
Weighted average waiting time: The average time each process spent in the ready queue multiplied by its priority. That is: 
Average response time: In this context we will consider the response time to be the time from when the process arrived till the process started running (time in the ready queue before running for the first time).
Weighted average response time: The time from when the process arrived till the process started running multiplied by its priority. That is: 

Submission
Besides the code to your scheduler please submit the following Report:
A short introduction to the problem you are trying to solve.
A description of the how each of the scheduling methods work, including your hybrid one.
A description of your code design and your choice of data structures.

Correctness Results - In this section you should present the results for each scheduler in the following way:
Show a small input file (around 4 processes).
Manually draw a Gantt Chart to represent the scheduling.
Manually calculate the average waiting time and average response time.
Show the output of your algorithm to ensure correctness.

Analysis Results - Using a larger input file (50 processes), show a table which compares the results of all different scheduling algorithms. In addition, for the rr algorithm show a graph of the different analysis measurements as a function of the quantum time. If your own scheduling algorithm has parameters as well, show how they affect the analysis measurements in a graph as well.
Conclusion - Which algorithms worked the best and in what cases.

An appendix describing which part of the project each team member was in charge of.

References - If necessary.
Example Input
101, 10, 0, 1
102, 8, 0, 3
103, 5, 5, 5
104, 4, 7, 10

Example Output (fcfs)
Time: 0, process 101 running
Time: 1, process 101 running
Time: 2, process 101 running
Time: 3, process 101 running
Time: 4, process 101 running
Time: 5, process 101 running
Time: 6, process 101 running
Time: 7, process 101 running
Time: 8, process 101 running
Time: 9, process 101 running
Time: 10, process 102 running
Time: 11, process 102 running
Time: 12, process 102 running
Time: 13, process 102 running
Time: 14, process 102 running
Time: 15, process 102 running
Time: 16, process 102 running
Time: 17, process 102 running
Time: 18, process 103 running
Time: 19, process 103 running
Time: 20, process 103 running
Time: 21, process 103 running
Time: 22, process 103 running
Time: 23, process 104 running
Time: 24, process 104 running
Time: 25, process 104 running
Time: 26, process 104 running
Average waiting time is: 9.75
Average weighted waiting time is: 13.421052631578947
Average reponse time is: 9.75
Average weighted reponse time is: 13.421052631578947
Example Output (preprior)
Time: 0, process 102 running
Time: 1, process 102 running
Time: 2, process 102 running
Time: 3, process 102 running
Time: 4, process 102 running
Time: 5, process 103 running
Time: 6, process 103 running
Time: 7, process 104 running
Time: 8, process 104 running
Time: 9, process 104 running
Time: 10, process 104 running
Time: 11, process 103 running
Time: 12, process 103 running
Time: 13, process 103 running
Time: 14, process 102 running
Time: 15, process 102 running
Time: 16, process 102 running
Time: 17, process 101 running
Time: 18, process 101 running
Time: 19, process 101 running
Time: 20, process 101 running
Time: 21, process 101 running
Time: 22, process 101 running
Time: 23, process 101 running
Time: 24, process 101 running
Time: 25, process 101 running
Time: 26, process 101 running
Average waiting time is: 7.5
Average weighted waiting time is: 3.3684210526315788
Average reponse time is: 4.25
Average weighted reponse time is: 0.8947368421052632
