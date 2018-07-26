package org.ls.entity;

/**
 * 订单
 */
public class Exemplaire {

	private int id;// 主键

	private String bianHao;// 编号  code

	private String xingHao;// 型号 modele

	private int km;// 公里数   kilometre

	private float price;// 汽车价格  prix

	private Date startTime;// 开始日期  date commance de loyer

	private Date endTime;// 截止日期  date fini

	private String renterName;// 租客名字   nom de emprunteur

	private int hourseNunber;// 马力

	private int type;// 0——代表汽车， 1——代表摩托车      0 c'est t'a dire auto, 1 egale moto

	/**
	 * @param id
	 * @param bianHao
	 * @param xingHao
	 * @param km
	 * @param price
	 * @param startTime
	 * @param endTime
	 * @param renterName
	 */
	public Exemplaire(int id, String bianHao, String xingHao, int km, float price,
			String startTime, String endTime, String renterName,
			int hourseNunber, int type) {
		super();
		this.id = id;
		this.bianHao = bianHao;
		this.xingHao = xingHao;
		this.km = km;
		this.price = price;
		this.startTime = new Date(startTime.split("/")[0],
				startTime.split("/")[1], startTime.split("/")[2]);
		this.endTime = new Date(endTime.split("/")[0], endTime.split("/")[1],
				endTime.split("/")[2]);
		this.renterName = renterName;
		this.hourseNunber = hourseNunber;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBianHao() {
		return bianHao;
	}

	public void setBianHao(String bianHao) {
		this.bianHao = bianHao;
	}

	public String getXingHao() {
		return xingHao;
	}

	public void setXingHao(String xingHao) {
		this.xingHao = xingHao;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getStartTime() {
		return startTime.toString();
	}

	public void setStartTime(String startTime) {// 1990/03/16
		this.startTime = new Date(startTime.split("/")[0],
				startTime.split("/")[1], startTime.split("/")[2]);
	}

	public String getEndTime() {
		return endTime.toString();
	}

	public void setEndTime(String endTime) {
		this.endTime = new Date(endTime.split("/")[0], endTime.split("/")[1],
				endTime.split("/")[2]);
	}

	public String getRenterName() {
		return renterName;
	}

	public void setRenterName(String renterName) {
		this.renterName = renterName;
	}

	public int getHourseNunber() {
		return hourseNunber;
	}

	public void setHourseNunber(int hourseNunber) {
		this.hourseNunber = hourseNunber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
