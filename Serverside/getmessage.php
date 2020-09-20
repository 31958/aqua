<?php

include 'config.php' ;

 $con = mysqli_connect($host,$user,$pass,$db);

 $senderID = $_GET['senderID'];

 $sql = "SELECT messageID, senderID, receiverID, message, dateTime FROM Messages";
 $result = mysqli_query($con,$sql)

 if ($result->num_rows > 0) {
   // output data of each row
   while($row = $result->fetch_assoc()) {
     echo "messageID: " . $row["messageID"]. "\nsenderID: " . $row["senderID"]. "\nreceiverID: " . $row["receiverID"]. "\nmessage: " . $row["message"]. "\ndateTime: " . $row["dateTime"];
   }
 } else {
   echo "0 results";
 }

 mysqli_close($con);

?>
