/**
 * Project:A01079605_assignmnet02
 * File: BooksDialog.java
 * Date: Nov 18, 2019
 * Time: 4:54:59 PM
 */
package a01079605.book.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import a00123456.book.BookOptions;
import a00123456.book.data.Book;
import a00123456.book.io.CompareByAuthor;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class BooksDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public BooksDialog(List<Book> books) {
		setBounds(100, 100, 1300, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		if (BookOptions.Value.BY_AUTHOR.isHasArg()) {
			Collections.sort(books, new CompareByAuthor());
			if (BookOptions.Value.DESCENDING.isHasArg()) {
				Collections.reverse(books);
			}
		}

		JList list = new JList(books.toArray());
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);
		contentPanel.add(scrollPane);

		{
			JPanel buttonPane = new JPanel();

			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BookOptions.Value.BY_AUTHOR.setArg(false);
						BookOptions.Value.DESCENDING.setArg(false);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BookOptions.Value.BY_AUTHOR.setArg(false);
						BookOptions.Value.DESCENDING.setArg(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}

}
