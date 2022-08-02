package com.lutzseverino.discordbooks.discord.message;

import com.lutzseverino.discordbooks.discord.component.ActionableRow;

import java.util.List;

public interface Sendable {

    String getText();

    Sendable setText(String text);

    List<Embed> getEmbeds();

    Sendable setEmbeds(List<Embed> embeds);

    Sendable addEmbeds(Embed... embeds);

    List<ActionableRow> getActionableRows();

    Sendable setActionableRows(List<ActionableRow> actionableRows);

}
