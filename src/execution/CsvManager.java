package execution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CsvManager {

	public static void generateCsv(String csvFile, int aggregationOrder) {

		System.out.println("setting up...");

		String line = "";
		String cvsSplitBy = ",";
		boolean firstLine = true;

		java.util.ArrayList<Integer> force = new java.util.ArrayList<Integer>();
		java.util.ArrayList<Integer> startX = new java.util.ArrayList<Integer>();
		java.util.ArrayList<Integer> endX = new java.util.ArrayList<Integer>();
		java.util.ArrayList<Integer> deltaX = new java.util.ArrayList<Integer>();
		java.util.ArrayList<Double> deltaXDeg = new java.util.ArrayList<Double>();

		java.util.ArrayList<Integer> aggregateDeltaX = null;
		java.util.ArrayList<Double> aggregateDeltaXdouble = null;
		java.util.ArrayList<Double> aggregatedeltaXDeg = null;

		String title = null;

		System.out.println("reading input file...");

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

			// BEGIN READ

			while ((line = br.readLine()) != null) {

				if(!firstLine) {

					line = line.replaceAll(" ", "");

					String[] row = line.split(cvsSplitBy);
					force.add(Integer.parseInt(row[0]));
					startX.add(Integer.parseInt(row[1]));
					endX.add(Integer.parseInt(row[2]));
					deltaX.add(Integer.parseInt(row[3]));
					deltaXDeg.add(Double.parseDouble(row[4]));

				} else {
					firstLine = false;
					title = line;
				}

			}

			// END READ
			
			// for more precision
			java.util.ArrayList<Double> deltaXdouble = new java.util.ArrayList<Double>(); 
			deltaXdouble = doubleListToIntegerList(deltaX);
			
			// BEGIN ERROR CORRECTION
			
			deltaXDeg = Corrector.adjust(deltaXDeg, "deltaXDeg");
			deltaXdouble = Corrector.adjust(deltaXdouble, "deltaX");	
			
			// END ERROR CORRECTION	
			
			// BEGIN AGGREGATION

			System.out.println("aggregation...");

			aggregatedeltaXDeg = Aggregator.aggregate(deltaXDeg, aggregationOrder);
			aggregateDeltaXdouble = Aggregator.aggregate(deltaXdouble, aggregationOrder);
			
			// END AGGREGATION
			
			aggregateDeltaX = integerListToDoubleList(aggregateDeltaXdouble);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: cannot find '" + csvFile + "' file.");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: cannot read " + csvFile + "' file.");
			return;
		}

		// write results

		String newCsvFileName = "output-AG-" + aggregationOrder + "-T-" + System.currentTimeMillis() + ".csv";
		System.out.println("generating new csv file '" + newCsvFileName + "'...");

		for(int i = -1; i < force.size(); i++) {
			try (BufferedWriter bw 
					= new BufferedWriter(new FileWriter(newCsvFileName, true))) {
				if(i == -1) {
					bw.write(title);
					bw.newLine();
					bw.flush();
				} else {
					int adjustedEndX = startX.get(i) + aggregateDeltaX.get(i);
					String s = force.get(i) + ", " + 
							startX.get(i) + ", " +
							adjustedEndX + ", " + 
							aggregateDeltaX.get(i) + ", " +
							aggregatedeltaXDeg.get(i);   
					bw.write(s);
					bw.newLine();
					bw.flush();
				}
			} catch(IOException ex) { 
				ex.printStackTrace();
			}
		} 

		System.out.println("DONE!");
		JOptionPane.showMessageDialog(null, "Process completed! Output file: '" + newCsvFileName + "'.");

	}

	private static ArrayList<Integer> integerListToDoubleList(ArrayList<Double> input) {	
		java.util.ArrayList<Integer> output = new java.util.ArrayList<Integer>();
		for(double element:input) {
			output.add((int) element);
		}
		return output;
	}

	private static ArrayList<Double> doubleListToIntegerList(ArrayList<Integer> input) {		
		java.util.ArrayList<Double> output = new java.util.ArrayList<Double>();
		for(double element:input) {
			output.add(element);
		}
		return output;
	}

}