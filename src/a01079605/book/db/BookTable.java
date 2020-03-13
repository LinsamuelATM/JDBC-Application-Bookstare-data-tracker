/**
 * Project:A01079605_assignment2
 * File: BookTable.java
 * Date: Nov 18, 2019
 * Time: 11:08:46 AM
 */
package a01079605.book.db;

/**
 * @author Samuel Lin, A01079605
 *
 */
public enum BookTable {
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

	BookTable(String name, String type, int length, int column) {
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
