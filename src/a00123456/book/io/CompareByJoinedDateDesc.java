/**
 * Project:A01079605_assignment01
 * File: CompareByJoinedDateDesc.java
 * Date: Oct 20, 2019
 * Time: 9:39:06 PM
 */
package a00123456.book.io;

import java.util.Comparator;

import a00123456.book.data.Customer;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class CompareByJoinedDateDesc implements Comparator<Customer> {
	@Override
	public int compare(Customer customer1, Customer customer2) {
		return customer2.getJoinedDate().compareTo(customer1.getJoinedDate());
	}
}
