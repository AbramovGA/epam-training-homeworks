package t06;


public class Notebook {

    private double CAPACITY_FACTOR = 1.5;
    private double THRESHOLD = 0.75;


    private int size = 0;
    private int capacity = 10;
    private Note[] notes;

    public Notebook() {
        notes = new Note[capacity];
    }

    /**
     * Adds a note to the notebook
     *
     * @param note a note to add
     */
    public void add(String note) {

        notes[size] = new Note(note);
        size++;
        rebuildArray();

    }

    /**
     * Removes a note from the notebook
     *
     * @param index index of note to remove
     */
    public void remove(int index) throws IllegalArgumentException {

        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException();

        System.arraycopy(notes, index + 1, notes, index, size - 1 - index);

        size--;
    }

    /**
     * Edits a note in the notebook
     *
     * @param index index of note to edit
     * @param note  a note to replace with
     */
    public void edit(int index, String note) throws IllegalArgumentException {

        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException();

        notes[index].setNote(note);
    }

    /**
     * Prints all existing notes on the screen
     */
    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(notes[i].getNote());
        }
    }

    private void rebuildArray() {
        if (size > capacity * THRESHOLD) {
            capacity *= CAPACITY_FACTOR;
            Note[] temp = notes;
            notes = new Note[capacity];
            System.arraycopy(temp, 0, notes, 0, temp.length);
        }
    }

    class Note {
        String note;

        Note(String note) {
            this.note = note;
        }

        String getNote() {
            return note;
        }

        void setNote(String note) {
            this.note = note;
        }
    }

}
