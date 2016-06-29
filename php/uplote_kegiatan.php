<?php 
	require_once ('koneksi.php'); 

	if ($_SERVER['REQUEST_METHOD']=='POST') {
			
			$judul = $_POST['judul']; 
			$keterangan = $_POST['keterangan'];
			$image = $_POST['image']; 
			
			$now = DateTime::createFromFormat('U.u',microtime(true)); 
			$id = $now->format('YmdHisu');

			$uplote_folder = "img_kegiatan"; 
			$path	="$uplote_folder/$id.jpeg"; 
			$web = "http://belajar.com/uplote/".$path;

			if (file_put_contents($path, base64_decode($image)) != false){
				$sql = "INSERT into tb_kegiatan (nama,keterangan,photo) VALUES ('$judul','$keterangan','$web')";

				$result = mysqli_query($koneksi,$sql);

				if ($result) {
					echo "berhasil";
				}else{
					mysqli_error($result);
				}

				exit;
			}else{
				echo "something wrong";
				exit;
			}

	}else{
		echo "not send";
	}


 ?>