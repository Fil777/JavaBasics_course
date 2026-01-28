package fil.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class StartProcess {

    public void StartProcess() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("dir", "/B")
                .directory(new File("c:\\Temp"))
                .redirectInput(ProcessBuilder.Redirect.DISCARD)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.INHERIT);

        Process process = new ProcessBuilder.start();

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
            reader.lines().forEach(System.out::println);
        }

        int exitValue = process.waitFor();
        if (exitValue != 0) {
            System.err.println("Subprocess aborted");
        }
    }
}
