-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-07-2016 a las 12:22:03
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `gestioncursos`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAlumno`(IN `codigo` INT)
    NO SQL
DELETE from alumno
WHERE codAlumno = codigo$$

CREATE DEFINER=`admin`@`%` PROCEDURE `getAllAlumno`()
    NO SQL
SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email,telefono,dni_nie,fNacimiento, a.codGenero as 'codGenero', g.nombre as 'nGenero'
FROM alumno a
 INNER JOIN genero g ON g.codGenero = a.codGenero$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAlumno`(OUT `codigo` INT, IN `nombre` VARCHAR(150), IN `apellidos` VARCHAR(250), IN `fNacimiento` DATE, IN `email` VARCHAR(100), IN `telefono` VARCHAR(9), IN `dni_nie` VARCHAR(13), IN `codGenero` INT)
    NO SQL
BEGIN
INSERT INTO alumno(nombre, apellidos, fNacimiento, email, telefono, dni_nie, codGenero) VALUES(LOWER(nombre), LOWER(apellidos), fNacimiento, LOWER(email), LOWER(telefono), LOWER(dni_nie), codGenero);
SET codigo = last_Insert_id();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAlumno`(IN `codigo` INT, IN `nombre` VARCHAR(150) CHARSET utf8, IN `apellidos` VARCHAR(250) CHARSET utf8, IN `dni_nie` VARCHAR(9) CHARSET utf8, IN `fNacimiento` DATE, IN `email` VARCHAR(100) CHARSET utf8, IN `telefono` VARCHAR(13) CHARSET utf8, IN `codGenero` INT)
    NO SQL
UPDATE `alumno` SET
`nombre`=LOWER(nombre),`apellidos`=LOWER(apellidos),`dni_nie`=LOWER(dni-nie),`fNacimiento`=fNaciemiento,`email`=LOWER(email),`telefono`=LOWER(telefono),`codGenero`=codGenero WHERE `codAlumno`=codigo$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE IF NOT EXISTS `alumno` (
  `codAlumno` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Indice del Alumno',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre del alumno',
  `apellidos` varchar(250) NOT NULL,
  `dni_nie` varchar(9) NOT NULL COMMENT 'documento de identificacion del alumno',
  `fNacimiento` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `codGenero` int(11) NOT NULL,
  PRIMARY KEY (`codAlumno`),
  UNIQUE KEY `dni_nie` (`dni_nie`),
  KEY `codGenero_2` (`codGenero`),
  KEY `codGenero_3` (`codGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion`
--

CREATE TABLE IF NOT EXISTS `calificacion` (
  `codAlumno` int(11) NOT NULL,
  `codCurso` int(11) NOT NULL,
  `codModulo` int(11) NOT NULL,
  `nota` int(11) NOT NULL,
  `fExamen` date NOT NULL,
  PRIMARY KEY (`codAlumno`,`codCurso`,`codModulo`),
  KEY `fk_calificicacion_modulo` (`codModulo`),
  KEY `fk_calificacion_curso` (`codCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE IF NOT EXISTS `curso` (
  `codCurso` int(11) NOT NULL,
  `nombre` varchar(150) NOT NULL,
  `codPatrocinador` varchar(50) NOT NULL,
  `codTipoCurso` int(11) NOT NULL,
  PRIMARY KEY (`codCurso`),
  KEY `codTipoCurso_2` (`codTipoCurso`),
  KEY `codTipoCurso_3` (`codTipoCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_alumno`
--

CREATE TABLE IF NOT EXISTS `curso_alumno` (
  `codAlumno` int(11) NOT NULL,
  `codCurso` int(11) NOT NULL,
  `referencia` varchar(50) NOT NULL,
  `fInicio` date NOT NULL,
  `fFin` date DEFAULT NULL,
  PRIMARY KEY (`codAlumno`,`codCurso`),
  UNIQUE KEY `referencia` (`referencia`),
  KEY `fk_alumno_curso_curso` (`codCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE IF NOT EXISTS `genero` (
  `codGenero` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`codGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulo`
--

CREATE TABLE IF NOT EXISTS `modulo` (
  `codModulo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  `uFormativa` varchar(50) NOT NULL,
  `duracion` int(11) NOT NULL,
  PRIMARY KEY (`codModulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocurso`
--

CREATE TABLE IF NOT EXISTS `tipocurso` (
  `codTipoCurso` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`codTipoCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
  ADD CONSTRAINT `fk_calificicacion_modulo` FOREIGN KEY (`codModulo`) REFERENCES `modulo` (`codModulo`);

--
-- Filtros para la tabla `curso`
--
ALTER TABLE `curso`
  ADD CONSTRAINT `fk_curso_tipocurso` FOREIGN KEY (`codTipoCurso`) REFERENCES `tipocurso` (`codTipoCurso`);

--
-- Filtros para la tabla `curso_alumno`
--
ALTER TABLE `curso_alumno`
  ADD CONSTRAINT `fk_alumno_curso_alumno` FOREIGN KEY (`codAlumno`) REFERENCES `alumno` (`codAlumno`),
  ADD CONSTRAINT `fk_alumno_curso_curso` FOREIGN KEY (`codCurso`) REFERENCES `curso` (`codCurso`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
