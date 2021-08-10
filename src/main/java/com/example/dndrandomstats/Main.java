package com.example.dndrandomstats;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
	boolean usedMulligan = false;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Random Stat Generator");
			GridPane grid = new GridPane();
			Text rollText = new Text("Roll for Stats");
			Text statText = new Text("You Rolled:");
			rollText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
			Button roll = new Button();
			roll.setText("Roll the Dice");
			Button mulligan = new Button();
			mulligan.setText("Mulligan");
			mulligan.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					int[] statsHolder = generateStats();
					statText.setText("You rerolled:");
					for(int i = 0; i < stats.length; i++) {
						stats[i].setText(String.valueOf(statsHolder[i]));
					}
					mulligan.setDisable(true);
					
				}
			});
			roll.setOnAction(new EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e) {
					generateGrid(grid, 3, 8);
					grid.getChildren().remove(rollText);
					grid.getChildren().remove(roll);
					grid.add(statText, 1, 0);
					stats = toText(generateStats());
					for(int i = 0; i < stats.length; i++) {
						grid.add(stats[i], 1, i+1);
					}
					grid.add(mulligan, 1,7);
				}
			});
			generateGrid(grid,3,3);
			grid.add(rollText, 1, 0);
			grid.add(roll, 1, 1);
			Scene scene = new Scene(grid,400,400);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	// each if statment checks if the curent number of rows or cols is equal to the new number
    // if it is less than, it sets the constrains for the current rows or cols and adds the 
	// additional rows or col needed to meet the input number
	public void generateGrid(GridPane grid, int cols, int rows) {
		
		grid.setGridLinesVisible(true);
		final int numCols = cols;
		final int numRows = rows;
		//compares curent cols to needed cols
		if(currentCols < numCols) {
			//sets the current cols to the wid of the new cols
			for(int i = 0; i < currentRows; i++) {
				RowConstraints rowConst = new RowConstraints();
				rowConst.setPercentHeight(100/numRows);
				grid.getRowConstraints().set(i, rowConst);
			}
			// adds any additional needed cols
			for (int i = currentCols; i < numCols; i++) {
				ColumnConstraints colConst = new ColumnConstraints();
				colConst.setPercentWidth(100/numCols);
				grid.getColumnConstraints().add(colConst);
			}
		}
		currentCols = numCols;
		if(currentRows < numRows) {
			for(int i = 0; i < currentRows; i++) {
				RowConstraints rowConst = new RowConstraints();
				rowConst.setPercentHeight(100/numRows);
				grid.getRowConstraints().set(i, rowConst);
			}
			for(int i = currentRows; i < numRows; i++) {
				RowConstraints rowConst = new RowConstraints();
				rowConst.setPercentHeight(100/numRows);
				grid.getRowConstraints().add(rowConst);
			}
		}
		currentRows = numRows;
		//grid.setHgap(25);
		//grid.setVgap(25);
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
