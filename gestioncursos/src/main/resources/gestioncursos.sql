-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-07-2016 a las 12:14:47
-- Versión del servidor: 5.7.9
-- Versión de PHP: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestioncursos`
--
DROP DATABASE `gestioncursos`;
CREATE DATABASE IF NOT EXISTS `gestioncursos` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `gestioncursos`;

DELIMITER $$
--
-- Procedimientos
--
DROP PROCEDURE IF EXISTS `deleteAlumno`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAlumno` (IN `codigo` INT)  NO SQL
DELETE FROM alumno 
WHERE codAlumno = codigo$$

DROP PROCEDURE IF EXISTS `deleteCurso`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCurso` (IN `codigo` INT)  BEGIN
DELETE FROM curso WHERE codCurso = codigo;
END$$

DROP PROCEDURE IF EXISTS `deleteModulo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteModulo` (IN `codigo` INT)  BEGIN
DELETE FROM modulo WHERE codModulo = codigo;
END$$

DROP PROCEDURE IF EXISTS `getAllAlumno`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAlumno` ()  NO SQL
SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, telefono, dni_nie, fNacimiento, a.codGenero, g.nombre as 'nGenero' 
FROM alumno a
INNER JOIN genero g ON g.codGenero = a.codGenero$$

DROP PROCEDURE IF EXISTS `getAllCurso`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCurso` ()  BEGIN
SELECT `codCurso`, `nombre` AS 'nCurso', `codPatrocinador`, `codTipoCurso` FROM `curso`;
END$$

DROP PROCEDURE IF EXISTS `getAllModulo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllModulo` ()  BEGIN
SELECT `codModulo`, `nombre`, `uFormativa`, `duracion` FROM modulo;
END$$

DROP PROCEDURE IF EXISTS `getAlumnoById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getAlumnoById` (IN `codigo` INT)  NO SQL
SELECT codAlumno, a.nombre AS 'nAlumno', apellidos, dni_nie, fNacimiento, email, telefono, a.codGenero, g.nombre AS 'nGenero' FROM alumno a INNER JOIN genero g ON g.codGenero = a.codGenero WHERE a.codAlumno = codigo$$

DROP PROCEDURE IF EXISTS `getCursoById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getCursoById` (IN `codigo` INT)  NO SQL
SELECT codCurso, nombre AS 'nCurso' , codPatrocinador, codTipoCurso FROM curso WHERE codCurso = codigo$$

DROP PROCEDURE IF EXISTS `getModuloById`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getModuloById` (IN `codigo` INT)  BEGIN
SELECT `codModulo`, `nombre`, `uFormativa`, `duracion` FROM modulo WHERE codModulo = codigo;
END$$

