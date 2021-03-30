package com.szczygiel.pcbuildershop;

import com.szczygiel.pcbuildershop.category.CategoryDto;
import com.szczygiel.pcbuildershop.Item.ItemDto;
import com.szczygiel.pcbuildershop.UserProfile.LoginDto;
import com.szczygiel.pcbuildershop.UserProfile.RegisterDto;
import com.szczygiel.pcbuildershop.category.Category;
import com.szczygiel.pcbuildershop.Item.Item;
import com.szczygiel.pcbuildershop.UserProfile.UserProfile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class DataTestSamples {

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

    public static List<ItemDto> getItemDtoSamples() {
        return List.of(
                new ItemDto(1L, 1L, 1L, LocalDateTime.now(), "Ryzen 3600", "16 rdzeni, 32 wątki - najlepszy w swoim rodzaju", BigDecimal.valueOf(100.35)),
                new ItemDto(2L, 1L, 2L, LocalDateTime.now(), "Intel i5-8300H", "Architektura 14nm++++++++ robi swoje.", BigDecimal.valueOf(50.45)),
                new ItemDto(3L, 2L, 3L, LocalDateTime.now(), "SSD ADATA 512GB", "Duża pojemność, zmieści sporo gier.", BigDecimal.valueOf(25.99))
        );
    }

    public static List<LoginDto> getLoginDtoSamples() {
        return List.of(
                new LoginDto("jlowdes0", "ad8PRESdw"),
                new LoginDto("cpodbury1", "2s8WFJKV6"),
                new LoginDto("iodunniom2", "MMOOEcwn")
        );
    }

    public static List<UserProfile> getUserProfileSamples(){
        return List.of(
                new UserProfile(1L, "jlowdes0", "ad8PRESdw", "amarushak0@mediafire.com", null),
                new UserProfile(2L, "cpodbury1", "2s8WFJKV6", "tdrever1@discovery.com", null),
                new UserProfile(3L, "iodunniom2", "MMOOEcwn", "rlimming2@cdbaby.com", null)
        );
    }

    public static List<Category> getCategorySamples() {
        return List.of(
                new Category(1L, "Procesory", "Serce komputera, służące do wykonywania obliczeń", null),
                new Category(2L, "Karty graficzne", "Tworzą do wytwarzania grafiki komputerowej.", null),
                new Category(3L, "Dyski twarde HDD i SSD", "Przechowywanie danych na komputerze.", null)
        );
    }

    public static List<Item> getItemSamples() {
        return List.of(
                new Item(1L, getCategorySamples().get(0),  getUserProfileSamples().get(0),
                        "Ryzen 3600", "16 rdzeni, 32 wątki - najlepszy w swoim rodzaju",
                        LocalDateTime.now(), BigDecimal.valueOf(100.35)),
                new Item(2L, getCategorySamples().get(0),  getUserProfileSamples().get(1),
                        "Intel i5-8300H", "Architektura 14nm++++++++ robi swoje.",
                        LocalDateTime.now(), BigDecimal.valueOf(50.45)),
                new Item(3L, getCategorySamples().get(2),  getUserProfileSamples().get(2),
                        "SSD ADATA 512GB", "Duża pojemność, zmieści sporo gier.",
                        LocalDateTime.now(), BigDecimal.valueOf(25.99))

        );
    }
}
