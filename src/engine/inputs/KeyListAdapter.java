package inputs;

public interface KeyListAdapter extends KeyList{
    @Override
    default void down(int key, int mods){

    }

    @Override
    default void pressed(int key, int mods){

    }

    @Override
    default void released(int key, int mods){

    }
}
