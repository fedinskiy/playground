package ru.fedinskiy;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Properties;

public class database {
	public static void main(String[] args) {
		String url = "jdbc:sqlite:db/zoo";

		Properties properties = new Properties();
		properties.setProperty(SQLiteConfig.Pragma.DATE_STRING_FORMAT.pragmaName, "yyyy-MM-dd HH:mm");
		try (Connection conn = DriverManager.getConnection(url, properties);
		     Statement stmt = conn.createStatement()) {

			ResultSet result = stmt.executeQuery("Select date_born from animal");
			ResultSetMetaData meta = result.getMetaData();
			System.out.println(meta.getCatalogName(1));
			System.out.println(meta.getColumnType(1)); //Types.VARCHAR
			System.out.println(meta.getColumnTypeName(1));
			while (result.next()) {
				System.out.println(result.getDate(1) + "!");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getSQLState());
			System.out.println(e.getErrorCode());
		}
	}

	public static void datasource() throws SQLException {
		SQLiteDataSource source = new SQLiteDataSource();
		try (Connection connection = source.getConnection()) {

		}
	}

	public static void practice(Connection connection) throws SQLException {
		try (Statement stmt = connection.createStatement();
		     ResultSet rs = stmt.executeQuery("select * from STUDENT");) {
			while (rs.next()) {

			}
		}
	}
}
