package org.ls.ui;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.ls.entity.Exemplaire;
import org.ls.tools.ScreenUtils;

/**
 * 查看摩托车订单详情
 */
public class CheckMotoOrderUI extends JFrame {

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

	private Exemplaire order;

	/**
	 * The constructor adds all the components to the frame
	 */
	public CheckMotoOrderUI(Exemplaire order) {
		this.order = order;
		this.setTitle("les information de la commande");
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
		jTextFieldId.setText(order.getId() + "");
		jTextFieldId.setEnabled(false);

		JLabel jLabelPwd = new JLabel("code:");
		jLabelPwd.setBounds(50, 50, 70, 30);
		contentPanel.add(jLabelPwd);

		jTextBianHao = new JTextField();
		jTextBianHao.setBounds(130, 55, 150, 25);
		contentPanel.add(jTextBianHao);
		jTextBianHao.setText(order.getBianHao());
		jTextBianHao.setEnabled(false);

		JLabel jLabelFamilyName = new JLabel("cylindrée :");
		jLabelFamilyName.setBounds(50, 80, 70, 30);
		contentPanel.add(jLabelFamilyName);

		jTextXingHao = new JTextField();
		jTextXingHao.setBounds(130, 85, 150, 25);
		contentPanel.add(jTextXingHao);
		jTextXingHao.setText(order.getHourseNunber() + "");
		jTextXingHao.setEnabled(false);

		JLabel jLabelLastName = new JLabel("kilomètre :");
		jLabelLastName.setBounds(50, 110, 70, 30);
		contentPanel.add(jLabelLastName);

		jTextKm = new JTextField();
		jTextKm.setBounds(130, 115, 150, 25);
		contentPanel.add(jTextKm);
		jTextKm.setText(order.getKm() + "");
		jTextKm.setEnabled(false);

		JLabel jLabelHouseNum = new JLabel("prix:");
		jLabelHouseNum.setBounds(50, 140, 70, 30);
		contentPanel.add(jLabelHouseNum);

		jTextPrice = new JTextField();
		jTextPrice.setBounds(130, 145, 150, 25);
		contentPanel.add(jTextPrice);
		jTextPrice.setText(order.getPrice() + "");
		jTextPrice.setEnabled(false);

		JLabel jLabelStreet = new JLabel("datecommence:");
		jLabelStreet.setBounds(50, 170, 70, 30);
		contentPanel.add(jLabelStreet);

		JLabel jLabelPostcode = new JLabel("datefini:");
		jLabelPostcode.setBounds(50, 200, 70, 30);
		contentPanel.add(jLabelPostcode);

		jTextStartTime = new JTextField();
		jTextStartTime.setBounds(130, 175, 150, 25);
		jTextStartTime.setText(order.getStartTime());

		jTextEndTime = new JTextField();
		jTextEndTime.setBounds(130, 205, 150, 25);
		jTextEndTime.setText(order.getEndTime());

		jTextStartTime.setEnabled(false);
		jTextEndTime.setEnabled(false);
		contentPanel.add(jTextStartTime);
		contentPanel.add(jTextEndTime);

		this.add(contentPanel);

	}
}
