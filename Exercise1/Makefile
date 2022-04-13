CC = gcc
all: bin/main bin/array

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) -c $< -o $@

bin/array: build/array.o build/main.o build/b_insertionsort.o build/quicksort.o $(COMMON_DEPS)
	$(CC) -o bin/array build/array.o build/main.o build/b_insertionsort.o build/quicksort.o

clean:
	rm -f build/* bin/*