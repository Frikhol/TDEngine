package core;

import de.matthiasmann.twl.utils.PNGDecoder;
import entities.components.Mesh;
import entities.components.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    private static List<Integer> vaos = new ArrayList<Integer>();
    private static List<Integer> vbos = new ArrayList<Integer>();
    private static List<Integer> textures = new ArrayList<Integer>();

    private static Mesh loadToVAO(float[] positions, int[] indices, float[] textureCoords, float[] normals){
        int vaoID = createVAO();
        bindIndicesBuffer(indices);
        storeDataInAttributeList(0,3,positions);
        storeDataInAttributeList(1,2,textureCoords);
        storeDataInAttributeList(2,3,normals);
        unbindVAO();
        return new Mesh(vaoID,indices.length);
    }

    public static int loadToVAO(float[] positions,float[] textureCoords, List<Integer> vboList){
        int vaoID = createVAO();
        vboList.add(storeDataInAttributeList(0,2,positions));
        vboList.add(storeDataInAttributeList(1,2,textureCoords));
        unbindVAO();
        return vaoID;
    }

    public static Mesh loadToVAO(float[] positions){
        int vaoID = createVAO();
        storeDataInAttributeList(0,2,positions);
        unbindVAO();
        return new Mesh(vaoID,positions.length/2);
    }

    private static void bindIndicesBuffer(int[] indices){
        int vboID = GL15.glGenBuffers();
        //System.out.println("bindIndicesBuffer function, vboID = "+vboID);
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER,vboID);
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER,buffer,GL15.GL_STATIC_DRAW);
    }

    private static int storeDataInAttributeList(int attributeNumber, int coordinateSize, float[] data){
        int vboID = GL15.glGenBuffers();
        //System.out.println("storeDataInAttributeList function, vboID = "+vboID);
        vbos.add(vboID);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboID);
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer,GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attributeNumber,coordinateSize, GL11.GL_FLOAT,false,0,0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
        return vboID;
    }

    private static int createVAO(){
        int vaoID = GL30.glGenVertexArrays();
        //System.out.println("CreateVAO function, vaoID = "+vaoID);
        vaos.add(vaoID);
        GL30.glBindVertexArray(vaoID);
        return vaoID;
    }

    private static IntBuffer storeDataInIntBuffer(int[] data){
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data);
        buffer.flip();
        return buffer;
    }

    private static FloatBuffer storeDataInFloatBuffer(float[] data){
        FloatBuffer buffer = BufferUtils.createFloatBuffer((data.length));
        buffer.put(data);
        buffer.flip();
        return  buffer;
    }

    private static void unbindVAO(){
        GL30.glBindVertexArray(0);
        //System.out.println("unbindVAO function");
    }

    public static Texture loadTexture(String fileName){
        PNGDecoder decoder = null;
        try {
            decoder = new PNGDecoder(Loader.class.getClassLoader().getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(4 * decoder.getWidth() * decoder.getHeight());
        try {
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.flip();
        int id = GL15.glGenTextures();
        GL11.glBindTexture(GL20.GL_TEXTURE_2D, id);
        GL11.glPixelStorei(GL20.GL_UNPACK_ALIGNMENT, 1);
        GL11.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MIN_FILTER, GL20.GL_LINEAR_MIPMAP_LINEAR);
        GL11.glTexParameterf(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_LOD_BIAS, -0);
        GL11.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGBA, decoder.getWidth(), decoder.getHeight(), 0, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, buffer);
        GL30.glGenerateMipmap(GL20.GL_TEXTURE_2D);
        GL11.glEnable(GL20.GL_TEXTURE_2D);
        return new Texture(id);
    }

    public static Mesh loadObjModel (String filename){
        FileReader fr = null;
        try {
            fr = new FileReader(new File("Assets/models/"+filename+".obj"));
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
                    Vector3f vertex = new Vector3f(Float.parseFloat(currentLine[1]),Float.parseFloat(currentLine[2]),Float.parseFloat(currentLine[3]));
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
        return loadToVAO(verticesArray,indicesArray,textureArray,normalsArray);
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

    public static void cleanUP(){
        for(int vao:vaos)
            GL30.glDeleteVertexArrays(vao);
        for(int vbo:vbos)
            GL30.glDeleteBuffers(vbo);
        for(int texture:textures)
            GL11.glDeleteTextures(texture);
    }
}
