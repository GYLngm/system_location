package org.ls.entity;

/**
 * 日期
 */
public class Date {            //la classe date avec mois,annee.jour

	private String year;
	private String month;
	private String day;

	/**
	 * @param year
	 * @param month
	 * @param day
	 */
	public Date(String year, String month, String day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public String toString() {
		return year + "/" + month + "/" + day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
