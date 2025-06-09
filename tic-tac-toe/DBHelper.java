import java.sql.*;

public class DBHelper {
    static final String URL = "jdbc:postgresql://localhost:5432/tic-tac-toe";
    static final String USER = "postgres"; // replace with your DB user
    static final String PASS = "postgres"; // replace with your DB password
    static {
        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            System.out.println("✅ PostgreSQL JDBC Driver registered.");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ PostgreSQL Driver not found! Check your classpath.");
            e.printStackTrace();
        }
    }


    private static int currentGameId = -1;
    private static int moveCount = 0;

    public static void startGame() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO game_history DEFAULT VALUES RETURNING game_id")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                currentGameId = rs.getInt("game_id");
                moveCount = 0;
                System.out.println("🎮 New Game Started. Game ID: " + currentGameId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void recordMove(char player, int row, int col) {
        moveCount++;
        int cellIndex = row * 3 + col; // 0 to 8

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO moves (game_id, move_no, player, cell_index) VALUES (?, ?, ?, ?)")) {
            ps.setInt(1, currentGameId);
            ps.setInt(2, moveCount);
            ps.setString(3, String.valueOf(player));
            ps.setInt(4, cellIndex);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     public static void updateGameStats(Character winner) {
        // --- Part 1: Update winner in game_history table (per-game outcome) ---
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE game_history SET winner = ? WHERE game_id = ?")) {

            if (winner == null) {
                ps.setNull(1, java.sql.Types.CHAR); // Set SQL NULL for a draw
                System.out.println("📊 Game ID " + currentGameId + ": Result saved as Draw.");
            } else {
                ps.setString(1, String.valueOf(winner)); // Set 'X' or 'O' for winner
                System.out.println("📊 Game ID " + currentGameId + ": Winner saved as " + winner + ".");
            }
            ps.setInt(2, currentGameId); // Use the current game's ID

            int updatedRows = ps.executeUpdate();
            if (updatedRows == 0) {
                System.err.println("⚠️ Warning: No rows updated in game_history for Game ID " + currentGameId);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error updating game history with winner.");
            e.printStackTrace();
        }
    }
}