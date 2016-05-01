<?php
include_once "connection.php";

/**
 * Login del usuario.
 *
 * @param string $email
 * @param string $password
 * @return array
 */
function login_user($email, $password){
    $connection = db_connect();
    $login = array();    
    $query = "SELECT * FROM User WHERE email = '$email';";
    $result = mysqli_query($connection,$query);    
    if (!$result){
        $login[] = array(
            'idUser'    => '',
            'Name'      => '',
            'Username'  => '',
            'Email'     => '',
            'Password'  => 'Error en consulta -> '. mysqli_error($connection));
    }
    else{
        while ($row = mysqli_fetch_array($result)){
            if ($row['Password'] == $password){
                $login[] = array(
                    'idUser'    => $row['idUser'],
                    'Name'      => $row['Name'],
                    'Username'  => $row['Username'],
                    'Email'     => $row['Email'],
                    'Password'  => $row['Password']
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
function insert_user($name, $email, $password){
    $connection = db_connect();
    $response = '';
    $query = "INSERT INTO User(Name, Email, Password) "
            ."VALUES "
            ."('$name', '$email', '$password');";
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

function get_categories(){
    $connection = db_connect();
    $response = array();
    $query = "SELECT * FROM Category;";
    $result = mysqli_query($connection,$query);
    if (!$result){
        $response[] = array(
            'idCategory'    => '',
            'Name'  => 'Error en consulta -> '. mysqli_error($connection));
    }
    else{
        while ($row = mysqli_fetch_array($result)){
            $response[] = array(
                'idCategory'    => $row['idCategory'],
                'Name'          => $row['Name']
            );
        }
    }
    mysqli_close($connection);
    return $response;
}

function get_bloques(){
    $connection = db_connect();
    $response = array();
    $query = "SELECT * FROM Bloque;";
    $result = mysqli_query($connection,$query);
    if (!$result){
        $response[] = array(
            'idBloque'              => '',
            'Category_idCategory'   => '',
            'Title'                 => '',
            'Description'           => '',
            'Image'                 => 'Error en consulta -> '. mysqli_error($connection));
    }
    else{
        while ($row = mysqli_fetch_array($result)){
            $response[] = array(
                'idBloque'              => $row['idBloque'],
                'Category_idCategory'   => $row['Category_idCategory'],
                'Title'                 => $row['Title'],
                'Description'           => $row['Description'],
                'Image'                 => $row['Image']
            );
        }
    }
    mysqli_close($connection);
    return $response;
}