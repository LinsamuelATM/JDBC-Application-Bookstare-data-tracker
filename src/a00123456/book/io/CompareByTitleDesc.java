/**
 * Project:A01079605_assignment01
 * File: CompareByTitleDesc.java
 * Date: Oct 21, 2019
 * Time: 12:39:04 AM
 */
package a00123456.book.io;

import java.util.Comparator;

import a00123456.book.data.Purchase;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class CompareByTitleDesc implements Comparator<Purchase> {
	@Override
	public int compare(Purchase p1, Purchase p2) {
		return Long.valueOf(p2.getBookId()).compareTo(Long.valueOf(p1.getBookId()));
	}

}
