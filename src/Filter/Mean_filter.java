package Filter;

import java.awt.image.BufferedImage;

public class Mean_filter extends Filter {

	public Mean_filter(int size, BufferedImage bi) {
		this.size = size;
		im2arr(bi);
	}

	 protected double convolution(double[][] input) {
		double sum = 0;
		for (double[] row : input) {
			for (double d : row)
				sum += d;
		}

		return sum / (size * size);
	}

	
}
