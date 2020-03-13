/**
 * Project:A01079605_assignmnet02
 * File: CustomerDetailDialog.java
 * Date: Nov 18, 2019
 * Time: 10:04:35 PM
 */
package a01079605.book.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import a00123456.book.data.Customer;
import a00123456.book.data.util.Common;
import net.miginfocom.swing.MigLayout;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class CustomerDetailDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */

	/*
	 * public static void main(String[] args) {
	 * try {
	 * Customerdialog dialog = new Customerdialog();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true);
	 * } catch (Exception e) {
	 * e.printStackTrace();
	 * }
	 * }
	 */

	/**
	 * Create the dialog.
	 */
	public CustomerDetailDialog(Customer customer) {

		String date = Common.DATE_FORMAT.format(customer.getJoinedDate());
		String ID = String.valueOf(customer.getId());
		String first_Name = customer.getFirstName();
		String last_Name = customer.getLastName();
		String street = customer.getStreet();
		String city = customer.getCity();
		String postal_Code = customer.getPostalCode();
		String phone = customer.getPhone();
		String email = customer.getEmailAddress();

		setBounds(100, 100, 500, 600);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][][][][][][][][][][][]"));
		{
			JLabel lblId = new JLabel("               ID");
			contentPanel.add(lblId, "cell 3 0,alignx trailing");
		}
		{
			textField = new JTextField();
			textField.setText(ID);
			textField.setEditable(false);
			contentPanel.add(textField, "cell 4 0,growx");
			textField.setColumns(10);
		}
		{
			JLabel lblFirstName = new JLabel("  First Name");
			contentPanel.add(lblFirstName, "cell 3 2,alignx trailing");
		}
		{
			textField_1 = new JTextField();
			textField_1.setText(first_Name);
			textField_1.setEditable(false);
			contentPanel.add(textField_1, "cell 4 2,growx");
			textField_1.setColumns(10);
		}
		{
			JLabel lblLastName = new JLabel("  Last Name");
			contentPanel.add(lblLastName, "cell 3 4,alignx trailing");
		}
		{
			textField_2 = new JTextField();
			textField_2.setText(last_Name);
			textField_2.setEditable(false);
			contentPanel.add(textField_2, "cell 4 4,growx");
			textField_2.setColumns(10);
		}
		{
			JLabel lblStreet = new JLabel("         Street");
			contentPanel.add(lblStreet, "cell 3 6,alignx trailing");
		}
		{
			textField_3 = new JTextField();
			textField_3.setText(street);
			textField_3.setEditable(false);
			contentPanel.add(textField_3, "cell 4 6,growx");
			textField_3.setColumns(10);
		}
		{
			JLabel lblCity = new JLabel("            City");
			contentPanel.add(lblCity, "cell 3 8,alignx trailing");
		}
		{
			textField_4 = new JTextField();
			textField_4.setText(city);
			textField_4.setEditable(false);
			contentPanel.add(textField_4, "cell 4 8,growx");
			textField_4.setColumns(10);
		}
		{
			JLabel lblPostalCode = new JLabel("Postal Code");
			contentPanel.add(lblPostalCode, "cell 3 10,alignx trailing");
		}
		{
			textField_5 = new JTextField();
			textField_5.setText(postal_Code);
			textField_5.setEditable(false);
			contentPanel.add(textField_5, "cell 4 10,growx");
			textField_5.setColumns(10);
		}
		{
			JLabel lblPhone = new JLabel("         Phone");
			contentPanel.add(lblPhone, "cell 3 12,alignx trailing");
		}
		{
			textField_6 = new JTextField();
			textField_6.setText(phone);
			textField_6.setEditable(false);
			contentPanel.add(textField_6, "cell 4 12,growx");
			textField_6.setColumns(10);
		}
		{
			JLabel lblEmail = new JLabel("          Email");
			contentPanel.add(lblEmail, "cell 3 14,alignx trailing");
		}
		{
			textField_7 = new JTextField();
			textField_7.setText(email);
			textField_7.setEditable(false);
			contentPanel.add(textField_7, "cell 4 14,growx");
			textField_7.setColumns(10);
		}
		{
			JLabel lblJoinedDate = new JLabel("Joined Date");
			contentPanel.add(lblJoinedDate, "cell 3 16,alignx trailing");
		}
		{
			textField_8 = new JTextField();
			textField_8.setText(date);
			textField_8.setEditable(false);
			contentPanel.add(textField_8, "cell 4 16,growx");
			textField_8.setColumns(10);
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
