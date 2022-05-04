CC = gcc
all: bin/main bin/list bin/skiplist_tests

INCLUDES = src/*.h

COMMON_DEPS = $(INCLUDES) Makefile

build/%.o: src/%.c $(COMMON_DEPS)
	$(CC) -c $< -o $@

bin/list: build/list.o build/main.o build/skip_list.o $(COMMON_DEPS)
	$(CC) -o bin/list build/list.o build/main.o build/skip_list.o 

bin/skiplist_tests: build/skiplist_tests.o build/list.o build/skip_list.o build/unity.o $(COMMON_DEPS)
	$(CC) -o bin/skiplist_tests build/skiplist_tests.o build/list.o build/skip_list.o build/unity.o

clean:
	rm -f build/* bin/*