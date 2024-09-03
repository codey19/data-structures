import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSMatrixInput {
    public static void main(String[] args){CSMatrixInput app=new CSMatrixInput();}

    public CSMatrixInput(){load("C:\\JavaProj\\Tasks\\inputData\\MatrixInput.txt");}

    public void load(String fileName){
        try{
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line="";
            while((line=br.readLine())!= null){//original ex:"{{1,2},{3,4}} {{4,5},{6,7}}"
                //Separates the two matrices
                String[] matrices = line.split(" ");//ex:"{{1,2},{3,4}}" and "{{4,5},{6,7}}"
                int [][] aMatrix = new int[matrices[0].split("},\\{").length][];//counts the number of rows to initialize the array with
                int [][] bMatrix = new int[matrices[1].split("},\\{").length][];
                fillRows(matrices[0], aMatrix);
                fillRows(matrices[1], bMatrix);
                //Calculations and printing
                System.out.println("Matrix 1:");
                printMatrix(aMatrix);
                System.out.println("Matrix 2:");
                printMatrix(bMatrix);

                System.out.println("Sum:");
                int[][] output = getMatrixSum(aMatrix, bMatrix, 1);
                if(output != null)
                    printMatrix(output);
                else
                    System.out.println("Sum is not possible");

                System.out.println("Difference:");
                output = getMatrixSum(aMatrix, bMatrix, -1);
                if(output != null)
                    printMatrix(output);
                else
                    System.out.println("Difference is not possible");

                System.out.println("Product:");
                output = multiplyMatrices(aMatrix, bMatrix);
                if(output != null)
                    printMatrix(output);
                else
                    System.out.println("Product is not possible");
            }
        }
        catch (IOException io){
            System.err.println("File Error: "+io);
        }
    }

    public void fillRows(String matrixString, int [][] matrixFinal){
        //trims the string of the {} then splits it by "},{" between each row
        String[] rows = matrixString.substring(2, matrixString.length() - 2).split("},\\{");//ex: "1,2" and "3,4"
        for(int i = 0; i < rows.length; i++){
            String[] rowValues = rows[i].split(",");
            int[] temp = new int[rowValues.length];
            for(int j = 0; j < rowValues.length; j++)
                temp[j] = Integer.parseInt(rowValues[j]);
            matrixFinal[i] = temp;
        }
    }

    public int[][] getMatrixSum(int[][] aMatrix, int[][] bMatrix, int factor){//factor either 1 or -1 to be used for both sum and difference
        if(aMatrix.length != bMatrix.length || aMatrix[0].length != bMatrix[0].length)
            return null;
        int[][] sum = new int[aMatrix.length][aMatrix[0].length];
        for(int i = 0; i < aMatrix.length; i++)
            for(int j =0 ; j < aMatrix[i].length; j++)
                sum[i][j] = aMatrix[i][j] + factor * bMatrix[i][j];
        return sum;
    }

    public int[][] multiplyMatrices(int[][] aMatrix, int[][] bMatrix){
        if(aMatrix[0].length != bMatrix.length)
            return null;
        int[][] product = new int[aMatrix.length][bMatrix[0].length];
        for(int i = 0; i < aMatrix.length; i++){//A rows
            for(int j = 0; j < bMatrix[0].length; j++){//B columns
                int sum = 0;
                for(int k = 0; k < aMatrix[0].length; k++){//A columns
                    sum += aMatrix[i][k] * bMatrix[k][j];
                }
                product[i][j] = sum;
            }
        }
        return product;
    }

    public void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j =0 ; j < matrix[i].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }
}