package br.com.darioklein.ecopass.config;

import br.com.darioklein.ecopass.domain.dto.userDTO.UserCreateDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletCreateDTO;
import br.com.darioklein.ecopass.service.UserService;
import br.com.darioklein.ecopass.service.WalletService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Configuration
@Profile("local")
public class LocalConfig {

    private UserService userService;
    private WalletService walletService;

    public void startUsersDb() {

        UserCreateDTO user1 = new UserCreateDTO("Dario", "48141136576", "dario@mail.com", "12345678", "18997325622", LocalDate.of(2000, 10, 2));
        UserCreateDTO user2 = new UserCreateDTO("Eduardo", "15878396980", "eduardo@mail.com", "12345678", "18996358877", LocalDate.of(2000, 10, 2));
        UserCreateDTO user3 = new UserCreateDTO("Luis", "36541198882", "luis@mail.com", "12345678", "18997325611", LocalDate.of(2000, 10, 2));

        userService.createAll(List.of(user1, user2, user3));
    }

    public void startWalletsDb() {

        WalletCreateDTO wallet1 = new WalletCreateDTO(userService.findById(3L).id(), BigDecimal.valueOf(530), BigDecimal.valueOf(210));
        WalletCreateDTO wallet2 = new WalletCreateDTO(userService.findById(2L).id(), BigDecimal.valueOf(100), BigDecimal.valueOf(300));
        WalletCreateDTO wallet3 = new WalletCreateDTO(userService.findById(1L).id(), BigDecimal.valueOf(50), BigDecimal.valueOf(100));

        walletService.createAll(List.of(wallet1, wallet2, wallet3));
    }

    @PostConstruct
    public void init() {
        startUsersDb();
        startWalletsDb();
    }
}
