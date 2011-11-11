package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Comment extends Model {

	@ManyToOne
	public MobilePhone phone;

	public String content;

	public Comment(MobilePhone phone, String content) {
		super();
		this.phone = phone;
		this.content = content;
	}

}
