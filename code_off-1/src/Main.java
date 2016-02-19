import com.sun.deploy.util.StringUtils;
import org.w3c.dom.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tinashe.chaterera on 2016/02/19.
 */
public class Main {

    Map<String,Integer> alphabetMap;
    public  static void main (String [] args){
        Main main = new Main();
        //test palindromes
        System.out.println("abbba ==>" + main.isPalindrome("abbba"));
        System.out.println("abbbc ==>" + main.isPalindrome("abbbc"));
        System.out.println("abbbba ==>" + main.isPalindrome("abbbba"));
        System.out.println("abbbbc ==>" + main.isPalindrome("abbbba"));

        //test string values
        System.out.println("abbbba ==>" + main.getStringValue("abbbba"));
        System.out.println("AABBB ==>" + main.getStringValue("AABBB"));

        BufferedReader br = null;
        try {
             br = new BufferedReader(new FileReader("input.txt"));
            StringBuilder sbOutputResult = new StringBuilder();
            //get first line
            String line =  br.readLine();
            int numberOfStrings = Integer.parseInt(line);

            List<String> allStringsList = new ArrayList<String>();
            while ( (line = br.readLine()) != null) {
                allStringsList.add(line);
            }

            for(String s:allStringsList){
                sbOutputResult.append(s +"\n");
                sbOutputResult.append(main.isPalindrome(s) +"\n");

                int currentStringValue = main.getStringValue(s);
                for(String anotherString:allStringsList){
                    if(currentStringValue == main.getStringValue(anotherString) && s!=anotherString){
                        sbOutputResult.append(anotherString +"\n");
                    }
                }
            }

            File file = new File("outputFile.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(sbOutputResult.toString());
            bw.close();


        } catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try {
                br.close();
            } catch (Exception e){

            }
        }

    }
    public Main (){
        String ALPHABET ="a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
        String[] ALPHABET_ARRAY = ALPHABET.split(",");

         alphabetMap = new HashMap<String,Integer>();
        for(int i =0; i< ALPHABET_ARRAY.length; i++){
            alphabetMap.put(ALPHABET_ARRAY[i],i);
            alphabetMap.put(ALPHABET_ARRAY[i].toUpperCase(),i +1);
        }
    }

    private boolean isPalindrome( String inputLine){
        boolean result = false;

        if((inputLine.length() %2) == 0 ){
            String firstValue = inputLine.substring(0,inputLine.length()/2);

            String secondValue = reverseString(inputLine.substring(inputLine.length()/2 ));

            result = firstValue.equals(secondValue);
        }

        if((inputLine.length() %2) != 0 ){
            char pivotCharacter = inputLine.charAt((inputLine.length() +1)/2);
            String firstValue = inputLine.substring(0,(inputLine.length() +1)/2);
            System.out.println( (inputLine.length() +1)/2 -1);
            String secondValue = reverseString(inputLine.substring((inputLine.length() +1)/2 - 1));

            result = firstValue.equals(secondValue);
        }

        return  result;
    }

    private String reverseString(String input){
        StringBuffer sb = new StringBuffer();

        for(int i=input.length()-1; i>=0; i--){
            sb.append(input.charAt(i));
        }

        return  sb.toString();
    }

    private int getStringValue(String input){
        int result =0;
        for(int i=0; i<input.length(); i++) {
            result = result + alphabetMap.get(input.charAt(i) +"");
        }
        return result;
    }
}
