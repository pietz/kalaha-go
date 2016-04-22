# KalahaGo
KalahaGo is a game I wrote as an university project. It's based on the popular board game Kalaha, which is also often referred to as Mancala. KalahaGo is based on the "Empty Capture" version of the game.

## Run
To run the game simply launch the KalahaGo.jar file from the command line ('java -jar KalahaGo.jar') and make sure you have the latest version of Java installed.

## Interactions
Once you configured the settings and started the game you have a few different interactions you can use.

  - Click & Hold to see where the last stone will fall into
  - Double click to make a move
  - Activating the hint mode will display the best possible move in red and show you ratings for each of your playing fields
  
## Challenges
Creating KalahaGo came with multiple steps and challenges involved. At first I created the GUI in JavaFX with help of the SceneBuilder tool. After that I designed the overall structure of the classes before implementing the game logic as well as the AI opponent. Arguably the biggest challenge while writing KalahaGo was Performance. 

When working with the Minimax Algorithm and the tree structure that comes with it, you’re easily dealing with hundreds of thousands of nodes. Based on the assignment I had to deal with an O(3n) runtime efficiency because I had to build up the tree with every move, analyze every node for its rating and write a Log-File documentating the tree information. These three steps had to be executed in this order, because step 2 needed the tree to be already built and step 3 needed the rating to be complete. 

Doing calculations for 100.000 nodes is a pain in the ass performancewise. Doing calculations for 100.000 nodes three times doesn’t make it any better. I was able to get it down to O(2n) by combining Minimax and building the tree. On my way down the tree I added the nodes that needed to be added. Once the tree was complete I recursively returned the ratings from the bottom of the tree to the top where I needed them.
