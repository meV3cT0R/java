package com.vector.megumin;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api",produces = "application/json")
@CrossOrigin(origins="*")

public class MeguminController {
    private MeguminRepository meguminRepo;

    public MeguminController(MeguminRepository meguminRepo) {
        this.meguminRepo = meguminRepo;
    }

    @GetMapping("megumins")
    public Iterable<Megumin> megumins() {
        PageRequest page = PageRequest.of(0,12);
        return meguminRepo.findAll(page).getContent();
    }
    @GetMapping("/megumins/{id}")
    public ResponseEntity<Megumin> meguminById(@PathVariable("id") long id) {
        Optional<Megumin> megumin = meguminRepo.findById(id);
        if(megumin.isPresent()) return new ResponseEntity<>(megumin.get(),HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }


    @PostMapping(path="/megumins/add",consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Megumin saveMegumin(@RequestBody Megumin megumin) {
        return meguminRepo.save(megumin);
    }

    @PutMapping(path="/megumins/update")
    public Megumin updateMegumin(@RequestBody Megumin megumin) {
        return meguminRepo.save(megumin);
    }

    @DeleteMapping(path="/megumins/delete/{id}")
    public void deleteMegumin(@PathVariable("id") long id) {
        meguminRepo.deleteById(id);
    }

    @GetMapping(path="/megumins/totalmegumin",produces = "application/json")
    public TotalCount totalMegumin() {
        TotalCount totalCount = new TotalCount();
        totalCount.setTotalCount(meguminRepo.findAll().size());
        return totalCount;
    }
}
