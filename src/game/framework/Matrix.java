package game.framework;

import java.io.Serializable;
import java.util.Arrays;

/**
 * A generalized Matrix with n x m dimensions.
 * <br />
 * <h1>Not Yet Implemented</h1>
 * <ul>
 *  <li>Determinant</li>
 *  <li>Inverse</li>
 *  <li>LU-Decomposition</li>
 * </ul>
 * @version incomplete
 * @author mike, paul
 */
public class Matrix implements Serializable 
{
    //<editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * 2D array of floats representing the data in the Matrix.
     */
    public final float[][] data;
    
    /**
     * A integer that contains the number of rows in the Matrix.
     */
    public final int ROWS; 
    
    /**
     * A integer that contains the number of columns in the Matrix.
     */
    public final int COLUMNS;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Complete Constructor / Default Constructor
     * Takes a 2d-array of floats and creates a new NMatrix with the data.
     * @param data 
     */
    public Matrix(float[][] data)
    {
        this.data = data;
        this.ROWS = data.length;
        this.COLUMNS = data[0].length;
    }
    
    /**
     * Copy Constructor
     * Creates a copy of the matrix provided.
     * @param a A Matrix we want to copy.
     */
    public Matrix(Matrix a)
    {
        this(a.data);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Add">
    /**
     * Performs addition with two Matrices.
     * @param a A Matrix to be added.
     * @param b A Matrix to be added. 
     * @return Returns a + b.
     */
    public static Matrix add(Matrix a, Matrix b)
    {
        Matrix sum = null;
        if(a.ROWS == b.ROWS && a.COLUMNS == b.COLUMNS)
        {
            float[][] ws = new float[a.ROWS][a.COLUMNS];
            for(int j = 0; j < a.ROWS; ++j)
            {
                for(int i = 0; i < a.COLUMNS; ++i)
                {
                    ws[j][i] = a.data[j][i] + b.data[j][i];
                }
            }
            sum = new Matrix(ws);
        }
        return sum;
    }
    
    /**
     * Performs addition with this Matrix and that Matrix.
     * @param that A Matrix to be added.
     * @return Returns this + that.
     */
    public Matrix add(Matrix that)
    {
        return Matrix.add(this, that);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Determinant">
    public static float determinant(Matrix a)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    
    public float determinant()
    {
        return Matrix.determinant(this);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Divide">
    /**
     * Divides each component of a Matrix by a scalar value.
     * @param a A Matrix we want to divide.
     * @param scalar A scalar value we want to divide by.
     * @return Returns a / scalar.
     */
    public static Matrix divide(Matrix a, float scalar)
    {
        float[][] ws = new float[a.ROWS][a.COLUMNS];
        for(int j = 0; j < a.ROWS; ++j)
        {
            for(int i = 0; i < a.COLUMNS; ++i)
            {
                ws[j][i] = a.data[j][i] / scalar; 
            }
        }
        return new Matrix(ws);
    }
    
    /**
     * Divides each component of this Matrix by a scalar value.
     * @param scalar A scalar value we want to divide by.
     * @return Returns this / scalar.
     */
    public Matrix divide(float scalar)
    {
        return Matrix.divide(this, scalar);
    }
    
    /**
     * b Matrix divides a Matrix component wise.
     * @param a A Matrix we want to divide.
     * @param b A Matrix we want to divide. 
     * @return Returns a / b.
     */
    public static Matrix divide(Matrix a, Matrix b)
    {
        Matrix quotient = null;
        if(a.ROWS == b.ROWS && a.COLUMNS == b.COLUMNS)
        {
            float[][] ws = new float[a.ROWS][a.COLUMNS];
            for(int j = 0; j < a.ROWS; ++j)
            {
                for(int i = 0; i < a.COLUMNS; ++i)
                {
                    ws[j][i] = a.data[j][i] / b.data[j][i]; 
                }
            }
            quotient = new Matrix(ws);
        }
        return quotient;
    }
    
    /**
     * that Matrix divides this Matrix component wise.
     * @param that A Matrix we want to divide.
     * @return Returns this / that.
     */
    public Matrix divide(Matrix that)
    {
        return Matrix.divide(this, that);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Equals">
    /**
     * Compares this Matrix with another Object believed to be a Matrix.
     * @param obj An object believed to be a Matrix.
     * @return Returns the comparison of this == that.
     */
    @Override
    public boolean equals(Object obj)
    {
        boolean equals = false;
        if(obj == this)
            equals = true;
        else if(obj != false && obj instanceof Matrix)
        {
            Matrix that = (Matrix)obj;
            if(Arrays.deepEquals(this.data, that.data))
                equals = true;
        }
        return equals;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Helper Methods">
    /**
     * Adds the data of two rows together.
     * @param row1 Data of a row to be added.
     * @param row2 Data of a row to be added.
     * @return row1 + row2.
     */
    private static float[] addRow(float[] row1, float[] row2)
    {
        float[] n_row = null;
        if(row1.length == row2.length)
        {
            n_row = new float[row1.length];
            for(int i = 0; i < row1.length; ++i)
                n_row[i] = row1[i] + row2[i];
        }
        return n_row;
    }
    
    /**
     * Multiplies the data of a row by a scalar value.
     * @param row Data of a row to scale.
     * @param scalar A value to scale the row by.
     * @return Returns row * scalar.
     */
    private static float[] multiplyRow(float[] row, float scalar)
    {
        float[] ws = new float[row.length];
        for(int i = 0; i < row.length; i++)
            ws[i] = row[i] * scalar;
        return ws;
    }
    
    /**
     * Replaces a row in a 2D-Array.
     * @param a Data of Matrix with row to be replaced.
     * @param index Index of row to be replaced.
     * @param row Data of new row.
     */
    private static void replaceRow(float[][] a, int index, float[] row)
    {
        a[index] = row;
    }
    
    /**
     * Debug Method prints out row contents
     */
    private static void rowPrinter(float[] row)
    {
        for(int i = 0; i < row.length; i++)
            System.out.print(row[i] + " ");
        System.out.println("");
    }
    
    /**
     * Moves all rows containing all zero to the bottom of the Matrix.
     * @param a A float[][] containing the data of a Matrix. 
     * @return Returns A Matrix with zeros in the proper locations for methods like rref.
     */
    private static float[][] swapZeros(float[][] a)
    {
        float[][] ws = a.clone();
        int startIndex = ws.length - 1;
        int endIndex = 0;
        for(int i = 0; i < a.length; ++i)
        {
            if(startIndex > endIndex && Matrix.zeroRow(ws, i))
            {
                for(int j = startIndex; j >= endIndex; --j)
                {
                    if(!Matrix.zeroRow(ws, j))
                    {
                        Matrix.swapRows(ws, i, j);
                        --startIndex; // With Each successfull swap this zero row is now in place. 
                        break;
                    }
                }
            }
            ++endIndex; // After Each iteration that row is considered sorted.
        }
        return ws;
    }
    
    /**
     * Swaps the rows of a float[][].
     * @param a A float[][] containing the data we want to swap the rows of.
     * @param r1 The index of a row we want to swap.
     * @param r2 The index of a row we want to swap.
     */
    private static void swapRows(float[][] a, int r1, int r2)
    {
        float[] temp = a[r1];
        a[r1] = a[r2];
        a[r2] = temp;
    }
    
    /**
     * Checks to see if a row is zero.
     * @param a A float[][] containing the data of a Matrix.
     * @param row The row index that we want to check.
     * @return Returns true if the row is all zeros.
     */
    private static boolean zeroRow(float[][] a, int row)
    {
        boolean zero = true;
        for(int i = 0; i < a[0].length; ++i)
            zero &= a[row][i] == 0;
        return zero;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Inverse">
    public static Matrix inverse(Matrix a)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    
    public Matrix inverse()
    {
        return Matrix.inverse(this);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="LU Decomposition">
    public static Matrix[] luDecomposition(Matrix a)
    {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
    
    public Matrix[] luDecomposition()
    {
        return Matrix.luDecomposition(this);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Multiply">
    /**
     * Helper Method for multiplying the data of a Matrix
     * @param a Data of a matrix we want to multiply.
     * @param scalar A scalar value we want to multiply into the provided data.
     * @return Returns scalar * A
     */
    private static float[][] multiply(float[][] a, float scalar)
    {
        float[][] ws = new float[a.length][a[0].length];
        for(int j = 0; j < a.length; ++j)
        {
            for(int i = 0; i < a[0].length; ++i)
            {
                ws[j][i] = a[j][i] * scalar;
            }
        }
        return ws;
    }
    
    /**
     * Multiplies a scalar value into the Matrix.
     * @param a A Matrix we want to multiply.
     * @param scalar A scalar value we want to multiply into the provided Matrix.
     * @return Returns scalar * A.
     */
    public static Matrix multiply(Matrix a, float scalar)
    {
        return new Matrix(Matrix.multiply(a.data, scalar));
    }
    
    /**
     * Multiplies a scalar value into this Matrix.
     * @param scalar A scalar value we want to multiply into the provided Matrix.
     * @return Returns scalar * this.
     */
    public Matrix multiply(float scalar)
    {
        return Matrix.multiply(this, scalar);
    }
    
    /**
     * Helper Method for Multiplying the data of two matrices. 
     * @param a Data of a matrix we want to multiply.
     * @param b Data of a Matrix we want to multiply.
     * @return Returns a * b;
     */
    private static float[][] multiply(float[][] a, float[][] b)
    {
        float[][] product = null;
        if(a[0].length == b.length)
        {
            product = new float[a.length][b[0].length];
            for(int j = 0; j < a.length; ++j)
            {
                for(int i = 0; i < b[0].length; ++i)
                {
                    for(int k = 0; k < a[0].length; ++k)
                    {
                        product[j][i] += a[j][k] * b[k][i]; 
                    }
                }
            }
        }
        return product;
    }
    
    /**
     * Perform Matrix multiplication between two matrices.
     * @param a A Matrix we want to multiply.
     * @param b A matrix we want to multiply.
     * @return Returns a * b.
     */
    public static Matrix multiply(Matrix a, Matrix b)
    {
        return new Matrix(Matrix.multiply(a.data, b.data));
    }
    
    /**
     * Perform Matrix multiplication between this Matrix and that Matrix.
     * @param that A Matrix we want to multiply.
     * @return Returns this * that.
     */
    public Matrix multiply(Matrix that)
    {
        return Matrix.multiply(this, that);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Rank">
    /**
     * Computes the rank of the provided Matrix.
     * @param a A Matrix we want to find the rank of.
     * @return Returns the ran of the Matrix.
     */
    public static int rank(Matrix a)
    {
        Matrix rref = Matrix.rref(a);
        int rank = 0;
        for(int j = 0; j < rref.ROWS; ++j)
        {
            for(int i = 0; i < rref.COLUMNS; ++i)
            {
                if(rref.data[j][i] == 0)
                {
                    ++rank;
                    break;
                }
            }
        }
        return rank;
    }
    
    /**
     * Computes the rank of this Matrix.
     * @return Returns the rank of this Matrix.
     */
    public int rank()
    {
        return Matrix.rank(this);
    }
    //</editor-fold>
 
    //<editor-fold defaultstate="collapsed" desc="Row Echelon Form (ref)">
    /**
     * Finds the Row Echelon Form(ref) of the provided Matrix.
     * @param a A Matrix we want to find the Row Echelon Form of.
     * @return Returns a Matrix in Row Echelon Form. 
     */
    public static Matrix ref(Matrix a)
    {
        // Search to see if we have any rows that are all zero.
        // If there are any rows that contain all zero, we will swap them.
        float[][] ws = Matrix.swapZeros(a.data);
        // Check to see if a11 is zero.
        if(ws[0][0] == 0)
            // try to find a row that the first element is not zero.
            for(int i = 1; i < ws.length; ++i)
                if(ws[i][0] != 0)
                    Matrix.swapRows(ws, 0, i);
        // Use gaussian elimination to reduce the rows.
        float[] tempRow;
        int pivot = 0;
        // Start Work!
        for(int i = 0; i < ws.length; i++)
        {
            // Find the pivot(first non-zero entry in the row)
            for(int k = 0; k < ws[0].length; k++)
            {
                // Found a Pivot
                if(ws[i][k] > 0)
                {
                    pivot = k;
                    break;
                }
            }
            // Operate on the row if it does notcontain all zeros.
            if(!Matrix.zeroRow(ws, i))
            {
                // multiply the row by the inverse of the pivot
                tempRow = Matrix.multiplyRow(ws[i], 1 / ws[i][pivot]);
                // Replace the row by its scaled Value
                Matrix.replaceRow(ws, i, tempRow);
                // Convert Values under the pivot to zero.
                for(int j = i + 1; j < ws.length; j++)
                {
                    tempRow = Matrix.multiplyRow(ws[i], -1 * ws[j][pivot]);
                    tempRow = Matrix.addRow(tempRow, ws[j]);
                    Matrix.replaceRow(ws, j, tempRow);
                }
            }
        }
        return new Matrix(ws);
    }
    
    /**
     * Finds the Row Echelon Form(ref) of this Matrix.
     * @return Returns a Matrix in Row Echelon Form.
     */
    public Matrix ref()
    {
        return Matrix.ref(this);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Reduced Row Echelon Form (rref)">
    /**
     * Finds the Reduced Row Echelon Form(rref) of the provided Matrix.
     * @param a A Matrix we want to find the rref of.
     * @return Returns a Matrix in Reduced Row Echelon Form.
     */
    public static Matrix rref(Matrix a)
    {
        /**
         * All Columns where a pivot exists, the pivot must by the only non-zero value
         * in that column.
         */
        Matrix ref = Matrix.ref(a);
        float[][] ws = ref.data;
        float[] tempRow;
        int pivotIndex = 0;
        boolean pivotExists = false;
        // Go to each row
        for(int j = 0; j < ref.ROWS; ++j)
        {
            // Search for pivot
            for(int i = 0; i < ref.COLUMNS; ++i)
            {
                // If a pivot was found break.
                if(ws[j][i] == 1)
                {
                    pivotIndex = i;
                    pivotExists = true;
                    break;
                }
            }
            // If a pivot exists check the contents of the column.
            if(pivotExists)
            {
                // Search the column for other non-zero values.
                for(int k = 0; k < ref.ROWS; ++k)
                {
                    // If we found a non-zero value in the column we need to remove it.
                    if(k != j && ws[k][pivotIndex] != 0)
                    {
                        tempRow = Matrix.multiplyRow(ws[j], -1 * ws[k][pivotIndex]);
                        tempRow = Matrix.addRow(tempRow, ws[k]);
                        Matrix.replaceRow(ws, k, tempRow);
                    }
                }
            }
            // Reset the pivot exists flag
            pivotExists = false;
        }
        return new Matrix(ws);
    }
    
    /**
     * Finds the Reduced Row Echelon Form(rref) of this Matrix.
     * @return Returns a Matrix in Reduced Row Echelon Form.
     */
    public Matrix rref()
    {
        return Matrix.rref(this);
    }
    //</editor-fold>
 
    //<editor-fold defaultstate="collapsed" desc="Special Matrices">
    /**
     * nxn Identity Matrix
     * @param n A integer defininf the size of the Identity Matrix.
     * @return Returns a nxn Identity Matrix.
     */
    public static Matrix identity(int n)
    {
        float[][] ws = new float[n][n];
        for(int i = 0; i < n; ++i)
        {
            ws[i][i] = 1;
        }
        return new Matrix(ws);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Subtract">
    /**
     * Performs subtraction between two Matrices.
     * @param a A Matrix to be subtracted from.
     * @param b A Matrix to subtract by. 
     * @return Returns a - b.
     */
    public static Matrix subtract(Matrix a, Matrix b)
    {
        Matrix sum = null;
        if(a.ROWS == b.ROWS && a.COLUMNS == b.COLUMNS)
        {
            float[][] ws = new float[a.ROWS][a.COLUMNS];
            for(int j = 0; j < a.ROWS; ++j)
            {
                for(int i = 0; i < a.COLUMNS; ++i)
                {
                    ws[j][i] = a.data[j][i] - b.data[j][i];
                }
            }
            sum = new Matrix(ws);
        }
        return sum;
    }
    
    /**
     * Performs subtraction between this Matrix and that Matrix. 
     * @param that A Matrix to subtract by.
     * @return Returns this - that.
     */
    public Matrix subtract(Matrix that)
    {
        return Matrix.subtract(this, that);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="To-String">
    @Override
    public String toString()
    {
        String out = "";
        for(int j = 0; j < this.ROWS; ++j)
        {
            for(int i = 0; i < this.COLUMNS; ++i)
            {
                out += this.data[j][i] + " ";
            }
            out += "\n";
        }
        return out;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Transpose">
    /**
     * Creates the transpose of a Matrix.
     * @param a A Matrix we want to transpose.
     * @return Returns the transpose of A.
     */
    public static Matrix transpose(Matrix a)
    {
        float[][] t = new float[a.COLUMNS][a.ROWS];
        for(int j = 0; j < a.ROWS; ++j)
        {
            for(int i = 0; i < a.COLUMNS; ++i)
            {
                t[i][j] = a.data[j][i];
            }
        }
        return new Matrix(t);
    }
    
    /**
     * Creates the transpose of this Matrix.
     * @return Returns the transpose of this.
     */
    public Matrix transpose()
    {
        return Matrix.transpose(this);
    }
    //</editor-fold> 
}