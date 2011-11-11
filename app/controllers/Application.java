package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Brand;
import models.Comment;
import models.MobilePhone;
import play.Logger;
import play.mvc.Controller;
import util.GSonSerializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Application extends Controller {

	public static void index() {
		// long id= 1;
		// MobilePhone myphone= MobilePhone.findById(id);
		// myphone.addComment("ucuz, hızlı, şık");

		// List<Brand> brandlist= Brand.findAll();

		List brands = Brand.findAll();
		List mobilephones = MobilePhone.findAll();
		render(brands, mobilephones);
	}

	// public static void getModels(Brand brand) {
	//
	// List models= brand.phones;
	// render(models);
	// }

	public static void mobilephones(String term) {
		Logger.info("Trying to find a phone with : " + term);
		List<MobilePhone> mobilephones = MobilePhone.find("byModelNameLike",
				"%" + term + "%").fetch();
		Logger.info("Filtered mobiles size : " + mobilephones.size());
		// renderJSON(jsonObject);
		List<Class<?>> classesToSkip = new ArrayList<Class<?>>();
		classesToSkip.add(Comment.class);
		classesToSkip.add(Brand.class);
		renderJSON(GSonSerializer.createGSonBuilder(classesToSkip).toJson(
				mobilephones));
	}

	/**
	 * @param phoneid
	 * @param contents
	 */
	public static void createComment(long phoneid, String[] contents) {
		MobilePhone phone = MobilePhone.findById(phoneid);
		Logger.info("phone found : " + phone.modelName);
		for (int i = 0; i < contents.length; i++) {
			if (!contents[i].equals("")) {
				Comment comment = new Comment(phone, contents[i]).save();
				Logger.info("comment saved : " + comment.content);
			}
		}
		// geri dondurmedik
	}

}