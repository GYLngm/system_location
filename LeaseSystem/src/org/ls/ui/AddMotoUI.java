package org.ls.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.ls.dao.MotoDao;
import org.ls.entity.Moto;
import org.ls.tools.ScreenUtils;

/**
 * 添加摩托车
 */
public class AddMotoUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;
	private JTextField jTextFieldBianHao;
	private JTextField jTextFieldKm;
	private JTextField jTextFieldPrice;
	private JTextField jTextStoreNum;
	private JTextField jTextHourseNum;

	private JButton btnReset;
	private JButton btnReg;

	/**
	 * The constructor adds all the components to the frame
	 */
	public AddMotoUI() {
		this.setTitle("Ajouter les motos ");
		/* 设置显示的位置和窗体大小 */
		int[] centerLocation = ScreenUtils.getCenterLocation(350, 300);
		this.setBounds(centerLocation[0], centerLocation[1], centerLocation[2],
				centerLocation[3]);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.layoutComponents();
		// ****设置图标
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File("images/icon.jpg"));
			this.setIconImage(icon);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// this.setVisible(true);

	}

	/**
	 * Helper method to add components to the frame
	 */
	public void layoutComponents() {

		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		JLabel jLabelUsername = new JLabel("code:");
		jLabelUsername.setBounds(50, 20, 70, 30);
		contentPanel.add(jLabelUsername);

		jTextFieldBianHao = new JTextField();
		jTextFieldBianHao.setBounds(130, 25, 150, 25);
		contentPanel.add(jTextFieldBianHao);

		// protected String bianHao;// 编号
		//
		// protected int km;// 公里数
		//
		// protected float price;// 汽车价格
		//	
		// protected int storeNum;// 库存量

		// this.xingHao = xingHao;

		JLabel jLabelPwd = new JLabel("kilomètre:");
		jLabelPwd.setBounds(50, 50, 70, 30);
		contentPanel.add(jLabelPwd);

		jTextFieldKm = new JTextField();
		jTextFieldKm.setBounds(130, 55, 150, 25);
		jTextFieldKm.setDocument(new NumberLenghtLimitedDmt(10)); // 这个文本框只能输入10位而且是只能是数字!!!
		contentPanel.add(jTextFieldKm);

		JLabel jLabelConPwd = new JLabel("prix:");
		jLabelConPwd.setBounds(50, 80, 70, 30);
		contentPanel.add(jLabelConPwd);

		jTextFieldPrice = new JTextField();
		jTextFieldPrice.setBounds(130, 85, 150, 25);
		contentPanel.add(jTextFieldPrice);
		jTextFieldPrice.setDocument(new NumberLenghtLimitedDmt(10)); // 这个文本框只能输入10位而且是只能是数字!!!

		JLabel jLabelFamilyName = new JLabel("existence :");
		jLabelFamilyName.setBounds(50, 110, 70, 30);
		contentPanel.add(jLabelFamilyName);

		jTextStoreNum = new JTextField();
		jTextStoreNum.setBounds(130, 115, 150, 25);
		jTextStoreNum.setDocument(new NumberLenghtLimitedDmt(10)); // 这个文本框只能输入10位而且是只能是数字!!!
		contentPanel.add(jTextStoreNum);

		JLabel jLabelLastName = new JLabel("cylindrée:");
		jLabelLastName.setBounds(50, 140, 70, 30);
		contentPanel.add(jLabelLastName);

		jTextHourseNum = new JTextField();
		jTextHourseNum.setBounds(130, 145, 150, 25);
		contentPanel.add(jTextHourseNum);
		jTextHourseNum.setDocument(new NumberLenghtLimitedDmt(10)); // 这个文本框只能输入10位而且是只能是数字!!!

		btnReg = new JButton("Confirm");
		btnReset = new JButton("Reset");

		btnReg.setBounds(60, 210, 100, 30);
		btnReset.setBounds(180, 210, 100, 30);

		btnReg.addActionListener(this);
		btnReset.addActionListener(this);
		contentPanel.add(btnReg);
		contentPanel.add(btnReset);

		this.add(contentPanel);

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

					if (jTextFieldBianHao.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le code!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFieldKm.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le kilomètre !", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFieldPrice.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le prix !", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextStoreNum.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez la existence !", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextHourseNum.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le cylindrée !", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (MotoDao.getInstance().add(
							new Moto(0, jTextFieldBianHao.getText().toString(),
									Integer.parseInt(jTextFieldKm.getText()
											.toString()), Float
											.parseFloat(jTextFieldPrice
													.getText().toString()),
									Integer.parseInt(jTextStoreNum.getText()
											.toString()), Integer
											.parseInt(jTextHourseNum.getText()
													.toString())))) {
						JOptionPane.showMessageDialog(null, "réussir d'ajouté !");
						setVisible(false);
						
						AdminMainUI.panelMotos.removeAll();
						AdminMainUI.initPanelMotos(AdminMainUI.panelMotos);
						AdminMainUI.panelMotos.updateUI();
						
						return;
					} else {
						JOptionPane.showMessageDialog(null, "echouer d'ajouté !");
						return;
					}

				}
			}).start();

		} else if (e.getSource() == btnReset) {

			jTextFieldBianHao.setText("");
			jTextFieldKm.setText("");
			jTextFieldPrice.setText("");
			jTextStoreNum.setText("");
			jTextHourseNum.setText("");

		}
	}

}
