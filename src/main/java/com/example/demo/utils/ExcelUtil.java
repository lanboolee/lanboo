/*
package com.example.demo.utils;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


*/
/**
 * @author lanboo
 * @date 2018/9/25 10:43
 *//*

public class ExcelUtil {
    private static Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    public static Workbook createWorkbook() {
        return new SXSSFWorkbook(500);
    }

    public static Workbook createWorkbook(MultipartFile multipartFile) {
        try {
            return new HSSFWorkbook(multipartFile.getInputStream());
        } catch (Exception e) {
            try {
                return new XSSFWorkbook(multipartFile.getInputStream());
            } catch (IOException e1) {
                //throw SystemException.wrap(e1, SystemErrorCode.EXCEL_PARSE_ERROR);
                log.info("Excel解析错误,请检查版本,异常信息为{}",e1);
            }
        }
    }

    public static Workbook createWorkbook(InputStream in) {
        try {
            return new HSSFWorkbook(in);
        } catch (Exception e) {
            try {
                return new XSSFWorkbook(in);
            } catch (IOException e1) {
                log.info("Excel解析错误,请检查版本,异常信息为{}",e1);
            }
        }
    }


    public static void writer(Workbook wb, String path) throws IOException {
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(path);
            wb.write(outputStream);
        } catch (Exception e) {
            log.info("Excel解析错误,请检查版本,异常信息为{}",e);

        } finally {
            if (ObjectUtils.allNotNull(outputStream)) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    public static void addHeader(Row headerRow, CellStyle style, String... headers) {
        int column = 0;
        for (String header : headers) {
            ExcelUtil.addCell(headerRow, column++, header, style);
        }
    }

    public static Cell addFormulaCell(Row row, int column, Object val, CellStyle style) {
        Cell cell = row.createCell(column);
        try {
            cell.setCellFormula((String) val);
        } catch (Exception ex) {
            log.info("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        cell.setCellStyle(style);
        return cell;
    }

    public static Cell addCell(Row row, int column, Object val, CellStyle style) {
        Cell cell = row.createCell(column);
        try {
            if (val == null) {
                cell.setCellValue("");
            } else if (val instanceof String) {
                cell.setCellValue((String) val);
            } else if (val instanceof Integer) {
                cell.setCellValue((Integer) val);
            } else if (val instanceof Long) {
                cell.setCellValue((Long) val);
            } else if (val instanceof Double) {
                cell.setCellValue((Double) val);
            } else if (val instanceof Float) {
                cell.setCellValue((Float) val);
            } else if (val instanceof Date) {
                cell.setCellValue(DateUtils.formatDate((Date) val, DateUtils.FORMAT3));
            } else if (val instanceof BigDecimal) {
                cell.setCellValue(((BigDecimal) val).doubleValue());
            } else if (val instanceof Object) {
                cell.setCellValue(val.toString());
            }
        } catch (Exception ex) {
            log.info("Set cell value [" + row.getRowNum() + "," + column + "] error: " + ex.toString());
            cell.setCellValue(val.toString());
        }
        cell.setCellStyle(style);
        return cell;
    }

    public static CellStyle getBigTitleStyle(Workbook wb) {
        CellStyle titleStyle = wb.createCellStyle();
        // titleStyle.setBorderRight(CellStyle.BORDER_NONE); // 设置边框线
        // titleStyle.setBorderLeft(CellStyle.BORDER_NONE);
        // titleStyle.setBorderTop(CellStyle.BORDER_NONE);
        // titleStyle.setBorderBottom(CellStyle.BORDER_NONE);
        // titleStyle.setAlignment(CellStyle.BORDER_NONE);// 设置居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    public static CellStyle getTitleStyle(Workbook wb) {
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setBorderRight(BorderStyle.THIN); // 设置边框线
        titleStyle.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        titleStyle.setWrapText(true);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setBold(true);
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    public static CellStyle getRedTitleStyle(Workbook wb) {
        CellStyle titleStyle = wb.createCellStyle();
        titleStyle.setBorderRight(BorderStyle.THIN); // 设置边框线
        titleStyle.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        titleStyle.setBorderLeft(BorderStyle.THIN);
        titleStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        titleStyle.setBorderTop(BorderStyle.THIN);
        titleStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        titleStyle.setBorderBottom(BorderStyle.THIN);
        titleStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        titleStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.GREY_25_PERCENT.getIndex());
        titleStyle.setWrapText(true);
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        titleFont.setBold(true);
        titleFont.setColor(IndexedColors.RED.getIndex());
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    public static CellStyle getHeaderStyle(Workbook wb) {
        CellStyle headerStyle = wb.createCellStyle();
        headerStyle.setBorderRight(BorderStyle.THIN); // 设置边框线
        headerStyle.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font headerFont = wb.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);
        return headerStyle;
    }

    public static CellStyle getCellStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderRight(BorderStyle.THIN); // 设置边框线
        cellStyle.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        cellStyle.setFont(titleFont);
        return cellStyle;
    }

    public static CellStyle getCellStyleNum(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        DataFormat df = wb.createDataFormat();  //此处设置数据格式
        cellStyle.setDataFormat(df.getFormat("#0.00")); //小数点后保留两位，可以写contentStyle.setDataFormat(df.getFormat("#,
        cellStyle.setBorderRight(BorderStyle.THIN); // 设置边框线
        cellStyle.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        Font titleFont = wb.createFont();
        titleFont.setFontName("Arial");
        titleFont.setFontHeightInPoints((short) 10);
        cellStyle.setFont(titleFont);
        return cellStyle;
    }

    //返回小数位保留四位的单元格格式
    public static CellStyle getCellStyleForBigDecimal(Workbook wb) {
        CellStyle cellStyle = getCellStyle(wb);
        DataFormat df = wb.createDataFormat();
        cellStyle.setDataFormat(df.getFormat("0.0000"));
        return cellStyle;
    }

    public static CellStyle getFirst(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
      */
