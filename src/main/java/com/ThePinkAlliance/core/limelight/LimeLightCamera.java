package com.ThePinkAlliance.core.limelight;

import com.ThePinkAlliance.core.logging.Telemetry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class LimeLightCamera implements CameraInterface {

    private static String cameraName = "limelight";

    private NetworkTable getCamera() {
        return NetworkTableInstance.getDefault().getTable(cameraName);
    }

    @Override
    public boolean isConnected() {
        boolean isConnected = false;
        if (getCamera().containsKey("getpipe"))
            isConnected = true;
        return isConnected;
    }

    @Override
    public void close() {
        // Nothing to do.
    }

    /**
     * Gets the pipeline index from the PhotonVision camera.
     * 
     * @return the pipeline index
     */
    private int getPipelineIndex() {
        NetworkTable cam = getCamera();

        if (isConnected()) {
            return (int) (cam.getEntry("getpipe").getDouble(0));
        } else
            throw new IllegalStateException("Limelight camera not connected");
    }

    @Override
    public PipelineType setPipeline(PipelineType type) {
        if (isConnected()) {
            int pipelineIndex = getPipelineIndex(type);
            getCamera().putValue("pipeline", NetworkTableValue.makeDouble(pipelineIndex));
        }

        return getPipelineType(getPipelineIndex());
    }

    @Override
    public CameraData getTarget() {
        CameraData camTargets = new CameraData();
        camTargets.isConnected = isConnected();
        if (camTargets.isConnected) {
            NetworkTable camera = getCamera();
            camTargets.pipelineType = getPipelineType(getPipelineIndex());
            camTargets.latencyMillis = camera.getEntry("tl").getDouble(0);
            // This check is not reliable, but we use it anyways.
            boolean hasTargets = camera.getEntry("tv").getDouble(0) == 1;
            if (hasTargets) {
                getAprilTagTargets(camera, camTargets);
            }
        }
        toDashboard(camTargets);
        return camTargets;
    }

    /**
     * Get reflective target data.
     * 
     * @param camera     the camera reference
     * @param camTargets
     * @return a list of 0 or more reflective targets from largest area to smallest.
     */
    private void getReflectiveTarget(NetworkTable camera, CameraData camTargets, PipelineType pType) {
        // TODO: How do we get multiple targets?
        camTargets.addReflectiveTarget(0, camera.getEntry("tx").getDouble(0),
                camera.getEntry("ty").getDouble(0), pType);
    }

    /**
     * Get april tag target data.
     * 
     * @param camera     the camera reference
     * @param camTargets
     * @return a list of 0 or more april tag targets from closest to farthest.
     */
    private void getAprilTagTargets(NetworkTable camera, CameraData camTargets) {
        // TODO: How do we get multiple targets?
        // double[] camPose =
        // camera.getEntry("targetpose_cameraspace").getDoubleArray(new double[0]);
        double[] camPose = camera.getEntry("targetpose_cameraspace").getDoubleArray(new double[0]);
        String aa = "";
        for (int i = 0; i < camPose.length; i++)
            aa += camPose[i] + ",";
        Telemetry.logData("targetpose_cameraspace", aa, getClass());
        // Don't trust the flag that tells you there is a target
        if (camPose.length > 5) {
            double targetDistance = Math.abs(camPose[2]);
            double zAngle = camPose[4];
            double computedZAngle = zAngle; // Same as the reported angle.
            camTargets.addAprilTagTarget(0, camera.getEntry("tx").getDouble(0),
                    camera.getEntry("ty").getDouble(0), computedZAngle, targetDistance);
        }
    }
}
