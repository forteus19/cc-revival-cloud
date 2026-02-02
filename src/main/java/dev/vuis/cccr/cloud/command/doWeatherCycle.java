package dev.vuis.cccr.cloud.command;

//please fix this as you see fit vuis, I pushed this to the github so you can change it and throw it into the CC.jar easily.

import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.storage.WorldInfo;

public class doWeatherCycle extends CommandBase {

    @Override
    public String getCommandName() {
        return "doWeatherCycle";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/doWeatherCycle <true/false>";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            WorldInfo worldInfo = MinecraftServer.getServer().worldServers[0].getWorldInfo();

            if (args[0].equalsIgnoreCase("false")) {
                worldInfo.setRainTime(600000000);
                worldInfo.setThunderTime(600000000);
                worldInfo.setRaining(false);
                worldInfo.setThundering(false);
                notifyAdmins(sender, "WeatherCycle set too false", new Object[0]);
            }
            else if (args[0].equalsIgnoreCase("true")){
                worldInfo.setRainTime(6000);
                worldInfo.setThunderTime(6000);

            }
        }
        }
    }

    @Override
    public List getCommandAliases() {
        return Arrays.asList("sw", "doWeatherCycle");
    }