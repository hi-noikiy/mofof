package com.mofof.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;

public class ImageUtils {

	public static void resizeImage(InputStream input, File toFile, int width, int height) throws IOException {
		BufferedImage image = ImageIO.read(input);
		Thumbnails.of(image)
		.size(width, height)
		.toFile(toFile);
	}
	
	public static void resizeImage(File input, File toFile, int width, int height) throws IOException {
		BufferedImage image = ImageIO.read(input);
		Thumbnails.of(image)
		.size(width, height)
		.toFile(toFile);
	}
}
