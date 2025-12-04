package br.com.darioklein.ecopass.service;

import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletCreateDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletPatchDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletRespondeDTO;
import br.com.darioklein.ecopass.domain.dto.walletDTO.WalletUpdateDTO;
import br.com.darioklein.ecopass.domain.mapper.WalletMapper;
import br.com.darioklein.ecopass.domain.model.User;
import br.com.darioklein.ecopass.domain.model.Wallet;
import br.com.darioklein.ecopass.repository.UserRepository;
import br.com.darioklein.ecopass.repository.WalletRepository;
import br.com.darioklein.ecopass.service.exception.DataIntegrityViolationException;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final WalletMapper mapper;

    @Transactional(readOnly = true)
    public WalletRespondeDTO findById(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Carteira não encontrada"));
        return mapper.toResponse(wallet);
    }

    @Transactional
    public WalletRespondeDTO create(WalletCreateDTO dto) {
        User user = userRepository.findById(dto.userId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

        if (walletRepository.existsByUser(user)) {
            throw new DataIntegrityViolationException("O usuário digitado já possuí uma carteira");
        }

        Wallet wallet = mapper.toEntity(dto);
        wallet.setUser(user);

        Wallet walletSaved = walletRepository.save(wallet);
        return mapper.toResponse(walletSaved);
    }

    @Transactional
    public void createAll(List<WalletCreateDTO> dtos) {
        List<Wallet> walletList = dtos.stream().map((dto) -> {
            User user = userRepository.findById(dto.userId()).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

            if (walletRepository.existsByUser(user)) {
                throw new DataIntegrityViolationException("O usuário digitado já possuí uma carteira");
            }

            Wallet wallet = mapper.toEntity(dto);
            wallet.setUser(user);

            return wallet;
        }).toList();

        walletRepository.saveAll(walletList);
    }

    @Transactional
    public WalletRespondeDTO update(Long id, WalletUpdateDTO dto) {
        Wallet walletFound = walletRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Carteira não encontrada"));

        mapper.updateEntityFromDto(dto, walletFound);

        walletFound = walletRepository.save(walletFound);
        return mapper.toResponse(walletFound);
    }

    @Transactional
    public void updatePartial(Long id, WalletPatchDTO dto) {
        Wallet walletFound = walletRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Carteira não encontrada"));

        mapper.patchEntityFromDto(dto, walletFound);

        walletRepository.save(walletFound);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!walletRepository.existsById(id)) {
            throw new ObjectNotFoundException("Carteira não encontrada");
        }
        walletRepository.deleteById(id);
    }

}
