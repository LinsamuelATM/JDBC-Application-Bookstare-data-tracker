/**
 * Project:A01079605_assignmnet02
 * File: bookframe.java
 * Date: Nov 18, 2019
 * Time: 1:29:05 PM
 */
package a01079605.book.ui;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import a00123456.book.BookOptions;
import a00123456.book.data.Book;
import a00123456.book.data.Customer;
import a00123456.book.data.Purchase;
import a01079605.book.db.BookDao;
import a01079605.book.db.CustomerDao;
import a01079605.book.db.PurchaseDao;
import net.miginfocom.swing.MigLayout;

/**
 * @author Samuel Lin, A01079605
 *
 */
public class bookframe extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public bookframe(CustomerDao customerDao, BookDao bookDao, PurchaseDao purchaseDao) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmDrop = new JMenuItem("Drop");
		mntmDrop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DropDialog dropdia = new DropDialog(customerDao, bookDao, purchaseDao);
			}
		});
		mnNewMenu.add(mntmDrop);

		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmQuit);

		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);

		JMenuItem mntmCount = new JMenuItem("Count");
		mntmCount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(bookframe.this, "Total books" + "\n" + bookDao.getIds().size(), "Total Books",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mnBooks.add(mntmCount);

		JCheckBoxMenuItem chckbxmntmByAuthor = new JCheckBoxMenuItem("By Author");
		chckbxmntmByAuthor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookOptions.Value.BY_AUTHOR.setArg(true);
			}
		});
		mnBooks.add(chckbxmntmByAuthor);

		JCheckBoxMenuItem chckbxmntmDescending = new JCheckBoxMenuItem("Descending");
		chckbxmntmDescending.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookOptions.Value.DESCENDING.setArg(true);
			}
		});
		mnBooks.add(chckbxmntmDescending);

		JMenuItem mntmList = new JMenuItem("List");
		mntmList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Book> books = new ArrayList<Book>();
				try {
					List<String> ids = bookDao.getIds();
					for (String id : ids) {
						books.add(bookDao.getBook(id));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				BooksDialog bookdia = new BooksDialog(books);
			}
		});
		mnBooks.add(mntmList);

		JMenu mnCustomers = new JMenu("Customers");
		menuBar.add(mnCustomers);

		JMenuItem mntmCount_1 = new JMenuItem("Count");
		mntmCount_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(bookframe.this, "Total Customers" + "\n" + customerDao.getIds().size(), "Total Customers",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnCustomers.add(mntmCount_1);

		JCheckBoxMenuItem chckbxmntmByJoinDate = new JCheckBoxMenuItem("By Join Date");
		chckbxmntmByJoinDate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookOptions.Value.BY_JOIN_DATE.setArg(true);
			}
		});
		mnCustomers.add(chckbxmntmByJoinDate);

		JMenuItem mntmList_1 = new JMenuItem("List");
		mntmList_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Customer> customers = new ArrayList<Customer>();
				List<String> ids;
				try {
					ids = customerDao.getIds();
					for (String id : ids) {
						customers.add(customerDao.getCustomer(id));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				CustomerDialog custdia = new CustomerDialog(customers);

			}
		});
		mnCustomers.add(mntmList_1);

		JMenu mnPurchases = new JMenu("Purchases");
		menuBar.add(mnPurchases);

		JMenuItem mntmTotal = new JMenuItem("Total");
		mntmTotal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					List<String> ids = purchaseDao.getIds();
					List<Purchase> purchases = new ArrayList<Purchase>();
					for (String id : ids) {
						purchases.add(purchaseDao.getPurchase(id));
					}
					float count = 0;

					for (Purchase p : purchases) {
						count = count + p.getPrice();
					}
					JOptionPane.showMessageDialog(bookframe.this, "Total Purchases" + "\n" + count + "dollars", "Total Purchases",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (HeadlessException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnPurchases.add(mntmTotal);

		JCheckBoxMenuItem chckbxmntmByLastName = new JCheckBoxMenuItem("By Last Name");
		chckbxmntmByLastName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BookOptions.Value.BY_LASTNAME.setArg(true);
			}
		});
		mnPurchases.add(chckbxmntmByLastName);

		JCheckBoxMenuItem chckbxmntmByTitle = new JCheckBoxMenuItem("By Title");
		chckbxmntmByTitle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookOptions.Value.BY_TITLE.setArg(true);
			}
		});
		mnPurchases.add(chckbxmntmByTitle);

		JCheckBoxMenuItem chckbxmntmDescending_1 = new JCheckBoxMenuItem("Descending");
		chckbxmntmDescending_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookOptions.Value.DESCENDING.setArg(true);
			}
		});
		mnPurchases.add(chckbxmntmDescending_1);

		JMenuItem mntmFilterByCustomer = new JMenuItem("Filter by Customer ID");
		mntmFilterByCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BookOptions.Value.CUSTOMER_ID.setArg(true);
			}
		});
		mnPurchases.add(mntmFilterByCustomer);

		JMenuItem mntmList_2 = new JMenuItem("List");
		mntmList_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<String> ids;
				List<Purchase> purchases = new ArrayList<Purchase>();
				try {
					ids = purchaseDao.getIds();
					for (String id : ids) {
						try {
							purchases.add(purchaseDao.getPurchase(id));
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				PurchaseDialog purchasedia = new PurchaseDialog(purchases);
			}
		});
		mnPurchases.add(mntmList_2);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(bookframe.this, "Assignment 2" + "\nBy Samuel Lin A01079605", "About Assignment 2",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[]"));
	}

}
