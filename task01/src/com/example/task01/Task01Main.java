package com.example.task01;

import java.io.*;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:

        /*
        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));
        */
    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        String title = null;

        ProcessBuilder pb = new ProcessBuilder();
        pb.command("ffprobe", "-v", "error", "-of", "flat", "-show_format", file.getName());
        pb.directory(file.getParentFile());

        Process process = pb.start();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))){
            String line;
            while((line = br.readLine()) != null){
                if (line.startsWith("format.tags.title")){
                    title = line.split("=")[1].replace("\"", "");
                    break;
                }
            }
        }
        process.waitFor();

        return title;
    }
}
