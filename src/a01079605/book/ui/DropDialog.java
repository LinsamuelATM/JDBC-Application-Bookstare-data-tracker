/**
 * Project:A01079605_assignmnet02
 * File: DropDialog.java
 * Date: Nov 18, 2019
 * Time: 4:54:42 PM
 */
package a01079605.book.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a01079605.book.db.BookDao;
import a01079605.book.db.CustomerDao;
import a01079605.book.db.PurchaseDao;
import net.miginfocom.swing.MigLayout;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class DropDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public DropDialog(CustomerDao customerDao, BookDao bookDao, PurchaseDao purchaseDao) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[51.00,grow][31.00,grow]"));
		{
			JLabel lblDoYouWant = new JLabel("Do you want to drop input data and exit system ? ");
			contentPanel.add(lblDoYouWant, "cell 1 0");
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							customerDao.drop();
							bookDao.drop();
							purchaseDao.drop();
							System.exit(0);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

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
