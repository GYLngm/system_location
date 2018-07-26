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

import org.ls.dao.OrderDao;
import org.ls.entity.Moto;
import org.ls.entity.Exemplaire;
import org.ls.tools.Constants;
import org.ls.tools.DatePicker;
import org.ls.tools.ScreenUtils;

/**
 * 下订单(摩托车)
 */
public class AddMotoOrderUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;
	private JTextField jTextFieldId;
	private JTextField jTextBianHao;
	private JTextField jTextXingHao;
	private JTextField jTextKm;
	private JTextField jTextPrice;
	private JTextField jTextStartTime;
	private JTextField jTextEndTime;

	private JButton btnReset;
	private JButton btnReg;

	private Moto moto;

	/**
	 * The constructor adds all the components to the frame
	 */
	public AddMotoOrderUI(Moto order) {
		this.moto = order;
		this.setTitle("la commande ");
		/* 设置显示的位置和窗体大小 */
		int[] centerLocation = ScreenUtils.getCenterLocation(350, 350);
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
		JLabel jLabelUsername = new JLabel("ID:");
		jLabelUsername.setBounds(50, 20, 70, 30);
		contentPanel.add(jLabelUsername);

		jTextFieldId = new JTextField();
		jTextFieldId.setBounds(130, 25, 150, 25);
		contentPanel.add(jTextFieldId);
		jTextFieldId.setText(moto.getId() + "");
		jTextFieldId.setEnabled(false);

		JLabel jLabelPwd = new JLabel("code:");
		jLabelPwd.setBounds(50, 50, 70, 30);
		contentPanel.add(jLabelPwd);

		jTextBianHao = new JTextField();
		jTextBianHao.setBounds(130, 55, 150, 25);
		contentPanel.add(jTextBianHao);
		jTextBianHao.setText(moto.getBianHao());
		jTextBianHao.setEnabled(false);

		JLabel jLabelFamilyName = new JLabel("cylindrée :");
		jLabelFamilyName.setBounds(50, 80, 70, 30);
		contentPanel.add(jLabelFamilyName);

		jTextXingHao = new JTextField();
		jTextXingHao.setBounds(130, 85, 150, 25);
		contentPanel.add(jTextXingHao);
		jTextXingHao.setText(moto.getHorsepower() + "");
		jTextXingHao.setEnabled(false);

		JLabel jLabelLastName = new JLabel("kilomètre:");
		jLabelLastName.setBounds(50, 110, 70, 30);
		contentPanel.add(jLabelLastName);

		jTextKm = new JTextField();
		jTextKm.setBounds(130, 115, 150, 25);
		contentPanel.add(jTextKm);
		jTextKm.setText(moto.getKm() + "");
		jTextKm.setEnabled(false);

		JLabel jLabelHouseNum = new JLabel("prix:");
		jLabelHouseNum.setBounds(50, 140, 70, 30);
		contentPanel.add(jLabelHouseNum);

		jTextPrice = new JTextField();
		jTextPrice.setBounds(130, 145, 150, 25);
		contentPanel.add(jTextPrice);
		jTextPrice.setText(moto.getPrice() + "");
		jTextPrice.setEnabled(false);

		JLabel jLabelStreet = new JLabel("datecommence:");
		jLabelStreet.setBounds(50, 170, 70, 30);
		contentPanel.add(jLabelStreet);

		JLabel jLabelPostcode = new JLabel("datefini:");
		jLabelPostcode.setBounds(50, 200, 70, 30);
		contentPanel.add(jLabelPostcode);

		jTextStartTime = new JTextField();
		jTextStartTime.setBounds(130, 175, 150, 25);

		jTextEndTime = new JTextField();
		jTextEndTime.setBounds(130, 205, 150, 25);

		/**
		 * 控件加上日期选择
		 */
		DatePicker datePicker = DatePicker.getInstance();
		datePicker.register(jTextStartTime);
		datePicker = DatePicker.getInstance();
		datePicker.register(jTextEndTime);
		jTextStartTime.setEditable(false);
		jTextEndTime.setEditable(false);
		contentPanel.add(jTextStartTime);
		contentPanel.add(jTextEndTime);

		btnReg = new JButton("confirm");
		btnReset = new JButton("annuler ");

		btnReg.setBounds(60, 260, 100, 30);
		btnReset.setBounds(180, 260, 100, 30);

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

					if (jTextStartTime.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "tapez la date commence !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextEndTime.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "tapez la date fini!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					Exemplaire order = new Exemplaire(0, moto.getBianHao(), "", moto
							.getKm(), moto.getPrice(), jTextStartTime.getText()
							.toString().trim(), jTextEndTime.getText()
							.toString().trim(), LoginUI.currUser.getId(), moto
							.getHorsepower(), Constants.MOTO);

					if (OrderDao.getInstance().louer(order)) {
						JOptionPane.showMessageDialog(null, "réussi !");
						setVisible(false);

						// 刷新我的订单界面
						RenterMainUI.panelOrder.removeAll();
						RenterMainUI.initPanelOrder(RenterMainUI.panelOrder);
						RenterMainUI.panelOrder.updateUI();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "échouer de faire la commande!");
						return;
					}

				}
			}).start();

		} else if (e.getSource() == btnReset) {

			setVisible(false);

		}
	}
}
