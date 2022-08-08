package com.lutzseverino.discordbooks.jda.errors;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class JDABookErrorHandlerImpl implements JDABookErrorHandler {
    public void whenBookIsNull(ButtonInteractionEvent event) {
        event.reply("This book may no longer exist.").setEphemeral(true).queue();
    }

    public void whenUserIsNotOwner(ButtonInteractionEvent event) {
        event.reply("You cannot navigate this book").setEphemeral(true).queue();
    }
}
