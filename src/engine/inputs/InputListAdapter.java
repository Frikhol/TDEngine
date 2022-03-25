package inputs;

public interface InputListAdapter extends InputList {
    @Override
    default void keyDown(int key, int mods){

    }

    @Override
    default void keyPressed(int key, int mods){

    }

    @Override
    default void keyReleased(int key, int mods){

    }

    @Override
    default void mouseDown(int button, int mods){

    }

    @Override
    default void mousePressed(int button, int mods){

    }

    @Override
    default void mouseReleased(int button, int mods){

    }

}
