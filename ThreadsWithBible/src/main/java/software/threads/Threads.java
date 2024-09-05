/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package software.threads;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mlegi
 */
public class Threads extends Thread{
   
    
    public static void single(String [] arr) {  
        
     
        int wordCounter = 0;
        for(int i = 1; i < arr.length;i++){
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("bible.txt")))) {
            String line = null;      
            while ((line = reader.readLine()) != null) {
                if(line.contains(arr[i])){
                    ++wordCounter;
                }
            }
            System.out.println(arr[i] + " shows up " + wordCounter + " times.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
        }
        wordCounter = 0;
        }
    }

    public static void main(String[] args) {
        char t = 'x';       
        Scanner scan = new Scanner(System.in);       
        if(args[0].charAt(0) == 's'){
            single(args);
        }else if(args[0].charAt(0) == 'm'){
            
        }
    }
}





