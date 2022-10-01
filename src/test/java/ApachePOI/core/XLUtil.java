package ApachePOI.core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtil {
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path = null;

	public XLUtil(String path) {
		this.path = path;

	}

	public static String[][] getData(String path, String sheetName) throws IOException {

		XLUtil xlUtil = new XLUtil(path);

		int totalRows = xlUtil.getRowCount(sheetName);
		int totalCols = xlUtil.getColumnCount(sheetName, 1);

		String loginData[][] = new String[totalRows][totalCols];

		for (int i = 1; i <= totalRows; i++) {// starting with 1 because row 0 is just the header
			for (int j = 0; j < totalCols; j++) {
				loginData[i - 1][j] = xlUtil.getCellData(sheetName, i, j);
			}
		}
		return loginData;
	}

	public static String[][] getData(String path, String sheetName, String tableName) {

		String loginData[][] = null;
		try {
			FileInputStream fi = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fi);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XLUtil xlUtil = new XLUtil(path);
			Cell tableStartCell = XLUtil.findCell(sheet, tableName, 1);
			Cell tableEndCell = XLUtil.findCell(sheet, tableName, 2);
			int totalRows = xlUtil.getRowCount(tableStartCell, tableEndCell);
			int totalCols = xlUtil.getColumnCount(tableStartCell, tableEndCell);
			loginData = new String[totalRows - 1][totalCols - 1];
			int startRowIndex = tableStartCell.getRowIndex();
			int endRowIndex = tableEndCell.getRowIndex();
			int startColumnIndex = tableStartCell.getColumnIndex();
			int endColumnIndex = tableEndCell.getColumnIndex();
			int ci = 0;
			int cj;
			DataFormatter formatter = new DataFormatter();
			for (int i = startRowIndex + 1; i < endRowIndex; i++, ci++) {
				cj = 0;
				for (int j = startColumnIndex + 1; j < endColumnIndex; j++, cj++) {
					Cell currentCell = sheet.getRow(i).getCell(j);
					String strValue = formatter.formatCellValue(currentCell);
					loginData[ci][cj] = strValue;
				}
			}
			workbook.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return loginData;

	}

	public static Cell findCell(Sheet sheet, String text, int numberToFind) throws IOException {

		int currentNum = 0;
		DataFormatter formatter = new DataFormatter();

		for (Row row : sheet) {
			for (Cell cell : row) {
				String strValue = formatter.formatCellValue(cell);
				if (text.equals(strValue)) {
					currentNum++;
					if (currentNum == numberToFind) {
						return cell;
					}
				}
			}
		}
		return null;
	}

	public int tableStartRow(XSSFSheet sheet, String text, int numberToFind) throws IOException {
		int startRow = XLUtil.findCell(sheet, text, numberToFind).getRowIndex();
		return startRow;
	}

	public int getRowCount(Cell tableStartCell, Cell tableEndCell) throws IOException {
		int rowCount = tableEndCell.getRowIndex() - tableStartCell.getRowIndex();
		return rowCount;
	}

	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
	}

	public int getColumnCount(Cell tableStartCell, Cell tableEndCell) throws IOException {
		int columnCount = tableEndCell.getColumnIndex() - tableStartCell.getColumnIndex();
		return columnCount;
	}

	public int getColumnCount(String sheetName, int rowNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
			data = formatter.formatCellValue(cell);// returns the formatted value of a cell as a String regardless of
													// the cell type
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);
		cell.setCellValue(data);

		fo = new FileOutputStream(path);
		workbook.write(fo);// updating this cell data in excel

		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillGreenColor(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rowNum, int colNum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(sheetName);

		row = sheet.getRow(rowNum);
		cell = row.getCell(colNum);

		style = workbook.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		workbook.write(fo);
		workbook.close();
		fi.close();
		fo.close();
	}

}
