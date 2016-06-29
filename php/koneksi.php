<?php 
	define('DB_HOSTNAME', 'localhost');
	define('DB_USER', 'root');
	define('DB_PASS', '');
	define('DB_DATABASE', 'db_kkn');


	$koneksi = mysqli_connect(DB_HOSTNAME,DB_USER,DB_PASS,DB_DATABASE) or die(mysqli_error());
	
	
	
	
 ?>