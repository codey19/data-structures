import java.io.*;

public class LucasNumber{
	String bestString;

	public static void main(String[] args){
		Work app=new Work();

	}
	
	public Work(){
		bestString = "";
		load("10LetterWords.txt");
		System.out.println(bestString);
	}

	    public void load(String fileName){
	        try{
	            File file = new File(fileName);
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            String line="";
	            while((line=br.readLine())!= null) // if line not null store as String
	            {
					if(!isVowel(line.charAt(0)) && hasSixVowels(line)){
						bestString = line;
						return;
					}

	            }
	        }
	        catch (IOException io){
	            System.err.println("File Error: "+io);
	        }
	   }

	   public boolean isVowel(char letter){
		  return letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u';
		}

		public boolean hasSixVowels(String word){
			int numVowels = 0;
			for(char e: word.toCharArray())
				if(isVowel(e))
					numVowels++;
			return numVowels >= 6;
		}
}