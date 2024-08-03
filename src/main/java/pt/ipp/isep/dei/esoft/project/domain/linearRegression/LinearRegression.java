

package pt.ipp.isep.dei.esoft.project.domain.linearRegression;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class LinearRegression {

    /**
     * Retrieves the data for the simple regression this method reads the Csv file converted to XSSFWorkbook.
     * It stores the data in an array n by 2 being that n is the sample size. The first column are the values for
     * the independent variable, the second column is the dependent one.
     * @param workbook csv file converted to workbook
     * @param columnIndex index of the independent variable
     * @return array ready to add to the SimpleRegression object
     */
    public static double[][] getDataSimpleRegression(XSSFWorkbook workbook, int columnIndex) {
        double[][] data = new double[getCellsCountInColumn(workbook)][2];
        XSSFSheet sheet = workbook.getSheetAt(0);
        int x = 0;
        for (Row row : sheet) {
            Cell cellIndependentVariable = row.getCell(columnIndex);
            Cell cellDependentVariable = row.getCell(5);
            double independentVariable = cellIndependentVariable.getNumericCellValue();
            double dependentVariable = cellDependentVariable.getNumericCellValue();
            data[x][0] = independentVariable;
            data[x][1] = dependentVariable;
            x++;
        }
        return data;
    }

    /**
     * Gets the number of rows of the table in the csv file
     * @param workbook csv file converted to workbook
     * @return number of rows
     */

    public static int getCellsCountInColumn(XSSFWorkbook workbook) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        int cellCount = 0;
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                // Process the numeric cell
                cellCount++;
            }
        }
        return cellCount;
    }

    /**
     * Gets the values for the independent variables for the multilinear regression
     * @param workbook
     * @return
     */

    public static double[][] getIndependentVariables(XSSFWorkbook workbook) {
        Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
        int rowCount = sheet.getLastRowNum() + 1;
        int columnCount = 5; // Assuming 5 independent variables

        double[][] independentVariables = new double[rowCount][columnCount];

        for (int row = 0; row < rowCount; row++) {
            Row currentRow = sheet.getRow(row);
            for (int col = 0; col < columnCount; col++) {
                Cell cell = currentRow.getCell(col);
                double value = cell.getNumericCellValue();
                independentVariables[row][col] = value;
            }
        }

        return independentVariables;
    }

    /**
     * Gets the values for the dependent variable for the multilinear regression
     * @param workbook
     * @return
     */

    public static double[] getDependentVariable(XSSFWorkbook workbook) {
        Sheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet
        int rowCount = sheet.getLastRowNum() + 1;
        int columnCount = 6; // Assuming 6 columns with the last one being the dependent variable

        double[] dependentVariable = new double[rowCount];

        for (int row = 0; row < rowCount; row++) {
            Row currentRow = sheet.getRow(row);
            Cell cell = currentRow.getCell(columnCount - 1); // Last column
            double value = cell.getNumericCellValue();
            dependentVariable[row] = value;
        }

        return dependentVariable;
    }
}




