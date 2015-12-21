package com.mygdx.gravitytoy.vc;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.gravitytoy.simulation.Particle;

public class ParticleInformation {
	
	private float mass ;
	private float radius ;
	private Vector2 position ;
	
	public ParticleInformation (Particle p) {
		this.mass = p.getMass() ;
		this.radius = (float) Math.log(mass);
		this.position = p.getPosition() ;
	}
	
	public ParticleInformation(float mass , Vector2 position) {
		this.mass = mass ;
		this.position = position ;
		this.radius = (float)Math.log(mass) ;
	}
	
	public float getMass() {
		return mass ;
	}
	
	public float getRadius() {
		return radius ;
	}
	
	public Vector2 getPosition() {
		return position ;
	}
	
}
