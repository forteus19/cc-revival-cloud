package dev.vuis.cccr.cloud.packets.command;

public interface ICommandSender {
    String getSender();

    void sendChat(String var1);
}
