package com.example.Integration2.invokers;

import com.example.Integration2.commands.Command;
import com.example.Integration2.commandsImplementations.fanCommands.DecreaseFanSpeedCommandImpl;
import com.example.Integration2.commandsImplementations.fanCommands.IncreaseFanSpeedCommandImpl;
import com.example.Integration2.commandsImplementations.fanCommands.TurnOffFanCommandImpl;
import com.example.Integration2.commandsImplementations.fanCommands.TurnOnFanCommandImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanRemote {

    Command command;
    @Autowired
    TurnOnFanCommandImpl turnOnFanCommand;
    @Autowired
    TurnOffFanCommandImpl turnOffFanCommand;
    @Autowired
    IncreaseFanSpeedCommandImpl increaseFanSpeedCommand;
    @Autowired
    DecreaseFanSpeedCommandImpl decreaseFanSpeedCommand;

    public String TurnOnFan() {
        command = turnOnFanCommand;
        return command.ExecuteCommand();
    }

    public String turnOffFan() {
        command = turnOffFanCommand;
        return command.ExecuteCommand();
    }

    public String increaseFanSpeed() {
        command = increaseFanSpeedCommand;
        return command.ExecuteCommand();
    }

    public String decreaseFanSpeed() {
        command = decreaseFanSpeedCommand;
        return command.ExecuteCommand();
    }
}
