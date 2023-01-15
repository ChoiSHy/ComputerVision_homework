package Filter;

import java.awt.image.BufferedImage;

public class Laplacian_filter extends Filter {
	double[][] filter;

	public Laplacian_filter(BufferedImage bi) {
		this.size = 3;
		im2arr(bi);

		filter = new double[3][3];
		filter[0][0] = -4;
		filter[0][1] = 6;
		filter[0][2] = -4;
		filter[1][0] = 6;
		filter[1][1] = -8;
		filter[1][2] = 6;
		filter[2][0] = -4;
		filter[2][1] = 6;
		filter[2][2] = -4;
	}

	@Override
	protected double convolution(double[][] slice) {
		double sum=0;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				sum+=slice[i][j]*filter[i][j];
		return sum>255 ? 255: sum<0 ? 0:sum;
	}
}
