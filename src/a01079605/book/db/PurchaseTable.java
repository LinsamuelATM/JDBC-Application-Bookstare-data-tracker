/**
 * Project:A01079605_assignment2
 * File: PurchaseTable.java
 * Date: Nov 18, 2019
 * Time: 11:09:12 AM
 */
package a01079605.book.db;

/**
 * @author Samuel Lin, A01079605
 *
 */
public enum PurchaseTable {

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

	PurchaseTable(String name, String type, int length, int column) {
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
