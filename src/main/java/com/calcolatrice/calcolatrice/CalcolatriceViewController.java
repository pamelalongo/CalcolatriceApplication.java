package com.calcolatrice.calcolatrice;

import com.fathzer.soft.javaluator.DoubleEvaluator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/*
 logaritmo base 10 è log
 logaritmo naturale è ln
 esponenziale è ^
 modulo è %
 */

public class CalcolatriceViewController {

    @FXML
    private Label displayLabel;

    private static final String ZERO = "0";
    private static final String ERROR_DIV_BY_0 = "Cannot divide by 0";
    private static final String ERROR_SYNTAX = "wrong syntax";
    private boolean isLastDot = false;
    private boolean isResult = false;

    public void initialize(){
        displayLabel.setText(ZERO);
        displayLabel.setAlignment(Pos.CENTER_RIGHT);
    }

    public void insertOperator(ActionEvent event){
        String newOperator = ((Button) event.getSource()).getText();
        if(!isOperator(getLastChar())){
            displayLabel.setText(displayLabel.getText()+newOperator);
            isLastDot = false;
            isResult = false;
        }
    }

    public void insertZero(ActionEvent event){
        insertNumb(event);
    }

    public void insertDot(){
        if(!isLastDot && !isOperator(getLastChar())){
            displayLabel.setText(displayLabel.getText()+".");
            isLastDot = true;
        }
    }

    public void insertNumb(ActionEvent actionEvent) {
        String newNumber = ((Button) actionEvent.getSource()).getText();

        if(displayLabel.getText().equals(ZERO) || displayLabel.getText().equals(ERROR_DIV_BY_0)
        || displayLabel.getText().equals(ERROR_SYNTAX) || isResult){
            displayLabel.setText(newNumber);
            isResult = false;
        }
        else{
            displayLabel.setText(displayLabel.getText()+newNumber);
        }
    }

    public void deleteExpr() {
        isLastDot = false;
        displayLabel.setText(ZERO);
    }

    public void insertEqual() {
        if(getLastChar().equals(".")){
            displayLabel.setText(displayLabel.getText()+"0");
        }

        if(isOperator(getLastChar())){
            displayLabel.setText(ERROR_SYNTAX);
            return;
        }

        double result = new DoubleEvaluator().evaluate(displayLabel.getText());

        if(result == Double.POSITIVE_INFINITY){
            displayLabel.setText(ERROR_DIV_BY_0);
        }
        else {
            if((result % (1.0)) == 0){
                displayLabel.setText("" + (int) result);
            }
            else {
                displayLabel.setText("" + result);
            }
        }
        isLastDot = false;
        isResult = true;
    }

    public void insertExponential() {
        displayLabel.setText(displayLabel.getText() + "^");
        isLastDot = false;
        isResult = false;
    }

    public void insertLogarithm() {
        String number = displayLabel.getText(); // Ottiene il numero dalla Label
        if (!number.isEmpty()) {
            double num = Double.parseDouble(number);
            double result = Math.log10(num);
            displayLabel.setText("log " + num + " = " + result); // Mostra il risultato nella Label
            isLastDot = false;
            isResult = true;
        } else {
            // Messaggio di errore se il campo di testo è vuoto
            displayLabel.setText("Please enter a number");
        }
    }



    public String getLastChar(){
        return displayLabel.getText().substring(displayLabel.getText().length()-1);
    }

    public boolean isOperator(String lastChar){
        return switch (lastChar) {
            case "+", "/", "*", "-","^","log" -> true;
            default -> false;
        };
    }
}
