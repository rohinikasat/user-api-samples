package com.apex.samples;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.apex.samples.core.ApexBaseAPITest;
import com.apex.samples.core.ApexHttpMessageValidators;
import com.apex.samples.core.ApexHttpUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class UserAPI_JXL_Datadriven_GET_Test_NG extends ApexBaseAPITest implements UserAPIConstatnt{

	@DataProvider(name="success_ids")
	public Object[][] getSuccessfulIds(){
//		String [][] testIds = {
//				{"2", "janet.weaver@reqres.in", "Jane"},
//				{"1", "george.bluth@reqres.in", "George"}
//		};
		return getTableArray("TestIds.xls", "Sheet1", "SuccessIds");
	}
	
	@Test(dataProvider= "success_ids")
	public void testWithCorrectUserID(String id, String email, String name) throws ClientProtocolException,ParseException, IOException {
		
		String url = baseURL + id;
		HttpResponse response = ApexHttpUtil.sendAndReceiveGETMesage(url);

		String result = ApexHttpUtil.getResponseString(response);
		System.out.println(result);
		
		ApexHttpMessageValidators.performBasicValidations(response, STATUS_CODE_200,STATUS_MESSAGE_OK);
		
		Assert.assertTrue(result.contains("id"));
		Assert.assertTrue(result.contains(email));
		Assert.assertTrue(result.contains(name));
		
	}

	public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName){
        String[][] tabArray=null;
        try{
            Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
            Sheet sheet = workbook.getSheet(sheetName);
            int startRow,startCol, endRow, endCol,ci,cj;
            Cell tableStart=sheet.findCell(tableName);
            startRow=tableStart.getRow();
            startCol=tableStart.getColumn();

            Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                               

            endRow=tableEnd.getRow();
            endCol=tableEnd.getColumn();
            System.out.println("startRow="+startRow+", endRow="+endRow+", " +
                    "startCol="+startCol+", endCol="+endCol);
            tabArray=new String[endRow-startRow-1][endCol-startCol-1];
            ci=0;

            for (int i=startRow+1;i<endRow;i++,ci++){
                cj=0;
                for (int j=startCol+1;j<endCol;j++,cj++){
                    tabArray[ci][cj]=sheet.getCell(j,i).getContents();
                }
            }
        }
        catch (Exception e)    {
            System.out.println("error in getTableArray()");
        }

        return(tabArray);
    }
}
