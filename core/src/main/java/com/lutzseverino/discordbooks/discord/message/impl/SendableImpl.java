package com.lutzseverino.discordbooks.discord.message.impl;

import com.lutzseverino.discordbooks.discord.component.ActionableRow;
import com.lutzseverino.discordbooks.discord.message.Embed;
import com.lutzseverino.discordbooks.discord.message.Sendable;

import java.util.ArrayList;
import java.util.List;

public class SendableImpl implements Sendable {
    private String text;
    private List<Embed> embeds = new ArrayList<>();
    private List<ActionableRow> actionableRows = new ArrayList<>();

    public SendableImpl() {
    }

    public SendableImpl(String text) {
        this.text = text;
    }

    public SendableImpl(Sendable sendable) {
        this.text = sendable.getText();
        this.embeds = sendable.getEmbeds();
        this.actionableRows = sendable.getActionableRows();
    }

    @Override public String getText() {
        return this.text;
    }

    @Override public Sendable setText(String text) {
        this.text = text;
        return this;
    }

    @Override public List<Embed> getEmbeds() {
        return this.embeds;
    }

    @Override public Sendable setEmbeds(List<Embed> embeds) {
        this.embeds = embeds;
        return this;
    }

    @Override public Sendable setEmbeds(Embed... embeds) {
        return setEmbeds(List.of(embeds));
    }

    @Override public Sendable addEmbeds(List<Embed> embeds) {
        this.embeds.addAll(embeds);
        return this;
    }

    @Override public Sendable addEmbeds(Embed... embeds) {
        return addEmbeds(List.of(embeds));
    }

    @Override public List<ActionableRow> getActionableRows() {
        return this.actionableRows;
    }

    @Override public Sendable setActionableRows(List<ActionableRow> actionableRows) {
        this.actionableRows = actionableRows;
        return this;
    }

    @Override public Sendable setActionableRows(ActionableRow... actionableRows) {
        return setActionableRows(List.of(actionableRows));
    }

    @Override public Sendable addActionableRows(List<ActionableRow> actionableRows) {
        this.actionableRows.addAll(actionableRows);
        return this;
    }

    @Override public Sendable addActionableRows(ActionableRow... actionableRows) {
        return addActionableRows(List.of(actionableRows));
    }
}
