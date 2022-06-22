package com.ccsw.tutorial.lease;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.lease.model.Lease;
import com.ccsw.tutorial.lease.model.LeaseDto;
import com.ccsw.tutorial.lease.model.LeaseSearchDto;

/**
 * 
 * @author ccamposa
 *
 */
@Service
@Transactional
public class LeaseServiceImpl implements LeaseService {
    @Autowired
    LeaseRepository leaseRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    GameService gameService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Lease get(Long id) {
        return this.leaseRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Lease> findPage(LeaseSearchDto dto) {
        return this.leaseRepository.findAll(dto.getPageable());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, LeaseDto data) {
        Lease lease = null;

        if (id == null)
            lease = new Lease();
        else
            lease = this.get(id);

        BeanUtils.copyProperties(data, lease, "id", "game", "client");

        lease.setClient(clientService.get(data.getClient().getId()));
        lease.setGame(gameService.get(data.getGame().getId()));

        this.leaseRepository.save(lease);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) {
        this.leaseRepository.deleteById(id);
    }
}