package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {

    public DcMotor mRoller;
    public DcMotor mStars;
    public DcMotor mBelts;
    public Servo mGate;

    public Intake(DcMotor mRoller, DcMotor mStars, DcMotor mBelts, Servo mGate){
        this.mRoller = mRoller;
        this.mStars = mStars;
        this.mBelts = mBelts;
        this.mGate = mGate;
    }

    public void forward(){
        mRoller.setPower(0.6);
        mStars.setPower(1);
        mBelts.setPower(0.6);
        mGate.setPosition(0.35);
    }

    public void reverse(){
        mRoller.setPower(-0.5);
        mStars.setPower(-0.5);
        mBelts.setPower(-0.5);
        mGate.setPosition(0.35);
    }

    public void stop(){
        mRoller.setPower(0);
        mStars.setPower(0);
        mBelts.setPower(0);
        mGate.setPosition(0.35);
    }

    public void shoot(){
        mRoller.setPower(0.35);
        mStars.setPower(0);
        mBelts.setPower(1);
        mGate.setPosition(0);
    }

    public void preload(){
        mRoller.setPower(-0.5);
        mStars.setPower(0);
        mBelts.setPower(-0.5);
        mGate.setPosition(0);
    }

}
