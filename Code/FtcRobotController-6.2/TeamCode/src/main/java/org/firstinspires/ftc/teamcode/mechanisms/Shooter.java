package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

public class Shooter {

    public DcMotor m1;
    public DcMotor m2;

    public Shooter(DcMotor m1, DcMotor m2){
        this.m1 = m1;
        this.m2 = m2;
    }

    public void shoot(){
        m1.setPower(1);
        m2.setPower(1);
    }

    public void stop(){
        m1.setPower(0);
        m2.setPower(0);
    }

}
