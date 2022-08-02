package com.lutzseverino.discordbooks.jda.errors;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class JDABookErrorHandlerImpl implements JDABookErrorHandler {
    public void whenBookIsNull(ButtonInteractionEvent event) {
        event.reply("Book not found, it may no longer exist in the codebase.").queue();
    }

    public void whenUserIsNotOwner(ButtonInteractionEvent event) {
        event.reply("You are not the author of this book.").queue();
    }
}
