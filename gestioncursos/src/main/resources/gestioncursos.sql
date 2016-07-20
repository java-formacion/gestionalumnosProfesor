-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-07-2016 a las 13:26:02
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
DELETE FROM alumno
WHERE codAlumno=@codigo$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteCurso`()
    NO SQL
DELETE FROM curso
WHERE codCurso=@codigo$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAlumno`()
    NO SQL
SELECT a.codAlumno, a.nombre as 'nAlumno' , a.apellidos, a.dni_nie, a.fNacimiento, a.codGenero as 'codGenero', g.nombreGenero
FROM alumno a
	INNER JOIN genero g ON g.codGenero=a.codGenero$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAlumno`(OUT `codigo` INT, IN `nombre` VARCHAR(150), IN `apellido` VARCHAR(250), IN `dni_nie` VARCHAR(9), IN `fNacimiento` DATE, IN `codGenero` INT, IN `email` VARCHAR(100), IN `telefono` VARCHAR(13))
    NO SQL
BEGIN
INSERT INTO 
`alumno`(`nombre`, `apellidos`, `dni_nie`, `fNacimiento`, `codGenero`, `email`, `telefono`) 
VALUES (lower(nombre),lower(apellido),lower(dni_nie),fNacimiento,codGenero,lower(email),lower(telefono));

SET codigo=last_insert_id();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertCurso`(OUT `codCurso` INT(11), IN `nombreCurso` VARCHAR(150), IN `codPatrocinador` VARCHAR(50), IN `tipoCurso` INT(1))
    NO SQL
BEGIN
INSERT INTO 
`alumno`(`nombreCurso`, `codPatrocinador`, `tipoCurso`) 
VALUES (lower(nombreCurso),lower(codPatrocinador),tipoCurso);

SET codCurso=last_insert_id();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAlumno`(IN `codigo` INT, IN `nombre` VARCHAR(150), IN `apellido` VARCHAR(250), IN `dni_nie` VARCHAR(9), IN `fNacimiento` DATE, IN `codGenero` INT, IN `email` VARCHAR(100), IN `telefono` VARCHAR(13))
    NO SQL
UPDATE `alumno` SET `nombre`=lower(nombre),`apellidos`=lower(apellido),`dni_nie`=lower(dni_nie),`fNacimiento`=fNacimiento,`codGenero`=codGenero,`email`=lower(email),`telefono`=lower(telefono) WHERE `codAlumno`=codigo$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCurso`(OUT `codigo` INT(11), IN `nombreCurso` VARCHAR(150), IN `codPatrocinador` VARCHAR(50), IN `tipoCurso` INT(1))
    NO SQL
UPDATE `curso` SET `nombreCurso`=lower(nombreCurso),`codPatrocinador`=lower(codPatrocinador),`tipoCurso`=tipoCurso WHERE `codCurso`=codigo$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE IF NOT EXISTS `alumno` (
  `codAlumno` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de Alumno',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre de Alumno',
  `apellidos` varchar(250) NOT NULL COMMENT 'Apellidos de Alumno',
  `dni_nie` varchar(9) NOT NULL COMMENT 'Documento de Identificacion',
  `fNacimiento` date NOT NULL COMMENT 'Fecha de nacimiento',
  `codGenero` int(11) NOT NULL COMMENT 'Genero',
  `email` varchar(100) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  PRIMARY KEY (`codAlumno`),
  UNIQUE KEY `dni-nie` (`dni_nie`),
  KEY `codGenero_2` (`codGenero`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`codAlumno`, `nombre`, `apellidos`, `dni_nie`, `fNacimiento`, `codGenero`, `email`, `telefono`) VALUES
(1, 'a', 'y', '789e', '2014-09-09', 1, 'asdf@gmail.com', '123456'),
(5, 'Angus', 'Young', '81475345F', '1934-12-03', 1, 'highschoolrock@gmail.com', '5558974'),
(6, 'hercules', 'rockefeller', '123456781', '2015-01-05', 1, 'hrocke@gmail.com', '666614776'),
(7, 'q', 'q', 'q', '2016-07-04', 1, 'q', 'q');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion`
--

CREATE TABLE IF NOT EXISTS `calificacion` (
  `codAlumno` int(11) NOT NULL COMMENT 'Codigo Alumno',
  `codCurso` int(11) NOT NULL COMMENT 'Codigo de Curso',
  `codModulo` int(11) NOT NULL COMMENT 'Codigo de Modulo',
  `nota` int(11) NOT NULL COMMENT 'Nota',
  `fExamen` date NOT NULL COMMENT 'Fecha de Examen',
  PRIMARY KEY (`codAlumno`,`codCurso`,`codModulo`),
  KEY `fk_calificacion_curso` (`codCurso`),
  KEY `fk_calificacion_modulo` (`codModulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE IF NOT EXISTS `curso` (
  `codCurso` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de Curso',
  `nombreCurso` varchar(150) NOT NULL COMMENT 'Nombre de Curso',
  `codPatrocinador` varchar(50) NOT NULL COMMENT 'Codigo de referencia de Curso',
  `tipoCurso` int(1) NOT NULL COMMENT 'Tipo de Curso',
  PRIMARY KEY (`codCurso`),
  KEY `tipoCurso_2` (`tipoCurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`codCurso`, `nombreCurso`, `codPatrocinador`, `tipoCurso`) VALUES
(1, 'asdf', 'jariguai', 2);

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
  KEY `fk_curso_curso_alumno` (`codCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE IF NOT EXISTS `genero` (
  `codGenero` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de genero',
  `nombreGenero` varchar(15) NOT NULL COMMENT 'Genero',
  PRIMARY KEY (`codGenero`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `genero`
--

INSERT INTO `genero` (`codGenero`, `nombreGenero`) VALUES
(1, 'Masculino'),
(2, 'Femenino'),
(3, 'Otros');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modulo`
--

CREATE TABLE IF NOT EXISTS `modulo` (
  `codModulo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de Modulo',
  `nombreModulo` varchar(150) NOT NULL COMMENT 'Nombre de Modulo',
  `uFormativa` varchar(50) NOT NULL COMMENT 'Unidad Formativa',
  `duracion` int(11) NOT NULL COMMENT 'Duracion en horas',
  PRIMARY KEY (`codModulo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocurso`
--

CREATE TABLE IF NOT EXISTS `tipocurso` (
  `codTipoCurso` int(11) NOT NULL AUTO_INCREMENT,
  `tipoCurso` varchar(50) NOT NULL,
  PRIMARY KEY (`codTipoCurso`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `tipocurso`
--

INSERT INTO `tipocurso` (`codTipoCurso`, `tipoCurso`) VALUES
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
  ADD CONSTRAINT `fk_curso_tipocurso` FOREIGN KEY (`tipoCurso`) REFERENCES `tipocurso` (`codTipoCurso`);

--
-- Filtros para la tabla `curso_alumno`
--
ALTER TABLE `curso_alumno`
  ADD CONSTRAINT `fk_alumno_curso_alumno` FOREIGN KEY (`codAlumno`) REFERENCES `alumno` (`codAlumno`),
  ADD CONSTRAINT `fk_curso_curso_alumno` FOREIGN KEY (`codCurso`) REFERENCES `curso` (`codCurso`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
