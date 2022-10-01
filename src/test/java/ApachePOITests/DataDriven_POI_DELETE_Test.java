package ApachePOITests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexHttpMessageValidators;
import com.apex.samples.core.ApexHttpUtil;

import ApachePOI.core.XLUtil;

public class DataDriven_POI_DELETE_Test implements ApachePOIConstatnt {
	
	@Test(dataProvider= "success_ids")
	public void testDelete() throws ParseException, IOException {

		String url = baseURL + "2";
		HttpResponse response = ApexHttpUtil.sendAndReceiveDELETEMesage(url);

		String result = ApexHttpUtil.getResponseString(response);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_204, STATUS_MESSAGE_NO_CONTENT);

	}

}
