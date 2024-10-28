import random
import time
# Classes representing different entities
class TimePeriod:
    def __init__(self, period_id, days, time):
        self.period_id = period_id
        self.days = days
        self.time = time


class Room:
    def __init__(self, room_id, name, size, multimedia):
        self.room_id = room_id
        self.name = name
        self.size = size
        self.multimedia = multimedia


class Professor:
    def __init__(self, professor_id, name):
        self.professor_id = professor_id
        self.name = name


class Course:
    def __init__(self, crn, name, professor, size, needs_multimedia):
        self.crn = crn
        self.name = name
        self.professor = professor
        self.size = size
        self.needs_multimedia = needs_multimedia
        self.time_period = None
        self.room = None



class Chromosome:
    def __init__(self, courses, rooms, time_periods):
        self.schedule = {}  # Mapping course CRN to (room, time period)
        self.courses = courses
        self.rooms = rooms
        self.time_periods = time_periods
        self.fitness_score = None
        self.generate_schedule()

    def generate_schedule(self):
        """ Randomly assign a room and time period to each course """
        for course in self.courses:
            valid_assignments = self.get_valid_assignments(course)
            if valid_assignments:
                course.room, course.time_period = random.choice(valid_assignments)
                self.schedule[course] = (course.room, course.time_period)  # Store the course object

    def get_valid_assignments(self, course):
        """ Return valid (room, time period) assignments for a course based on hard constraints """
        valid_assignments = []
        used_room_time = set((room, time_period) for room, time_period in self.schedule.values())

        for room in self.rooms:
            for time_period in self.time_periods:
                # Ensure no other course is already scheduled in the same room at the same time
                if (room, time_period) in used_room_time:
                    continue

                # Room must be large enough and meet multimedia requirements
                if room.size >= course.size and (not course.needs_multimedia or room.multimedia):
                    valid_assignments.append((room, time_period))
        return valid_assignments

    def calculate_fitness(self):
        """ Calculate the fitness score based on soft rules """
        # Initial fitness based on room capacities
        fitness = sum(course.size - room.size for course, (room, time_period) in self.schedule.items())

        # Add penalties and bonuses for professor preferences
        fitness -= self.calculate_penalties()
        fitness += self.calculate_bonuses()

        self.fitness_score = fitness
        return fitness

    def calculate_penalties(self):
        """ Calculate penalties based on professor schedules """
        penalty = 0
        professor_courses = {}

        # Group courses by professor
        for course, (room, time_period) in self.schedule.items():
            if course.professor not in professor_courses:
                professor_courses[course.professor] = []
            professor_courses[course.professor].append(time_period)

        # Penalty for 3 or more consecutive courses and long breaks
        for professor, periods in professor_courses.items():
            periods.sort(key=lambda p: p.time)
            consecutive_courses = 0
            last_period_time = None

            for period in periods:
                if last_period_time is None or period.time == last_period_time + 1:
                    consecutive_courses += 1
                else:
                    consecutive_courses = 1  # Reset for a new block of consecutive courses

                if consecutive_courses >= 3:
                    penalty += 10  # Penalty for 3 or more consecutive courses

                if last_period_time is not None and period.time > last_period_time + 2:
                    penalty += 15  # Penalty for long breaks

                last_period_time = period.time

        return penalty

    def calculate_bonuses(self):
        """ Calculate bonuses for preferred room assignments """
        bonus = 0
        professor_rooms = {}

        # Group courses by professor and room
        for course, (room, time_period) in self.schedule.items():
            if course.professor not in professor_rooms:
                professor_rooms[course.professor] = []
            professor_rooms[course.professor].append(room)

        # Bonus for repeated room use by the same professor
        for professor, rooms in professor_rooms.items():
            room_count = {}
            for room in rooms:
                if room.room_id not in room_count:
                    room_count[room.room_id] = 0
                room_count[room.room_id] += 1
            for count in room_count.values():
                if count > 1:
                    bonus += (count - 1) * 5  # Bonus for repeated room use

        return bonus


# Initialize population with chromosomes
def initialize_population(courses, rooms, time_periods, n):
    return [Chromosome(courses, rooms, time_periods) for _ in range(n)]


# Fitness proportional selection using roulette wheel
def roulette_wheel(population):
    max_fitness = sum([chrom.calculate_fitness() for chrom in population])
    pick = random.uniform(0, max_fitness)
    current = 0
    for chrom in population:
        current += chrom.fitness_score
        if current > pick:
            return chrom


# Tournament selection
def tournament_selection(population):
    subset_size = max(1, int(0.25 * len(population)))
    subset = random.sample(population, subset_size)

    best_individual = max(subset, key=lambda individual: individual.calculate_fitness())

    return best_individual


# Crossover function
def crossover(parent1, parent2, pc):
    if random.random() < pc:
        point = random.randint(1, len(parent1.courses) - 1)
        child1 = Chromosome(parent1.courses[:point] + parent2.courses[point:], parent1.rooms, parent1.time_periods)
        child2 = Chromosome(parent2.courses[:point] + parent1.courses[point:], parent1.rooms, parent1.time_periods)
        return child1, child2
    return parent1, parent2


# Mutation function
def mutate(chromosome, pm):
    if random.random() < pm:
        # Randomly reassign a time period or room for one course
        course = random.choice(chromosome.courses)
        valid_assignments = chromosome.get_valid_assignments(course)
        if valid_assignments:
            course.room, course.time_period = random.choice(valid_assignments)


