package com.szczygiel.pcbuildershop.util;

import com.szczygiel.pcbuildershop.Item.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class ValidationUtilTest {
    @Autowired
    private ValidationUtil validationUtil;

    @Test
    public void invalidItem_ShouldNotPass() {
        //given
        ItemDto itemDto = new ItemDto(-5L,
                1234L,
                0L,
                LocalDateTime.now().minusMinutes(1),
                "xd".repeat(260),
                "xd".repeat(260),
                BigDecimal.valueOf(-100L)
        );
        //when
        String response = validationUtil.validateItemDto(itemDto);
        //then
        assertEquals("Invalid userId. Invalid categoryId. Invalid title. Invalid description.", response);
    }

}
