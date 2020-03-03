package com.jdc.restaurant.client;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jdc.restaurant.client.api.MenuApi;
import com.jdc.restaurant.client.dto.Menu;
import com.jdc.restaurant.client.utils.RequestUtils;
import com.jdc.restaurant.client.utils.RestaurantClientFactory;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class MenuClient {

	private MenuApi api;
	
	public MenuClient() {
		api = RestaurantClientFactory.generate(MenuApi.class);
	}
	
	public Menu create(Menu data) {
		return RequestUtils.execute(api.create(data));
	}

	public List<Menu> upload(List<Menu> data) {
		return RequestUtils.execute(api.upload(data));
	}

	public Menu update(Menu data) {
		return RequestUtils.execute(api.update(data));
	}

	public List<Menu> findAll() {
		return RequestUtils.execute(api.findAll());
	}
	
	public Menu findById(long id) {
		return RequestUtils.execute(api.findById(id));
	}

	public List<Menu> findByCategory(long id) {
		return RequestUtils.execute(api.findByCategory(id));
	}
	
	public List<Menu> search(Map<String, String> query) {
		return RequestUtils.execute(api.search(query));
	}
	
	public Menu upload(Menu menu, File  file) {
		RequestBody body = MultipartBody.create(MediaType.parse("image/*"), file);
		MultipartBody.Part part = MultipartBody.Part.createFormData("photo", menu.getCode(), body);
		
		try {
			Response<Menu> resp  = api.uploadPhoto(menu.getId(), part).execute();
			return resp.body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}
}
