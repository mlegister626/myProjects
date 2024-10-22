import random


# Initialize population
def initialize_population(size, chrom_length=20):
    return [[random.randint(0, 1) for _ in range(chrom_length)] for _ in range(size)]


# Fitness function - counts the number of 1s in the chromosome
def fitness(chromosome):
    return sum(chromosome)


# Selection methods
# 1. Fitness proportional selection (roulette wheel)
def roulette_wheel_selection(population):
    total_fitness = sum(fitness(chrom) for chrom in population)
    selection_probs = [fitness(chrom) / total_fitness for chrom in population]
    return population[weighted_choice(selection_probs)]


def weighted_choice(weights):
    r = random.uniform(0, sum(weights))
    upto = 0
    for i, w in enumerate(weights):
        if upto + w >= r:
            return i
        upto += w
    return len(weights) - 1


# 2. Tournament selection
def tournament_selection(population):
    first = random.choice(population)
    second = random.choice(population)
    return first if fitness(first) > fitness(second) else second


# Crossover
def crossover(parent1, parent2, Pc):
    if random.random() < Pc:
        point = random.randint(1, len(parent1) - 1)  # Ensure crossover point is not 0
        return parent1[:point] + parent2[point:], parent2[:point] + parent1[point:]
    return parent1, parent2


# Mutation
def mutate(chromosome, Pm):
    return [bit if random.random() > Pm else 1 - bit for bit in chromosome]


# Genetic Algorithm
def genetic_algorithm(population_size, Pc, Pm, selection_method):
    population = initialize_population(population_size)
    generation = 0
    log_file = open('genetic_log.csv', 'w')

    while True:
        fitnesses = [fitness(chrom) for chrom in population]
        max_fitness = max(fitnesses)
        avg_fitness = sum(fitnesses) / len(fitnesses)
        min_fitness = min(fitnesses)

        log_file.write(f'{generation},{min_fitness},{avg_fitness},{max_fitness}\n')

        print(
            f"Generation {generation}: Min Fitness = {min_fitness}, Avg Fitness = {avg_fitness}, Max Fitness = {max_fitness}")

        if max_fitness == 20:
            print(f"Optimal chromosome found in generation {generation}")
            break

        new_population = []
        for _ in range(population_size // 2):
            if selection_method == 1:
                parent1 = roulette_wheel_selection(population)
                parent2 = roulette_wheel_selection(population)
            else:
                parent1 = tournament_selection(population)
                parent2 = tournament_selection(population)

            child1, child2 = crossover(parent1, parent2, Pc)
            new_population.append(mutate(child1, Pm))
            new_population.append(mutate(child2, Pm))

        population = new_population
        generation += 1

    log_file.close()


# User input and running the algorithm
if __name__ == "__main__":
    print("Marcello Legister")
    population_size = int(input("Enter population size: "))
    selection_method = int(input("Enter selection method (1 for Roulette Wheel, 2 for Tournament): "))
    Pc = float(input("Enter crossover probability (Pc): "))
    Pm = float(input("Enter mutation probability (Pm): "))

    genetic_algorithm(population_size, Pc, Pm, selection_method)

