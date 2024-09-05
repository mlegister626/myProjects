// Complete the 6 methods specified in the file below
// The main is complete

import java.io.*;
import java.util.*;


public class Assign7 {
	// 2 dimensional array (matrix) to store data
	private static String[][] netflixData = new String[8807][12];
	public static ArrayList<ShowType> showTypes;
	public static void main(String[] args) {
		
		showTypes = new ArrayList<ShowType>(200);
		//Initializing showTypes
		fillMatrix();
		System.out.print("The entry at row index 6735 is ");
		System.out.println(netflixData[6735][2] + " " + netflixData[6735][7] 
								+ " " + netflixData[6735][8]);
		// Above line should print Fallen 1998 R
		
		System.out.println("The movie with the longest runtime is " +
						longestMovieRunTime()[0] + " at " + 
						longestMovieRunTime()[1] + " mins");
		
		System.out.println("\"Betting on Zero\" was released in " + 
								getReleaseYear("Betting on Zero"));
		
		System.out.println("Timothy Olyphant stars in the following movies and TV shows:");
		System.out.println(whichShowsMovies("Timothy Olyphant"));
		
		System.out.print("Number of items listed in Documentaries is ");
		System.out.println(numberOfType("Documentaries"));

		String mostPopular = mostPopularCategory();
		System.out.println("The most popular category at Netflix is " 
									+ mostPopular);
		System.out.println("which appears " + 
							numberOfType(mostPopular) + " times");
							
	}


	// Method to place all the entries from netflix_titles.txt
	// into the netflixData matrix
	public static void fillMatrix() {
	Scanner scan = null;
	String reader = "";
	int iterator = 0;
	
	try{
		scan = new Scanner(new File("netflix_titles.txt"));
		scan.useDelimiter("###");
		scan.nextLine();
		int j = 0;
		while(scan.hasNextLine()){							
				for(int i = 0; i < 12; i++){
					netflixData[j][i] = scan.next();		
			}
			j++;
		}
		scan.close();		
	}catch(FileNotFoundException e){
		System.out.println("File not found");
	}

	}

	// Use a String array to return the title and the runtime
	// of the movie with the longest runtime 
	public static String[] longestMovieRunTime() {
	//show_id = 0// type =1// title =2// director =3// cast = 4// country = 5// date_added = 6// release =7/ rating= 8// 
	//duration = 9// listed_in = 10// description//11
	// seperate time from minutes
	// make minutes into its own variable
	// compare the minutes to the rest of the array
	String name = netflixData[0][2];
	String seperate = netflixData[0][9];
	String[] split = seperate.split(" min");
	int longMinutes = Integer.parseInt(split[0]);
	
	for(int i = 1; i < netflixData.length; i++){
		try{
			if(isMovie(netflixData[i][1]) == true){
				seperate = netflixData[i][9];
				split = seperate.split(" min");
			//System.out.println(split[0]);
				if(longMinutes < Integer.parseInt(split[0])){
					longMinutes = Integer.parseInt(split[0]);
					name = netflixData[i][2];
				}
			}
		}
		catch(NumberFormatException e){
			System.out.print("");
		}
	}
	
	String[] longestMovie = {name, String.valueOf(longMinutes)};
	return longestMovie;
	}							
	// Method to return the release year a certain movie or TV show was released
	public static int getReleaseYear(String name) {
	//match string name to the name that was passed
	// return the year releaseed[7]
	
		for(int i = 0; i < netflixData.length; i++){
			if(netflixData[i][2].equals(name)){
				return Integer.parseInt(netflixData[i][7]);
			}
		}
		return -1;


	}	

	// Return list of TV shows or films in which a certain actor stars
	// Return as an ArrayList<String>
	public static ArrayList<String> whichShowsMovies(String name) {
	// get actors name // make an array list<string> add to list// return list
	ArrayList<String> names = new ArrayList<String>();

	for(int i = 0; i < netflixData.length; i++){
		String listNames = netflixData[i][4];
		String[] namesSeperated = listNames.split(", ");
		for(int j = 0; j < namesSeperated.length; j++){
			if(namesSeperated[j].equals(name)){
				names.add(netflixData[i][2]);
			}
		}
	}
	return names;
	}

	// Returns the number of items that include type
	// in the "listed_in" column
	// Each "listed_in" entry contains a list of types
	public static int numberOfType(String type) {
	// get a match// add to count // return how many match there are
	String listedIn = "";
	String[] split;
	int count = 0;
	for(int i = 0; i < netflixData.length; i++){
		listedIn = netflixData[i][10];
		split = listedIn.split(", ");
		for(int j = 0; j < split.length;j++){			
			if(split[j].toUpperCase().equals(type.toUpperCase())){
				count++;
			}
		
		}
	}// wrong and must correct tomorrow
	return count;
	}


	// Return the most popular type in the "listed_in" column
	// Each "listed_in" entry contains a list of types
	public static String mostPopularCategory() {
	String genre = "";
	Integer biggest = 0;
	String movie = "";
	Dictionary<String, Integer> dict = new Hashtable<>();
	
	for(int i = 0; i < 8807; i++){
		genre = netflixData[i][10];
		String[] split = genre.split(", ");
		for(String t: split){
			if(dict.get(t) == null){
				dict.put(t,1);
			}else{
				dict.put(t, (Integer)dict.get(t) + 1);
			}
		}
	}
	Enumeration<String> r = dict.keys();
	while(r.hasMoreElements()){
		String next = r.nextElement();
		if(dict.get(next) > biggest){
			biggest = dict.get(next);
			movie = next;
		}
	}
	return movie;
	}
	/*public static String popularCategory(){
		//showTypes
		//System.out.println("here");
		System.out.println(showTypes.get(1).getType());
		ShowType mostPop = showTypes.get(1);
		for(int i = 0; i < showTypes.size(); i++){
			if(mostPop.getFreq() < showTypes.get(i).getFreq()){
				mostPop = showTypes.get(i);
			}
		}
		return mostPop.getType();
	}*/
	public static boolean isMovie(String type){
		type = type.toUpperCase();
		return type.equals("MOVIE");
		
	}
	// making movie type objects with access to their frequencies
	public class ShowType{
		String type;
		int frequency;
		public ShowType(String t, int amount){
			type = t;
			frequency = amount;
		}
		public void setType(String t){
			type = t;
		}
		public void setFreq(int f){
			frequency  = f;
		}
		public String getType(){
			return type;
		}
		public int getFreq(){
			return frequency;
		}
		}
		
	
}
	
		