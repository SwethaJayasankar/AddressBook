package com.addressbook.controller;

import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;

	import java.util.*;

	@RestController
	@RequestMapping("/api/test")
	public class AddressBookController {

	    private Map<Long, Map<String, String>> dummyDB = new HashMap<>();
	    private Long idCounter = 1L;

	    // GET all
	    @GetMapping
	    public ResponseEntity<List<Map<String, String>>> getAll() {
	        return ResponseEntity.ok(new ArrayList<>(dummyDB.values()));
	    }

	    // GET by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Map<String, String>> getById(@PathVariable Long id) {
	        Map<String, String> data = dummyDB.get(id);
	        if (data != null) {
	            return ResponseEntity.ok(data);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // POST
	    @PostMapping
	    public ResponseEntity<Map<String, String>> create(@RequestBody Map<String, String> input) {
	        dummyDB.put(idCounter, input);
	        input.put("id", idCounter.toString());
	        idCounter++;
	        return ResponseEntity.ok(input);
	    }

	    // PUT (Update by ID)
	    @PutMapping("/{id}")
	    public ResponseEntity<Map<String, String>> update(@PathVariable Long id, @RequestBody Map<String, String> updatedData) {
	        if (dummyDB.containsKey(id)) {
	            updatedData.put("id", id.toString());
	            dummyDB.put(id, updatedData);
	            return ResponseEntity.ok(updatedData);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // DELETE
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        if (dummyDB.remove(id) != null) {
	            return ResponseEntity.ok().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	}



