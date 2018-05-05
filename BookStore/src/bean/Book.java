package bean;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Book {
	private Integer isbn;
	private String title;
	private Date publicationDate;
	private Integer threshold;
	private Integer price;
	private Integer copiesNumber;
	private Integer cid;
	private Integer pid;
	
	public Book(Integer isbn, String title, Date publicationDate, Integer threshold,
			Integer price, Integer copies, Integer cid, Integer pid) {
		this.isbn = isbn;
		this.title = title;
		this.publicationDate = publicationDate;
		this.threshold = threshold;
		this.price = price;
		this.copiesNumber = copies;
		this.cid = cid;
		this.pid = pid;
	}
	public Book(ResultSet set) {
		try {
			this.isbn = set.getInt(1);
			this.title = set.getString(2);
			this.publicationDate = set.getDate(3);
			this.threshold = set.getInt(4);
			this.price = set.getInt(5);
			this.copiesNumber = set.getInt(6);
			this.cid = set.getInt(7);
			this.pid = set.getInt(8);
		} catch (SQLException e) {}
		
	}

	public Integer getIsbn() {
		return isbn;
	}

	public void setIsbn(Integer isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getThreshold() {
		return threshold;
	}

	public void setThreshold(Integer threshold) {
		this.threshold = threshold;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getCopiesNumber() {
		return copiesNumber;
	}

	public void setCopiesNumber(Integer copiesNumber) {
		this.copiesNumber = copiesNumber;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}
}
