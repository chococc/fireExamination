package com.sxbang.friday.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.*;


public class ReadExcelUtils {
	private Logger logger = LoggerFactory.getLogger(ReadExcelUtils.class);
	private Workbook wb;
	private Sheet sheet;
	private Row row;

	public ReadExcelUtils(String fileName,InputStream is){
		String ext = fileName.substring(fileName.lastIndexOf("."));
		try{
			if(".xls".equals(ext)){
				wb = new HSSFWorkbook(is);
			}else{
				wb=new XSSFWorkbook(is);
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}

	}
	public ReadExcelUtils(String filepath) {
		if(filepath==null){
			return;
		}
		String ext = filepath.substring(filepath.lastIndexOf("."));
		try {
			InputStream is = new FileInputStream(filepath);
			if(".xls".equals(ext)){
				wb = new HSSFWorkbook(is);
			}else{
				wb=new XSSFWorkbook(is);
			}
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
	}
	
	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @return String 表头内容的数组
	 */
	public String[] readExcelTitle() throws Exception{
		if(wb==null){
			throw new Exception("Workbook对象为空！");
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		
		System.out.println("colNum:" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			//title[i] = getStringCellValue(row.getCell((short) i));
			row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
			title[i] = row.getCell(i).getStringCellValue();
		}
		return title;
	}
 
	/**
	 * 读取Excel数据内容
	 * 
	 * @return Map 包含单元格数据内容的Map对象
	 */
	public Map<Integer, Map<Integer,Object>> readExcelContent() throws Exception{
		if(wb==null){
			throw new Exception("Workbook对象为空！");
		}
		Map<Integer, Map<Integer,Object>> content = new HashMap<Integer, Map<Integer,Object>>();
		
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			int j = 0;
			Map<Integer,Object> cellValue = new HashMap<Integer, Object>();
			while (j < colNum) {
				Object obj = getCellFormatValue(row.getCell(j));
				cellValue.put(j, obj);
				j++;
			}
			content.put(i, cellValue);
		}
		return content;
	}
 
	/**
	 * 
	 * 根据Cell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private Object getCellFormatValue(Cell cell) {
		Object cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case Cell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (DateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式
					// data格式是带时分秒的：2013-7-10 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// data格式是不带带时分秒的：2013-7-10
					Date date = cell.getDateCellValue();
					cellvalue = date;
				} else {// 如果是纯数字
 
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			default:// 默认的Cell值
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
 
	private static String getValue(HSSFCell hssfCell) {
	        if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(hssfCell.getBooleanCellValue());
	        } else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
	            Double d = hssfCell.getNumericCellValue();
	            DecimalFormat df = new DecimalFormat("0");
	            return df.format(d);
	        } else {
	            return String.valueOf(hssfCell.getStringCellValue().trim());
	        }
	    }
	
	
	public static void main(String[] args) {
//    	GridResult<Map<String, Object>> result = new GridResult<Map<String, Object>>();
    	String titleName = "";
    	try{
    		ReadExcelUtils excelReader = new ReadExcelUtils("E://云公司研发中心外包人员面试表.xlsx");
        	String[] title = excelReader.readExcelTitle();
        	for (String s : title) {
        		titleName = titleName+s+",";
    		}
    	}catch (Exception e) {
    		System.out.println("未找到指定路径的文件!");
			e.printStackTrace();
		}
    	
        try {
            InputStream stream = new FileInputStream("D:\\FileRecv\\accumRepairTemplate.xls");
            HSSFWorkbook work = new HSSFWorkbook(stream);
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < work.getNumberOfSheets(); i++) {
                HSSFSheet hssfSheet = work.getSheetAt(i);
                for (int j = 1; j <= hssfSheet.getLastRowNum(); j++) {
                    HSSFRow hssfRow = hssfSheet.getRow(j);
                    if (hssfRow != null) {
                        Map<String, Object> map = new HashMap<String, Object>();
                        for(int z=0;z<titleName.split(",").length;z++){
                        	String key=titleName.split(",")[z];
                        	try{
                        		map.put(key, getValue(hssfRow.getCell(z)));
                        	}catch (Exception e) {
                        		map.put(key, "");
							}
                        	
                        	
                        }
                        list.add(map);
                    }
                }
            }
//            result.setResultCode(0);
//            result.setRecordList(list);
        } catch (IOException e) {
//            result.setResultCode(1);
//            result.setResultMsg("文件读取异常：" +ExceptionUtil.getStackTraceDetail(e));
        }
	}

}
