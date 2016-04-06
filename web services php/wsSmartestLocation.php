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

$nameSpace = 'urn:LegoMindstorms';

$soapServer->configureWSDL($serviceName,$nameSpace);

$soapServer->wsdl->schemaTargetNamespace = $nameSpace;



$HTTP_RAW_POST_DATA = isset($HTTP_RAW_POST_DATA) ? $HTTP_RAW_POST_DATA : '';       
$soapServer->service($HTTP_RAW_POST_DATA);