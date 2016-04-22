<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

include_once "connection.php";

/**
 * Login del usuario.
 *
 * @param string $email
 * @param string $password
 * @return array
 */
function login($email, $password){
    $connection = db_connect();
    $login = array();    
    $query = "SELECT * FROM User WHERE email = '$email';";
    $result = mysqli_query($connection,$query);    
    if (!$result){
        $login[] = array(
            'idUser'	=> '',
            'username'	=> '',
            'email'		=> '',
            'password'  => 'Error en consulta -> '. mysqli_error($connection));
    }
    else{
        while ($row = mysqli_fetch_array($result)){
            if ($row['Password'] == $password){
                $login[] = array(
                    'idUser'	=> $row['idUser'],
                    'username'	=> $row['Username'],
                    'email'		=> $row['Email'],
                    'password'	=> $row['Password']
                );
            }
        }
    }
    mysqli_close($connection);
    return $login;
}

/**
 * Método para insertar un nuevo usuario
 * a la base de datos.
 *
 * @param string $name
 * @param string $username
 * @param string $email
 * @param string $password
 * @return string
 */
function insert_user($name, $username, $email, $password){
    $connection = db_connect();
    $response = '';
    $query = "INSERT INTO User(Name, Username, Email, Password) "
            ."VALUES "
            ."('$name', '$username', '$email', '$password');";
    $result = mysqli_query($connection, $query);
    if (!$result){
        $response = 'ERROR : Insert fallido -> ' . mysqli_error($connection);
    }
    else{
        $response = '1';
    }
    mysqli_close($connection);
    return $response;
}


