package davidius.orchestratorservice.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class helloWorldControler {



    @GetMapping("/deploy")
    public ResponseEntity<String> deployHelloWorld() {
        try {
            // Commande Docker (doit tourner sur une machine avec Docker installé)
            ProcessBuilder pb = new ProcessBuilder("docker", "run", "--rm", "hello-world");
            pb.redirectErrorStream(true);
            Process process = pb.start();

            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                return ResponseEntity.ok("Déploiement réussi !\n" + output);
            } else {
                return ResponseEntity.status(500).body("Erreur au déploiement\n" + output);
            }

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Exception: " + e.getMessage());
        }
    }
}
