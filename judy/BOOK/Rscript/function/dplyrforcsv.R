library(dplyr)
#設定路徑　
setwd("E:/book-recommender/judy/BOOK")


x<-readLines("./input/book.csv",encoding = "UTF-8")



x.header<-strsplit(x[1],split = ",")%>%
  unlist()%>%
  gsub("\"","",.)%>%
  gsub("\\\\","",.)


for(i in (2:length(x))){
  x.header<-rbind(x.header,strsplit(x[i],split = ",")%>%unlist())
}

View(x.header)

gsub("\"","",.)%>%
  gsub("\\\\","",.)

