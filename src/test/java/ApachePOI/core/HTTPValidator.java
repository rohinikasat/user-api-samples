package ApachePOI.core;

import org.apache.http.HttpResponse;
import org.testng.Assert;

public class HTTPValidator {
	public static void performBasicValidations(HttpResponse response, int expcode, String expectedMessage) {
		Assert.assertEquals(response.getStatusLine().getStatusCode(), expcode);
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), expectedMessage);
	}
}
