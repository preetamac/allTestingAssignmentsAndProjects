package salesForce.com.utility;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

//import org.apache.poi.examples.ss.CellStyleDetails;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiXlsxFileUtility 
{
	
	private static XSSFWorkbook workbook=null;
	
	//Read Specific Cell data
	public  static Object readSingleCellDataFromXLfile(File path,String sheetName,int rowNum,int cellNum) 
	{
		Object data=null;
				
		try {
			workbook = new XSSFWorkbook(path);
			//System.out.println(workbook.getSheetName(0));
			XSSFSheet sheet= workbook.getSheet(sheetName);
			XSSFRow row= sheet.getRow(rowNum);
			XSSFCell cell= row.getCell(cellNum);
			if(cell.getCellType()==CellType.NUMERIC)
			{
				data=(Object)Double.valueOf(cell.getNumericCellValue());
				//System.out.print(cell.getNumericCellValue()+" ");
			}
			else if(cell.getCellType()==CellType.STRING)
			{
				data=(Object)cell.getStringCellValue();
				//System.out.print(cell.getStringCellValue()+" ");
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

	
	//To get total row count
	public static int getTotalRowsCount(XSSFSheet sheet)
	{
		return sheet.getLastRowNum()+1;
	}
	
	//To get total cell count
	
	public static int getTotalCellCount(XSSFRow row)
	{
		return row.getLastCellNum();
	}
	
	
	
	
	//Read Entire Excel sheet data
	public  static void readallDataFromXLfile(File path,String sheetName) 
	{
		Object[][] ob=new Object[10][20];
		XSSFWorkbook workbook=null;
		try {
			workbook = new XSSFWorkbook(path);
			
		} catch (InvalidFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		XSSFSheet sheet= workbook.getSheet(sheetName);
		int RowTotal=getTotalRowsCount(sheet);
	
		Iterator<Row> rows=sheet.iterator();
			while(rows.hasNext())
			{
				Row row=rows.next();
				Iterator<Cell>cells=row.iterator();
				while(cells.hasNext())
				{
					Cell cell=cells.next();
					if(cell.getCellType()==CellType.NUMERIC)
					{
						System.out.print(cell.getNumericCellValue()+" ");
					}
					else if(cell.getCellType()==CellType.STRING)
					{
						System.out.print(cell.getStringCellValue()+" ");
					}
				}
				System.out.println();
			}
			
		
		
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//Read all Data from the sheet and return it to test method
	public  static Object readAllCellDataFromXLfile(String path,String sheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream(new File(path));
		
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum()+1;
		int columnCount=sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rowCount][columnCount];
		
		Iterator<Row> rows=sheet.rowIterator();
		int i=0,j=0;
		while(rows.hasNext())
		{
			Iterator<Cell> cells=rows.next().cellIterator();
			j=0;
			while(cells.hasNext()) {
				XSSFCell cell=(XSSFCell) cells.next();
				if(cell.getCellType()==CellType.NUMERIC) {
					data[i][j]=cell.getNumericCellValue();
				}
				else if(cell.getCellType()==CellType.STRING) {
					data[i][j]=cell.getStringCellValue();
				}
				j++;
			}
			i++;
		}
		return data;
	}
	
	
	
	//All Sheets data(All data) in a excel file
	public  static void readallSheetsDataFromXLfile(File path)
	{
		//HOmeWork
	}
	
	//Write Cell String  data
	public static void writeCelldataXlsx(File path,String sheetName,int row,int column,String data)
	{
		FileInputStream fis=null;
		XSSFWorkbook workbook=null;
		FileOutputStream fos=null;
		
		try {
			
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.getSheet(sheetName);
			
			XSSFRow rowData=sheet.getRow(row);
			
			XSSFCell cell=rowData.getCell(column);
			cell.setCellValue(data);
		    fos = new FileOutputStream(path);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		try {
			workbook.write(fos);
			fis.close();
			fos.flush();
			fos.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed writing");
		
	}
	//Read double value
	public static void writeCelldataXlsx(File path,String sheetName,int row,int column,double data)
	{
		FileInputStream fis=null;
		XSSFWorkbook workbook=null;
		FileOutputStream fos=null;
		
		try {
			
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.getSheet(sheetName);
			
			XSSFRow rowData=sheet.getRow(row);
			
			XSSFCell cell=rowData.getCell(column);
			cell.setCellValue(data);
		    fos = new FileOutputStream(path);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		try {
			workbook.write(fos);
			fis.close();
			fos.flush();
			fos.close();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed writing");
		
	}
	
	///
	public static void createSheetAndAddData(File path,String SheetName)
	{
		FileInputStream fis=null;
		XSSFWorkbook workbook=null;
		try {
			fis=new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet newSheet=workbook.createSheet(SheetName);
		XSSFRow newRow1=newSheet.createRow(0);
		
		CellStyle style = workbook.createCellStyle();  
		
		
		style.setFillForegroundColor(IndexedColors.BLUE.getIndex());  
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
        
		XSSFCell cell1=newRow1.createCell(0, CellType.STRING);
		
		
		XSSFCell cell2=newRow1.createCell(1, CellType.STRING);
		
		cell1.setCellValue("User");
		cell1.setCellStyle(style);
		cell1.setCellFormula("HYPERLINK(\"https://www.geeksforgeeks.org/\", \"click here\")");
		cell2.setCellValue("Password");
		cell2.setCellStyle(style);
		
		CellStyle style1 = workbook.createCellStyle();  
		style1.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		style1.setFillPattern(FillPatternType.BIG_SPOTS);
		
		
		XSSFRow newRow2=newSheet.createRow(1);
		XSSFCell cell3=newRow2.createCell(0, CellType.NUMERIC);
		XSSFCell cell4=newRow2.createCell(1, CellType.STRING);
		
		
		//CellStyle style=cell4.getCellStyle();
		//style.setFillBackgroundColor(IndexedColors.BLACK.getIndex());
		
		
		
		
		cell3.setCellValue("abcuser");
		cell3.setCellStyle(style1);
		
		
		cell4.setCellValue("xyzpass");
		cell4.setCellStyle(style1);
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(path);
			workbook.write(fos);
			fis.close();
			fos.flush();
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed creating new Sheet and Added data");
	}
	
	
	
	
}
