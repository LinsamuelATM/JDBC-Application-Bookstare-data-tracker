/**
 * Project:A01079605_lab07
 * File: CustomerDaoTester.java
 * Date: Oct 28, 2019
 * Time: 12:51:25 PM
 */
package a01079605.book.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a00123456.book.ApplicationException;
import a00123456.book.data.Customer;
import a00123456.book.io.CustomerReader;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class CustomerDaoTester {

	private Properties properties;
	private static Connection connection;
	private Database database;
	private CustomerDao customerDao;

	private static final Logger LOG = LogManager.getLogger();

	public CustomerDaoTester() {

	}

	public void generate() throws IOException, SQLException {
		File dbPropertiesFile = new File("db.properties");
		properties = new Properties();
		properties.load(new FileInputStream(dbPropertiesFile));
		database = new Database(properties);

		if (!dbPropertiesFile.exists()) {
			System.err.println("Cannot find file");
			System.exit(-1);
		}

		try {
			run();
		} catch (Exception e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		} finally {
			database.shutdown();
		}

	}

	private void run() throws Exception {
		connect();

		try {

			dropTables();

			System.out.println();
			System.out.println();

			createTables();
			System.out.println();
			System.out.println();

			insertCustomers();
			System.out.println();
			System.out.println();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
	}

	private void connect() throws SQLException, ApplicationException {
		connection = Database.getConnection();
		customerDao = new CustomerDao(database);
	}

	private void dropTables() throws SQLException {
		customerDao.drop();
	}

	private void createTables() throws SQLException {
		customerDao.create();

	}

	private void insertCustomers() throws SQLException, ApplicationException {
		try {
			Collection<Customer> customers = CustomerReader.read().values();

			for (Customer c : customers) {
				customerDao.add(c);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
			System.out.println(e);
		}
	}

	private Customer readCustomer() {
		Customer customer = null;
		try {
			// customer = customerDao.getCustomer("2");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return customer;
	}

	private void update(Customer customer) {
		try {
			customerDao.update(customer);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	private void delete(Customer student) {
		try {
			customerDao.delete(student);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	public CustomerDao getCustomerDao() {
		return this.customerDao;
	}

}
