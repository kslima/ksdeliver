package com.kslima.ksdeliver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class KsdeliverApplicationTests {

	@Test
	public void list() {
		List<Double> doubles = new ArrayList<>();
		doubles.add(20.5);
		doubles.add(10.0);
		doubles.add(20.5);

		double sum  = doubles.stream()
						.reduce(0.0, Double::sum);

		Assertions.assertEquals(51, sum);
	}

}
