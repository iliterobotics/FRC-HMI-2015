package org.ilite.uitools.widget.claw3D;

import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.control.SkinBase;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.util.Duration;

public class Claw3DSkin extends SkinBase<Claw3D> {

	protected Claw3DSkin(Claw3D skinnable) {
		super(skinnable);
		initGraphics();
	}
	private void initGraphics(){
		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setSpecularColor(Color.ORANGE);
	    redMaterial.setDiffuseColor(Color.RED);
	    
		Cylinder sphere = new Cylinder(20, 100);
		sphere.setOnMouseClicked(observable -> sphere.rotateProperty().set(100));
		sphere.setMaterial(redMaterial);
		
		this.getChildren().add(demoRect(200f, 300f));
	}
	
	private MeshView demoRect(float width, float height){
		MeshView view = new MeshView();
		TriangleMesh rect = new TriangleMesh();
		
		float[] points = {
				 12.0f,   0.0f, 12.0f,
				  7.0f,   2.5f,  2.0f,
				 -1.5f,   2.5f, -9.0f,
				 -3.5f,  -3.5f, -5.0f,
				-12.0f,   0.0f, 12.0f,
				 -1.5f,  5.25f, -9.0f,
				  7.0f,   0.0f,  2.0f,
				  3.5f,   3.5f, -5.0f,
				 -7.0f,   0.0f,  2.0f,
				 -4.5f,   2.5f,  2.0f,
				-9.25f, -5.25f, -9.0f,
				  1.5f, -5.25f, -9.0f,
				 -7.0f,  -2.5f,  2.0f,
				 9.25f,  5.25f, -9.0f,
				  4.5f,  -2.5f,  2.0f,
				  1.5f,  -2.5f, -9.0f,

        };
        float[] texCoords = {
                0.5f, 0.5f  // idx t3
        };
        /**
         * points:
         * 2      3  
         *  -------\ 
         *  |\    | \ 
         *  | \   |  |5
         *  |  \  | /|
         *  |   \ |/ |4
         *  |    \| / 
         *  -------/  
         * 0      1  
         *
         * texture[3] 0,0 maps to vertex 2
         * texture[2] 0,1 maps to vertex 0
         * texture[0] 1,1 maps to vertex 1
         * texture[1] 1,0 maps to vertex 3
         *
         * Two triangles define rectangular faces:
         * p0, t0, p1, t1, p2, t2 // First triangle of a textured rectangle
         * p0, t0, p2, t2, p3, t3 // Second triangle of a textured rectangle
         */

//if you use the co-ordinates as defined in the above comment, it will be all messed up
//        int[] faces = {
//                0, 0, 1, 1, 2, 2,
//                0, 0, 2, 2, 3, 3
//        };


//try defining faces in a clockwise order to see what the difference is.
        int[] faces = {
        		0, 0, 1, 0, 9, 0, 
        		13,0,  5,0,   4, 0,
        		0, 0, 4, 0, 12,  0,
        		14,0, 10,0,  11, 0,
        		1, 0, 0, 0, 11,  0,
        		15,0,  7,0,   6, 0,
        		1, 0, 6, 0, 14,  0,
        		12,0,  8,0,   9, 0,
        		2, 0, 3, 0,  8,  0,
        		12,0,  4,0,   5, 0,
        		2, 0, 5, 0, 13,  0,
        		15,0, 11,0,  10, 0,
        		3, 0, 2, 0, 10,  0,
        		14,0,  6,0,   7, 0,
        		3, 0, 7, 0, 15,  0,
        		13,0,  9,0,   8, 0
        };

        rect.getPoints().setAll(points);
        rect.getTexCoords().setAll(texCoords);
        rect.getFaces().setAll(faces);
        
        view.setMesh(rect);
        view.setMaterial(new PhongMaterial(Color.RED));
        view.setCullFace(CullFace.NONE);
        view.setRotationAxis(new Point3D(100, 0, 50));
        
        RotateTransition rt = new RotateTransition();
        rt.setToAngle(1000);
        rt.setDuration(Duration.millis(6000));
        rt.setAutoReverse(true);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setNode(view);
        rt.play();
        
        return view;
	}
	
	

	

}
