<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>ACS106121_Ch5_hw.php</title>
</head>
<body>
<table border="1"> 
<?php 

// 顯示表格的標題列
print "<tr><td bgcolor=\"#52a3ff\">*</td>";
$station = array("","基隆","八堵","七堵","五堵","汐止","南港","松山","台北");
$ticket = array("優待票","全票");
for ( $i = 1; $i <= 8; $i++ ) 
   print "<td bgcolor=\"#b8bbff\"><b>" . $station[$i] . "</b></td>";
print "</tr>";
// 巢狀迴圈
for ( $i = 1; $i <= 8; $i++ ) {
   print "<tr>";
   print "<td bgcolor=\"#8adcff\"><b>" . $station[$i] . "</b></td>";
   $j = 1;
   while ( $j <= 8 ) { 
	  if($i > $j )
	  {
		print "<td bgcolor=\"#ebffef\">";
		$a = abs(($i - $j) * 10);
		print "<font color=red>$a</font>";
		print "</td>";
		$j++;
	  }
	  else if ($j > $i)
	  {
		print "<td bgcolor=\"#ebf7ff\">";
		print abs(($i - $j) * 20);
		print "</td>";
		$j++;
	  }
	  else
	  {
		print "<td>";
		print abs(($i - $j) * 0);
		print "</td>";
		$j++;
	  }
      
   }
   print "</tr>";
}
print $station[$_POST["start"]] . "到" . $station[$_POST["end"]] . "的" . $ticket[$_POST["price"]] . "價是" . abs(($_POST["start"] - $_POST["end"])) * 10 * (++$_POST["price"]); 
?>
</table>
<form action="Ch5_4.php" method="post">
起站：  <select name="start">
　			<option value="1">基隆</option>
　			<option value="2">八堵</option>
　			<option value="3">七堵</option>
　			<option value="4">五堵</option>
　			<option value="5">汐止</option>
　			<option value="6">南港</option>
　			<option value="7">松山</option>
　			<option value="8">台北</option>
		</select><br>
訖站：	<select name="end">
　			<option value="1">基隆</option>
　			<option value="2">八堵</option>
　			<option value="3">七堵</option>
　			<option value="4">五堵</option>
　			<option value="5">汐止</option>
　			<option value="6">南港</option>
　			<option value="7">松山</option>
　			<option value="8">台北</option>
		</select><br>
票種：	<select name="price">
　			<option value="0">優待票</option>
　			<option value="1">全票</option>
		</select><br>
		<input type="Submit">
</form>
</body>
</html>
