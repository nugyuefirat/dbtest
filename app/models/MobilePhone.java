package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class MobilePhone extends Model {

	@ManyToOne
	public Brand brand;

	public String modelName;

	@OneToMany(mappedBy = "phone")
	public List<Comment> comments = new ArrayList<Comment>();

	public MobilePhone(Brand brand, String modelName) {
		super();
		this.brand = brand;
		this.modelName = modelName;
	}
	
	public MobilePhone addComment(String content)
	{
		Comment newComment = new Comment(this,content).save();
		this.comments.add(newComment);
		this.save();
		return this;
	}
	
	
}
