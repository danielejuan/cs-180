import java.io.*;
import java.util.*;

/*

This main method has two parts: training and testing. For this, I've divided the cleveland.data dataset
into the clevelandTrain.txt and clevelandTest.txt, 60% and 40% respectively.

The method parses the clevelandTrain data set first, then trains the classifier with it. Next,
it parses clevelandTest.txt then classifies the vectors. The accuracy is calculated and printed out.

Accuracy results with different lambdas:

-0.0: 58.90411%
-0.01: 100%
-0.2: 98.630135%
-0.3: 98.630135%
-0.4: 97.26027% 
-0.5: 97.26027% 
-1.0: 97.26027%
-2.0: 94.52046% 
-5.0: 91.78082%
-10.0: 90.41096%
-20.0: 90.41096%
-50.0: 86.30137%
-100.0: 86.30137%
 

*/

public class Main {
	
	@SuppressWarnings("unchecked")
    public static void main(String[] args) {
		try {
			final Classifier<String, String> bayes = new BayesClassifier<String, String>();
			bayes.setMemoryCapacity(500);
			FeatureVector vector;		
			String[] features = new String[76];
			String[] parsedLine;
			BufferedReader br = new BufferedReader(new FileReader("clevelandTrain.txt"));
			String line = br.readLine();
			
			
			//Part 1: Training with the clevelandTrain.txt dataset:
			while (line != null) {
				vector = new FeatureVector();
				
				for(int numFeaturesRecorded = 0; numFeaturesRecorded < features.length;){
					parsedLine = line.split(" ");
					for(int j = 0; j < parsedLine.length; j++){
						features[numFeaturesRecorded] = parsedLine[j];
						numFeaturesRecorded++;
					}
					line = br.readLine();
				}
	
				
				vector.age = features[2];
				vector.sex = features[3];
				vector.cp = features[8];
				vector.trestbps = features[9];
				vector.chol = features[11];
				vector.fbs = features[15];
				vector.restecg = features[18];
				vector.thalach = features[31];
				vector.exang = features[37];
				vector.oldpeak = features[39];
				vector.slope = features[40];
				vector.ca = features[43];
				vector.thal = features[50];
				vector.num = features[57];
				
				
				
				if(vector.num.equals("0")){
						bayes.learn("Has no heart disease", vector.getList());
				} else {
						bayes.learn("Has heart disease", vector.getList());
				}
			}
			
			
			
			//Part 2: Testing with the clevelandTest.txt dataset:
			
			float numTotalVectors = 0;
			float numCorrect = 0;
			float accuracy;
			br = new BufferedReader(new FileReader("clevelandTest.txt"));
			line = br.readLine();
			
			while (line != null) {
				vector = new FeatureVector();
				
				for(int numFeaturesRecorded = 0; numFeaturesRecorded < features.length;){
					parsedLine = line.split(" ");
					for(int j = 0; j < parsedLine.length; j++){
						features[numFeaturesRecorded] = parsedLine[j];
						numFeaturesRecorded++;
					}
					line = br.readLine();
				}
	
				
				vector.age = features[2];
				vector.sex = features[3];
				vector.cp = features[8];
				vector.trestbps = features[9];
				vector.chol = features[11];
				vector.fbs = features[15];
				vector.restecg = features[18];
				vector.thalach = features[31];
				vector.exang = features[37];
				vector.oldpeak = features[39];
				vector.slope = features[40];
				vector.ca = features[43];
				vector.thal = features[50];
				vector.num = features[57];
				
				if(vector.num.equals("0")){
						if(bayes.classify(vector.getList()).getCategory().equals("Has no heart disease")){
							numCorrect++;
						}
				} else if(bayes.classify(vector.getList()).getCategory().equals("Has heart disease")){
						numCorrect++;
				}
				numTotalVectors++;
					
				
			}
			
			accuracy = (numCorrect / numTotalVectors)*100;	
			System.out.println("Accuracy: " + accuracy + "%");
			
			br.close();
			
		
		} catch (Exception e) {
            System.out.println("Main: Something bad happened :(");
            e.printStackTrace();
        }
    }

}