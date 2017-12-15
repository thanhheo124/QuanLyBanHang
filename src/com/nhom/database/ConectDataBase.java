package com.nhom.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConectDataBase {

	protected Connection conn;

	public ConectDataBase() {

	}

	public Connection getConnection() {
		Properties pt = new Properties();
		try {
			FileInputStream fileInput = new FileInputStream(new File("src/Database.properties"));
			pt.load(fileInput);
			String url = pt.getProperty("url");
			String classForName = pt.getProperty("classForName");

			Class.forName(classForName);
			conn = DriverManager.getConnection(url);
		} catch (IOException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Connection getConn() {
		return conn;
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
