<?php

$host = getenv('POSTGRES_HOST') ?: 'db';
$dbname = getenv('POSTGRES_DB') ?: 'cursera';
$user = getenv('POSTGRES_USER') ?: 'postgres';
$password = getenv('POSTGRES_PASSWORD') ?: 'example';

function getDbConnection() {
    global $host, $dbname, $user, $password;
    try {
        $dsn = "pgsql:host=$host;dbname=$dbname";
        $pdo = new PDO($dsn, $user, $password);
        $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

        return $pdo;
    } catch (PDOException $e) {
        die("Connection failed: " . $e->getMessage());
    }
}