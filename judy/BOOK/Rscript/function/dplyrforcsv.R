library(dplyr)
#設定路徑　
setwd("E:/book-recommender/judy/BOOK")
x<-readLines("./input/book.csv",encoding = "UTF-8")

x.header<-NULL
for(i in (2:length(x))){
  x.header<-rbind(x.header,strsplit(x[i],split = ",")%>%unlist())
}

x.sub<-
  x.header%>%
  gsub("\"","",.)%>%
  gsub("\\\\","",.)

colnames(x.sub)<-  
  strsplit(x[1],split = ",")%>%
  unlist()%>%
  gsub("\"","",.)%>%
  gsub("\\\\","",.)

db<-x.sub
db%>%na.exclude()%>%View()
db[3454,]

