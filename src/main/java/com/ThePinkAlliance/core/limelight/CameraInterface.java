package com.ThePinkAlliance.core.limelight;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Generic camera interface.
 */
public interface CameraInterface {
    static final int PIPELINE_APRILTAG = 0;
    static final int PIPELINE_REFLECTIVE_HIGH = 1;
    static final int PIPELINE_REFLECTIVE_LOW = 2;

    public enum PipelineType {
        APRIL_TAG,
        REFLECTIVE_HIGH,
        REFLECTIVE_LOW
    }

    /**
     * Check if the camera is connected.
     * 
     * @return true if the camera is connected, false otherwise.
     */
    public boolean isConnected();

    /**
     * Sets the pipeline type.
     * 
     * @param type the pipeline type
     * @return the pipeline type as reported by the camera once the switch occurs
     */
    public PipelineType setPipeline(PipelineType type);

    /**
     * Get closest target data.
     * 
     * @return closest target data
     */
    public CameraData getTarget();

    /**
     * Close the connection to the camera.
     */
    public void close();

    /**
     * Write the camera data to the dashboard.
     * 
     * @param data camera data
     */
    default void toDashboard(CameraData data) {
        SmartDashboard.putBoolean("Cam connected", data.isConnected);
        SmartDashboard.putBoolean("Cam Target detected", data.hasTargets());
        SmartDashboard.putNumber("Cam Latency", data.latencyMillis);
        SmartDashboard.putNumber("Cam Num Targets", data.getTargets().size());
        SmartDashboard.putString("Cam Pipeline", data.pipelineType.toString());
        if (data.hasTargets()) {
            SmartDashboard.putNumber("Cam Target ID", data.getTargets().get(0).id);
            SmartDashboard.putNumber("Cam Target Distance", data.getTargets().get(0).targetDistance);
            SmartDashboard.putNumber("Cam Target X Angle", data.getTargets().get(0).targetXAngle);
            SmartDashboard.putNumber("Cam Target Y Angle", data.getTargets().get(0).targetYAngle);
        }
    }

    /**
     * Converts a pipeline type an index number. Override if the camera is not set
     * like this.
     * 
     * @param type the pipeline type
     * @return the pipeline index
     */
    default int getPipelineIndex(PipelineType type) {
        int pipelineIndex = 0;
        switch (type) {
            case REFLECTIVE_HIGH:
                pipelineIndex = PIPELINE_REFLECTIVE_HIGH;
                break;

            case REFLECTIVE_LOW:
                pipelineIndex = PIPELINE_REFLECTIVE_LOW;
                break;

            case APRIL_TAG:
                pipelineIndex = PIPELINE_APRILTAG;
                break;

            default:
                throw new IllegalStateException("Unknown pipeline type" + type);
        }
        return pipelineIndex;

    }

    /**
     * Retrieves the pipeline type from the camera. Override if the camera is not
     * set like this.
     * 
     * @return the pipeline type.
     */
    default PipelineType getPipelineType(int pipelineIndex) {
        PipelineType pipelineType;
        switch (pipelineIndex) {
            case 0:
                pipelineType = PipelineType.APRIL_TAG;
                break;

            case 1:
                pipelineType = PipelineType.REFLECTIVE_HIGH;
                break;

            case 2:
                pipelineType = PipelineType.REFLECTIVE_LOW;
                break;

            default:
                throw new IllegalStateException("Unknown PhotonVision pipeline #" + pipelineIndex);
        }
        return pipelineType;
    }
}
