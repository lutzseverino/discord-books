![java language](https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=oracle&logoColor=white) ![license](https://img.shields.io/github/license/lutzseverino/discord-books) ![maven central version](https://img.shields.io/maven-central/v/com.lutzseverino.discordbooks/discord-books) ![gitHub last commit](https://img.shields.io/github/last-commit/lutzseverino/discord-books) ![total lines](https://img.shields.io/tokei/lines/github/lutzseverino/discord-books)

![github-frame](https://user-images.githubusercontent.com/28309837/182840517-972a184a-95c1-4cfb-ad82-e703af652cb6.png)
![discord-books library showcase](https://user-images.githubusercontent.com/28309837/182843934-f5eb5cb9-3f76-44a9-82a2-20d1a1ee3648.gif)

## What is this?

A lightweight, implementable library that provides pagination and helps you create multipage messages - or "books" -
automatically on Discord.

## Summary

- [What is this?](#what-is-this-)
- [Summary](#summary)
- [Installation](#installation)
  * [Available spins](#available-spins)
  * [Versions](#versions)
- [Features](#features)
- [Usage](#usage)
  * [Instantiating the library](#instantiating-the-library)
    + [Available default database technologies](#available-default-database-technologies)
  * [Creating the book](#creating-the-book)
  * [Adding pages](#adding-pages)
  * [Customizing buttons](#customizing-buttons)
  * [Adding component rows](#adding-component-rows)
  * [Building the book](#building-the-book)
  * [Registering the books](#registering-the-books)
  * [Sending the books](#sending-the-books)
  * [Sending on-the-go books](#sending-on-the-go-books)
  * [Listening to the button event](#listening-to-the-button-event)
  * [Handling errors](#handling-errors)
- [Credits](#credits)

## Installation

Add the following to your `pom.xml` file:

```xml
<dependency>
    <groupId>com.lutzseverino.discordbooks</groupId>
    <artifactId>discord-books-SPIN</artifactId>
    <version>VERSION</version>
</dependency> 
```

Alternatively, for Gradle:

```gradle
implementation 'com.lutzseverino.discordbooks:discord-books-SPIN:VERSION'
```

Replace `SPIN` and `VERSION` with the appropriate values.

### Available spins

* core
* [jda](https://github.com/DV8FromTheWorld/JDA) (Java Discord API)

### Versions

This is the latest version of the library on Maven Central. You can compile the library yourself for newer, unreleased
versions.

![Maven Central](https://img.shields.io/maven-central/v/com.lutzseverino.discordbooks/discord-books)

## Features

* Create static books that don't expire as long as they exist in your source.
* Create on-the-go books that can contain variable content.
* Choose how books should be stored and retrieved.
* Customize all aspects of the action rows, add extra ones or change the order.
* Add action rows to individual pages.
* Handle errors easily.

## Usage

Usage is mostly the same across spins.

### Instantiating the library

Different database options may be used and set when creating the `DiscordBooks` object.

The databases will be used to store the books, once they're gone, pagination will not be possible. Books that contain
static content are added to the database on register.

By default, the databases used for books with and without identifiers, will be a map. But this can be changed by calling
the `#setDatabase()` and `#setTemporaryDatabase()` methods.

It's highly encouraged that you use a self-expiring form of database for temporary books, to keep it from filling up
endlessly while your program is running.

```java
public class Main {
    public final DiscordBooks discordBooks = new DiscordBooks()
            .setDatabase(new MapDB())
            .setTemporaryDatabase(new GuavaDB());
}
```

For extra default database options, you can install the `database` spin.

If these extra defaults are still not enough, you can implement the `BookDB` interface to add your own.

```xml
<dependency>
    <groupId>com.lutzseverino.discordbooks</groupId>
    <artifactId>discord-books-database-TECH</artifactId>
    <version>VERSION</version>
</dependency> 
```

Replace `TECH` and `VERSION` with the appropriate values.

#### Available default database technologies

* guava (GuavaDB)
* redis (JedisDB)

### Creating the book

First, we extend the Book object and call the `super` constructor to specify the identifier of the book.

```java
public class Example extends Book {
    public Example() {
        // "example" will be the identifier of the book.
        super("example");
    }
}
```

### Adding pages

Pages are added with the `#addPages(Sendable...)` method. `Sendables` are like messages, they can contain text,
embeds, or both at the same time.

In this example, we're adding three pages, each one can benefit from everything messages can contain.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        addPages(
                // First page, has regular text and a button of its own.
                new SendableImpl("A regular ol' text message.")
                        .addActionableRows(ActionableRow.of(
                                new ClickableImpl(Clickable.Style.PRIMARY)
                                        .setId("example-page-button")
                                        .setDisplay("Pages can have buttons of their own!"))),
                // Second page, has two embeds.
                new SendableImpl().addEmbeds(
                        new EmbedImpl()
                                .setTitle("Embeds?")
                                .setDescription("No problemo."),
                        new EmbedImpl()
                                .setTitle("Another one, too!")
                                .setDescription("Messages can have multiple of these!")
                ),
                // Third page, has regular text and an embed.
                new SendableImpl().setText("Text and embeds at the same time?")
                        .addEmbeds(
                                new EmbedImpl()
                                        .setTitle("I betcha")
                                        .setDescription("Need more convincing? Too bad, I've got no more pages left."))
        );
    }
}
```

### Customizing buttons

You may want to change how the navigation buttons look, so customization is possible via the `#setNextClickable()`
and `#setPreviousClickable()` methods.

After creating the clickables, you'll have to add them manually by calling `#addClickables()`. This is
required, so you can have control over the order of the buttons. You may also add extra buttons to this row.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        // {...}

        // Customize the navigation buttons to our liking
        setNextClickable(clickable -> clickable.setEmoji("➡️"));
        setPreviousClickable(clickable -> clickable.setEmoji("⬅️"));

        // Then add the buttons to the book, alongside any additional buttons you may want to add.
        addClickables(
                // Top to bottom, left to right.
                getPreviousClickable(),
                new ClickableImpl(Clickable.Style.DANGER)
                        .setId("example-danger")
                        .setEmoji("🔥"),
                getNextClickable()
        );
    }
}
```

### Adding component rows

No rows will be displayed yet, to do that, you'll need to call the `#addActionableRows()` method. Here - alongside any
other rows you may want to add - you' can add the row you populated before.

This is a way to ensure you get to decide the order of the rows.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        // {...}

        // Finish up by adding the rows.
        addActionableRows(
                // Top to bottom.
                getClickableRow(),
                ActionableRow.of(
                        new ClickableImpl(Clickable.Style.SECONDARY)
                                .setId("example-secondary")
                                .setDisplay("More rows?"),
                        new ClickableImpl(Clickable.Style.SUCCESS)
                                .setId("example-success")
                                .setDisplay("Easy task!")
                ),
                ActionableRow.of(
                        new SelectableImpl("select", new SelectableImpl.OptionImpl("Menus too!", "Amazing")
                                .setDefault(true)
                        )
                )
        );
    }
}
```

### Building the book

After all of that is done, we call the `#build()` method to stitch everything together.

```java
public class Example extends JDABook {
    public Example() {
        super("example");

        // {...}

        build();
    }
}
```

### Registering the books

To register the books, you call the `#registerBooks` method.

```java
public class Main {
    public final DiscordBooks discordBooks = new DiscordBooks();

    public static void main(String[] args) {
        discordBooks.registerBooks(new Example());
    }
}
```
### Sending the books

That was easy, right? Let's send the books!

First, we create an instance of a `Receivable`. This changes depending on the spin you're using, for JDA that'll
be `JDAReceivable`.

```java
JDAReceivable jdaReceivable=new JDAReceivable(event.getChannel());
jdaReceivable.receive(DiscordBooks.getBook("example"));
```

### Sending on-the-go books

Books don't necessarily need to be registered to be sent, you can create and send books on the spot.

```java
public class Main {
    public static void main(String[] args) {
        JDAReceivable jdaReceivable = new JDAReceivable(event.getChannel());

        jdaReceivable.receive(new Book()
                .addPages(
                        new SendableImpl("This is a temporary book!"),
                        new SendableImpl("With variable content, right " + event.getAuthor().getAsTag() + "?")
                ).build());
    }
}
```

This book will be stored in the temporary database. Read [the first usage chapter](#instantiating-the-library) for more
information.

### Listening to the button event

The library comes with a pre-made listener for the buttons, you can use it by adding it to your respective library
builder.

For JDA, it would look like this:

```java
builder.addEventListeners(new JDABookButtonListener());
```

Alternatively, you can create your own listener, if you wish to change the behaviour.

### Handling errors

Each spin has its own error handler, if you want to change its behaviour, you'll need to implement it and pass it to the
aforementioned listener.

For JDA, it would look like this:

```java
public class JDABookErrorHandlerImpl implements JDABookErrorHandler {
    @Override public void whenBookIsNull(ButtonInteractionEvent event) {
        event.reply("This book may no longer exist.").setEphemeral(true).queue();
    }

    @Override public void whenUserIsNotOwner(ButtonInteractionEvent event) {
        event.reply("You cannot navigate this book").setEphemeral(true).queue();
    }
}
```

```java
builder.addEventListeners(new JDABookButtonListener(new JDABookErrorHandlerImpl()));
```

If no error handler is provided, the library will use its own default one, which looks like the above snippet.

## Credits

Idea and execution by [Jasper Lutz Severino](https://github.com/lutzseverino). General audit and help for all versions
below 1.0.0 by [Alberto Mimbrero](https://github.com/mimbrero).