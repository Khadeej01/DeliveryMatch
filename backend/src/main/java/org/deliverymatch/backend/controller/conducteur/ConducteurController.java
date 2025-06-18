package org.deliverymatch.backend.controller.conducteur;



import org.deliverymatch.backend.model.utilisateur.Conducteur;
import org.deliverymatch.backend.repository.ConducteurRepository;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/conducteurs")
public class ConducteurController {

    private final ConducteurRepository repository;

    public ConducteurController(ConducteurRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Conducteur> tousLesConducteurs() {
        return repository.findAll();
    }

    @PostMapping
    public Conducteur creerConducteur(@RequestBody Conducteur conducteur) {
        return repository.save(conducteur);
    }

    @GetMapping("/{id}")
    public Conducteur obtenirConducteur(@PathVariable Long id) {
        return repository.findById(id).orElseThrow();
    }
}