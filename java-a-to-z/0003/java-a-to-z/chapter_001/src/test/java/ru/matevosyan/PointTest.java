package ru.matevosyan;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PointTest {

	@Test
	public void whenAddTwoPointsThenReturnPointsDistance() {
		
		//assign

		Point pointTestFirst = new Point(1.0,1.0);
		Point pointTestSecond = new Point(2.0,2.0);
		
		//act 1.4142135623730951

		double result = pointTestSecond.distanceTo(pointTestFirst);
		assertThat(result, is(1.4142135623730951d));
		

	}
	
}