drop database if exists ligadefutbol;

Create database ligadefutbol;

use ligadefutbol;

Create table Jugadores (
    id_jugador smallint(8) auto_increment primary key,
    nombreApellidos varchar(30),
    nombreCamiseta varchar(20),
	numeroCamiseta tinyint(2),
    edad tinyint(2),
    equipo varchar(30),
    posicion varchar(20),
    fotoJugador blob
);

insert into jugadores values
(null, 'Cristiano Ronaldo', 'Ronaldo', 7, 28, 'Real Madrid', 'Delantero', null),
(null, 'Iker Casillas', 'Casillas', 1, 31, 'Real Madrid', 'Portero', null),
(null, 'Raul Albiol', 'Albiol', 17, 30, 'Real Madrid', 'Defensa', null),
(null, 'Mesut Ozil', 'Ozil', 10, 25, 'Real Madrid', 'Centrocampista', null),
(null, 'Luka Modric', 'Modric', 24, 26, 'Real Madrid', 'Centrocampista', null),
(null, 'Karim Benzema', 'Benzema', 9, 24, 'Real Madrid', 'Delantero', null),
(null, 'Lionel Messi', 'Messi', 10, 27, 'FC Barcelona', 'Delantero', null)