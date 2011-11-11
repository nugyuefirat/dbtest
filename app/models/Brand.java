package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class Brand extends Model {

	public String brandName;

	@OneToMany(mappedBy = "brand")
	public List<MobilePhone> phones = new ArrayList<MobilePhone>();

	public Brand(String brandName) {
		super();
		this.brandName = brandName;
	}

}
