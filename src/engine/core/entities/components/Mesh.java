package core.entities.components;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.List;

public class Mesh {

    private int vaoID;
    private List<Integer> vbos;
    private ArrayList<Vector3f> vertices;
    private int vertexCount;

    public Mesh(int vaoID, List<Integer> vbos, int vertexCount){
        this.vaoID = vaoID;
        this.vbos = vbos;
        this.vertexCount = vertexCount;
    }

    public Mesh(int vaoID, List<Integer> vbos,ArrayList<Vector3f> vertices, int vertexCount){
        this.vaoID = vaoID;
        this.vbos = vbos;
        this.vertices = vertices;
        this.vertexCount = vertexCount;
    }

    public ArrayList<Vector3f> getVertices() {
        return (ArrayList<Vector3f>) vertices.clone();
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

