package com.chrisplus.maple.simulation.utils;

import java.util.Random;

public class Utils {

  private Utils(){

  }

  private final static Random random = new Random();

  public static int nextRandomInt(int start, int end){
    return random.nextInt(end-start+1) + start;
  }

  public static int nextRandomInt(Random random, int start, int end){
    return random.nextInt(end-start+1) + start;
  }
}
