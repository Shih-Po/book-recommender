library(dplyr)
library(ggplot2)

# load files
setwd("/Users/shipo/Projects/book-recommender/test/shihpo/")
booklist <- read.csv("booklist.csv")

names(booklist)
booklist$category %>% summary()

bc <- booklist %>% select(ISBN, category)
bc_type <- bc %>% group_by(category) %>% summarise(count = n())

View(bc_type)

# test clean category
b_category_dict <- booklist$category %>% unique() %>% as.character()

for (i in 1:length(b_category_dict)) {
  b_category_dict[i] <- strsplit(b_category_dict[i], split = "   ")[[1]][1]
  b_category_dict[i] <- strsplit(b_category_dict[i], split = "  ")[[1]][1]
  b_category_dict[i] <- strsplit(b_category_dict[i], split = " ")[[1]][1]
  print(b_category_dict[i])
}

ggplot(bc_type, aes(x = category, y = count)) + geom_bar(stat = "identity")
