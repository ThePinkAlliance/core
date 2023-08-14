package com.ThePinkAlliance.core.limelight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Allows for camera simulation via Network Tables (SmartDashboard) keys.
 * NOTE: I was not able to make a real simulated device. I could not find enough
 * information
 * online on how to do that.
 */
public class CameraInterfaceSim implements CameraInterface {
    // Dashboard key definitions.
    private static final String CAM_CONNECTED_KEY = "SimCam connected";
    private static final String CAM_HAS_TARGET_KEY = "SimCam has target";
    private static final String CAM_ID_KEY = "SimCam target id";
    private static final String CAM_LATENCY_KEY = "SimCam latency millis";
    private static final String CAM_XANGLE_KEY = "SimCam target X angle";
    private static final String CAM_YANGLE_KEY = "SimCam target Y angle";
    private static final String CAM_ZANGLE_KEY = "SimCam target Z angle";
    private static final String CAM_DISTANCE_KEY = "SimCam target distance (m)";
    private static final String CAM_IS_APRIL_TAG_KEY = "SimCam is AprilTag";

    public CameraInterfaceSim() {
        // Initialize the dashboard, so we can set the values.
        SmartDashboard.putBoolean(CAM_CONNECTED_KEY, false);
        SmartDashboard.putBoolean(CAM_HAS_TARGET_KEY, false);
        SmartDashboard.putBoolean(CAM_IS_APRIL_TAG_KEY, true);
        SmartDashboard.putNumber(CAM_ID_KEY, 5);
        SmartDashboard.putNumber(CAM_LATENCY_KEY, 40000);
        SmartDashboard.putNumber(CAM_XANGLE_KEY, 0);
        SmartDashboard.putNumber(CAM_YANGLE_KEY, 0);
        SmartDashboard.putNumber(CAM_ZANGLE_KEY, 0);
        SmartDashboard.putNumber(CAM_DISTANCE_KEY, 1.0);
    }

    @Override
    public CameraData getTarget() {
        CameraData retVal = new CameraData();
        retVal.isConnected = SmartDashboard.getBoolean(CAM_CONNECTED_KEY, false);
        boolean hasTarget = SmartDashboard.getBoolean(CAM_HAS_TARGET_KEY, false);
        retVal.latencyMillis = SmartDashboard.getNumber(CAM_LATENCY_KEY, 40000);
        if (SmartDashboard.getBoolean(CAM_IS_APRIL_TAG_KEY, false)) {
            retVal.pipelineType = PipelineType.APRIL_TAG;
            if (hasTarget) {
                retVal.addAprilTagTarget((int) SmartDashboard.getNumber(CAM_ID_KEY, 1),
                        SmartDashboard.getNumber(CAM_XANGLE_KEY, 0),
                        SmartDashboard.getNumber(CAM_YANGLE_KEY, 0),
                        SmartDashboard.getNumber(CAM_ZANGLE_KEY, 0),
                        SmartDashboard.getNumber(CAM_DISTANCE_KEY, 1.0));
            }
        } else {
            retVal.pipelineType = PipelineType.REFLECTIVE_HIGH;
            if (hasTarget) {
                retVal.addReflectiveTarget((int) SmartDashboard.getNumber(CAM_ID_KEY, 1),
                        SmartDashboard.getNumber(CAM_XANGLE_KEY, 0),
                        SmartDashboard.getNumber(CAM_YANGLE_KEY, 0), PipelineType.REFLECTIVE_HIGH);
            }
        }

        return retVal;
    }

    @Override
    public void close() {
        // Nothing to do.
    }

    @Override
    public PipelineType setPipeline(PipelineType type) {
        if (type == PipelineType.APRIL_TAG)
            SmartDashboard.putBoolean(CAM_IS_APRIL_TAG_KEY, true);
        else
            SmartDashboard.putBoolean(CAM_IS_APRIL_TAG_KEY, false);
        return type;
    }

    @Override
    public boolean isConnected() {
        return true;
    }

}
