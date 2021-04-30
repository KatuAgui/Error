-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: sistemaclinicooficial
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo` (
  `idCargo` int NOT NULL AUTO_INCREMENT,
  `cargo` varchar(60) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo`
--

LOCK TABLES `cargo` WRITE;
/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` VALUES (18,'Doctor');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `citamedica`
--

DROP TABLE IF EXISTS `citamedica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `citamedica` (
  `idCitaMedica` int NOT NULL AUTO_INCREMENT,
  `citaMedicaObservaciones` text COLLATE utf8_spanish_ci NOT NULL,
  `citaMedicaFecha` date NOT NULL,
  `citaMedicaHoraInicio` time NOT NULL,
  `citaMedicaHoraFinal` time NOT NULL,
  `citaMedicaNumeroIdentidad` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idCitaMedica`),
  KEY `fk_cita_medica_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_cita_medica_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `citamedica`
--

LOCK TABLES `citamedica` WRITE;
/*!40000 ALTER TABLE `citamedica` DISABLE KEYS */;
INSERT INTO `citamedica` VALUES (8,'ninguna','2021-04-25','21:00:00','22:00:00','0801-2000-00733',1);
/*!40000 ALTER TABLE `citamedica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consultamedica`
--

DROP TABLE IF EXISTS `consultamedica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `consultamedica` (
  `idConsultasMedicas` int NOT NULL AUTO_INCREMENT,
  `consultaMedicaFechaIngreso` date NOT NULL,
  `consultaMedicaObservaciones` text COLLATE utf8_spanish_ci NOT NULL,
  `consultaMedicaRecetasMedicas` text COLLATE utf8_spanish_ci NOT NULL,
  `consultaMedicaNumeroIdentidad` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`idConsultasMedicas`),
  KEY `fk_consulta_medica_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_consulta_medica_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `consultamedica`
--

LOCK TABLES `consultamedica` WRITE;
/*!40000 ALTER TABLE `consultamedica` DISABLE KEYS */;
/*!40000 ALTER TABLE `consultamedica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `idEmpleado` int NOT NULL AUTO_INCREMENT,
  `empleadoPrimerNombre` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `empleadoSegundoNombre` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `empleadoPrimerApellido` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `empleadoSegundoApellido` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `empleadoDireccion` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
  `empleadoTelefonoCelular` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `idCargo` int NOT NULL,
  `idEstado` int NOT NULL,
  PRIMARY KEY (`idEmpleado`),
  KEY `fk_empleados_cargo1_idx` (`idCargo`),
  KEY `fk_empleado_estado_empleado1_idx` (`idEstado`),
  CONSTRAINT `fk_empleado_estado_empleado1` FOREIGN KEY (`idEstado`) REFERENCES `estadoempleado` (`idEstado`),
  CONSTRAINT `fk_empleados_cargo1` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`idCargo`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'Juan','Carlos','Martinez','Mendoza','Choluteca','9898-0076',18,1);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estadoempleado`
--

DROP TABLE IF EXISTS `estadoempleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estadoempleado` (
  `idEstado` int NOT NULL AUTO_INCREMENT,
  `estado` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idEstado`),
  UNIQUE KEY `estado_UNIQUE` (`estado`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estadoempleado`
--

LOCK TABLES `estadoempleado` WRITE;
/*!40000 ALTER TABLE `estadoempleado` DISABLE KEYS */;
INSERT INTO `estadoempleado` VALUES (1,'Activo'),(2,'Inactivo');
/*!40000 ALTER TABLE `estadoempleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historiaclinica`
--

DROP TABLE IF EXISTS `historiaclinica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historiaclinica` (
  `numeroIdentidad` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `historiaClinicaFechaCreacion` date NOT NULL,
  `historiaClinicaCardiobasculares` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaPulmonares` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaDigestivo` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaDiabetes` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaRenales` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaQuirurgicos` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaAlergicos` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaTransfusiones` varchar(8) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaMedicamentos` varchar(400) COLLATE utf8_spanish_ci DEFAULT NULL,
  `historiaClinicaObservaciones` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `idUsuario` int NOT NULL,
  PRIMARY KEY (`numeroIdentidad`),
  KEY `fk_historia_clinica_usuario1_idx` (`idUsuario`),
  CONSTRAINT `fk_historia_clinica_usuario1` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`),
  CONSTRAINT `fk_historiaClinica_paciente1` FOREIGN KEY (`numeroIdentidad`) REFERENCES `paciente` (`numeroIdentidad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historiaclinica`
--

LOCK TABLES `historiaclinica` WRITE;
/*!40000 ALTER TABLE `historiaclinica` DISABLE KEYS */;
INSERT INTO `historiaclinica` VALUES ('0801-2000-00733','2021-10-30','true','false','false','false','false','false','false','false','ninguno','ninguna',1);
/*!40000 ALTER TABLE `historiaclinica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paciente` (
  `numeroIdentidad` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `pacientePrimerNombre` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `pacienteSegundoNombre` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pacientePrimerApellido` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `pacienteSegundoApellido` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pacienteAntecedentesFamiliares` varchar(100) COLLATE utf8_spanish_ci DEFAULT NULL,
  `pacienteFechaNacimiento` date NOT NULL,
  `pacienteTipoSangre` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `pacienteDireccion` varchar(75) COLLATE utf8_spanish_ci NOT NULL,
  `pacienteTelefonoCelular` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `pacientePeso` decimal(7,2) NOT NULL,
  `pacienteEstatura` decimal(5,2) NOT NULL,
  `pacienteCiudadProcedencia` varchar(65) COLLATE utf8_spanish_ci NOT NULL,
  `pacienteEmail` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL,
  `idSexo` int NOT NULL,
  PRIMARY KEY (`numeroIdentidad`),
  KEY `fk_pacientes_sexo1_idx` (`idSexo`),
  CONSTRAINT `fk_pacientes_sexo1` FOREIGN KEY (`idSexo`) REFERENCES `sexo` (`idSexo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES ('0801-2000-00733','Katia','Lisbeth','Aguilar','Garcia','ninguno','1999-12-30','A+','Choluteca','9586-3008',1.50,1.65,'Tegucigapa','katy@gmail.com',2);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sexo`
--

DROP TABLE IF EXISTS `sexo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sexo` (
  `idSexo` int NOT NULL AUTO_INCREMENT,
  `sexo` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  PRIMARY KEY (`idSexo`),
  UNIQUE KEY `sexo_UNIQUE` (`sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sexo`
--

LOCK TABLES `sexo` WRITE;
/*!40000 ALTER TABLE `sexo` DISABLE KEYS */;
INSERT INTO `sexo` VALUES (2,'Femenino'),(1,'Masculino');
/*!40000 ALTER TABLE `sexo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `usuarioNombreUsuario` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `usuarioPassword` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `usuarioFechaCreacion` date NOT NULL,
  `usuarioFechaBaja` date DEFAULT NULL,
  `idEstado` int NOT NULL,
  `idEmpleado` int NOT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_usuario_empleados1_idx` (`idEmpleado`),
  KEY `fk_usuario_estado_empleado1_idx` (`idEstado`),
  CONSTRAINT `fk_usuario_empleados1` FOREIGN KEY (`idEmpleado`) REFERENCES `empleado` (`idEmpleado`),
  CONSTRAINT `fk_usuario_estado_empleado1` FOREIGN KEY (`idEstado`) REFERENCES `estadoempleado` (`idEstado`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'juanCarlos','1234','2020-10-12','2022-10-12',1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sistemaclinicooficial'
--
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizarCargo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarCargo`(IN _cargo VARCHAR(10),
											IN _idCargo int )
BEGIN
	UPDATE cargo 
		SET cargo = _cargo
	WHERE idCargo = _idCargo;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizarCitaMedica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarCitaMedica`(IN _citaMedicaObservaciones text(10000), 
									      IN _citaMedicaFecha date,
                                          IN _citaMedicaHoraInicio time,
                                          IN _citaMedicaHoraFinal time,									
                                          IN _citaMedicaNumeroIdentidad varchar(15),
                                          IN _idUsuario int)
BEGIN
	UPDATE citaMedica 
		SET citaMedicaObservaciones = _citaMedicaObservaciones,
			citaMedicaFecha = _citaMedicaFecha,
            citaMedicaHoraInicio = _citaMedicaHoraInicio,
            citaMedicaHoraFinal = _citaMedicaHoraFinal ,
            idUsuario = _idUsuario
	WHERE citaMedicaNumeroIdentidad = _citaMedicaNumeroIdentidad;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizarConsultaMedica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarConsultaMedica`(IN _fechaIngreso DATE, IN _observaciones TEXT(10000), IN _recetaMedica TEXT(500),
											 IN _numeroIdentidad VARCHAR(15),IN _idUsuario INT)
BEGIN
	UPDATE consultaMedica
		SET consultaMedicaFechaIngreso = _fechaIngreso,
			consultaMedicaObservaciones = _observaciones,
			consultaMedicaRecetasMedicas = _recetaMedica,
			consultaMedicaNumeroIdentidad = _numeroIdentidad,
            idUsuario = _idUsuario
			WHERE consultaMedicaNumeroIdentidad = _numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizarEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarEmpleado`(IN _idEmpleado int, 
									   IN _empleadoPrimerNombre varchar(15),
									   IN _empleadoSegundoNombre varchar(15),
                                       IN _empleadoPrimerApellido varchar(15),
                                       IN _empleadoSegundoApellido varchar(15),
                                       IN _empleadoDireccion varchar(45),
                                       IN _empleadoTelefonoCelular char(9),
                                       IN _idCargo int,
                                       IN _idEstado int
									)
BEGIN
	UPDATE empleado
		SET	empleadoPrimerNombre = _empleadoPrimerNombre,
			empleadoSegundoNombre = _empleadoSegundoNombre,
            empleadoPrimerApellido = _empleadoPrimerApellido,
			empleadoSegundoApellido = _empleadoSegundoApellido,
            empleadoDireccion = _empleadoDireccion,
            empleadoTelefonoCelular = _empleadoTelefonoCelular,
            idCargo = _idCargo,
            idEstado = _idEstado
	WHERE idEmpleado = _idEmpleado;		
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizarHistoriaClinica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarHistoriaClinica`(	IN _fechaCreacion date,
												IN _cardiobasculares varchar(8),
                                                IN _pulmonares varchar(8),
                                                IN _digestivo varchar(8),
                                                IN _diabetes varchar(8),
                                                IN _renales varchar(8),
                                                IN _quirurgicos varchar(8),
                                                IN _alergicos varchar(8),
                                                IN _transfusiones varchar(8),
                                                IN _medicamentos varchar(8),
                                                IN _observaciones varchar(500),
                                                IN _idUsuario int,
                                                IN _numeroIdentidad varchar(15))
BEGIN
	UPDATE historiaClinica 
   SET 	fechaCreacion = _fechaCreacion, 
		cardiobasculares = _cardiobasculares, 
        pulmonares = _pulmonares, 
        digestivo = _digestivo, 
        diavetes = _diabetes, 
        renales = _renales, 
        quirurgicos = _quirurgicos, 
        transfusiones = _transfusiones, 
        medicamentos = _medicamentos, 
        observaciones = _observaciones, 
        idUsuario = _idUsuario
	WHERE numeroIdentidad = _numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizarPaciente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarPaciente`(IN _numeroIdentidad varchar(15),IN _pacientePrimerNombre varchar(15), 
									IN _pacienteSegundoNombre varchar(15), IN _pacientePrimerApellido varchar(15),
                                    IN _pacienteSegundoApellido varchar (15), IN _pacienteAntecedentesFamiliares varchar(100),
                                    IN _pacienteFechaNacimiento date, IN _pacienteTipoSangre varchar (10), IN _pacienteDireccion varchar (75),
                                    IN _pacienteTelefonoCelular varchar(9), IN _pacienteEPeso decimal(7,2), IN _pacienteEstatura decimal(5,2),
                                    IN _pacienteCiudadProcedencia varchar(65), IN _pacienteEmail varchar(45),IN _idSexo int)
BEGIN
	UPDATE paciente
		SET numeroIdentidad = _numeroIdentidad,
			pacientePrimerNombre = _pacientePrimerNombre,
            pacienteSegundoNombre = _pacienteSegundoNombre,
            pacientePrimerApellido = _pacientePrimerApellido,
            pacienteSegundoApellido = _pacienteSegundoApellido,
            pacienteAntecedentesFamiliares = _pacienteAntecedentesFamiliares,
            pacienteFechaNacimiento = _pacienteFechaNacimiento,
            pacienteTipoSangre = _pacienteTipoSangre,
            pacienteDireccion = _pacienteDireccion,
			pacienteTelefonoCelular = _pacienteTelefonoCelular,
            pacientePeso = _pacientePeso,
            pacienteEstatura = _pacienteEstatura,
            pacienteCiudadProcedencia = _pacienteCiudadProcedencia,
            pacienteEmail = _pacienteEmail,
            idSexo = _idSexo
			WHERE numeroIdentidad = _numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_actualizarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_actualizarUsuario`(IN _idUsuario int, 
									   IN _usuarioNombreUsuario varchar(10),
									   IN _usuarioPassword varchar(9),
                                       IN _usuarioFechaCreacion date,
                                       IN _usuarioFechaBaja date,
									   IN _idEstado int,
                                       IN _idEmpleado int
									  )
BEGIN
	UPDATE usuario
		SET	usuarioNombreUsuario = _usuarioNombreUsuario,
			usuarioPassword = _usuarioPassword,
			usuarioFechaCreacion = _usuarioFechaCreacion,
            usuarioFechaBaja = _usuarioFechaBaja,
            idEstado = _idEstado,
            idEmpleado = _idEmpleado
	WHERE idUsuario = _idUsuario;		
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_autoIncrementarCargoId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_autoIncrementarCargoId`()
BEGIN
	SELECT MAX(LAST_INSERT_ID(idCargo)) + 1 AS idCargo
    FROM cargo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_autoIncrementarCitaMedicaId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_autoIncrementarCitaMedicaId`()
BEGIN 
	SELECT MAX(LAST_INSERT_ID(idCitaMedica)) + 1 AS idCitaMedica
    FROM citaMedica;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarCargo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarCargo`(IN _idCargo int )
BEGIN
	DELETE
    FROM cargo
    WHERE IdCargo = _idCargo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarCitaMedica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarCitaMedica`(IN _citaMedicaNumeroIdentidad varchar(15))
BEGIN
	DELETE
    FROM citamedica
    WHERE citaMedicaNumeroIdentidad = _citaMedicaNumeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarConsulta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarConsulta`(IN _numeroIdentidad VARCHAR(15))
BEGIN
	DELETE
    FROM consultaMedica
    WHERE numeroIdentidad = _numeroIdentidad;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarConsultaMedica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarConsultaMedica`(IN _numeroIdentidad VARCHAR(15))
BEGIN
	DELETE
    FROM consultaMedica
    WHERE consultaMedicaNumeroIdentidad = _numeroIdentidad;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarEmpleado`(IN _idEmpleado int)
BEGIN
	DELETE
    FROM empleado
    WHERE idEmpleado = _idEmpleado;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarHistoriaClinica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarHistoriaClinica`(IN _numeroIdentidad VARCHAR(15))
BEGIN
	DELETE 
    FROM historiaClinica
    WHERE numeroIdentidad = _numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarPaciente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarPaciente`(IN _numeroIdentidad VARCHAR(15))
BEGIN
	DELETE
    FROM paciente
    WHERE numeroIdentidad = _numeroIdentidad;    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_eliminarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminarUsuario`(IN _idUsuario int)
BEGIN
	DELETE
    FROM usuario
    WHERE idUsuario = _idUsuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertarCargo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarCargo`( IN _cargo varchar(15)
									)
BEGIN
		INSERT INTO cargo (cargo)
        VALUES (_cargo);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertarCitaMedica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarCitaMedica`(IN _citaMedicaObservaciones text(10000), 
									      IN _citaMedicaFecha date,
                                          IN _citaMedicaHoraInicio time,
                                          IN _citaMedicaHoraFinal time,									
                                          IN _citaMedicaNumeroIdentidad varchar(15),
                                          IN _idUsuario int)
BEGIN
	INSERT INTO citamedica (citaMedicaObservaciones, citaMedicaFecha, citaMedicaHoraInicio, citaMedicaHoraFinal,citaMedicaNumeroIdentidad,idUsuario )
    VALUES (_citaMedicaObservaciones, _citaMedicaFecha, _citaMedicaHoraInicio, _citaMedicaHoraFinal, _citaMedicaNumeroIdentidad, _idUsuario);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertarConsultaMedica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarConsultaMedica`(IN _fechaIngreso DATE, IN _observaciones TEXT(10000), IN _recetaMedica TEXT(500),
											IN _numeroIdentidad VARCHAR(15),IN _idUsuario INT)
BEGIN
	INSERT INTO consultaMedica(consultaMedicaFechaIngreso,consultaMedicaObservaciones,consultaMedicaRecetasMedicas,consultaMedicaNumeroIdentidad,idUsuarioo)
    VALUES (_fechaIngreso,_observaciones,_recetaMedica,_numeroIdentidad,_idUsuario);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertarEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarEmpleado`( IN _empleadoPrimerNombre varchar(15),
									  IN _empleadoSegundoNombre varchar(15),
                                      IN _empleadoPrimerApellido varchar(15),
                                      IN _empleadoSegundoApellido varchar(15),
                                      IN _empleadoDireccion varchar(45),
                                      IN _empleadoTelefonoCelular char(9),
                                      IN _idCargo int,
                                      IN _idEstado int
									)
BEGIN
		INSERT INTO empleado(empleadoPrimerNombre, empleadoSegundoNombre, empleadoPrimerApellido, empleadoSegundoApellido, empleadoDireccion, empleadoTelefonoCelular, idCargo, idEstado)
        VALUES (_empleadoPrimerNombre, _empleadoSegundoNombre, _empleadoPrimerApellido, _empleadoSegundoApellido, _empleadoDireccion, _empleadoTelefonoCelular, _idCargo, _idEstado);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertarHistoriaClinica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarHistoriaClinica`(	IN _fechaCreacion date,
												IN _cardiobasculares varchar(8),
                                                IN _pulmonares varchar(8),
                                                IN _digestivo varchar(8),
                                                IN _diabetes varchar(8),
                                                IN _renales varchar(8),
                                                IN _quirurgicos varchar(8),
                                                IN _alergicos varchar(8),
                                                IN _transfusiones varchar(8),
                                                IN _medicamentos varchar(8),
                                                IN _observaciones varchar(500),
                                                IN _idUsuario int                                            
											)
BEGIN
	INSERT INTO historiaClinica 
    VALUES (_fechaCreacion, _cardiobasculares, _pulmonares, _digestivo, _diabetes, _renales, _quirurgicos, _transfusiones, _medicamentos, _observaciones, _idUsuario  );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertarPaciente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarPaciente`(IN _numeroIdentidad varchar(15),IN _pacientePrimerNombre varchar(15), 
									IN _pacienteSegundoNombre varchar(15), IN _pacientePrimerApellido varchar(15),
                                    IN _pacienteSegundoApellido varchar (15), IN _pacienteAntecedentesFamiliares varchar(100),
                                    IN _pacienteFechaNacimiento date, IN _pacienteTipoSangre varchar (10), IN _pacienteDireccion varchar (75),
                                    IN _pacienteTelefonoCelular varchar(9), IN _pacienteEPeso decimal(7,2), IN _pacienteEstatura decimal(5,2),
                                    IN _pacienteCiudadProcedencia varchar(65), IN _pacienteEmail varchar(45),IN _idSexo int)
BEGIN
	INSERT INTO paciente(numeroIdentidad,pacientePrimerNombre,pacienteSegundoNombre,pacientePrimerApellido,pacienteSegundoApellido,pacienteFechaNacimiento,
						pacienteAntecedentesFamiliares,pacienteTelefonoCelular,pacientePeso,pacienteEstatura,pacienteCiudadProcedencia,pacienteEmail,idSexo)
    VALUES (_numeroIdentida, _pacientePrimerNombre, _pacienteSegundoNombre, _pacientePrimerApellido, _pacienteSegundoApellido,
			_pacienteAntecedentesFamiliares, _pacienteFechaNacimiento, _pacienteTipoSangre, _pacienteDireccion, _pacienteTelefonoCelular,
            _pacientePeso, _pacienteEstatura, _pacienteCiudadProcedencia, _pacienteEmail, _idSexo);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_insertarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertarUsuario`(  IN _usuarioNombreUsuario varchar(10),
									  IN _usuarioPassword varchar(9),
                                      IN _usuarioFechaCreacion date,
                                      IN _usuarioFechaBaja date,
                                      IN _idEstado int,
                                      IN _idEmpleado int
									)
BEGIN
		INSERT INTO usuario(usuarioNombreUsuario, usuarioPassword, usuarioFechaCreacion, usuarioFechaBaja, idEstado, idEmpleado)
        VALUES (_usuarioNombreUsuario, _usuarioPassword, _usuarioFechaCreacion, _usuarioFechaBaja, _idEstado, _idEmpleado);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarCargo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarCargo`()
BEGIN
	SELECT *
    FROM cargo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarCargoId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarCargoId`(IN _idCargo int)
BEGIN
	SELECT *
    FROM cargo
    WHERE idCargo = _idCargo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarCitaMedica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarCitaMedica`()
BEGIN 
	SELECT citamedica.idCitaMedica,citamedica.citaMedicaObservaciones , citamedica.citaMedicaFecha, citamedica.citaMedicaHoraInicio, citamedica.citaMedicaHoraFinal,
			citamedica.idUsuario,CONCAT(usuario.usuarioNombreUsuario) AS nombreUsuario, CONCAT(empleado.empleadoPrimerNombre, ' ' ,empleado.empleadoPrimerApellido) AS nombreEmpleado, 
            citamedica.citaMedicaNumeroIdentidad,CONCAT(paciente.pacientePrimerNombre, ' ' ,paciente.pacientePrimerApellido) AS nombrepaciente
            -- CONCAT(cargo.cargo) AS CargoDentroClinica
			
    FROM citamedica INNER JOIN usuario
					  ON citamedica.idUsuario = usuario.idUsuario
                      INNER JOIN paciente 
                      ON citamedica.citaMedicaNumeroIdentidad = paciente.numeroIdentidad
                      INNER JOIN empleado
                      ON citamedica.idUsuario = empleado.idEmpleado;
                      -- INNER JOIN cargo
                      -- ON citamedica.idUsuario = cargo.idCargo;
                      
                      
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarCitaMedicaRangoFecha` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarCitaMedicaRangoFecha`(IN _citaMedicaFecha date , IN _citaMedicaFechaFinal date )
BEGIN 
	SELECT citamedica.citaMedicaObservaciones , citamedica.citaMedicaFecha, citamedica.citaMedicaHoraInicio, citamedica.citaMedicaHoraFinal,
			CONCAT(usuario.usuarioNombreUsuario) AS nombreUsuario, CONCAT(empleado.empleadoPrimerNombre, ' ' ,empleado.empleadoPrimerApellido) AS nombreEmpleado, 
            citamedica.citaMedicaNumeroIdentidad,CONCAT(paciente.pacientePrimerNombre, ' ' ,paciente.pacientePrimerApellido) AS nombrepaciente
    FROM citamedica INNER JOIN usuario
					  ON citamedica.idUsuario = usuario.idUsuario
                      INNER JOIN paciente 
                      ON citamedica.citaMedicaNumeroIdentidad = paciente.numeroIdentidad
                      INNER JOIN empleado
                      ON citamedica.idUsuario = empleado.idEmpleado
	WHERE citamedica.citaMedicaFecha BETWEEN _citaMedicaFecha AND _citaMedicaFechaFinal;

    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarConsultas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarConsultas`()
BEGIN
	SELECT consultaMedica.idConsultasMedicas,paciente.numeroIdentidad,paciente.pacientePrimerNombre ,paciente.pacienteSegundoNombre ,paciente.pacientePrimerApellido ,paciente.pacienteSegundoApellido
			,paciente.pacienteAntecedentesFamiliares ,paciente.pacienteDireccion, paciente.pacienteTelefonoCelular, paciente.pacientePeso,
            paciente.pacienteEstatura, sexo.sexo, consultaMedica.consultaMedicaFechaIngreso, consultaMedica.consultaMedicaObservaciones, consultaMedica.consultaMedicaRecetasMedicas, usuario.usuarioNombreUsuario, empleado.empleadoPrimerNombre, 
            empleado.empleadoPrimerApellido, cargo.cargo, consultaMedica.idUsuario
    FROM consultaMedica INNER JOIN paciente
						 ON consultaMedica.consultaMedicaNumeroIdentidad = paciente.numeroIdentidad
                         INNER JOIN usuario
                         ON consultaMedica.idUsuario = usuario.idUsuario
                         INNER JOIN sexo
                         ON paciente.idSexo = sexo.idSexo
                         INNER JOIN empleado
                         ON usuario.idEmpleado = empleado.idEmpleado
                         INNER JOIN cargo
                         ON cargo.idCargo = empleado.idCargo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarConsultaX` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarConsultaX`(IN _numeroIdentidad VARCHAR(15))
BEGIN
	SELECT consultaMedica.idConsultasMedicas,paciente.numeroIdentidad,paciente.pacientePrimerNombre ,paciente.pacienteSegundoNombre ,paciente.pacientePrimerApellido ,paciente.pacienteSegundoApellido
			,paciente.pacienteAntecedentesFamiliares ,paciente.pacienteDireccion, paciente.pacienteTelefonoCelular, paciente.pacientePeso,
            paciente.pacienteEstatura, sexo.sexo, consultaMedica.consultaMedicaFechaIngreso, consultaMedica.consultaMedicaObservaciones, consultaMedica.consultaMedicaRecetasMedicas, usuario.usuarioNombreUsuario, empleado.empleadoPrimerNombre, 
            empleado.empleadoPrimerApellido, cargo.cargo, consultaMedica.idUsuario
    FROM consultaMedica INNER JOIN paciente
						 ON consultaMedica.consultaMedicaNumeroIdentidad = paciente.numeroIdentidad
                         INNER JOIN usuario
                         ON consultaMedica.idUsuario = usuario.idUsuario
                         INNER JOIN sexo
                         ON paciente.idSexo = sexo.idSexo
                         INNER JOIN empleado
                         ON usuario.idEmpleado = empleado.idEmpleado
                         INNER JOIN cargo
                         ON cargo.idCargo = empleado.idCargo
	WHERE consultaMedica.consultaMedicaNumeroIdentidad = _numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarEmpleado`(IN _idEmpleado int)
BEGIN
	SELECT *
    FROM empleado
    WHERE idEmpleado = _idEmpleado;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarEmpleados` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarEmpleados`()
BEGIN 
	SELECT *
    FROM empleado;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarHistoriaClinica` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarHistoriaClinica`()
BEGIN
	SELECT hc.numeroIdentidad, hc.alergicos, hc.cardiobasculares, hc.diabetes, hc.digestivo, hc.fechacreacion, hc.idUsuario, hc.medicamentos,
		   hc.numeroIdentidad, hc.observaciones, hc.pulmonares, hc.quirurgicos, hc.renales, hc.transfusiones, 
           CONCAT(p.primerNombre,' ',p.segundoNombre,' ',primerApellido,' ',segundoApellido ) as nombrePaciente,
           cm.fecha, cm.idCitaMedica, cm.horaInicio,cm.horaFinal, cm.observaciones,
           u.nombreUsuario, u.idEstado,
           com.fechaIngreso, com.recetasMedicas, com.observaciones
    FROM historiaClinica hc INNER JOIN paciente p
							 ON hc.numeroIdentidad = p.numeroIdentidad
                             INNER JOIN citaMedica cm
							 ON hc.numeroIdentidad = cm.numeroIdentidad
                             INNER JOIN usuario u
							 ON hc.idUsuario = u.idUsuario
                             INNER JOIN consultaMedica com
							 ON hc.numeroIdentidad = com.numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarHistoriaClinicaCondicion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarHistoriaClinicaCondicion`(IN _numeroIdentidad VARCHAR(15))
BEGIN
	SELECT hc.numeroIdentidad, hc.alergicos, hc.cardiobasculares, hc.diabetes, hc.digestivo, hc.fechacreacion, hc.idUsuario, hc.medicamentos,
		   hc.numeroIdentidad, hc.observaciones, hc.pulmonares, hc.quirurgicos, hc.renales, hc.transfusiones, 
           CONCAT(p.primerNombre,' ',p.segundoNombre,' ',primerApellido,' ',segundoApellido ) as nombrePaciente,
           cm.fecha, cm.idCitaMedica, cm.horaInicio,cm.horaFinal, cm.observaciones,
           u.nombreUsuario, u.idEstado,
           com.fechaIngreso, com.recetasMedicas, com.observaciones
    FROM historiaClinica hc INNER JOIN paciente p
							 ON hc.numeroIdentidad = p.numeroIdentidad
                             INNER JOIN citaMedica cm
							 ON hc.numeroIdentidad = cm.numeroIdentidad
                             INNER JOIN usuario u
							 ON hc.idUsuario = u.idUsuario
                             INNER JOIN consultamedica com
							 ON hc.numeroIdentidad = com.numeroIdentidad
	WHERE hc.NumeroIdentidad = _numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarHistoriaClinicaCondicionRangoFecha` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarHistoriaClinicaCondicionRangoFecha`(IN _fechaIcial DATE,
															  IN _fechaFinal DATE)
BEGIN
	SELECT hc.numeroIdentidad, hc.alergicos, hc.cardiobasculares, hc.diabetes, hc.digestivo, hc.fechacreacion, hc.idUsuario, hc.medicamentos,
		   hc.numeroIdentidad, hc.observaciones, hc.pulmonares, hc.quirurgicos, hc.renales, hc.transfusiones, 
           CONCAT(p.primerNombre,' ',p.segundoNombre,' ',primerApellido,' ',segundoApellido ) as nombrePaciente,
           cm.fecha, cm.idCitaMedica, cm.horaInicio,cm.horaFinal, cm.observaciones,
           u.nombreUsuario, u.idEstado,
           com.fechaIngreso, com.recetasMedicas, com.observaciones
    FROM historiaClinica hc INNER JOIN paciente p
							 ON hc.numeroIdentidad = p.numeroIdentidad
                             INNER JOIN citaMedica cm
							 ON hc.numeroIdentidad = cm.numeroIdentidad
                             INNER JOIN usuario u
							 ON hc.idUsuario = u.idUsuario
                             INNER JOIN consultaMedica com
							 ON hc.numeroIdentidad = com.numeroIdentidad
	WHERE hc.fechaCreacion BETWEEN _fechaInicial AND _fechaFinal;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarPacientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarPacientes`()
BEGIN
	SELECT paciente.numeroIdentidad,CONCAT(paciente.pacientePrimerNombre, ' ' ,paciente.pacienteSegundoNombre) AS nombresDelPaciente,
    CONCAT(paciente.pacientePrimerApellido, ' ' ,paciente.pacienteSegundoApellido) AS apellidosDelPaciente, 
			 sexo.sexo, paciente.pacienteAntecedentesFamiliares, paciente.pacienteTipoSangre, paciente.pacienteDireccion,
            paciente.pacienteTelefonoCelular, paciente.pacientePeso, paciente.pacienteEstatura, paciente.pacienteCiudadProcedencia, paciente.pacienteEmail
    FROM paciente INNER JOIN sexo
				  ON paciente.idSexo = sexo.idSexo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarPacientesX` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarPacientesX`(IN _numeroIdentidad varchar(15))
BEGIN
	SELECT paciente.numeroIdentidad,CONCAT(paciente.pacientePrimerNombre, ' ' ,paciente.pacienteSegundoNombre) AS nombresDelPaciente,
    CONCAT(paciente.pacientePrimerApellido, ' ' ,paciente.pacienteSegundoApellido) AS apellidosDelPaciente, 
			 sexo.sexo, paciente.pacienteAntecedentesFamiliares, paciente.pacienteTipoSangre, paciente.pacienteDireccion,
            paciente.pacienteTelefonoCelular, paciente.pacientePeso, paciente.pacienteEstatura, paciente.pacienteCiudadProcedencia, paciente.pacienteEmail
    FROM paciente INNER JOIN sexo
				  ON paciente.idSexo = sexo.idSexo
                  WHERE paciente.numeroIdentidad = _numeroIdentidad;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarPorNumeroIdentidad` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarPorNumeroIdentidad`(IN _citaMedicaNumeroIdentidad varchar(15))
BEGIN 
	SELECT citamedica.citaMedicaObservaciones , citamedica.citaMedicaFecha, citamedica.citaMedicaHoraInicio, citamedica.citaMedicaHoraFinal,
			CONCAT(usuario.usuarioNombreUsuario) AS nombreUsuario, CONCAT(empleado.empleadoPrimerNombre, ' ' ,empleado.empleadoPrimerApellido) AS nombreEmpleado, 
            citamedica.citaMedicaNumeroIdentidad,CONCAT(paciente.pacientePrimerNombre, ' ' ,paciente.pacientePrimerApellido) AS nombrepaciente
    FROM citamedica INNER JOIN usuario
					  ON citamedica.idUsuario = usuario.idUsuario
                      INNER JOIN paciente 
                      ON citamedica.citaMedicaNumeroIdentidad = paciente.numeroIdentidad
                      INNER JOIN empleado
                      ON citamedica.idUsuario = empleado.idEmpleado
	WHERE citamedica.citaMedicaNumeroIdentidad = _citaMedicaNumeroIdentidad;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarUsuario` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarUsuario`(IN _IdUsuario int)
BEGIN
	SELECT *
    FROM usuario
    WHERE IdUsuario = _IdUsuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_mostrarUsuarios` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_mostrarUsuarios`()
BEGIN 
	SELECT *
    FROM usuario;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-30 22:23:46
