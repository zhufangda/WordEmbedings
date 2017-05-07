/**
 * Signals that an attempt to find a word denoted by a specified string in corpus
 * has failed.
 *
 * <p> This exception will be thrown by the {@link Corpus} getVector method when a word
 * with the specified string does not exist in corpus. 
 * 
 * @author  WANG Huan
 * @since   1.0
 */

public class WordNotFoundException extends Exception {
	private static final long serialVersionUID = -3254603523063584284L;

	public WordNotFoundException() {
		super();
	}
	
	public WordNotFoundException(String msg) {
		super(msg);
	}

}
