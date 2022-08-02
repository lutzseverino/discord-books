package st.networkers.discordbooks.message;

import st.networkers.discordbooks.component.ActionableRow;

import java.util.List;

public interface Sendable {

    String getText();

    List<Embed> getEmbeds();

    List<ActionableRow> getActionableRows();

    Sendable setActionableRows(List<ActionableRow> actionableRows);

}
