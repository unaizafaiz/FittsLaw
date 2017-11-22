# FITTS LAW EXPERIMENT #

CS 522: HCI Homework 5
By Unaiza Faiz

### Steps To Run the Program ###

The project is has two components, data collection using the java application apparatus and analysing the data using R.

To Run the application:
1. Clone the project
2. Use intellij to run the program
3. Follow the instructions to complete the task and get the data

The data collected is saved in a file name data\_dump.log

To run the R script: (FittsLaw/FittsLawDataAnalysis.R)
1. Run the R script (using an IDE like R studio)

##Fitts Law Java Application
The application captures movement time for 2 target width and 3 different distances. It iterates the target circles in clockwise movement to get the data. The cases of target size and distance and randomly changed for the user. The data collected is stored in data_dump.log file

##Using R to analyse data
The data is analysed using R. The following steps are performed for the same:

1. We first read the data from the data\_dump.log file and store it in a table
2. Next, we find the mean time for each case using the aggregate() function.
3. We calculate ID for each distance D and target width W as ID = log2((D/W)+1)
4. The regression co-effeciencts are calculated for MT vs ID using lm() 
5. We plot a graph of MT ~ ID and the linear regression line to verify the fit.
6. The throughput for each case is then calculated as TP = ID/MT 
7. A graph and linear regression is plotted for TP vs ID using same steps as while plotting MT vs ID
8. The data is analysed for both.

##Report
The report for the experiment has been submitted on blackboard and can also be found in the location FittsLaw/FittsLaw\_Report.doc