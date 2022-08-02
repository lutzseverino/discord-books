package com.lutzseverino.discordbooks.jda.discord.message;

import com.lutzseverino.discordbooks.discord.component.Actionable;
import com.lutzseverino.discordbooks.discord.component.Clickable;
import com.lutzseverino.discordbooks.discord.component.Selectable;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import com.lutzseverino.discordbooks.jda.discord.component.JDAButton;
import com.lutzseverino.discordbooks.jda.discord.component.JDAMenu;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.ItemComponent;

import java.util.ArrayList;
import java.util.List;

public class JDAMessage {
    private final Sendable sendable;

    public JDAMessage(Sendable sendable) {
        this.sendable = sendable;
    }

    public static Message buildMessage(Sendable sendable) {
        MessageBuilder builder = new MessageBuilder(sendable.getText());
        List<ActionRow> rows = new ArrayList<>();

        builder.setEmbeds(JDAEmbed.buildMessageEmbeds(sendable.getEmbeds()));

        sendable.getActionableRows().forEach(actionableRow -> {
            List<ItemComponent> components = new ArrayList<>();

            for (Actionable actionable : actionableRow.getActionables())
                switch (actionable.getType()) {
                    case CLICKABLE:
                        components.add(JDAButton.buildButton((Clickable) actionable));
                        break;
                    case SELECTABLE:
                        components.add(JDAMenu.buildSelectMenu((Selectable) actionable));
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown actionable type");
                }

            rows.add(ActionRow.of(components));
        });

        builder.setActionRows(rows);

        return builder.build();
    }

    public Message buildMessage() {
        return buildMessage(sendable);
    }
}
