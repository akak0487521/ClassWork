myfun1 <- function(x)
{
  total <- c(sum(x$Credit[which(x$Score >= 60)]), mean(x$Score),  sum((x$Score * x$Credit) / sum(x$Credit)))
  cat(sprintf("總學分: %.1f    平均: %.2f    加權平均: %.2f\n", total[1],total[2],total[3]))
}
myfun2 <- function(x)
{
  sem <- unique(x$Semester)
  credit2 <- sum(x$Credit)
  realCredit <- sum(x$Credit[which(x$Score >= 60)])
  mean2 <- mean(x$Score)
  Wmean <- sum((x$Score * x$Credit) / sum(x$Credit))
  failes <- paste(x$Course[which(x$Score < 60)], collapse = " ")
  cat(sprintf("%8s   %.1f        %.1f     %.2f      %.2f     %s \n",sem,credit2,realCredit,mean2,Wmean,failes))
}

myfun <- function()
{
  
  df <- data.frame(read.table(file = "~/transcript.csv", sep = ",",header = TRUE))
  myfun1(df)
  cat(c("    學期","  修習學分","    實得學分","    平均","    加權平均","    不及格科目"), seperate = " ")
  cat("\n-------------------------------------------------------------------------------\n")
  d_ply(df, .variables = "Semester", .fun = myfun2)
  passes <- df[which(df$Score < 60)]
}  
