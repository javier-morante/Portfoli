<?php
include "./allConfFile.php";
?>
<!DOCTYPE html>
<html lang="es-ES">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Form</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="./styles.css">
</head>

<body onload="resatradio()">
    <header>
        <?php
        if (!isset($_SESSION["nickname"])) {

            header("Location: index.php?err=1");
        } else {
            echo '<h1 class="titles" >Bienvenido ' . $_SESSION["nickname"] . '</h1>';
        }
        ?>
        <h2 class="subtitle">porfavor complete el formlario</h2>
    </header>
    <div class="userinterac">
        <form action="datashow.php" method="post">
            <label for="favouriteM" class="lbl">Anime o Manga favorito</label>
            <input class="form-control" type="text" name="favoanime" required>

            <div class="check required">
                <label for="genres[]" class="lbl">Que generos son tus preferidos</label>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="genres[]" value="Drama">
                    <label class="form-check-label " for="flexCheckDefault">
                        Drama
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="genres[]" value="Isekai">
                    <label class="form-check-label" for="flexCheckDefault">
                        Isekai
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="genres[]" value="Aventura">
                    <label class="form-check-label" for="flexCheckDefault">
                        Aventura
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="genres[]" value="Romace">
                    <label class="form-check-label" for="flexCheckDefault">
                        Romace
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" id="flexCheckDefault" name="genres[]" value="Acción">
                    <label class="form-check-label" for="flexCheckDefault">
                        Acción
                    </label>
                    
                </div>
                <?php

                if (isset($_GET["err"])) {
                    if ($_GET["err"] == 1 or $_GET["err"] == 0) {
                        echo '<p class="errortext">One field is required</p>';
                    }
                }

                ?>

            </div>
            <label for="selFp" class="lbl">De estos animes populares cual te gusta mas</label>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="fanime" id="flexRadioDefault1" value="One peace">
                <label class="form-check-label" for="flexRadioDefault1">
                    One peace
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="fanime" id="flexRadioDefault1" value="Naruto">
                <label class="form-check-label" for="flexRadioDefault1">
                    Naruto
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="fanime" id="flexRadioDefault1" value="Kimetsu">
                <label class="form-check-label" for="flexRadioDefault1">
                    Kimetsu no yaiba
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="fanime" id="flexRadioDefault1" value="Shingeki">
                <label class="form-check-label" for="flexRadioDefault1">
                    Shingeki no kyojin
                </label>
            </div>

            <?php

            if (isset($_GET["err"])) {
                if ($_GET["err"] == 2 or $_GET["err"] == 0) {
                    echo '<p class="errortext">this field is required</p>';
                }
            }

            ?>

            <label for="maweb" class="lbl">Que pagina de manga prefieres</label>
            <select class="form-select" aria-label="Default select example" name="maweb" required>
                <option value="yugen">YugenMangas</option>
                <option value="olympus">Olympus Scan</option>
                <option value="lector">LectorManga</option>
            </select>
            <label for="anweb" class="lbl">Que pagina de anime prefieres</label>
            <select class="form-select" aria-label="Default select example" name="anweb" required>
                <option value="flv">AnimeFLV</option>
                <option value="Crun">Crunchyroll</option>
                <option value="fenix">AnimeFenix</option>
            </select>
            <input type="submit" value="ENVIAR" class="btn btn-primary lbl">

        </form>
    </div>
    <footer>
        <h2>Made by Javier Morante Nuñez</h2>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>