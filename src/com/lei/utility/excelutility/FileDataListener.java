package com.lei.utility.excelutility;

import java.util.List;

import com.lei.exception.common.ProcessFailedException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public interface FileDataListener {
	public void skippedData(List<String> rowData)throws ProcessFailedException;
	public void gotData(List<String> rowData)throws ProcessFailedException;
	public void finish()throws ProcessFailedException;
}
