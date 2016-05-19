library(dplyr)

# Fix the bookname, delete not important words
booknameFixer = function(dchar) {
  dchar <- gsub('的', '', dchar)
  dchar <- gsub('-', '', dchar)
  dchar <- gsub('!', '', dchar)
  dchar <- gsub('！', '', dchar)
  dchar <- gsub(':', '', dchar)
  dchar <- gsub('\\.', '', dchar)
  dchar <- gsub('：', '', dchar)
  dchar <- gsub('，', '', dchar)
  dchar <- gsub('？.', '', dchar)
  dchar <- gsub('、', '', dchar)
  dchar <- gsub('（', '', dchar)
  dchar <- gsub('）', '', dchar)
  dchar <- gsub('～', '', dchar)
  dchar <- gsub('\\+', '', dchar)
  dchar <- gsub('\\(', '', dchar)
  dchar <- gsub('\\)', '', dchar)
  dchar <- gsub('\\d', '', dchar)
  return(dchar)
}

head(df)

for (i in 200:210) {
  dchar <- df['bookname'][[1]][i] %>% as.character()
  print(dchar)
  
  dchar <- booknameFixer(dchar)
  print(dchar)
}