package com.lutzseverino.discordbooks.book;

import com.lutzseverino.discordbooks.discord.component.ActionableRow;
import com.lutzseverino.discordbooks.discord.component.Clickable;
import com.lutzseverino.discordbooks.discord.component.impl.ClickableImpl;
import com.lutzseverino.discordbooks.discord.message.Sendable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class Book {
    private final List<Sendable> pages = new ArrayList<>();
    private final Clickable previousClickable = new ClickableImpl(Clickable.Style.PRIMARY).setDisplay("Previous");
    private final Clickable nextClickable = new ClickableImpl(Clickable.Style.PRIMARY).setDisplay("Next");
    private final List<Clickable> clickables = new ArrayList<>(List.of(this.previousClickable, this.nextClickable));
    private final List<ActionableRow> actionableRows = new ArrayList<>(List.of(new ActionableRow(this.clickables)));
    private final List<String> owners = new ArrayList<>();
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public Book() {
    }

    /**
     * @return the name of the book
     */
    public String getName() {
        return name;
    }


    /**
     * @param name the name of the book
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param index the index
     * @return the page at the index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    @NotNull public Sendable getPage(int index) {
        return pages.get(index);
    }

    /**
     * @return the list of pages
     */
    public List<Sendable> getPages() {
        return pages;
    }

    /**
     * Uses the provided page as the index, then adds
     * one and returns the resulting page.
     *
     * @param page the index
     * @return the next page, null if out of bounds
     */
    @Nullable public Sendable getNextPage(Sendable page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index + 1);
    }

    /**
     * Uses the provided page as the index, then subtracts
     * one and returns the resulting page.
     *
     * @param page the index
     * @return the next page, null if out of bounds
     */
    @Nullable public Sendable getPreviousPage(Sendable page) {
        int index = pages.indexOf(page);

        return index == -1 ? null : pages.get(index - 1);
    }

    /**
     * @param pages the pages to add
     */
    public void addPages(Sendable @NotNull ... pages) {
        this.pages.addAll(List.of(pages));
    }

    /**
     * Gets the list of {@link Clickable}s.
     *
     * @return a list of action rows
     * @see #getClickables()
     * @see #getClickableRow()
     */
    private List<Clickable> getClickables() {
        return this.clickables;
    }

    /**
     * Clears the default list and sets the items of
     * the {@link ActionableRow} of {@link Clickable}s.
     *
     * @param clickables the {@link Clickable}s to set
     * @see #setClickables(Clickable...)
     * @see #setNextClickable(UnaryOperator)
     * @see #setPreviousClickable(UnaryOperator)
     */
    public void setClickables(List<Clickable> clickables) {
        this.clickables.clear();
        this.clickables.addAll(clickables);
    }

    /**
     * Sets the items of the {@link ActionableRow} of {@link Clickable}s.
     *
     * @param clickables the {@link Clickable}s to set
     * @see #setClickables(Clickable...)
     * @see #setNextClickable(UnaryOperator)
     * @see #setPreviousClickable(UnaryOperator)
     */
    public void setClickables(Clickable... clickables) {
        this.clickables.clear();
        this.clickables.addAll(List.of(clickables));
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
     * Some settings, such as the disabled state or ID will be overridden
     * at send time.
     *
     * @param nextClickable the operator to modify the {@link Clickable}
     */
    public void setNextClickable(@NotNull UnaryOperator<Clickable> nextClickable) {
        nextClickable.apply(this.nextClickable);
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
     * Some settings, such as the disabled state or ID will be overridden
     * at send time.
     *
     * @param previousClickable the operator to modify the {@link Clickable}
     */
    public void setPreviousClickable(@NotNull UnaryOperator<Clickable> previousClickable) {
        previousClickable.apply(this.previousClickable);
    }

    /**
     * Returns an {@link ActionableRow} containing the book's
     * {@link Clickable}s, which should include the navigation {@link Clickable}.
     *
     * @return the {@link ActionableRow} of {@link Clickable}s
     */
    public ActionableRow getClickableRow() {
        return new ActionableRow(clickables);
    }

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
     */
    public void setActionableRows(List<ActionableRow> actionRows) {
        this.actionableRows.clear();
        this.actionableRows.addAll(actionRows);
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
     */
    public void setActionableRows(ActionableRow... actionRows) {
        this.actionableRows.clear();
        this.actionableRows.addAll(List.of(actionRows));
    }

    /**
     * @return the IDs of the owners of the book
     */
    public List<String> getOwners() {
        return owners;
    }

    /**
     * @param ids the IDs of the owners of the book
     */
    public void setOwners(String... ids) {
        this.owners.addAll(List.of(ids));
    }

    /**
     * @param ids the IDs of the owners of the book
     */
    public void setOwners(List<String> ids) {
        this.owners.addAll(ids);
    }

    /**
     * Builds the first page of the book, modifying
     * the stored {@link Clickable}s
     *
     * @return the built {@link Sendable}
     */
    public Sendable build() {
        return build(0);
    }

    /**
     * Builds a page of the book, modifying
     * the stored {@link Clickable}s
     *
     * @param index the page number to build
     * @return the built {@link Sendable}
     */
    public Sendable build(int index) {
        Sendable sendable = pages.get(index);

        previousClickable.setDisabled(index == 0).setId("book:" + getName() + "@" + (index - 1));
        nextClickable.setDisabled(index == pages.size() - 1).setId("book:" + getName() + "@" + (index + 1));

        return sendable.setActionableRows(actionableRows);
    }
}
