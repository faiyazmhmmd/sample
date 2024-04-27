package com.Swagger_Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import groovyjarjarantlr4.v4.runtime.CodePointCharStream;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Sample extends Base_Class {

	private String string;
	String Stateid;
	int stateidnum;
	String logtoken;
	int cityidnum;
	int address_idnum;
	String address_id;

	@Test(priority = 1)
	public void Login() {

		addHeader("accept", "application/json");
		addBasicAuth("faiyazmhmmd@gmail.com", "Madurai@123");
		Response addRequestType = addRequestType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");
		int respondCode = getRespondCode(addRequestType);
		Assert.assertEquals(respondCode, 200, "verify the request code");

		Login_output_pojo a = addRequestType.as(Login_output_pojo.class);

		String first_name = a.getData().getFirst_name();
		System.out.println(first_name);
		Assert.assertEquals(first_name, "Faiyaz", "verify the first Name");

		String last_name = a.getData().getLast_name();
		System.out.println(last_name);
		Assert.assertEquals(last_name, "mohammed", "verify the Last Name");

		logtoken = a.getData().getLogtoken();

	}

	@Test(priority = 2)
	public void StateList() {

		addHeader("accept", "application/json");
		Response addRequestType = addRequestType("GET", "https://omrbranch.com/api/stateList");
		int respondCode = getRespondCode(addRequestType);
		Assert.assertEquals(respondCode, 200, "verify the response code");

		StateList_output_pojo a = addRequestType.as(StateList_output_pojo.class);
		ArrayList<Statelist> data = a.getData();
		for (Statelist eachstateList : data) {
			String name = eachstateList.getName();
			int id = eachstateList.getId();
			if (name.equals("Tamil Nadu")) {
				stateidnum = eachstateList.getId();
				Stateid = String.valueOf(id);

				System.out.println(id);
				Assert.assertEquals(id, 35, "verify the statelist TN id is 35");
			}
		}
	}

	@Test(priority = 3)
	public void CityList() {
		List<Header> listheaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listheaders.add(h1);
		listheaders.add(h2);
		Headers headers = new Headers(listheaders);
		addHeaders(headers);

		CityList_input_pojo citylist_input = new CityList_input_pojo(Stateid);
		addBody(citylist_input);

		Response addRequestType = addRequestType("POST", "https://omrbranch.com/api/cityList");
		int respondCode = getRespondCode(addRequestType);
		Assert.assertEquals(respondCode, 200, "verify the respondcode");

		CityList_output_pojo a = addRequestType.as(CityList_output_pojo.class);
		ArrayList<Citylist> data = a.getData();
		for (Citylist cityList : data) {
			String name = cityList.getName();

			if (name.equals("Madurai")) {

				cityidnum = cityList.getId();
				System.out.println(cityidnum);
				Assert.assertEquals(cityidnum, 3912, "verify the Madurai city id is 3912");
			}
		}
	}

	@Test(priority = 4)
	public void AddAddress() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

		addAdress_input_pojo addAdress_input = new addAdress_input_pojo("faiyaz", "mohammed", "9566456774", "apartment",
				stateidnum, cityidnum, 101, "625002", "2/234", "home");
		addBody(addAdress_input);

		Response addRequestType = addRequestType("POST", "https://omrbranch.com/api/addUserAddress");
		int respondCode = getRespondCode(addRequestType);
		Assert.assertEquals(respondCode, 200, "verify the response code");

		addAdress_output_pojo a = addRequestType.as(addAdress_output_pojo.class);
		String message = a.getMessage();
		Assert.assertEquals(message, "Address added successfully", "verify the add adress msg");
		address_idnum = a.getAddress_id();
		address_id = String.valueOf(address_idnum);

	}

	@Test(priority = 5)
	public void updateAddress() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

		updateAdress_input_pojo updateadress_input = new updateAdress_input_pojo(address_id, "mohammed", "fara",
				"9566456114", "apartment", stateidnum, cityidnum, 101, "625002", "2/234", "home");
		addBody(updateadress_input);

		Response addRequestType = addRequestType("PUT", "https://omrbranch.com/api/updateUserAddress");
		int respondCode = getRespondCode(addRequestType);
		Assert.assertEquals(respondCode, 200, "verify the responsecode");

		updateAdress_output_pojo a = addRequestType.as(updateAdress_output_pojo.class);
		String message = a.getMessage();
		Assert.assertEquals(message, "Address updated successfully", "verify the address update message");

	}

	@Test(priority = 6)
	public void deleteAddress() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);

		DeleteAddress_input_pojo deleadd_input = new DeleteAddress_input_pojo(address_id);
		addBody(deleadd_input);

		Response addRequestType = addRequestType("DELETE", "https://omrbranch.com/api/deleteAddress");
		int respondCode = getRespondCode(addRequestType);
		Assert.assertEquals(respondCode, 200, "verify the response code");

		DeleteAdress_output_pojo a = addRequestType.as(DeleteAdress_output_pojo.class);
		String message = a.getMessage();
		Assert.assertEquals(message, "Address deleted successfully", "verify the address delete message");
	}

	@Test(priority = 7)
	public void changeProfilePhoto() {

		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "multipart/form-data");
		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		
		addFormData("profile_picture",new File("D:\\maven\\maven work spsce\\Api2\\profile\\batman.png"));
		
		Response requestType = addRequestType("POST", "https://omrbranch.com/api/changeProfilePic");
		int respondCode = getRespondCode(requestType);
		Assert.assertEquals(respondCode,200,"verify the response code");
		
		Changeprofile_output_pojo a = requestType.as(Changeprofile_output_pojo.class);
		String message = a.getMessage();
		Assert.assertEquals(message, "Profile updated Successfully","verify profile update message");
 
	}
	@Test(priority =8)
	public void serachProduct() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		
		Search_input_pojo search_input=new Search_input_pojo("nuts");
		addBody(search_input);
		
		Response requestType = addRequestType("POST", "https://omrbranch.com/api/searchProduct");
		 int respondCode = getRespondCode(requestType);
		 Assert.assertEquals(respondCode, 200,"verify theresponse code");
		 
		 search_output_pojo a = requestType.as(search_output_pojo.class);
		 
		 String message = a.getMessage();
		 Assert.assertEquals(message, "OK","verify the search message");
		
	}
	@Test(priority = 9)
	public void getAddress() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		
		Response requestType = addRequestType("GET","https://omrbranch.com/api/getUserAddress");
		int respondCode = getRespondCode(requestType);
		Assert.assertEquals(respondCode, 200,"verfication of response code");
		
		getAddress_outpot_pojo a = requestType.as(getAddress_outpot_pojo.class);
		String message = a.getMessage();
		Assert.assertEquals(message, "OK","verify the succress message");
		
	}

}
