package com.kslima.ksdeliver;

import com.kslima.ksdeliver.dto.OrderDTO;
import com.kslima.ksdeliver.services.exceptions.ValidateException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@SpringBootTest
class KsdeliverApplicationTests {

	@Test
	void contextLoads() {

		OrderDTO dto = new OrderDTO();

		validate(dto);
	}

	public static void validate(OrderDTO dto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<OrderDTO>> violations = validator.validate(dto);
		if (!violations.isEmpty()) {
			violations.forEach(v -> System.out.println(v.getMessage()));
			throw new ValidateException("Erro de validacao");
		}
	}

}
