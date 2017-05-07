import java.util.ArrayList;

/*** 
 * The vector Class represent a row vector and provides the fundamental vector operations.
 * @since 1.0
 */
public class Vector
{
	private final double[] vector;
	private final int dimension;
	
	/***
	 * Constructs and initializes a vector with a specified dimension.
	 * @param dimension the dimension of the vector
	 * */
	public Vector (int dimension) {
		this.dimension=dimension;
		this.vector = new double[dimension];
	}
	
	/***
	 * Returns the component at the specified position in this vector.
	 * @param position index of the component to return
	 * */
	public double getComponent(int position){
		if (position >= dimension || position < 0){
			throw new IndexOutOfBoundsException("The position " + position
					+ " is out of vector's size" + dimension + ".");
		}
		return this.vector[position];
	}
	
	/***
	 * Replaces the component at the specified position in this vector with the specified value.
	 * @param position position of the component to replace
	 * @param value value to be stored at the specified position
	 * */
	public void setComponent(int position,double value) {
		if (position >= this.dimension || position < 0){
			throw new IndexOutOfBoundsException("The position " + position
					+ " is out of vector's size" + dimension + ".");
		}
		this.vector[position] = value;
	}
	
    /** the sum of this vector and specified vector.
     * @param vec vector added.
     * @return the sum of this vector and specified vector 
     */
	public Vector addition ( Vector vec) {
    	Vector addVector = new Vector(dimension);
   
	    for(int i = 0; i < this.dimension; i++){
	    	addVector.setComponent(i, this.getComponent(i) + vec.getComponent(i));
	    }
	    return addVector;
    }
	/** The vector subtraction operation.
	 * @param subtrahend vector subtrahend.
	 * @return the difference of this vector and reference vector. 
	 */
	public Vector soustraction (Vector subtrahend) {
		Vector newVector = new Vector(dimension);
		
	    for(int i = 0; i < this.dimension; i++){
	    	newVector.setComponent(i, this.getComponent(i) - subtrahend.getComponent(i));
	    }
	    return newVector;
	}
	/** The scalar multiplication 
	 * @param scalar the scalar multiplying this vector
	 * @return a vector multiplied by specified scalar.
	 */
	public Vector multiplication (double scalar){
		Vector multiVector = new Vector(dimension);
		
		for(int i = 0; i < this.dimension; i++){
			multiVector.setComponent(i, this.getComponent(i)*scalar);
	    }
		return multiVector;
	}
	/** The scalar product. C = A.B
	 * @param vector B
	 * @return the result of product of two vectors. A.B
	 */
	public double product (Vector vec){
		double productVector = 0.0;
		for(int i = 0; i < this.dimension; i++){
			productVector = productVector + this.getComponent(i)*vec.getComponent(i);
	    }
		return productVector;
	}
	/** return a 2-norm of a vector.
	 * @return the norm of a vector.
	 */
	public double norm (){
		double sum = 0.0;
		for(int i = 0; i < this.dimension; i++){
			sum = sum + this.getComponent(i)* this.getComponent(i);
	    }
		return Math.sqrt(sum);
	}
	
	/** Returns the distance between this vector and reference vector
	 * @param vec the reference vector.
	 * @return the distance of two vectors.
	 */
	public double distance (Vector vec){
		return this.soustraction(vec).norm();
	}
	/** Returns the Cosine similarity.
	 * Cosine similarity is a measure of similarity between two non-zero vectors 
	 * of an inner product space that measures the cosine of the angle between them.
	 * @param vec another vector
	 * @return the similarity between two vectors.
	 */
	public double similarity (Vector vec) {
		return this.product(vec)/(this.norm()*vec.norm());
	}
}