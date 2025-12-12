package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletCreateDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletPatchDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletRespondeDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletUpdateDTO;

import java.util.List;

public interface WalletService {

    WalletRespondeDTO findById(Long id);

    WalletRespondeDTO create(WalletCreateDTO dto);

    void createAll(List<WalletCreateDTO> dtos);

    WalletRespondeDTO update(Long id, WalletUpdateDTO dto);

    void updatePartial(Long id, WalletPatchDTO dto);

    void deleteById(Long id);
}
