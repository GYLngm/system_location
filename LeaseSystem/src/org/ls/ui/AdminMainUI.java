package org.ls.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
 * 管理员进入的主界面   la fenetre principle de administrateur
 */
public class AdminMainUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 管理租客信息面板  le panel de  menegement de emprunteur
	 */
	public static JPanel panelRenters;
	private static JButton addRenter;
	static List<Emprunteur> allEmprunteurs;

	/**
	 * 管理汽车信息面板
	 */
	public static JPanel panelAutos;
	private static JButton addAoto;
	static List<Auto> allAutos;

	/**
	 * 管理摩托车信息面板  menegement de moto
	 */
	public static JPanel panelMotos;
	private static JButton addMoto;
	static List<Moto> allMotos;

	/**
	 * 订单信息面板
	 */
	private JPanel panelOrder;
	private List<Exemplaire> allOrders;

	/**
	 * 库存信息面板
	 */
	private JPanel panelStore;
	private JScrollPane jsp;// 滚动条——内容过多的话可以上下拖拉
	private JTextArea ja;// 显示查询结果

	/**
	 * 选项卡
	 */
	private JTabbedPane tabs;

	/**
	 * 在构造函数中进行初始化
	 */
	public AdminMainUI() {

		// ****设置图标
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File("images/icon.jpg"));
			this.setIconImage(icon);
		} catch (Exception e) {
		}

		tabs = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
		panelRenters = new JPanel();
		initPanelRenters(panelRenters);
		tabs.addTab("  Emprunteurs    ", panelRenters);

		panelAutos = new JPanel();
		initPanelAutos(panelAutos);
		tabs.addTab("   Véhicules   ", panelAutos);

		panelMotos = new JPanel();
		initPanelMotos(panelMotos);
		tabs.addTab("   Motos   ", panelMotos);

		panelOrder = new JPanel();
		initPanelOrder(panelOrder);
		tabs.addTab("   Commande   ", panelOrder);

		panelStore = new JPanel();
		initPanelStore(panelStore);
		tabs.addTab("   Flotte   ", panelStore);

		tabs.setSize(50, 30);
		Container contentPane = getContentPane();
		contentPane.add(tabs);

		int[] locate = org.ls.tools.ScreenUtils.getCenterLocation(
				Constants.WIDTH, Constants.HEIGHT);
		this.setSize(locate[2], locate[3]);
		this.setLocation(locate[0], locate[1]);
		this.setTitle("location véhicules ");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);// 无法放大窗体
	}

	/**
	 * 库存信息
	 * 
	 * @param panelStore2
	 */
	private void initPanelStore(JPanel panelStore2) { 
		// 获取库存信息    obtenir les information des existences  

		// 显示摩托车的库存
		StringBuffer stringBuffer = new StringBuffer();
		if (allMotos.size() != 0) {
			stringBuffer.append(allMotos.size() + " MOTOS" + "\r\n");
			for (Moto moto : allMotos) {
				stringBuffer.append(moto.toString() + "\r\n");
			}
		}

		stringBuffer.append("\r\n");

		// 显示汽车的库存
		HashMap<String, ArrayList<Auto>> autoMap = new HashMap<String, ArrayList<Auto>>();
		if (allAutos.size() != 0) {
			for (Auto auto : allAutos) {

				if (autoMap.containsKey(auto.getXingHao())) {
					ArrayList<Auto> arrayList = autoMap.get(auto.getXingHao());
					arrayList.add(auto);
				} else {
					ArrayList<Auto> arrayList = new ArrayList<Auto>();
					arrayList.add(auto);
					autoMap.put(auto.getXingHao(), arrayList);
				}

			}

			// 遍历autoMap    explorer  les  tous les motos
			Iterator<Entry<String, ArrayList<Auto>>> iterator = autoMap
					.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, ArrayList<Auto>> next = iterator.next();
				stringBuffer.append(next.getValue().size() + " AUTOS"
						+ " " + next.getKey() + "\r\n");
				for (Auto auto : next.getValue()) {
					stringBuffer.append(auto.toString() + "\r\n");
				}
				stringBuffer.append("\r\n");
			}

		}

		panelStore2.setLayout(new BorderLayout());
		ja = new JTextArea();
		ja.setText(stringBuffer.toString());
		jsp = new JScrollPane(ja);
		panelStore2.add(jsp);
	}

	/**
	 * @param panelOrder
	 */
	private void initPanelOrder(JPanel panelOrder) {
		panelOrder.setLayout(null);
		allOrders = OrderDao.getInstance().getAll();
		createOrderTable(allOrders, panelOrder);
	}

	/**
	 * @param all
	 * @param panelOrder2
	 */
	private void createOrderTable(List<Exemplaire> all, JPanel panelOrder) {

		/**
		 * 表格的列
		 */
		String[] columnsTitle = { "ID", "code", "kilomètre ", "prix", "datecommence", "datefini",
				"emprunteur", "opération" };
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
	private void addMouseOrderListener(final JTable jTable) {

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = jTable.getSelectedRow();
				int column = jTable.getSelectedColumn();
				String id = jTable.getValueAt(row, 0).toString();
				String str = jTable.getValueAt(row, column).toString();
				if (str.equals("voir")) {
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
		// "ID", "编号", "公里数", "价格", "开始日期", "截止日期", "租客"

		int i = 0;
		for (Exemplaire item : all) {
			jTable.setValueAt(item.getId(), i, 0);
			jTable.setValueAt(item.getBianHao(), i, 1);
			jTable.setValueAt(item.getKm() + "", i, 2);
			jTable.setValueAt(item.getPrice() + "", i, 3);
			jTable.setValueAt(item.getStartTime(), i, 4);
			jTable.setValueAt(item.getEndTime(), i, 5);
			jTable.setValueAt(item.getRenterName(), i, 6);
			jTable.setValueAt("voir", i, 7);
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
		jTable.getColumn("emprunteur").setCellRenderer(render);
		jTable.getColumn("opération").setCellRenderer(render);

		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jTable.getTableHeader().setDefaultRenderer(hr);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable
				.getColumnModel();
		/**
		 * 列宽度
		 */
		dcm.getColumn(0).setPreferredWidth(120);
		dcm.getColumn(1).setPreferredWidth(120);
		dcm.getColumn(2).setPreferredWidth(120);
		dcm.getColumn(3).setPreferredWidth(120);
		dcm.getColumn(4).setPreferredWidth(120);
		dcm.getColumn(5).setPreferredWidth(120);
		dcm.getColumn(6).setPreferredWidth(120);
		dcm.getColumn(7).setPreferredWidth(90);

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
	public static void initPanelMotos(JPanel panelMotos2) {

		panelMotos.setLayout(null);
		addMoto = new JButton("ajoute moto");
		addMoto.setBounds(820, 10, 100, 30);
		addMoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddMotoUI().setVisible(true);

			}
		});
		panelMotos.add(addMoto);
		allMotos = MotoDao.getInstance().getAll();
		createMotoTable(allMotos, panelMotos);

	}

	/**
	 * @param all
	 * @param panelMotos
	 */
	private static void createMotoTable(List<Moto> all, JPanel panelMotos) {

		/**
		 * 表格的列
		 */
		String[] columnsTitle = { "ID", "code", "cylindrée", "kilomètre ", "prix", "opération1", "opération2" };
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
		jsp.setBounds(0, 50, Constants.WIDTH, Constants.HEIGHT - 50);
		jsp.addMouseMotionListener(new MyMouseMotionAdapter(jsp));
		panelMotos.add(jsp);
	}

	/**
	 * 鼠标点击监听
	 * 
	 * @param jTable
	 */
	private static void addMouseMotoListener(final JTable jTable) {

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = jTable.getSelectedRow();
				int column = jTable.getSelectedColumn();
				String id = jTable.getValueAt(row, 0).toString();
				String str = jTable.getValueAt(row, column).toString();
				if (str.equals("modifier")) {
					for (Moto moto : allMotos) {
						if (("" + moto.getId()).equals(id)) {
							new UpdateMotoUI(moto).setVisible(true);
							break;
						}
					}

				} else if (str.equals("supprimer")) {

					int result = JOptionPane.showConfirmDialog(null, "confirmer de supprimer ?",
							"Attention", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						if (MotoDao.getInstance().deleteById(
								Integer.parseInt(id))) {
							JOptionPane.showMessageDialog(null, "réussir de supprimer !");
							AdminMainUI.panelMotos.removeAll();
							AdminMainUI.initPanelMotos(AdminMainUI.panelMotos);
							AdminMainUI.panelMotos.updateUI();

						} else {
							JOptionPane.showMessageDialog(null, "échouer de supprimer !");
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
	private static void setContentOfMotoTable(JTable jTable, List<Moto> all) {

		// "ID", "编号", "型号", "公里数", "价格", "操作1", "操作2"
		int i = 0;
		for (Moto item : all) {
			jTable.setValueAt(item.getId(), i, 0);
			jTable.setValueAt(item.getBianHao(), i, 1);
			jTable.setValueAt(item.getHorsepower() + "", i, 2);
			jTable.setValueAt(item.getKm() + "", i, 3);
			jTable.setValueAt(item.getPrice() + "", i, 4);
			jTable.setValueAt("modifier", i, 5);
			jTable.setValueAt("supprimer", i, 6);
			i++;
		}

	}

	/**
	 * @param motoTable
	 */
	private static void setAttributeOfMotoTable(JTable jTable) {

		JTableHeader jTableHeader = jTable.getTableHeader();
		jTableHeader.setReorderingAllowed(false);
		jTableHeader.setPreferredSize(new Dimension(0, 25));
		jTableHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
		// "ID", "编号", "型号", "公里数", "价格", "操作1", "操作2"
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.getColumn("ID").setCellRenderer(render);
		jTable.getColumn("code").setCellRenderer(render);
		jTable.getColumn("cylindrée").setCellRenderer(render);
		jTable.getColumn("kilomètre ").setCellRenderer(render);
		jTable.getColumn("prix").setCellRenderer(render);
		jTable.getColumn("opération1").setCellRenderer(render);
		jTable.getColumn("opération2").setCellRenderer(render);

		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jTable.getTableHeader().setDefaultRenderer(hr);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable
				.getColumnModel();
		/**
		 * 列宽度
		 */
		dcm.getColumn(0).setPreferredWidth(130);
		dcm.getColumn(1).setPreferredWidth(155);
		dcm.getColumn(2).setPreferredWidth(155);
		dcm.getColumn(3).setPreferredWidth(130);
		dcm.getColumn(4).setPreferredWidth(130);
		dcm.getColumn(5).setPreferredWidth(130);
		dcm.getColumn(6).setPreferredWidth(115);

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
	public static void initPanelAutos(JPanel panelAutos) {
		panelAutos.setLayout(null);
		addAoto = new JButton("Ajoute auto");
		addAoto.setBounds(820, 10, 100, 30);
		panelAutos.add(addAoto);
		addAoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddAutoUI().setVisible(true);
			}
		});
		allAutos = AutoDao.getInstance().getAll();
		createAutoTable(allAutos, panelAutos);
	}

	/**
	 * @param all
	 * @param panelAutos
	 */
	private static void createAutoTable(List<Auto> all, JPanel panelAutos) {
		/**
		 * 表格的列
		 */
		String[] columnsTitle = { "ID", "code", "modèle", "kilomètre ", "prix", "opération1", "opération2" };
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
		jsp.setBounds(0, 50, Constants.WIDTH, Constants.HEIGHT - 50);
		jsp.addMouseMotionListener(new MyMouseMotionAdapter(jsp));
		panelAutos.add(jsp);

	}

	/**
	 * 鼠标点击监听
	 * 
	 * @param jTable
	 */
	private static void addMouseAutoListener(final JTable jTable) {

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = jTable.getSelectedRow();
				int column = jTable.getSelectedColumn();
				String id = jTable.getValueAt(row, 0).toString();
				String str = jTable.getValueAt(row, column).toString();
				if (str.equals("modifier")) {
					for (Auto auto : allAutos) {
						if (("" + auto.getId()).equals(id)) {
							new UpdateAutoUI(auto).setVisible(true);
							break;
						}
					}
				} else if (str.equals("supprimer")) {

					int result = JOptionPane.showConfirmDialog(null, "confirmer de supprimer?",
							"attention ", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						if (AutoDao.getInstance().deleteById(
								Integer.parseInt(id))) {
							JOptionPane.showMessageDialog(null, "réussir de supprimer !");

							AdminMainUI.panelAutos.removeAll();
							AdminMainUI.initPanelAutos(AdminMainUI.panelAutos);
							AdminMainUI.panelAutos.updateUI();

						} else {
							JOptionPane.showMessageDialog(null, "echouer de supprimer !");
							return;
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
	private static void setContentOfAutoTable(JTable jTable, List<Auto> all) {

		// "ID", "编号", "型号", "公里数", "价格", "操作1", "操作2"
		int i = 0;
		for (Auto item : all) {
			jTable.setValueAt(item.getId(), i, 0);
			jTable.setValueAt(item.getBianHao(), i, 1);
			jTable.setValueAt(item.getXingHao(), i, 2);
			jTable.setValueAt(item.getKm() + "", i, 3);
			jTable.setValueAt(item.getPrice() + "", i, 4);
			jTable.setValueAt("modifier", i, 5);
			jTable.setValueAt("supprimer", i, 6);
			i++;
		}

	}

	/**
	 * @param autoTable
	 */
	private static void setAttributeOfAutoTable(JTable jTable) {

		JTableHeader jTableHeader = jTable.getTableHeader();
		jTableHeader.setReorderingAllowed(false);
		jTableHeader.setPreferredSize(new Dimension(0, 25));
		jTableHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
		// "ID", "编号", "型号", "公里数", "价格", "操作1", "操作2"
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.getColumn("ID").setCellRenderer(render);
		jTable.getColumn("code").setCellRenderer(render);
		jTable.getColumn("modèle").setCellRenderer(render);
		jTable.getColumn("kilomètre ").setCellRenderer(render);
		jTable.getColumn("prix").setCellRenderer(render);
		jTable.getColumn("opération1").setCellRenderer(render);
		jTable.getColumn("opération2").setCellRenderer(render);

		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jTable.getTableHeader().setDefaultRenderer(hr);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable
				.getColumnModel();
		/**
		 * 列宽度
		 */
		dcm.getColumn(0).setPreferredWidth(130);
		dcm.getColumn(1).setPreferredWidth(155);
		dcm.getColumn(2).setPreferredWidth(155);
		dcm.getColumn(3).setPreferredWidth(130);
		dcm.getColumn(4).setPreferredWidth(130);
		dcm.getColumn(5).setPreferredWidth(130);
		dcm.getColumn(6).setPreferredWidth(115);

		/**
		 * 行高度
		 */
		jTable.setRowHeight(30);
		jTable.setColumnSelectionAllowed(false);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	/**
	 * 租客信息
	 * 
	 * @param panelRenters2
	 */
	public static void initPanelRenters(final JPanel panelRenters) {
		panelRenters.setLayout(null);
		addRenter = new JButton("Ajoute emprunteur ");
		addRenter.setBounds(770, 10, 150, 30);
		addRenter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddEmprunteurUI().setVisible(true);

			}
		});

		panelRenters.add(addRenter);
		allEmprunteurs = EmprunteurDao.getInstance().getAll();
		createRenterTable(allEmprunteurs, panelRenters);
	}

	/**
	 * 创建表格
	 * 
	 * @param all
	 * @param panelRenters2
	 */
	private static void createRenterTable(List<Emprunteur> all,
			JPanel panelRenters) {
		/**
		 * 表格的列
		 */
		String[] columnsTitle = { "ID", "password", "nom", "prénom", "codepostal", "ville", "num",
				"rue", "opération 1", "opération 2" };
		DefaultTableModel model = new DefaultTableModel(columnsTitle, all
				.size());
		JTable renterTable = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		JScrollPane jsp = new JScrollPane(renterTable);
		setAttributeOfRenterTable(renterTable);
		setContentOfRenterTable(renterTable, all);
		addMouseRenterListener(renterTable);
		jsp.setBounds(0, 50, Constants.WIDTH, Constants.HEIGHT - 50);
		jsp.addMouseMotionListener(new MyMouseMotionAdapter(jsp));
		panelRenters.add(jsp);

	}

	/**
	 * 鼠标点击监听
	 * 
	 * @param jTable
	 */
	private static void addMouseRenterListener(final JTable jTable) {

		jTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = jTable.getSelectedRow();
				int column = jTable.getSelectedColumn();
				String id = jTable.getValueAt(row, 0).toString();
				String str = jTable.getValueAt(row, column).toString();
				if (str.equals("modifier")) {
					for (Emprunteur em : allEmprunteurs) {
						if (em.getId().equals(id)) {
							new UpdateEmprunteurUI(em).setVisible(true);
							break;
						}
					}
				} else if (str.equals("supprimer")) {

					int result = JOptionPane.showConfirmDialog(null, "confirmer de supprimer?",
							"attention ", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
					if (result == JOptionPane.OK_OPTION) {
						if (EmprunteurDao.getInstance().deleteById(id)) {
							JOptionPane.showMessageDialog(null, "réussir de supprimer !");
							AdminMainUI.panelRenters.removeAll();
							AdminMainUI
									.initPanelRenters(AdminMainUI.panelRenters);
							AdminMainUI.panelRenters.updateUI();

						} else {
							JOptionPane.showMessageDialog(null, "échouer de supprimer !");
						}
					}

				}

			}

		});
	}

	/**
	 * @param renterTable
	 * @param all
	 */
	private static void setContentOfRenterTable(JTable jTable,
			List<Emprunteur> all) {
		// "ID", "密码", "姓","名", "邮编", "城市", "门牌号", "街道"
		int i = 0;
		for (Emprunteur item : all) {
			jTable.setValueAt(item.getId(), i, 0);
			jTable.setValueAt(item.getPassword(), i, 1);
			jTable.setValueAt(item.getFamilyName(), i, 2);
			jTable.setValueAt(item.getLastName(), i, 3);
			jTable.setValueAt(item.getAddress().getPostcode(), i, 4);
			jTable.setValueAt(item.getAddress().getCity(), i, 5);
			jTable.setValueAt(item.getAddress().getHouseNum(), i, 6);
			jTable.setValueAt(item.getAddress().getStreet(), i, 7);
			jTable.setValueAt("modifier", i, 8);
			jTable.setValueAt("supprimer", i, 9);
			i++;
		}

	}

	/**
	 * @param jTable
	 */
	private static void setAttributeOfRenterTable(JTable jTable) {

		JTableHeader jTableHeader = jTable.getTableHeader();
		jTableHeader.setReorderingAllowed(false);
		jTableHeader.setPreferredSize(new Dimension(0, 25));
		jTableHeader.setBorder(new EmptyBorder(0, 0, 0, 0));
		// "ID", "密码", "姓名", "邮编", "城市", "门牌号", "街道"
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		jTable.getColumn("ID").setCellRenderer(render);
		jTable.getColumn("password").setCellRenderer(render);
		jTable.getColumn("nom").setCellRenderer(render);
		jTable.getColumn("prénom").setCellRenderer(render);
		jTable.getColumn("codepostal").setCellRenderer(render);
		jTable.getColumn("ville").setCellRenderer(render);
		jTable.getColumn("num").setCellRenderer(render);
		jTable.getColumn("rue").setCellRenderer(render);
		jTable.getColumn("opération 1").setCellRenderer(render);
		jTable.getColumn("opération 2").setCellRenderer(render);

		DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
		hr.setHorizontalAlignment(JLabel.CENTER);
		jTable.getTableHeader().setDefaultRenderer(hr);
		DefaultTableColumnModel dcm = (DefaultTableColumnModel) jTable
				.getColumnModel();
		/**
		 * 列宽度
		 */
		dcm.getColumn(0).setPreferredWidth(80);
		dcm.getColumn(1).setPreferredWidth(100);
		dcm.getColumn(2).setPreferredWidth(90);
		dcm.getColumn(3).setPreferredWidth(90);
		dcm.getColumn(4).setPreferredWidth(90);
		dcm.getColumn(5).setPreferredWidth(90);
		dcm.getColumn(6).setPreferredWidth(120);
		dcm.getColumn(7).setPreferredWidth(120);
		dcm.getColumn(8).setPreferredWidth(80);
		dcm.getColumn(9).setPreferredWidth(80);

		/**
		 * 行高度
		 */
		jTable.setRowHeight(30);
		jTable.setColumnSelectionAllowed(false);
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

}
