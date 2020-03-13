/**
 * Project:A01079605_assignment2
 * File: CustomerDao.java
 * Date: Nov 18, 2019
 * Time: 10:51:05 AM
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

import a00123456.book.data.Customer;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class CustomerDao extends Dao {
	public static final String TABLE_NAME = DbConstants.CUSTOMER_TABLE_NAME;

	private static final Logger LOG = LogManager.getLogger();

	public CustomerDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String sql = String.format("create table %s(" // 1
				+ "%s VARCHAR(50), " // 2
				+ "%s VARCHAR(50), " // 3
				+ "%s VARCHAR(50), " // 4
				+ "%s VARCHAR(50), " // 5
				+ "%s VARCHAR(50), " // 6
				+ "%s VARCHAR(50), " // 7
				+ "%s VARCHAR(50), " // 8
				+ "%s VARCHAR(50), " // 9
				+ "%s VARCHAR(50), " // 10
				+ "primary key (%s) )", // 11
				tableName, // 1
				Fields.CUSTOMER_ID.getName(), // 2
				Fields.FIRST_NAME.getName(), //
				Fields.LAST_NAME.getName(), // 5
				Fields.STREET.getName(), Fields.CITY.getName(), Fields.POSTAL_CODE.getName(), Fields.PHONE.getName(), //
				Fields.EMAILADDRESS.getName(), Fields.JOINDATE.getName(), Fields.CUSTOMER_ID.getName()); // 2

		LOG.debug(sql);
		super.create(sql);
	}

	public void add(Customer customer) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			String city = customer.getCity();
			if (customer.getCity().equals("Motta Sant'Anastasia")) {
				city = "Motta Sant Anastasia";
			}
			String sql = String.format("insert into %s values(" // 1 tableName
					+ "'%s', " // 2 StudentId
					+ "'%s', " // 3 FirstName
					+ "'%s', " // 4 LastName
					+ "'%s', " // 5 Street
					+ "'%s', " // 6 City
					+ "'%s', " // 7 PostalCode
					+ "'%s', " // 8 Phone
					+ "'%s', " // 9 EmailAddress
					+ "'%s')", // 10 JoinedDate
					tableName, // 1
					customer.getId(), // 2
					customer.getFirstName(), // 3
					customer.getLastName(), // 4
					customer.getStreet(), // 5
					city, // 6
					customer.getPostalCode(), // 7
					customer.getPhone(), // 8
					customer.getEmailAddress(), // 9
					customer.getJoinedDate()); // 10
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public Customer getCustomer(String customerId) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Customer customer = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.CUSTOMER_ID.getName(), customerId);
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

				int year = Integer.parseInt(resultSet.getString(Fields.JOINDATE.getName()).substring(0, 4));
				int month = Integer.parseInt(resultSet.getString(Fields.JOINDATE.getName()).substring(5, 7));
				int day = Integer.parseInt(resultSet.getString(Fields.JOINDATE.getName()).substring(8, 10));

				customer = new Customer();
				customer.setId((Integer.parseInt(resultSet.getString(Fields.CUSTOMER_ID.getName()))));
				customer.setFirstName(resultSet.getString(Fields.FIRST_NAME.getName()));
				customer.setLastName(resultSet.getString(Fields.LAST_NAME.getName()));
				customer.setStreet(resultSet.getString(Fields.STREET.getName()));
				customer.setCity(resultSet.getString(Fields.CITY.getName()));
				customer.setPostalCode(resultSet.getString(Fields.POSTAL_CODE.getName()));
				customer.setPhone(resultSet.getString(Fields.PHONE.getName()));
				customer.setEmailAddress(resultSet.getString(Fields.EMAILADDRESS.getName()));
				customer.setJoinedDate(year, month, day);
			}
		} finally {
			close(statement);
		}

		return customer;
	}

	public void update(Customer customer) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s'WHERE %s='%s'", tableName, //
					Fields.CUSTOMER_ID.getName(), customer.getId(), //
					Fields.FIRST_NAME.getName(), customer.getFirstName(), //
					Fields.LAST_NAME.getName(), customer.getFirstName(), //
					Fields.STREET.getName(), customer.getStreet(), Fields.CITY.getName(), customer.getCity(), Fields.POSTAL_CODE.getName(),
					customer.getPostalCode(), Fields.PHONE.getName(), customer.getPhone(), //
					Fields.EMAILADDRESS.getName(), customer.getEmailAddress(), Fields.JOINDATE.getName(), customer.getJoinedDate());

			LOG.debug(sql);
			int rowcount = statement.executeUpdate(sql);
			System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Customer customer) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.CUSTOMER_ID.getName(), customer.getId());
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
			String sql = String.format("SELECT * FROM %s", tableName, Fields.CUSTOMER_ID.getName());
			LOG.debug(sql);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				ids.add(result.getString(Fields.CUSTOMER_ID.getName()));
			}

		} finally {
			close(statement);
		}
		return ids;
	}

	public enum Fields {

		CUSTOMER_ID("customerId", "VARCHAR", 9, 1), //
		FIRST_NAME("firstName", "VARCHAR", 20, 2), //
		LAST_NAME("lastName", "VARCHAR", 20, 4), //
		STREET("street", "VARCHAR", 40, 7), //
		CITY("city", "VARCHAR", 20, 4), //
		POSTAL_CODE("postalCode", "VARCHAR", 10, 8), //
		PHONE("phone", "VARCHAR", 10, 5), //
		EMAILADDRESS("email", "VARCHAR", 40, 7), //
		JOINDATE("joinDate", "DATE", -1, 6); //

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

	private static class Validator {

		private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		private static final String YYYYMMDD_PATTERN = "(20\\d{2})(\\d{2})(\\d{2})"; // valid for years 2000-2099

		private Validator() {
		}

		/**
		 * Validate an email string.
		 *
		 * @param email
		 *            the email string.
		 * @return true if the email address is valid, false otherwise.
		 */
		public static boolean validateEmail(final String email) {
			return email.matches(EMAIL_PATTERN);
		}

		public static boolean validateJoinedDate(String yyyymmdd) {
			return yyyymmdd.matches(YYYYMMDD_PATTERN);
		}

	}

}
