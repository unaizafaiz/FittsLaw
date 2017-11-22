#Reading Fitts law apparatus data from a file
dataFL <-read.table("logfile.txt",header = FALSE, sep=":", col.names = c("W","D","MT"))
dataFL

#compute mean of Movement Time for each case
dataFL <- aggregate(dataFL$MT, list(W=dataFL$W,D=dataFL$D), mean)

#Calculating ID for each D,W and MT
dataFL$ID<- log2((dataFL$D/dataFL$W)+1)
dataFL

#Saving data of ID, MT
fittsData<- dataFL[,c(3,4)]
fittsData

#Finding regression co-efficients through linear regression
linearMod <- lm(fittsData$x~fittsData$ID, data=fittsData)
summary(linearMod)

#plot graph of MT vs ID
plot(fittsData$ID, fittsData$x)

#drawing the line to get regression coeffecient a,b
abline(linearMod, col="red")

#Calculating throughput - where x is the MT value
fittsData$TP<- fittsData$ID/fittsData$x

#plot graph of TP vs ID
plot(fittsData$ID, fittsData$TP)

#Finding regression co-efficients through linear regression
linearModTP <- lm(fittsData$TP~fittsData$ID, data=fittsData)

#drawing the line to get regression coeffecient a,b
abline(linearModTP, col="red")


