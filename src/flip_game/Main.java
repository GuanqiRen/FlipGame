package flip_game;

import java.util.Vector;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class Main extends Application {
    
    private Vector<Vector<FlipButton>> benchmark = new Vector<Vector<FlipButton>>();
    private Vector<Vector<FlipButton>> solution = new Vector<Vector<FlipButton>>();
    private final int n=8;

    private void reset(Vector<Vector<FlipButton>> table) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                table.get(i).get(j).reset();
            }
        }
    }

    private void createTable(Vector<Vector<FlipButton>> table) {
        for (int i = 0; i < n; ++i) {
            Vector<FlipButton> v = new Vector<FlipButton>(n);
            for (int j = 0; j < n; ++j) {
                v.add(new FlipButton());
            }
            table.add(v);
        }
    }

    private GridPane createGrid(Vector<Vector<FlipButton>> table) {
        createTable(table);
        GridPane gridPane = new GridPane();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                gridPane.add(table.get(i).get(j), i , j);
            }
        }
        return gridPane;
    }

    private void addNeighbor(Vector<Vector<FlipButton>> table) {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - 1 >= 0) {
                    table.get(i).get(j).addNeighbor(table.get(i-1).get(j));
                } 
                if (i + 1 < n) {
                    table.get(i).get(j).addNeighbor(table.get(i+1).get(j));
                }
                if (j - 1 >= 0) {
                    table.get(i).get(j).addNeighbor(table.get(i).get(j-1));
                }
                if (j + 1 < n) {
                    table.get(i).get(j).addNeighbor(table.get(i).get(j+1));
                }
            }
        }
    }

    private HBox createControlPane() {
        HBox controlPane = new HBox();
        Button resetSolutionBtn = new Button("Reset Solution");
        resetSolutionBtn.setOnAction(actionEvent -> {reset(solution);});
        Button resetBenchmarkBtn = new Button("Reset Benchmark");
        resetBenchmarkBtn.setOnAction(actionEvent -> {reset(benchmark);});
        controlPane.getChildren().addAll(resetBenchmarkBtn, resetSolutionBtn);
        controlPane.setAlignment(Pos.CENTER);
        return controlPane;
    }

    @Override
    public void start(Stage stage) {
        GridPane benchmarkPane = createGrid(benchmark);
        GridPane solutionPane = createGrid(solution);
        addNeighbor(solution);

        BorderPane rootPane = new BorderPane();
        
        rootPane.setLeft(benchmarkPane);
        rootPane.setRight(solutionPane);
        rootPane.setBottom(createControlPane());

        Scene scene = new Scene(rootPane, 900, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}