package com.apex.samples.core;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class ApexHttpUtil {
	public static String getResponseString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String result = "";
		if (entity != null) {
			// return it as a String
			result = EntityUtils.toString(entity);
			System.out.println(result);
		}
		return result;
	}

	public static HttpResponse sendAndReceiveGETMesage(String url) throws IOException, ClientProtocolException {
		// step1: create a http client object
		HttpClient client = HttpClientBuilder.create().build();

		// step2: create the request message
		HttpGet request = new HttpGet(url);

		// step3: send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static HttpResponse sendAndReceivePOSTMesage(String url, List<NameValuePair> params) throws IOException, ClientProtocolException {
		// step1: create a http client object
		HttpClient client = HttpClientBuilder.create().build();

		// step2: create the request message
		HttpPost request = new HttpPost(url);
		request.setEntity(new UrlEncodedFormEntity(params));

		// step3: send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static HttpResponse sendAndReceivePUTMesage(String url, List<NameValuePair> params) throws IOException, ClientProtocolException {
		// step1: create a http client object
		HttpClient client = HttpClientBuilder.create().build();

		// step2: create the request message
		HttpPut request = new HttpPut(url);
		request.setEntity(new UrlEncodedFormEntity(params));

		// step3: send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static HttpResponse sendAndReceivePATCHMesage(String url, List<NameValuePair> params) throws IOException, ClientProtocolException {
		// step1: create a http client object
		HttpClient client = HttpClientBuilder.create().build();

		// step2: create the request message
		HttpPatch request = new HttpPatch(url);
		request.setEntity(new UrlEncodedFormEntity(params));

		// step3: send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public static HttpResponse sendAndReceiveDELETEMesage(String url) throws IOException, ClientProtocolException {
		// step1: create a http client object
		HttpClient client = HttpClientBuilder.create().build();

		// step2: create the request message
		HttpDelete request = new HttpDelete(url);

		// step3: send and get the response message
		HttpResponse response = client.execute(request);
		return response;
	}
}
