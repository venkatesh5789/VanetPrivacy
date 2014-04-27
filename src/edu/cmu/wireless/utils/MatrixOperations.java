package edu.cmu.wireless.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MatrixOperations {

	private static final String DELIMITER = ",";
	
	public static double[][] getMatrixFromCsv(String filename, int rows, int cols)
	{		
		double[][] matrix = new double[rows][cols];

		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(new File(filename)));
			String nextLine;
			String[] parts;
			
			int i=0;
			
			while ((nextLine = bufReader.readLine()) != null) {
				parts = nextLine.split(DELIMITER);
				for (int j=0; j<parts.length;j++) {
					matrix[i][j] = Double.parseDouble(parts[j]);
				}
				i++;
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return matrix;		
	}
	
	public static double[] getDoubleArrayFromCsv(String filename, int rows)
	{
		double[] array = new double[rows];

		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(new File(filename)));
			String nextLine;
			String[] parts;
			
			int i=0;
			
			while ((nextLine = bufReader.readLine()) != null) {
				parts = nextLine.split(DELIMITER);
				
				for (int j=0; j<parts.length;j++) {
					array[i] = Double.parseDouble(parts[j]);
					i++;
				}
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return array;	
	}
	
	public static String[] getStringArrayFromCsv(String filename, int rows)
	{
		String[] array = new String[rows];

		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(new File(filename)));
			String nextLine;
			String[] parts;
			
			int i=0;
			
			while ((nextLine = bufReader.readLine()) != null) {
				parts = nextLine.split(DELIMITER);
				
				for (int j=0; j<parts.length;j++) {
					array[i] = parts[j].toString();
					i++;
				}
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return array;	
	}
	
	public static int[] getIntArrayFromCsv(String filename, int rows)
	{
		int[] array = new int[rows];

		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(new File(filename)));
			String nextLine;
			String[] parts;
			
			int i=0;
			
			while ((nextLine = bufReader.readLine()) != null) {
				parts = nextLine.split(DELIMITER);
				
				for (int j=0; j<parts.length;j++) {
					array[i] = Integer.parseInt(parts[j]);
					i++;
				}
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return array;	
	}
	
	public static long[] getLongArrayFromCsv(String filename, int rows)
	{
		long[] array = new long[rows];

		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(new File(filename)));
			String nextLine;
			String[] parts;
			
			int i=0;
			
			while ((nextLine = bufReader.readLine()) != null) {
				parts = nextLine.split(DELIMITER);
				
				for (int j=0; j<parts.length;j++) {
					array[i] = Long.parseLong(parts[j].toString());
					i++;
				}
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return array;	
	}
	
	public static double[][] multiplyMatrices(double[][] matrix1,double[][] matrix2)
	{
		double[][] result = new double[matrix1.length][matrix2[0].length];
		
		for (int i = 0; i < matrix1.length; i++) { 
            for (int j = 0; j < matrix2[0].length; j++) { 
                for (int k = 0; k < matrix1[0].length; k++) { 
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
		
		return result;
	}
	
	// Predicts rating & box office run. Beta array includes the intercept term as first value.
	public static double predictValue(double[] beta, double[] x)
	{
		double result = beta[0];
		
		for (int i=1;i<beta.length;i++)
		{
			result += x[i-1] * beta[i];
		}
		
		return result;
	}
	
	// This function along with computeLogisticPartGLM replicates the MATLAB function mnrval in Java.
	// It can be used to compute probability of a given movie being in a given revenue class.
	public static double computeLinearPartGLM(double[] beta, double[] x, double constant)
	{
		double result = constant;
		
		for (int i=0;i<beta.length;i++)
		{
			result += x[i] * beta[i];
		}
		
		return result;
	}
	
	public static double computeLogisticPartGLM(double value)
	{
		double result = 1/(1 + Math.exp(value * (-1.0)));
		return result;
	}
	
	public static int getRevenueClass(double[] revenueProbabilities)
	{
		int result = 0;
		
		for (int i = 1; i < Constants.NO_REVENUE_CLASSES; i++)
		{
			if (revenueProbabilities[i] > revenueProbabilities[result])
			{
				result = i;
			}
		}
		return result;
	}
}
