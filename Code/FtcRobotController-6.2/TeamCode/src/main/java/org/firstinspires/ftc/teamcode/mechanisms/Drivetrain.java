package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class Drivetrain {

    public DcMotor m1;
    public DcMotor m2;

    public Drivetrain(DcMotor m1, DcMotor m2){
        this.m1 = m1;
        this.m2 = m2;
    }

    public void drivePOV(double ty, double rx, double mult){

        double m1Power = Range.clip((ty + rx)*mult, -1.0, 1.0) ;
        double m2Power = Range.clip((ty - rx)*mult, -1.0, 1.0) ;

        m1.setPower(m1Power);
        m2.setPower(m2Power);
    }

}