/*cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
		cellStyle.setFillPattern(FillPattern.SOLID_FOREGROUND);
		cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 10);
		titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cellStyle.setFont(titleFont);*//*


        cellStyle.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		*/
/*Font titleFont = wb.createFont();
		titleFont.setFontName("Arial");
		titleFont.setFontHeightInPoints((short) 10);
		cellStyle.setFont(titleFont);*//*

        return cellStyle;
    }

    public static CellStyle getOtherStle(Workbook wb) {
        CellStyle style = wb.createCellStyle();

        //style.setFillBackgroundColor(HSSFColor.LIGHT_TURQUOISE.index);
        style.setBorderRight(BorderStyle.THIN); // 设置边框线
        style.setRightBorderColor(IndexedColors.GREY_80_PERCENT.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        //自动换行
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.CENTER);// 设置居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);// 设置居中
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        Font font = wb.createFont();
        font.setColor(IndexedColors.RED.index);//自体颜色
        style.setFont(font);
        return style;
    }


    public static void writer(Workbook wb, String fileName, HttpServletResponse response) throws IOException {
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            log.info("Excel解析错误,请检查版本,异常信息为{}",e);
        } finally {
            if (ObjectUtils.(response.getOutputStream())) {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        }
    }

    */
/**
     * 设置数据格式
     * @param wb
     * @param sheet
     * @param formatMao 列与格式<列数，格式>
     *//*

    public static void setDateFormat(Workbook wb, XSSFSheet sheet, Map<Integer,String> formatMao) {
        //从第二行开始设置
        for (int row = 1; row < 200; row++) {
            XSSFRow contentRow  = sheet.createRow(row);
            formatMao.keySet().forEach(cell ->{
                CellStyle cellStyle = wb.createCellStyle();
                XSSFCell xssfCell = contentRow.createCell(cell);
                DataFormat df = wb.createDataFormat();
                cellStyle.setDataFormat(df.getFormat(formatMao.get(cell)));
                xssfCell.setCellStyle(cellStyle);
            });
        }
    }

    public static String getCellValue(Cell cell) {
        Object val = "";
        try {
            if (cell != null) {
                if (cell.getCellTypeEnum() == CellType.NUMERIC) {
                    val = cell.getNumericCellValue();
                    if (!AirUtils.hv(val)) {
                        val = 0;
                    }
                } else if (cell.getCellTypeEnum() == CellType.STRING) {
                    val = cell.getStringCellValue();
                    if (!AirUtils.hv(val)) {
                        val = "''";
                    }
                } else if (cell.getCellTypeEnum() == CellType.FORMULA) {
                    val = cell.getCellFormula();
                } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellTypeEnum() == CellType.BOOLEAN) {
                    val = cell.getErrorCellValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return StringUtils.trim(String.valueOf(val));
    }

    public static Object beanGetValue(Class<?> clazz, Object object, String fieldName) throws Exception {
        Field field = null;
        for (; clazz != null; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                continue;
            }
            if (field != null) {
                break;
            }
        }
        if (field == null) {
            return null;
        }
        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
        Method getMethod = pd.getReadMethod();
        Object o = getMethod.invoke(object);
        return o;
    }

    public static void beanSetValue(Class<?> clazz, Object object, String fieldName, Object value) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        if (field.getType() == String.class) {
            field.set(object, value.toString());
        } else if (field.getType() == Integer.class) {
            if (value instanceof String) {
                field.set(object, Integer.valueOf(value.toString()));
            } else {
                field.set(object, value);
            }
        } else if (field.getType() == Double.class) {
            if (value instanceof String) {
                field.set(object, Double.valueOf(value.toString()));
            } else {
                field.set(object, value);
            }
        } else if (field.getType() == Long.class) {
            if (value instanceof String) {
                field.set(object, Long.valueOf(value.toString()));
            } else {
                field.set(object, value);
            }
        }
    }

    public static Integer yesOrNo(String yesOrNo) {
        if (AirUtils.eq(yesOrNo, "是")) {
            return Constants.PLATFORM_YES;
        } else if (AirUtils.eq(yesOrNo, "否")) {
            return Constants.PLATFORM_NO;
        } else {
            return -1;
        }
    }

    public static Integer enableOrDisable(String enableOrDisable) {
        if (AirUtils.eq(enableOrDisable, "启用")) {
            return Constants.PLATFORM_STATUS_ENABLE;
        } else if (AirUtils.eq(enableOrDisable, "停用")) {
            return Constants.PLATFORM_STATUS_DISABLE;
        } else {
            return -1;
        }
    }

    public static Integer dishSpicy(String spicy) {
        if (AirUtils.eq(spicy, "不辣")) {
            return 331;
        } else if (AirUtils.eq(spicy, "微辣")) {
            return 332;
        } else if (AirUtils.eq(spicy, "中辣")) {
            return 333;
        } else if (AirUtils.eq(spicy, "高辣")) {
            return 334;
        } else if (AirUtils.eq(spicy, "极辣")) {
            return 335;
        } else {
            return -1;
        }
    }

    public static Integer dishCommMode(String commMode) {
        if (AirUtils.eq(commMode, "按比例提成")) {
            return 381;
        } else if (AirUtils.eq(commMode, "按份提成")) {
            return 382;
        } else if (AirUtils.eq(commMode, "按只提成")) {
            return 383;
        } else {

            return -1;
        }
    }

    public static String saleMode(Integer saleMode) {
        if (AirUtils.eq(saleMode.toString(), "711")) {
            return "堂食";
        } else if (AirUtils.eq(saleMode.toString(), "712")) {
            return "外带";
        } else if (AirUtils.eq(saleMode.toString(), "713")) {
            return "外送";
        } else {
            return "未知";
        }
    }

    public static String billAttribute(Integer billAttribute) {
        if (AirUtils.eq(billAttribute.toString(), "721")) {
            return "取消冲减";
        } else if (AirUtils.eq(billAttribute.toString(), "722")) {
            return "反结冲减";
        } else if (AirUtils.eq(billAttribute.toString(), "723")) {
            return "反结";
        } else if (AirUtils.eq(billAttribute.toString(), "724")) {
            return "正常";
        } else if (AirUtils.eq(billAttribute.toString(), "725")) {
            return "整单取消";
        } else {
            return "未知";
        }
    }

    */
