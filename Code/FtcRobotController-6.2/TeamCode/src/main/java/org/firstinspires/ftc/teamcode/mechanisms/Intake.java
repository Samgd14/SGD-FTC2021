package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Intake {

    public DcMotor mRoller;
    public DcMotor mStars;

    public Intake(DcMotor mRoller, DcMotor mStars){
        this.mRoller = mRoller;
        this.mStars = mStars;
    }

    public void forward(){
        mRoller.setPower(1);
        mStars.setPower(1);
    }

    public void reverse(){
        mRoller.setPower(-0.5);
        mStars.setPower(-0.5);
    }

    public void stop(){
        mRoller.setPower(0);
        mStars.setPower(0);
    }

    public void shoot(){
        mRoller.setPower(1);
        mStars.setPower(0);
    }

}
