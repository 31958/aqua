<?php

include 'config.php' ;
 
 $con = mysqli_connect($host,$user,$pass,$db);
 
 $messageID = $_POST['messageID'];
 $senderID = $_POST['senderID'];
 $receiverID = $_POST['receiverID'];
 $message = $_POST['message'];
 $dateTime = $_POST['dateTime'];

 $sql = "INSERT INTO Messages (messageID, senderID, receiverID, message, dateTime) VALUES ('$messageID','$senderID','$receiverID','$message','$dateTime')";
 
 if(mysqli_query($con,$sql)){
 
 echo 'Data Submit Successfully';
 
 }
 else{
 
 echo 'Try Again';
 
 }
 mysqli_close($con);
?>