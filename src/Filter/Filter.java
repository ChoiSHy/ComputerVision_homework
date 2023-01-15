package Filter;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Filter {
	int size;
	double [][] arr;
	double [][] res;
	
	protected abstract double convolution(double[][] slice);
	
	public void im2arr(BufferedImage bi){
		arr = new double[bi.getHeight()+2][bi.getWidth()+2];
		int half=size/2;
		for (int x = 0; x < bi.getHeight(); x++) {
			for (int y=0; y < bi.getWidth(); y++) {
				Color c = new Color(bi.getRGB(y,x));
				arr[x+half][y+half] += c.getRed();
				arr[x+half][y+half] += c.getGreen();
				arr[x+half][y+half] += c.getBlue();
				arr[x+half][y+half] /= 3.0;
			}
		}
	}

	public BufferedImage arr2im() {
		BufferedImage bi = new BufferedImage(res[0].length, res.length, BufferedImage.TYPE_INT_BGR);
		
		for (int x = 0; x<res.length;x++) {
			for (int y =0; y<res[0].length;y++) {
				bi.setRGB(y, x, new Color( (int)res[x][y] , (int)res[x][y] , (int)res[x][y] ).getRGB());
			}
		}
		return bi;
	}
	
	public void filtering() {
		int half=size/2;
		res=new double[arr.length-(half*2)][arr[0].length-(half*2)];
		
		for (int i = half; i < arr.length - half; i++) {
			for (int j = half; j < arr[0].length - half; j++) {
				double[][] input = new double[size][size];
				
				for (int x = -half; x < half; x++)
					for (int y = -half; y < half; y++)
						input[x + half][y + half] = arr[i + x][j + y];

				res[i-half][j-half] = convolution(input);
			}
		}
	}
	
}
