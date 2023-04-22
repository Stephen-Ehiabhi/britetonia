package com.fashion;

import com.fashion.model.Inventory;
import com.fashion.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
      return args -> {
          Inventory inventory = new Inventory();
          inventory.setSkuCode("Amaka Evening Gown");
          inventory.setQuantity(22);

          Inventory inventory1 = new Inventory();
          inventory1.setSkuCode("Grace morning Gown");
          inventory1.setQuantity(14);

          inventoryRepository.save(inventory);
          inventoryRepository.save(inventory1);

      };
    }

}