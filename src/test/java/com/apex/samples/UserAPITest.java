package com.apex.samples;


import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.net.http.HttpRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

public class UserAPITest {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		//step1: create a http client object
		HttpClient client = HttpClientBuilder.create().build();
		
		//step2: create the request message
		HttpGet request = new HttpGet("https://reqres.in/api/users/2");
		
		//step3: send and get the response message
		HttpResponse response = client.execute(request);
			
		//step4: validate the response message
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		
		HttpEntity entity = response.getEntity();
        if (entity != null) {
            // return it as a String
            String result = EntityUtils.toString(entity);
            System.out.println(result);
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");
        
       
	}
        
}
