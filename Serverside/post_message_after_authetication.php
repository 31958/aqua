<?php

// include our OAuth2 Server object
require_once __DIR__.'/server.php';

// Handle a request to a resource and authenticate the access token
if (!$server->verifyResourceRequest(OAuth2\Request::createFromGlobals())) {
    $server->getResponse()->send();
    die;
}
echo json_encode(array('success' => true, 'message' => 'You accessed my APIs!'));

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
