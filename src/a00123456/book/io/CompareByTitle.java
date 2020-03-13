/**
 * Project:A01079605_assignment01
 * File: CompareByTitle.java
 * Date: Oct 20, 2019
 * Time: 11:01:34 PM
 */
package a00123456.book.io;

import java.util.Comparator;

import a00123456.book.data.Purchase;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class CompareByTitle implements Comparator<Purchase> {
	@Override
	public int compare(Purchase p1, Purchase p2) {
		return Long.valueOf(p1.getBookId()).compareTo(Long.valueOf(p2.getBookId()));
	}

}
