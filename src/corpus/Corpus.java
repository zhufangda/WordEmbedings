package corpus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.TreeMap;
import java.nio.file.Paths;
/**
 * An object that maps all the word French to vector.
 * A <tt>Corpus</tt> cannot contain duplicate words;
 * each words can map to at most one vector.
 *
 * <p>This class will read a file vecs50 in order to get all the words and its
 * vectors</p>
 *
 * <p>The <tt>Corpus</tt> class provides one factory method , which
 * allow user to get a corpus from a file. There are no constructor for users.
 * 
 * @since 1.0
 */

public class Corpus {
    
	Map<String, Vector> vecDir;
    private int nbWord = 0; 
    
    /***
     * Gets the number of words in the corpus
     * @return the number of words in the corpus
     * **/
    public int getNbWord() {
        return nbWord;
    }
    
    /**
     * Returns the dimension of the vector representing the word
     * @return the dimension of vector
     * **/
    public int getDimension() {
        return dimension;
    }

    private int dimension = 0;
    
	private Corpus() {
		vecDir = new HashMap<>();
	}
	
    /**
     * Associates the specified vector with the specified word in this corpus
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value. 
     * 
     * @param word word with which the specified vector is to be associated
     * @param vect vector to be associated with the specified word
     */
	public void putWord(String word, Vector vect){
		vecDir.put(word, vect);
	}
	
	/*** Gets the corpus from the file vecs50
	 * @param filePath the file path of the vecs50
	 * **/
	public void readFromFile(String filePath){
		File file = new File(filePath);
		
		/***
		 * This method allow us to read the vectors form 
		 * the file vecs50
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
					tmpVector.setComponent(j,Double.parseDouble(tmp[j+1]));;
				}
				
				putWord(word, tmpVector);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	   public void readFromFileIo(String filePath){

	        Map<String, Vector> newvec = new ConcurrentHashMap<>();
	        /***
	         * This method allow us to read the vectors form 
	         * the file vecs50
	         * ***/
	        Path path = Paths.get(filePath);

	           
	        try (BufferedReader rdr = Files.newBufferedReader(Paths.get(filePath),
	                Charset.defaultCharset())) {
	             
	            String input = rdr.readLine();
	             String[] tmp = input.split(" ");
	                
	            /***Get the number of words in file vecs50 
	             * and the dimension of the vector.***/
	            nbWord = Integer.parseInt(tmp[0]);
	            dimension = Integer.parseInt(tmp[1]);
	            
	            /*** get all vectors in the file vecs50**/
	            Vector tmpVector = null;
	            String word = null;
	            for(String line; (line = rdr.readLine()) != null;){ 
	                tmpVector = new Vector(50);             
	                tmp = line.split(" ");
	                word = tmp[0];
	                for (int j=0; j<dimension ;j++) {
	                    tmpVector.setComponent(j,Double.parseDouble(tmp[j+1]));;
	                }
	                
	                newvec.put(word, tmpVector);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    /**
     * Returns the vector to which the specified word is mapped.
     *
     * <p>More formally, if this corpus contains a mapping from a word
     * to a vector, then this method returns its vector; otherwise
     * it will throw a <code>WordNotFoundException</code>.
     * @param word the word whose associated vector is to be returned
     * @return the vector to which the specified word is mapped.
     * @throws WordNotFoundException if the word is not in corpus.
     * @throws NullPointerException if the specified word is null and this corpus
     *         does not permit null keys
     */
	public Vector getVector(String word) throws WordNotFoundException{
		Vector vector = this.vecDir.get(word);
		if(vector==null)
			throw new WordNotFoundException("Can not find the word '" + word + "' in corpus!");
		return vector; 
	}
	
	/***
	 * Returns the most similar words with specified vector and its correlation coefficient.
	 * <p> This method will calculate the cosine similarity between the specified vector and
	 * each word in corpus.It can return a specified number of similar word.These mappings from
	 *  a similarity to a word will save in a map.
	 * @param vect the reference vector with which to compare.
	 * @param size the number of similar words which to return
	 * @return a mapping sorted by the similarity containing the specified number of mappings 
	 * 			from a similarity to a word 
	 * </p> 
	 * 
	 * ***/
	public List<Map.Entry<Double,String>> getSimilarWord(Vector vect,int size){
		
		TreeMap<Double, String> sortedMap = new TreeMap<>((a,b)-> b.compareTo(a));
		for (Entry<String, Vector> entry: this.vecDir.entrySet()) {
			sortedMap.put(entry.getValue().similarity(vect), entry.getKey());
		}
		
		List<Map.Entry<Double, String>> resultat = new ArrayList<>(size);
		int comptor = 0;
		for (Map.Entry<Double, String> couple: sortedMap.entrySet()) {
			resultat.add(couple);
			comptor++;
			if(comptor==size) break;
		}
		
		return resultat;
	}
	
	/***
	 * Returns the most similar words with specified word and its correlation coefficient.
	 * <p>This method will calculate the cosine similarity between the specified word and
	 * each word in corpus.It can return a specified number of similar word.These mappings from
	 *  a similarity to a word will save in a map.</p>
	 * @param word the reference word with which to compare.
	 * @param size the number of similar words which to return
	 * @return a mapping sorted by the similarity containing the specified number of mappings 
	 * 			from a similarity to a word, Or null if the word doesn't exist in the corpus.  
	 * 
	 * @throws WordNotFoundException if the word is not in corpus.
	 * 
	 * ***/
	public List<Map.Entry<Double,String>> getSimilarWord(String word,int size) throws WordNotFoundException{
		Vector vect = this.vecDir.get(word);
		if(vect == null) throw new WordNotFoundException("Can not find the word '" + word + "' in corpus!");
		return getSimilarWord(vect, size);
	}
	
	/*** a factory method who allows us to get the corpus from 
	 * a file donated by the argument
	 * @param filePath The file path of corpus
	 * **/
	public static Corpus corpusFactory(String filePath){
		Corpus corpus = new Corpus();
		corpus.readFromFile(filePath);
		return corpus;
	}
	
	@Override
	public String toString() {
		return this.vecDir.toString();
	}
		
}
