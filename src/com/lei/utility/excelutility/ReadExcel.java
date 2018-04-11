package com.lei.utility.excelutility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.lei.exception.common.ProcessFailedException;
import com.lei.utility.CommonUtils;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class ReadExcel {
	private FileDataListener listener;
	int skipTopRows = 1;
	int sheetNumber = 2-1;
	public ReadExcel(FileDataListener listener) {
		this.listener = listener;
	}
	public void readExcel(String filePath) throws IOException, EncryptedDocumentException, InvalidFormatException, ProcessFailedException{        
		BufferedInputStream inputStream = CommonUtils.getBufferedInputStream(filePath);
		if(inputStream!=null){
			Workbook wb = WorkbookFactory.create(new FileInputStream(new File(filePath)));
			if(wb.getSheetAt(sheetNumber)!=null){
				Sheet sheet = wb.getSheetAt(sheetNumber);
				Iterator<Row> rowIterator = sheet.iterator();
				for(int start=0;start<skipTopRows;start++){
					if(rowIterator.hasNext()){
						Row row = rowIterator.next();
						List<String> rowData = getRowData(row);
						listener.skippedData(rowData);
					}
				}
				while(rowIterator.hasNext()){
					Row row = rowIterator.next();
					List<String> rowData = getRowData(row);
					listener.gotData(rowData);
				}
				listener.finish();
			}
		}
	}
	
	private List<String> getRowData(Row row){
		List<String> rowData = null;
		
		int startCellNumber = row.getFirstCellNum();
		int lastCellNumber = row.getLastCellNum();
		
		rowData = new ArrayList<String>();
		
		for(int i=0;i<lastCellNumber;i++){
			String cellValue = null;
			if(row.getCell(i)!=null){
				int cellType = row.getCell(i).getCellType();
				if(cellType == Cell.CELL_TYPE_NUMERIC){
					if(HSSFDateUtil.isCellDateFormatted(row.getCell(i))){
						if(row.getCell(i).getDateCellValue()!=null){
							cellValue = ""+row.getCell(i).getDateCellValue();
						}
					}else{
						cellValue = ""+row.getCell(i).getNumericCellValue();
					}
				}else {
					cellValue = row.getCell(i).getStringCellValue();
				}
			}
			rowData.add(cellValue);
		}
		return rowData;
	}
	
	
}
