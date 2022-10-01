package com.apex.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexBaseAPITest;
import com.apex.samples.core.ApexHttpMessageValidators;
import com.apex.samples.core.ApexHttpUtil;

public class UserAPI_POST_Test_NG extends ApexBaseAPITest implements UserAPIConstatnt{

	static final String baseURL = "https://reqres.in/api/";


	@Test
	public void testPostLogin() throws ParseException, IOException {
		String url = baseURL + "login";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", "eve.holt@reqres.in"));
		params.add(new BasicNameValuePair("password", "cityslicka"));
		
		HttpResponse response =ApexHttpUtil.sendAndReceivePOSTMesage(url, params);

		String result = ApexHttpUtil.getResponseString(response);
		System.out.println(result);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
	}

	@Test
	public void testPostLoginUnsuccessful() throws ParseException, IOException {
		String url = baseURL + "login";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", "eve.holt@reqres.in"));
		
		HttpResponse response =ApexHttpUtil.sendAndReceivePOSTMesage(url, params);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_400,STATUS_MESSAGE_BAD_REQUEST);
	}

	@Test
	public void testPostCreateUser() throws ParseException, IOException {
		String url = baseURL + "users";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", "Rohini"));
		params.add(new BasicNameValuePair("job", "student"));
		
		HttpResponse response =ApexHttpUtil.sendAndReceivePOSTMesage(url, params);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_201,STATUS_MESSAGE_CREATED);
	}

	@Test
	public void testPostRegister() throws ParseException, IOException {
		String url = baseURL + "register";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", "eve.holt@reqres.in"));
		params.add(new BasicNameValuePair("password", "pistol"));
		
		HttpResponse response =ApexHttpUtil.sendAndReceivePOSTMesage(url, params);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
	}

	@Test
	public void testPostRegisterUnsuccessful() throws ParseException, IOException {
		String url = baseURL + "register";
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", "eve.holt@reqres.in"));
		
		HttpResponse response =ApexHttpUtil.sendAndReceivePOSTMesage(url, params);
		
		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_400,STATUS_MESSAGE_BAD_REQUEST);
	}

}