package com.loxx3450.hw_04_02_25.task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AutosDbCreator {
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";
	private static final String PASSWORD = "local";
	private static final String DB_NAME = "autos_db";
	private static final String TARGET_DB_URL = "jdbc:postgresql://localhost:5432/" + DB_NAME;

	public static void createDb() throws SQLException {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			ensureDbExists(conn);
		}

		// Reopening connection to connect directly to TARGET_DB
		try (Connection conn = DriverManager.getConnection(TARGET_DB_URL, USER, PASSWORD)) {
			ensureTableAutosExists(conn);
		}
	}

	private static void ensureDbExists(Connection conn) throws SQLException {
		if (! dbExists(conn, DB_NAME)) {
			String createDbQuery = "CREATE DATABASE " + DB_NAME;

			try (Statement statement = conn.createStatement()) {
				statement.executeUpdate(createDbQuery);
			}
		}
	}

	private static void ensureTableAutosExists(Connection conn) throws SQLException {
		try (Statement statement = conn.createStatement()) {
			createEnumAutoType(statement);
			createTableAutos(statement);
		}
	}

	private static void createEnumAutoType(Statement statement) throws SQLException {
		String createAutoTypeQuery = """
				 DO $$
				 BEGIN
					 IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'auto_type') THEN
						 CREATE TYPE auto_type AS ENUM('sedan', 'hatchback', 'station wagon');
					 END IF;
				 END $$;
				
			""";

		statement.executeUpdate(createAutoTypeQuery);
	}

	private static void createTableAutos(Statement statement) throws SQLException {
		String createTableQuery = """
					CREATE TABLE IF NOT EXISTS autos (
						id SERIAL PRIMARY KEY,
						manufacturer VARCHAR(50) NOT NULL,
						model VARCHAR(50) NOT NULL,
						engine_capacity DECIMAL(3, 2) NOT NULL,
						year INTEGER NOT NULL CHECK (year > 0 AND year <= EXTRACT(YEAR FROM CURRENT_DATE)),
						color VARCHAR(50) NOT NULL,
						type auto_type NOT NULL
					);
				""";

		statement.executeUpdate(createTableQuery);
	}

	private static boolean dbExists(Connection conn, String dbName) throws SQLException {
		String query = "SELECT 1 FROM pg_database WHERE datname = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, dbName);

			try (ResultSet rs = statement.executeQuery()) {
				return rs.next();
			}
		}
	}
}
