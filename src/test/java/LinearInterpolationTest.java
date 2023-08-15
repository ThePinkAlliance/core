import static org.junit.Assert.assertEquals;

import java.util.List;

import com.ThePinkAlliance.core.math.LinearInterpolationTable;
import edu.wpi.first.math.Pair;
import org.junit.Test;

public class LinearInterpolationTest {
  /**
   * NOTE: If the signs of the vectors in the list are opposites then the
   * interpolated result will be skewed.
   */
  List<Pair<Integer, Integer>> points = List.of(
      new Pair<Integer, Integer>(120, 125),
      new Pair<Integer, Integer>(110, 115),
      new Pair<Integer, Integer>(100, 105),
      new Pair<Integer, Integer>(130, 135),
      new Pair<Integer, Integer>(140, 145),
      new Pair<Integer, Integer>(150, 155));
  List<Pair<Integer, Integer>> negativePoints = List.of(
      new Pair<Integer, Integer>(-40, -145),
      new Pair<Integer, Integer>(-30, -135),
      new Pair<Integer, Integer>(-20,
          -125),
      new Pair<Integer, Integer>(-10, -115),
      new Pair<Integer, Integer>(0, 105),
      new Pair<Integer, Integer>(30, 135),
      new Pair<Integer, Integer>(40, 145),
      new Pair<Integer, Integer>(50, 155));
  List<Pair<Integer, Integer>> emptyPoints = List.of(
      new Pair<Integer, Integer>(0, 0),
      new Pair<Integer, Integer>(0, 0));

  LinearInterpolationTable table = new LinearInterpolationTable(points);
  LinearInterpolationTable emptyTable = new LinearInterpolationTable(emptyPoints);
  LinearInterpolationTable negativeTable = new LinearInterpolationTable(negativePoints);

  @Test
  public void CheckTableOutput() {
    assertEquals(128.0, table.interp(123), 0);
  }

  @Test
  public void NegativeTableInput() {
    assertEquals(-140, negativeTable.interp(-35), 0);
  }

  @Test
  public void MaxTableInput() {
    assertEquals(Double.NaN, table.interp(194), 0);
  }

  @Test
  public void CheckEmptyTable() {
    assertEquals(Double.NaN, emptyTable.interp(20), 0);
  }
}
