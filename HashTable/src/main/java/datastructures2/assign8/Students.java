/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datastructures2.assign8;

/**
 *
 * @author mlegi
 */
public class Students {
    String name;
    String parEdu;
    String lunch;
    String courseFinished;
    String mathScore;
    String readingScore;
    String writingScore;
    
    public Students(String n, String p, String l, String cF, String math, String reading, String writing){
        name = n;
        parEdu = p;
        lunch = l;
        courseFinished = cF;
        mathScore = math;
        readingScore = reading;
        writingScore = writing;
    }
    public void setName(String n){
        name = n;
    }
    public void setparEdu(String p){
        parEdu = p;
    }
    public void setLunch(String l){
        lunch = l;
    }
    public void setCourse(String s){
        courseFinished = s;
    }
    public void setMath(String m){
        mathScore = m;
    }
    public void setReading(String r){
        readingScore = r;
    }
    public void setWriting(String w){
        writingScore = w;
    }
    public String getName(){
        return name;
    }
    public String getParEdu(){
        return parEdu;
    }
    public String getLunch(){
        return lunch;
    }
    public String getCourse(){
        return courseFinished;
    }
    public String getMath(){
        return mathScore;
    }
    public String getReading(){
        return readingScore;
    }
    public String getWriting(){
        return writingScore;
    }  
}
