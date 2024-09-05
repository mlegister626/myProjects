/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


package datastructures2.assign8;
import java.util.*;
import java.io.*;
/**
 *
 * @author mlegi
 */
public class OpenAddressingComparison {
static void fillArray(ArrayList<Students> arr){
    String name = "";
    String parEdu = "";
    String lunch = "";
    String courseFinished = "";
    String mathScore = "";
    String readingScore = "";
    String writingScore = "";
    Scanner fileInput = null;
    
    try{
        fileInput = new Scanner(new File("exam_data.txt"));
        fileInput.useDelimiter(",");
        while(fileInput.hasNextLine()){
            name = fileInput.next();
            parEdu = fileInput.next();
            lunch = fileInput.next();
            courseFinished = fileInput.next();
            mathScore = fileInput.next();
            readingScore = fileInput.next();
            writingScore = fileInput.nextLine();
            Students newS = new Students(name,parEdu,lunch,courseFinished, mathScore, readingScore, writingScore);
            arr.add(newS);
        }
    }catch(FileNotFoundException e){
        System.out.println("The file was not found.");
    }
}
public static int getHashCode(Students r){
    int hash = 0;
    for(int i = 0;i < r.getName().length(); i++){
        hash += (i+1) * (int)r.getName().charAt(i);
        }
    return hash;
}
public static int hash2(int r){
    return 991 - (r % 991);
}

public static void linearP1(ArrayList<Students> arr){
    //get char at 0 convert to 
    Students [] linearProbe = new Students[1031];
    int numberOfProbes = 0;
    int aveProbes = 0;
    int count =0;
    System.out.println("Linear probing with an Increment of 1");
    System.out.println("Total number of probes :\t average number of probes: ");
    for(int j = 0; j < arr.size(); j++){
        if(j % 50 == 0 && j > 0){
        count++;
        System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + (double)numberOfProbes/50.0);
        numberOfProbes = 0;
        aveProbes = 0;
        }
        int hashCode = getHashCode(arr.get(j));
        int desiredIndex = hashCode % linearProbe.length;
        while(linearProbe[desiredIndex] != null){
            desiredIndex = (desiredIndex + 1) % linearProbe.length;
            ++numberOfProbes;
        }
        linearProbe[desiredIndex] = arr.get(j);
        //System.out.println(arr.get(j).getName() + " " + key);
    }
    count++;
    int hashHim = 0;
     System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + (double)numberOfProbes/50);
     System.out.println("Empty indexes: ");
     for(int i = 0; i < linearProbe.length; i++){
         if(linearProbe[i] == null){
             System.out.print(i + " ");
         }
         if(linearProbe[i] != null && linearProbe[i].getName().compareTo("Mylie Mathis") == 0) {
                hashHim = i;
            }
     }
     System.out.println("\nMylie mathis is at index " + hashHim);
}

