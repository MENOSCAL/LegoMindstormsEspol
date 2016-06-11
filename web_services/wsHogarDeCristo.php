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
        'Image'                 => array('name' => 'Image', 'type' => 'xsd:string')));

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

////////////////////////////////////////////////
// Define the struct data type
$soapServer->wsdl->addComplexType(
//Name
    'structTaller',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idTaller'    => array('name' => 'idTaller', 'type' => 'xsd:string'),
        'Title'    => array('name' => 'Title', 'type' => 'xsd:string'),
        'Image'          => array('name' => 'Image', 'type' => 'xsd:string'))
);
// Define array
$soapServer->wsdl->addComplexType(
    'arrayTaller',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structTaller[]')),
    'tns:structTaller'
);
// Registering the method
$soapServer->register(
//Name
    'get_talleres',
    //Input:Parameter
    array(),
    //Output:Return
    array('return' => 'tns:arrayTaller'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_talleres',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Lista de talleres.');

////////////////////////////////////////////////
// Define the struct data type
$soapServer->wsdl->addComplexType(
//Name
    'structPregunta',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idPregunta'        => array('name' => 'idPregunta'     , 'type' => 'xsd:string'),
        'Taller_idTaller'   => array('name' => 'Taller_idTaller', 'type' => 'xsd:string'),
        'Name'              => array('name' => 'Name'           , 'type' => 'xsd:string'))
);
// Define array
$soapServer->wsdl->addComplexType(
    'arrayPregunta',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structPregunta[]')),
    'tns:structPregunta'
);
// Registering the method
$soapServer->register(
//Name
    'get_preguntas',
    //Input:Parameter
    array('idTaller' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayPregunta'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_preguntas',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Lista de preguntas.');

////////////////////////////////////////////////
// Define the struct data type
$soapServer->wsdl->addComplexType(
//Name
    'structRespuesta',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idRespuesta'           => array('name' => 'idRespuesta', 'type'            => 'xsd:string'),
        'Pregunta_idPregunta'   => array('name' => 'Pregunta_idPregunta', 'type'    => 'xsd:string'),
        'Name'                  => array('name' => 'Name', 'type'                   => 'xsd:string'),
        'Valido'                => array('name' => 'Valido', 'type'                 => 'xsd:string'))
);
// Define array
$soapServer->wsdl->addComplexType(
    'arrayRespuesta',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structRespuesta[]')),
    'tns:structRespuesta'
);
// Registering the method
$soapServer->register(
//Name
    'get_respuestas',
    //Input:Parameter
    array('idPregunta' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayRespuesta'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_respuestas',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    '');

////////////////////////////////////////////////
// Define the struct data type
$soapServer->wsdl->addComplexType(
//Name
    'structImagenTaller',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idImagenTaller'     => array('name' => 'idImagenTaller', 'type'            => 'xsd:string'),
        'Foto'               => array('name' => 'Foto', 'type'    => 'xsd:string'),
        'Taller_idTaller'    => array('name' => 'Taller_idTaller', 'type'                 => 'xsd:string'))
);
// Define array
$soapServer->wsdl->addComplexType(
    'arrayImagenTaller',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structImagenTaller[]')),
    'tns:structImagenTaller'
);
// Registering the method
$soapServer->register(
//Name
    'get_imagen_taller',
    //Input:Parameter
    array('idTaller' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayImagenTaller'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_imagen_taller',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    '');

////////////////////////////////////////////////
$soapServer->register(
//Name
    'insert_user_taller',
    //Input:Parameter
    array('idUser' => 'xsd:string', 'idTaller' => 'xsd:string', 'puntaje' => 'xsd:string'),
    //Output:Return
    array('return' => 'xsd:string'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#insert_user_taller',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    '');

////////////////////////////////////////////////
$soapServer->register(
//Name
    'validar_user',
    //Input:Parameter
    array('email' => 'xsd:string'),
    //Output:Return
    array('return' => 'xsd:string'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#validar_user',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    '');

////////////////////////////////////////////////
// Define the struct data type
$soapServer->wsdl->addComplexType(
//Name
    'structUserTaller',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'id_User_has_taller'    => array('name' => 'id_User_has_taller'     , 'type' => 'xsd:string'),
        'User_idUser'           => array('name' => 'User_idUser', 'type' => 'xsd:string'),
        'Taller_idTaller'       => array('name' => 'Taller_idTaller', 'type' => 'xsd:string'),
        'Puntaje'               => array('name' => 'Puntaje'           , 'type' => 'xsd:string'))
);
// Define array
$soapServer->wsdl->addComplexType(
    'arrayUserTaller',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structUserTaller[]')),
    'tns:structUserTaller'
);
// Registering the method
$soapServer->register(
//Name
    'get_taller_por_usuario_taller',
    //Input:Parameter
    array('idUser' => 'xsd:string','idTaller' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayUserTaller'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_taller_por_usuario_taller',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Lista de preguntas.');


// Registering the method
$soapServer->register(
//Name
    'get_taller_por_usuario',
    //Input:Parameter
    array('idUser' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayUserTaller'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_taller_por_usuario',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    'Lista de preguntas.');

////////////////////////////////////////////////
// Define the struct data type
$soapServer->wsdl->addComplexType(
//Name
    'structJoin',
    'complexType',
    'struct',
    'all',
    '',
    array(
        'idTaller'    		=> array('name' => 'idTaller'     , 'type' => 'xsd:string'),
        'Title'    		=> array('name' => 'Title'     , 'type' => 'xsd:string'),
        'Image'    		=> array('name' => 'Image'     , 'type' => 'xsd:string'),
        'id_User_has_taller'    => array('name' => 'id_User_has_taller'     , 'type' => 'xsd:string'),
        'User_idUser'           => array('name' => 'User_idUser', 'type' => 'xsd:string'),
        'Taller_idTaller'       => array('name' => 'Taller_idTaller', 'type' => 'xsd:string'),
        'Puntaje'               => array('name' => 'Puntaje'           , 'type' => 'xsd:string'))
);
// Define array
$soapServer->wsdl->addComplexType(
    'arrayJoin',
    'complexType',
    'array',
    '',
    'SOAP-ENC:Array',
    array(),
    array(array('ref' => 'SOAP-ENC:arrayType','wsdl:arrayType' => 'tns:structJoin[]')),
    'tns:structJoin'
);
// Registering the method
$soapServer->register(
//Name
    'get_talleres_by_user',
    //Input:Parameter
    array('idUser' => 'xsd:string'),
    //Output:Return
    array('return' => 'tns:arrayJoin'),
    //NameSpace
    'urn:HogarDeCristo',
    //SoapAction
    'urn:HogarDeCristo#get_talleres_by_user',
    //Style
    'rpc',
    //Use
    'encoded',
    //Documentation
    '');


$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';       
$soapServer->service($HTTP_RAW_POST_DATA);
