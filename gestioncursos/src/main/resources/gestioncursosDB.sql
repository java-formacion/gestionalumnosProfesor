-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-07-2016 a las 10:48:43
-- Versión del servidor: 10.1.13-MariaDB
-- Versión de PHP: 5.5.37

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `gestioncursos`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteAlumno` (IN `codigo` INT)  NO SQL
DELETE FROM alumno 
WHERE codAlumno = codigo$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `deleteCurso` (IN `codigo` INT)  BEGIN
DELETE FROM curso WHERE codCurso = codigo;
END$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `deleteModulo` (IN `codigo` INT)  BEGIN
DELETE FROM modulo WHERE codModulo = codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllAlumno` ()  NO SQL
SELECT codAlumno, a.nombre as 'nAlumno', apellidos, email, telefono, dni_nie, fNacimiento, a.codGenero, g.nombre as 'nGenero' 
FROM alumno a
INNER JOIN genero g ON g.codGenero = a.codGenero$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `getAllCurso` ()  BEGIN
SELECT `codCurso`, `nombre` AS 'nCurso', `codPatrocinador`, `codTipoCurso` FROM `curso`;
END$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `getAllModulo` ()  BEGIN
SELECT `codModulo`, `nombre`, `uFormativa`, `duracion` FROM modulo;
END$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `getAlumnoById` (IN `codigo` INT)  NO SQL
SELECT codAlumno, a.nombre AS 'nAlumno', apellidos, dni_nie, fNacimiento, email, telefono, a.codGenero, g.nombre AS 'nGenero' FROM alumno a INNER JOIN genero g ON g.codGenero = a.codGenero WHERE a.codAlumno = codigo$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `getCursoById` (IN `codigo` INT)  NO SQL
SELECT codCurso, nombre AS 'nCurso' , codPatrocinador, codTipoCurso FROM curso WHERE codCurso = codigo$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `getModuloById` (IN `codigo` INT)  BEGIN
SELECT `codModulo`, `nombre`, `uFormativa`, `duracion` FROM modulo WHERE codModulo = codigo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `insertAlumno` (IN `nombre` VARCHAR(150), IN `apellidos` VARCHAR(250), IN `dni` VARCHAR(9), IN `fecha` DATE, IN `email` VARCHAR(100), IN `telefono` VARCHAR(13), IN `codGenero` INT, OUT `codAlumno` INT)  NO SQL
BEGIN
INSERT INTO alumno (nombre, apellidos, dni_nie, fNacimiento, email, telefono, codGenero) VALUES(LOWER(nombre), LOWER(apellidos),LOWER(dni),fecha,LOWER(email),LOWER(telefono),codGenero);

SET codAlumno = LAST_INSERT_ID();

END$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `insertCurso` (IN `nombre` VARCHAR(150), IN `codTipoCurso` INT, IN `codPatrocinador` VARCHAR(50), OUT `codCurso` INT)  BEGIN
INSERT INTO curso(`nombre`, `codPatrocinador`, `codTipoCurso`) VALUES (LOWER(nombre), LOWER(codPatrocinador), codTipoCurso);
SET codCurso = LAST_INSERT_ID();
END$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `insertModulo` (IN `nombre` VARCHAR(150), IN `uFormativa` VARCHAR(50), IN `duracion` INT, OUT `codModulo` INT)  BEGIN
INSERT INTO modulo(`nombre`, `uFormativa`, `duracion`) VALUES (nombre,uFormativa,duracion);
SET codModulo = LAST_INSERT_ID();
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateAlumno` (IN `codigo` INT, IN `nombre` VARCHAR(150), IN `apellidos` VARCHAR(250), IN `dni` VARCHAR(9), IN `fecha` DATE, IN `email` VARCHAR(100), IN `telefono` VARCHAR(13), IN `codGenero` INT)  NO SQL
UPDATE `alumno` SET `nombre`=LOWER(nombre),
`apellidos`= LOWER(apellidos),
`dni_nie`=LOWER(dni),
`fNacimiento`=fecha,
`email`=LOWER(email),
`telefono`=LOWER(telefono),
`codGenero`=codGenero 
WHERE codAlumno = codigo$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `updateCurso` (IN `codigo` INT, IN `nombre` VARCHAR(150), IN `codPatrocinador` VARCHAR(50), IN `codTipo` INT)  BEGIN
UPDATE curso SET `nombre`=nombre,`codPatrocinador`=codPatrocinador,`codTipoCurso`=codTipo WHERE codCurso = codigo;
END$$

CREATE DEFINER=`usuario`@`%` PROCEDURE `updateModulo` (IN `nombre` VARCHAR(150), IN `uFormativa` VARCHAR(50), IN `duracion` INT, IN `codigo` INT)  BEGIN
UPDATE modulo SET `nombre`=nombre,`uFormativa`=uFormativa,`duracion`=duracion WHERE codModulo = codigo;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `codAlumno` int(11) NOT NULL COMMENT 'Indice del alumno',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre del alumno',
  `apellidos` varchar(250) NOT NULL COMMENT 'Apellidos del alumno',
  `dni_nie` varchar(9) NOT NULL COMMENT 'Documento de identificacion del alumno',
  `fNacimiento` date NOT NULL COMMENT 'Fecha de nacimiento del alumno',
  `email` varchar(100) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `codGenero` int(11) NOT NULL COMMENT 'Codigo de genero'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificacion`
