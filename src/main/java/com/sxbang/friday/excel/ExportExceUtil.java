/**
 * Copyright (c) 1987-2010 Fujian Fujitsu Communication Software Co., 
 * Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of 
 * Fujian Fujitsu Communication Software Co., Ltd. 
 * ("Confidential Information"). You shall not disclose such 
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with FFCS.
 *
 * FFCS MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. FFCS SHALL NOT BE LIABLE FOR
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
 */
package com.sxbang.friday.excel;

import org.apache.poi.hssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ExportExceUtil {

	public <T> void exportExcel(Map<String, String> headerMap, List<Map<String, T>> datas, String title, File file) throws IOException {
		String[] headerNames = new String[headerMap.size()];
		String[] headerKeys = new String[headerMap.size()];
		Iterator<Entry<String, String>> it = headerMap.entrySet().iterator();
		int i = 0;
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			headerNames[i] = entry.getValue();
			headerKeys[i] = entry.getKey();
			i++;
		}
		this.exportExcel(headerNames, headerKeys, datas, title, file);

	}

	public <T> void exportExcel(String[] headerNames, String[] headerKeys, List<Map<String, T>> datas, String title, File file) throws IOException {
		int i = 0;
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(title);

		HSSFRow row;
		row = sheet.createRow(0);
		for (int j = 0; j < headerNames.length; j++) {
			HSSFCell cell = row.createCell(j);
			HSSFRichTextString text = new HSSFRichTextString(headerNames[j]);
			cell.setCellValue(text);
		}

		for (i = 0; i < datas.size(); i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < headerKeys.length; j++) {
				HSSFCell cell = row.createCell(j);
				String key = headerKeys[j];
				T data = datas.get(i).get(key);
				HSSFRichTextString text = new HSSFRichTextString(data.toString());
				cell.setCellValue(text);
			}
		}
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			workbook.write(out);
		} catch (IOException e) {
			throw e;
		} finally {
			out.close();
			workbook.close();
		}

	}
	
    /**
     */
    public  <T> HSSFWorkbook exportExcel(String[] headerNames, String[] headerKeys,
        List<Map<String, T>> datas, String title) throws IOException {
        int i = 0;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(title);

        HSSFRow row;
        row = sheet.createRow(0);
        for (int j = 0; j < headerNames.length; j++) {
            HSSFCell cell = row.createCell(j);
            HSSFRichTextString text = new HSSFRichTextString(headerNames[j]);
            cell.setCellValue(text);
        }

        for (i = 0; i < datas.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < headerKeys.length; j++) {
                HSSFCell cell = row.createCell(j);
                String key = headerKeys[j];
                T data = datas.get(i).get(key); 
                HSSFRichTextString text = new HSSFRichTextString(data.toString());
                cell.setCellValue(text);
            }
                 
        } 
       workbook.close(); 
       return workbook;
    }
 
}
