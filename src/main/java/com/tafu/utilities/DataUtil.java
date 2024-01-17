package com.tafu.utilities;

import java.util.Hashtable;

import com.tafu.genericLibrary.ExcelLibrary;

public class DataUtil {
	
	public static Object[][] getData(String testName,String sheetName,ExcelLibrary xls){
		int testStartRowNum=0;
		// find the row num from which test starts
		for(int rNum=1;rNum<=xls.getRowCount(sheetName);rNum++){
			if(xls.getCellData(sheetName, 0, rNum).equals(testName)){
				testStartRowNum=rNum;
				break;
			}
		}
		//System.out.println("Test "+ testName +" starts from "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int totalCols=0;
		while(!xls.getCellData(sheetName, totalCols, colStartRowNum).equals("")){
			totalCols++;
		}
		//System.out.println("Total Cols in test "+ testName + " are "+ totalCols);
		//rows
		int dataStartRowNum=testStartRowNum+2;
		int totalRows=0;
		while(!xls.getCellData(sheetName, 0, dataStartRowNum+totalRows).equals("")){
			totalRows++;
		}
		//System.out.println("Total Rows in test "+ testName + " are "+ totalRows);
		
		Object[][] data = new Object[totalRows][1];
		int index=0;
		Hashtable<String,String> table=null;
		for(int rNum=dataStartRowNum;rNum<(dataStartRowNum+totalRows);rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<totalCols;cNum++){
				table.put(xls.getCellData(sheetName, cNum, colStartRowNum), xls.getCellData(sheetName, cNum, rNum));
				//System.out.print(xls.getCellData(sheetName, cNum, rNum)+" ");
			}
			table.put("RowNum", String.valueOf(rNum));
			data[index][0]= table;
			index++;
			//System.out.println();
		}	
		return data;
	}
	
}

