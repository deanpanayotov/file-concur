import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileConcur {
	
	public static void main(String[] args){
	String filename;
	filename=read();
	find(filename);
	//find("C:\\test.txt");
	}
	
	public static String read(){
		String userInput=null;
		try
        {
			System.out.println("Please enter: ");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            userInput = in.readLine();
            System.out.println("\n\nUser entered -> " + userInput);
        }
        catch(IOException e)
        {
            System.out.println("IOException");
        }
		return userInput;
	}
	
	public static void find(String filename){
		int[] count= new int[1000];
		for(int i=0;i<1000;i++){
			count[i]=0;
		}
		String strLine="", strPhrase, strArray="";
		char[] ch;
		try{
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((strLine = br.readLine()) != null)   {
				strLine=strLine.toLowerCase();
				ch = strLine.toCharArray();
				for (int i=0;i<ch.length-1;i++){
				if(Character.isLetter(ch[i]) && Character.isLetter(ch[i+1])){
					strPhrase=ch[i]+""+ch[i+1];
					if(!strArray.contains(strPhrase)){ 
						strArray+=strPhrase+" ";
						count[strArray.indexOf(strPhrase)/3]=1;
					}else{
						count[strArray.indexOf(strPhrase)/3]++;
					}
				}
				}
				
				/*
				while(strLine.length()>0){
					if(strLine.contains(phrase )){
						++concur;
						strLine=strLine.substring(strLine.indexOf(phrase)+phrase.length());
					}else{
						strLine="";
					}
				}*/
				
			}
			in.close();
		    }catch (Exception e){
		  System.err.println("Error: " + e.getMessage());
		  }
		for(int i=0;i<strArray.length()/3;i++){
			System.out.print(strArray.substring(i*3,i*3+2)+ ": "+count[i]+"; ");
			if(i%10==0){
				System.out.println();
			}
		}
	}
}
