Ycount <- function(x)
{
   tmpHeader <- substr(x,0,1)
   tmpID <- paste(c(headernum[which(header == tmpHeader)],substr(x,2,10)),sep = "", collapse = "")
   tmpX <- as.numeric(tmpID)
   tmpY <- sum(c(tmpX %/% 10000000000 * 1,(tmpX %/% 1000000000 ) %% 10 * 9,(tmpX %/% 1000000000 ) %% 10 * 8,(tmpX %/% 100000000 ) %% 10 * 7,(tmpX %/% 10000000 ) %% 10 * 6,(tmpX %/% 1000000 ) %% 10 * 5,(tmpX %/% 100000 ) %% 10 * 4,(tmpX %/% 10000 ) %% 10 * 3,(tmpX %/% 1000 ) %% 10 * 2,(tmpX %/% 100 ) %% 10 * tmpX %% 10 * 1))
   return(tmpY)
}

testY <- function(y)
{
   if((y %% 10 ) == 0)
   return(TRUE)
   else return(FALSE)
}

generate <- function(n)
{
   IDset <- replicate(n, paste(c(sample(header,size = 1),sample(sexnum,size = 1),sample(c(0:9), size = 8, replace = TRUE)),sep = "", collapse = ""));
   return(IDset)
}

regionDefine <- function(m, x)
{
    tmpHeader <- substr(x,0,1)
    RegSet <- replicate(m,paste(Regions[which(header = tmpHeader)]))
}

DefineStatus <- function()
{
  header <- c(LETTERS[1:22], LETTERS[24:25])
  headernum <- c(10,11,12,13,14,15,16,17,34,18,19,20,21,22,35,23,24,25,26,27,28,29,30,31)
  sexnum <- c(1,2)
  Regions <- c("","","","","","","","","","","",)
}
