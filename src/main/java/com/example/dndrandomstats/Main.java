package com.example.dndrandomstats;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class Main extends Application {
	
	int currentCols = 0;
	int currentRows = 0;
	Text[] stats = new Text[6];
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Random Stat Generator");
			GridPane grid = new GridPane();
			//initialize buttons and text objects
			Font standard = Font.font("Tahoma", FontWeight.NORMAL, 20);

			Text rollText = new Text("Roll for Stats");
			grid.setHalignment(rollText,HPos.CENTER);
			Text statText = new Text("You Rolled:");
			grid.setHalignment(statText,HPos.CENTER);
			rollText.setFont(standard);
			statText.setFont(standard);

			Button roll = new Button();
			roll.setText("Roll the Dice");
			grid.setHalignment(roll,HPos.CENTER);
			Button mulligan = new Button();
			mulligan.setText("Mulligan");
			grid.setHalignment(mulligan,HPos.CENTER);

			//both event handlers

			//generates the larger grid and displays stats
			roll.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					generateGrid(grid, 3, 9);
					grid.getChildren().remove(rollText);
					grid.getChildren().remove(roll);
					grid.add(statText, 1, 0);
					stats = toText(generateStats());
					for(int i = 0; i < stats.length; i++) {
						grid.add(stats[i], 1, i+1);
						grid.setHalignment(stats[i], HPos.CENTER);
					}
					grid.add(mulligan, 1,7);
				}
			});

			// adds the EventHandler to enable the mulligan button to re-roll stats
			// replaces the current stats with the new stats
			mulligan.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					int[] statsHolder = generateStats();
					statText.setText("You rerolled:");
					statText.setTextAlignment(TextAlignment.CENTER);
					for(int i = 0; i < stats.length; i++) {
						stats[i].setText(String.valueOf(statsHolder[i]));

					}
					mulligan.setDisable(true);
					
				}
			});

			//generates the grid for the first window
			generateGrid(grid,3,3);
			grid.add(rollText, 1, 0);
			grid.add(roll, 1, 1);
			Scene scene = new Scene(grid,400,400);
			scene.getStylesheets().add(String.valueOf(getClass().getResource("stylesheet.css")));


			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// each if statement checks whether the current number of rows or cols is equal to the new number
    // if it is less than, it sets the constraints for the current rows or cols and adds the
	// additional rows or col needed to meet the input number
	public void generateGrid(GridPane grid, int cols, int rows) {
		//compares current cols to needed cols
		if(currentCols < cols) {
			//sets the current cols to the wid of the new cols
			double percent = 100.0 / cols;
			for(int i = 0; i < currentCols; i++) {
				ColumnConstraints colConst = new ColumnConstraints();
				colConst.setPercentWidth(percent);
				grid.getColumnConstraints().set(i, colConst);
			}
			// adds any additional needed cols
			for (int i = currentCols; i < cols; i++) {
				ColumnConstraints colConst = new ColumnConstraints();
				colConst.setPercentWidth(percent);
				grid.getColumnConstraints().add(colConst);
			}
		}
		currentCols = cols;
		if(currentRows < rows) {
			double percent = 100.0 / rows;
			for(int i = 0; i < currentRows; i++) {
				RowConstraints rowConst = new RowConstraints();
				rowConst.setPercentHeight(percent);
				grid.getRowConstraints().set(i, rowConst);
			}
			for(int i = currentRows; i < rows; i++) {
				RowConstraints rowConst = new RowConstraints();
				rowConst.setPercentHeight(percent);
				grid.getRowConstraints().add(rowConst);
			}
		}
		currentRows = rows;

	}
	
	public int[] generateStats() {
		StatBlock stats = new StatBlock();
		stats.generateStats();
		return stats.toArray();
	}

	public Text[] toText(int[] array) {
		Text[] output = new Text[6];
		for(int i = 0; i < output.length; i++) {
			output[i] = new Text(String.valueOf(array[i]));
		}
		return output;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
