package physics.colliders;

import entities.components.Material;
import entities.components.Model;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static core.loader.Loader.*;

public class Collider {
    private Vector3f size;
    private Model model;

    public Collider() {
        size = new Vector3f(0.0f);
    }

    public Vector3f getSize() {
        return size;
    }

    public void setSize(Vector3f size) {
        this.size = size;
    }

    public void resize(Vector3f vertex) {
        if((size.x-Math.abs(vertex.x))<0)
            size.x = Math.abs(vertex.x);
        if((size.y-Math.abs(vertex.y))<0)
            size.y = Math.abs(vertex.y);
        if((size.z-Math.abs(vertex.z))<0)
            size.z = Math.abs(vertex.z);
    }

    public void loadCollider(){
        FileReader fr = null;
        try {
            fr = new FileReader(new File("Assets/models/Collider.obj"));
        } catch (FileNotFoundException e) {
            System.err.println("Couldn't load file");
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(fr);
        String line;
        List<Vector3f> vertices = new ArrayList<Vector3f>();
        List<Vector2f> textures = new ArrayList<Vector2f>();
        List<Vector3f> normals = new ArrayList<Vector3f>();
        List<Integer> indices = new ArrayList<Integer>();
        float[] verticesArray = null;
        float[] normalsArray = null;
        float[] textureArray = null;
        int[] indicesArray = null;
        try{
            while (true){
                line=reader.readLine();
                String[] currentLine = line.split(" ");
                if(line.startsWith("v ")){
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1])*size.x,Float.parseFloat(currentLine[2])*size.y/2f,Float.parseFloat(currentLine[3])*size.z);
                    vertices.add(vertex);
                } else if(line.startsWith("vt ")){
                    Vector2f texture = new Vector2f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]));
                    textures.add(texture);
                } else if(line.startsWith("vn ")){
                    Vector3f normal = new Vector3f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]),Float.parseFloat(currentLine[3]));
                    normals.add(normal);
                } else if(line.startsWith("f ")){
                    textureArray = new float[vertices.size()*2];
                    normalsArray = new float[vertices.size()*3];
                    break;
                }
            }
            while(line!=null){
                if(!line.startsWith("f")){
                    line = reader.readLine();
                    continue;
                }
                String[] currentLine = line.split(" ");
                String[] vertex1 = currentLine[1].split("/");
                String[] vertex2 = currentLine[2].split("/");
                String[] vertex3 = currentLine[3].split("/");

                processVertex(vertex1,indices,textures,normals,textureArray,normalsArray);
                processVertex(vertex2,indices,textures,normals,textureArray,normalsArray);
                processVertex(vertex3,indices,textures,normals,textureArray,normalsArray);
                line = reader.readLine();
            }
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        verticesArray = new float[vertices.size()*3];
        indicesArray = new int[indices.size()];

        int vertexPointer = 0;
        for(Vector3f vertex:vertices){
            verticesArray[vertexPointer++] = vertex.x;
            verticesArray[vertexPointer++] = vertex.y;
            verticesArray[vertexPointer++] = vertex.z;

        }
        for(int i = 0;i<indices.size();i++){
            indicesArray[i] = indices.get(i);
        }
        model = new Model("Collider",loadToVAO(verticesArray,indicesArray,textureArray,normalsArray),new Material("Collider",1f,0,1,0));
    }

    private static void processVertex(String[] vertexData, List<Integer> indices, List<Vector2f> textures, List<Vector3f> normals,float[] textureArray,float[] normalsArray){
        int currentVertexPointer = Integer.parseInt(vertexData[0])-1;
        indices.add(currentVertexPointer);
        Vector2f currentTex = textures.get(Integer.parseInt((vertexData[1]))-1);
        textureArray[currentVertexPointer*2] = currentTex.x;
        textureArray[currentVertexPointer*2+1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2])-1);
        normalsArray[currentVertexPointer*3] = currentNorm.x;
        normalsArray[currentVertexPointer*3+1] = currentNorm.y;
        normalsArray[currentVertexPointer*3+2] = currentNorm.z;
    }

    public Model getModel() {
        return model;
    }

    public void scale(float scale) {
        size.mul(scale);
    }
}
