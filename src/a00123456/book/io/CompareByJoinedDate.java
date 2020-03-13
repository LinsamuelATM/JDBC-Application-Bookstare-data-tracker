/**
 * Project: A00123456Lab4
 * File: CompareByJoinedDate.java
 */

package a00123456.book.io;

import java.util.Comparator;

import a00123456.book.data.Customer;

/**
 * @author Sam Cirka, A00123456
 *
 */
public class CompareByJoinedDate implements Comparator<Customer> {
	@Override
	public int compare(Customer customer1, Customer customer2) {
		return customer1.getJoinedDate().compareTo(customer2.getJoinedDate());
	}
}
