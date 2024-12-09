<?php
header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] !== 'DELETE') {
    http_response_code(405); // Method Not Allowed
    echo json_encode(['error' => 'Invalid HTTP method. Use DELETE.']);
    exit();
}

include_once('../../config/db.php');

$data = json_decode(file_get_contents('php://input'));

if (isset($data->id_usuario)) {
    $pdo = getDbConnection();

    try {
        $sql = "DELETE FROM usuario WHERE id_usuario = ?";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([$data->id_usuario]);
        $numberDeleted = $stmt->rowCount();

        if($numberDeleted>0){
            echo json_encode(['message' => 'User deleted successfully']);
        }else{
            echo json_encode(['message' => 'User did not exist']);
        }

    } catch (PDOException $e) {
        http_response_code(400);
        echo json_encode(['error' => $e->getMessage()]);
    }
} else {
    http_response_code(400);
    echo json_encode(['error' => 'Missing required fields']);
}