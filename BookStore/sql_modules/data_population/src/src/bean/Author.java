package bean;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Author {
	private Integer aid;
	private String name;
	public Author(Integer aid, String name) {
		this.aid = aid;
		this.name = name;
	}
	public Author(ResultSet set) {
		try {
			this.aid = set.getInt(1);
			this.name = set.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean equals(Object obj) {
		if (obj instanceof Author) {
			Author at = (Author)obj;
			return at.getAid() == this.getAid() && at.getName().equals(this.getName());
		} else {
			return false;
		}
	}
	
}
