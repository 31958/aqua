<?php
include 'config.php' ;

$con = mysqli_connect($host,$user,$pass,$db);

$ID = $_POST['ID'];
$author_ID = $_POST['author_ID'];
$postContent = $_POST['postContent'];
$dateTime = $_POST['dateTime'];

$sql = "INSERT INTO Posts (ID, author_ID, postContent, dateTime) VALUES ('$ID','$author_ID','$postContent','$dateTime')";

if(mysqli_query($con,$sql)){

    echo 'Data Submit Successfully';

}
else{

    echo 'Try Again';

}
mysqli_close($con);
?>
