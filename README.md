# 2D-Game  
My first 2D turn-based RPG built in Java, featuring player movement, world exploration, item management, and save/load functionality using file I/O.

## Overview  
This project is a **2D turn-based RPG** where players can move around a world area, interact with objects, collect items, and battle monsters.  
The game demonstrates **core game development concepts** such as state management, object interaction, turn-based mechanics, and file-based saving/loading.

The main goal of this project was to learn how to handle **game loops**, **keyboard input**, **file I/O**, and **object-oriented design** while creating a playable RPG environment.

---

## Features  
* **Turn-Based Combat System** – Engage in battles where players and monsters take turns to attack or use items.  
* **Player Movement** – Navigate the world using WASD controls.  
* **World Area Exploration** – Explore different zones containing items and monsters.  
* **Inventory System** – Collect, manage, and use items via an interactive inventory screen.  
* **File I/O Save System** – Save and load player progress using external files.  
* **Pause Menu** – Access options such as saving and exiting from the pause menu.  
* **Interactive Objects** – Pick up items or interact with objects in the world.  

---

## Controls  
**Movement**  
- `W`: Move Up  
- `A`: Move Left  
- `S`: Move Down  
- `D`: Move Right  

**Gameplay**  
- `E`: Pick up or interact with objects  
- `Esc`: Pause the game (press `E` while paused to save)  
- `I`: Open inventory  

**Inventory Controls**  
- Use the **mouse** and **right-click** to move items around the inventory.  

---

## How to Run  
### 1. Clone the Repository  
```bash
git clone https://github.com/zachbs/2D-Game.git
```

```bash
cd 2D-Game
```

### 2. Compile the Game

If using command line:

```bash
javac -d bin src/**/*.java
```

### 3. Run the Game

```bash
java -cp bin Main
```

## Future Improvements  
* **Enhanced Combat:** Add special abilities or elemental attacks.  
* **Expanded World:** Introduce multiple biomes, towns, and dungeons.  
* **Dialogue System:** Implement NPC conversations and quests.    
* **Better Save System:** Implement JSON-based save files for easier editing.  

---

## Educational Purpose  
This project served as a major learning experience in:  
* Implementing **object-oriented design** in a game environment.  
* Handling **file I/O** for saving and loading state.  
* Building a **turn-based game loop** from scratch.  
* Designing basic **2D game mechanics** like movement, collision, and interaction.  

---

## Current Status: Playable Prototype  
The core systems — movement, combat, saving/loading, and inventory management — are fully functional.  
While there’s room for expansion, the project stands as a complete, working foundation for a **2D turn-based RPG**.

   
