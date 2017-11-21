# Title     : TODO
# Objective : TODO
# Created by: unaizafaiz
# Created on: 11/19/17


df<-read.table("output.txt",header = FALSE, sep=":",
              col.names = c("Index","C1x","C1y","C2x","C2y","Click1x","Click1y","Click2x","Click2y","Time"))
print(df)
df$c1ErrorX <-(df$Click1x-df$C1x)
df$c1ErrorY <-(df$Click1y-df$C1y)
df$c2ErrorX <-(df$Click2x-df$C2x)
df$c2ErrorY <-(df$Click2y-df$C2y)

df<-read.table("logfile.txt",header = FALSE, sep=":", col.names = c("W","D","Time"))
print(df)

#Calculate ID for each
df$ID <- log2((df$D/df$W)+1)
print(df)

#Building data frame of only columns ID and MT
scatterPlot<- df[,c(3,4)]

#Scatter Plot
scatter.smooth(x=scatterPlot$ID, y=scatterPlot$Time, main="ID ~ MT")


#Linear Regression
plot(scatterPlot$ID, scatterPlot$Time)
model1 <- lm(scatterPlot$Time~scatterPlot$ID, data=scatterPlot)
model1
abline(model1, col="red")


#Plot MT vs ID and TP vs ID
plot(scatterPlot$Time, scatterPlot$ID)
plot(scatterPlot$TP, scatterPlot$ID)

#Calculate meanTime for each group
meanTime <- aggregate(df$Time, list(W=df$W,D=df$D), mean)
print(meanTime)

#Calculate ID for each group
meanTime$ID <- log2((meanTime$D/meanTime$W)+1)
print(meanTime)

#save the data into a table
scatterPlotTable <- data.frame(meanTime$x,meanTime$ID)