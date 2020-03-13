/**
 * Project:A01079605_assignment01
 * File: CompareByAuthor.java
 * Date: Oct 20, 2019
 * Time: 10:00:48 PM
 */
package a00123456.book.io;

import java.util.Comparator;

import a00123456.book.data.Book;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class CompareByAuthor implements Comparator<Book> {
	@Override
	public int compare(Book b1, Book b2) {
		return b1.getAuthors().compareTo(b2.getAuthors());

	}

}
