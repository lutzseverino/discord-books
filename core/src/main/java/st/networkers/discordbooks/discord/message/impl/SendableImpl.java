package st.networkers.discordbooks.discord.message.impl;

import st.networkers.discordbooks.discord.component.ActionableRow;
import st.networkers.discordbooks.discord.message.Embed;
import st.networkers.discordbooks.discord.message.Sendable;

import java.util.ArrayList;
import java.util.List;

public class SendableImpl implements Sendable {
    private String text;
    private List<Embed> embeds = new ArrayList<>();
    private List<ActionableRow> actionableRows;

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

    @Override public Sendable addEmbeds(Embed... embeds) {
        this.embeds.addAll(List.of(embeds));
        return this;
    }

    @Override public List<ActionableRow> getActionableRows() {
        return this.actionableRows;
    }

    @Override public Sendable setActionableRows(List<ActionableRow> actionableRows) {
        this.actionableRows = actionableRows;
        return this;
    }
}
