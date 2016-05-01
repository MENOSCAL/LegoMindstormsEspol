<?php
require_once('lib/nusoap.php');
require_once('functions.php');
/**
 * Consultas en internet
 * @reference http://atobeto-eremita.blogspot.com/2010/06/como-devolver-arreglos-y-estructuras-de.html
 * @reference https://en.wikipedia.org/wiki/PHPDoc
 */
$soapServer = new soap_server();
$serviceName = 'HogarDeCristo';
$nameSpace = 'urn:HogarDeCristo';
$soapServer->configureWSDL($serviceName,$nameSpace);
$soapServer->wsdl->schemaTargetNamespace = $nameSpace;

$soapServer->wsdl->addComplexType(
    //Name
    'structLogin',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idUser'    => array('name' => 'idUser',    'type' => 'xsd:string'),
        'Name'      => array('name' => 'Name',      'type' => 'xsd:string'),
        'Username'  => array('name' => 'Username',  'type' => 'xsd:string'),
        'Email'     => array('name' => 'Email',     'type' => 'xsd:string'),
        'Password'  => array('name' => 'Password',  'type' => 'xsd:string')));

$soapServer->wsdl->addComplexType(
    'arrayLogin',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structLogin[]')),
    'tns:structLogin'
    );

$soapServer->register(
    //Name
    'login_user', 
    //Input:Parameter
    array('email' => 'xsd:string','password' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayLogin'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#login_user',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Login de usuario');

////////////////////////////////////////////////

$soapServer->register(
    //Name
    'insert_user',
    //Input:Parameter
    array('name' => 'xsd:string', 'email' => 'xsd:string', 'password' => 'xsd:string'),
    //Output:Return
    array('return' => 'xsd:string'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#insert_user',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Método para insertar un nuevo usuario a la base de datos.');

////////////////////////////////////////////////
// Define the struct data type
$soapServer->wsdl->addComplexType(
//Name
    'structCategory',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idCategory'    => array('name' => 'idCategory', 'type' => 'xsd:string'),
        'Name'          => array('name' => 'Name', 'type' => 'xsd:string')));

$soapServer->wsdl->addComplexType(
    'arrayCategory',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structCategory[]')),
    'tns:structCategory'
);
// Registering the method
$soapServer->register(
//Name
    'get_categories',
    //Input:Parameter
    array(),
    //Output:Return
    array('return' => 'tns:arrayCategory'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_categories',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Lista de categorias.');

////////////////////////////////////////////////

$soapServer->wsdl->addComplexType(
//Name
    'structBloque',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idBloque'              => array('name' => 'idBloque', 'type' => 'xsd:string'),
        'Category_idCategory'   => array('name' => 'Category_idCategory', 'type' => 'xsd:string'),
        'Title'                 => array('name' => 'Title', 'type' => 'xsd:string'),
        'Description'           => array('name' => 'Description', 'type' => 'xsd:string'),
        'Image'                 => array('name' => 'Image', 'type' => 'xsd:base64Binary')));

$soapServer->wsdl->addComplexType(
    'arrayBloque',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structBloque[]')),
    'tns:structBloque'
);

$soapServer->register(
//Name
    'get_bloques',
    //Input:Parameter
    array(),
    //Output:Return
    array('return' => 'tns:arrayBloque'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_bloques',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Lista de bloques.');


$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';       
$soapServer->service($HTTP_RAW_POST_DATA);