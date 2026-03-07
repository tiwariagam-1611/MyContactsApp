package com.seveneleven.mycontactsapp.command;


import java.util.Stack;

public class CommandManager {
    private final Stack<Command> history = new Stack<>();

    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }
}

