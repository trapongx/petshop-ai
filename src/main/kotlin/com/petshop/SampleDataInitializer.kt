package com.petshop

import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class SampleDataInitializer(
    private val petRepository: PetRepository,
) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        if (petRepository.count() > 0) return

        petRepository.save(Pet(name = "Milo", type = "cat", age = 3))
        petRepository.save(Pet(name = "Buddy", type = "dog", age = 5))
        petRepository.save(Pet(name = "Nibbles", type = "hamster", age = 1))
    }
}
