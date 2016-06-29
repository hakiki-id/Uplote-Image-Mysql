<?php 
	require_once ('koneksi.php'); 

	$page = $_GET['page']; 

	$limit = 6; 
	$start = ($page - 1 ) * $limit; 

	$query = "SELECT * from tb_kegiatan order by date_time DESC LIMIT $start,$limit";

	$result = mysqli_query($koneksi,$query); 

	$tempArray = array(); 
	while ($rows = mysqli_Fetch_assoc($result)) {
		$tempArray[]=$rows;
	}

	echo json_encode(array("kegiatan"=> $tempArray));
 ?>