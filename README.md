<div align="center">
    <h1 align="center">Discord Books</h1>
    <p>A lightweight, implementable library that provides message pagination for Discord</p>
    <p>
        <img alt="badge java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=flat&logo=oracle&logoColor=white"/>
        <img alt="license" src="https://img.shields.io/github/license/lutzseverino/discord-books">
        <img alt="maven central version" src="https://img.shields.io/maven-central/v/com.lutzseverino.discordbooks/discord-books">
        <img alt="gitHub last commit" src="https://img.shields.io/github/last-commit/lutzseverino/discord-books">
    </p>
</div>

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

This is the latest version of the library on Maven Central. Compile the library yourself for newer, unreleased
versions.

![Maven Central](https://img.shields.io/maven-central/v/com.lutzseverino.discordbooks/discord-books)

## Features

* Create static books that don't expire as long as they exist in your source.
* Create on-the-go books that can contain variable content.
* Choose how books should be stored and retrieved.
* Customize all aspects of the action rows, add extra ones or change the order.
* Add action rows to individual pages.
* Handle errors easily.

## Wiki

Understand the library and learn how to use it by reading the [wiki](https://github.com/lutzseverino/discord-books/wiki).

## Credits

Idea and execution by [Jasper Lutz Severino](https://github.com/lutzseverino). General audit and help for all versions
below 1.0.0 by [Alberto Mimbrero](https://github.com/mimbrero).