public static void linearP5(ArrayList<Students> arr){
    //get char at 0 convert to 
    Students [] linearProbe = new Students[1031];
    int numberOfProbes = 0;
    int aveProbes = 0;
    int count =0;
    System.out.println("\nLinear probing with an Increment of 5");
    System.out.println("Total number of probes :\t average number of probes: ");
    for(int j = 0; j < arr.size(); j++){
        if(j % 50 == 0 && j > 0){
        count++;
        System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + numberOfProbes/50.0);
        numberOfProbes = 0;
        aveProbes = 0;
        }
        int hashCode = getHashCode(arr.get(j));
        int desiredIndex = hashCode % linearProbe.length;
        while(linearProbe[desiredIndex] != null){
            desiredIndex = (desiredIndex + 5) % linearProbe.length;
            ++numberOfProbes;
        }
        linearProbe[desiredIndex] = arr.get(j);
        //System.out.println(arr.get(j).getName() + " " + key);
    }
    count++;
    int hashHim = 0;
     System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + (double)numberOfProbes/50);
     System.out.println("Empty indexes: ");
     for(int i = 0; i < linearProbe.length; i++){
         if(linearProbe[i] == null){
             System.out.print(i + " ");
         }
         if(linearProbe[i] != null && linearProbe[i].getName().compareTo("Mylie Mathis") == 0) {
                hashHim = i;
            }
     }
     System.out.println("\nMylie mathis is at index " + hashHim);
}
public static void quadraticProbe(ArrayList<Students> arr){
    //get char at 0 convert to 
    Students [] linearProbe = new Students[1031];   
    int numberOfProbes = 0;
    int aveProbes = 0;
    int count =0;
    System.out.println("\nQuadratic probing ");
    System.out.println("Total number of probes :\t average number of probes: ");
    for(int j = 0; j < arr.size(); j++){
        if(j % 50 == 0 && j > 0){
        count++;
        System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + numberOfProbes/50.0);
        numberOfProbes = 0;
        aveProbes = 0;
        }
        int count2 = 1;
        int hashCode = getHashCode(arr.get(j));
        int desiredIndex = hashCode % linearProbe.length;
        int originalIndex = desiredIndex;
        while(linearProbe[desiredIndex] != null){
            desiredIndex = (originalIndex + count2 * count2) % linearProbe.length;
            ++numberOfProbes;
            ++count2;
        }
        linearProbe[desiredIndex] = arr.get(j);
        //System.out.println(arr.get(j).getName() + " " + key);
    }
    count++;
    int hashHim = 0;
     System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + (double)numberOfProbes/50);
     System.out.println("Empty indexes: ");
     for(int i = 0; i < linearProbe.length; i++){
         if(linearProbe[i] == null){
             System.out.print(i + " ");
         }
         if(linearProbe[i] != null && linearProbe[i].getName().compareTo("Mylie Mathis") == 0) {
                hashHim = i;
            }
     }
     System.out.println("\nMylie mathis is at index " + hashHim);
}
public static void doubleHash(ArrayList<Students> arr){
    //get char at 0 convert to 
    Students [] linearProbe = new Students[1031];   
    int numberOfProbes = 0;
    int aveProbes = 0;
    int count =0;
    System.out.println("\nDouble hashing: used 991 for double hashing inc: ");
    System.out.println("Total number of probes :\t average number of probes: ");
    for(int j = 0; j < arr.size(); j++){
        if(j % 50 == 0 && j > 0){
        count++;
        System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + numberOfProbes/50.0);
        numberOfProbes = 0;
        aveProbes = 0;
        }
        
        int hashCode = getHashCode(arr.get(j));
        int desiredIndex = hashCode % linearProbe.length;
        int inc = hash2(hashCode);
        while(linearProbe[desiredIndex] != null){
            desiredIndex = (desiredIndex + inc) % linearProbe.length;
            ++numberOfProbes;  
        }
        linearProbe[desiredIndex] = arr.get(j);
    }
    count++;
    int hashHim = 0;
     System.out.println("Cycle: " + count + "\t\t" + numberOfProbes + "\t" + (double)numberOfProbes/50);
     System.out.println("Empty indexes: ");
     for(int i = 0; i < linearProbe.length; i++){
         if(linearProbe[i] == null){
             System.out.print(i + " ");
         }
         if(linearProbe[i] != null && linearProbe[i].getName().compareTo("Mylie Mathis") == 0) {
                hashHim = i;
            }
     }
     System.out.println("\nMylie mathis is at index " + hashHim);
}
    
    public static void main(String[] args) {
        //make methods  for each type of probing
        //linear
        // linear w/ 5 increment
        // quadratic
        //probing
        // double hashing w/ prime key
        //load up array
        ArrayList<Students> s = new ArrayList<Students>();
        Students find =null;
        int hashHim= 0;
        fillArray(s);
        for(int i = 0; i < s.size(); i++){
            if(s.get(i).getName().compareTo("Demetrius Grant") == 0) {
                hashHim = i;
            }
        }
        System.out.println("Demetrius Grants hash code is : " + getHashCode(s.get(hashHim)) + "\n");
        linearP1(s);
        linearP5(s);
        quadraticProbe(s);
        doubleHash(s);
    }
}
