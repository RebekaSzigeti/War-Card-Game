# War-Card-Game

# 🃏 WAR Card Game (Java Swing)

A simple desktop implementation of the classic **War card game** built with Java and Swing.

This project demonstrates object-oriented programming, GUI development, file handling, and basic game logic.

---

## Features

- Graphical user interface (Java Swing)
- Main menu (Start Game, Statistics, Exit)
- Manual and Auto Play modes
- Background music and sound effects
- Persistent statistics saved to file
- Round limit to prevent infinite games
- Clean object-oriented design

---

## Technologies Used

- Java
- Java Swing (GUI)
- Collections Framework (List, Queue)
- File I/O (BufferedReader, FileWriter)
- Java Streams API
- Java Sound API

---

##  Project Structure

```
war-game/
├── Card.java
├── Deck.java
├── Player.java
├── WarGameFrame.java
├── MainMenuFrame.java
├── CardPanel.java
├── StatisticsManager.java
├── SoundManager.java
├── Main.java
├── stats.txt (generated automatically)
└── sounds/
├── background.wav
└── click.wav

```

- Note: Sound files (background.wav, click.wav) are not included.
- The game works without them.

---

## Game Rules

- Each player starts with half of the shuffled deck.
- Both players play one card per round.
- Higher rank wins both cards.
- If ranks are equal → WAR.
- The game ends when:
  - One player runs out of cards
  - 50 rounds are reached (declared as a draw)
 
  ---

 ## Statistics

Game results are saved automatically in: stats.txt
Saved information:
- Date of the game
- Winner (PLAYER / COMPUTER)
- Draws

Statistics can be viewed from the main menu.

