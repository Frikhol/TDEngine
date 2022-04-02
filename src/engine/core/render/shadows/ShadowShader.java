package core.render.shadows;

import org.joml.Matrix4f;

import core.render.shaders.ShaderProgram;

public class ShadowShader extends ShaderProgram {
	
	private static final String VERTEX_FILE = "src/engine/core.render.shadows/shadowVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/engine/core.render.shadows/shadowFragmentShader.txt";
	
	private int location_mvpMatrix;

	protected ShadowShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_mvpMatrix = super.getUniformLocation("mvpMatrix");
		
	}
	
	protected void loadMvpMatrix(Matrix4f mvpMatrix){
		super.loadMatrix(location_mvpMatrix, mvpMatrix);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "in_position");
	}

}
