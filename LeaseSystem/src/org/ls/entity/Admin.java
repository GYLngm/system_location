package org.ls.entity;

/**
 * 管理员
 */
public class Admin {

	protected String id;       // id
	protected String password;   // mot de passe de admistrateur

	/**
	 * @param id
	 * @param password
	 */
	public Admin(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
