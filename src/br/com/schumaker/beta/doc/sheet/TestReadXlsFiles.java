package br.com.schumaker.beta.doc.sheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author hudson schumaker
 */
public class TestReadXlsFiles {

    static Row row;
    public static void main(String args[]) {
        try {
            FileInputStream fis = new FileInputStream(new File("/Volumes/swap/HStudio/Project S@murai/Docs/Projeto Inovação/Entregues/orc_ed04_2014.xls"));

            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            System.out.println(workbook.getNumberOfSheets());
            HSSFSheet spreadsheet = workbook.getSheetAt(0);
            Iterator< Row> rowIterator = spreadsheet.iterator();

            while (rowIterator.hasNext()) {
                 row = rowIterator.next();
                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t\t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            System.out.print(cell.getStringCellValue() + "\t\t");
                            break;
                    }
                }
                System.out.println("");
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
