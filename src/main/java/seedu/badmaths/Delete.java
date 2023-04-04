package seedu.badmaths;
import seedu.badmaths.ui.Ui;

public class Delete {
    private static final String filePath = "data/notes.txt";
    public static NotesList notes;
    protected static String toDo;

    public Delete (NotesList notes, String toDo) {
        this.toDo = toDo;
        this.notes = notes;
    }

    public static boolean isInvalidIndex(int index, NotesList notes) {
        return (index < 0 || index >= notes.getSize());
    }

    public static boolean isAnInt(String todo) {
        try {
            Integer.parseInt(todo);
        } catch (NumberFormatException numberException) {
            Ui.printInvalidNumberEntered();
            return false;
        }
        return true;
    }

    public static void deleteNotes() {
        try {
            if (isAnInt(toDo) == false) {
                throw new IllegalTodoException();
            }
            int deleteIndex = Integer.parseInt(toDo) - 1; // deleteIndex == 3
            if (isInvalidIndex(deleteIndex, notes) == true) { // if true
                throw new IllegalIndexException();
            }
            Ui.printDelete(notes.getText(deleteIndex), notes.getSize());
            notes.remove(deleteIndex);
        } catch (IllegalIndexException exceptionIndex) {
            System.out.println("Oops! This note does not exist. Please try again.");
        } catch (IllegalTodoException exceptionTodo) {
            Ui.printIncorrectFormatEntered();
        }
    }
}
