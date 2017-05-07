
public class Main {
	
	public static void main(String[] args) {
		final int size;
		/***Initialization of the program*/
		
		// Initialization of corpus
		if(args.length == 0){
			System.out.println("You have to type the path of corpus as" 
							+ "a argument.");
			System.exit(0);
		}
		
		Corpus corpus = Corpus.corpusFactory(args[0]);
		
		if(args.length ==3 && args[1].equals("-k")){
			size = Integer.parseInt(args[2]);
		}else{
			size = 7;
		}
		
		//TODO
		/**
		 * ci chu shi cheng xu shuo ming
		 * 
		 * **/
		
		while(true){
	}
		
	}
}
