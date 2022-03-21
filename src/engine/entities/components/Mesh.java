package entities.components;

import org.lwjgl.opengl.GL30;

import java.util.List;

public class Mesh {

    private int vaoID;
    private List<Integer> vbos;
    private int vertexCount;

    public Mesh(int vaoID, List<Integer> vbos, int vertexCount){
        this.vaoID = vaoID;
        this.vbos = vbos;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public List<Integer> getVbos() {
        return vbos;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void clearUp(){
        GL30.glDeleteVertexArrays(this.vaoID);
        for(int vbo:vbos)
            GL30.glDeleteBuffers(vbo);
        vbos.clear();
    }
}

