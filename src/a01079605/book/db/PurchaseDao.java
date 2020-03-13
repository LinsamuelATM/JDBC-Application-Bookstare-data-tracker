/**
 * Project:A01079605_assignment2
 * File: PurchaseDao.java
 * Date: Nov 18, 2019
 * Time: 10:57:38 AM
 */
package a01079605.book.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00123456.book.data.Purchase;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class PurchaseDao extends Dao {
	public static final String TABLE_NAME = DbConstants.PURCHASE_TABLE_NAME;

	private static final Logger LOG = LogManager.getLogger();

	public PurchaseDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String sql = String.format("create table %s(" // 1
				+ "%s VARCHAR(50), " // 7
				+ "%s VARCHAR(50), " // 8
				+ "%s VARCHAR(50), " // 9
				+ "%s VARCHAR(50), " // 10
				+ "primary key (%s) )", // 11
				tableName, // 1
				Fields.PURCHASE_ID.getName(), // 2
				Fields.CUSTOMER_ID.getName(), //
				Fields.BOOK_ID.getName(), // 5
				Fields.PRICE.getName(), Fields.PURCHASE_ID.getName()); // 2

		LOG.debug(sql);
		super.create(sql);
	}

	public void add(Purchase purchase) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("insert into %s values(" // 1 tableName
					+ "'%s', " // 7 PostalCode
					+ "'%s', " // 8 Phone
					+ "'%s', " // 9 EmailAddress
					+ "'%s')", // 10 JoinedDate
					tableName, // 1
					purchase.getId(), // 2
					purchase.getCustomerId(), // 3
					purchase.getBookId(), // 4
					purchase.getPrice()); // 5
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public Purchase getPurchase(String purchaseID) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Purchase purchase = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.PURCHASE_ID.getName(), purchaseID);
			LOG.debug(sql);
			ResultSet resultSet = statement.executeQuery(sql);

			// get the Student
			// throw an exception if we get more than one result
			int count = 0;
			while (resultSet.next()) {
				count++;
				if (count > 1) {
					throw new Exception(String.format("Expected one result, got %d", count));
				}
				purchase = new Purchase();
				purchase.setId((Integer.parseInt(resultSet.getString(Fields.PURCHASE_ID.getName()))));
				purchase.setCustomerId((Integer.parseInt(resultSet.getString(Fields.CUSTOMER_ID.getName()))));
				purchase.setBookId(Integer.parseInt(resultSet.getString(Fields.BOOK_ID.getName())));
				purchase.setPrice(Float.parseFloat((resultSet.getString(Fields.PRICE.getName()))));
			}
		} finally {
			close(statement);
		}

		return purchase;
	}

	public void update(Purchase purchase) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s'WHERE %s='%s'", tableName, //
					Fields.PURCHASE_ID.getName(), purchase.getId(), Fields.CUSTOMER_ID.getName(), purchase.getCustomerId(), Fields.BOOK_ID.getName(),
					purchase.getBookId(), Fields.PRICE.getName(), purchase.getPrice()
			//
			);

			LOG.debug(sql);
			int rowcount = statement.executeUpdate(sql);
			System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Purchase purchase) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.PURCHASE_ID.getName(), purchase.getId());
			LOG.debug(sql);
			int rowcount = statement.executeUpdate(sql);
			System.out.println(String.format("Deleted %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public List<String> getIds() throws SQLException {
		Statement statement = null;
		Connection connection;
		List<String> ids = new ArrayList<String>();
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			String sql = String.format("SELECT * FROM %s", tableName, Fields.PURCHASE_ID.getName());
			LOG.debug(sql);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				ids.add(result.getString(Fields.PURCHASE_ID.getName()));
			}

		} finally {
			close(statement);
		}
		return ids;
	}

	public enum Fields {

		PURCHASE_ID("purchaseId", "VARCHAR", 9, 1), //
		CUSTOMER_ID("customerId", "VARCHAR", 20, 2), //
		BOOK_ID("bookId", "VARCHAR", 20, 4), //
		PRICE("price", "VARCHAR", 40, 7); //

		private final String name;
		private final String type;
		private final int length;
		private final int column;

		Fields(String name, String type, int length, int column) {
			this.name = name;
			this.type = type;
			this.length = length;
			this.column = column;
		}

		public String getType() {
			return type;
		}

		public String getName() {
			return name;
		}

		public int getLength() {
			return length;
		}

		public int getColumn() {
			return column;
		}
	}

}
