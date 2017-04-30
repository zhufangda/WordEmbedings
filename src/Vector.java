import java.util.ArrayList;

public class Vector
{
	private final double[] vector;
	private final int size;
	
	public Vector (int size) {
		this.size=size;
		this.vector = new double[size];
	}
	
	public double getComponent(int position){
		if (position >= size || position < 0){
			throw new IndexOutOfBoundsException("The position " + position
					+ " is out of vector's size" + size + ".");
		}
		return this.vector[position];
	}
	
	public void setComponent(int position,double value) {
		if (position >= size || position < 0){
			throw new IndexOutOfBoundsException("The position " + position
					+ " is out of vector's size" + size + ".");
		}
		this.vector[position] = value;
	}
	
    /**
     * @param vec vector added.
     */
	public Vector addition ( Vector add) {
    	Vector addVector = new Vector(size);
   
	    for(int i = 0; i < this.size; i++){
	    	addVector.setComponent(i, this.getComponent(i) + add.getComponent(i));
	    }
	    return addVector;
    }
	/**
	 * @param subtrahend vector subtrahend.
	 */
	public Vector soustraction (Vector subtrahend) {
		Vector newVector = new Vector(size);
		
	    for(int i = 0; i < this.size; i++){
	    	newVector.setComponent(i, this.getComponent(i) - subtrahend.getComponent(i));
	    }
	    return newVector;
	}
	/**
	 * @param scalar
	 * @return the Vector multiplied by a scalar.
	 */
	public Vector multiplication (double scalar){
		Vector multiVector = new Vector(size);
		
		for(int i = 0; i < this.size; i++){
			multiVector.setComponent(i, this.getComponent(i)*scalar);
	    }
		return multiVector;
	}
	/**
	 * @param scalar
	 * @return the resualt of product of two vectors.
	 */
	public double product (Vector vec){
		double productVector = 0.0;
		for(int i = 0; i < this.size; i++){
			productVector = productVector + this.getComponent(i)*vec.getComponent(i);
	    }
		return productVector;
	}
	/**
	 * @return the norm of a vector.
	 */
	public double norm (){
		double sum = 0.0;
		for(int i = 0; i < this.size; i++){
			sum = sum + this.getComponent(i)* this.getComponent(i);
	    }
		return Math.sqrt(sum);
	}
	
	/**
	 * @param vec another vector.
	 * @return the distance of two vectors.
	 */
	public double distance (Vector vec){
		return this.soustraction(vec).norm();
	}
	/**
	 * @param vec another vector
	 * @return the similarity between two vectors.
	 */
	public double similarity (Vector vec) {
		return this.product(vec)/(this.norm()*vec.norm());
	}
}
