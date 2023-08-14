package com.ThePinkAlliance.core.limelight;

import java.util.ArrayList;
import java.util.List;

import com.ThePinkAlliance.core.limelight.CameraInterface.PipelineType;

/**
 * Best target data from the camera.
 */
public class CameraData {
    /**
     * Reprensentation for a target's data. For reflective targets the one largest
     * area
     * is first (which should be the closest in most cases), and for AprilTags the
     * closest
     * is first.
     */
    public static class TargetData {
        /**
         * The target ID. For AprilTags this is the fudicial ID.
         */
        public int id = -1;

        /**
         * The horizontal (left or right) angle to the target, 0 being in front,
         * positive
         * to the right and negative to the left.
         */
        public double targetXAngle = 0;

        /**
         * The vertical (up or down) angle to the target, 0 being in front of the midle
         * of the camera field of view, positive above and negative below.
         */
        public double targetYAngle = 0;

        /**
         * How parallel if the robot to the target, 0 being parallel, negative means the
         * robot
         * needs to turn clockwise to be parallel, and positive means the robot needs to
         * turn
         * counter closewise.
         */
        public double targetZAngle = 0;

        /**
         * Distance to the target.
         */
        public double targetDistance = 0;

        /**
         * The target type
         */
        public PipelineType targetType = null;
    }

    /**
     * Is the camera connected to the robot.
     */
    public boolean isConnected = false;

    /**
     * The pipeline index.
     */
    public PipelineType pipelineType = PipelineType.APRIL_TAG;

    /**
     * The latency reported by the camera on obtaining the target information.
     */
    public double latencyMillis = 0;

    /**
     * The target data.
     */
    private List<TargetData> targets = new ArrayList<TargetData>();

    /**
     * Add a new april tag target.
     * 
     * @param id       the fudicial ID
     * @param xAngle   the horizontal angle (0 is in front of the camera) to the
     *                 target
     * @param yAngle   the vertical angle (0 is in front of the camera) to the
     *                 target
     * @param distance the distance from the camera to the target
     */
    public void addAprilTagTarget(int id, double xAngle, double yAngle, double zAngle, double distance) {
        TargetData newTarget = new TargetData();
        newTarget.id = id;
        newTarget.targetXAngle = xAngle;
        newTarget.targetYAngle = yAngle;
        newTarget.targetZAngle = zAngle;
        newTarget.targetDistance = distance;
        newTarget.targetType = PipelineType.APRIL_TAG;
        targets.add(newTarget);
    }

    /**
     * Add a new april tag target.
     * 
     * @param id     the target ID
     * @param xAngle the horizontal angle (0 is in front of the camera) to the
     *               target
     * @param yAngle the vertical angle (0 is in front of the camera) to the target
     */
    public void addReflectiveTarget(int id, double xAngle, double yAngle, PipelineType pType) {
        TargetData newTarget = new TargetData();
        newTarget.id = id;
        newTarget.targetXAngle = xAngle;
        newTarget.targetYAngle = yAngle;
        newTarget.targetType = pType;
        targets.add(newTarget);
    }

    /**
     * Get the list of targets.
     * 
     * @return a list of targets or an empty list if no targets
     */
    public List<TargetData> getTargets() {
        return targets;
    }

    /**
     * Do we have targets?
     * 
     * @return true if we have targets
     */
    public boolean hasTargets() {
        return !targets.isEmpty();
    }
}
