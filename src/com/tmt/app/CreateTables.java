package com.tmt.app;

import java.io.IOException;
import java.sql.SQLException;

import com.tmt.service.DatabaseService;

public class CreateTables {
	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		//Mysql tables
		DatabaseService databaseService = new DatabaseService();
		databaseService.createTables();
	}
}