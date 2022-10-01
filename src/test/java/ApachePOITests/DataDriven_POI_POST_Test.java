package ApachePOITests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexHttpMessageValidators;
import com.apex.samples.core.ApexHttpUtil;

import ApachePOI.core.HTTPUtil;
import ApachePOI.core.XLUtil;


public class DataDriven_POI_POST_Test implements ApachePOIConstatnt{

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path = null;
	
	static final String baseURL = "https://reqres.in/api/";
	
	
	@Test
	public void testPostLogin() throws ParseException, IOException {
		
		String url = baseURL + "login";
		
		path = "TestPostIds.xlsx";
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet("Sheet1");
		
		String email = sheet.getRow(1).getCell(1).getStringCellValue();
		String password = sheet.getRow(1).getCell(3).getStringCellValue();	
		
		System.out.println(email);
		System.out.println(password);
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		
		HttpResponse response =HTTPUtil.sendAndReceivePOSTMesage(url, params);

		String result = HTTPUtil.getResponseString(response);
		System.out.println(result);

		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
	}
	
}
