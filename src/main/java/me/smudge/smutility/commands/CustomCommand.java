/*
       _____           _    _ _   _ _     
      / ____|         | |  | | | (_) |    
     | (___  _ __ ___ | |  | | |_ _| |___ 
      \___ \| '_ ` _ \| |  | | __| | / __|
      ____) | | | | | | |__| | |_| | \__ \
     |_____/|_| |_| |_|\____/ \__|_|_|___/
     
     Author : Smudge
*/

package me.smudge.smutility.commands;

import me.smudge.smutility.UtilityPlayer;
import me.smudge.smutility.configuration.ConfigManager;
import me.smudge.smutility.configuration.ConfigurationSection;

import java.util.ArrayList;

/**
 * Represents a custom command
 * Making it easier to create a command from the config
 */
public abstract class CustomCommand extends Command {

    /**
     * The name of the command in the config
     */
    protected abstract String getConfigName();

    /**
     * If the command requires arguments
     */
    protected abstract boolean requiresArguments();

    @Override
    public String getName() {
        return ConfigManager.getCommands().getCommandInfo(this.getConfigName()).getName();
    }

    @Override
    public ArrayList<String> getAliases() {
        return ConfigManager.getCommands().getCommandInfo(this.getConfigName()).getAliases();
    }

    @Override
    public String getPermission() {
        return ConfigManager.getCommands().getCommandInfo(this.getConfigName()).getPermission();
    }

    @Override
    public abstract String getArgumentName();

    @Override
    public void onCommandRun(UtilityPlayer player, String arguments) {

        // If the command requires arguments and there are non
        if (this.requiresArguments() && arguments == null) {
            player.sendMessage(ConfigManager.getMessages().getMessages().getRequiresArguments());
            return;
        }

        // Run custom command
        this.onCommandRun(
                player, arguments,
                ConfigManager.getCommands().getCommandInfo(this.getConfigName()).getMessage()
        );
    }

    /**
     * When this command is run
     *
     * @param player    Player that ran the command
     * @param arguments Arguments
     * @param message   Config message
     */
    protected abstract void onCommandRun(UtilityPlayer player, String arguments, String message);

    /**
     * Used to get command section
     */
    protected ConfigurationSection getInfo() {
        return ConfigManager.getCommands().getCommandInfo(this.getConfigName()).getSection();
    }
}
