package com.petshop

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/pets")
class PetController(
    private val petRepository: PetRepository,
) {
    @GetMapping
    fun list(): List<Pet> {
        return petRepository.findAll()
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Pet> {
        val pet = petRepository.findById(id).orElse(null) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(pet)
    }

    @PostMapping
    fun create(
        @RequestBody pet: Pet,
        uriBuilder: UriComponentsBuilder,
    ): ResponseEntity<Pet> {
        val toSave = Pet(
            id = null,
            name = pet.name,
            type = pet.type,
            age = pet.age,
        )

        val saved = petRepository.save(toSave)
        val location = uriBuilder.path("/pets/{id}").buildAndExpand(saved.id).toUri()

        return ResponseEntity.status(HttpStatus.CREATED).location(location).body(saved)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody pet: Pet,
    ): ResponseEntity<Pet> {
        val existing = petRepository.findById(id).orElse(null) ?: return ResponseEntity.notFound().build()

        existing.name = pet.name
        existing.type = pet.type
        existing.age = pet.age

        val saved = petRepository.save(existing)
        return ResponseEntity.ok(saved)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        if (!petRepository.existsById(id)) {
            return ResponseEntity.notFound().build()
        }

        petRepository.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}

