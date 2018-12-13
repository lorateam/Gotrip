package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 * 利用开源组件POI3.13动态导出EXCEL文档 
 *
 * @version v1.0
 * @param
 *  */
public class ExportExcel {
    public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

    /**
     * 这是一个通用的方法 
     * @param fileName 文件名 
     * @param headers  表格属性列名数组 
     * @param dataset  需要显示的数据集合 
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     */
    @SuppressWarnings("deprecation")
    public static HSSFWorkbook exportExcel(HttpServletRequest request,HttpServletResponse response,String fileName, String[] headers, List<Object[]> dataset,  String pattern) {
        String docsPath = request.getSession().getServletContext().getRealPath("docs");//docs文件夹目录  
        String filePath = docsPath + FILE_SEPARATOR + fileName+"-任意名.xls";
        // 声明一个工作薄  
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格  
        HSSFSheet sheet = workbook.createSheet("Excel");
        // 设置表格默认列宽度为15个字节  
        //sheet.setDefaultColumnWidth((short) 15);  
        // 生成一个表格标题行样式  
        HSSFCellStyle style = getColumnTopStyle(workbook);
        // 生成非标题样式  
        HSSFCellStyle style2 = getColumnStyle(workbook);
        // 产生表格标题行  
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        // 遍历集合数据，产生数据行  
        Iterator<Object[]> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);//从第1行开始创建  
            Object[] obj = (Object[]) it.next();
            for (short i = 0; i < obj.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                Object value = obj[i];
                String textValue = null;
                if (!"".equals(value) && value != null) {
                    if (value instanceof Integer) {
                        int intValue = (Integer) value;
                        cell.setCellValue(intValue);
                    } else if (value instanceof Float) {
                        float fValue = (Float) value;
                        cell.setCellValue(fValue);
                    } else if (value instanceof Double) {
                        double dValue = (Double) value;
                        cell.setCellValue(dValue);
                    } else if (value instanceof Long) {
                        long longValue = (Long) value;
                        cell.setCellValue(longValue);
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        if(null==pattern||pattern.equals("")){
                            pattern="yyyy-MM-dd";
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                        cell.setCellValue(textValue);
                    } else {
                        // 其它数据类型都当作字符串简单处理  
                        textValue = value.toString();
                        cell.setCellValue(textValue); // 设置单元格的值  
                    }
                } else {
                    cell.setCellValue("");
                }
            }
        }

        //让列宽随着导出的列长自动适应    
        for (int colNum = 0; colNum < headers.length; colNum++) {
            int columnWidth = sheet.getColumnWidth(colNum) / 256;
            for (int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++) {
                HSSFRow currentRow;
                // 当前行未被使用过  
                if (sheet.getRow(rowNum) == null) {
                    currentRow = sheet.createRow(rowNum);
                } else {
                    currentRow = sheet.getRow(rowNum);
                }
                if (currentRow.getCell(colNum) != null) {
                    HSSFCell currentCell = currentRow.getCell(colNum);
                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        int length = currentCell.getStringCellValue() != null
                                ? currentCell.getStringCellValue().getBytes().length : 10;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            if (colNum == 0) {
                sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
            } else {
                sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
            }
        }
        try {
            if (createDir(docsPath)) {
                OutputStream out = new FileOutputStream(filePath);
                workbook.write(out);
                out.close();
                download(filePath, response);// 执行下载  
                cleanFile(filePath);// 删除已完成下载的文件  
            }
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    /**
     * 导出下载,弹出下载框 
     * @param path  文件下载路径 
     * @param response
     */
    public static void download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。  
            File file = new File(path);
            // 取得文件名。  
            String filename = file.getName();
            // 以流的形式下载文件。  
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response  
            response.reset();
            // 设置response的Header  
            response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**

     * 清除文件

     * @param filePath  文件路径

     */
    public static  void cleanFile(String docsPath) {
        File file = new File(docsPath);
        file.delete();
    }

    /*
     * 列头单元格样式
     */
    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置样式;  
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;  
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;  
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;  
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;  
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 设置自动换行;  
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 设置字体  
        HSSFFont font = workbook.createFont();
        // 设置字体颜色  
        font.setColor(HSSFColor.VIOLET.index);
        // 设置字体大小  
        font.setFontHeightInPoints((short) 12);
        // 字体加粗  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 设置字体名字  
        font.setFontName("Courier New");
        // 在样式用应用设置的字体;  
        style.setFont(font);

        return style;
    }

    /*
     * 列数据信息单元格样式
     */
    public static HSSFCellStyle getColumnStyle(HSSFWorkbook workbook) {
        // 设置样式;  
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置底边框;  
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // 设置底边框颜色;  
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        // 设置左边框;  
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // 设置左边框颜色;  
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        // 设置右边框;  
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // 设置右边框颜色;  
        style.setRightBorderColor(HSSFColor.BLACK.index);
        // 设置顶边框;  
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // 设置顶边框颜色;  
        style.setTopBorderColor(HSSFColor.BLACK.index);
        // 设置自动换行;  
        style.setWrapText(false);
        // 设置水平对齐的样式为居中对齐;  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 设置垂直对齐的样式为居中对齐;  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 设置字体  
        HSSFFont font = workbook.createFont();
        // 设置字体大小  
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 设置字体名字  
        font.setFontName("Courier New");
        // 在样式用应用设置的字体;  
        style.setFont(font);

        return style;
    }
    /**
     * 创建目录 
     * @param destDirName
     * @return  true 存在目录  false 不存在目录 
     */
    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return true;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目录  
        if (dir.mkdirs()) {
            System.out.println("创建目录" + destDirName + "成功！");
            return true;
        } else {
            System.out.println("创建目录" + destDirName + "失败！");
            return false;
        }
    }
}  