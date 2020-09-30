<?php
include 'config.php' ;

$con = mysqli_connect($host,$user,$pass,$db);

$id = $_POST['id'];
$author_id = $_POST['author_id'];
$post_text = $_POST['post_text'];
$post_image = $_POST['post_image'];
$dateTime = $_POST['dateTime'];

$sql = "INSERT INTO Posts (id, author_iD, post_text, post_image, dateTime) VALUES ('$id','$author_id','$post_text','$post_image','$dateTime')";

if(mysqli_query($con,$sql)){

    echo 'Data Submit Successfully';

}
else{

    echo 'Try Again';

}
mysqli_close($con);
?>
