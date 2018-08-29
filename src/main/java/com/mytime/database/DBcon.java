package com.mytime.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class DBcon {

	public static String dbRecord;
	static String value = "";
	static String delMngr = "";

	// public static String dataBaseConncection(String tableName, String fieldName,
	// String fieldValue) {
	public static void main(String args[]) {

		try {

			/**
			 * Establish connection with mongo database to fetch required records from the
			 * specific tables or schemas
			 */
			MongoClient mongo = new MongoClient("localhost", 27017);
			DB db = mongo.getDB("mytime");

			/**
			 * By using below code we can get all database names present in db List<String>
			 * dbs = mongo.getDatabaseNames(); for(String db : dbs){ System.out.println(db);
			 * }
			 */

			// DBCollection table = db.getCollection(tableName);

			DBCollection table = db.getCollection("Accounts");
			/**
			 * By using below code we can get all table names present in specific db
			 * Set<String> tables = db.getCollectionNames(); for(String coll : tables){
			 * System.out.println(coll); }
			 */

			BasicDBObject searchQuery = new BasicDBObject();
			// searchQuery.put(fieldName, fieldValue);

			searchQuery.put("accountName", "Nisum India");
			DBCursor cursor = table.find(searchQuery);

			while (cursor.hasNext()) {
				dbRecord = cursor.next().toString();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(dbRecord);
		// return dbRecord;

		String arrHeaders[] = { "Account Name", "Industry Type", "Delivery Managers", "Status" };
		String arrDBRecord[] = dbRecord.split(",");
		for (int i = 0; i < arrHeaders.length; i++) {
			for (int j = 0; j < arrDBRecord.length; j++) {
				String field[] = arrDBRecord[j].split(":");
				if (field.length == 2) {
					if (arrHeaders[i].equalsIgnoreCase("Delivery Managers")) {
						String fieldName = field[0].replace("\"", "");
						String firstString = arrHeaders[i].toLowerCase().replaceAll("\\s", "").trim();
						String secondString = fieldName.toLowerCase().trim();
						if (firstString.equalsIgnoreCase(secondString)) {
							String delManagers = field[1].replaceAll("\\[\\]", "");
							String arrDelManagers[] = delManagers.split(",");
							for (int k = 0; k <= arrDelManagers.length; k++) {
								if (k == arrDelManagers.length - 1) {
									delMngr = delMngr + arrDelManagers[k];
								} else {
									delMngr = delMngr + arrDelManagers[k] + ",";
									System.out.println(delMngr);
								}
							}
						}

					} else {
						String fieldName = field[0].replace("\"", "");
						String firstString = arrHeaders[i].toLowerCase().replaceAll("\\s", "").trim();
						String secondString = fieldName.toLowerCase().trim();
						if (firstString.equalsIgnoreCase(secondString)) {
							if (i == arrHeaders.length - 1) {
								value = value + field[1];
							} else {
								value = value + field[1] + ",";
								System.out.println(value);
							}
						}
					}

				}

			}
		}

	}
}
