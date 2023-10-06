<?php
include "./allConfFile.php";
?>
<!DOCTYPE html>
<html lang="es-ES">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="styles.css">
    <title>Data form show</title>
</head>

<body>
    <header>
        <a href="aout.php" class="txt">
            <div class="btt-out">
                log out
            </div>
        </a>

        <?php
        if (!isset($_SESSION["nickname"])) {

            header("Location: index.php?err=1");
        } else {
            echo '<h1 class="titles">Bienvenido ' . $_SESSION["nickname"] . '</h1>';
        }
        echo "</header>";
        ?>
        <div class="content">

            <?php
            if ($_SERVER["REQUEST_METHOD"] == "POST") {
                echo '<p class="txt-p fa">Tu manga/anime favorito es <span class="txt-cb">' . $_POST["favoanime"] . '</span> i es muy guay</p>';

                echo '<div class="pr"><p class="txt-p txt-c">Tus generos preferidos:<p>';
                echo '<ul>';

                if (empty($_POST["genres"])) {

                    header("Location: formulario.php?err=1");
                }

                foreach ($_POST["genres"] as $genre) {
                    echo '<li class="txt-l">' . $genre . '</li>';
                }

                if (empty($_POST["fanime"])) {
                    header("Location: formulario.php?err=2");
                }

                if (empty($_POST["genres"]) and empty($_POST["fanime"])) {

                    header("Location: formulario.php?err=0");
                }

                echo '</ul></div>';
                echo '<div class="content-img ani">';
                switch ($_POST["fanime"]) {
                    case 'One peace':
                        echo '<img class="img s-100p" src="https://i.pinimg.com/550x/50/22/2e/50222e533fac64e629d4462b113423e2.jpg" alt="One peace" >';
                        break;
                    case 'Naruto':
                        echo '<img class="img d-tr s-150p" src="https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Naruto_logo.svg/1200px-Naruto_logo.svg.png" alt="Naruto">';
                        break;
                    case 'Kimetsu':
                        echo '<img class="img s-150p" src="https://e00-marca.uecdn.es/assets/multimedia/imagenes/2023/02/24/16772569754891.jpg" alt="Kimetsu no yaiba">';
                        break;
                    case 'Shingeki':
                        echo '<img class="img " src="https://depor.com/resizer/f5lcG_SPYS-M-CdJCkzRBRB4ncI=/640x0/smart/filters:format(jpeg):quality(75)/cloudfront-us-east-1.images.arcpublishing.com/elcomercio/7GIIGO2GA5FJBMKXN5XT4KA3NU.jpg" alt="Shingeki no kyoyin">';
                        break;
                }
                echo '</div>';
                echo '<div class="content-img mangav">';
                switch ($_POST["maweb"]) {
                    case 'yugen':
                        echo '<img class="img" src="img/yugen.png" alt="YugenMangas">';
                        break;
                    case 'olympus':
                        echo '<img class="img d-tr" src="img/olym.png" alt="OlympusScan">';
                        break;

                    case 'lector':
                        echo '<img class="img d-tr" src="img/Lectorm.png" alt="Lectormangas">';
                        break;
                }
                echo '</div>';
                echo '<div class="content-img aniv">';
                switch ($_POST["anweb"]) {
                    case 'flv':
                        echo '<img class="img d-tr" src="img/animeflv.jpg" alt="Animeflv">';
                        break;
                    case 'Crun':
                        echo '<img class="img d-tr" src="img/crun.jpg" alt="Crunchyroll">';
                        break;
                    case 'fenix':
                        echo '<img class="img d-tr" src="data:image/png;base64,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" alt="AnimeFenix">';
                        break;
                }
                echo '</div>';
            }
            ?>
        </div>
        <footer>
            <h2>Made by Javier Morante Nuñez</h2>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>

</html>