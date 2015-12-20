package com.mygdx.gravitytoy.simulation;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

public class Universe {
	
	private static Universe INSTANCE = new Universe() ;
	private Set<Particle> particles ;
	
	private Universe() {
		this.particles = new HashSet<Particle>() ;
	}
	
	public Universe getInstance() {
		return INSTANCE ;
	}
	
	public void addParticle(Particle p) {
		this.particles.add(p) ;
	}
	
	public void removeParticle(Particle p) {
		this.particles.remove(p);
	}
	
	protected void integrateForces() {
		for (Particle p : particles) {
			for (Particle s : particles) {
				if (! p.equals(s)) {
					p.interact(s);
				}
			}
		}
	}
	
	protected void applyForces(float delta) {
		for (Particle p : particles) {
			p.update(delta);
		}
	}
	/**
	 * This method evaluates all collisions among particles and builds a new
	 * particles set with every particle Cluster replaced with an equivalent
	 * mass
	 * TODO: Verify efficiency
	 */
	protected void applyCollisions(){
		Map <Particle,Set<Particle>> collisions = new HashMap<Particle, Set<Particle>>() ;
		for (Particle p : particles) {
			Circle p_c = new Circle(p.getPosition(), (float) Math.log(p.getMass())) ;
			collisions.put(p, new HashSet<Particle>()) ;
			for (Particle s : particles) {
				if (! p.equals(s)) {
					Circle s_c = new Circle(s.getPosition(), (float)Math.log(s.getMass())) ;
					if (Intersector.overlaps(p_c, s_c)) {
						collisions.get(p).add(s) ;
					}
				}
			}
		}
		HashSet<Particle> nparticles = new HashSet<Particle>() ;
		for (Particle p : collisions.keySet()) {
			if (collisions.containsKey(p)) {
				if (collisions.get(p).isEmpty()) {
					nparticles.add(p) ;
				} else {
					Cluster c = new Cluster(collisions.get(p)) ;
					for (Map.Entry<Particle, Set<Particle>> e : collisions.entrySet() ) {
						c.add(e.getValue());
					}
					c.removeFromMap(collisions);
					nparticles.add(c.equivalentParticle()) ;
				}
			}
		}
		this.particles = nparticles ;
	}
	/**
	 * This class adds the concept of a Cluster, i.e. a set
	 * of particles that form a continuous structure
	 * @author saques
	 */
	private class Cluster {
		private Set<Particle> cluster ; 
		
		public Cluster (Set<Particle> initial) {
			cluster = new HashSet<Particle>(initial) ;
		}
		/**
		 * Adds other's particles to this cluster if and
		 * only if other and cluster are not disjoint
		 * @param other The other set of particles
		 */
		public void add(Set<Particle> other){
			if (!Collections.disjoint(this.cluster,other)){
				cluster.addAll(other) ;
			}
		}
		public void removeFromMap(Map<Particle,Set<Particle>> map) {
			for (Particle p : cluster) {
				map.remove(p) ;
			}
		}
		/**
		 * This method returns a new Particle whose mass is
		 * the sum of all masses in this cluster
		 * @return
		 */
		public Particle equivalentParticle() {
			float totalMass = 0 ;
			float maxMass = 0 ;
			Vector2 position = null;
			for (Particle p: cluster) {
				if (p.getMass() > maxMass) {
					maxMass = p.getMass() ;
					position = p.getPosition() ;
				}
				totalMass += p.getMass() ;
			}
			return new Particle(position, totalMass) ;
		}
		
	}
}
