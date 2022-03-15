package entities;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Track {
    private List<Vector2f> points;
    private ArrayList<Float> lengths = new ArrayList<>();
    public Track(Vector2f ...pointArr){
        this.points = Arrays.asList(pointArr);
        for(int i=1;i<pointArr.length;i++){
            lengths.add((lengths.size()==0?0:lengths.get(lengths.size()-1))+Vector2f.distance(pointArr[i].x,pointArr[i].y,pointArr[i-1].x,pointArr[i-1].y));
        }
    }
    public Vector2f getPosition(float completePath){
        for(int i=0;i<lengths.size();i++){
            if(lengths.get(i)>=completePath){
                float numerator = completePath-(i==0?0:lengths.get(i-1));
                float denominator = lengths.get(i)-(i==0?0:lengths.get(i-1));
                float share = numerator/denominator;
                float x = points.get(i).x + share*(points.get(i+1).x-points.get(i).x);
                float y = points.get(i).y + share*(points.get(i+1).y-points.get(i).y);
                return new Vector2f(x,y);
            }
        }
        return points.get(points.size()-1);
    }
    public float getPathLength(){
        return lengths.get(lengths.size()-1);
    }
}
