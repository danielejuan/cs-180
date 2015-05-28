import java.io.*;
import java.util.*;

/*

This main method has two parts: training and testing. For this, I've divided the cleveland.data dataset
into the clevelandTrain.txt and clevelandTest.txt, 60% and 40% respectively.

The method parses the clevelandTrain data set first, then trains the classifier with it. Next,
it parses clevelandTest.txt then classifies the vectors. The accuracy is calculated and printed out.

At the end, the program receives vector text files from the user for classification. It prints
"Y" for the presence of heart disease and "N" for the opposite.
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
			BufferedReader br;
			String line;
			Scanner sc = new Scanner(System.in);
			String trainDir;
			String testDir;
			
			//C:\Users\jedpatrickdatu\Documents\GitHub\cs-180\clevelandTrain.txt
			//C:\Users\jedpatrickdatu\Documents\GitHub\cs-180\clevelandTest.txt
			//C:\Users\jedpatrickdatu\Documents\GitHub\cs-180\sampleinput.txt
			
			//Intro and user input:
			System.out.println("Welcome to the Heart Disease Diagnosis Program.\n\nEnter the absolute directory of training file: ");
			trainDir = sc.nextLine();
			System.out.println("\nEnter the absolute directory of the testing file: ");
			testDir = sc.nextLine();
			
			//Part 1: Training with the clevelandTrain.txt dataset:
			System.out.println("Training...");
			br = new BufferedReader(new FileReader(trainDir));
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
						bayes.learn("Has no heart disease", vector.getList());
				} else {
						bayes.learn("Has heart disease", vector.getList());
				}
			}
			
			
			
			//Part 2: Testing with the clevelandTest.txt dataset:
			
			float numTotalVectors = 0;
			float numCorrect = 0;
			float accuracy;
			
			System.out.println("Testing...");
			br = new BufferedReader(new FileReader(testDir));
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
			System.out.println("Training and testing done.\n");
			
			
			//User input:
			String inputDir;
			features = new String[13];			
			while(true){
				System.out.println("Enter absolute directory of input to be diagnosed(Enter 'quit' to exit.):");
				inputDir = sc.nextLine();
				if(inputDir.equals("quit")){
					System.out.println("Goodbye!");
					br.close();
					break;
				}
				br = new BufferedReader(new FileReader(inputDir));
				
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
		
					
					vector.age = features[0];
					vector.sex = features[1];
					vector.cp = features[2];
					vector.trestbps = features[3];
					vector.chol = features[4];
					vector.fbs = features[5];
					vector.restecg = features[6];
					vector.thalach = features[7];
					vector.exang = features[8];
					vector.oldpeak = features[9];
					vector.slope = features[10];
					vector.ca = features[11];
					vector.thal = features[12];
					
					if(bayes.classify(vector.getList()).getCategory().equals("Has no heart disease")){
						System.out.print("N");
					} else {
						System.out.print("Y");
					}
					System.out.println();
				}
			}
		
		} catch (Exception e) {
            System.out.println("Main: Something bad happened :(");
            e.printStackTrace();
        }
    }

}