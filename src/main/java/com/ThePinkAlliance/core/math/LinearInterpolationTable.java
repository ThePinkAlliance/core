package com.ThePinkAlliance.core.math;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import edu.wpi.first.math.Pair;

/**
 * The linear interpolation takes a table with two columns and will find two
 * values in the first column, one that's smaller then the input and one
 * that's
 * greater then the input, then it will find there corresponding values in the
 * second
 * column and interpolate between those two based off of the original input.
 * 
 * Learn more about linear interpolation.
 * <br>
 * </br>
 * <a href="https://en.wikipedia.org/wiki/Linear_interpolation">Guide from
 * wikipedia</a>
 * <br>
 * </br>
 * <a href=
 * "https://matthew-brett.github.io/teaching/linear_interpolation.html">Guide
 * from matthew brett</a>
 * 
 * @note The search algorithm in this utility is not optimized, average call
 *       time 8ms.
 */
public class LinearInterpolationTable {
  ArrayList<Pair<Integer, Integer>> points;
  double lastResult;

  public LinearInterpolationTable(List<Pair<Integer, Integer>> points) {
    ArrayList<Pair<Integer, Integer>> mutList = new ArrayList<>(points);

    mutList.sort((a, b) -> b.getFirst() > a.getFirst() ? 1 : -1);

    this.points = mutList;
  }

  /**
   * Converts a Vector2d stream into a Vector2d list.
   */
  public List<Pair<Integer, Integer>> streamListVector(Stream<Pair<Integer, Integer>> stream) {
    Iterator<Pair<Integer, Integer>> iterator = stream.iterator();
    ArrayList<Pair<Integer, Integer>> list = new ArrayList<>();

    iterator.forEachRemaining((e) -> list.add(e));

    return list;
  }

  /**
   * This will interpolate the value for the y column using input e.
   * 
   * @param e The value to interpolate between
   */
  public double interp(double e) {
    /*
     * NOTE:
     * Now after filtering the points list we will have two lists with vectors that
     * are bigger and smaller then our input, now normally after this we would sort
     * each list from greatest to smallest and retrieve the greatest vector of both
     * the newly sorted lists however whether we need to do this depends on our
     * table makeup. For example if we have a table with all positive values then we
     * don't need to worry about sorting however if we have a table with negative
     * values then sorting might become necessary.
     */
    ArrayList<Pair<Integer, Integer>> greaterPoints = new ArrayList<>(
        streamListVector(
            points.stream()
                .filter(v -> Math.abs(v.getFirst()) > Math.abs(e) && Math.signum(e) == Math.signum(v.getFirst()))));
    ArrayList<Pair<Integer, Integer>> smallerPoints = new ArrayList<>(
        streamListVector(
            points.stream()
                .filter(v -> Math.abs(v.getFirst()) < Math.abs(e) && Math.signum(e) == Math.signum(v.getFirst()))));

    if (greaterPoints.isEmpty() || smallerPoints.isEmpty()) {
      return Double.NaN;
    }

    Pair<Integer, Integer> vec1 = smallerPoints.get(0);
    Pair<Integer, Integer> vec2 = greaterPoints.get(0);

    /*
     * If vector two is undefined and vector one is defined then that means the
     * input is bigger then whats in our table, so to make sure the output won't
     * become zero in this situation we will assign the second vector to the biggest
     * vector in our table.
     */
    if (vec2 == null && vec1 != null) {
      vec2 = points.get(points.size() - 1);
    }

    if (vec1 == null || vec2 == null) {
      return 0;
    }

    return vec1.getSecond()
        + (e - vec1.getFirst()) * ((vec2.getSecond() - vec1.getSecond()) / (vec2.getFirst() - vec1.getFirst()));
  }
}