--

CREATE TABLE `calificacion` (
  `codAlumno` int(11) NOT NULL,
  `codCurso` int(11) NOT NULL,
  `codModulo` int(11) NOT NULL,
  `nota` int(11) NOT NULL,
  `fExamen` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `codCurso` int(11) NOT NULL COMMENT 'Codigo de curso',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre del curso',
  `codPatrocinador` varchar(50) NOT NULL COMMENT 'Referencia del curso',
  `codTipoCurso` int(11) NOT NULL COMMENT 'Codigo del tipo de curso'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso_alumno`
--

CREATE TABLE `curso_alumno` (
  `codAlumno` int(11) NOT NULL COMMENT 'Codigo de alumno',
  `codCurso` int(11) NOT NULL COMMENT 'Codigo de curso',
  `referencia` varchar(50) NOT NULL,
  `fInicio` date NOT NULL COMMENT 'Fecha de inicio',
  `fFin` date DEFAULT NULL COMMENT 'Fecha fin'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `genero`
--

CREATE TABLE `genero` (
  `codGenero` int(11) NOT NULL COMMENT 'Codigo de genero',
  `nombre` varchar(50) NOT NULL COMMENT 'Nombre de genero'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

CREATE TABLE `modulo` (
  `codModulo` int(11) NOT NULL COMMENT 'Codigo de modulo',
  `nombre` varchar(150) NOT NULL COMMENT 'Nombre del modulo',
  `uFormativa` varchar(50) NOT NULL COMMENT 'Unidad formativa del modulo',
  `duracion` int(11) NOT NULL COMMENT 'Duracion del modulo'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipocurso`
--

CREATE TABLE `tipocurso` (
  `codTipoCurso` int(11) NOT NULL COMMENT 'Codigo del tipo de curso',
  `nombre` varchar(50) NOT NULL COMMENT 'Nombre del tipo de curso'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipocurso`
--

INSERT INTO `tipocurso` (`codTipoCurso`, `nombre`) VALUES
(1, 'Lanbide'),
(2, 'Hobetuz'),
(3, 'Fundacion Tripartita');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`codAlumno`),
  ADD UNIQUE KEY `dni-nie` (`dni_nie`),
  ADD UNIQUE KEY `codGenero_2` (`codGenero`),
  ADD KEY `codGenero` (`codGenero`);

--
-- Indices de la tabla `calificacion`
--
ALTER TABLE `calificacion`
  ADD PRIMARY KEY (`codAlumno`,`codCurso`,`codModulo`),
  ADD KEY `fk_calificacion_curso` (`codCurso`),
  ADD KEY `fk_calificacion_modulo` (`codModulo`);

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`codCurso`),
  ADD UNIQUE KEY `codTipoCurso` (`codTipoCurso`),
  ADD KEY `codTipoCurso_2` (`codTipoCurso`);

--
-- Indices de la tabla `curso_alumno`
--
ALTER TABLE `curso_alumno`
  ADD PRIMARY KEY (`codAlumno`,`codCurso`),
  ADD UNIQUE KEY `referencia` (`referencia`),
  ADD KEY `fk_cursoAlumno_curso` (`codCurso`);

--
-- Indices de la tabla `genero`
--
ALTER TABLE `genero`
  ADD PRIMARY KEY (`codGenero`);

--
-- Indices de la tabla `modulo`
--
ALTER TABLE `modulo`
  ADD PRIMARY KEY (`codModulo`);

--
-- Indices de la tabla `tipocurso`
--
ALTER TABLE `tipocurso`
  ADD PRIMARY KEY (`codTipoCurso`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `codAlumno` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Indice del alumno', AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `curso`
--
ALTER TABLE `curso`
  MODIFY `codCurso` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de curso', AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `genero`
--
ALTER TABLE `genero`
  MODIFY `codGenero` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de genero', AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `modulo`
--
ALTER TABLE `modulo`
  MODIFY `codModulo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo de modulo', AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `tipocurso`
--
ALTER TABLE `tipocurso`
  MODIFY `codTipoCurso` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Codigo del tipo de curso', AUTO_INCREMENT=4;
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
