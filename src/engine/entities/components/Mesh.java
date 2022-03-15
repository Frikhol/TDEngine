package entities.components;

public class Mesh {

    private int vaoID;
    private int vertexCount;

    public Mesh() {
    }

    public Mesh(int vaoID, int vertexCount){
        this.vaoID = vaoID;
        this.vertexCount = vertexCount;
    }

    public int getVaoID() {
        return vaoID;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public void setVaoID(int vaoID) {
        this.vaoID = vaoID;
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }
}

