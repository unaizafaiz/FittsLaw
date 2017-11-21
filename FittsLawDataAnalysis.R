#Reading Fitts law apparatus data from a file
dataFL <-read.table("logfile.txt",header = FALSE, sep=":", col.names = c("W","D","MT"))
dataFL

#Calculating ID for each D,W and MT
dataFL$ID<- log2((dataFL$D/dataFL$W)+1)
dataFL

#Saving data of ID, MT
fittsData<- dataFL[,c(3,4)]

#Scatter plot of the data 
scatter.smooth(x=fittsData$ID, y=fittsData$MT, main="ID ~ MT")

#Finding regression co-efficients through linear regression
linearMod <- lm(fittsData$MT~fittsData$ID, data=fittsData)
summary(linearMod)

#drawing the line to get regression coeffecient a,b
abline(linearMod, col="red")

#Calculating throughput
fittsData$TP<- fittsData$ID/fittsData$MT

#plot graph of MT vs ID
plot(fittsData$MT, fittsData$ID)

#plot graph of TP vs ID
plot(fittsData$TP, fittsData$ID)


