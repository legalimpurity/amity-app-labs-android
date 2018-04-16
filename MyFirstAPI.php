<?php
header('Content-Type: application/json');
$rootTag = array();
// array_push($rootTag,"Sahil Bhutani");
// array_push($rootTag,"Animesh Verma");
// array_push($rootTag,"Rajat Khanna");

$conn = new mysqli("localhost","root","root","app_lab_test_database");
if($conn->connect_error)
{
  die("Connection failed");
}

$myquery = "SELECT * from app_lab_test_table";
$myQueryResult = $conn->query($myquery);

if($myQueryResult->num_rows > 0)
{
  while($row = $myQueryResult->fetch_assoc())
  {
    $elemtag= array();
    $elemtag["first_name"]=$row["firstName"];
    $elemtag["last_name"]=$row["lastName"];
    $elemtag["age"]=$row["age"];
    array_push($rootTag,$elemtag);
  }
}
$conn -> close();
echo json_encode($rootTag);
?>