DROP PROCEDURE IF EXISTS `insertAlumno`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAlumno` (IN `nombre` VARCHAR(150), IN `apellidos` VARCHAR(250), IN `dni` VARCHAR(9), IN `fecha` DATE, IN `email` VARCHAR(100), IN `telefono` VARCHAR(13), IN `codGenero` INT, OUT `codAlumno` INT)  NO SQL
BEGIN
INSERT INTO alumno (nombre, apellidos, dni_nie, fNacimiento, email, telefono, codGenero) VALUES(LOWER(nombre), LOWER(apellidos),LOWER(dni),fecha,LOWER(email),LOWER(telefono),codGenero);

SET codAlumno = LAST_INSERT_ID();

END$$

DROP PROCEDURE IF EXISTS `insertCurso`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertCurso` (IN `nombre` VARCHAR(150), IN `codTipoCurso` INT, IN `codPatrocinador` VARCHAR(50), OUT `codCurso` INT)  BEGIN
INSERT INTO curso(`nombre`, `codPatrocinador`, `codTipoCurso`) VALUES (LOWER(nombre), LOWER(codPatrocinador), codTipoCurso);
SET codCurso = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `insertModulo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertModulo` (IN `nombre` VARCHAR(150), IN `uFormativa` VARCHAR(50), IN `duracion` INT, OUT `codModulo` INT)  BEGIN
INSERT INTO modulo(`nombre`, `uFormativa`, `duracion`) VALUES (nombre,uFormativa,duracion);
SET codModulo = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS `updateAlumno`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAlumno` (IN `codigo` INT, IN `nombre` VARCHAR(150), IN `apellidos` VARCHAR(250), IN `dni` VARCHAR(9), IN `fecha` DATE, IN `email` VARCHAR(100), IN `telefono` VARCHAR(13), IN `codGenero` INT)  NO SQL
UPDATE `alumno` SET `nombre`=LOWER(nombre),
`apellidos`= LOWER(apellidos),
`dni_nie`=LOWER(dni),
`fNacimiento`=fecha,
`email`=LOWER(email),
`telefono`=LOWER(telefono),
`codGenero`=codGenero 
WHERE codAlumno = codigo$$

DROP PROCEDURE IF EXISTS `updateCurso`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCurso` (IN `codigo` INT, IN `nombre` VARCHAR(150), IN `codPatrocinador` VARCHAR(50), IN `codTipo` INT)  BEGIN
UPDATE curso SET `nombre`=nombre,`codPatrocinador`=codPatrocinador,`codTipoCurso`=codTipo WHERE codCurso = codigo;
END$$

DROP PROCEDURE IF EXISTS `updateModulo`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateModulo` (IN `nombre` VARCHAR(150), IN `uFormativa` VARCHAR(50), IN `duracion` INT, IN `codigo` INT)  BEGIN
UPDATE modulo SET `nombre`=nombre,`uFormativa`=uFormativa,`duracion`=duracion WHERE codModulo = codigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

DROP TABLE IF EXISTS `alumno`;
CREATE TABLE IF NOT EXISTS `alumno` (
  `codAlumno` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Indice del alumno',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre del alumno',
  `apellidos` varchar(250) NOT NULL COMMENT 'Apellidos del alumno',
  `dni_nie` varchar(9) NOT NULL COMMENT 'Documento de identificacion del alumno',
  `fNacimiento` date NOT NULL COMMENT 'Fecha de nacimiento del alumno',
  `email` varchar(100) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `codGenero` int(11) NOT NULL COMMENT 'Codigo de genero',
  PRIMARY KEY (`codAlumno`),
  UNIQUE KEY `dni-nie` (`dni_nie`),
  KEY `codGenero` (`codGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Truncar tablas antes de insertar `alumno`
--

TRUNCATE TABLE `alumno`;
--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codAlumno`, `nombre`, `apellidos`, `dni_nie`, `fNacimiento`, `email`, `telefono`, `codGenero`) VALUES
(1, 'marta', 'rivera del amo', '16087431n', '2016-07-20', '', '', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion`
--

