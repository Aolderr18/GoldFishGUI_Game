import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GoldFishRemembersEverything extends Application {
	static int w; /*
					 * This variable is used for loop iterations.
					 */
	static int numberOfLives; // This is the number of lives the player has.
	static boolean gameActive;
	/**
	 * If the game is inactive, the player failed to complete the round.
	 */
	static boolean[] coinTurned;/*
								 * This boolean value array holds information as
								 * to which coins have been touched so far in a
								 * given round.
								 */
	static boolean[] coinMatched;/**
	* This boolean value array holds information as to which coins have been matched up 
	* to their corresponding colors.
	*/
	static boolean[] coinSelected; /*
	* This boolean value array holds information as to which coin was the last one to
	* get touched.
	*/

	static boolean primaryExpressionAvailable;
	static boolean secondaryExpressionAvailable;
	static String primaryExpression;
	static String secondaryExpression;
	static boolean firstCoinSelected;
	static int score;

	@SuppressWarnings("incomplete-switch")
	public void start(Stage primaryStage) {
		coinMatched = new boolean[] { false, false, false, false };
		coinSelected = new boolean[] { false, false, false, false, false, false, false, false };
		coinTurned = new boolean[] { false, false, false, false, false, false, false, false };
		firstCoinSelected = false;
		gameActive = true;
		// Create the boundaries
		Rectangle leftBoundary, topBoundary, rightBoundary, bottomBoundary;
		leftBoundary = new Rectangle(0, 0, 20, 1200);
		topBoundary = new Rectangle(0, 0, 1200, 20);
		rightBoundary = new Rectangle(1200, 0, 20, 1200);
		bottomBoundary = new Rectangle(0, 720, 1200, 80);
		Rectangle _leftBoundary, _topBoundary, _rightBoundary, _bottomBoundary;
		_leftBoundary = new Rectangle(0, 0, 20, 1200);
		_topBoundary = new Rectangle(0, 0, 1200, 20);
		_rightBoundary = new Rectangle(1200, 0, 20, 1200);
		_bottomBoundary = new Rectangle(0, 720, 1200, 80);
		/**
		 * The horizontal divider must be between 320 and 400 units down.
		 */
		double horizontalDivide = (Math.random() * 1000) % 81 + 350;
		/**
		 * The left vertical divide must be between 350 and 450 units to the
		 * right.
		 */
		double leftVerticalDivide = (Math.random() * 1200) % 101 + 350;
		/**
		 * The right vertical divide must be between 750 and 850 units to the
		 * right.
		 */
		double rightVerticalDivide = (Math.random() * 1200) % 101 + 750;
		// Create the walls
		Rectangle wall1, wall2, wall3, wall4, wall5, wall6, wall7, wall8;
		wall1 = new Rectangle();
		wall2 = new Rectangle();
		wall3 = new Rectangle();
		wall4 = new Rectangle();
		wall5 = new Rectangle();
		wall6 = new Rectangle();
		wall7 = new Rectangle();
		wall8 = new Rectangle();
		wall1.setFill(Color.DARKOLIVEGREEN);
		wall2.setFill(Color.DARKOLIVEGREEN);
		wall3.setFill(Color.DARKOLIVEGREEN);
		wall4.setFill(Color.DARKOLIVEGREEN);
		wall5.setFill(Color.DARKOLIVEGREEN);
		wall6.setFill(Color.DARKOLIVEGREEN);
		wall7.setFill(Color.DARKOLIVEGREEN);
		wall8.setFill(Color.DARKOLIVEGREEN);
		ArrayList<Rectangle> primaryWalls = new ArrayList<Rectangle>();
		primaryWalls.add(wall1);
		primaryWalls.add(wall2);
		primaryWalls.add(wall3);
		primaryWalls.add(wall4);
		ArrayList<Rectangle> secondaryWalls = new ArrayList<Rectangle>();
		secondaryWalls.add(wall5);
		secondaryWalls.add(wall6);
		secondaryWalls.add(wall7);
		secondaryWalls.add(wall8);
		boolean[] regionChosen = new boolean[] { false, false, false, false, false, false };
		w = 0;
		while (w < 4) {
			short region = (short) (Math.random() * 6);
			if (!regionChosen[region]) {
				double width = Math.random() * 101 + 100;
				double height = Math.random() * 101 + 100;
				switch (region) {
				case 0:
					primaryWalls.get(w).setX(leftVerticalDivide - width - 30);
					primaryWalls.get(w).setY(horizontalDivide - height - 30);
					primaryWalls.get(w).setWidth(width);
					primaryWalls.get(w).setHeight(height);
					break;
				case 1:
					primaryWalls.get(w).setX(rightVerticalDivide - width - 30);
					primaryWalls.get(w).setY(horizontalDivide - height - 30);
					primaryWalls.get(w).setWidth(width);
					primaryWalls.get(w).setHeight(height);
					break;
				case 2:
					primaryWalls.get(w).setX(1200 - width - 30);
					primaryWalls.get(w).setY(horizontalDivide - height - 30);
					primaryWalls.get(w).setWidth(width);
					primaryWalls.get(w).setHeight(height);
					break;
				case 3:
					primaryWalls.get(w).setX(leftVerticalDivide - width - 30);
					primaryWalls.get(w).setY(horizontalDivide + height + 30);
					primaryWalls.get(w).setWidth(width);
					primaryWalls.get(w).setHeight(height);
					break;
				case 4:
					primaryWalls.get(w).setX(rightVerticalDivide - width - 30);
					primaryWalls.get(w).setY(horizontalDivide + height + 30);
					primaryWalls.get(w).setWidth(width);
					primaryWalls.get(w).setHeight(height);
					break;
				case 5:
					primaryWalls.get(w).setX(1200 - width - 30);
					primaryWalls.get(w).setY(horizontalDivide + height + 30);
					primaryWalls.get(w).setWidth(width);
					primaryWalls.get(w).setHeight(height);
					break;
				}
				regionChosen[region] = true;
				++w;
			}
		}
		Rectangle door = new Rectangle();
		door.setWidth(Math.random() * 101 + 100);
		door.setHeight(Math.random() * 101 + 100);
		door.setFill(Color.DARKOLIVEGREEN);
		int n = (int) (Math.random() * regionChosen.length);
		int h = n + 6;
		for (; n < h; ++n) {
			if (!regionChosen[n % regionChosen.length]) {
				switch (n % regionChosen.length) {
				case 0:
					door.setX(leftVerticalDivide - door.getWidth() - 30);
					door.setY(horizontalDivide - door.getHeight() - 30);
					break;
				case 1:
					door.setX(rightVerticalDivide - door.getWidth() - 30);
					door.setY(horizontalDivide - door.getHeight() - 30);
					break;
				case 2:
					door.setX(1200 - door.getWidth() - 30);
					door.setY(horizontalDivide - door.getHeight() - 30);
					break;
				case 3:
					door.setX(leftVerticalDivide - door.getWidth() - 30);
					door.setY(horizontalDivide + door.getHeight() + 30);
					break;
				case 4:
					door.setX(rightVerticalDivide - door.getWidth() - 30);
					door.setY(horizontalDivide + door.getHeight() + 30);
					break;
				case 5:
					door.setX(1200 - door.getWidth() - 30);
					door.setY(horizontalDivide + door.getHeight() + 30);
					break;
				}
				break;
			}
		}
		regionChosen[0] = false;
		regionChosen[1] = false;
		regionChosen[2] = false;
		regionChosen[3] = false;
		regionChosen[4] = false;
		regionChosen[5] = false;
		w = 0;
		while (w < 4) {
			short region = (short) (Math.random() * 6);
			if (!regionChosen[region]) {
				double width = Math.random() * 101 + 100;
				double height = Math.random() * 101 + 100;
				switch (region) {
				case 0:
					secondaryWalls.get(w).setX(leftVerticalDivide - width - 30);
					secondaryWalls.get(w).setY(horizontalDivide - height - 30);
					secondaryWalls.get(w).setWidth(width);
					secondaryWalls.get(w).setHeight(height);
					break;
				case 1:
					secondaryWalls.get(w).setX(rightVerticalDivide - width - 30);
					secondaryWalls.get(w).setY(horizontalDivide - height - 30);
					secondaryWalls.get(w).setWidth(width);
					secondaryWalls.get(w).setHeight(height);
					break;
				case 2:
					secondaryWalls.get(w).setX(1200 - width - 30);
					secondaryWalls.get(w).setY(horizontalDivide - height - 30);
					secondaryWalls.get(w).setWidth(width);
					secondaryWalls.get(w).setHeight(height);
					break;
				case 3:
					secondaryWalls.get(w).setX(leftVerticalDivide - width - 30);
					secondaryWalls.get(w).setY(horizontalDivide + height + 30);
					secondaryWalls.get(w).setWidth(width);
					secondaryWalls.get(w).setHeight(height);
					break;
				case 4:
					secondaryWalls.get(w).setX(rightVerticalDivide - width - 30);
					secondaryWalls.get(w).setY(horizontalDivide + height + 30);
					secondaryWalls.get(w).setWidth(width);
					secondaryWalls.get(w).setHeight(height);
					break;
				case 5:
					secondaryWalls.get(w).setX(1200 - width - 30);
					secondaryWalls.get(w).setY(horizontalDivide + height + 30);
					secondaryWalls.get(w).setWidth(width);
					secondaryWalls.get(w).setHeight(height);
					break;
				}
				regionChosen[region] = true;
				++w;
			}
		}
		Rectangle otherDoor = new Rectangle();
		otherDoor.setWidth(Math.random() * 101 + 100);
		otherDoor.setHeight(Math.random() * 101 + 100);
		otherDoor.setFill(Color.DARKOLIVEGREEN);
		n = (int) (Math.random() * regionChosen.length);
		h = n + 6;
		for (; n < h; ++n) {
			if (!regionChosen[n % regionChosen.length]) {
				switch (n % regionChosen.length) {
				case 0:
					otherDoor.setX(leftVerticalDivide - door.getWidth() - 30);
					otherDoor.setY(horizontalDivide - door.getHeight() - 30);
					break;
				case 1:
					otherDoor.setX(rightVerticalDivide - door.getWidth() - 30);
					otherDoor.setY(horizontalDivide - door.getHeight() - 30);
					break;
				case 2:
					otherDoor.setX(1200 - door.getWidth() - 30);
					otherDoor.setY(horizontalDivide - door.getHeight() - 30);
					break;
				case 3:
					otherDoor.setX(leftVerticalDivide - door.getWidth() - 30);
					otherDoor.setY(horizontalDivide + door.getHeight() + 30);
					break;
				case 4:
					otherDoor.setX(rightVerticalDivide - door.getWidth() - 30);
					otherDoor.setY(horizontalDivide + door.getHeight() + 30);
					break;
				case 5:
					otherDoor.setX(1200 - door.getWidth() - 30);
					otherDoor.setY(horizontalDivide + door.getHeight() + 30);
					break;
				}
				break;
			}
		}
		Circle coin1, coin2, coin3, coin4, coin5, coin6, coin7, coin8;
		coin1 = new Circle(20);
		coin2 = new Circle(20);
		coin3 = new Circle(20);
		coin4 = new Circle(20);
		coin5 = new Circle(20);
		coin6 = new Circle(20);
		coin7 = new Circle(20);
		coin8 = new Circle(20);
		coin1.setFill(Color.LIGHTGRAY);
		coin2.setFill(Color.LIGHTGRAY);
		coin3.setFill(Color.LIGHTGRAY);
		coin4.setFill(Color.LIGHTGRAY);
		coin5.setFill(Color.LIGHTGRAY);
		coin6.setFill(Color.LIGHTGRAY);
		coin7.setFill(Color.LIGHTGRAY);
		coin8.setFill(Color.LIGHTGRAY);
		ArrayList<Circle> primaryCoins = new ArrayList<Circle>();
		primaryCoins.add(coin1);
		primaryCoins.add(coin2);
		primaryCoins.add(coin3);
		primaryCoins.add(coin4);
		ArrayList<Circle> secondaryCoins = new ArrayList<Circle>();
		secondaryCoins.add(coin5);
		secondaryCoins.add(coin6);
		secondaryCoins.add(coin7);
		secondaryCoins.add(coin8);
		w = 0;
		boolean valid;
		while (w < 4) {
			valid = true;
			double centerX = Math.random() * 1000 + 100, centerY = Math.random() * 600 + 50;
			int z = 0;
			while (z < 5) {
				if (z < 4) {
					if (centerX > primaryWalls.get(z).getX() - 30
							&& centerX < primaryWalls.get(z).getX() + primaryWalls.get(z).getWidth() + 30)
						valid = false;
					if (centerY > primaryWalls.get(z).getY() - 30
							&& centerY < primaryWalls.get(z).getY() + primaryWalls.get(z).getWidth() + 30)
						valid = false;
				} else {
					if (centerX > door.getX() - 30 && centerX < door.getX() + door.getWidth() + 30)
						valid = false;
					if (centerY > door.getY() - 30 && centerY < door.getY() + door.getWidth() + 30)
						valid = false;
				}
				if (!valid)
					break;
				++z;
			}
			z = 0;
			while (z < w) {
				if (centerX > primaryCoins.get(z).getCenterX() - 30 && centerX < primaryCoins.get(z).getCenterX() + 30)
					valid = false;
				if (centerY > primaryCoins.get(z).getCenterY() - 30 && centerY < primaryCoins.get(z).getCenterY() + 30)
					valid = false;
				if (!valid)
					break;
				++z;
			}
			if (!valid)
				continue;
			primaryCoins.get(w).setCenterX(centerX);
			primaryCoins.get(w).setCenterY(centerY);
			++w;
		}
		w = 0;
		while (w < 4) {
			valid = true;
			double centerX = Math.random() * 1000 + 100, centerY = Math.random() * 600 + 50;
			int z = 0;
			while (z < 5) {
				if (z < 4) {
					if (centerX > secondaryWalls.get(z).getX() - 30
							&& centerX < secondaryWalls.get(z).getX() + secondaryWalls.get(z).getWidth() + 30)
						valid = false;
					if (centerY > secondaryWalls.get(z).getY() - 30
							&& centerY < secondaryWalls.get(z).getY() + secondaryWalls.get(z).getWidth() + 30)
						valid = false;
				} else {
					if (centerX > otherDoor.getX() - 30 && centerX < otherDoor.getX() + otherDoor.getWidth() + 30)
						valid = false;
					if (centerY > otherDoor.getY() - 30 && centerY < otherDoor.getY() + otherDoor.getWidth() + 30)
						valid = false;
				}
				if (!valid)
					break;
				++z;
			}
			z = 0;
			while (z < w) {
				if (centerX > secondaryCoins.get(z).getCenterX() - 30
						&& centerX < secondaryCoins.get(z).getCenterX() + 30)
					valid = false;
				if (centerY > secondaryCoins.get(z).getCenterY() - 30
						&& centerY < secondaryCoins.get(z).getCenterY() + 30)
					valid = false;
				if (!valid)
					break;
				++z;
			}
			if (!valid)
				continue;
			secondaryCoins.get(w).setCenterX(centerX);
			secondaryCoins.get(w).setCenterY(centerY);
			++w;
		}
		Rectangle fish = new Rectangle();
		fish.setWidth(8);
		fish.setHeight(40);
		fish.setFill(Color.GOLD);
		Rectangle nextFish = new Rectangle();
		nextFish.setWidth(8);
		nextFish.setHeight(40);
		nextFish.setFill(Color.GOLD);
		Text number1, number2, number3, number4;
		Text answer;
		int[] numbers = new int[5];
		for (int x = 0; x < numbers.length; ++x) {
			numbers[x] = (int) (Math.random() * 40);
			for (int q = 0; q < x; ++q)
				while (numbers[q] == numbers[x])
					numbers[x] = (int) (Math.random() * 40);
		}
		answer = new Text(door.getX() + door.getWidth() / 2, door.getY() + door.getHeight() / 2, "" + numbers[0]);
		number1 = new Text(wall1.getX() + wall1.getWidth() / 2, wall1.getY() + wall1.getHeight() / 2, "" + numbers[1]);
		number2 = new Text(wall2.getX() + wall2.getWidth() / 2, wall2.getY() + wall2.getHeight() / 2, "" + numbers[2]);
		number3 = new Text(wall3.getX() + wall3.getWidth() / 2, wall3.getY() + wall3.getHeight() / 2, "" + numbers[3]);
		number4 = new Text(wall4.getX() + wall4.getWidth() / 2, wall4.getY() + wall4.getHeight() / 2, "" + numbers[4]);
		byte mode = (byte) (Math.random() * 1);
		String operationSymbolLeft, operationSymbolRight;
		int operand1, operand2, operand3;
		switch (mode) {
		case 0:// This is multiplication mode A
			operationSymbolLeft = " * ";
			operand1 = (int) (Math.random() * 10) + 1;
			operand2 = (int) (Math.random() * 10) + 1;
			if (operand1 * operand2 > numbers[0]) {
				operationSymbolRight = " - ";
				operand3 = operand1 * operand2 - numbers[0];
			} else {
				operationSymbolRight = " + ";
				operand3 = numbers[0] - operand1 * operand2;
			}
			primaryExpression = "" + operand1 + operationSymbolLeft + operand2 + operationSymbolRight + operand3;
		}
		primaryExpressionAvailable = true;
		Text number5, number6, number7, number8;
		Text answer2;
		int[] secondaryNumbers = new int[5];
		for (int x = 0; x < secondaryNumbers.length; ++x) {
			secondaryNumbers[x] = (int) (Math.random() * 40);
			for (int q = 0; q < x; ++q)
				while (secondaryNumbers[q] == secondaryNumbers[x])
					secondaryNumbers[x] = (int) (Math.random() * 40);
		}
		answer2 = new Text(otherDoor.getX() + otherDoor.getWidth() / 2, otherDoor.getY() + otherDoor.getHeight() / 2,
				"" + secondaryNumbers[0]);
		number5 = new Text(wall5.getX() + wall5.getWidth() / 2, wall5.getY() + wall5.getHeight() / 2,
				"" + secondaryNumbers[1]);
		number6 = new Text(wall6.getX() + wall6.getWidth() / 2, wall6.getY() + wall6.getHeight() / 2,
				"" + secondaryNumbers[2]);
		number7 = new Text(wall7.getX() + wall7.getWidth() / 2, wall7.getY() + wall7.getHeight() / 2,
				"" + secondaryNumbers[3]);
		number8 = new Text(wall8.getX() + wall8.getWidth() / 2, wall8.getY() + wall8.getHeight() / 2,
				"" + secondaryNumbers[4]);
		byte nextMode = (byte) (Math.random() * 1);
		String operationSymbolLeft2, operationSymbolRight2;
		int operand4, operand5, operand6;
		switch (nextMode) {
		case 0:// This is multiplication mode A
			operationSymbolLeft2 = " * ";
			operand4 = (int) (Math.random() * 10) + 1;
			operand5 = (int) (Math.random() * 10) + 1;
			if (operand4 * operand5 > secondaryNumbers[0]) {
				operationSymbolRight2 = " - ";
				operand6 = operand4 * operand5 - secondaryNumbers[0];
			} else {
				operationSymbolRight2 = " + ";
				operand6 = secondaryNumbers[0] - operand4 * operand5;
			}
			secondaryExpression = "" + operand4 + operationSymbolLeft2 + operand5 + operationSymbolRight2 + operand6;
		}
		secondaryExpressionAvailable = true;
		Pane primaryPane = new Pane(leftBoundary, topBoundary, rightBoundary, bottomBoundary, wall1, wall2, wall3,
				wall4, door, coin1, coin2, coin3, coin4, fish, number1, number2, number3, number4, answer);
		Scene myScene = new Scene(primaryPane);
		System.out.println("Elburn Days");
		myScene.setFill(Color.AQUA);
		primaryStage.setScene(myScene);
		primaryStage.show();
		Stage secondaryStage = new Stage();
		Pane secondaryPane = new Pane(_leftBoundary, _topBoundary, _rightBoundary, _bottomBoundary, wall5, wall6, wall7,
				wall8, otherDoor, coin5, coin6, coin7, coin8, nextFish, number5, number6, number7, number8, answer2);
		Scene nextScene = new Scene(secondaryPane);
		nextScene.setFill(Color.AQUA);
		secondaryStage.setScene(nextScene);
		myScene.setOnKeyPressed(e -> {
			if (primaryExpressionAvailable) {
				JOptionPane.showMessageDialog(null, primaryExpression);
				primaryExpressionAvailable = false;
			}
			switch (e.getCode()) {
			case UP:
				fish.setHeight(40);
				fish.setWidth(8);
				fish.setY(fish.getY() - 3);
				break;
			case DOWN:
				fish.setHeight(40);
				fish.setWidth(8);
				fish.setY(fish.getY() + 3);
				break;
			case LEFT:
				fish.setHeight(8);
				fish.setWidth(40);
				fish.setX(fish.getX() - 3);
				break;
			case RIGHT:
				fish.setHeight(8);
				fish.setWidth(40);
				fish.setX(fish.getX() + 3);
				break;
			}
			switch (e.getCode()) {
			case UP:
			case DOWN:
			case LEFT:
			case RIGHT:
				w = 0;
				while (w < 4) {
					if (fish.getX() + (fish.getWidth() / 2.0) > primaryWalls.get(w).getX()
							&& fish.getX() + (fish.getWidth() / 2.0) < primaryWalls.get(w).getX()
									+ primaryWalls.get(w).getWidth()
							&& fish.getY() + (fish.getHeight() / 2.0) > primaryWalls.get(w).getY()
							&& fish.getY() + (fish.getHeight() / 2.0) < primaryWalls.get(w).getY()
									+ primaryWalls.get(w).getHeight()) {
						gameActive = false;
						JOptionPane.showMessageDialog(null, "You touched the incorrect door!");
					}
					if (fish.getX() + (fish.getWidth() / 2.0) > primaryCoins.get(w).getCenterX() - 30
							&& fish.getX() + (fish.getWidth() / 2.0) < primaryCoins.get(w).getCenterX() + 30
							&& fish.getY() + (fish.getHeight() / 2.0) > primaryCoins.get(w).getCenterY() - 30
							&& fish.getY() + (fish.getHeight() / 2.0) < primaryCoins.get(w).getCenterY() + 30) {
						coinSelected[w] = true;
						switch (w) {
						case 0:
							if (coinMatched[w])
								break;
							if (coinSelected[w + 4]) {
								JOptionPane.showMessageDialog(null, "You matched up the red coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							primaryCoins.get(w).setFill(Color.RED);
							for (int q = 0; q < 8; ++q)
								if (q != w) {
									if (coinSelected[q] && coinTurned[w] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						case 1:
							if (coinMatched[w])
								break;
							if (coinSelected[w + 4]) {
								JOptionPane.showMessageDialog(null, "You matched up the orange coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							primaryCoins.get(w).setFill(Color.ORANGE);
							for (int q = 0; q < 8; ++q)
								if (q != w) {
									if (coinSelected[q] && coinTurned[w] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						case 2:
							if (coinMatched[w])
								break;
							if (coinSelected[w + 4]) {
								JOptionPane.showMessageDialog(null, "You matched up the green coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							primaryCoins.get(w).setFill(Color.GREEN);
							for (int q = 0; q < 8; ++q)
								if (q != w) {
									if (coinSelected[q] && coinTurned[w] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						case 3:
							if (coinMatched[w])
								break;
							if (coinSelected[w + 4]) {
								JOptionPane.showMessageDialog(null, "You matched up the white coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							primaryCoins.get(w).setFill(Color.WHITE);
							for (int q = 0; q < 8; ++q)
								if (q != w) {
									if (coinSelected[q] && coinTurned[w] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
										--numberOfLives;
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						}
						coinTurned[w] = true;
					}
					++w;
				}
			}
			if (!gameActive) {
				primaryStage.close();
				start(primaryStage);
			}
			boolean roundSolved = true;
			for (int i = 0; i < coinMatched.length; ++i)
				if (!coinMatched[i])
					roundSolved = false;
			if (roundSolved) {
				JOptionPane.showMessageDialog(null, "Congratulations! You have matched up all the coins!");
				primaryStage.close();
				start(primaryStage);
			}
		});
		nextScene.setOnKeyPressed(e -> {
			if (secondaryExpressionAvailable) {
				JOptionPane.showMessageDialog(null, secondaryExpression);
				secondaryExpressionAvailable = false;
			}
			switch (e.getCode()) {
			case UP:
				nextFish.setHeight(40);
				nextFish.setWidth(8);
				nextFish.setY(nextFish.getY() - 3);
				break;
			case DOWN:
				nextFish.setHeight(40);
				nextFish.setWidth(8);
				nextFish.setY(nextFish.getY() + 3);
				break;
			case LEFT:
				nextFish.setHeight(8);
				nextFish.setWidth(40);
				nextFish.setX(nextFish.getX() - 3);
				break;
			case RIGHT:
				nextFish.setHeight(8);
				nextFish.setWidth(40);
				nextFish.setX(nextFish.getX() + 3);
				break;
			}
			switch (e.getCode()) {
			case UP:
			case DOWN:
			case LEFT:
			case RIGHT:
				w = 0;
				while (w < 4) {
					if (nextFish.getX() + (nextFish.getWidth() / 2.0) > secondaryWalls.get(w).getX()
							&& nextFish.getX() + (nextFish.getWidth() / 2.0) < secondaryWalls.get(w).getX()
									+ secondaryWalls.get(w).getWidth()
							&& nextFish.getY() + (nextFish.getHeight() / 2.0) > secondaryWalls.get(w).getY()
							&& nextFish.getY() + (nextFish.getHeight() / 2.0) < secondaryWalls.get(w).getY()
									+ secondaryWalls.get(w).getHeight()) {
						gameActive = false;
						JOptionPane.showMessageDialog(null, "You touched the incorrect door!");
					}
					if (nextFish.getX() + (nextFish.getWidth() / 2.0) > secondaryCoins.get(w).getCenterX() - 30
							&& nextFish.getX() + (nextFish.getWidth() / 2.0) < secondaryCoins.get(w).getCenterX() + 30
							&& nextFish.getY() + (nextFish.getHeight() / 2.0) > secondaryCoins.get(w).getCenterY() - 30
							&& nextFish.getY() + (nextFish.getHeight() / 2.0) < secondaryCoins.get(w).getCenterY()
									+ 30) {
						coinSelected[w + 4] = true;
						switch (w) {
						case 0:
							if (coinMatched[w])
								break;
							if (coinSelected[w]) {
								JOptionPane.showMessageDialog(null, "You matched up the red coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							System.out.println("sec red");
							secondaryCoins.get(w).setFill(Color.RED);
							for (int q = 0; q < 8; ++q)
								if (q != w + 4) {
									System.out.println(coinTurned[q]);
									if (coinSelected[q] && coinTurned[w + 4] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						case 1:
							if (coinMatched[w])
								break;
							if (coinSelected[w]) {
								JOptionPane.showMessageDialog(null, "You matched up the orange coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							System.out.println("sec orange");
							secondaryCoins.get(w).setFill(Color.ORANGE);
							for (int q = 0; q < 8; ++q)
								if (q != w + 4) {
									System.out.println(coinTurned[q]);
									if (coinSelected[q] && coinTurned[w + 4] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						case 2:
							if (coinMatched[w])
								break;
							if (coinSelected[w]) {
								JOptionPane.showMessageDialog(null, "You matched up the green coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							System.out.println("sec green");
							secondaryCoins.get(w).setFill(Color.GREEN);
							for (int q = 0; q < 8; ++q)
								if (q != w + 4) {
									System.out.println(coinTurned[q]);
									if (coinSelected[q] && coinTurned[w + 4] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						case 3:
							if (coinMatched[w])
								break;
							if (coinSelected[w]) {
								JOptionPane.showMessageDialog(null, "You matched up the white coins!");
								primaryCoins.get(w).setFill(Color.AQUA);
								secondaryCoins.get(w).setFill(Color.AQUA);
								coinMatched[w] = true;
								break;
							}
							System.out.println("sec white");
							secondaryCoins.get(w).setFill(Color.WHITE);
							for (int q = 0; q < 8; ++q)
								if (q != w + 4) {
									System.out.println(coinTurned[q]);
									if (coinSelected[q] && coinTurned[w + 4] && !coinMatched[q % 4]) {
										gameActive = false;
										JOptionPane.showMessageDialog(null,
												"The last coin you turned does not match this one.");
									}
									coinSelected[q] = false;
									if (!coinMatched[q % 4])
										if (q < 4)
											primaryCoins.get(q).setFill(Color.LIGHTGRAY);
										else
											secondaryCoins.get(q - 4).setFill(Color.LIGHTGRAY);
								}
							break;
						}
						coinTurned[w + 4] = true;
					}
					++w;
				}
			}
			boolean roundSolved = true;
			for (int i = 0; i < coinMatched.length; ++i)
				if (!coinMatched[i])
					roundSolved = false;
			if (roundSolved) {
				JOptionPane.showMessageDialog(null, "Congratulations! You have matched up all the coins!");
				secondaryStage.close();
				start(primaryStage);
			}
		});
		System.out.println("Wide ruled");
		myScene.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.O) {
				if (fish.getX() + fish.getWidth() / 2.0 > door.getX()
						&& fish.getX() + fish.getWidth() / 2.0 < door.getX() + door.getWidth()
						&& fish.getY() + fish.getHeight() / 2.0 > door.getY()
						&& fish.getY() + fish.getHeight() / 2.0 < door.getY() + door.getHeight()) {
					primaryStage.close();
					secondaryStage.show();
				}
			} else if (e.getCode() == KeyCode.X) {
				primaryStage.close();
				JOptionPane.showMessageDialog(null, "You have pressed the forbidden key!");
				--numberOfLives;
				JOptionPane.showMessageDialog(null, "You have " + numberOfLives + " lives left.");
				start(primaryStage);
			}
		});
		nextScene.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.O) {
				if (nextFish.getX() + nextFish.getWidth() / 2.0 > otherDoor.getX()
						&& nextFish.getX() + nextFish.getWidth() / 2.0 < otherDoor.getX() + otherDoor.getWidth()
						&& nextFish.getY() + nextFish.getHeight() / 2.0 > otherDoor.getY()
						&& nextFish.getY() + nextFish.getHeight() / 2.0 < otherDoor.getY() + otherDoor.getHeight()) {
					secondaryStage.close();
					primaryStage.show();
				}
			} else if (e.getCode() == KeyCode.X) {
				secondaryStage.close();
				JOptionPane.showMessageDialog(null, "You have pressed the forbidden key!");
				--numberOfLives;
				JOptionPane.showMessageDialog(null, "You have " + numberOfLives + " lives left.");
				start(primaryStage);
			}
		});
	}

	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "Press the 'O' key to open the door.");
		Application.launch(args);
		score = 0;
	}
}
