/**
 * Class to test the HashTable insert, remove and search functions
 * Read the csv file and send in the key to get the hash operation
 * Hashcode needs to be implemented using either ASCII CODE or POLYNOMIAL function
 * Hash compression is traditional division method to fit the hashcode in the table range.
 **/
import java.io.*;
public class TestHashTable {
    public static void main(String[] args) {
    
         final int CAPACITY = 37;
         final String INPUT_FILE = "./HashTable.csv";
         
         // First time call with ASCII as hash code
         String hashingType = "ASCII";
         HashTable HT = new HashTable(CAPACITY);
         
         System.out.println("Hash code with ASCII function results:\n ");
         
         //Open the file
         try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE))){ 
            String line;
            int linenum=1;
            //Read the file and process each line 
            while ((line = br.readLine()) != null) {
               try{
            	  String[] elements = line.split(","); //Split input line into elements
            	  char ops = elements[0].charAt(elements[0].length()-1);
            	  String value = "";
            	  String key = elements[1];  //key
                 //process insert, remove or search
                 if (ops == 'I' && elements.length >= 6) {
                  value = "Name: " + elements[2] + " " + elements[3] + " | Dep: " + elements[4] + " | GPA: " + elements[5];
              }              
            	  switch(ops)
            	  {
            	  case 'I': 
            		  if (HT.tableInsert(key, value) !=null) 
            			  System.out.println("Record " + linenum + " Insert " +  elements[1] + " Successful");
            		  else
            			  System.out.println("Record " + linenum + " Insert " +  elements[1] + " Unsuccessful!");
            		  break;
            	  case 'R':
            		  if (HT.tableRemove(key) !=null) 
            			  System.out.println("Record " + linenum + " Remove " +  elements[1] + " Successful");
            		  else
            			  System.out.println("Record " + linenum + " Remove " +  elements[1] + " Unsuccessful!" + " The entry for " + elements[1] + " was not found.");
            		  break;
            	  case 'S':
            		  if (HT.tableSearch(key) !=null) 
            			  System.out.println("Record " + linenum + " Search " +  elements[1] + " Successful");
            		  else
            			  System.out.println("Record " + linenum + " Search " +  elements[1] + " Unsuccessful!" + " The entry for " + elements[1] + " was not found.");
            		  break;
            	  default: break; 
            	  }
            	  linenum ++;
               }
               catch (Exception e) {
                  System.out.print(e.getClass().getName() + " ");
                  System.out.println(e.getMessage());
               }
            }  
            br.close();
         } 
         catch (Exception e) {
            System.out.print(e.getClass().getName() + " ");
            System.out.println(e.getMessage());
         }
         
         //print collisions and hashtable output
         System.out.println("\n" + hashingType + " Hash code collisions (Insertions) : " + HT.getCollisions());
         System.out.println(HT.tablePrint());
    } 
}