<?php
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * https://www.binpress.com/tutorial/using-php-with-mysql-the-right-way/17
 */

define('DB_SERVER','108.167.133.34');
define('DB_NAME','connie_lego');
define('DB_USER','connie_usrlego');
define('DB_PASS','!HPhdbnXU6iC');

function db_connect() {
    // Define connection as a static variable, to avoid connecting more than once 
    static $connection;
    // Try and connect to the database, if a connection has not been established yet
    if(!isset($connection)) {
        $connection = mysqli_connect(DB_SERVER, DB_USER, DB_PASS, DB_NAME);
    }
    // If connection was not successful, handle the error
    if($connection === false) {
        // Handle error - notify administrator, log to a file, show an error screen, etc.
        return mysqli_connect_error(); 
    }

    return $connection;
}











