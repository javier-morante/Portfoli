<?php
// session_start();

// $inactive = 30; // 5 minutos

// if (isset($_SESSION['last_activity']) && (time() - $_SESSION['last_activity'] > $inactive)) {
//     session_unset();
//     session_destroy();
// }

// $_SESSION['last_activity'] = time();

// if (!isset($_SESSION['active'])) {
//     session_regenerate_id(true);
//     $_SESSION['active'] = true;
// }

?>
<?php
session_start();

// Configurar el tiempo de vida de la sesión en segundos
$sessionTimeout = 30; // 5 minutos

// Verificar si la sesión está activa y ha pasado el tiempo de inactividad
if (isset($_SESSION['last_activity']) && (time() - $_SESSION['last_activity'] > $sessionTimeout)) {
    // Cerrar la sesión y destruir todas las variables de sesión
    session_unset();
    session_destroy();
    // Redirigir al usuario a la página de inicio de sesión o a otra página de tu elección
    header("Location: index.php?err=1");
    exit();
}

// Actualizar el tiempo de actividad de la sesión
$_SESSION['last_activity'] = time();

// Resto del código de tu aplicación
?>


