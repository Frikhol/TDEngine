package ui.font.shaders;

import org.joml.Vector2f;
import org.joml.Vector3f;
import core.render.shaders.ShaderProgram;

public class FontShader extends ShaderProgram {

	private static final String VERTEX_FILE = "src/engine/ui.font/core.render.shaders/fontVertex.txt";
	private static final String FRAGMENT_FILE = "src/engine/ui.font/core.render.shaders/fontFragment.txt";

	private int location_colour;
	private int location_translation;

	public FontShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void getAllUniformLocations() {
		location_colour = super.getUniformLocation("colour");
		location_translation = super.getUniformLocation("translation");
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0,"position");
		super.bindAttribute(1,"textureCoords");
	}


	public void loadColour(Vector3f colour){
		super.loadVector(location_colour,colour);
	}

	public void loadTranslation(Vector2f translation){
		super.load2DVector(location_translation,translation);
	}


}
