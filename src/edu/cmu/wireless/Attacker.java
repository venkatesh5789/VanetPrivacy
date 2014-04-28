package edu.cmu.wireless;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.cmu.wireless.utils.MatrixOperations;

public class Attacker extends MatrixOperations{
	
//	private static int entry_lane;
//	private static float entry_speed;
//	
//	public Attacker(int entry_lane, int entry_speed){
//		this.entry_lane = entry_lane;
//		this.entry_speed = entry_speed;
//	}
	
	public int[] AttackerPrediction(int entry_lanes[], int exit_events[], int entry_speed[]){
		
		double matrix[][] = getMatrixFromCsv("/output/sample_probability_csv", 7, 7);
		
		int[] prediction = new int[exit_events.length];
		double best_prediction = 0;
		
		for(int i = 0; i < entry_lanes.length; i++){
			best_prediction = 0;
			for(int j = 0; j < exit_events.length; i++){
				if(matrix[entry_lanes[i]][exit_events[j]] > best_prediction){
					prediction[i] = exit_events[j];
					best_prediction = matrix[entry_lanes[i]][exit_events[j]];
				}
			}
		}
		int prediction_copy[] = prediction; 
		int exit_copy[] = exit_events;
		
		Arrays.sort(prediction);
		Arrays.sort(exit_events);
		
		boolean flag = true;
		for(int i = 0; i < prediction.length; i++){
			if(prediction[i] != exit_events[i])
				flag = false;
		}
		
		if(flag == true)
			return prediction;
		
		if(checkUnique(exit_events)){
			double probability_conflict1[] = null;
			//finding the first exit lane not in the prediction
			for(int i = 0; i < exit_events.length ; i++){
				if(exit_events[i] != prediction[i]){
					if(prediction[i] < exit_events[i]){
						//search for prediction[i] and predictin[i-1] in its copy
						//to get the index to map it to entry lanes which allows you
						//to index into the matrix
						for(int j=0; j < prediction_copy.length; j++){
							
							if(prediction_copy[j] == prediction[i])
								probability_conflict1[j] = matrix[entry_lanes[j]][exit_events[i]];
						}
					}
				}
			}
		}
		else{
			//timing;
		}
		
					
		
		
		
		
		
//		int exit_lane = -1;
//		//int prediction[] = new int[7];
//		double highest_probability = 0;
//		int prediction_count = 0;
//		
//		for(int i=0; i<prediction.length; i++)
//			prediction[i] = -1;
		
		
		
//		for(int i=0; i<7; i++){
//			if(matrix[entry_lane][i] > highest_probability){
//				prediction_count = 0;
//				highest_probability = matrix[entry_lane][i];
//				exit_lane = i;
//				prediction[prediction_count++] = i;
//			}
//			if(matrix[entry_lane][i] == highest_probability && matrix[entry_lane][i] != 0){
//				exit_lane = -1;
//				prediction[prediction_count++] = i;
//			}
//		}
		return prediction;
		
//		if(prediction_count == 1)
//			return exit_lane;
//		else if(prediction_count == 0)
//			return -1;
//		else
//			timingPrediction(prediction, 0);
		
		
		
		
		//		double highest_probability = 0;
//		boolean prediction_done = false;
//		int prediction;
//		
//		
//		int j = 0;
//		for(int i = 0; i<7; i++){
//			if(matrix[entry_lane][i] > highest_probability){
//				highest_probability = matrix[entry_lane][i];
//				prediction = i;
//				prediction_done = true;
//			}
//			else if(matrix[entry_lane][i] == highest_probability){
//				prediction_done = false;
//			}
//		}
//		
		
	}
	
	public boolean checkUnique(int exit_events[]){
		
		List inputList = Arrays.asList(exit_events);
		Set inputSet = new HashSet(inputList);
		if(inputSet.size()< inputList.size()){
            return true;
        }
		
		return false;
	}

	public int timingPrediction(int prediction[], int entry_speed){
		return 0;
		
	}
	
	
//	public static void main(String args[])
//	{
//		double matrix[][] = getMatrixFromCsv("/output/sample_probability_csv", 7, 7);
//		System.out.println(matrix[0][6]);
//		
//	}

}
