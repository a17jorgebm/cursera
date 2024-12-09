<?php
header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405); // Method Not Allowed
    echo json_encode(['error' => 'Invalid HTTP method. Use POST.']);
    exit();
}

include_once('../../config/db.php');

$data = json_decode(file_get_contents('php://input'));

// Assign variables with null as the default value if the JSON field does not exist
$dni = $data->dni ?? null;
$nombre = $data->nombre ?? null;
$apel1 = $data->apel1 ?? null;
$apel2 = $data->apel2 ?? null;
$email = $data->email ?? null;
$tipo = $data->tipo ?? null;

if (isset(
    $dni,
    $nombre,
    $apel1,
    $email,
    $tipo
    )
) {
    $pdo = getDbConnection();

    try {
        $sql = "INSERT INTO usuario (dni, nombre, apel1, apel2, email, tipo) VALUES (?, ?, ?, ?, ?, ?)";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$dni, $nombre, $apel1, $apel2, $email, $tipo]);

        echo json_encode(['message' => 'User created successfully']);
    } catch (PDOException $e) {
        http_response_code(400);
        echo json_encode(['error' => $e->getMessage()]);
    }
} else {
    http_response_code(400);
    echo json_encode(['error' => 'Missing required fields']);
}

