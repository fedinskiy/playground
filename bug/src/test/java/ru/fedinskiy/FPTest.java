package ru.fedinskiy;

import org.junit.Assert;
import org.junit.Test;

public class FPTest {

	private static final double DELTA = 0.2;
	private static final float C = 1.01233995f;
	private static final float A = 12345679f;
	private static final float B = 12345678f;
	private static final float FIRST_RESULT = 972730.0f;
	private static final float SECOND_RESULT = 6249012.0f;

	private double stable(float a, float b, float c){
		return Math.sqrt((a+(b+c))*(c-(a-b))*(c+(a-b))*(a+(b-c)))/4;
	}

	private double heron(float a, float b, float c){
		float s = (a+b+c)/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	private double doubleHeron(double a, double b, double c){
		double s = (a+b+c)/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	@Test
	public void stable() {
		Assert.assertEquals(FIRST_RESULT, stable(A, B, C), DELTA);
		Assert.assertEquals(SECOND_RESULT, stable(A, A, C), DELTA);
	}

	@Test
	public void heron() {
		Assert.assertEquals(FIRST_RESULT, heron(A, B, C), DELTA);
		Assert.assertEquals(SECOND_RESULT, heron(A, A, C), DELTA);
	}

	@Test
	public void doubleHeron() {
		Assert.assertEquals(FIRST_RESULT, doubleHeron(A, B, C), DELTA);
		Assert.assertEquals(SECOND_RESULT, doubleHeron(A, A, C), DELTA);
	}
}
