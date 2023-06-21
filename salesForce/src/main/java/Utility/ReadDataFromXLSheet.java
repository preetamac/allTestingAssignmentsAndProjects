package Utility;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadDataFromXLSheet {
	
	public  static String readCellDataFromXLfile(File path,String sheetName,int rowNum,int cellNum) 
	{
		File filepath=new File(System.getProperty("user.dir")+"/src/test/resources/MyExcelSheet.xlsx");
		XSSFWorkbook workbook=null;
		String data="";
		
		try {
			workbook = new XSSFWorkbook(filepath);
			//System.out.println(workbook.getSheetName(0));
			XSSFSheet sheet= workbook.getSheet(sheetName);
			XSSFRow row= sheet.getRow(rowNum);
			XSSFCell cell= row.getCell(cellNum);
			
			if(cell.getCellType()==CellType.NUMERIC)
			{
				double value=cell.getNumericCellValue();
				data=Double.toString(value);
			}
			else if(cell.getCellType()==CellType.STRING)
			{
				data=cell.getStringCellValue();
				 
			}
		}
		catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
		
	}


}
