CC = gcc
all: bin/main bin/list bin/list_tests

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) -c $< -o $@

bin/list: build/list.o build/main.o build/skip_list.o $(COMMON_DEPS)
	$(CC) -o bin/list build/list.o build/main.o build/skip_list.o 

clean:
	rm -f build/* bin/*