package security.backend.c4g2.securityBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import security.backend.c4g2.securityBackEnd.models.Rol;
import security.backend.c4g2.securityBackEnd.repositories.RolRepository;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/roles")
public class RolController {

    @Autowired
    private RolRepository rolRepository;

    @GetMapping("")
    public List<Rol> index(){
        return this.rolRepository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Rol create(@RequestBody Rol infoRol){
        return rolRepository.save(infoRol);
    }

    @GetMapping("{id}")
    public Rol show(@PathVariable String id){
        return rolRepository.findById(id).orElse(null);
    }

    @PutMapping("{id}")
    public Rol update(@PathVariable String id, @RequestBody Rol infoRol){
        Rol currentRol = rolRepository.findById(id).orElse(null);
        if (currentRol != null) {
            currentRol.setName(infoRol.getName());
            currentRol.setDescription(infoRol.getDescription());
            return this.rolRepository.save(currentRol);
        }else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        Rol currentRol = this.rolRepository.findById(id).orElse(null);
        if (currentRol != null) {
            this.rolRepository.delete(currentRol);
        }
    }
}