# Run the genetic algorithm
def genetic_algorithm(courses, rooms, time_periods, totalPopulation, generations, pc, pm):
    population = initialize_population(courses, rooms, time_periods, totalPopulation)
    best_chromosome_ever = None
    best_fitness = float('-inf')  # Track best fitness ever found
    best_generation_ever = 0  # Track which generation produced the best fitness

    for generation in range(generations):
        new_population = []
        best_fitness_current_gen = float('-inf')  # Track best fitness in the current generation
        best_chromosome_current_gen = None  # Track the best chromosome in the current generation

        # Find the best chromosome in the current generation
        for chrom in population:
            fitness = chrom.calculate_fitness()
            if fitness > best_fitness_current_gen:
                best_fitness_current_gen = fitness
                best_chromosome_current_gen = chrom

        # Compare the best fitness of the current generation with the best fitness ever
        if best_fitness_current_gen > best_fitness:
            best_fitness = best_fitness_current_gen
            best_chromosome_ever = best_chromosome_current_gen
            best_generation_ever = generation  # Update the generation where the best fitness was found

        # Stop if an optimal solution is found
        if best_fitness == 0:  # Assuming 0 is the optimal fitness
            break

        # Selection, Crossover, Mutation
        while len(new_population) < totalPopulation:
            parent1 = tournament_selection(population)
            parent2 = tournament_selection(population)
            child1, child2 = crossover(parent1, parent2, pc)
            mutate(child1, pm)
            mutate(child2, pm)
            new_population.append(child1)
            new_population.append(child2)

        # Replace old population with the new one
        population = new_population[:totalPopulation]

    # Output the best chromosome schedule
    print(f"Best schedule found in generation {best_generation_ever} with fitness {best_fitness}")
    for course, (room, time_period) in best_chromosome_ever.schedule.items():
        print(
            f"Class {course.crn}: {course.name} RoomID {room.room_id} "
            f"(Total Enrolled: {course.size}, Class Capacity: {room.size}) "
            f"at Time {time_period.days} {time_period.time}"
        )


def addTimePeriods(periods):
    for x in range(2,9):
        periods.append(TimePeriod(x,"M W F", x + 7))
    return periods

def addRoomList(rooms):
    rooms.append(Room(1,"BL134", 30, True))
    rooms.append(Room(2,"BL138", 50, True))
    rooms.append(Room(3,"KR224", 40, False))
    rooms.append(Room(4,"KR206", 30, True))
    rooms.append(Room(5,"Biddle123", 35, False))
    rooms.append(Room(6,"Biddle205", 45, False))
    rooms.append(Room(7,"ES100", 100, True))
    return rooms

def addCourses(course_list):
    course_list.append(Course(1, "cs456", "Bilitski", 20, True))
    course_list.append(Course(2, "cs456", "Bilitski", 20, True))
    course_list.append(Course(3, "cs1783", "Bilitski", 15, True))
    course_list.append(Course(4, "cs455", "Ohl", 20, True))
    course_list.append(Course(5, "cs455", "Ohl", 20, True))
    course_list.append(Course(6, "cs015", "Sandro", 35, True))
    course_list.append(Course(7, "cs015", "Mr xxx", 35, False))
    course_list.append(Course(8, "cs015", "Mr xxx", 35, False))
    course_list.append(Course(9, "cs015", "Frederick", 35, False))
    course_list.append(Course(10, "math001", "Peter", 40, False))
    course_list.append(Course(11, "math001", "Peter", 50, False))
    course_list.append(Course(12, "math001", "Peter", 60, False))
    course_list.append(Course(13, "math002", "Brian", 40, False))
    course_list.append(Course(14, "math002", "Brian", 50, False))
    course_list.append(Course(15, "math002", "Brian", 60, False))
    course_list.append(Course(16, "Soc100", "Meg", 45, True))
    course_list.append(Course(17, "Soc100", "Meg", 40, True))
    course_list.append(Course(18, "Soc100", "Meg", 35, True))
    course_list.append(Course(19, "CS047", "Stewie", 15, True))
    course_list.append(Course(20, "CS047", "Stewie", 15, True))
    course_list.append(Course(21, "PSY200", "Glen", 30, False))
    course_list.append(Course(22, "PSY200", "Glen", 35, False))
    course_list.append(Course(23, "PSY200", "Glen", 30, False))
    course_list.append(Course(24, "cs045", "Ohl", 20, True))
    course_list.append(Course(25, "cs045", "Ohl", 20, True))
    course_list.append(Course(26, "cs015", "Sandro", 20, True))
    return course_list
def addProfessors(professors):
    professors = {
        "Bilitski": Professor(1, "Bilitski"),
        "Ohl": Professor(2, "Ohl"),
        "Sandro": Professor(3, "Sandro"),
        "Mr xxx": Professor(4, "Mr xxx"),
        "Frederick": Professor(5, "Frederick"),
        "Peter": Professor(6, "Peter"),
        "Brian": Professor(7, "Brian"),
        "Meg": Professor(8, "Meg"),
        "Stewie": Professor(9, "Stewie"),
        "Glen": Professor(10, "Glen")
    }
    return professors


# Run the example
if __name__ == "__main__":
    periodList = []
    periodList = addTimePeriods(periodList)
    roomsList = []
    roomsList = addRoomList(roomsList)
    courselist = []
    courselist = addCourses(courselist)

    # Prompt the user for input values
    population = int(input("Enter population size : "))
    max_generations = int(input("Enter maximum generations : "))
    pc = float(input("Enter crossover probability : "))
    pm = float(input("Enter mutation probability : "))

    # Run the genetic algorithm with the user-provided inputs
    genetic_algorithm(courselist, roomsList, periodList, population, generations=max_generations, pc=pc, pm=pm)