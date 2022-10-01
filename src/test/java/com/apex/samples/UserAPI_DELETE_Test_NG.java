package com.apex.samples;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexBaseAPITest;
import com.apex.samples.core.ApexHttpMessageValidators;
import com.apex.samples.core.ApexHttpUtil;

public class UserAPI_DELETE_Test_NG extends ApexBaseAPITest implements UserAPIConstatnt{
	
	
	@Test
	public void testDelete() throws ParseException, IOException {
		
		String url = baseURL + "2";
		HttpResponse response = ApexHttpUtil.sendAndReceiveDELETEMesage(url);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_204,STATUS_MESSAGE_NO_CONTENT);

	}

	

}