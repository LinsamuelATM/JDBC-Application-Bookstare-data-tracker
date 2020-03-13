/**
 * Project:A01079605_assignmnet02
 * File: PurchaseDaoTester.java
 * Date: Nov 18, 2019
 * Time: 3:05:03 PM
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
import a00123456.book.data.Purchase;
import a00123456.book.io.PurchaseReader;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class PurchaseDaoTester {
	private Properties properties;
	private static Connection connection;
	private Database database;
	private PurchaseDao purchaseDao;

	private static final Logger LOG = LogManager.getLogger();

	public PurchaseDaoTester() {

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

			insertPurchases();
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
		purchaseDao = new PurchaseDao(database);
	}

	private void dropTables() throws SQLException {
		purchaseDao.drop();
	}

	private void createTables() throws SQLException {
		purchaseDao.create();

	}

	private void insertPurchases() throws SQLException, ApplicationException {
		try {
			Collection<Purchase> purchases = PurchaseReader.read().values();

			for (Purchase p : purchases) {
				purchaseDao.add(p);
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

	private void update(Purchase purchase) {
		try {
			purchaseDao.update(purchase);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	private void delete(Purchase purchase) {
		try {
			purchaseDao.delete(purchase);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e);
		}
	}

	public PurchaseDao getPurchaseDao() {
		return this.purchaseDao;
	}

}
