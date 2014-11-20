package fontCreator;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import fontastic.FGlyph;
import fontastic.Fontastic;

public class Test {

	public static void main(String[] args) {

		PApplet applet = new PApplet();
		Fontastic font = null;

		PImage img = null; // Declaring a variable of type PImage
		// going to load the image

		File outputImage = null;
		try{ // testing to see if the output of SplitImage is found, these should be the chunk images 

			outputImage = new File("ScriptTemplate0.png"); // not needed for loadImage or Pvectors at the moment
			// but going to need for conversation later on, since we can no directly access pvector with bufferedImage 
			// might as well test now with the loadimage, if one fails the other will surely fail 

			//created a method called image 
			img=loadImage("ScriptTemplate0.png"); // Make a new instance of a PImage by loading an image file

			//location of the upper and right and left corner 
			// this is going to display the original size of the image
			//if we want to change it, we need to add parameters, c and d , this will affects its width and height 
			// x and y coordinates 
			image(img, 0,0);

		}
		catch(Exception e){ // error message, something went wrong with the file 
			//most likely split image was not run first
			//something wrong with the name, case sensitive 
			//refresh and try again	
			System.out.println("An error occured while trying to load your images");
			System.out.println("Pleas ensure your images are in the right location and try again");
			System.out.println("You need to make sure you run 'Split Image' was run first");
			e.printStackTrace();
			System.exit(0);
		}

		File outputFolder = null;
		try { // going to create output folder now 

			// Fontastic is no different than a String, but used for folder names and fonts in this case 
			font= new Fontastic(applet, "ExampleFont2"); // Create a new Fontastic object


			font.setAuthor("Andreas Koller"); // Set author name - will be saved in
			// TTF file too

			String output = "ExampleFont2"; // I want to find the location of the output folder 
			// so call the string on the file, then use getAbsolutePath() on the file 
			outputFolder = new File(output);
			System.out.println("Output folder has been created at: " + outputFolder.getAbsolutePath()); // location of the output folder
			// also suggest success, output folder was made 
		} catch (Exception ex) { // error message of output folder was not made correctly

			System.out.println("An Error occured while trying to make output folder, plesae try again, Thanks");
			ex.printStackTrace();
			System.exit(0);

		}

		// if no exceptions occur until this point, means image was successfully loaded, and output folder was successfully made 
		// next need to load the image into a PVector


		//here might start causing issues with conversion 
		// conversation has not started yet
		// but we are preparing for it now 
		// goal right now is to successfully create a buffered image so we can get the width and the height of each image 

		FileInputStream picture = null;

		try { // testing to ensure that the that out outputImages  from Split Images can be read in the InputStream ( bytes of fata )
			picture = new FileInputStream(outputImage);
			// actually read the file in bytes
			// outputImage is the file name "ScriptTemplate0.png"
			//call outputImage it on FileInputStream
			System.out.println("File was successful readin!");
		} catch (FileNotFoundException e) {
			System.out.println("Error occured, File could not be read, please try again ");
			System.out.println("Most likly files are not in the correct location, pleas ensure they are at: " + outputImage.getAbsolutePath());
			e.printStackTrace();
			System.exit(0);
		} 



		BufferedImage readImage = null; // Initialing a BufferedImage  
		try { // want to test to ensure a buffered image can be create successfully 
			readImage = ImageIO.read(picture); // readImage the variable we declared is now being used to read picture
			// picture = FileInputStream  which = outputImage which = our split up image 
			System.out.println("A BufferedImage was succesfuly made, Congrats!");
			System.out.println("Conversation can now begin!");
		} catch (IOException e) { // fail case 
			System.out.println("Error occured while trying to create a BufferedImage, please try again");
			System.out.println("Check to ensure that FileInputStream is corrrect;");
			e.printStackTrace();
			System.exit(0);
		} // code works perfect to this part, no issues after testing
		// if any errors occur, it will be with the code below
		// but BufferedImage has been successful made 

		// now able to read all image data via readImage which is the BufferedImage  
		// called it on picture which was the FileInputStream 



		// don't forget 'readImage' is the bufferedImage 
		// we can not use the function .getWidth(), and getHeight(), directly on the PImage 
		//but since the images height and width are the same, we can  use a bufferedImage to determine the height and the width
		// get it by default via those functions 


		// since out BufferedImage is created, going to convert it to points , using PVector

		// sometimes we need to load the pixels first so if an error occurs, try this code below 
		//img.loadPixels();

		//Going to convert this image to a point set of dimension 4
		//Going to loop on the Pixels 

		int width = readImage.getWidth();    // remember these are predetermined by java 
		int height = readImage.getHeight();
		int entirePixels = width * height; // full picture, should gives us the pixels of the image 

		PVector[] points = new PVector[0]; // setting a vector equal to a vector of the entire pixels
		System.out.println(entirePixels);
		// source of the problem 
		//setting parameter to 0, allows it to run 

		PVector pixels;
		Color c;


		System.out.println("Conversation is now starting!, Stay tuned");

		//System.out.println(entirePixels); use to figure out the amount of the pixels 
		try{
			//Conversation process is now begin 

			for(int row = 0; row<readImage.getHeight();row++){ // going to loop through the rows,
				for(int col =0; col<readImage.getWidth(); col++){ // going to loop through the columns 

					// remember we are looping through the pixels to recreate the image through a PVector 

					c = new Color(readImage.getRGB(col, row));

					// we need this, important, error keep occurring
					//PVector not being declared directly??
					// .array also causing issues 

					/*pixels = new PVector();
					pixels.x = c.getRed();
					pixels.y = c.getGreen();
					pixels.z = c.getBlue();

					points[ row * width + col ] = pixels;*/

				}
			}

			System.out.println("Image was success converted, Almost Finished!");
			// conversation should be done at this point
			// just need to draw out this image 
		}
		catch(Exception ex){
			System.out.println("An error occured while trying to convert your images, please try again");
			System.out.println("Check the height and width of your image");
			ex.printStackTrace();
			System.exit(0);
		}

		//ignore this code for now
		// below is set to adjust the dimensions of the contour 
		// do we need to adjust or at least set dimensions now?? lets see
		// i do not want to affect the image, just print out the actual image 

		/*
		points[0] = new PVector(0, 0); // Start at bottom left
		points[1] = new PVector(1000, 0); // The normal width is 512, the normal
		// height 1024 
		points[2] = new PVector(23454, 888); // y coordinates are from bottom to
		// top!
		points[3] = new PVector(0, 500);
		 */

		FGlyph glyph;
		try{
		
			//source of issue
			glyph = font.addGlyph('a'); // Assign contour to character A
			glyph.addContour(points); // as of right now this is causing problems, 

			System.out.println("Success at creating Glyph image, keyboard assisgned");
			System.out.println("Your font is about to be built!");

			// parameter of points guessing is too high?
			// set to the value of the pixels of the image
			// need only 4 points
		}
		catch(Exception e){
			System.out.println("An error occured while trying to create your Glyph image");
			e.printStackTrace();
			System.exit(0);
		}

		try{

			font.buildFont(); // Build the font resulting in .ttf and .woff files
			// and a HTML template to preview the WOFF
			font.cleanup();

			System.out.println("Success!!");
			System.out.println("Font has been successfully created!!");
		}
		catch(Exception e){
			System.out.println("An error occured while building the font ");
			System.out.println("Could not create the .tff and .woff file");
		}

	}


	private static void image(PImage img, int i, int j) {
		img=loadImage("ScriptTemplate0.png");
		i = 0;
		j = 0;
	}

	private static PImage loadImage(String string) {
		string = "ScriptTemplate0.png";
		File file = new File(string);
		System.out.println("Success at finding your file");
		System.out.println("Found file location at:" + file.getAbsolutePath());
		return null;
	}



}
