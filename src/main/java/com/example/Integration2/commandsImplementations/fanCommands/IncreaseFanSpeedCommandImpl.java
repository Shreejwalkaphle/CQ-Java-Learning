package com.example.Integration2.commandsImplementations.fanCommands;

import com.example.Integration2.commands.Command;
import com.example.Integration2.receivers.Fan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IncreaseFanSpeedCommandImpl  implements Command {
    @Autowired
    Fan fan;

    @Override
    public String ExecuteCommand() {
        return fan.increaseSpeed();
    }
}