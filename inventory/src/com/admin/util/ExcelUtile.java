package com.admin.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtile
{
	
	/**
     * 导出报表
     * 
     * @param workbook
     * @param response
     * @return
     */
    public static boolean download(XSSFWorkbook workbook, HttpServletResponse response,
        String fileName)
        throws UnsupportedEncodingException
    {
        // 开始下载
        // 设置ContentType字段值
        response.setContentType("text/html;charset=utf-8");
        
        response.addHeader("Content-type",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        
        response.addHeader("Content-Disposition",
            "attachment;filename=" + URLEncoder.encode(fileName, "utf-8")
                + ".xls");
        
        try
        {
            OutputStream os = response.getOutputStream();
            
            workbook.write(os);
            
            os.flush();
            
            workbook.close();
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
    
    public static void excelUtil(HSSFWorkbook workbook, String[] header,
        HSSFSheet sheet, List<List<String>> dataContent, int size,
        String dateTime)
    {
        HSSFFont f = workbook.createFont();
        
        HSSFCellStyle style = workbook.createCellStyle();
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//左右居中
        // 表头数据
        // 设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(size);
        // 创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        // 遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++)
        {
            // 创建一个单元格
            HSSFCell cell = headrow.createCell(i);
            // 创建一个内容对象
            // XSSFRichTextString text = new XSSFRichTextString(header[i]);
            // 将内容对象的文字内容写入到单元格中
            
            cell.setCellValue(header[i]);
            
            cell.setCellStyle(style);
        }
        
        HSSFRow row1 = null;
        
        for (int i = 0; i < dataContent.size(); i++)
        {
            List<String> strings = dataContent.get(i);
            
            row1 = sheet.createRow(i + 1);
            
            for (int j = 0; j < strings.size(); j++)
            {
                HSSFCell cell = row1.createCell(j);
                
                if (isNumeric(strings.get(j)))
                {
                    if (isInteger(strings.get(j)))
                    {
                        style.setDataFormat(
                            HSSFDataFormat.getBuiltinFormat("0"));
                        
                        cell.setCellValue(Long.parseLong(strings.get(j)));
                        
                        cell.setCellStyle(style);
                    }
                    else
                    {
                        style.setDataFormat(
                            HSSFDataFormat.getBuiltinFormat("0.00"));
                        
                        cell.setCellValue(Double.parseDouble(strings.get(j)));
                        
                        cell.setCellStyle(style);
                    }
                }
                else
                {
                    HSSFRichTextString text =
                        new HSSFRichTextString(strings.get(j));
                    
                    cell.setCellValue(text);
                    
                    cell.setCellStyle(style);
                }
            }
        }
        
        HSSFRow row2 = sheet.createRow(dataContent.size() + 1);
        
        HSSFCell cell = row2.createCell(0);
        
        HSSFCellStyle style1 = workbook.createCellStyle();
        
        HSSFFont font = workbook.createFont();
        // font.setColor(HSSFColor.RED.index);//HSSFColor.VIOLET.index //字体颜色
        if (dateTime == null)
        {
            dateTime = "未添加时间";
        }
        
        cell.setCellValue("备注：当前时间：" + dateTime);
        
        style1.setFont(font);
        
        cell.setCellStyle(style1);
        // 合并单元格
        // 获取最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
        // 行和列都是从0开始计数，且起始结束都会合并
        CellRangeAddress region =
            new CellRangeAddress(lastRowNum, lastRowNum, 0, header.length - 1);
        
        sheet.addMergedRegion(region);
    }
    
    public static void excelUtilPoi(XSSFWorkbook workbook, String[] header,
        XSSFSheet sheet, List<List<String>> dataContent, int size,
        String dateTime)
    {
        XSSFCellStyle style = workbook.createCellStyle();
        
        XSSFDataFormat df = workbook.createDataFormat();
        // style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//左右居中
        // 表头数据
        // 设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(size);
        // 创建第一行表头
        XSSFRow headrow = sheet.createRow(0);
        // 遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++)
        {
            // 创建一个单元格
            XSSFCell cell = headrow.createCell(i);
            // 创建一个内容对象
            // XSSFRichTextString text = new XSSFRichTextString(header[i]);
            // 将内容对象的文字内容写入到单元格中
            cell.setCellValue(header[i]);
            
            cell.setCellStyle(style);
        }
        
        XSSFRow row1 = null;
        
        for (int i = 0; i < dataContent.size(); i++)
        {
            List<String> strings = dataContent.get(i);
            
            row1 = sheet.createRow(i + 1);
            
            for (int j = 0; j < strings.size(); j++)
            {
                XSSFCell cell = row1.createCell(j);
                
                if (isNumeric(strings.get(j)))
                {
                    if (isInteger(strings.get(j)))
                    {
                        style.setDataFormat(
                            HSSFDataFormat.getBuiltinFormat("0"));
                        
                        cell.setCellStyle(style);
                        
                        cell.setCellValue(Long.parseLong(strings.get(j)));
                    }
                    else
                    {
                        style.setDataFormat(
                            HSSFDataFormat.getBuiltinFormat("0.00"));
                        
                        cell.setCellStyle(style);
                        
                        cell.setCellValue(Double.parseDouble(strings.get(j)));
                    }
                }
                else
                {
                    XSSFRichTextString text =
                        new XSSFRichTextString(strings.get(j));
                    
                    cell.setCellValue(text);
                    
                    cell.setCellStyle(style);
                }
            }
        }
        
        XSSFRow row2 = sheet.createRow(dataContent.size() + 1);
        
        XSSFCell cell = row2.createCell(0);
        
        XSSFCellStyle style1 = workbook.createCellStyle();
        
        XSSFFont font = workbook.createFont();
        // font.setColor(XSSFColor.RED.index);//XSSFColor.VIOLET.index //字体颜色
        if (dateTime == null)
        {
            dateTime = "未添加时间";
        }
        
        cell.setCellValue("备注：当前时间：" + dateTime);
        
        style1.setFont(font);
        
        cell.setCellStyle(style1);
        // 合并单元格
        // 获取最后一行的行号
        int lastRowNum = sheet.getLastRowNum();
        // 合并日期占两行(4个参数，分别为起始行，结束行，起始列，结束列)
        // 行和列都是从0开始计数，且起始结束都会合并
        CellRangeAddress region =
            new CellRangeAddress(lastRowNum, lastRowNum, 0, header.length - 1);
        
        sheet.addMergedRegion(region);
    }
    
    public void excelActList(XSSFWorkbook workbook,
        List<Map<String, String>> list)
    {
        
    }
    
    public static void main(String[] args)
        throws Exception
    {
        XSSFWorkbook workBook = new XSSFWorkbook();
        
        XSSFSheet sheet = workBook.createSheet();
        
        String[] head = {"姓名", "年龄", "性别"};
        
        List<List<String>> data = new ArrayList<>();
        
        for (int i = 0; i < 1000000; i++)
        {
            List<String> list = new ArrayList<>();
            
            list.add("20200803");
            
            list.add("1" + i);
            
            list.add("男" + i);
            
            data.add(list);
        }
        
        excelUtilPoi(workBook, head, sheet, data, 10, null);
        
        workBook.write(new FileOutputStream("E:/hello1.xlsx"));
        
        //System.out.println(isNumeric("13608001418"));
        
        //System.out.println(isInteger("13608001418"));
    }
    
    public static boolean isNumeric(String str)
    {
        if (str == null)
        {
            str = "";
        }
        
        Pattern pattern = Pattern.compile("^(-?\\d+)(\\.\\d+)?$");
        
        Matcher isNum = pattern.matcher(str);
        
        if (!isNum.matches())
        {
            return false;
        }
        
        return true;
    }
    
    public static boolean isInteger(String str)
    {
        if (str == null)
        {
            str = "";
        }
        
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        
        Matcher isNum = pattern.matcher(str);
        
        if (!isNum.matches())
        {
            return false;
        }
        
        return true;
    }
}
