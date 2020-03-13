/**
 * Project:A01079605_assignmnet02
 * File: PurchaseDialog.java
 * Date: Nov 18, 2019
 * Time: 4:55:30 PM
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
import a00123456.book.data.Purchase;
import a00123456.book.io.CompareByTitle;
import a00123456.book.io.CompareByTitleDesc;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class PurchaseDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public PurchaseDialog(List<Purchase> purchases) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		if (BookOptions.Value.BY_TITLE.isHasArg()) {
			Collections.sort(purchases, new CompareByTitle());
			if (BookOptions.Value.DESCENDING.isHasArg()) {
				Collections.sort(purchases, new CompareByTitleDesc());
			}
		}

		JList list = new JList(purchases.toArray());
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
