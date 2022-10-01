package com.apex.samples;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexBaseAPITest;
import com.apex.samples.core.ApexHttpMessageValidators;
import com.apex.samples.core.ApexHttpUtil;

public class UserAPI_GET_Test_NG extends ApexBaseAPITest implements UserAPIConstatnt{

	@DataProvider(name="success_ids")
	public Object[][] getSuccessfulIds(){
		String [][] testIds = {
				{"2", "janet.weaver@reqres.in", "Janet"},
				{"1", "george.bluth@reqres.in", "George"}
		};
		return testIds;
	}
	
	@Test(dataProvider = "success_ids")
	public void testWithCorrectUserID() throws ParseException, IOException {
		
		String url = baseURL + "2";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMesage(url);

		String result = ApexHttpUtil.getResponseString(response);
		
		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
		
	}

	@Test
	public void testWithANonNumber() throws ClientProtocolException, IOException {
		String url = baseURL + "abc";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMesage(url);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_404,STATUS_MESSAGE_NOT_FOUND);
	}

	@Test
	public void testWithSpecialCharacter() throws ClientProtocolException, IOException {
		String url = baseURL + "&@";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMesage(url);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_404,STATUS_MESSAGE_NOT_FOUND);
	}

	@Test
	public void testWithBlank() throws ClientProtocolException, IOException {
		String url = baseURL + "";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMesage(url);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
	}

	@Test
	public void testWithLongNumber() throws ClientProtocolException, IOException {
		String url = baseURL + "27437625857943505";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMesage(url);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_404,STATUS_MESSAGE_NOT_FOUND);
	}
	
	@Test
	public void testWithNonExistingUserId() throws ClientProtocolException, IOException {
		String url = baseURL + "13";
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMesage(url);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_404,STATUS_MESSAGE_NOT_FOUND);

	}
}
