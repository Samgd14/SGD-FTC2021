/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanisms.DifferentialDrive;
import org.firstinspires.ftc.teamcode.mechanisms.Gripper;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Shooter;

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class Teleop extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private TouchSensor s_gripper;

    //Declare mechanisms
    private DifferentialDrive drivetrain;
    private Intake intake;
    private Shooter shooter;
    private Gripper gripper;

    // Variables setup
    double speedMultiplier = 0.6 ;
    
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Declare and initialize the hardware variables
        DcMotor m_frontLeft = hardwareMap.get(DcMotor.class, "m_frontLeft");
        DcMotor m_frontRight = hardwareMap.get(DcMotor.class, "m_frontRight");
        DcMotor m_shootLeft = hardwareMap.get(DcMotor.class, "m_shootLeft");
        DcMotor m_shootRight = hardwareMap.get(DcMotor.class, "m_shootRight");
        DcMotor m_intakeFloorRoller = hardwareMap.get(DcMotor.class, "m_intakeFloorRoller");
        DcMotor m_intakeStars = hardwareMap.get(DcMotor.class, "m_intakeStars");
        DcMotor m_gripperArm = hardwareMap.get(DcMotor.class, "m_gripperArm");

        CRServo m_gripper1 = hardwareMap.get(CRServo.class, "m_gripper1");
        CRServo m_gripper2 = hardwareMap.get(CRServo.class, "m_gripper2");

        s_gripper = hardwareMap.get(TouchSensor.class, "s_gripper");

        // Set motor directions
        m_frontLeft.setDirection(DcMotor.Direction.FORWARD);
        m_frontRight.setDirection(DcMotor.Direction.REVERSE);
        m_shootLeft.setDirection(DcMotor.Direction.FORWARD);
        m_shootRight.setDirection(DcMotor.Direction.REVERSE);
        m_intakeFloorRoller.setDirection(DcMotor.Direction.REVERSE);
        m_intakeStars.setDirection(DcMotor.Direction.FORWARD);
        m_gripperArm.setDirection(DcMotor.Direction.FORWARD);

        m_gripper1.setDirection(DcMotorSimple.Direction.FORWARD);
        m_gripper2.setDirection(DcMotorSimple.Direction.REVERSE);

        //Initialize mechanisms
        drivetrain = new DifferentialDrive(m_frontLeft, m_frontRight);
        intake = new Intake(m_intakeFloorRoller, m_intakeStars);
        shooter = new Shooter(m_shootLeft, m_shootRight);
        gripper = new Gripper(m_gripper1, m_gripper2, m_gripperArm);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {

        //Set the speed multiplier according to left and right bumpers
        if(gamepad1.left_bumper){ speedMultiplier = 1.0 ; }
        else if(gamepad1.right_bumper){ speedMultiplier = 0.6 ; }
        
        // Drive the robot
        double ty = gamepad1.left_trigger - gamepad1.right_trigger ;
        double rx = - gamepad1.left_stick_x ;
        drivetrain.drivePOV(ty, rx, speedMultiplier);

        // Update the intake
        if(gamepad2.a){ intake.forward(); }
        else if(gamepad2.b){ intake.stop(); }
        else if(gamepad2.x){ intake.shoot(); }
        else if(gamepad2.y){ intake.reverse(); }

        // Update the gripper
        if(gamepad2.dpad_down){gripper.forward();}
        else if(gamepad2.dpad_up){gripper.reverse();}
        else if(gamepad2.dpad_right){gripper.goTo(90);}
        else if(gamepad2.dpad_left){gripper.goTo(20);}

        if(s_gripper.isPressed()){gripper.stop();}

        //Update the shooter
        if(gamepad2.start){shooter.shoot();}
        else if (gamepad2.back){shooter.stop();}

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Drivetrain", "ty (%.2f), rx (%.2f)", ty, rx);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
