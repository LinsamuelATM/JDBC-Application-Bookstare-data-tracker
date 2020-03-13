/**
 * Project:A01079605_assignment2
 * File: BookDao.java
 * Date: Nov 18, 2019
 * Time: 10:57:23 AM
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

import a00123456.book.data.Book;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class BookDao extends Dao {
	public static final String TABLE_NAME = DbConstants.BOOK_TABLE_NAME;

	private static final Logger LOG = LogManager.getLogger();

	public BookDao(Database database) {
		super(database, TABLE_NAME);
	}

	@Override
	public void create() throws SQLException {
		String sql = String.format("create table %s(" // 1
				+ "%s VARCHAR(100), " // 2
				+ "%s VARCHAR(100), " // 3
				+ "%s VARCHAR(100), " // 4
				+ "%s VARCHAR(100), " // 5
				+ "%s VARCHAR(100), " // 6
				+ "%s VARCHAR(100), " // 7
				+ "%s VARCHAR(100), " // 8
				+ "%s VARCHAR(100), " // 9
				+ "primary key (%s) )", // 11
				tableName, // 1
				Fields.BOOK_ID.getName(), // 2
				Fields.ISBN.getName(), //
				Fields.AUTHORS.getName(), // 5
				Fields.YEAR.getName(), Fields.TITLE.getName(), Fields.RATING.getName(), Fields.RATINGSCOUNT.getName(), //
				Fields.IMAGEURL.getName(), Fields.BOOK_ID.getName()); // 2

		LOG.debug(sql);
		super.create(sql);
	}

	public void add(Book book) throws SQLException {
		Statement statement = null;
		try {
			Connection connection = Database.getConnection();
			statement = connection.createStatement();
			String title = book.getTitle();
			String author = book.getAuthors();
			if (title.contains("\'")) {
				String[] book_name = title.split("\'");
				String first = book_name[0];
				String second = book_name[1];
				title = first + second;
			}
			if (author.contains("\'")) {

				String[] author_name = author.split("\'");
				String first = author_name[0];
				String second = author_name[1];
				author = first + second;
			}

			String sql = String.format("insert into %s values(" // 1 tableName
					+ "'%s', " // 2 bookId
					+ "'%s', " // 3 Isbn
					+ "'%s', " // 4 authors
					+ "'%s', " // 5 year
					+ "'%s', " // 6 title
					+ "'%s', " // 7 year
					+ "'%s', " // 8 rating
					+ "'%s')", // 10 rating count
					tableName, // 1
					String.valueOf(book.getId()), book.getIsbn(), author, String.valueOf(book.getYear()), title, String.valueOf(book.getRating()),
					String.valueOf(book.getRatingsCount()), book.getImageUrl());
			LOG.debug(sql);
			statement.executeUpdate(sql);
		} finally {
			close(statement);
		}
	}

	public Book getBook(String bookId) throws SQLException, Exception {
		Connection connection;
		Statement statement = null;
		Book book = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("SELECT * FROM %s WHERE %s = '%s'", tableName, Fields.BOOK_ID.getName(), bookId);
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
				book = new Book();
				book.setId((Integer.parseInt(resultSet.getString(Fields.BOOK_ID.getName()))));
				book.setIsbn(resultSet.getString(Fields.ISBN.getName()));
				book.setAuthors(resultSet.getString(Fields.AUTHORS.getName()));
				book.setYear(Integer.parseInt(resultSet.getString(Fields.YEAR.getName())));
				book.setTitle(resultSet.getString(Fields.TITLE.getName()));
				book.setRating(Float.parseFloat(resultSet.getString(Fields.RATING.getName())));
				book.setRatingsCount(Integer.parseInt(resultSet.getString(Fields.RATINGSCOUNT.getName())));
				book.setImageUrl(resultSet.getString(Fields.IMAGEURL.getName()));
			}
		} finally {
			close(statement);
		}

		return book;
	}

	public void update(Book book) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("UPDATE %s set %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s', %s='%s'WHERE %s='%s'", tableName, //
					Fields.BOOK_ID.getName(), book.getId(), //
					Fields.ISBN.getName(), book.getIsbn(), //
					Fields.AUTHORS.getName(), book.getAuthors(), //
					Fields.YEAR.getName(), String.valueOf(book.getYear()), Fields.TITLE.getName(), book.getTitle(), Fields.RATING.getName(),
					String.valueOf(book.getRating()), Fields.RATINGSCOUNT.getName(), String.valueOf(book.getRatingsCount()), //
					Fields.IMAGEURL.getName(), book.getImageUrl());

			LOG.debug(sql);
			int rowcount = statement.executeUpdate(sql);
			System.out.println(String.format("Updated %d rows", rowcount));
		} finally {
			close(statement);
		}
	}

	public void delete(Book book) throws SQLException {
		Connection connection;
		Statement statement = null;
		try {
			connection = Database.getConnection();
			statement = connection.createStatement();
			// Execute a statement
			String sql = String.format("DELETE from %s WHERE %s='%s'", tableName, Fields.BOOK_ID.getName(), book.getId());
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
			String sql = String.format("SELECT * FROM %s", tableName, Fields.BOOK_ID.getName());
			LOG.debug(sql);
			ResultSet result = statement.executeQuery(sql);
			while (result.next()) {
				ids.add(result.getString(Fields.BOOK_ID.getName()));
			}

		} finally {
			close(statement);
		}
		return ids;
	}

	public enum Fields {

		BOOK_ID("BookId", "VARCHAR", 9, 1), //
		ISBN("ISBN", "VARCHAR", 20, 2), //
		AUTHORS("Authors", "VARCHAR", 20, 4), //
		YEAR("Year", "VARCHAR", 40, 7), //
		TITLE("Title", "VARCHAR", 20, 4), //
		RATING("Rating", "VARCHAR", 10, 8), //
		RATINGSCOUNT("RatingsCount", "VARCHAR", 10, 5), //
		IMAGEURL("ImageUrl", "VARCHAR", 40, 7);

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
