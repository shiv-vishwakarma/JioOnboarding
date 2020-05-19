package apachePoiHandsOn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DemoPOI {

	public static void main(String[] args) {
		try {
			
			Workbook wb = WorkbookFactory.create(new File("./ExcelFile.xlsx"));
			Sheet sheet1 = wb.getSheetAt(0);
			
			int rowStart = sheet1.getFirstRowNum();
			int rowEnd = sheet1.getLastRowNum();
			
			ArrayList <DummyExcel> excelList = new ArrayList<>();
			
			for(int i = rowStart+1 ; i<=rowEnd; i++)
			{
				DummyExcel de = new DummyExcel();
				Row row = sheet1.getRow(i);
				//String[] temp = new String[row.getLastCellNum()];
				
				for(int j = row.getFirstCellNum() + 1; j < row.getLastCellNum(); j++)
				{
					String cell = row.getCell(j).toString();
					//System.out.println(cell.toString());
					switch (j) { 
			        case 1: de.setCategory(cell); 
			            	break; 
			            	
			        case 2: de.setParameterName(cell); 
			            	break; 
			            	
			        case 3: de.setDefaultvalue(cell); 
			            	break; 
			            	
			        case 4: de.setDatatype(cell);
			            	break; 
			            	
			        case 5: de.setParameterDescription(cell);
			            	break; 
			            	
			        case 6: de.setVerificationProcedure(cell);
			            	break; 
			            	
			        case 7: de.setParameterType(cell);
			            	break; 
			            	
			        case 8:	de.setTunedvalue(cell);
			        		break;
			        		
			        default: 
			            	break; 
			        } 
					                   
				}
				excelList.add(de);
			}
			
			for(DummyExcel dex: excelList){
                System.out.println(dex);
                System.out.println();
            }
			
			addToExcel(excelList);	
		
		/*
		 * Sheet sheet2 = wb.createSheet("JavaCreatedSheet"); Row row =
		 * sheet2.createRow(0); Cell name = row.createCell(0);
		 * name.setCellValue("Writing from Java to Excel"); wb.write(new
		 * FileOutputStream("./NewFile.xlsx"));
		 */
		 
		wb.close();
			
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void addToExcel(List<DummyExcel> list) {
		
		Object[] instanceVariables = list.getClass().getDeclaredFields();
		int tempColumnCount = instanceVariables.length;
		int totalNumOfColumns = tempColumnCount + 1;
		//System.out.println("totalNumOfColumns >> "+totalNumOfColumns);
		  
		int totalNumOfRows = list.size(); 
		//System.out.println("totalRows > "+totalNumOfRows);
	try {
		
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet2 = wb.createSheet("JavaCreatedSheet");
			CreationHelper createHelper = wb.getCreationHelper();
			
			Font font = wb.createFont();
			font.setFontHeightInPoints((short)10);
			font.setFontName("Courier New");
			font.setBold(true);
			font.setColor(IndexedColors.ROYAL_BLUE.getIndex());
			
			
			CellStyle style = wb.createCellStyle();
			style.setFont(font);
			style.setWrapText(true);
			style.setBorderBottom(BorderStyle.MEDIUM);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.MEDIUM);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.MEDIUM);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.MEDIUM);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
    		style.setFillPattern(FillPatternType.LEAST_DOTS);
			
			
			
			  
			for(int i = 0; i<totalNumOfRows; i++)
			{ 
				  DummyExcel deWrite = list.get(i); 
				  Row row = sheet2.createRow(i);
				  //row.setHeightInPoints(50);
				  row.setHeightInPoints((3*sheet2.getDefaultRowHeightInPoints()));
				 				  
				  for(int j = 0; j<totalNumOfColumns; j++)
				  {
					  Cell cell = row.createCell(j);
					  
					 					  
					  switch (j) { 
				        case 0: //CellStyle style1 = wb.createCellStyle();
				        		//style1.setFillBackgroundColor(IndexedColors.PINK.getIndex());
				        		cell.setCellValue(deWrite.getCategory());
				        		cell.setCellStyle(style);
				        		//cell.setCellStyle(style1);
				            	break; 
				            	
				        case 1: cell.setCellValue(deWrite.getParameterName());
				        		cell.setCellStyle(style);
				            	break; 
				            	
				        case 2: cell.setCellValue(deWrite.getDefaultvalue());       						 
				        		cell.setCellStyle(style);
				            	break; 
				            	
				        case 3: cell.setCellValue(deWrite.getDatatype());	
				        		cell.setCellStyle(style);
				            	break; 
				            	
				        case 4: cell.setCellValue(deWrite.getParameterDescription());
				        		cell.setCellStyle(style);
				            	break; 
				            	
				        case 5: cell.setCellValue(deWrite.getVerificationProcedure());
				        		cell.setCellStyle(style);
				            	break; 
				            	
				        case 6: cell.setCellValue(deWrite.getParameterType());
				        		cell.setCellStyle(style);
				            	break; 
				            	
				        case 7:	cell.setCellValue(deWrite.getTunedvalue());
				        		cell.setCellStyle(style);
				        		break;
				        		
				        default: 
				            	break; 
				        } 
					  
				  }
			}
			
			Cell cell = sheet2.createRow(13).createCell(0);
			cell.setCellValue("URL Link");
			XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
			link.setAddress("https://poi.apache.org/");
			cell.setHyperlink((XSSFHyperlink) link);
			
			for(int columnIndex = 0; columnIndex < totalNumOfRows; columnIndex++) {
			     sheet2.autoSizeColumn(columnIndex);
			}
			
			sheet2.addMergedRegion(new CellRangeAddress(0, 0, 2, 3));

			FileOutputStream fos = new FileOutputStream("./JavaToExcel.xlsx");
			wb.write(fos);
			wb.close();
			fos.close();
			
			
	} catch (EncryptedDocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 

	}
}
	 
