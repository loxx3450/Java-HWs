package com.loxx3450.hw_04_02_25.task2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.function.Consumer;

public class AutosDbHelper implements AutoCloseable {
	private static final String URL = "jdbc:postgresql://localhost:5432/autos_db";
	private static final String USER = "postgres";
	private static final String PASSWORD = "local";
	private Connection conn;

	public void connect() throws SQLException {
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		System.out.println("Connected to PostgreSQL database");
	}

	// ========= Print methods =========
	public void printAllAutos() throws SQLException {
		String selectQuery = "SELECT * FROM autos";

		executeStatement(selectQuery, this::safePrintAuto);
	}

	public void printAllManufacturers() throws SQLException {
		String selectQuery = "SELECT DISTINCT manufacturer FROM autos";

		executeStatement(selectQuery, (resultSet) -> {
			try {
				String manufacturer = resultSet.getString("manufacturer");
				System.out.println(manufacturer);
			} catch (SQLException e) {
				System.err.println("Error processing row: " + e.getMessage());
			}
		});
	}

	public void printCountOfAutosByManufacturer() throws SQLException {
		String selectQuery = """
					SELECT manufacturer, COUNT(*) 
					FROM autos 
					GROUP BY manufacturer
				""";

		executeStatement(selectQuery, (resultSet) -> {
			try {
				String manufacturer = resultSet.getString("manufacturer");
				int count = resultSet.getInt("count");

				System.out.printf("Manufacturer '%s' has %d autos%n", manufacturer, count);
			} catch (SQLException e) {
				System.err.println("Error processing row: " + e.getMessage());
			}
		});
	}

	public void printManufacturerWithTheMostAutos() throws SQLException {
		String selectQuery = """
					 SELECT manufacturer, COUNT(*) as count
					 FROM autos
					 GROUP BY manufacturer
					 ORDER BY count DESC
					 FETCH FIRST 1 ROW WITH TIES
				""";

		executeStatement(selectQuery, (resultSet) -> {
			try {
				String manufacturer = resultSet.getString("manufacturer");
				int count = resultSet.getInt("count");

				System.out.printf("Manufacturer '%s' has the most autos (%d total)%n", manufacturer, count);
			} catch (SQLException e) {
				System.err.println("Error processing row: " + e.getMessage());
			}
		});
	}

	public void printManufacturerWithTheFewestAutos() throws SQLException {
		String selectQuery = """
					 SELECT manufacturer, COUNT(*) as count
					 FROM autos
					 GROUP BY manufacturer
					 ORDER BY count ASC
					 FETCH FIRST 1 ROW WITH TIES
				""";

		executeStatement(selectQuery, (resultSet) -> {
			try {
				String manufacturer = resultSet.getString("manufacturer");
				int count = resultSet.getInt("count");

				System.out.printf("Manufacturer '%s' has the fewest autos (%d total)%n", manufacturer, count);
			} catch (SQLException e) {
				System.err.println("Error processing row: " + e.getMessage());
			}
		});
	}

	public void printAllAutosFromYear(int year) throws SQLException {
		printAllAutosByFilter(AutoFilter.builder()
			.year(Optional.of(year))
			.build());
	}

