# Memento Pattern in Gaming

## Overview of the Memento Pattern

The Memento pattern is a behavioral design pattern that allows an object to save its state to restore to its previous state later. This pattern is particularly useful in scenarios where you need to provide an undo mechanism or restore an object to its previous state upon encountering an error.

In the Memento pattern, three main actors are involved:

- **Originator**: The object whose state needs to be saved and restored.
- **Memento**: A lightweight object that stores the state of the originator. The Memento is passive; it only holds the data without any logic of its own.
- **Caretaker**: Manages the mementos but does not modify or inspect the contents of the mementos.

## Application in Gaming

In gaming, the Memento pattern can be highly valuable. It allows developers to implement features such as game saves, undos, or rollbacks to a previous game state, enhancing the player's experience by providing flexibility and control over the game progress.

### Implementation in This Project

In this project, the Memento pattern is applied to manage the state of a game level. Here's how it's structured:

- **GameLevel (Originator)**: Represents the current state of a game level, including player position, inventory items, and score. It's capable of saving its state to a `Memento` object and restoring its state from the same.
- **GameLevel.Memento**: Encapsulates the state of a `GameLevel` instance. This includes the player's position, inventory, and score at a certain point in time.
- **GameLevelHistory (Caretaker)**: Keeps track of changes in the game level's state by storing a list of `Memento` objects. It allows the game to save snapshots of its state and revert to previous states.

### Example Usage

```java
GameLevel gameLevel = new GameLevel(0, 0, new ArrayList<>(Arrays.asList("Sword", "Shield")), 0);
GameLevelHistory history = new GameLevelHistory();

// Save initial state
history.save(gameLevel);

// Changes in the game level
gameLevel.setPlayerPositionX(10);
gameLevel.getInventory().add("Bow");
gameLevel.setScore(50);

// Save the changed state
history.save(gameLevel);

// Undo the last change
history.undo(gameLevel);
