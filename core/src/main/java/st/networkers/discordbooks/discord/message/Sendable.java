package st.networkers.discordbooks.discord.message;

import st.networkers.discordbooks.discord.component.ActionableRow;

import java.util.List;

public interface Sendable {

    String getText();

    List<Embed> getEmbeds();

    List<ActionableRow> getActionableRows();

    Sendable setActionableRows(List<ActionableRow> actionableRows);

}