	public void printAllAutosBetweenYears(int from, int until) throws SQLException {
		ensureConnected();

		String selectQuery = """
					SELECT * 
					FROM autos
					WHERE year BETWEEN ? AND ?
				""";

		try (PreparedStatement statement = conn.prepareStatement(selectQuery)) {
			statement.setInt(1, from);
			statement.setInt(2, until);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					printAuto(resultSet);
				}
			}
		}
	}

	public void printAllAutosByManufacturer(String manufacturer) throws SQLException {
		printAllAutosByFilter(AutoFilter.builder()
			.manufacturer(Optional.of(manufacturer))
			.build());
	}

	public void printAllAutosByColor(String color) throws SQLException {
		printAllAutosByFilter(AutoFilter.builder()
			.color(Optional.of(color))
			.build());
	}

	public void printAllAutosByEngineCapacity(double engineCapacity) throws SQLException {
		printAllAutosByFilter(AutoFilter.builder()
			.engineCapacity(Optional.of(engineCapacity))
			.build());
	}

	public void printAllAutosByType(AutoType type) throws SQLException {
		printAllAutosByFilter(AutoFilter.builder()
			.type(Optional.of(type))
			.build());
	}

	public void printAllAutosByFilter(AutoFilter filter) throws SQLException {
		ensureConnected();

		StringBuilder selectQuery = new StringBuilder("""
				SELECT *
				FROM autos
				WHERE true
			""");

		if (filter.getManufacturer().isPresent())
			selectQuery.append(" AND manufacturer ILIKE ?");
		if (filter.getColor().isPresent())
			selectQuery.append(" AND color ILIKE ?");
		if (filter.getEngineCapacity().isPresent())
			selectQuery.append(" AND engine_capacity = ?");
		if (filter.getType().isPresent())
			selectQuery.append(" AND type::text ILIKE ?"); 	// cast type to text to use 'ILIKE'
		if (filter.getYear().isPresent())
			selectQuery.append(" AND year = ?");

		try (PreparedStatement statement = conn.prepareStatement(selectQuery.toString())) {
			int paramCounter = 1;

			if (filter.getManufacturer().isPresent())
				statement.setString(paramCounter++, filter.getManufacturer().get());
			if (filter.getColor().isPresent())
				statement.setString(paramCounter++, filter.getColor().get());
			if (filter.getEngineCapacity().isPresent())
				statement.setDouble(paramCounter++, filter.getEngineCapacity().get());
			if (filter.getType().isPresent())
				statement.setString(paramCounter++, filter.getType().get().toString());
			if (filter.getYear().isPresent())
				statement.setInt(paramCounter++, filter.getYear().get());


			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					printAuto(resultSet);
				}
			}
		}
	}

	// ========= Data Manipulation methods =========
	public void insertAuto(Auto auto) throws SQLException {
		ensureConnected();

		String insertQuery = """
			INSERT INTO autos (manufacturer, model, engine_capacity, year, color, type)
			VALUES (?, ?, ?, ?, ?, ?::auto_type)
		""";

		try (PreparedStatement statement = conn.prepareStatement(insertQuery)) {
			statement.setString(1, auto.getManufacturer());
			statement.setString(2, auto.getModel());
			statement.setDouble(3, auto.getEngineCapacity());
			statement.setInt(4, auto.getYear());
			statement.setString(5, auto.getColor());
			statement.setString(6, auto.getType().toString().toLowerCase());

			statement.executeUpdate();
		}
	}

	public void deleteAuto(int id) throws SQLException {
		ensureConnected();

		String deleteQuery = "DELETE FROM autos WHERE id = ?";

		try (PreparedStatement statement = conn.prepareStatement(deleteQuery)) {
			statement.setInt(1, id);

			statement.executeUpdate();
		}
	}

	public void updateAuto(UpdateAutoDTO updateDTO) throws SQLException {
		ensureConnected();

		StringBuilder updateQuery = new StringBuilder("UPDATE autos SET ");

		if (updateDTO.getManufacturer().isPresent()) {
			updateQuery.append("manufacturer = ?,");
		}
		if (updateDTO.getModel().isPresent()) {
			updateQuery.append("model = ?,");
		}
		if (updateDTO.getEngineCapacity().isPresent()) {
			updateQuery.append("engine_capacity = ?,");
		}
		if (updateDTO.getYear().isPresent()) {
			updateQuery.append("year = ?,");
		}
		if (updateDTO.getColor().isPresent()) {
			updateQuery.append("color = ?,");
		}
		if (updateDTO.getType().isPresent()) {
			updateQuery.append("type = ?::auto_type,");
		}

		// Delete last ','
		if (updateQuery.charAt(updateQuery.length() - 1) == ',') {
			updateQuery.deleteCharAt(updateQuery.length() - 1);
		}

		updateQuery.append(" WHERE id = ?");

		try (PreparedStatement statement = conn.prepareStatement(updateQuery.toString())) {
			int paramCounter = 1;

			if (updateDTO.getManufacturer().isPresent())
				statement.setString(paramCounter++, updateDTO.getManufacturer().get());
			if (updateDTO.getModel().isPresent())
				statement.setString(paramCounter++, updateDTO.getModel().get());
			if (updateDTO.getEngineCapacity().isPresent())
				statement.setDouble(paramCounter++, updateDTO.getEngineCapacity().get());
			if (updateDTO.getYear().isPresent())
				statement.setInt(paramCounter++, updateDTO.getYear().get());
			if (updateDTO.getColor().isPresent())
				statement.setString(paramCounter++, updateDTO.getColor().get());
			if (updateDTO.getType().isPresent())
				statement.setString(paramCounter++, updateDTO.getType().get().toString().toLowerCase());

			// For WHERE
			statement.setInt(paramCounter, updateDTO.getId());

			statement.executeUpdate();
		}
	}


	public void disconnect() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
			System.out.println("Disconnected from PostgreSQL database");
		}
	}

	private void executeStatement(String selectQuery, Consumer<ResultSet> rowHandler) throws SQLException {
		ensureConnected();

		try (Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(selectQuery)) {

			while (resultSet.next()) {
				rowHandler.accept(resultSet);
			}
		}
	}

	private void ensureConnected() throws SQLException {
		if (conn == null || conn.isClosed()) {
			throw new SQLException("No active database connection. Call connect() first.");
		}
	}

	private void safePrintAuto(ResultSet resultSet) {
		try {
			printAuto(resultSet);
		} catch (SQLException e) {
			System.err.println("Error processing row: " + e.getMessage());
		}
	}

	private void printAuto(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		String manufacturer = resultSet.getString("manufacturer");
		String model = resultSet.getString("model");
		double engineCapacity = resultSet.getDouble("engine_capacity");
		int year = resultSet.getInt("year");
		String color = resultSet.getString("color");
		String type = resultSet.getString("type");

		Auto auto = new Auto(id, manufacturer, model, engineCapacity, year, color, AutoType.fromString(type));

		System.out.println(auto);
	}

	@Override
	public void close() throws Exception {
		disconnect();
	}
}
