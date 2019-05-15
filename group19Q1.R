myfun <- function()
{
  anslist <- (read.csv(file = "./anslist.csv", header = TRUE, sep = ","))
  ans <- c("B", "A", "A", "D", "C", 20, 20, 30, "Cherry", "Pear")
  credit <- c(8, 8, 8, 8, 8, 10, 10, 10, 15, 15)
  classes <- factor(anslist[,1]);memberNum <- c(sum(anslist[,1] == levels(classes)[1]),sum(anslist[,1] == levels(classes)[2]),sum(anslist[,1] == levels(classes)[3]),sum(anslist[,1] == levels(classes)[4]),sum(anslist[,1] == levels(classes)[5]),sum(anslist[,1] == levels(classes)[6]),sum(anslist[,1] == levels(classes)[7]),sum(anslist[,1] == levels(classes)[8]));classes <- (levels(classes))
  cat("班級:    ");cat(classes,sep = "    ");cat("    總數\n")
  cat("----------------------------------------------------\n")
  cat("人數:   ");cat(memberNum,sep = "   ");cat(c("   ",sum(memberNum),"\n"))
  scoreBound <- c("0~10","10~20","20~30","30~40","40~50","50~60","60~70","70~80","80~90","90~100")
  cat("\n分數:  ");cat(scoreBound,sep = " ")
  cat("\n------------------------------------------------------------------\n")
}
//dalpy(anslist,variables = c("E1", "E2", "E3", "E4", "E5", "M1", "M2", "M3", "Q1", "Q2"), fun = check,)