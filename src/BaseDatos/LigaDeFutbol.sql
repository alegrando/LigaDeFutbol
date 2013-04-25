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
