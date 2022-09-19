// create Command interface and Executor class here
@FunctionalInterface
interface Command {
    void execute();
}

class Executor {
    public void executeCommand(Command command) {
        command.execute();
    }
}

class Application2 {

    private final Executor executor;
    private final Editor editor;

    public Application2(Executor executor, Editor editor) {
        this.executor = executor;
        this.editor = editor;
    }

    void run() {
        // write your code here
        executor.executeCommand(editor::selectAll);
        executor.executeCommand(() -> editor.saveToClipboard(editor.getSelection()));
        executor.executeCommand(() -> editor.replaceSelection(editor.getClipboard()));
    }
}

interface Editor {

    /**
     * Select all text.
     */
    void selectAll();

    /**
     * Returns previously selected text.
     */
    String getSelection();

    /**
     * Replaces selected text with the specified value.
     *
     * @param value the text that replaces selected text
     */
    void replaceSelection(String value);

    /**
     * Returns text in the clipboard.
     */
    String getClipboard();

    /**
     * Saves provided value to the clipboard.
     *
     * @param value the text that will be saved to the clipboard
     */
    void saveToClipboard(String value);
}

class Exec {
    public static void main(String[] args) {
        //implementation unknown
    }
}