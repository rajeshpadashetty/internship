import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// =========================
// Note Class (Data Model)
// =========================
class Note {
    private String title;
    private String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nContent: " + content;
    }
}

// =========================
// NotesManager Class (Handles File I/O + Logic)
// =========================
class NotesManager {
    private ArrayList<Note> notes;
    private final String FILE_NAME = "notes.txt";

    public NotesManager() {
        notes = new ArrayList<>();
        loadNotes(); // Load saved notes at startup
    }

    // Add a new note
    public void addNote(String title, String content) {
        notes.add(new Note(title, content));
        saveNotes();
        System.out.println("✅ Note added successfully!");
    }

    // View all notes
    public void viewNotes() {
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        }
        System.out.println("\n--- Notes List ---");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i).getTitle());
        }
    }

    // View a specific note by index
    public void viewNoteDetails(int index) {
        if (index < 0 || index >= notes.size()) {
            System.out.println("❌ Invalid note number!");
            return;
        }
        System.out.println("\n" + notes.get(index));
    }

    // Delete a note
    public void deleteNote(int index) {
        if (index < 0 || index >= notes.size()) {
            System.out.println("❌ Invalid note number!");
            return;
        }
        Note removed = notes.remove(index);
        saveNotes();
        System.out.println("🗑️ Note '" + removed.getTitle() + "' deleted successfully!");
    }

    // Save notes to file
    private void saveNotes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Note note : notes) {
                writer.write(note.getTitle() + "|" + note.getContent());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Error saving notes: " + e.getMessage());
        }
    }

    // Load notes from file
    private void loadNotes() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return; // No saved notes yet

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|", 2);
                if (parts.length == 2) {
                    notes.add(new Note(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error loading notes: " + e.getMessage());
        }
    }
}

// =========================
// Main Class (User Interface)
// =========================
public class NotesApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NotesManager manager = new NotesManager();
        int choice;

        do {
            System.out.println("\n===== Notes App =====");
            System.out.println("1. Add Note");
            System.out.println("2. View All Notes");
            System.out.println("3. View Note Details");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Ensure valid numeric input
            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter note title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter note content: ");
                    String content = sc.nextLine();
                    manager.addNote(title, content);
                }
                case 2 -> manager.viewNotes();
                case 3 -> {
                    manager.viewNotes();
                    System.out.print("Enter note number to view: ");
                    int index = sc.nextInt() - 1;
                    manager.viewNoteDetails(index);
                }
                case 4 -> {
                    manager.viewNotes();
                    System.out.print("Enter note number to delete: ");
                    int index = sc.nextInt() - 1;
                    manager.deleteNote(index);
                }
                case 5 -> System.out.println("💾 Exiting... Notes saved successfully!");
                default -> System.out.println("❌ Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
