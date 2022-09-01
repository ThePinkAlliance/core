
import com.ThePinkAlliance.core.shooter.TargetPackageConstraints;
import com.ThePinkAlliance.core.shooter.TargetPackageHelper;
import com.ThePinkAlliance.core.util.Gains;
import org.junit.*;
import static org.junit.Assert.*;

public class ShooterTest {
  TargetPackageConstraints constraints;
  Gains gains;

  double rpmSlope = 7.5;
  double rpmIntercept = 2118;
  double hoodSlope = -244.8;
  double hoodIntercept = 37634;
  double hoodMin = -200;
  double hoodMax = -78000;
  double feedforwardMagic = 70000;

  @Before
  public void setup() {
    gains = new Gains(0, 0, 0);
    constraints = new TargetPackageConstraints(gains, rpmSlope, rpmIntercept, hoodSlope, hoodIntercept, hoodMin,
        hoodMax, feedforwardMagic);
  }

  @Test
  public void testTargetDistance() {
    var target = new TargetPackageHelper().calculatePackageFromDistance(constraints, 100);
    assertEquals(2868.0, target.getRpm(), 100);
  }
}
