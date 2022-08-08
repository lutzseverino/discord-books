package com.lutzseverino.discordbooks.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.lutzseverino.discordbooks.discord.component.ActionableRow;
import com.lutzseverino.discordbooks.discord.component.Clickable;
import com.lutzseverino.discordbooks.discord.component.impl.ClickableImpl;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@SuppressWarnings("UnusedReturnValue")
public class Book {
    private final List<Sendable> pages = new ArrayList<>();
    private final List<String> owners = new ArrayList<>();
    private Clickable previousClickable = new ClickableImpl(Clickable.Style.PRIMARY).setDisplay("Previous");
    private Clickable nextClickable = new ClickableImpl(Clickable.Style.PRIMARY).setDisplay("Next");
    private final List<Clickable> clickables = new ArrayList<>(List.of(previousClickable, nextClickable));
    private final List<ActionableRow> actionableRows = new ArrayList<>(List.of(ActionableRow.of(clickables)));
    private String id;

    public Book(String id) {
        this.id = id;
    }

    public Book() {
        this.id = "";
    }

    /**
     * @return the identifier of the book
     */
    public String getId() {
        return id;
    }


    /**
     * @param id the identifier of the book
     * @return this book, for chained calls
     */
    public Book setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Calls to set up navigation and returns
     * a given page.
     *
     * @param index the index
     * @return the page at the index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @NotNull public Sendable getPage(int index) {
        setNavigation(index);
        return pages.get(index);
    }

    /**
     * @return the list of pages
     */
    public List<Sendable> getPages() {
        return pages;
    }

    /**
     * @param pages the pages to add
     * @return this book, for chained calls
     */
    public Book addPages(Sendable @NotNull ... pages) {
        this.pages.addAll(List.of(pages));
        return this;
    }

    /**
     * Gets the list of {@link Clickable}s.
     *
     * @return a list of action rows
     * @see #getClickables()
     * @see #getClickableRow()
     */
    public List<Clickable> getClickables() {
        return clickables;
    }

    /**
     * Clears the default list and sets the items of
     * the {@link ActionableRow} of {@link Clickable}s.
     *
     * @param clickables the {@link Clickable}s to set
     * @return this book, for chained calls
     * @see #addClickables(Clickable...)
     * @see #setNextClickable(UnaryOperator)
     * @see #setPreviousClickable(UnaryOperator)
     */
    public Book addClickables(List<Clickable> clickables) {
        this.clickables.clear();
        this.clickables.addAll(clickables);
        return this;
    }

    /**
     * Clears the default list and sets the items of
     * the {@link ActionableRow} of {@link Clickable}s.
     *
     * @param clickables the {@link Clickable}s to set
     * @return this book, for chained calls
     * @see #addClickables(Clickable...)
     * @see #setNextClickable(UnaryOperator)
     * @see #setPreviousClickable(UnaryOperator)
     */
    public Book addClickables(Clickable... clickables) {
        return addClickables(List.of(clickables));
    }

    /**
     * @return the {@link Clickable} used to navigate to the next page.
     */
    public Clickable getNextClickable() {
        return nextClickable;
    }

    /**
     * Allows you to set the base {@link Clickable} used to navigate to
     * the next page.
     * <p>
     * Some settings, such as the disabled state or identifier will be overridden
     * at send time.
     *
     * @param nextClickable the operator to modify the {@link Clickable}
     * @return this book, for chained calls
     */
    public Book setNextClickable(@NotNull UnaryOperator<Clickable> nextClickable) {
        nextClickable.apply(this.nextClickable);
        return this;
    }

    /**
     * Allows you to set the base {@link Clickable} used to navigate to
     * the next page.
     * <p>
     * Some settings, such as the disabled state or identifier will be overridden
     * at send time.
     *
     * @param nextClickable the {@link Clickable}
     * @return this book, for chained calls
     */
    @JsonSetter private Book setNextClickable(Clickable nextClickable) {
        this.nextClickable = nextClickable;
        return this;
    }

