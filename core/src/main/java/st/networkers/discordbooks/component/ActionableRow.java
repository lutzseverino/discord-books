package st.networkers.discordbooks.component;

import java.util.List;

public class ActionableRow {
    private final List<? extends Actionable> actionables;

    public ActionableRow(List<? extends Actionable> actionables) {
        this.actionables = actionables;
    }

    public static ActionableRow of(Actionable... actionables) {
        return new ActionableRow(List.of(actionables));
    }

    public List<? extends Actionable> getActionables() {
        return actionables;
    }
}
