
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import com.ThePinkAlliance.core.math.SphericalCoordinates;

public class SphericalCoordinatesTest {
  @Test
  public void sphericalToCartesian() {
    SphericalCoordinates coordinates = new SphericalCoordinates(7.34, -2.67, 2.83);
    Translation3d translation3d = coordinates.toCartesian();

    assertEquals(-2.004635177, translation3d.getX(), 0.05);
    assertEquals(-1.022306058, translation3d.getY(), 0.05);
    assertEquals(-6.98655338, translation3d.getZ(), 0.05);
  }

  @Test
  public void cartesianToSpherical() {
    Translation3d translation3d = new Translation3d(-2, -1, -7);
    SphericalCoordinates sphericalCoordinates = SphericalCoordinates.fromCartesian(translation3d);

    assertEquals(7.3484, sphericalCoordinates.getR(), 0.05);
    assertEquals(-2.6779, sphericalCoordinates.getTheta(), 0.05);
    assertEquals(2.8323, sphericalCoordinates.getPhi(), 0.05);
  }

  @Test
  public void sphericalDifference() {
    SphericalCoordinates desiredCoordinates = new SphericalCoordinates(1, Units.degreesToRadians(90),
        Units.degreesToRadians(180));
    SphericalCoordinates currentCoordinates = new SphericalCoordinates(0.5, Units.degreesToRadians(90),
        Units.degreesToRadians(90));
    SphericalCoordinates coordinateDifference = desiredCoordinates.subtract(currentCoordinates);

    assertEquals(0.5, coordinateDifference.getR(), 0.05);
    assertEquals(0, coordinateDifference.getTheta(), 0.05);
    assertEquals(1.57, coordinateDifference.getPhi(), 0.05);
  }
}
