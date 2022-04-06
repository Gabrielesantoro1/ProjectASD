CC = gcc
all: bin/main bin/array

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) -c $< -o $@

bin/ordered_array: build/array.o build/main.o $(COMMON_DEPS)
	$(CC) -o bin/array build/array.o build/_main.o

clean:
	rm -f build/* bin/*