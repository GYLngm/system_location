package org.ls.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.ls.dao.AutoDao;
import org.ls.dao.EmprunteurDao;
import org.ls.dao.MotoDao;
import org.ls.dao.OrderDao;
import org.ls.entity.Auto;
import org.ls.entity.Emprunteur;
import org.ls.entity.Moto;
import org.ls.entity.Exemplaire;
import org.ls.tools.Constants;

import sun.swing.table.DefaultTableCellHeaderRenderer;

/**
 * 租客进入的主界面  la fenêtre principal de emprunteur 
 */
public class RenterMainUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 管理汽车信息面板   la fenêtre de management de véhicule 
	 */
	private JPanel panelAutos;
	private List<Auto> allAutos;

	/**
	 * 管理摩托车信息面板    la  fenêtre de management de  moto 
	 */
	private JPanel panelMotos;
	private List<Moto> allMotos;

	/**
	 * 订单信息面板  la fenêtre de la commande
	 */
	public static JPanel panelOrder;
	static List<Exemplaire> allOrders;

	/**
	 * 个人信息面板  l'écran de information  personnelle 
	 */
	private JPanel panelPersonalInfo;
	private JTextField jTextFieldId;
	private JTextField jTextFieldPassword;
	private JTextField jTextFamilyName;
	private JTextField jTextLastName;
	private JTextField jTextHouseNum;
	private JTextField jTextStreet;
	private JTextField jTextPostcode;
	private JTextField jTextCity;
	private JButton btnReset;
	private JButton btnReg;
	private Emprunteur emprunteur;
	private String oldId;

	/**
	 * 选项卡
	 */
	private JTabbedPane tabs;

	/**
	 * 在构造函数中进行初始化   initialisation 
	 */
	public RenterMainUI() {
		// ****设置图标
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File("images/icon.jpg"));
			this.setIconImage(icon);
		} catch (Exception e) {
		}

		tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

		panelAutos = new JPanel();
		initPanelAutos(panelAutos);
		tabs.addTab("   Auto a louer   ", panelAutos);

		panelMotos = new JPanel();
		initPanelMotos(panelMotos);
		tabs.addTab("   Moto a louer  ", panelMotos);

		panelOrder = new JPanel();
		initPanelOrder(panelOrder);
		tabs.addTab("   Ma commande ", panelOrder);

		panelPersonalInfo = new JPanel();
		initPanelPersonalInfo(panelPersonalInfo);
		tabs.addTab("  Info personnelle   ", panelPersonalInfo);

		tabs.setSize(50, 30);
		Container contentPane = getContentPane();
		contentPane.add(tabs);

		int[] locate = org.ls.tools.ScreenUtils.getCenterLocation(
				Constants.WIDTH, Constants.HEIGHT);
		this.setSize(locate[2], locate[3]);
		this.setLocation(locate[0], locate[1]);
		this.setTitle("Location de Vehicule");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);// 无法放大窗体
	}

	/**
	 * 个人信息面板  panneau information 
	 * 
	 * @param panelPersonalInfo
	 */
	private void initPanelPersonalInfo(JPanel panelPersonalInfo) {
		panelPersonalInfo.setLayout(null);

		emprunteur = (Emprunteur) LoginUI.currUser;
		oldId = emprunteur.getId();

		JLabel jLabelUsername = new JLabel("ID:");
		jLabelUsername.setBounds(150, 20, 70, 30);
		panelPersonalInfo.add(jLabelUsername);

		jTextFieldId = new JTextField();
		jTextFieldId.setBounds(230, 25, 150, 25);
		panelPersonalInfo.add(jTextFieldId);
		jTextFieldId.setText(emprunteur.getId());

		JLabel jLabelPwd = new JLabel("Password:");
		jLabelPwd.setBounds(150, 50, 70, 30);
		panelPersonalInfo.add(jLabelPwd);

		jTextFieldPassword = new JTextField();
		jTextFieldPassword.setBounds(230, 55, 150, 25);
		panelPersonalInfo.add(jTextFieldPassword);
		jTextFieldPassword.setText(emprunteur.getPassword());

		JLabel jLabelFamilyName = new JLabel("Nom:");
		jLabelFamilyName.setBounds(150, 80, 70, 30);
		panelPersonalInfo.add(jLabelFamilyName);

		jTextFamilyName = new JTextField();
		jTextFamilyName.setBounds(230, 85, 150, 25);
		panelPersonalInfo.add(jTextFamilyName);
		jTextFamilyName.setText(emprunteur.getFamilyName());

		JLabel jLabelLastName = new JLabel("Prenom:");
		jLabelLastName.setBounds(150, 110, 70, 30);
		panelPersonalInfo.add(jLabelLastName);

		jTextLastName = new JTextField();
		jTextLastName.setBounds(230, 115, 150, 25);
		panelPersonalInfo.add(jTextLastName);
		jTextLastName.setText(emprunteur.getLastName());

		JLabel jLabelHouseNum = new JLabel("Num:");
		jLabelHouseNum.setBounds(150, 140, 70, 30);
		panelPersonalInfo.add(jLabelHouseNum);

		jTextHouseNum = new JTextField();
		jTextHouseNum.setBounds(230, 145, 150, 25);
		panelPersonalInfo.add(jTextHouseNum);
		jTextHouseNum.setText(emprunteur.getAddress().getHouseNum());

		JLabel jLabelStreet = new JLabel("Rue:");
		jLabelStreet.setBounds(150, 170, 70, 30);
		panelPersonalInfo.add(jLabelStreet);

		jTextStreet = new JTextField();
		jTextStreet.setBounds(230, 175, 150, 25);
		panelPersonalInfo.add(jTextStreet);
		jTextStreet.setText(emprunteur.getAddress().getStreet());

		JLabel jLabelPostcode = new JLabel("Codepostal:");
		jLabelPostcode.setBounds(150, 200, 70, 30);
		panelPersonalInfo.add(jLabelPostcode);

		jTextPostcode = new JTextField();
		jTextPostcode.setBounds(230, 205, 150, 25);
		panelPersonalInfo.add(jTextPostcode);
		jTextPostcode.setText(emprunteur.getAddress().getPostcode());

		JLabel jLabelCity = new JLabel("Ville:");
		jLabelCity.setBounds(150, 230, 70, 30);
		panelPersonalInfo.add(jLabelCity);

		jTextCity = new JTextField();
		// jTextCity.setBounds(50, 310, 70, 30);
		jTextCity.setBounds(230, 235, 150, 25);
		panelPersonalInfo.add(jTextCity);
		jTextCity.setText(emprunteur.getAddress().getCity());

		btnReg = new JButton("Confirm");
		btnReset = new JButton("Reset");

		btnReg.setBounds(160, 360, 100, 30);
		btnReset.setBounds(280, 360, 100, 30);

		btnReg.addActionListener(this);
		btnReset.addActionListener(this);
		panelPersonalInfo.add(btnReg);
		panelPersonalInfo.add(btnReset);

	}

	/**
	 * @param panelOrder
	 */
	public static void initPanelOrder(JPanel panelOrder) {
		panelOrder.setLayout(null);
		allOrders = OrderDao.getInstance().getAll();
		createOrderTable(allOrders, panelOrder);
	}

	/**
	 * @param all
	 * @param panelOrder2
	 */
	private static void createOrderTable(List<Exemplaire> all, JPanel panelOrder) {

		/**
		 * 表格的列
		 */
		String[] columnsTitle = { "ID", "code", "kilomètre ", "prix", "datecommence", "datefini", "opération " };
		DefaultTableModel model = new DefaultTableModel(columnsTitle, all
				.size());
		JTable orderTable = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jsp = new JScrollPane(orderTable);
		setAttributeOfOrderTable(orderTable);
		setContentOfOrderTable(orderTable, all);
		addMouseOrderListener(orderTable);
		jsp.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		jsp.addMouseMotionListener(new MyMouseMotionAdapter(jsp));
		panelOrder.add(jsp);

	}

	/**
	 * 鼠标点击监听
	 * 
	 * @param jTable
	 */
	private static void addMouseOrderListener(final JTable jTable) {

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = jTable.getSelectedRow();
				int column = jTable.getSelectedColumn();
				String id = jTable.getValueAt(row, 0).toString();
				String str = jTable.getValueAt(row, column).toString();
				if (str.equals("Voir")) {
					for (Exemplaire order : allOrders) {
						if (("" + order.getId()).equals(id)) {
							if (order.getType() == Constants.AUTO) {
								new CheckAutoOrderUI(order).setVisible(true);
							} else {
								new CheckMotoOrderUI(order).setVisible(true);
							}
						}
					}

				}
			}

		});
	}

	/**
	 * @param jTable
	 * @param all
	 */
	private static void setContentOfOrderTable(JTable jTable, List<Exemplaire> all) {
		// "ID", "编号", "公里数", "价格", "开始日期", "截止日期"

		int i = 0;
		for (Exemplaire item : all) {
			jTable.setValueAt(item.getId(), i, 0);
			jTable.setValueAt(item.getBianHao(), i, 1);
			jTable.setValueAt(item.getKm() + "", i, 2);
			jTable.setValueAt(item.getPrice() + "", i, 3);
			jTable.setValueAt(item.getStartTime(), i, 4);
			jTable.setValueAt(item.getEndTime(), i, 5);
			jTable.setValueAt("Voir", i, 6);
			i++;
		}

	}

	/**
	 * @param orderTable
	 */
	private static void setAttributeOfOrderTable(JTable jTable) {

		JTableHeader jTableHeader = jTable.getTableHeader();
		jTableHeader.setReorderingAllowed(false);
		jTableHeader.setPreferredSize(new Dimension(0, 25));
		jTableHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
		// "ID", "编号", "公里数", "价格", "开始日期", "截止日期", "租客"
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.getColumn("ID").setCellRenderer(render);
		jTable.getColumn("code").setCellRenderer(render);
		jTable.getColumn("kilomètre ").setCellRenderer(render);
		jTable.getColumn("prix").setCellRenderer(render);
		jTable.getColumn("datecommence").setCellRenderer(render);
		jTable.getColumn("datefini").setCellRenderer(render);
		jTable.getColumn("opération ").setCellRenderer(render);

		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jTable.getTableHeader().setDefaultRenderer(hr);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable
				.getColumnModel();
		/**
		 * 列宽度
		 */
		dcm.getColumn(0).setPreferredWidth(120);
		dcm.getColumn(1).setPreferredWidth(150);
		dcm.getColumn(2).setPreferredWidth(150);
		dcm.getColumn(3).setPreferredWidth(150);
		dcm.getColumn(4).setPreferredWidth(150);
		dcm.getColumn(5).setPreferredWidth(150);
		dcm.getColumn(6).setPreferredWidth(80);

		/**
		 * 行高度
		 */
		jTable.setRowHeight(30);
		jTable.setColumnSelectionAllowed(false);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	/**
	 * 摩托车信息
	 * 
	 * @param panelMotos2
	 */
	private void initPanelMotos(JPanel panelMotos2) {

		panelMotos.setLayout(null);
		allMotos = MotoDao.getInstance().getAll();
		createMotoTable(allMotos, panelMotos);

	}

	/**
	 * @param all
	 * @param panelMotos
	 */
	private void createMotoTable(List<Moto> all, JPanel panelMotos) {

		/**
		 * 表格的列
		 */
		String[] columnsTitle = { "ID", "code", "cylindrée ", "kilomètre ", "prix", "opération " };
		DefaultTableModel model = new DefaultTableModel(columnsTitle, all
				.size());
		JTable motoTable = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jsp = new JScrollPane(motoTable);
		setAttributeOfMotoTable(motoTable);
		setContentOfMotoTable(motoTable, all);
		addMouseMotoListener(motoTable);
		jsp.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		jsp.addMouseMotionListener(new MyMouseMotionAdapter(jsp));
		panelMotos.add(jsp);
	}

	/**
	 * 鼠标点击监听
	 * 
	 * @param jTable
	 */
	private void addMouseMotoListener(final JTable jTable) {

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = jTable.getSelectedRow();
				int column = jTable.getSelectedColumn();
				String id = jTable.getValueAt(row, 0).toString();
				String str = jTable.getValueAt(row, column).toString();
				if (str.equals("Fait la commande")) {
					for (Moto moto : allMotos) {
						if (("" + moto.getId()).equals(id)) {
							new AddMotoOrderUI(moto).setVisible(true);
							break;
						}
					}

				}
			}

		});
	}

	/**
	 * @param motoTable
	 * @param all
	 */
	private void setContentOfMotoTable(JTable jTable, List<Moto> all) {

		// "ID", "编号", "型号", "公里数", "价格", "操作"
		int i = 0;
		for (Moto item : all) {
			jTable.setValueAt(item.getId(), i, 0);
			jTable.setValueAt(item.getBianHao(), i, 1);
			jTable.setValueAt(item.getHorsepower() + "", i, 2);
			jTable.setValueAt(item.getKm() + "", i, 3);
			jTable.setValueAt(item.getPrice() + "", i, 4);
			jTable.setValueAt("Fait la commande", i, 5);
			i++;
		}

	}

	/**
	 * @param motoTable
	 */
	private void setAttributeOfMotoTable(JTable jTable) {

		JTableHeader jTableHeader = jTable.getTableHeader();
		jTableHeader.setReorderingAllowed(false);
		jTableHeader.setPreferredSize(new Dimension(0, 25));
		jTableHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
		// "ID", "编号", "型号", "公里数", "价格", "操作1", "操作2"
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.getColumn("ID").setCellRenderer(render);
		jTable.getColumn("code").setCellRenderer(render);
		jTable.getColumn("cylindrée ").setCellRenderer(render);
		jTable.getColumn("kilomètre ").setCellRenderer(render);
		jTable.getColumn("prix").setCellRenderer(render);
		jTable.getColumn("opération ").setCellRenderer(render);

		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jTable.getTableHeader().setDefaultRenderer(hr);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable
				.getColumnModel();
		/**
		 * 列宽度
		 */
		dcm.getColumn(0).setPreferredWidth(150);
		dcm.getColumn(1).setPreferredWidth(175);
		dcm.getColumn(2).setPreferredWidth(175);
		dcm.getColumn(3).setPreferredWidth(150);
		dcm.getColumn(4).setPreferredWidth(150);
		dcm.getColumn(5).setPreferredWidth(150);

		/**
		 * 行高度
		 */
		jTable.setRowHeight(30);
		jTable.setColumnSelectionAllowed(false);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	/**
	 * 汽车信息
	 * 
	 * @param panelAutos2
	 */
	private void initPanelAutos(JPanel panelAutos) {
		panelAutos.setLayout(null);
		allAutos = AutoDao.getInstance().getAll();
		createAutoTable(allAutos, panelAutos);
	}

	/**
	 * @param all
	 * @param panelAutos
	 */
	private void createAutoTable(List<Auto> all, JPanel panelAutos) {
		/**
		 * 表格的列
		 */
		String[] columnsTitle = { "ID", "code", "modèle ", "kilomètre ", "prix", "opération " };
		DefaultTableModel model = new DefaultTableModel(columnsTitle, all
				.size());
		JTable autoTable = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jsp = new JScrollPane(autoTable);
		setAttributeOfAutoTable(autoTable);
		setContentOfAutoTable(autoTable, all);
		addMouseAutoListener(autoTable);
		jsp.setBounds(0, 0, Constants.WIDTH, Constants.HEIGHT);
		jsp.addMouseMotionListener(new MyMouseMotionAdapter(jsp));
		panelAutos.add(jsp);

	}

	/**
	 * 鼠标点击监听
	 * 
	 * @param jTable
	 */
	private void addMouseAutoListener(final JTable jTable) {

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = jTable.getSelectedRow();
				int column = jTable.getSelectedColumn();
				String id = jTable.getValueAt(row, 0).toString();
				String str = jTable.getValueAt(row, column).toString();
				if (str.equals("Fait la commande")) {
					for (Auto auto : allAutos) {
						if (("" + auto.getId()).equals(id)) {
							new AddAutoOrderUI(auto).setVisible(true);
							break;
						}
					}
				}

			}

		});
	}

	/**
	 * @param autoTable
	 * @param all
	 */
	private void setContentOfAutoTable(JTable jTable, List<Auto> all) {

		// "ID", "编号", "型号", "公里数", "价格", "操作"
		int i = 0;
		for (Auto item : all) {
			jTable.setValueAt(item.getId(), i, 0);
			jTable.setValueAt(item.getBianHao(), i, 1);
			jTable.setValueAt(item.getXingHao(), i, 2);
			jTable.setValueAt(item.getKm() + "", i, 3);
			jTable.setValueAt(item.getPrice() + "", i, 4);
			jTable.setValueAt("Fait la commande", i, 5);
			i++;
		}

	}

	/**
	 * @param autoTable
	 */
	private void setAttributeOfAutoTable(JTable jTable) {

		JTableHeader jTableHeader = jTable.getTableHeader();
		jTableHeader.setReorderingAllowed(false);
		jTableHeader.setPreferredSize(new Dimension(0, 25));
		jTableHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
		// "ID", "编号", "型号", "公里数", "价格", "操作1", "操作2"
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.getColumn("ID").setCellRenderer(render);
		jTable.getColumn("code").setCellRenderer(render);
		jTable.getColumn("modèle ").setCellRenderer(render);
		jTable.getColumn("kilomètre ").setCellRenderer(render);
		jTable.getColumn("prix").setCellRenderer(render);
		jTable.getColumn("opération ").setCellRenderer(render);

		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jTable.getTableHeader().setDefaultRenderer(hr);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable
				.getColumnModel();
		/**
		 * 列宽度
		 */
		dcm.getColumn(0).setPreferredWidth(150);
		dcm.getColumn(1).setPreferredWidth(175);
		dcm.getColumn(2).setPreferredWidth(175);
		dcm.getColumn(3).setPreferredWidth(150);
		dcm.getColumn(4).setPreferredWidth(150);
		dcm.getColumn(5).setPreferredWidth(150);

		/**
		 * 行高度
		 */
		jTable.setRowHeight(30);
		jTable.setColumnSelectionAllowed(false);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	/**
	 * 点击按钮监听
	 * 
	 * @param e
	 *            the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReg) {
			/**
			 * 开启线程，不会有卡顿的现象
			 */
			new Thread(new Runnable() {

				@Override
				public void run() {

					if (jTextFieldId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "tapez le ID!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFieldPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "tapez le mot de passé ", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFamilyName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le nom !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextLastName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le prénom !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextHouseNum.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le numéro !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextStreet.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez la rue !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextPostcode.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez la postecode!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextCity.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez la ville !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					Emprunteur newEmp = new Emprunteur(jTextFieldId.getText()
							.toString(), jTextFieldPassword.getText()
							.toString(), jTextFamilyName.getText().toString(),
							jTextLastName.getText().toString(), jTextHouseNum
									.getText().toString(), jTextStreet
									.getText().toString(), jTextPostcode
									.getText().toString(), jTextCity.getText()
									.toString());
					if (EmprunteurDao.getInstance().update(newEmp, oldId)) {
						JOptionPane.showMessageDialog(null, "Reussi de modifier !");
						// setVisible(false);
						LoginUI.currUser = newEmp;
						return;
					} else {
						JOptionPane.showMessageDialog(null, "Echec de modifier !");
						return;
					}

				}
			}).start();

		} else if (e.getSource() == btnReset) {

			jTextFieldId.setText("");
			jTextFieldPassword.setText("");
			jTextFamilyName.setText("");
			jTextLastName.setText("");
			jTextHouseNum.setText("");
			jTextStreet.setText("");
			jTextPostcode.setText("");
			jTextCity.setText("");

		}
	}
}
