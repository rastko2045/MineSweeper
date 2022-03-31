package gui;

import logic.Game;

import javax.swing.*;
import java.awt.*;

public class CustomOptionsDialog extends JDialog {

    private JSpinner rowsField, columnsField, minesField;
    private JLabel rowsLabel, columnsLabel, minesLabel;
    private JButton cancelButton, setButton;


    public CustomOptionsDialog(Game gameInstance, GUI gui) {
        super(gui, true);
        setTitle("Custom Options");
        setLayout(new GridLayout(0, 2));
        SpinnerNumberModel rowsModel = new SpinnerNumberModel(gameInstance.getBoard().getROWS(), 6, 20, 1);
        SpinnerNumberModel columnsModel = new SpinnerNumberModel(gameInstance.getBoard().getCOLS(), 7, 40, 1);
        SpinnerNumberModel minesModel = new SpinnerNumberModel(gameInstance.getBoard().getCOLS(), 0, 40 * 20, 1);
        rowsField = new JSpinner(rowsModel);
        rowsLabel = new JLabel("Rows:");
        columnsField = new JSpinner(columnsModel);
        columnsLabel = new JLabel("Columns:");
        minesField = new JSpinner(minesModel);
        minesLabel = new JLabel("Number of Mines:");

        cancelButton = new JButton("Cancel");
        setButton = new JButton("Set");
        cancelButton.addActionListener(e -> dispose());
        setButton.addActionListener(e -> {
            dispose();
            int minesNumber = (Integer) minesField.getValue();
            int rows = (Integer) rowsField.getValue();
            int cols = (Integer) columnsField.getValue();
            if (minesNumber > rows * cols) {
                minesNumber = rows * cols;
            }
            gameInstance.setCustomGame(rows, cols, minesNumber);
        });
        add(rowsLabel);
        add(rowsField);
        add(columnsLabel);
        add(columnsField);
        add(minesLabel);
        add(minesField);
        add(setButton);
        add(cancelButton);
        pack();
        setVisible(true);
    }
}
