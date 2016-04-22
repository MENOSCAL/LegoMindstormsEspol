<?php

require_once('lib/nusoap.php');
require_once('functions.php');

/**
 * Consultas en internet
 * @reference http://atobeto-eremita.blogspot.com/2010/06/como-devolver-arreglos-y-estructuras-de.html
 * @reference https://en.wikipedia.org/wiki/PHPDoc
 */

$soapServer = new soap_server();
$serviceName = 'LegoMindstormsEspol';
$nameSpace = 'urn:LegoMindstormsEspol';
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
        'username'  => array('name' => 'username',  'type' => 'xsd:string'),
        'email'     => array('name' => 'email',     'type' => 'xsd:string'),
        'password'  => array('name' => 'password',  'type' => 'xsd:string')));

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
    'login',
    //Input:Parameter
    array('email' => 'xsd:string','password' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayLogin'),
    //NameSpace
    'urn:LegoMindstormsEspol',
    //SoapAction
    'urn:LegoMindstormsEspol#login',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Login de usuario');

///////////////////////////////////////////

$soapServer->register(
    //Name
    'insert_user',
    //Input:Parameter
    array('name' => 'xsd:string', 'username' => 'xsd:string', 'email' => 'xsd:string', 'password' => 'xsd:string'),
    //Output:Return
    array('return' => 'xsd:string'),
    //NameSpace
    'urn:LegoMindstormsEspol',
    //SoapAction
    'urn:LegoMindstormsEspol#insert_user',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Método para insertar un nuevo usuario a la base de datos.');


$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';       
$soapServer->service($HTTP_RAW_POST_DATA);