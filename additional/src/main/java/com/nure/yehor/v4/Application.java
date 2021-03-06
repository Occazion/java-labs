package com.nure.yehor.v4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {

    private final Map<String, String> regex;

    public Application() {
        regex = new LinkedHashMap<>();
        regex.put("bool", "true|false");
        regex.put("char", "[A-Za-z]{1}");
        regex.put("int", "^-?\\d+$");
        regex.put("float", "^-?\\d+\\.\\d+$");
        regex.put("string", "\\w{2,}");
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.open("additional/v4.txt", true);
    }

    public void open(String filepath, boolean append) {
        try (FileWriter writer = new FileWriter(filepath, append);
             Scanner sc = new Scanner(System.in)) {
            while (true) {
                String line = sc.nextLine();
                if (line.equals("exit")) {
                    break;
                }
                if (isType(line)) {
                    writer.write(line);
                    writer.append(System.lineSeparator());
                    writer.flush();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private boolean isType(String data) {
        for (Map.Entry<String, String> entry : regex.entrySet()) {
            if (data.matches(entry.getValue())) {
                System.out.println("It's a " + entry.getKey());
                return true;
            }
        }
        System.out.println("Unknown type");
        return false;
    }

}
