package moneycalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Calculate {
    private final double amount;
    private final String currencyFrom;
    private final String currencyTo;
    
    public Calculate (double amount, String currencyFrom, String currencyTo) {
        this.amount = amount;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
    }
    
    public double calculate() throws MalformedURLException, IOException {
        URL url = new URL("http://api.fixer.io/latest?base=" + currencyFrom + "&symbols=" + currencyTo);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStreamReader input = new InputStreamReader(connection.getInputStream());
        try (BufferedReader reader = new BufferedReader(input)){
            String line = reader.readLine();
            line = line.substring(line.indexOf(currencyTo)+5, line.indexOf("}"));
            return amount*Double.parseDouble(line);
        }
    }
}
