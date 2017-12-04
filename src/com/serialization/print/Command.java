package com.serialization.print;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Command implements Externalizable{
    private String sender;
    private boolean listOfUsers;
    private boolean ping;
    private boolean serverTime;

    public Command() {}

    public Command(CommandBuilder builder) {
        this.sender = builder.sender;
        this.listOfUsers = builder.listOfUsers;
        this.ping = builder.ping;
        this.serverTime = builder.serverTime;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeBoolean(listOfUsers);
        out.writeBoolean(ping);
        out.writeBoolean(serverTime);
        out.writeUTF(sender);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        listOfUsers = in.readBoolean();
        ping = in.readBoolean();
        serverTime = in.readBoolean();
        sender = in.readUTF();
    }

    public String getSender() {
        return sender;
    }

    public boolean isListOfUsers() {
        return listOfUsers;
    }

    public boolean isPing() {
        return ping;
    }

    public boolean isServerTime() {
        return serverTime;
    }

    public static class CommandBuilder {
        private String sender;
        private boolean listOfUsers;
        private boolean ping;
        private boolean serverTime;

        public CommandBuilder(String sender) {
            this.sender = sender;
            this.listOfUsers = false;
            this.ping = false;
            this.serverTime = false;
        }

        public CommandBuilder listOfUsers(boolean value) {
            this.listOfUsers = value;
            return this;
        }

        public CommandBuilder ping(boolean value) {
            this.ping = value;
            return this;
        }

        public CommandBuilder serverTime(boolean value) {
            this.serverTime = value;
            return this;
        }

        public Command bild() {
            return new Command(this);
        }
    }
}
