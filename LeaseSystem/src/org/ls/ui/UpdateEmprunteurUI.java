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

import org.ls.dao.EmprunteurDao;
import org.ls.entity.Emprunteur;
import org.ls.tools.ScreenUtils;

/**
 * 修改租客
 */
public class UpdateEmprunteurUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;
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
	 * The constructor adds all the components to the frame
	 */
	public UpdateEmprunteurUI(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
		oldId = emprunteur.getId();
		this.setTitle("modifier les informations d'emprunteur ");
		/* 设置显示的位置和窗体大小 */
		int[] centerLocation = ScreenUtils.getCenterLocation(350, 450);
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
		jTextFieldId.setText(emprunteur.getId());

		JLabel jLabelPwd = new JLabel("password:");
		jLabelPwd.setBounds(50, 50, 70, 30);
		contentPanel.add(jLabelPwd);

		jTextFieldPassword = new JTextField();
		jTextFieldPassword.setBounds(130, 55, 150, 25);
		contentPanel.add(jTextFieldPassword);
		jTextFieldPassword.setText(emprunteur.getPassword());

		JLabel jLabelFamilyName = new JLabel("Nom:");
		jLabelFamilyName.setBounds(50, 80, 70, 30);
		contentPanel.add(jLabelFamilyName);

		jTextFamilyName = new JTextField();
		jTextFamilyName.setBounds(130, 85, 150, 25);
		contentPanel.add(jTextFamilyName);
		jTextFamilyName.setText(emprunteur.getFamilyName());

		JLabel jLabelLastName = new JLabel("Prenom:");
		jLabelLastName.setBounds(50, 110, 70, 30);
		contentPanel.add(jLabelLastName);

		jTextLastName = new JTextField();
		jTextLastName.setBounds(130, 115, 150, 25);
		contentPanel.add(jTextLastName);
		jTextLastName.setText(emprunteur.getLastName());

		JLabel jLabelHouseNum = new JLabel("Num:");
		jLabelHouseNum.setBounds(50, 140, 70, 30);
		contentPanel.add(jLabelHouseNum);

		jTextHouseNum = new JTextField();
		jTextHouseNum.setBounds(130, 145, 150, 25);
		contentPanel.add(jTextHouseNum);
		jTextHouseNum.setText(emprunteur.getAddress().getHouseNum());

		JLabel jLabelStreet = new JLabel("Rue:");
		jLabelStreet.setBounds(50, 170, 70, 30);
		contentPanel.add(jLabelStreet);

		jTextStreet = new JTextField();
		jTextStreet.setBounds(130, 175, 150, 25);
		contentPanel.add(jTextStreet);
		jTextStreet.setText(emprunteur.getAddress().getStreet());

		JLabel jLabelPostcode = new JLabel("Codepostal:");
		jLabelPostcode.setBounds(50, 200, 70, 30);
		contentPanel.add(jLabelPostcode);

		jTextPostcode = new JTextField();
		jTextPostcode.setBounds(130, 205, 150, 25);
		contentPanel.add(jTextPostcode);
		jTextPostcode.setText(emprunteur.getAddress().getPostcode());

		JLabel jLabelCity = new JLabel("Ville:");
		jLabelCity.setBounds(50, 230, 70, 30);
		contentPanel.add(jLabelCity);

		jTextCity = new JTextField();
		// jTextCity.setBounds(50, 310, 70, 30);
		jTextCity.setBounds(130, 235, 150, 25);
		contentPanel.add(jTextCity);
		jTextCity.setText(emprunteur.getAddress().getCity());

		btnReg = new JButton("Confirm");
		btnReset = new JButton("Reset");

		btnReg.setBounds(60, 360, 100, 30);
		btnReset.setBounds(180, 360, 100, 30);

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

					if (jTextFieldId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "ID est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFieldPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Password est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFamilyName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Nom est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextLastName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "prénom est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextHouseNum.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Num est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextStreet.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "la rue est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextPostcode.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "le code postal est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextCity.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "la ville est vide!", "警告",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (EmprunteurDao.getInstance().update(
							new Emprunteur(jTextFieldId.getText().toString(),
									jTextFieldPassword.getText().toString(),
									jTextFamilyName.getText().toString(),
									jTextLastName.getText().toString(),
									jTextHouseNum.getText().toString(),
									jTextStreet.getText().toString(),
									jTextPostcode.getText().toString(),
									jTextCity.getText().toString()), oldId)) {
						JOptionPane.showMessageDialog(null, "réussir de modifier!");
						setVisible(false);

						AdminMainUI.panelRenters.removeAll();
						AdminMainUI.initPanelRenters(AdminMainUI.panelRenters);
						AdminMainUI.panelRenters.updateUI();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "echouer de modifier!");
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
