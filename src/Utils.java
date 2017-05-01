import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale.FilteringMode;

import javax.imageio.stream.FileImageInputStream;


public class Utils {
	static Map <String,Vector> getThoughtVectors(String filePath){
		Map<String, Vector> listMap = new HashMap<String, Vector>();
		File file = new File(filePath);
		int nbWord = 0, dimension = 0;
		/***
		 * This method allow us to read the vectors form the file ve
		 * ***/
		try(FileReader fileReader = new FileReader(file);
			BufferedReader input = new BufferedReader(fileReader)){
			String inputLine = input.readLine();
			String[] tmp = inputLine.split(" ");
			
			/***Get the number of words in file vecs50 
			 * and the dimension of the vector.***/
			nbWord = Integer.parseInt(tmp[0]);
			dimension = Integer.parseInt(tmp[1]);
			
			/*** get all vectors in the file vecs50**/
			Vector tmpVector = null;
			String word = null;
			for(int i = 0; i<nbWord; i++){
				tmpVector = new Vector(50);				
				tmp = input.readLine().split(" ");
				word = tmp[0];
				for (int j=0; j<dimension ;j++) {
					tmpVector.set(i) = Double.parseDouble(tmp[i+1]);
				}
				
				listMap.put(word, tmpVector);
			}
		}
		return listMap;
	}
}
