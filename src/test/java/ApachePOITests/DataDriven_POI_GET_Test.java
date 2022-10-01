package ApachePOITests;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexHttpMessageValidators;

import ApachePOI.core.HTTPUtil;
import ApachePOI.core.XLUtil;

public class DataDriven_POI_GET_Test implements ApachePOIConstatnt{
	
	@DataProvider(name="success_ids")
	public Object[][] getSuccessfulIds() throws IOException{	
		return XLUtil.getData("TestIds3.xlsx", "Sheet1", "SuccessIds");
	}
	
	
	@Test(dataProvider= "success_ids")
	public void testWithCorrectUserID(String id, String email, String name) throws ClientProtocolException,ParseException, IOException {
		
		String url = baseURL + id;
		HttpResponse response = HTTPUtil.sendAndReceiveGETMesage(url);

		String result = HTTPUtil.getResponseString(response);
		System.out.println(result);
		
		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
		
		Assert.assertTrue(result.contains("id"));
		Assert.assertTrue(result.contains(email));
		Assert.assertTrue(result.contains(name));
		
	}
}
