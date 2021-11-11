package com.nttdata.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase principal
 * 
 * @author Alexei Viadero
 *
 */
public class NTTDataJDBCMain {
	/**
	 * Metodo principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Conexión y ejecución de consulta a BBDD (MySQL).
		stablishMDBConnection();
	}

	private static void stablishMDBConnection() {

		try {

			// Se establece el driver de conexión a BBDD de MySQL.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Apertura de conexión con BBDD MySQL (URL | Usuario | Contraseña).
			final Connection dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nttdata_javajdbc", "root", "rootroot");

			// Realización de consulta.
			final Statement sentence = dbConnection.createStatement();
			final String query = "SELECT * FROM nttdata_javajdbc.nttdata_mysql_soccer_player WHERE id_soccer_player=1";
			final ResultSet queryResult = sentence.executeQuery(query);

			// Interación de resultado.
			StringBuilder playerInfo = new StringBuilder();
 
			while (queryResult.next()) {

				playerInfo.append("ID jugador: ");
				playerInfo.append(queryResult.getString("id_soccer_player"));

				playerInfo.append(" | Nombre: ");
				playerInfo.append(queryResult.getString("name"));

				playerInfo.append(" | Fecha nacimiento: ");
				playerInfo.append(queryResult.getString("birth_date"));

				playerInfo.append(" | Demarcación: ");
				playerInfo.append(queryResult.getString("first_rol"));

				playerInfo.append(" | Demacración alternativa: ");
				playerInfo.append(queryResult.getString("second_rol"));

				playerInfo.append(" | ID equipo: ");
				playerInfo.append(queryResult.getString("id_soccer_team"));

				playerInfo.append("\n");
			}

			System.out.println(playerInfo.toString());

			// Cierre de conexión con BBDD.
			dbConnection.close();

			// Se lanza la excepción en caso de no conexión con BBDD o por falta de atributos indicados en el bucle.
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("[ERROR] - Error en la conexión con BBDD");
		}
	}
}
