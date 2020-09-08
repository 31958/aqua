<?php

include 'config.php' ;
 
 $con = mysqli_connect($host,$user,$pass,$db);
 
 $messageID = "0";
 $senderID = "0";
 $receiverID = "0";
 $message = "0";
 $dateTime = "0";

 $sql = "INSERT INTO 'Messages' ('messageID', 'senderID', 'receiverID', 'message', 'dateTime') VALUES ('0', '0', '0', '0', '0');";
 
 if(mysqli_query($con,$sql)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 echo $sql;
 
 }
 mysqli_close($con);
?>