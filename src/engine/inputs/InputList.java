package inputs;

public interface InputList {

    void keyDown(int key, int mods);

    void keyPressed(int key, int mods);

    void keyReleased(int key, int mods);

    void mouseDown(int button, int mods);

    void mousePressed(int button, int mods);

    void mouseReleased(int button, int mods);
}
