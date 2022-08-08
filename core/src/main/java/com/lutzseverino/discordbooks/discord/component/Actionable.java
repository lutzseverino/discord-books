package com.lutzseverino.discordbooks.discord.component;

import com.fasterxml.jackson.annotation.*;
import com.lutzseverino.discordbooks.discord.component.impl.ClickableImpl;
import com.lutzseverino.discordbooks.discord.component.impl.SelectableImpl;
import org.jetbrains.annotations.Nullable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ClickableImpl.class, name = "clickable"),
        @JsonSubTypes.Type(value = SelectableImpl.class, name = "selectable")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Actionable.class)
public interface Actionable {

    @Nullable String getId();

    Actionable setId(String id);

    boolean isDisabled();

    Actionable setDisabled(boolean disabled);

    @JsonIgnore default Type getType() {
        return Type.UNKNOWN;
    }

    enum Type {
        CLICKABLE,
        SELECTABLE,
        UNKNOWN,
    }

}
