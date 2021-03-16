package com.szczygiel.pcbuildershop;

import com.szczygiel.pcbuildershop.dto.ItemDto;
import com.szczygiel.pcbuildershop.dto.CategoryDto;
import com.szczygiel.pcbuildershop.dto.RegisterDto;

import java.util.List;

public class ControllerTestSamples {

    public static List<CategoryDto> getCategoryDtoSamples() {
        return List.of(
                new CategoryDto("Procesory", "Serce komputera, służące do wykonywania obliczeń"),
                new CategoryDto("Karty graficzne", "Tworzą do wytwarzania grafiki komputerowej."),
                new CategoryDto("Dyski twarde HDD i SSD", "Przechowywanie danych na komputerze.")
        );
    }

    public static List<RegisterDto> getRegisterDtoSamples() {
        return List.of(
                new RegisterDto("jlowdes0", "ad8PRESdw", "amarushak0@mediafire.com"),
                new RegisterDto("cpodbury1", "2s8WFJKV6", "tdrever1@discovery.com"),
                new RegisterDto("iodunniom2", "MMOOEcwn", "rlimming2@cdbaby.com")
        );
    }

    public static List<ItemDto> getItemSamples() {
        return List.of(
                new ItemDto()
        );
    }
}