    /**
     * @return the {@link Clickable} used to navigate to the previous page.
     */
    public Clickable getPreviousClickable() {
        return previousClickable;
    }

    /**
     * Allows you to set the base {@link Clickable} used to navigate to
     * the previous page.
     * <p>
     * Some settings, such as the disabled state or identifier will
     * be overridden at send time.
     *
     * @param previousClickable the operator to modify the {@link Clickable}
     * @return this book, for chained calls
     */
    public Book setPreviousClickable(@NotNull UnaryOperator<Clickable> previousClickable) {
        previousClickable.apply(this.previousClickable);
        return this;
    }

    /**
     * Allows you to set the base {@link Clickable} used to navigate to
     * the previous page.
     * <p>
     * Some settings, such as the disabled state or identifier will
     * be overridden at send time.
     *
     * @param previousClickable the {@link Clickable}
     * @return this book, for chained calls
     */
    @JsonSetter private Book setPreviousClickable(Clickable previousClickable) {
        this.previousClickable = previousClickable;
        return this;
    }

    /**
     * Returns an {@link ActionableRow} containing the book's
     * {@link Clickable}s, which should include the navigation {@link Clickable}.
     *
     * @return the {@link ActionableRow} of {@link Clickable}s
     */
    @JsonIgnore public ActionableRow getClickableRow() {
        return ActionableRow.of(clickables);
    }

    /**
     * @return the list of {@link ActionableRow}s
     */
    public List<ActionableRow> getActionableRows() {
        return actionableRows;
    }

    /**
     * Adds {@link ActionableRow}s to the book.
     * <p>
     * This method should be used to add your own {@link ActionableRow}s,
     * as well as the {@link ActionableRow} of {@link Clickable}s
     * containing the navigation.
     * <p>
     * Discord allows a maximum of 5 rows per message, but this
     * library doesn't limit the number of rows by itself.
     *
     * @param actionRows the {@link ActionableRow}s to set
     * @return this book, for chained calls
     */
    public Book addActionableRows(List<ActionableRow> actionRows) {
        actionableRows.clear();
        actionableRows.addAll(actionRows);
        return this;
    }


    /**
     * Adds {@link ActionableRow}s to the book.
     * <p>
     * This method should be used to add your own {@link ActionableRow}s,
     * as well as the {@link ActionableRow} of {@link Clickable}s
     * containing the navigation.
     * <p>
     * Discord allows a maximum of 5 rows per message, but this
     * library doesn't limit the number of rows by itself.
     *
     * @param actionRows the {@link ActionableRow}s to set
     * @return this book, for chained calls
     */

    public Book addActionableRows(ActionableRow... actionRows) {
        return addActionableRows(List.of(actionRows));
    }

    /**
     * @return the identifiers of the owners of the book
     */
    public List<String> getOwners() {
        return owners;
    }

    /**
     * @param ids the identifiers of the owners of the book
     */
    public Book setOwners(List<String> ids) {
        owners.clear();
        owners.addAll(ids);
        return this;
    }

    /**
     * @param ids the identifiers of the owners of the book
     */
    @JsonSetter public Book setOwners(String... ids) {
        return setOwners(List.of(ids));
    }

    /**
     * Adds the {@link ActionableRow}s to all
     * the pages of the book.
     * <p>
     * This method should be called after setting
     * the book up.
     *
     * @return this book, for chained calls
     */
    public Book build() {
        for (Sendable page : pages) {
            page.addActionableRows(actionableRows);
        }
        return this;
    }

    /**
     * Applies the navigation of the book
     * for a given index.
     *
     * @param index the index
     */
    private Book setNavigation(int index) {
        previousClickable.setDisabled(index == 0).setId("book:" + id + "@" + (index - 1));
        nextClickable.setDisabled(index == pages.size() - 1).setId("book:" + id + "@" + (index + 1));

        return this;
    }
}
