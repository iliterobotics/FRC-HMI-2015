package org.ilite.util.platform;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

/**
 * Class to test to see if the opencv is configured correctly. If opencv is 
 * configured correctly, in the {@link PlatformUtils}, then this will display 
 * some basic opencv commands. Otherwise this will return with an appropriate 
 * error message. 
 */
public class OpenCVHelloWorld {


	public static void main(String[] args) {

		if(!PlatformUtils.IS_OPENCV_LIB_CONFIG) {
			System.err.println("Failed to load the opencv lib!");
			return;
		}
		System.out.println("Welcome to OpenCV " + Core.VERSION);
		Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
		System.out.println("OpenCV Mat: " + m);
		Mat mr1 = m.row(1);
		mr1.setTo(new Scalar(1));
		Mat mc5 = m.col(5);
		mc5.setTo(new Scalar(5));
		System.out.println("OpenCV Mat data:\n" + m.dump());
	}


}
