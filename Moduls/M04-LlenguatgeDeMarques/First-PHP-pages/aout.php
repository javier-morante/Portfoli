<?php
include "./allConfFile.php";
?>
<!DOCTYPE html>
<html lang="es-ES">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Document</title>
</head>
<body>
    <?php
    if (!isset($_SESSION["nickname"])) {

        header("Location: index.php?err=1");
    } else {
        echo '<h1 class="titles"> Adios ' . $_SESSION["nickname"] . ' que le valla bien</h1>';

        

        session_unset();
        session_destroy();
        header("Refresh: 3;url=index.php");

    }
    ?>
    <div class="content-aout">
        <img class="cat" src="img/catbye.gif" alt="Adios" >
    </div>
</body>
</html>