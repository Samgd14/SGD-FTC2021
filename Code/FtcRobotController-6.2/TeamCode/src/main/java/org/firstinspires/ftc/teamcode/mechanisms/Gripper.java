package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Gripper {

    public CRServo m1;
    public CRServo m2;
    public DcMotor m3;
    public double angle;

    public Gripper(CRServo m1, CRServo m2, DcMotor m3){
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;

    }

    public void forward(){
        m1.setPower(1);
        m2.setPower(1);
    }

    public void reverse(){
        m1.setPower(-1);
        m2.setPower(-1);
    }

    public void stop(){
        m1.setPower(0);
        m2.setPower(0);
    }

    public void goTo(double angle){
        //m3.setTargetPosition( (int) Math.round(angle*3500));
    }

}
