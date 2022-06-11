CC = gcc
all: bin/array bin/array_tests

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) -c $< -o $@

bin/array: build/array.o build/main.o build/b_insertionsort.o build/quicksort.o $(COMMON_DEPS)
	$(CC) -o bin/array build/array.o build/main.o build/b_insertionsort.o build/quicksort.o

bin/array_tests: build/array_tests.o build/array.o build/b_insertionsort.o build/quicksort.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/array_tests build/array_tests.o build/array.o build/b_insertionsort.o build/quicksort.o build/unity.o

clean:
	rm -f build/* bin/*