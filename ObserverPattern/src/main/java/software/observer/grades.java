/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package software.observer;

import java.util.*;
import java.io.*;
/**
 *
 * @author mlegi
 */
class FileManager{
    private static String file;
    private static ArrayList<Integer> grades = new ArrayList();
    FileManager(String file){
        this.file = file;
    }   
    public static String GetInstance(String f){
      
        synchronized(FileManager.class){
            if (FileManager.file == null){
                FileManager.file = f;
            }
        }
        return FileManager.file;
    }    
    public void AddGrade(int grade){
        try{
            FileWriter fileWriter = new FileWriter(GetInstance(file),true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(grade));
            bufferedWriter.newLine();
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println("IOException try something else");
        }
        grades.add(grade);
        System.out.println(GetAllGrades(grades).toString());
        DisplayAverage(grades);
    }
    public Integer GetFirstGrade(){
        try{
        File myFile = new File(GetInstance(file));
        Scanner scan = new Scanner(myFile);
        int reader = 0;
        if(scan.hasNextLine()==true){
            reader = scan.nextInt();
            return reader;
        }
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return -1;
    }
    public static ArrayList <Integer> GetAllGrades( ArrayList <Integer> g){
        return g;
    }
    public void DeleteAllGrades(){
        try{
            FileWriter writer = new FileWriter(FileManager.file);
            writer.close();
        }catch(IOException e){
            System.out.println("IOException try something else");
        }
        grades.clear();
        System.out.println(GetAllGrades(grades).toString());
        DisplayAverage(grades);
    }
    
    public static void DisplayAverage(ArrayList <Integer> grader) {
        double average = 0;
        for(Integer g: grader){
            average += g;
        }
        System.out.println("The average score is:  " + average / grades.size());
    }
}
public class grades {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner (System.in);
        int input = 0;
        int addGrade = 0;
        FileManager manage = new FileManager("grades.txt");
        
        while(input != 3){
            System.out.println("Enter in (1) to add a grade (2) to delete all the grades or (3) to end the program or (0) to get the first grade in the list");
            input = scan.nextInt();
            if(input ==1){
                System.out.println("Add the grade that you'd like: ");
                addGrade = scan.nextInt();
                manage.AddGrade(addGrade);
            }else if( input ==2){
                manage.DeleteAllGrades();
            }else if(input ==0){
                System.out.println(manage.GetFirstGrade() + " is the first grade in the file");
            }
        }
    }  
}
