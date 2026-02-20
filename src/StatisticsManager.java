import java.io.*;
import java.time.LocalDate;

//jatekok mentese es statisztika
public class StatisticsManager {

    private static final String FILE = "stats.txt";

    // PLAYER win / COMPUTER win mentése
    public static void saveGameResult(boolean playerWon) {
        save(playerWon ? "PLAYER" : "COMPUTER");
    }

    // döntetlen mentése
    public static void saveDraw() {
        save("DRAW");
    }

    // közös mentő metódus
    private static void save(String result) {
        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(LocalDate.now() + ";" + result + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // statisztika betöltése
    public static String loadStatistics() {
        File file = new File(FILE);
        if (!file.exists()) {
            return "No games played yet";
        }

        int playerWins = 0;
        int computerWins = 0;
        int draws = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(";PLAYER")) playerWins++;
                else if (line.contains(";COMPUTER")) computerWins++;
                else if (line.contains(";DRAW")) draws++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int total = playerWins + computerWins + draws;
        if (total == 0) {
            return "No games played yet";
        }

        return """
               Games played: %d
               Player wins: %d
               Computer wins: %d
               Draws: %d
               """.formatted(total, playerWins, computerWins, draws);
    }
}
