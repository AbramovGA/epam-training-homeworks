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

    public static void main(String[] args) {
        var notebook = new Notebook();
        notebook.add("aaa");
        notebook.add("aab");
        notebook.add("aac");
        notebook.getAll();
        notebook.remove(1);
        notebook.getAll();
        notebook.edit(1, "edited note");
        notebook.getAll();

        for (int i = 0; i < 100; i++)
            notebook.add(String.valueOf(i));


    }

    public void add(String note) {

        notes[size] = new Note(note);
        size++;
        rebuildArray();

    }

    public void remove(int index) throws IllegalArgumentException {

        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException();

        System.arraycopy(notes, index + 1, notes, index, size - 1 - index);

        size--;
    }

    public void edit(int index, String note) throws IllegalArgumentException {

        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException();

        notes[index].setNote(note);
    }

    public void getAll() {
        for (int i = 0; i < size; i++) {
            System.out.println(notes[i].getNote());
        }
    }

    private void rebuildArray() {
        if (size > capacity * THRESHOLD) {
            capacity *= CAPACITY_FACTOR;
            Note[] temp = notes;
            notes = new Note[capacity];
            for (int i = 0; i < temp.length; i++) {
                notes[i] = temp[i];
            }
        }
    }

    class Note {
        String note;

        public Note(String note) {
            this.note = note;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }

}
