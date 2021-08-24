package co.com.personalsoft.pruebaml.rest;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.personalsoft.pruebaml.impl.IMutantImpl;
import co.com.personalsoft.pruebaml.impl.IRegistrosImpl;
import co.com.personalsoft.pruebaml.model.ADNDTO;
import co.com.personalsoft.pruebaml.model.MutantEntity;

@RestController
@RequestMapping("/mutant")
public class MutantRestApi {
	
	@Autowired
	private IMutantImpl iMutantImpl;
	@Autowired
	private IRegistrosImpl iRegistroImpl;
	
	@PostMapping("/")
	public ResponseEntity mutant(@RequestBody String[] dna) {
		if (iMutantImpl.isMutant(dna)) {
			return ResponseEntity.status(HttpStatus.OK)
			        .body(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
        .body(HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/stats")
	public ADNDTO stats() {
		List<MutantEntity> list = iRegistroImpl.findAll();
		ADNDTO adn = new ADNDTO();
		adn.setCountHumanDna(list.stream().filter(x -> x.getMutante() == (short)0).collect(Collectors.toList()).size());
		adn.setCountMutantDna(list.stream().filter(x -> x.getMutante() == (short)1).collect(Collectors.toList()).size());
		return adn;
	}
}
