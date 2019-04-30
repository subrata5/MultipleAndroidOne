<?php

require 'init.php'; 


$type = false;
if(isset($_GET['item_type'])){
    $type = $_GET['item_type'];
 } 

//$type = $_GET['item_type'];
//$type = "ios";


	if($type=='android')
	{

		$sql = "select * from androidos"; 
		$response = array();
		$result = mysqli_query($con, $sql);
		while($row = mysqli_fetch_array($result))
		{

			array_push($response,array('name'=>$row['name'],'release_year'=>$row['release_year'],'image_path'=>$row['image_path']));
		}

		echo json_encode($response);
	}

	else if ($type=='ios') {
		
		$sql = "select * from ios"; 
		$response = array();
		$result = mysqli_query($con, $sql);
		while($row = mysqli_fetch_array($result))
		{

			array_push($response,array('name'=>$row['name'],'release_year'=>$row['release_year'],'image_path'=>$row['image_path']));
		}

		echo json_encode($response);
	}

	mysqli_close($con)

?>