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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.ls.dao.EmprunteurDao;
import org.ls.entity.Emprunteur;
import org.ls.tools.ScreenUtils;

/**
 * 添加租客   ajouter les infos des emprunteur
 */
public class AddEmprunteurUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel;
	private JTextField jTextFieldId;
	private JPasswordField jTextFieldPassword;
	private JPasswordField jTextFieldConfirmPassword;
	private JTextField jTextFamilyName;
	private JTextField jTextLastName;
	private JTextField jTextHouseNum;
	private JTextField jTextStreet;
	private JTextField jTextPostcode;
	private JTextField jTextCity;

	private JButton btnReset;
	private JButton btnReg;

	/**
	 * The constructor adds all the components to the frame
	 */
	public AddEmprunteurUI() {
		this.setTitle("Ajoute l'emprunteur");
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

		JLabel jLabelPwd = new JLabel("Password:");
		jLabelPwd.setBounds(50, 50, 70, 30);
		contentPanel.add(jLabelPwd);

		jTextFieldPassword = new JPasswordField();
		jTextFieldPassword.setBounds(130, 55, 150, 25);
		contentPanel.add(jTextFieldPassword);

		JLabel jLabelConPwd = new JLabel("Confirm password:");
		jLabelConPwd.setBounds(50, 80, 70, 30);
		contentPanel.add(jLabelConPwd);

		jTextFieldConfirmPassword = new JPasswordField();
		jTextFieldConfirmPassword.setBounds(130, 85, 150, 25);
		contentPanel.add(jTextFieldConfirmPassword);

		JLabel jLabelFamilyName = new JLabel("Nom:");
		jLabelFamilyName.setBounds(50, 110, 70, 30);
		contentPanel.add(jLabelFamilyName);

		jTextFamilyName = new JTextField();
		jTextFamilyName.setBounds(130, 115, 150, 25);
		contentPanel.add(jTextFamilyName);

		JLabel jLabelLastName = new JLabel("prénom:");
		jLabelLastName.setBounds(50, 140, 70, 30);
		contentPanel.add(jLabelLastName);

		jTextLastName = new JTextField();
		jTextLastName.setBounds(130, 145, 150, 25);
		contentPanel.add(jTextLastName);

		JLabel jLabelHouseNum = new JLabel("Num:");
		jLabelHouseNum.setBounds(50, 170, 70, 30);
		contentPanel.add(jLabelHouseNum);

		jTextHouseNum = new JTextField();
		jTextHouseNum.setBounds(130, 175, 150, 25);
		contentPanel.add(jTextHouseNum);

		JLabel jLabelStreet = new JLabel("Rue:");
		jLabelStreet.setBounds(50, 200, 70, 30);
		contentPanel.add(jLabelStreet);

		jTextStreet = new JTextField();
		jTextStreet.setBounds(130, 205, 150, 25);
		contentPanel.add(jTextStreet);

		JLabel jLabelPostcode = new JLabel("Codepostal:");
		jLabelPostcode.setBounds(50, 230, 70, 30);
		contentPanel.add(jLabelPostcode);

		jTextPostcode = new JTextField();
		jTextPostcode.setBounds(130, 235, 150, 25);
		contentPanel.add(jTextPostcode);

		JLabel jLabelCity = new JLabel("Ville:");
		jLabelCity.setBounds(50, 260, 70, 30);
		contentPanel.add(jLabelCity);

		jTextCity = new JTextField();
		// jTextCity.setBounds(50, 310, 70, 30);
		jTextCity.setBounds(130, 265, 150, 25);
		contentPanel.add(jTextCity);

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

				@SuppressWarnings("deprecation")
				@Override
				public void run() {

					if (jTextFieldId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le ID !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFieldPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Entrez le mot de passe!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFieldConfirmPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Entrez le mot de passe!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (!jTextFieldPassword.getText().toString().equals(
							jTextFieldConfirmPassword.getText().toString())) {
						JOptionPane.showMessageDialog(null, "Le mot de passe n'est pas le meme !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextFamilyName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le Nom !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextLastName.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le prenom !", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextHouseNum.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le Numero de adresse!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextStreet.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez la Rue!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextPostcode.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le postecode", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (jTextCity.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Tapez le ville!", "Attention",
								JOptionPane.WARNING_MESSAGE);
						return;
					}

					if (EmprunteurDao.getInstance().add(
							new Emprunteur(jTextFieldId.getText().toString(),
									jTextFieldPassword.getText().toString(),
									jTextFamilyName.getText().toString(),
									jTextLastName.getText().toString(),
									jTextHouseNum.getText().toString(),
									jTextStreet.getText().toString(),
									jTextPostcode.getText().toString(),
									jTextCity.getText().toString()))) {
						JOptionPane.showMessageDialog(null, "添加成功!");
						setVisible(false);

						AdminMainUI.panelRenters.removeAll();
						AdminMainUI.initPanelRenters(AdminMainUI.panelRenters);
						AdminMainUI.panelRenters.updateUI();
						return;
					} else {
						JOptionPane.showMessageDialog(null, "添加失败!");
						return;
					}

				}
			}).start();

		} else if (e.getSource() == btnReset) {

			jTextFieldId.setText("");
			jTextFieldPassword.setText("");
			jTextFieldConfirmPassword.setText("");
			jTextFamilyName.setText("");
			jTextLastName.setText("");
			jTextHouseNum.setText("");
			jTextStreet.setText("");
			jTextPostcode.setText("");
			jTextCity.setText("");

		}
	}

}
