package Filter;

import java.awt.image.BufferedImage;
import java.util.Arrays;

public class Median_filter extends Filter{
	int sqr;
	public Median_filter(int size, BufferedImage bi) {
		sqr=size*size;
		this.size=size;
		im2arr(bi);
	}
	protected double convolution(double[][] slice) {
		double[] list=new double[sqr];
		int i=0;
		for(double []row:slice)
			for(double d:row)
				list[i++]=d;
		Arrays.sort(list);
		
		return list[sqr/2+1];
	}

}