/**
     * 单据类型
     *
     * @param billType
     * @return
     *//*

    public static String scmBillType(Integer billType) {
        switch (billType) {
            case 911:
                return "订货入库单";
            case 912:
                return "采购入库单";
            case 913:
                return "盘盈入库单";
            case 914:
                return "其他入库单";
            case 915:
                return "盘亏出库单";
            case 916:
                return "消耗出库单";
            case 917:
                return "报损出库单";
            case 919:
                return "其他出库单";
            case 921:
                return "调拨入库单";
            case 922:
                return "调拨出库单";
            case 939:
                return "配送出库单";
            case 937:
                return "配送入库单";
            case 934:
                return "直运入库单";
            case 953:
                return "期初入库单";
            case 938:
                return "直运入库单";
            default:
                return "未知";
        }
    }

    */
/**
     * 用于将excel表格中列索引转成列号字母，从A对应1开始
     *
     * @param index 列索引
     * @return 列号
     *//*

    public static String indexToColumn(int index) {
        if (index <= 0) {
            try {
                throw new Exception("Invalid parameter");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        index--;
        String column = "";
        do {
            if (column.length() > 0) {
                index--;
            }
            column = ((char) (index % 26 + (int) 'A')) + column;
            index = (index - index % 26) / 26;
        }
        while (index > 0);
        return column;
    }


    public static String getDateCellValue(Cell cell, String dateFormat) {
        try {
            if (cell != null) {
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date dateCellValue = cell.getDateCellValue();
                    return new SimpleDateFormat(dateFormat).format(dateCellValue);
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
*/
