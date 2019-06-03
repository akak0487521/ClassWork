myfun <- function(x)
{
  ggplot(x, aes(Date,Avg.price)) + geom_point() + geom_line()
}



Banana <- read_excel("A1.xls")
Orange <- read_excel("D1.xls")
Mango <- read_excel("R1.xls")
colnames(Banana) <- c("Date","Market","Product","Avg.price","DeltAmount")
colnames(Mango) <- c("Date","Market","Product","Avg.price","DeltAmount")
colnames(Orange) <- c("Date","Market","Product","Avg.price","DeltAmount")
Banana$Date <- as.Date(Banana$Date, order = "ymd")
Orange$Date <- as.Date(Orange$Date, order = "ymd")
Mango$Date <- as.Date(Mango$Date, order = "ymd")
ggplot(Mango, aes(Date,Avg.price,color = Market)) + geom_point() + geom_line()
ggplot(Banana, aes(Date,Avg.price,color = Market)) + geom_point() + geom_line()
ggplot(Orange, aes(Date,Avg.price,color = Market)) + geom_point() + geom_line()