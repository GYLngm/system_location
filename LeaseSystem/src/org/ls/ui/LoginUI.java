package org.ls.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.ls.dao.AdminDao;
import org.ls.dao.EmprunteurDao;
import org.ls.entity.Admin;
import org.ls.entity.Emprunteur;
import org.ls.tools.Colors;
import org.ls.tools.ScreenUtils;

/**
 * 登录界面   login window
 */
public class LoginUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 成员变量    set variabes
	 */
	private JLabel title, picture, jl1, jl2, jl3, jl4;
	private JTextField jtUsername, jtCheckCode;
	private JPasswordField jtPassword;
	private JButton loginBtn;
	JRadioButton adminJB = new JRadioButton("administrateur");
	JRadioButton renterJB = new JRadioButton("Emprunteur", true);
	ButtonGroup group = new ButtonGroup();

	/**
	 * 当前登录的用户
	 */
	public static Admin currUser;

	/**
	 * 构造函数
	 */
	public LoginUI() {
		title = new JLabel("Location vehicule");
		picture = new JLabel(new ImageIcon("images/logo.png"));// 背景标签
		/* mark = new JLabel(new ImageIcon("3.jpg")); */
		jl1 = new JLabel("ID：");
		jl2 = new JLabel("Password：");
		jl3 = new JLabel();
		jl4 = new JLabel("Changer un autre");
		jtUsername = new JTextField();
		jtPassword = new JPasswordField();
		jtCheckCode = new JTextField();
		loginBtn = new JButton("Je me connect");

	}

	// 页面布局
	public void launchFrame() {
		JPanel p = (JPanel) getContentPane();

		jl3.setText(getcode());
		jl3.setForeground(Colors.DEEP_BLUE); // 设置验证码的颜色
		jl3.setBorder(BorderFactory.createRaisedBevelBorder());// 使验证码凸出显示
		jl4.addMouseListener(new addEvent()); // 为“看不清”标签添加鼠标监听器
		loginBtn.addActionListener(this); // 为“登录”按钮添加监听器
		// loginBtn.setBorder(BorderFactory.createRaisedBevelBorder());

		// ****设置背景图片
		p.setLayout(null);// 使用setBounds(),必须使布局为null
		picture.setBounds(0, 0, 500, 100);
		p.add(picture);
		p.setOpaque(false);// 使内容窗格透明
		// 把背景图片添加到分层窗格的最底层作为背景
		getLayeredPane().add(picture, new Integer(Integer.MIN_VALUE));

		// ****设置标题
		title.setForeground(Colors.WHITE);
		title.setFont(new Font("华文行楷", Font.BOLD, 35));
		title.setBounds(150, 30, 250, 40);
		p.add(title);

		// ****设置图标
		BufferedImage icon = null;
		try {
			icon = ImageIO.read(new File("images/icon.jpg"));
			this.setIconImage(icon);
		} catch (Exception e) {

		}

		jl1.setBounds(140, 120, 70, 25);
		p.add(jl1);
		jtUsername.setBounds(210, 120, 130, 25);
		p.add(jtUsername);
		jl2.setBounds(140, 160, 70, 25);
		p.add(jl2);
		jtPassword.setBounds(210, 160, 130, 25);
		p.add(jtPassword);
		jtCheckCode.setBounds(140, 200, 85, 25);
		p.add(jtCheckCode);
		jl3.setBounds(250, 200, 40, 25);
		p.add(jl3);
		jl4.setBounds(300, 200, 150, 25);
		p.add(jl4);

		adminJB.setBounds(50, 260, 120, 30);
		group.add(adminJB);
		p.add(adminJB);

		renterJB.setBounds(170, 260, 120, 30);
		group.add(renterJB);
		p.add(renterJB);

		adminJB.setSelected(true);// 默认选中

		loginBtn.setBounds(290, 260, 120, 30);
		p.add(loginBtn);

		setTitle("Bienvenue dans la systeme de location vehicule");
		int[] centerLocation = ScreenUtils.getCenterLocation(500, 330);
		this.setLocation(centerLocation[0], centerLocation[1]);
		this.setSize(centerLocation[2], centerLocation[3]);
		setResizable(false);// 窗口不能被拖动
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// 获取验证码
	public String getcode() {
		Random rd = new Random();
		String str = "";
		int a[] = new int[4];
		for (int i = 0; i < 4; i++) {
			a[i] = rd.nextInt(200);
			if ((a[i] >= 48 && a[i] <= 57) || (a[i] >= 65 && a[i] <= 90)
					|| (a[i] >= 97 && a[i] <= 122)) {
				str = str + (char) a[i];
			} else { // 获取的字符不在范围内，获取未成功；
				i--; // i--，保证最终获取到四位的验证码
			}
		}
		return str;
	}

	// 判断文本框是否为空
	public boolean isEmpty(JTextField jt) {
		if (jt.getText().equals("")) {
			return true;
		}
		return false;
	}

	// 判断操作是否非法，主要是针对各文本框而言的；若文本框为空，提示填写信息
	public boolean isLegal() {
		if (isEmpty(jtUsername)) {
			JOptionPane.showMessageDialog(null, "Id ou mot de passe est valide");
			return false;
		} else if (isEmpty(jtPassword)) {
			JOptionPane.showMessageDialog(null, "Id ou mot de passe est valide");
			return false;
		} else if (isEmpty(jtCheckCode)) {
			JOptionPane.showMessageDialog(null, "Entreez le Code de vérification！");
			return false;
		} else {
			return true;
		}
	}

	// 按钮事件处理
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		String id = jtUsername.getText();
		String password = jtPassword.getText().toString();
		String code2 = jtCheckCode.getText();
		String code1 = jl3.getText();

		if (isLegal()) {
			if (code2.equalsIgnoreCase(code1)) {

				if (adminJB.isSelected()) {// 管理员登录

					currUser = AdminDao.getInstance().login(
							new Admin(id, password));

					if (currUser != null) {
						// System.out.println("登录成功");
						new AdminMainUI().setVisible(true);

						this.dispose();

					} else {

						JOptionPane.showMessageDialog(this,
								"Id ou mot de passe est incorrect !", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}

				} else {// 租客登录

					currUser = EmprunteurDao.getInstance().login(
							new Emprunteur(id, password));
					if (currUser != null) {
						new RenterMainUI().setVisible(true);
						this.dispose();

					} else {
						JOptionPane.showMessageDialog(this,
								"Id ou mot de passe est incorrect", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					}

				}

			} else {
				JOptionPane.showMessageDialog(this, "Code de vérification est incorrect !", "Erreur",
						JOptionPane.ERROR_MESSAGE);
				jl3.setText(getcode());

			}

		}

	}

	class addEvent extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			jl3.setText(getcode());
		}
	}

	/*
	 * 程序入口
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {

		/**
		 * 下面两句为了使界面呈现windows风格(去掉试试)
		 */
		String windows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		UIManager.setLookAndFeel(windows);

		new LoginUI().launchFrame();
	}
}