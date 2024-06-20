package com.example.Integration2.receivers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Fan {
    boolean active;
    int speed;

    public String turnOnFan(){
        setActive(true);
        setSpeed(50);
        return "fan turned on. speed is 50 rpm";
    }

    public String turnOffFan(){
        setActive(false);
        setSpeed(0);
        return "fan turned off and speed is 0 rpm";
    }

    public String decreaseSpeed(){
        setSpeed(25);
        return "fan speed decreased by 25 rpm";
    }
    public String increaseSpeed(){
        setSpeed(100);
        return "fan speed increased to 100 rpm";
    }
}
