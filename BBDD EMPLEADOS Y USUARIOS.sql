-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3308
-- Tiempo de generación: 17-05-2019 a las 07:18:12
-- Versión del servidor: 5.7.19
-- Versión de PHP: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `empleados`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `cod_empleado` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `puesto` varchar(30) NOT NULL,
  `sueldo` decimal(10,0) NOT NULL,
  `horas` int(11) NOT NULL DEFAULT '6'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`cod_empleado`, `nombre`, `apellido`, `puesto`, `sueldo`, `horas`) VALUES
(1, 'Manolo', 'Rodriguez', 'Jefe', '4500', 8),
(2, 'Pepe', 'Maldonado', 'Desarrollador', '2800', 6),
(3, 'Alberto', 'Sanchez', 'Informatico', '2800', 6),
(4, 'Alberto', 'Molina', 'Limpieza', '1200', 6),
(5, 'Pablo', 'Rodriguez', 'Desarrollo', '2800', 6),
(6, 'RIcardo', 'Molina', 'Informatico', '2400', 6),
(7, 'Daniel', 'Luque', 'Desarrollador', '2500', 6),
(8, 'Luis', 'Garcia', 'Administración', '1500', 6),
(9, 'Carlos', 'Gil', 'Investigación', '1800', 6),
(10, 'Pedro', 'Martín', 'Seguridad', '1400', 6),
(11, 'Jose', 'García', 'Seguridad', '1400', 6),
(12, 'Pedro', 'García', 'Seguridad', '1400', 6),
(13, 'Manuel', 'Martínez', 'Investigación', '1500', 6),
(14, 'Emiliano', 'Campillo', 'Abogado', '1600', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_usuario`
--

CREATE TABLE `tipo_usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `tipo_usuario`
--

INSERT INTO `tipo_usuario` (`id`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Usuario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nombre` varchar(80) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `last_session` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id_tipo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `usuario`, `password`, `nombre`, `correo`, `last_session`, `id_tipo`) VALUES
(1, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'admin', 'admin@mail.com', '2019-05-14 17:30:22', 1),
(2, 'pepe', '265392dc2782778664cc9d56c8e3cd9956661bb0', 'pepe', 'pepe@gmail.com', '2019-05-13 17:48:23', 2),
(4, 'perico', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'perico', 'perico@mail.com', '2019-04-29 16:54:34', 2),
(5, 'perez', '7110eda4d09e062aa5e4a390b0a572ac0d2c0220', 'perico', 'perico@gmail.com', '2019-05-13 16:01:37', 2),
(6, 'pedrito', '945c444acc4e489a3a193bf1ec2a0ae14b79279b', 'pedrito', 'pedrito@mail.com', '2019-05-13 16:43:05', 2),
(7, 'daddy', '3052150f9a9de9a376afcc809ff9e34e6c22f373', 'daddy', 'daddy2@mail.com', '2019-05-13 17:55:13', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`cod_empleado`);

--
-- Indices de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `cod_empleado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `tipo_usuario`
--
ALTER TABLE `tipo_usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
