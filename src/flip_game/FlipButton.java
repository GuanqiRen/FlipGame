package flip_game;

import java.util.Vector;

import javafx.scene.control.Button;

public class FlipButton extends Button{
    final String WHITE_STYLE = "-fx-background-color:white; -fx-border-color:lightgrey; -fxborder-width: 5px";
    final String BLACK_STYLE = "-fx-background-color:black";
    
    private boolean flipped = false;
    private Vector<FlipButton> neighbors = new Vector<FlipButton>();
    
    public FlipButton(){
        super();
        setMinSize(50,50);
        reset();
        setOnAction(actionEvent -> {flipNeighbors();});
    }

    public void reset() {
        flipped = false;
        setStyle(WHITE_STYLE);
    }

    public void flip() {
        flipped = !flipped;
        if (flipped) {
            setStyle(BLACK_STYLE);
        } else {
            setStyle(WHITE_STYLE);
        }
    }

    public void flipNeighbors() {
        flip();
        for (FlipButton button: neighbors) {
            button.flip();
        }
    }

    public void addNeighbor(FlipButton button) {
        neighbors.add(button);
    }

}