package com.apex.samples;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexBaseAPITest;
import com.apex.samples.core.ApexHttpMessageValidators;
import com.apex.samples.core.ApexHttpUtil;

public class UserAPI_PATCH_Test_NG extends ApexBaseAPITest implements UserAPIConstatnt{

	static final String baseURL = "https://reqres.in/api/";

	@Test
	public void testPATCH() throws ParseException, IOException {
		String url = baseURL + "2";
		

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("name", "morpheus"));
		params.add(new BasicNameValuePair("job", "zion resident"));
		
		HttpResponse response =ApexHttpUtil.sendAndReceivePATCHMesage(url, params);
		
		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
	}

}