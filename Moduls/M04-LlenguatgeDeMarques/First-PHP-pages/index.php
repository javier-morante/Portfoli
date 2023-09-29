<?php
session_start();
?>
<!DOCTYPE html>
<html lang="es-ES">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>inicio</title>
    
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="styles.css">
    
</head>

<body>
    <?php
    if (isset($_GET["err"])) {
        echo '<script>alert("No session active")</script>';
    }
    ?>
    <h1 class="titles">Bienvenido</h1>
    <div class="userinteractions">
        <div class="accordion" id="accordionExample">
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        <h2>log in</h2>
                    </button>
                </h2>
                <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
                    <div class="bg-transparent accordion-body">
                        <form action="index.php" method="POST" class="bg-transparent">
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">User Name</label>
                                <input type="text" class="form-control" id="exampleInputEmail1" name="namenick" required>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" name="password" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                        <?php

                        if (!empty($_POST["namenick"]) && !empty($_POST["password"])) {

                            $useList = json_decode(file_get_contents("./users.json"), true);
                            if ($useList == null) {
                                echo '<p class="errortext">There are no users</p>';
                            } else {
                                if ($_SERVER["REQUEST_METHOD"] == "POST") {
                                    $gotUser = false;
                                    for ($i = 0; $i < sizeof($useList); $i++) {
                                        if ($useList[$i]["nickname"] == strtolower($_POST["namenick"]) && $useList[$i]["password"] == md5($_POST["password"])) {
                                            $_SESSION["nickname"] = $_POST["namenick"];
                                            $_SESSION['last_activity'] = time();
                                            $gotUser = true;
                                            header("Location: formulario.php");
                                            break;
                                        }
                                    }

                                    if (!$gotUser) {
                                        echo '<p class="errortext"> User or passsword are wrong <p>';
                                    }
                                }
                            }
                        }

                        ?>
                    </div>
                </div>
            </div>
            <div class="accordion-item">
                <h2 class="accordion-header">
                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        <h3>Create account</h3>
                    </button>
                </h2>
                <div id="collapseTwo" class="accordion-collapse collapse" data-bs-parent="#accordionExample">
                    <div class="accordion-body">
                        <form action="index.php" method="post">
                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">User Name</label>
                                <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="nickname" required>
                            </div>
                            <div class="mb-3">
                                <label for="exampleInputPassword1" class="form-label">Password</label>
                                <input type="password" class="form-control" id="exampleInputPassword1" name="password" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Submit</button>

                            <?php

                            if (!empty($_POST["nickname"]) && !empty($_POST["password"])) {
                                if ($_SERVER["REQUEST_METHOD"] == "POST") {

                                    $useList = json_decode(file_get_contents("./users.json"), true);
                                    if ($useList == null) {
                                        $useList = [];
                                    }

                                    $userdata = ["nickname" => strtolower($_POST["nickname"]), "password" => md5($_POST["password"])];

                                    array_push($useList, $userdata);

                                    file_put_contents("./users.json", json_encode($useList));
                                }
                            }

                            ?>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <footer>
        <h2>Made by Javier Morante Nuñez</h2>
    </footer>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>