package br.edu.ifpr.vhs.services;

import br.edu.ifpr.vhs.entities.Vhs;
import br.edu.ifpr.vhs.repositories.VhsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VhsService {

    @Autowired
    private VhsRepository vhsRepository;

    public List<Vhs> findAll() {
        return vhsRepository.findAll();
    }

    public Vhs findById(Long id) {
        return vhsRepository.findById(id).orElse(null);
}

    public void save(Vhs vhs) {
        vhsRepository.save(vhs);
}

    public void deleteById(Long id) {
        vhsRepository.deleteById(id);
}

}
