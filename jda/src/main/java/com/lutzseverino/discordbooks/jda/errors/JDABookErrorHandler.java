package com.lutzseverino.discordbooks.jda.errors;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public interface JDABookErrorHandler {

    void whenBookIsNull(ButtonInteractionEvent event);

    void whenUserIsNotOwner(ButtonInteractionEvent event);
}
