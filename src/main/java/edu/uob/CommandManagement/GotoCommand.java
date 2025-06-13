package edu.uob.CommandManagement;

import edu.uob.EntityManagement.*;

public class GotoCommand extends GameCommand {
    @Override
    public String executeCommand() {
        if (this.trimmedCommand == null) return "Your command was invalid.";

        if (!this.trimmedCommand.hasCommandType()) {
            return "Invalid goto command.";
        }

        if (this.trimmedCommand.getEntities().size() != 1) {
            return "goto only works with exactly one location.";
        }

        PlayerEntity playerEntity = getPlayer();
        LocationEntity playerLocation = playerEntity.getPlayerLocation();

        for (String locationName : this.trimmedCommand.getEntities()) {
            GamePath gamePath = playerLocation.getPath(locationName);

            if (gamePath == null) {
                StringBuilder responseBuilder = new StringBuilder();
                responseBuilder.append("You can't go to ").append(locationName);
                return responseBuilder.toString();
            }
            LocationEntity destination = gamePath.pathTo();
            playerEntity.setPlayerLocation(destination);

            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("You have gone to ").append(destination.getEntityName());
            responseBuilder.append(": ").append(destination.getEntityDescription());
            return responseBuilder.toString();
        }

        return "You can't go there.";
    }
}

