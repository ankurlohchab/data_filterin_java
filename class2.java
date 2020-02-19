
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class class2 
{
    static String input_loc=null, output_loc = null,out_type= null, manipulations = null;
    
    public static void main (String args[])
    {
       
        
        {
          try{
            if(args[0].matches("-help"))
             throw new IllegalArgumentException("help ");
             }
          catch (IllegalArgumentException |ArrayIndexOutOfBoundsException help)
          {
              System.out.println("Usage: java -jar solution.jar \n -d [dataset complete address] \n -m [1 : removeing item with blank email, \n\t  2: removing item with blank address, \n\t 3: <both options 1 and 2>"
                      + "\n\t 4: avg price of orders per year, \n\t 5 : total price of order per year, \n\t 6: top three customers with max no of orders, \n\t 7 : <option 4,5, and 6 combined>  ] \n -o text [output file address]");
              System.exit(0);
          }
                  
            try 
            {
            if(!(args[0].matches("-d") && (!args[1].isEmpty())  && args[2].matches("-m") && (!args[3].isEmpty()) && args[4].matches("-o")  && (!args[5].isEmpty()) && (!args[6].isEmpty()) ))
            throw new IllegalArgumentException("illegal arguemtns");
            }
            catch (IllegalArgumentException | ArrayIndexOutOfBoundsException iae)
                
                    {
                       System.out.println("exiting...Please pass the arguements in the format -d [DATASETLOCATION] -m [MANIPULATIONMETHODS]-o [OUTPUTTYPE] [OUTPUTFILE]!");
                       System.exit(0);
                    }
        
        }
        
        // global arguements data stored 
    input_loc = args[1];
    manipulations = args[3];
    out_type = args[5];
    output_loc = args[6];
    
    
    
    readInput(input_loc);
        // let's print all the person read from CSV file
       
    }
    private static String readInput(String fileName)
    {
        long paid_total_18=0, unpaid_total_18=0;
        long paid_total_19=0, unpaid_total_19=0;
        int paid_count_18=0, unpaid_count_18=0;
        int paid_count_19=0, unpaid_count_19=0;
        float paid_avg_18=0,unpaid_avg_18=0;
        float paid_avg_19=0,unpaid_avg_19=0;
        
        List<String> email_list = new ArrayList<>();
      //  Map <String, Integer>map = new HashMap();   // create a map object 
    
      
        File fout = new File(output_loc);
        FileOutputStream fos = null;
        try {            fos = new FileOutputStream(fout);        } 
        catch (FileNotFoundException ex) 
        {
            System.out.println("The output file path is incorrect...please provide correct path...");
            System.exit(0);
        }
        BufferedWriter bw = new BufferedWriter (new OutputStreamWriter(fos));
            
    
    
        Path pathToFile = Paths.get(fileName);
        //try (BufferedReader br = Files.newBufferedReader (pathToFile,StandardCharsets.US_ASCII)){
        try {
            BufferedReader br = Files.newBufferedReader (pathToFile,StandardCharsets.US_ASCII);
            String line = br.readLine();
            
            while (line != null){
                String[] attributes = line.split(",");
                       

  // condition one
            
                if(manipulations.matches("3"))
                {    

                    if(!((attributes[2].matches("")) && (attributes[3].matches(""))))
                    {
                    System.out.println(line);

                        bw.write(line);
                        bw.newLine();
                    }
                }
                else if (manipulations.matches("1"))
                {
                    if(!(attributes[2].matches("")))
                    {
                    System.out.println(line);

                        bw.write(line);
                        bw.newLine();
                    } 
                }
                else if (manipulations.matches("2")) 
                {    
                    if(!(attributes[3].matches("")))
                    {
                    System.out.println(line);

                        bw.write(line);
                        bw.newLine();
                    } 
                }   
  // condition two
                
                
                {
                    //avg price of order per year paid and unpaid
                    if(attributes[5].matches("PAID"))
                    {
                        if(attributes[1].contains("2018"))
                        {
                            paid_total_18 += Long.parseLong(attributes[4]); 
                            paid_count_18++;
                        }
                        else if (attributes[1].contains("2019"))
                        {
                            paid_total_19 += Long.parseLong(attributes[4]); 
                            paid_count_19++;
                        }
                    }
                    if(attributes[5].matches("UNPAID"))
                    {
                        if(attributes[1].contains("2018"))
                        {
                            unpaid_total_18 += Long.parseLong(attributes[4]); 
                            unpaid_count_18++;
                        }
                        else if (attributes[1].contains("2019"))
                        {
                            unpaid_total_19 += Long.parseLong(attributes[4]); 
                            unpaid_count_19++;
                        }
                    }
                }
                
            // top 3 customers with highest no of orders
            // customers are seperated by email address,,, all customers without e mail are not coonsidered
                
                if(!attributes[2].matches(""))
                email_list.add(attributes[2]);
                
              
                line = br.readLine();
            }
            
            if(manipulations.matches("4") || manipulations.matches("7"))
            {
                paid_avg_18=(float)paid_total_18/(float)paid_count_18;
                unpaid_avg_18=(float)unpaid_total_18/(float)unpaid_count_18;

                System.out.println("paid avg yr 2018 ="+ paid_avg_18);          
                System.out.println("Unpaid Avg yr 2018= "+ unpaid_avg_18);

                bw.newLine();
                bw.write("paid avg yr 2018="+ paid_avg_18);
                bw.newLine();

                bw.newLine();
                bw.write("Unpaid Avgyr 2018= "+ unpaid_avg_18);
                bw.newLine();

                 paid_avg_19=(float)paid_total_19/(float)paid_count_19;
                unpaid_avg_19=(float)unpaid_total_19/(float)unpaid_count_19;

                System.out.println("paid avg yr 2019 ="+ paid_avg_19);          
                System.out.println("Unpaid Avg yr 2019= "+ unpaid_avg_19);

                bw.newLine();
                bw.write("paid avg yr 2019="+ paid_avg_19);
                bw.newLine();

                bw.newLine();
                bw.write("Unpaid Avg yr 2019= "+ unpaid_avg_19);
                bw.newLine();
            }    
                
                
                
            if(manipulations.matches("5") || manipulations.matches("7"))
            {    
                System.out.println("Total paid orders for year 2018 are: "+paid_total_18);
                bw.newLine();
                bw.write("Total paid orders for year 2018 are: "+paid_total_18);
                bw.newLine();

                

                System.out.println("Total paid orders for year 2019 are: "+paid_total_19);
                bw.newLine();
                bw.write("Total paid orders for year 2019 are: "+paid_total_19);
                bw.newLine();
            }
            
            // if user has selected option 6 or 7 as input option
            if(manipulations.matches("6") || manipulations.matches("7"))
            {
                Map <String,Long> map = email_list.stream().collect(Collectors.groupingBy(w -> w,Collectors.counting()));

                List<Map.Entry<String, Long>> result = map.entrySet().stream()
                 .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                 .limit(3)
                 .collect(Collectors.toList());

                System.out.println("Top three orders are : " + result);
                bw.newLine();
                bw.write("Top three orders are : " + result);
                bw.close();
            }
        }
        catch(IOException ioe){
           // ioe.printStackTrace();
           System.out.println("Incorrect input file path... exiting...please see -help for format...");
        }
        return null;
    }
   
    
   

}




    
  


    






