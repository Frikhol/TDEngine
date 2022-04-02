package ui.font.components;

import ui.font.GUIText;

import java.io.File;

/**
 * Represents a ui.font. It holds the ui.font's texture atlas as well as having the
 * ability to create the quad vertices for any text using this ui.font.
 * 
 * @author Karl
 *
 */
public class FontType {


	private int textureAtlas;
	private TextMeshCreator loader;

	/**
	 * Creates a new ui.font and loads up the data about each character from the
	 * ui.font file.
	 * 
	 * @param textureAtlas
	 *            - the ID of the ui.font atlas texture.
	 * @param fontFile
	 *            - the ui.font file containing information about each character in
	 *            the texture atlas.
	 */
	public FontType(int textureAtlas, File fontFile) {
		this.textureAtlas = textureAtlas;
		this.loader = new TextMeshCreator(fontFile);
	}

	/**
	 * @return The ui.font texture atlas.
	 */

	public int getTextureAtlas() {
		return textureAtlas;
	}

	/**
	 * Takes in an unloaded text and calculate all of the vertices for the quads
	 * on which this text will be rendered. The vertex positions and texture
	 * coords and calculated based on the information from the ui.font file.
	 * 
	 * @param text
	 *            - the unloaded text.
	 * @return Information about the vertices of all the quads.
	 */
	public TextMeshData loadText(GUIText text) {
		return loader.createTextMesh(text);
	}

}
