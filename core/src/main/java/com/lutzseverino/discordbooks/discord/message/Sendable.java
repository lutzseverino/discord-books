package com.lutzseverino.discordbooks.discord.message;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.lutzseverino.discordbooks.discord.component.ActionableRow;
import com.lutzseverino.discordbooks.discord.message.impl.SendableImpl;

import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(@JsonSubTypes.Type(value = SendableImpl.class, name = "default"))
public interface Sendable {

    String getText();

    Sendable setText(String text);

    List<Embed> getEmbeds();

    Sendable setEmbeds(List<Embed> embeds);

    @JsonSetter Sendable setEmbeds(Embed... embeds);

    Sendable addEmbeds(List<Embed> embeds);

    Sendable addEmbeds(Embed... embeds);

    List<ActionableRow> getActionableRows();

    Sendable setActionableRows(List<ActionableRow> actionableRows);

    @JsonSetter Sendable setActionableRows(ActionableRow... actionableRows);

    Sendable addActionableRows(List<ActionableRow> actionableRows);

    Sendable addActionableRows(ActionableRow... actionableRows);

}