DROP TABLE IF EXISTS `calificacion`;
CREATE TABLE IF NOT EXISTS `calificacion` (
  `codAlumno` int(11) NOT NULL,
  `codCurso` int(11) NOT NULL,
  `codModulo` int(11) NOT NULL,
  `nota` int(11) NOT NULL,
  `fExamen` date NOT NULL,
  PRIMARY KEY (`codAlumno`,`codCurso`,`codModulo`),
  KEY `fk_calificacion_curso` (`codCurso`),
  KEY `fk_calificacion_modulo` (`codModulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncar tablas antes de insertar `calificacion`
--

TRUNCATE TABLE `calificacion`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE IF NOT EXISTS `curso` (
  `codCurso` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de curso',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre del curso',
  `codPatrocinador` varchar(50) NOT NULL COMMENT 'Referencia del curso',
  `codTipoCurso` int(11) NOT NULL COMMENT 'Codigo del tipo de curso',
  PRIMARY KEY (`codCurso`),
  KEY `codTipoCurso_2` (`codTipoCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncar tablas antes de insertar `curso`
--

TRUNCATE TABLE `curso`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_alumno`
--

DROP TABLE IF EXISTS `curso_alumno`;
CREATE TABLE IF NOT EXISTS `curso_alumno` (
  `codAlumno` int(11) NOT NULL COMMENT 'Codigo de alumno',
  `codCurso` int(11) NOT NULL COMMENT 'Codigo de curso',
  `referencia` varchar(50) NOT NULL,
  `fInicio` date NOT NULL COMMENT 'Fecha de inicio',
  `fFin` date DEFAULT NULL COMMENT 'Fecha fin',
  PRIMARY KEY (`codAlumno`,`codCurso`),
  UNIQUE KEY `referencia` (`referencia`),
  KEY `fk_cursoAlumno_curso` (`codCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncar tablas antes de insertar `curso_alumno`
--

TRUNCATE TABLE `curso_alumno`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

DROP TABLE IF EXISTS `genero`;
CREATE TABLE IF NOT EXISTS `genero` (
  `codGenero` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de genero',
  `nombre` varchar(50) NOT NULL COMMENT 'Nombre de genero',
  PRIMARY KEY (`codGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Truncar tablas antes de insertar `genero`
--

TRUNCATE TABLE `genero`;
--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`codGenero`, `nombre`) VALUES
(1, 'Hombre'),
(2, 'Mujer'),
(3, 'Otros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulo`
--

DROP TABLE IF EXISTS `modulo`;
CREATE TABLE IF NOT EXISTS `modulo` (
  `codModulo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de modulo',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre del modulo',
  `uFormativa` varchar(50) NOT NULL COMMENT 'Unidad formativa del modulo',
  `duracion` int(11) NOT NULL COMMENT 'Duracion del modulo',
  PRIMARY KEY (`codModulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncar tablas antes de insertar `modulo`
--

TRUNCATE TABLE `modulo`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocurso`
--

DROP TABLE IF EXISTS `tipocurso`;
CREATE TABLE IF NOT EXISTS `tipocurso` (
  `codTipoCurso` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo del tipo de curso',
  `nombre` varchar(50) NOT NULL COMMENT 'Nombre del tipo de curso',
  PRIMARY KEY (`codTipoCurso`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Truncar tablas antes de insertar `tipocurso`
--

TRUNCATE TABLE `tipocurso`;
--
-- Volcado de datos para la tabla `tipocurso`
--

INSERT INTO `tipocurso` (`codTipoCurso`, `nombre`) VALUES
(1, 'Lanbide'),
(2, 'Hobetuz'),
(3, 'Fundacion Tripartita');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD CONSTRAINT `fk_alumno_genero` FOREIGN KEY (`codGenero`) REFERENCES `genero` (`codGenero`);

--
-- Filtros para la tabla `calificacion`
--
ALTER TABLE `calificacion`
  ADD CONSTRAINT `fk_calificacion_alumno` FOREIGN KEY (`codAlumno`) REFERENCES `alumno` (`codAlumno`),
  ADD CONSTRAINT `fk_calificacion_curso` FOREIGN KEY (`codCurso`) REFERENCES `curso` (`codCurso`),
  ADD CONSTRAINT `fk_calificacion_modulo` FOREIGN KEY (`codModulo`) REFERENCES `modulo` (`codModulo`);

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `fk_curso_tipocurso` FOREIGN KEY (`codTipoCurso`) REFERENCES `tipocurso` (`codTipoCurso`);

--
-- Filtros para la tabla `curso_alumno`
--
ALTER TABLE `curso_alumno`
  ADD CONSTRAINT `fk_cursoAlumno_alumno` FOREIGN KEY (`codAlumno`) REFERENCES `alumno` (`codAlumno`),
  ADD CONSTRAINT `fk_cursoAlumno_curso` FOREIGN KEY (`codCurso`) REFERENCES `curso` (`codCurso`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
