package com.sxbang.friday.excel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;

/**
 * excle工具 没用到
 */
public class ExcelUtil {


	public static List<Map<Integer, String>> excelToListMap(InputStream stream) {
		List<Map<Integer, String>> resultlist = new LinkedList<Map<Integer, String>>();
		StringBuffer errorMsg = new StringBuffer();

		try {
			@SuppressWarnings("resource")
			HSSFSheet hssfSheet = new HSSFWorkbook(stream).getSheetAt(0); // 第一个工作表
			int minColIx = -1; // 单元格起点
			int maxColIx = -1; // 单元格终点
			int lineNum = 0;
			int columNum = 0;
			for (int i = hssfSheet.getFirstRowNum(); i <= hssfSheet
					.getLastRowNum(); i++) {
				lineNum = i + 1;
				HSSFRow hssfRow = hssfSheet.getRow(i);
				if (null == hssfRow) {
					errorMsg.append("文件第" + lineNum+ "行为空");
					continue;
				}

				minColIx = hssfRow.getFirstCellNum();
				maxColIx = hssfRow.getLastCellNum();
				if (minColIx == -1 || maxColIx == -1) {
					errorMsg.append("文件第" + lineNum+ "行没有任何单元格");
					continue;
				}
				Map<Integer, String> mapLine = new HashMap<Integer, String>();
				for (int j = minColIx; j < maxColIx; j++) {
					try {
						columNum = j + 1;
						if (hssfRow.getCell(j) == null) {
							errorMsg.append("文件第"+ lineNum + "行第" + columNum+ "个单元格为空");
							continue;
						}
						hssfRow.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
						if (StringUtils.isEmpty(hssfRow.getCell(j).getStringCellValue())) {
							errorMsg.append("文件第"+ lineNum + "行第" + columNum+ "个单元格为空");
							continue;
						}
						String cellValue = hssfRow.getCell(j).getStringCellValue().trim();
						mapLine.put(columNum, cellValue);
					} catch (Exception e) {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						e.printStackTrace(pw);
						errorMsg.append("文件第"+ lineNum + "行第" + columNum+ "个单元格名称读取错误,错误信息:"+ sw.toString());
						continue;
					}
				}
				resultlist.add(mapLine);
			}
			
			return resultlist;
		} catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			return null;
		} finally {
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
