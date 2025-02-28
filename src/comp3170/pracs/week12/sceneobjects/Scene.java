package comp3170.pracs.week12.sceneobjects;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import comp3170.InputManager;
import comp3170.SceneObject;
import comp3170.pracs.week12.cameras.Camera;
import comp3170.pracs.week12.cameras.PerspectiveCamera;

public class Scene extends SceneObject {

	public static Scene theScene = null;
	private Skybox skybox;
	private PerspectiveCamera camera;

	public Scene() {
		theScene = this;
		skybox = new Skybox();
		camera = new PerspectiveCamera();
	}

	public void update(InputManager input, float deltaTime) {
		camera.update(input, deltaTime);		
	}

	public Camera getCamera() {
		return camera;
	}
	
	private Matrix4f viewMatrix = new Matrix4f();
	private Matrix4f projectionMatrix = new Matrix4f();
	private Matrix4f mvpMatrix = new Matrix4f();
	private Vector4f origin = new Vector4f(0,0,0,1);

	@Override
	public void draw(Matrix4f parentMatrix) {
		// draw the skybox without view translation, 
		// so it is always centred on the camera
		camera.getViewMatrix(viewMatrix);
		viewMatrix.setColumn(3, origin);
		camera.getProjectionMatrix(projectionMatrix);
		projectionMatrix.mul(viewMatrix, mvpMatrix);
		skybox.draw(mvpMatrix);		
	}
	
}
