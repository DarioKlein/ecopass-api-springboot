package br.com.darioklein.ecopass.controller;

import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletCreateDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletPatchDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletRespondeDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletUpdateDTO;
import br.com.darioklein.ecopass.service.impl.WalletServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/wallets")
public class WalletController {

    private final WalletServiceImpl walletService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<WalletRespondeDTO> findWalletById(@PathVariable Long id) {
        WalletRespondeDTO wallet = walletService.findById(id);
        return ResponseEntity.ok(wallet);
    }

    @PostMapping
    public ResponseEntity<WalletRespondeDTO> createWallet(@Valid @RequestBody WalletCreateDTO walletDTO) {
        WalletRespondeDTO wallet = walletService.create(walletDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(wallet.id()).toUri();
        return ResponseEntity.created(uri).body(wallet);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WalletRespondeDTO> updateWallet(@PathVariable Long id, @Valid @RequestBody WalletUpdateDTO walletDTO) {
        WalletRespondeDTO wallet = walletService.update(id, walletDTO);
        return ResponseEntity.ok(wallet);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Void> updatePartialWallet(@PathVariable Long id, @Valid @RequestBody WalletPatchDTO walletDTO) {
        walletService.updatePartial(id, walletDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteWallet(@PathVariable Long id) {
        walletService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
