<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

include_once "connection.php";

function login($email, $password){
    $connection = db_connect();
    $login = array();    
    $query = "SELECT * FROM user WHERE email = '$email';";
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
            if ($row['password'] == $password){
                $login[] = array(
                    'idUser'	=> $row['idUser'],
                    'username'	=> $row['username'],
                    'email'		=> $row['email'],
                    'password'	=> $row['password']
                );
            }
        }
    }
    mysqli_close($connection);
    return ($login);
}
