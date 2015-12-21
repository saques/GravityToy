package com.mygdx.gravitytoy.simulation;

import com.badlogic.gdx.math.Vector2;
/**
 * This class simulates a Particle, i.e.
 * every single dot that appears on the Universe
 * @author saques
 *
 */
public class Particle {
	
	private static int IDs = 0 ;
	private int id ;
	private float mass ;
	private Vector2 position ;
	private Vector2 velocity ;
	private Vector2 forces ;
	
	/**
	 * Creates a Particle with given position, mass and
	 * velocity vector
	 * @param position
	 * @param mass
	 * @param velocity
	 */
	public Particle(Vector2 position, float mass, Vector2 velocity){
		this.mass = mass ;
		this.position = position ;
		this.velocity = velocity ;
		this.forces = new Vector2() ;
		this.id = IDs ;
		IDs ++ ;
	}
	/**
	 * Creates a Particle with given initial position and mass,
	 * with velocity vector set as zero
	 * @param position The position of the cursor when clicked
	 * @param mass The mass of the particle as setted by the user
	 */
	public Particle(Vector2 position, float mass) {
		this(position,mass,new Vector2()) ;
	}
	
	/**
	 * This method updates the internal status of a particle,
	 * using the forces applied on it to change the velocity
	 * vector and its position
	 * @param delta The time since the last update
	 */
	public void update(float delta) {
		Vector2 acceleration = new Vector2(forces).scl(1/mass) ;
		Vector2 deltaV = new Vector2(acceleration).scl(delta);
		velocity.set(velocity.x+deltaV.x,velocity.y+deltaV.y) ;
		position.set(position.x+velocity.x*delta,position.y+velocity.y*delta) ;
		this.forces = new Vector2() ;
	}
	/**
	 * This method recieves other particle and adds to the forces of
	 * this particle considering the relative distance vector between
	 * both particles and their masses
	 * @param o
	 */
	public float interact (Particle o) {
		float distance = o.getPosition().dst(this.position) ;
		Vector2 relative = new Vector2(o.getPosition()).sub(this.position).nor() ;
		float force = (float)((this.getMass()*o.getMass())/Math.pow(distance, 2)) ;
		forces.add(relative.scl(force)) ;
		return force ;
	}
	
	
	public float getMass() {
		return mass ;
	}
	
	public Vector2 getVelocity() {
		return velocity ;
	}
	
	public Vector2 getPosition() {
		return position ;
	}
	
	public int hashCode() {
		return id ;
	}
	
	public int getID() {
		return id ;
	}
	
	public boolean equals (Object o) {
		return (o instanceof Particle) && (((Particle)o).getID() == this.id)  ;
	}
	
}
