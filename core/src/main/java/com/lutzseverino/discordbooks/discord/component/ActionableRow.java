package com.lutzseverino.discordbooks.discord.component;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ActionableRow {
    private final List<Actionable> actionables = new ArrayList<>();

    public ActionableRow(@JsonProperty("actionables") List<? extends Actionable> actionables) {
        this.actionables.addAll(actionables);
    }

    public static ActionableRow of(Actionable... actionables) {
        return of(List.of(actionables));
    }

    public static ActionableRow of(List<? extends Actionable> actionables) {
        return new ActionableRow(actionables);
    }

    public List<? extends Actionable> getActionables() {
        return actionables;
    }
}
